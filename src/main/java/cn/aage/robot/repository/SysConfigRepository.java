package cn.aage.robot.repository;

import cn.aage.robot.model.SysConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by shonve on 2016/10/21.
 */
public interface SysConfigRepository extends JpaRepository<SysConfig, Integer>, JpaSpecificationExecutor {
    SysConfig findByConfigName(String enabled_robot);
}
