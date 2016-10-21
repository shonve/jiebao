package cn.aage.robot.service;

import cn.aage.robot.model.User;
import cn.aage.robot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shonve on 2016/9/7.
 */
@Service("userService")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
