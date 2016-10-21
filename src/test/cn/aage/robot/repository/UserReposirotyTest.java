package cn.aage.robot.repository;

import cn.aage.robot.config.DataJpaConfig;
import cn.aage.robot.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
@ContextConfiguration(classes = DataJpaConfig.class)
public class UserReposirotyTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void findAll() {
        List<User> list = userRepository.findAll();
        for (User user : list) {
            System.out.println(user.getNickname());
        }
    }

}
