package cn.aage.robot.repository;

import cn.aage.robot.model.QQAds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by shonve on 2016/10/21.
 */
public interface QQAdsRepository extends JpaRepository<QQAds, Integer>, JpaSpecificationExecutor {
}
