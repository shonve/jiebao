package cn.aage.robot.repository;

import cn.aage.robot.model.QQConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by shonve on 2016/10/21.
 */
public interface QQConfigRepository extends JpaRepository<QQConfig, Integer>, JpaSpecificationExecutor {
    QQConfig findByConfigName(String enabled_robot);
}
