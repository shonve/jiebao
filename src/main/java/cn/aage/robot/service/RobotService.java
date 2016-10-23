package cn.aage.robot.service;

import cn.aage.robot.model.ApiInterface;
import cn.aage.robot.model.Message;
import cn.aage.robot.model.QQConfig;
import cn.aage.robot.repository.ApiInterfaceRepository;
import cn.aage.robot.repository.MessageRepository;
import cn.aage.robot.repository.QQConfigRepository;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by shonve on 2016/10/21.
 */
@Service("robotService")
public class RobotService {
    private static final Logger LOGGER = Logger.getLogger(RobotService.class.getName());

    @Autowired
    private QQConfigRepository qqConfigRepository;
    @Autowired
    private ApiInterfaceRepository apiInterfaceRepository;
    @Autowired
    private Md5Service md5Service;
    @Autowired
    private MessageRepository messageRepository;

    public String getReplyContent(String username, String sendMsg) {
        String content = "";
        String msgSource = "";

        if ((sendMsg.contains("解密") && sendMsg.toUpperCase().contains("MD5")) || sendMsg.toUpperCase().contains("@MD5")) {
            content = md5Service.getDecodeMd5(sendMsg.toUpperCase().replace("解密", "").replace("MD5", "").replace("@", "").trim());
            return content;
        }


        QQConfig config = qqConfigRepository.findByConfigName("enabled_robot");

        if (null != config) {
            ApiInterface api = apiInterfaceRepository.findByCode(config.getConfigValue());
            msgSource = api.getName();

            String result = "";
            if (config.getConfigValue().equals("tuling")) {
                try {
                    String url = api.getApiUrl() + "?" + "key=" + URLEncoder.encode(api.getApiKey(), "UTF-8")
                            + "&info=" + URLEncoder.encode(sendMsg, "UTF-8")
                            + "&userid=" + URLEncoder.encode(username, "UTF-8");

                    HttpClient httpClient = HttpClientBuilder.create().build();
                    HttpGet get = new HttpGet(url);
                    HttpResponse response = httpClient.execute(get);
                    if (response.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = response.getEntity();
                        if (entity != null) {
                            result = EntityUtils.toString(entity, "UTF-8");
                        }
                    }
                    if (null != result) {
                        final JSONObject data = new JSONObject(result);
                        final int code = data.optInt("code");

                        switch (code) {
                            case 40001:
                            case 40002:
                            case 40007:
                                LOGGER.error(data.optString("text"));
                                break;
                            case 40004:
                                content = "聊累了，明天请早吧~";
                                break;
                            case 100000:
                                content = data.optString("text");
                                break;
                            case 200000:
                                content = data.optString("text") + " " + data.optString("url");
                                break;
                            case 302000:
                                String ret302000 = data.optString("text") + " ";
                                JSONArray list302000 = data.optJSONArray("list");
                                StringBuilder builder302000 = new StringBuilder();
                                for (int i = 0; i < list302000.length(); i++) {
                                    JSONObject news = list302000.optJSONObject(i);
                                    builder302000.append(news.optString("article")).append(news.optString("detailurl"))
                                            .append("\n\n");
                                }
                                content = ret302000 + " " + builder302000.toString();

                                break;
                            case 308000:
                                String ret308000 = data.optString("text") + " ";
                                final JSONArray list308000 = data.optJSONArray("list");
                                final StringBuilder builder308000 = new StringBuilder();
                                for (int i = 0; i < list308000.length(); i++) {
                                    final JSONObject news = list308000.optJSONObject(i);
                                    builder308000.append(news.optString("name")).append(news.optString("detailurl"))
                                            .append("\n\n");
                                }
                                content = ret308000 + " " + builder308000.toString();
                                break;
                            default:
                                LOGGER.warn("Turing Robot default return [" + data.toString(4) + "]");
                        }
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                }
                content = content.replace("图灵", "洁宝").replace("默认机器人", "洁宝机器人").replace("<br>", "\n");
            } else if (config.getConfigValue().equals("moli")) {
                try {
                    final String url = api.getApiUrl() + "?" + "api_key=" + URLEncoder.encode(api.getApiKey(), "UTF-8")
                            + "&limit=8" + "&api_secret=" + URLEncoder.encode(api.getApiSecret(), "UTF-8")
                            + "&question=" + URLEncoder.encode(sendMsg, "UTF-8");

                    HttpClient httpClient = HttpClientBuilder.create().build();
                    HttpGet get = new HttpGet(url);
                    HttpResponse response = httpClient.execute(get);
                    if (response.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = response.getEntity();
                        if (entity != null) {
                            result = EntityUtils.toString(entity, "UTF-8");
                        }
                    }
                    if (null != result) {
                        content = result.substring(1);
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
            saveMessage(msgSource, username, sendMsg, content);
        }
        return content;
    }

    private void saveMessage(String msgSource, String username, String sendMsg, String replyMsg) {
        Message message = messageRepository.findBySendContentAndReplyContent(sendMsg, replyMsg);
        if (null == message) {
            message = new Message();
            message.setCreateTime(new Date());
            message.setSendContent(sendMsg);
            message.setReplyContent(replyMsg);
            message.setMsgSource(msgSource);
            message.setSendUser(username);
            messageRepository.save(message);
        }
    }
}
