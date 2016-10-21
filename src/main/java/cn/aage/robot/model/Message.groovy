package cn.aage.robot.model

import javax.persistence.*

/**
 * Created by john on 2016/10/20.
 */
@Entity
@Table(name = "message")
class Message {
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
     * 回复内容
     */
    @Column(name = "reply_content", nullable = false, length = 10000)
    String replyContent;

    /**
     * 发送内容
     */
    @Column(name = "send_content", nullable = false)
    String sendContent;

    /**
     * 备注
     */
    @Column(name = "remark")
    String remark;
    /**
     * 消息来源
     */
    @Column(name = "msg_source")
    String msgSource;
    /**
     * 消息发送人
     */
    @Column(name = "send_user")
    String sendUser;
}
