package cn.aage.robot.model.region

import javax.persistence.*

/**
 * Created by shonve on 2016/10/23.
 */
@Entity
@Table(name = "t_region")
class Re {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "code")
    String code;
    @Column(name = "country_code")
    String country_code;
    @Column(name = "region_name_e")
    String region_name_e;
    @Column(name = "region_name_c")
    String region_name_c;
    @Column(name = "level")
    String level;
    @Column(name = "upper_region")
    String upper_region;
}
