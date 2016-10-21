package cn.aage.robot.model.cook

import javax.persistence.*

/**
 * Created by shonve on 2016/10/22.
 */

@Entity
@Table(name = "cook_style")
class CookStyle {
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
     * 名字
     */
    @Column(name = "name", nullable = false)
    String name;

    /**
     * 关联的父类
     */
    @ManyToOne
    @JoinColumn(name = "style_id")
    CookStyle parent;

    @Column(name = "tmp_id")
    Integer tmpId;


}
