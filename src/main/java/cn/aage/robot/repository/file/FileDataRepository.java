package cn.aage.robot.repository.file;

import cn.aage.robot.model.file.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by john on 2016/11/9.
 */
public interface FileDataRepository extends JpaRepository<FileData, Long>, JpaSpecificationExecutor {
}
