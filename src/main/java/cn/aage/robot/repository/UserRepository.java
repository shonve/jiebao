package cn.aage.robot.repository;

import cn.aage.robot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by shonve on 2016/9/7.
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {
}
