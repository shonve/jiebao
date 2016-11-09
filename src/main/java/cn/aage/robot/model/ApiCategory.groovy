package cn.aage.robot.model

import javax.persistence.*

/**
 * Created by shonve on 2016/10/23.
 */
@Entity
@Table(name = "api_category")
class ApiCategory {
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


}
