package cn.aage.robot.repository;

import cn.aage.robot.model.QQDiscuss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by shonve on 2016/10/21.
 */
public interface QQDiscussRepository extends JpaRepository<QQDiscuss, Long>, JpaSpecificationExecutor {
    QQDiscuss findByDiscussId(long discussId);
}
