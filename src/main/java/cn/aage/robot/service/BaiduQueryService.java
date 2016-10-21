package cn.aage.robot.service;

//
//@Service("baiduQueryService")
//public class BaiduQueryService {
//
//    private static final Logger LOGGER = Logger.getLogger(BaiduQueryService.class.getName());
//
//
//    public String chat(String msg) {
//        if (StringUtils.isBlank(msg)) {
//            return null;
//        }
//
//        String BAIDU_URL = "https://sp0.baidu.com/yLsHczq6KgQFm2e88IuM_a/s?sample_name=bear_brain&request_query=#MSG#&bear_type=2";
//        try {
//            BAIDU_URL = BAIDU_URL.replace("#MSG#", URLEncoder.encode(msg, "UTF-8"));
//        } catch (final UnsupportedEncodingException e) {
//            LOGGER.error("Chat with Baidu Robot failed", e);
//            return null;
//        }
//
//        String result = null;
//        try {
//            HttpClient httpClient = HttpClientBuilder.create().build();
//
//            HttpPost post = new HttpPost(BAIDU_URL);
//            List<NameValuePair> list = new ArrayList<>();
////            list.add(new BasicNameValuePair("Cookie", BAIDU_COOKIE));
//
//            HttpResponse response = httpClient.execute(post);
//            if (response != null) {
//                HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    result = EntityUtils.toString(entity, "UTF-8");
//                }
//            }
//            if (null != result) {
//                final JSONObject data = new JSONObject(result);
//                final String content = (String) data.getJSONArray("result_list").getJSONObject(0).get("result_content");
//                String ret = (String) new JSONObject(content).get("answer");
//                ret = ret.replaceAll("小度", "shonve");
//
//                return ret;
//            }
//        } catch (final Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//}
