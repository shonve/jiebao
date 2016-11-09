package cn.aage.robot.service;

import cn.aage.robot.config.DataJpaConfig;
import cn.aage.robot.model.QQDiscuss;
import cn.aage.robot.repository.QQDiscussRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by shonve on 2016/9/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
@ContextConfiguration(classes = DataJpaConfig.class)
public class UserServiceTest {
    @Autowired
    private QQDiscussRepository qqDiscussRepository;

    @Test
    public  void testGetUsr(){
        QQDiscuss qqDiscuss=qqDiscussRepository.findByDiscussId(3526208990l);
        System.out.println(qqDiscuss.getDiscussName());

    }

}
