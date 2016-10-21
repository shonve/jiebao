package cn.aage.robot.repository.cook;

import cn.aage.robot.model.cook.CookAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by shonve on 2016/10/21.
 */
public interface CookAlbumRepository extends JpaRepository<CookAlbum, Long>, JpaSpecificationExecutor {
}
