package cn.aage.robot.repository;

import cn.aage.robot.model.QQGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by shonve on 2016/10/21.
 */
public interface QQGroupRepository extends JpaRepository<QQGroup, Long>, JpaSpecificationExecutor {
    QQGroup findByGroupId(long groupId);
}
