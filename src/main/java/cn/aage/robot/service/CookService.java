package cn.aage.robot.service;

import cn.aage.robot.model.cook.CookAlbum;
import cn.aage.robot.model.cook.CookBook;
import cn.aage.robot.model.cook.CookStep;
import cn.aage.robot.model.cook.CookStyle;
import cn.aage.robot.repository.cook.CookAlbumRepository;
import cn.aage.robot.repository.cook.CookBookRepository;
import cn.aage.robot.repository.cook.CookStepRepository;
import cn.aage.robot.repository.cook.CookStyleRepository;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shonve on 2016/10/21.
 */
@Service("cookService")
public class CookService {

    @Autowired
    private CookAlbumRepository cookAlbumRepository;
    @Autowired
    private CookBookRepository cookBookRepository;
    @Autowired
    private CookStepRepository cookStepRepository;
    @Autowired
    private CookStyleRepository cookStyleRepository;

    public void saveCookBook(String name) {
        String result = "";
        try {
            String url = "http://apis.juhe.cn/cook/query.php?" + "key=bc0332edb6486141c03e8d88c308b66e&menu=" + URLEncoder.encode(name, "UTF-8");

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
                JSONObject data = new JSONObject(result);

                if (data.getString("resultcode").equals("200")) {
                    JSONObject re = data.getJSONObject("result");
                    JSONArray jsonArray = re.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject en = jsonArray.getJSONObject(i);

                        CookBook cookbook = cookBookRepository.findByName(en.getString("title"));
                        if (null == cookbook) {
                            cookbook = new CookBook();
                        }
                        cookbook.setCreateTime(new Date());
                        cookbook.setName(en.getString("title"));
                        cookbook.setIngredients(en.getString("ingredients"));
                        cookbook.setSeasoning(en.getString("burden"));
                        cookbook.setTags(en.getString("tags"));
                        cookbook.setIntroduction(en.getString("imtro"));
                        cookBookRepository.save(cookbook);

                        JSONArray albums = en.getJSONArray("albums");
                        CookAlbum aaa = new CookAlbum();
                        aaa.setCreateTime(new Date());
                        aaa.setCookBook(cookbook);
                        aaa.setImageUrl(albums.get(0).toString());
                        cookAlbumRepository.save(aaa);

                        List<CookStep> ss = new ArrayList<>();
                        JSONArray steps = en.getJSONArray("steps");
                        for (int j = 0; j < steps.length(); j++) {
                            JSONObject step = steps.getJSONObject(j);
                            CookStep a = new CookStep();
                            a.setCreateTime(new Date());
                            a.setCookBook(cookbook);
                            a.setImageUrl(step.getString("img"));
                            a.setOperate(step.getString("step"));
                            a.setSort(j + 1);
                            ss.add(a);
                        }
                        cookStepRepository.save(ss);
                    }
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    public void saveStyle() {
        String result = "";
        try {
            String url = "http://apis.juhe.cn/cook/category?" + "key=bc0332edb6486141c03e8d88c308b66e";

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
                JSONObject data = new JSONObject(result);

                if (data.getString("resultcode").equals("200")) {
                    JSONArray jsonArray = data.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject en = jsonArray.getJSONObject(i);

                        CookStyle cookStyle = cookStyleRepository.findByName(en.getString("name"));
                        if (null == cookStyle) {
                            cookStyle = new CookStyle();
                        }
                        cookStyle.setCreateTime(new Date());
                        cookStyle.setName(en.getString("name"));
                        cookStyle.setTmpId(en.getInt("parentId"));
                        cookStyleRepository.save(cookStyle);

                        List<CookStyle> ss = new ArrayList<>();
                        JSONArray steps = en.getJSONArray("list");
                        for (int j = 0; j < steps.length(); j++) {
                            JSONObject step = steps.getJSONObject(j);
                            CookStyle a = new CookStyle();
                            a.setCreateTime(new Date());
                            a.setName(step.getString("name"));
                            a.setTmpId(step.getInt("id"));
                            a.setParent(cookStyle);
                            ss.add(a);
                        }
                        cookStyleRepository.save(ss);
                    }
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    public void saveStyle1() {
        String result = "";
        try {
            String url = "http://apis.juhe.cn/cook/index?" + "key=bc0332edb6486141c03e8d88c308b66e";

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
                JSONObject data = new JSONObject(result);

                if (data.getString("resultcode").equals("200")) {
                    JSONArray jsonArray = data.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject en = jsonArray.getJSONObject(i);

                        CookStyle cookStyle = cookStyleRepository.findByName(en.getString("name"));
                        if (null == cookStyle) {
                            cookStyle = new CookStyle();
                        }
                        cookStyle.setCreateTime(new Date());
                        cookStyle.setName(en.getString("name"));
                        cookStyle.setTmpId(en.getInt("parentId"));
                        cookStyleRepository.save(cookStyle);

                        List<CookStyle> ss = new ArrayList<>();
                        JSONArray steps = en.getJSONArray("list");
                        for (int j = 0; j < steps.length(); j++) {
                            JSONObject step = steps.getJSONObject(j);
                            CookStyle a = new CookStyle();
                            a.setCreateTime(new Date());
                            a.setName(step.getString("name"));
                            a.setTmpId(step.getInt("id"));
                            a.setParent(cookStyle);
                            ss.add(a);
                        }
                        cookStyleRepository.save(ss);
                    }
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
