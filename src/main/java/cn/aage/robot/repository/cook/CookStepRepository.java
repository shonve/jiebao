package cn.aage.robot.repository.cook;

import cn.aage.robot.model.cook.CookStep;
import cn.aage.robot.model.cook.CookStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by shonve on 2016/10/21.
 */
public interface CookStepRepository extends JpaRepository<CookStep, Long>, JpaSpecificationExecutor {
    List<CookStep> findByCookBookId(Integer id);
}
