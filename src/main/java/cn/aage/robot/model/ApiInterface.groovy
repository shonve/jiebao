package cn.aage.robot.model

import javax.persistence.*

/**
 * Created by shonve on 2016/10/23.
 */
@Entity
@Table(name = "api_interface")
class ApiInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    /**
     * 名字
     */
    @Column(name = "name", nullable = false, unique = true)
    String name;
    /**
     * 编码
     */
    @Column(name = "code", nullable = false, unique = true)
    String code;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    Date createTime;

    /**
     * api地址
     */
    @Column(name = "api_url")
    String apiUrl;

    /**
     * key
     */
    @Column(name = "api_key")
    String apiKey;
    /**
     * 密钥
     */
    @Column(name = "api_secret")
    String apiSecret;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    ApiCategory category;
}
