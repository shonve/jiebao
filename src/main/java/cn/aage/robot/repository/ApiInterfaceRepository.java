package cn.aage.robot.repository;

import cn.aage.robot.model.ApiInterface;
import cn.aage.robot.model.Md5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by shonve on 2016/10/23.
 */
public interface ApiInterfaceRepository extends JpaRepository<ApiInterface, Integer>, JpaSpecificationExecutor {
    ApiInterface findByCode(String configValue);
}
