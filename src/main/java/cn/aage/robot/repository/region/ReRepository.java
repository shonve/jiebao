package cn.aage.robot.repository.region;

import cn.aage.robot.model.region.Re;
import cn.aage.robot.model.region.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by shonve on 2016/10/23.
 */
public interface ReRepository extends JpaRepository<Re, Integer>, JpaSpecificationExecutor {
    List<Re> findByLevel(String s);
}
