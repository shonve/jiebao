package cn.aage.robot.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by shonve on 2016/10/21.
 */
@Entity
@Table(name = "md5")
class Md5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    Date createTime;

    /**
     * 字符串内容
     */
    @Column(name = "content", nullable = false,unique = true)
    String content;

    /**
     * 字符串加密内容
     */
    @Column(name = "md5_content", nullable = false, length = 32)
    String md5Content;
}
