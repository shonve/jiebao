package cn.aage.robot.repository.region;

import cn.aage.robot.model.region.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by shonve on 2016/10/23.
 */
public interface CountryRepository extends JpaRepository<Country, Integer>, JpaSpecificationExecutor {

}
