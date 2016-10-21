package cn.aage.robot.model

import javax.persistence.*

/**
 * Created by shonve on 2016/10/21.
 */
@Entity
@Table(name = "qq_group")
class QQGroup {
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
    @Column(name = "group_name", nullable = false)
    String groupName;

    /**
     * 群id
     */
    @Column(name = "group_id", nullable = false)
    Long groupId;

    /**
     * 群管理者
     */
    @Column(name = "admin_user")
    String adminUser;

    @Column(name = "uin")
    Long uin;

    /**
     * 是否开启消息回复(0：不开启；1：开启)
     */
    @Column(name = "open_flag")
    Integer openFlag;

    /**
     * 备注
     */
    @Column(name = "remark")
    String remark;


}
