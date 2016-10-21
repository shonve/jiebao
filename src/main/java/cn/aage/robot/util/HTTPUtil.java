package cn.aage.robot.util;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by john on 2016/10/20.
 */
public class HTTPUtil {

    private static SSLConnectionSocketFactory socketFactory;//私密连接工厂

    private static TrustManager manager = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };

    private static void enableSSL() {
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new TrustManager[]{manager}, null);
            socketFactory = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CloseableHttpResponse httpGet(String url, String cookie, String refer) throws IOException {
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();//设置全局的标准cookie策略
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();//设置可关闭的httpclient

        //发送get请求
        HttpGet get = new HttpGet(url);
        if (null != cookie) {
            get.setHeader("Cookie", cookie);
        }
        if (null != refer) {
            get.setHeader("Referer", refer);
        }
        CloseableHttpResponse response = httpClient.execute(get);
        return response;
    }

    public static CloseableHttpResponse httpPost(String url, List<NameValuePair> valuePairs, String cookie, String refer) throws IOException {
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();//设置全局的标准cookie策略
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();//设置可关闭的httpclient

        HttpPost post = new HttpPost(url);
        if (null != cookie) {
            post.setHeader("Cookie", cookie);
        }
        if (null != refer) {
            post.setHeader("Referer", refer);
        }
        if (null != valuePairs) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs, "UTF-8");
            post.setEntity(entity);
        }
        CloseableHttpResponse response = httpClient.execute(post);
        return response;

    }

    public static CloseableHttpResponse httpsGet(String url, String cookie, String refer) throws IOException {
        enableSSL();
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST)).setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();//设置全局的标准cookie策略

//        ConnectionSocketFactory socketFactory = PlainConnectionSocketFactory.getSocketFactory();
//        LayeredConnectionSocketFactory connectionSocketFactory = SSLConnectionSocketFactory.getSocketFactory();
//        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", socketFactory)
//                .register("https", connectionSocketFactory)
//                .build();

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        // 将最大连接数增加到200
        connectionManager.setMaxTotal(200);
        // 将每个路由基础的连接增加到20
        connectionManager.setDefaultMaxPerRoute(20);
//        // 将目标主机的最大连接数增加到50
//        HttpHost localhost = new HttpHost("http://blog.csdn.net/gaolu",80);
//        connectionManager.setMaxPerRoute(new HttpRoute(localhost), 50);

//        RequestConfig.Builder configBuilder = RequestConfig.custom();
//        // 设置连接超时
//        configBuilder.setConnectTimeout(MAX_TIMEOUT);
//        // 设置读取超时
//        configBuilder.setSocketTimeout(MAX_TIMEOUT);
//        // 设置从连接池获取连接实例的超时
//        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
//        // 在提交请求之前 测试连接是否可用
//        configBuilder.setStaleConnectionCheckEnabled(true);
//        config = configBuilder.build();


        //请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= 5) {// 如果已经重试了5次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (exception instanceof SSLException) {// ssl握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).setRetryHandler(httpRequestRetryHandler).setDefaultRequestConfig(config).build();//设置可关闭的httpclient

        //发送get请求
        HttpGet get = new HttpGet(url);
        if (null != cookie) {
            get.setHeader("Cookie", cookie);
        }
        if (null != refer) {
            get.setHeader("Referer", refer);
        }
        CloseableHttpResponse response = httpClient.execute(get);
        return response;
    }

    public static CloseableHttpResponse httpsPost(String url, List<NameValuePair> valuePairs, String cookie, String refer) throws IOException {
        enableSSL();
        RequestConfig config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST)).setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();//设置全局的标准cookie策略

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.INSTANCE).register("https", socketFactory).build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(config).build();//设置可关闭的httpclient

        HttpPost post = new HttpPost(url);
        if (null != cookie) {
            post.setHeader("Cookie", cookie);
        }
        if (null != refer) {
            post.setHeader("Referer", refer);
        }
        if (null != valuePairs) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs, "UTF-8");
            post.setEntity(entity);
        }
        CloseableHttpResponse response = httpClient.execute(post);
        return response;

    }

    private static void config(HttpRequestBase httpRequestBase) {
        httpRequestBase.setHeader("User-Agent", "Mozilla/5.0");
        httpRequestBase.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpRequestBase.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");//"en-US,en;q=0.5");
        httpRequestBase.setHeader("Accept-Charset", "ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7");

        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(3000)
                .setConnectTimeout(3000)
                .setSocketTimeout(3000)
                .build();
        httpRequestBase.setConfig(requestConfig);
    }


}
