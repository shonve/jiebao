package cn.aage.robot.model

import javax.persistence.*

/**
 * Created by shonve on 2016/10/21.
 */
@Entity
@Table(name = "sys_config")
class SysConfig {
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
     * 配置名
     */
    @Column(name = "config_name", nullable = false)
    String configName;

    /**
     * 配置值
     */
    @Column(name = "config_value", nullable = false)
    String configValue;
    /**
     * 备注
     */
    @Column(name = "remark", nullable = false)
    String remark;


}
