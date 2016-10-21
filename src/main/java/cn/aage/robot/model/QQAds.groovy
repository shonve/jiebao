package cn.aage.robot.model

import javax.persistence.*

/**
 * Created by shonve on 2016/10/21.
 */
@Entity
@Table(name = "qq_ads")
class QQAds {
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
     * 标题
     */
    @Column(name = "title", nullable = false)
    String title;
    /**
     * 内容
     */
    @Column(name = "content", nullable = false)
    String content;

    /**
     * 备注
     */
    @Column(name = "remark")
    String remark;

}
