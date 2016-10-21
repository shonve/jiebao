package cn.aage.robot.repository;

import cn.aage.robot.model.Md5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by shonve on 2016/10/21.
 */
public interface Md5Repository extends JpaRepository<Md5, Long>, JpaSpecificationExecutor {
    Md5 findByMd5Content(String md5Content);
}
