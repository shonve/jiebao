package cn.aage.robot.model.region

import javax.persistence.*

/**
 * Created by shonve on 2016/10/23.
 */
@Entity
@Table(name = "region")
class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    /**
     * 名字
     */
    @Column(name = "name", nullable = false)
    String name;

    /**
     * 全拼
     */
    @Column(name = "full_name", nullable = false)
    String fullName;


    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    Country country;

    @Column(name = "level")
    Integer level;


    @ManyToOne
    @JoinColumn(name = "parent_id")
    Region parent;
}
