package cn.aage.robot.service;

import cn.aage.robot.model.Md5;
import cn.aage.robot.repository.Md5Repository;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shonve on 2016/10/21.
 */
@Service("md5Service")
public class Md5Service {
    private static final Logger LOGGER = Logger.getLogger(Md5Service.class.getName());

    @Autowired
    private Md5Repository md5Repository;

    public void generateMd5() {
        List<Md5> md5s = new ArrayList<>();

        int num = 0;
        for (int i = 0; i < 99999999; i++) {
            num++;

            Md5 md5 = new Md5();
            md5.setCreateTime(new Date());
            md5.setContent(String.valueOf(i));
            md5.setMd5Content(DigestUtils.md5Hex(String.valueOf(i)));
            md5s.add(md5);

            if (num == 100) {
                md5Repository.save(md5s);
                md5s = new ArrayList<>();
                num = 0;
            }
        }
    }

    public String getDecodeMd5(String md5Content) {
        Md5 md5 = md5Repository.findByMd5Content(md5Content);
        if (null != md5) {
            return "解密所得：" + md5.getContent();
        }
        return "数据库暂时没有该字符串解密数据";
    }
}
