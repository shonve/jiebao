package cn.aage.robot.model

import javax.persistence.*

/**
 * Created by shonve on 2016/10/21.
 */
@Entity
@Table(name = "robot")
class Robot {
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
     * 机器人名字
     */
    @Column(name = "name", nullable = false, unique = true)
    String name;

    /**
     * 机器人名字
     */
    @Column(name = "robot_name", nullable = false)
    String robotName;

    /**
     * 机器人api地址
     */
    @Column(name = "api_url")
    String apiUrl;

    /**
     * 机器人key
     */
    @Column(name = "robot_key")
    String robotKey;
    /**
     * 机器人密钥
     */
    @Column(name = "robot_secret")
    String robotSecret;


}
