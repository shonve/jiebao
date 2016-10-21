package cn.aage.service;

import cn.aage.robot.config.DataConfig;
import cn.aage.robot.service.Md5Service;
import cn.aage.robot.web.Md5Controller;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by shonve on 2016/10/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
@ContextConfiguration(classes = DataConfig.class)
public class Md5ServiceTest {
    private static final Logger LOGGER = Logger.getLogger(Md5Controller.class.getName());

    @Autowired
    private Md5Service md5Service;

//    public static void  main(String args[]){
//        String str="shonve";
//        System.out.print("32bit result:" + DigestUtils.md5Hex(str) + "\n");
//        System.out.print("esult:" + DigestUtils.md5(str) + "\n");
//
//    }

    @Test
    public void testGenerateMd5() throws Exception {
        md5Service.generateMd5();
    }
}
