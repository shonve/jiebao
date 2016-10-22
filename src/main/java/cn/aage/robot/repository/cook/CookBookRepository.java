package cn.aage.robot.repository.cook;

import cn.aage.robot.model.cook.CookAlbum;
import cn.aage.robot.model.cook.CookBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by shonve on 2016/10/21.
 */
public interface CookBookRepository extends JpaRepository<CookBook, Integer>, JpaSpecificationExecutor {
    CookBook findByName(String title);

    List<CookBook> findByNameAndTmpIdAndIntroduction(String title, int id, String imtro);
}
