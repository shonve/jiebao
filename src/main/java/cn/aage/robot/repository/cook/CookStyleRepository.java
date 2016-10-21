package cn.aage.robot.repository.cook;

import cn.aage.robot.model.cook.CookStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by shonve on 2016/10/22.
 */
public interface CookStyleRepository extends JpaRepository<CookStyle, Integer>, JpaSpecificationExecutor {
    CookStyle findByName(String name);
}
