package cn.aage.robot.repository;

import cn.aage.robot.model.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by shonve on 2016/10/21.
 */
public interface RobotRepository extends JpaRepository<Robot, Integer>, JpaSpecificationExecutor {
    Robot findByName(String configValue);
}
