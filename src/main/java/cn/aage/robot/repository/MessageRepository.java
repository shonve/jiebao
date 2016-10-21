package cn.aage.robot.repository;

import cn.aage.robot.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by john on 2016/10/20.
 */
public interface MessageRepository extends JpaRepository<Message, Long>, JpaSpecificationExecutor {
    Message findBySendContentAndReplyContent(String sendMsg, String replyMsg);
}
