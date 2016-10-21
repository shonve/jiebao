package cn.aage.robot.model

import javax.persistence.*

/**
 * Created by shonve on 2016/10/21.
 */
@Entity
@Table(name = "qq_discuss")
class QQDiscuss {
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
     * 最后广告时间
     */
    @Column(name = "last_ad_time")
    Date lastAdTime;

    /**
     * 群名字
     */
    @Column(name = "discuss_name", nullable = false)
    String discussName;

    /**
     * 群id
     */
    @Column(name = "discuss_id", nullable = false)
    Long discussId;

    /**
     * 讨论组管理者
     */
    @Column(name = "admin_user")
    String adminUser;

    /**
     * 是否开启消息回复(0：不开启；1：开启)
     */
    @Column(name = "open_flag")
    Integer openFlag;

    @Column(name = "uin")
    Long uin;

    /**
     * 备注
     */
    @Column(name = "remark")
    String remark;


}
