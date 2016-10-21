package cn.aage.robot.model;

import javax.persistence.*;

/**
 * Created by john on 2016/9/7.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    /**
     * 昵称
     */
    @Column(name = "nickname")
    String nickname;

    /**
     * 真实名字
     */
    @Column(name = "realname")
    String realname;

    /**
     * 用户名
     */
    @Column(name = "username", nullable = false)
    String username;

    /**
     * 密码
     */
    @Column(name = "password")
    String password;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    Date createTime;


}
