package cn.aage.robot.model.cook

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 * Created by shonve on 2016/10/21.
 */
@Entity
@Table(name = "cook_step")
class CookStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    Date createTime;

    /**
     * 关联的食谱单
     */
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    CookBook cookBook;

    /**
     * 操作
     */
    @Column(name = "operate", nullable = false)
    String operate;

    /**
     * 排序
     */
    @Column(name = "sort", nullable = false)
    Integer sort;

    /**
     * 图片url
     */
    @Column(name = "image_url")
    String imageUrl;

}
