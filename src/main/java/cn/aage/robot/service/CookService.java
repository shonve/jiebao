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


    public void saveCook(String name, int pn) {
        String result = "";
        int total = 0;
        try {
            String url = "http://apis.juhe.cn/cook/query.php?" + "key=bc0332edb6486141c03e8d88c308b66e&menu=" + URLEncoder.encode(name, "UTF-8") + "&rn=30" + "&pn=" + pn;

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
                    total = re.getInt("totalNum");
                    pn = re.getInt("pn");//当前偏移
                    System.out.println("---总数：" + total + "-----偏移：" + pn);

                    JSONArray jsonArray = re.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject en = jsonArray.getJSONObject(i);

                        CookBook cookbook = null;
                        List<CookBook> cookbooks = cookBookRepository.findByNameAndTmpIdAndIntroduction(en.getString("title"), en.getInt("id"), en.getString("imtro"));
                        if (null != cookbooks && cookbooks.size() > 0) {
                            cookbook = cookbooks.get(0);
                        } else {
                            cookbook = new CookBook();
                        }
                        cookbook.setCreateTime(new Date());
                        cookbook.setName(en.getString("title"));
                        cookbook.setIngredients(en.getString("ingredients"));
                        cookbook.setSeasoning(en.getString("burden"));
                        cookbook.setTags(en.getString("tags"));
                        cookbook.setIntroduction(en.getString("imtro"));
                        cookbook.setTmpId(en.getInt("id"));
                        cookBookRepository.save(cookbook);


                        List<CookAlbum> aaaa = cookAlbumRepository.findByCookBookId(cookbook.getId());
                        if (null == aaaa || aaaa.size() == 0) {
                            JSONArray albums = en.getJSONArray("albums");
                            CookAlbum aaa = new CookAlbum();
                            aaa.setCreateTime(new Date());
                            aaa.setCookBook(cookbook);
                            aaa.setImageUrl(albums.get(0).toString());
                            cookAlbumRepository.save(aaa);
                        }

                        List<CookStep> sssss = cookStepRepository.findByCookBookId(cookbook.getId());
                        if (null == sssss || sssss.size() == 0) {
                            List<CookStep> ss = new ArrayList<>();
                            if (en.get("steps") instanceof JSONArray) {
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
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        if (pn != 0) {
            if (total - pn > 30) {
                pn += 30;
                saveCook(name, pn);
            }
        } else {
            pn += 30;
            saveCook(name, pn);
        }

    }

    public void saveStyle1(int pn) {
        List<CookStyle> list = cookStyleRepository.findAll();
        for (CookStyle style : list) {
            if (null != style.getParent()) {
                if (style.getId().intValue() >= 240) {
                    saveCC(0, style);
                }
            }
        }
    }

    public void saveCC(int pn, CookStyle style) {

        int total = 0;
        System.out.println("---处理ID：" + style.getTmpId());

        try {
            String result = "";
            String url = "http://apis.juhe.cn/cook/index?" + "key=b28a5772b9d71f56037550ae73a2ad77&cid=" + style.getTmpId() + "&rn=30&pn=" + pn;
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
                    JSONObject ob = data.getJSONObject("result");
                    total = ob.getInt("totalNum");
                    pn = ob.getInt("pn");//当前偏移
                    System.out.println("---总数：" + total + "-----偏移：" + pn);

                    JSONArray asss = ob.getJSONArray("data");
                    for (int i = 0; i < asss.length(); i++) {
                        JSONObject en = asss.getJSONObject(i);

                        CookBook cookbook = new CookBook();
                        cookbook.setCreateTime(new Date());
                        cookbook.setName(en.getString("title"));
                        cookbook.setIngredients(en.getString("ingredients"));
                        cookbook.setSeasoning(en.getString("burden"));
                        cookbook.setTags(en.getString("tags"));
                        cookbook.setIntroduction(en.getString("imtro"));
                        cookbook.setTmpId(en.getInt("id"));
                        cookbook.setCookStyle(style);
                        cookBookRepository.save(cookbook);


                        JSONArray albums = en.getJSONArray("albums");
                        CookAlbum aaa = new CookAlbum();
                        aaa.setCreateTime(new Date());
                        aaa.setCookBook(cookbook);
                        aaa.setImageUrl(albums.get(0).toString());
                        cookAlbumRepository.save(aaa);

                        List<CookStep> ss = new ArrayList<>();
                        if (en.get("steps") instanceof JSONArray) {
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
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        if (pn != 0) {
            if (total - pn > 30) {
                pn += 30;
                saveCC(pn, style);
            }
        } else {
            pn += 30;
            saveCC(pn, style);
        }

    }

    public void arrange() {
        List<CookBook> c = cookBookRepository.findAll();
        List<CookBook> w = new ArrayList<>();

        for (int i = 7949; i < c.size(); i++) {
            CookBook a = c.get(i);
            System.out.println("主ID：" + a.getId() + "-------名字" + a.getName());
            for (int j = c.size() - 1; j > i; j--) {
                CookBook b = c.get(j);
                if (a.getName().equals(b.getName()) && a.getTmpId() == b.getTmpId() && a.getId().intValue() != b.getId().intValue()) {
//                    System.out.println("重复ID：" + b.getId() + "-------名字" + a.getName());
//                    List<CookAlbum> aas = cookAlbumRepository.findByCookBookId(b.getId());
//                    cookAlbumRepository.delete(aas);
//
//                    List<CookStep> ssss = cookStepRepository.findByCookBookId(b.getId());
//                    cookStepRepository.delete(ssss);
//                    w.add(b);
                }
            }
        }
//        cookBookRepository.delete(w);
    }

    public void del() {
        List<CookBook> c = cookBookRepository.findAll();
        List<CookBook> w = new ArrayList<>();

        for (int i = 38026; i < c.size(); i++) {
            CookBook a = c.get(i);
            List<CookAlbum> aas = cookAlbumRepository.findByCookBookId(a.getId());
            cookAlbumRepository.delete(aas);

            List<CookStep> ssss = cookStepRepository.findByCookBookId(a.getId());
            cookStepRepository.delete(ssss);
            w.add(a);
        }
        cookBookRepository.delete(w);
    }
}
