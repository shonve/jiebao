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
@Entity
@Table(name = "country")
class Country {
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
     * 编码
     */
    @Column(name = "code")
    String code;
}
