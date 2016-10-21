package cn.aage.robot.model.cook

import javax.persistence.*

/**
 * Created by shonve on 2016/10/21.
 */
@Entity
@Table(name = "cook_book")
class CookBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    Date createTime;

    /**
     * 菜名
     */
    @Column(name = "name", nullable = false)
    String name;

    /**
     * 标签
     */
    @Column(name = "tags", nullable = false)
    String tags;

    /**
     * 介绍
     */
    @Column(name = "introduction", nullable = false, length = 10000)
    String introduction;

    /**
     * 主料
     */
    @Column(name = "ingredients", nullable = false)
    String ingredients;

    /**
     * 佐料
     */
    @Column(name = "seasoning")
    String seasoning;

    /**
     * 关联的食谱单
     */
    @ManyToOne
    @JoinColumn(name = "style_id")
    CookStyle cookStyle;
}
