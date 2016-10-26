package cn.aage.robot.model

import javax.persistence.*

/**
 * Created by shonve on 2016/10/23.
 */
@Entity
@Table(name = "ip_data")
class IpData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    Date updateTime;

    /**
     * ip开始段
     */
    @Column(name = "ip_start")
    String ipStart;
    /**
     * ip结束段
     */
    @Column(name = "ip_end")
    String ipEnd;
    /**
     * ip地址
     */
    @Column(name = "ip_address")
    String ipAddress;
    /**
     * 运营商
     */
    @Column(name = "operators")
    String operators;
}
