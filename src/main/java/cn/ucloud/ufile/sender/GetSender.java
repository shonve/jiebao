package cn.ucloud.ufile.sender;

import cn.ucloud.ufile.UFileClient;
import cn.ucloud.ufile.UFileConfig;
import cn.ucloud.ufile.UFileRequest;
import cn.ucloud.ufile.UFileResponse;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public class GetSender implements Sender {

    public GetSender() {

    }

    @Override
    public void makeAuth(UFileClient client, UFileRequest request) {
        String httpMethod = "GET";
        String contentMD5 = request.getContentMD5();
        String contentType = request.getContentType();
        String date = request.getDate();
        String canonicalizedUcloudHeaders = client.spliceCanonicalHeaders(request);
        String canonicalized_resource = "/" + request.getBucketName() + "/" + request.getKey();
        String stringToSign = httpMethod + "\n" + contentMD5 + "\n" + contentType + "\n" + date + "\n" +
                canonicalizedUcloudHeaders + canonicalized_resource;
        client.makeAuth(stringToSign, request);
    }

    @Override
    public UFileResponse send(UFileClient ufileClient, UFileRequest request) {
        String uri = "";
        try {
            uri = "http://" + request.getBucketName() + UFileConfig.getInstance().getDownloadProxySuffix()
                    + "/" + URIUtil.encodeWithinPath(request.getKey(), UTF8);
        } catch (URIException e) {
            e.printStackTrace();
        }
        HttpGet getMethod = new HttpGet(uri);

        HttpEntity resEntity = null;
        try {
            Map<String, String> headers = request.getHeaders();
            if (headers != null) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    getMethod.setHeader(entry.getKey(), entry.getValue());
                }
            }

            HttpResponse httpResponse = ufileClient.getHttpClient().execute(getMethod);

            resEntity = httpResponse.getEntity();

            UFileResponse ufileResponse = new UFileResponse();
            ufileResponse.setStatusLine(httpResponse.getStatusLine());
            ufileResponse.setHeaders(httpResponse.getAllHeaders());

            if (resEntity != null) {
                ufileResponse.setContentLength(resEntity.getContentLength());
                ufileResponse.setContent(resEntity.getContent());
            }
            return ufileResponse;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (resEntity != null && resEntity.getContent() != null) {
                    resEntity.getContent().close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
}
