package cn.aage.robot.ucloud;

import java.util.Date;

public class DownloadUrl {

    public static final String UCLOUD_PROXY_SUFFIX = ".ufile.ucloud.cn";
    public static final String UTF8 = "UTF-8";

    public DownloadUrl() {

    }

    public String getUrl(UFileClient uFileClient, UFileRequest request, int ttl, boolean isPrivate) {
        if (!isPrivate) {
            try {
                return "http://" + request.getBucketName() + uFileClient.getDownloadProxySuffix()
                        + "/" + request.getKey().getBytes("UTF8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return makeDownloadUrl(uFileClient, request, ttl);
    }

    public String makeSignature(UFileClient client, UFileRequest request, long expires) {
        String httpMethod = "GET";
        String contentMD5 = request.getContentMD5();
        String contentType = request.getContentType();
        String canonicalUCloudHeaders = client.spliceCanonicalHeaders(request);
        String canonicalResource = "/" + request.getBucketName() + "/" + request.getKey();
        String expire_str = "";
        if (expires > 0) {
            expire_str = String.valueOf(expires);
        }
        String stringToSign = httpMethod + "\n" + contentMD5 + "\n" + contentType + "\n" + expire_str + "\n" +
                canonicalUCloudHeaders + canonicalResource;

        String signature = new HmacSHA1().sign(client.getUCloudPrivateKey(), stringToSign);
        return signature;
    }

    public String makeDownloadUrl(UFileClient ufileClient,
                                  UFileRequest request, int ttl) {
        String expire_str = "";
        long expires = 0;
        if (ttl > 0) {
            expires = (new Date().getTime() / 1000) + ttl;
            expire_str = String.valueOf(expires);
        }
        String signature = makeSignature(ufileClient, request, expires);
        String url = "";
        try {
//            url = "http://" + request.getBucketName() + ufileClient.getDownloadProxySuffix()
//                    + URIUtil.encodePath("/" + request.getKey(), UTF8)
//                    + "?" + URIUtil.encodeWithinQuery("UCloudPublicKey", UTF8)
//                    + "=" + URIUtil.encodeWithinQuery(ufileClient.getUCloudPublicKey(), UTF8)
//                    + "&" + URIUtil.encodeWithinQuery("Expires", UTF8)
//                    + "=" + URIUtil.encodeWithinQuery(expire_str, UTF8)
//                    + "&" + URIUtil.encodeWithinQuery("Signature", UTF8)
//                    + "=" + URIUtil.encodeWithinQuery(signature, UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

}
