package cn.aage.robot.model.region

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by shonve on 2016/10/23.
 */
class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    /**
     * 名字
     */
    @Column(name = "name")
    String name;

    /**
     * 全拼
     */
    @Column(name = "full_name", nullable = false)
    String fullName;

}
