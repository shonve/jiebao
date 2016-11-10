package cn.aage.robot.sdk.test;

import cn.ucloud.ufile.UFileClient;
import cn.ucloud.ufile.UFileConfig;
import cn.ucloud.ufile.UFileRequest;
import cn.ucloud.ufile.UFileResponse;
import cn.ucloud.ufile.sender.DeleteSender;
import cn.ucloud.ufile.sender.GetSender;
import cn.ucloud.ufile.sender.PrefixSender;
import org.apache.http.Header;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 下载文件测试
 *
 * @author york
 */
public class PrefixFileListTest {
    public static void main(String args[]) {
        String bucketName = "shonve";
        String prefix = "";
        String marker = "";
        String configPath = "E:\\project\\other\\jiebao\\src\\main\\resources\\config.properties";

        //加载配置项
        UFileConfig.getInstance().loadConfig(configPath);

        UFileRequest request = new UFileRequest();
        request.setBucketName(bucketName);
        request.setKey("");

        UFileClient ufileClient = null;
        try {
            ufileClient = new UFileClient();
            getFile(ufileClient, request);
        } finally {
            ufileClient.shutdown();
        }
    }

    private static void getFile(UFileClient ufileClient, UFileRequest request) {

        PrefixSender sender = new PrefixSender();
        sender.makeAuth(ufileClient, request);

        UFileResponse response = sender.send(ufileClient, request);
        if (response != null) {
            System.out.println("status line: " + response.getStatusLine());
            Header[] headers = response.getHeaders();
            for (int i = 0; i < headers.length; i++) {
                System.out.println("header " + headers[i].getName() + " : " + headers[i].getValue());
            }

            System.out.println("body length: " + response.getContentLength());

            if (response.getContent() != null) {
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new InputStreamReader(response.getContent()));
                    String input;
                    while ((input = br.readLine()) != null) {
                        System.out.println(input);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}

