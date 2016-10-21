package cn.aage.robot.service;

import cn.aage.robot.model.QQAds;
import cn.aage.robot.model.QQDiscuss;
import cn.aage.robot.model.QQGroup;
import cn.aage.robot.repository.QQAdsRepository;
import cn.aage.robot.repository.QQDiscussRepository;
import cn.aage.robot.repository.QQGroupRepository;
import cn.aage.robot.util.BaoUtil;
import cn.aage.robot.util.Strings;
import com.scienjus.smartqq.callback.MessageCallback;
import com.scienjus.smartqq.client.SmartQQClient;
import com.scienjus.smartqq.model.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


@Service
public class QQService {
    private static final Logger LOGGER = Logger.getLogger(QQService.class.getName());

    @Autowired
    private RobotService robotService;
    @Autowired
    private QQDiscussRepository qqDiscussRepository;
    @Autowired
    private QQGroupRepository qqGroupRepository;
    @Autowired
    private QQAdsRepository qqAdsRepository;

    private final Map<Long, Group> QQ_GROUPS = new ConcurrentHashMap<>();

    private final Map<Long, Long> GROUP_AD_TIME = new ConcurrentHashMap<>();

    private final Map<Long, Discuss> QQ_DISCUSSES = new ConcurrentHashMap<>();

    private final Map<Long, Long> DISCUSS_AD_TIME = new ConcurrentHashMap<>();

    private SmartQQClient bao;

    /**
     * 超过 PUSH_GROUP_USER_COUNT 个成员的群才推送.
     */
    private static int PUSH_GROUP_USER_COUNT = BaoUtil.getInt("qq.bot.pushGroupUserCnt");

    /**
     * 记录未群推过的群 id 集合.
     */
    private static final Set<Long> UNPUSH_GROUPS = new CopyOnWriteArraySet<>();

    /**
     * 一次群推操作最多只推送 {@value #PUSH_GROUP_COUNT} 个群（为了尽量保证成功率）.
     */
    private static final int PUSH_GROUP_COUNT = 5;

    public void initQQClient() {
        LOGGER.info("开始初始化洁宝");

        bao = new SmartQQClient(new MessageCallback() {
            @Override
            public void onMessage(final Message message) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500 + RandomUtils.nextInt(1000));

                            final String userName = Long.toHexString(message.getUserId());
                            final String content = message.getContent();
                            final String key = BaoUtil.getString("qq.bot.key");
                            String msg = "";
                            if (!StringUtils.startsWith(content, key)) { // 不是管理命令，只是普通的私聊
                                msg = robotService.getReplyContent(userName, content);
                                bao.sendMessageToFriend(message.getUserId(), msg);
                            } else {
                                msg = StringUtils.substringAfter(content, key);
                                LOGGER.info("Received admin message: " + msg);
                                sendToPushQQGroups(msg);
                            }
                        } catch (final Exception e) {
                            LOGGER.error("JieBao on group message error", e);
                        }
                    }
                }).start();
            }

            @Override
            public void onGroupMessage(final GroupMessage message) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500 + RandomUtils.nextInt(1000));

                            onQQGroupMessage(message);
                        } catch (final Exception e) {
                            LOGGER.error("JieBao on group message error", e);
                        }
                    }
                }).start();
            }

            @Override
            public void onDiscussMessage(final DiscussMessage message) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500 + RandomUtils.nextInt(1000));

                            onQQDiscussMessage(message);
                        } catch (final Exception e) {
                            LOGGER.error("JieBao on group message error", e);
                        }
                    }
                }).start();
            }
        });
        // Load groups & disscusses
        reloadGroups();
        reloadDiscusses();

        LOGGER.info("洁宝初始化完毕");
    }

    public void closeQQClient() {
        if (null == bao) {
            return;
        }
        try {
            bao.close();
        } catch (final Exception e) {
            LOGGER.error("Closes QQ client failed", e);
        }
    }

    public void sendToPushQQGroups(final String msg) {
        try {
            final String pushGroupsConf = BaoUtil.getString("qq.bot.pushGroups");
            if (StringUtils.isBlank(pushGroupsConf)) {
                return;
            }

            // Push to all groups
            if (StringUtils.equals(pushGroupsConf, "*")) {
                int totalUserCount = 0;
                int groupCount = 0;

                if (UNPUSH_GROUPS.isEmpty()) { // 如果没有可供推送的群（群都推送过了）
                    reloadGroups();
                }

                for (final Map.Entry<Long, Group> entry : QQ_GROUPS.entrySet()) {
                    long groupId = 0;
                    int userCount = 0;

                    try {
                        final Group group = entry.getValue();
                        groupId = group.getId();

                        final GroupInfo groupInfo = bao.getGroupInfo(group.getCode());
                        userCount = groupInfo.getUsers().size();
                        if (userCount < PUSH_GROUP_USER_COUNT) {
                            // 把人不多的群过滤掉
                            UNPUSH_GROUPS.remove(groupId);

                            continue;
                        }

                        if (!UNPUSH_GROUPS.contains(groupId)) {
                            // 如果该群已经被推送过则跳过本次推送
                            continue;
                        }

                        if (groupCount >= PUSH_GROUP_COUNT) { // 如果本次群推操作已推送群数大于设定的阈值
                            break;
                        }

                        LOGGER.info("群发 [" + msg + "] 到 QQ 群 [" + group.getName() + ", 成员数=" + userCount + "]");
                        bao.sendMessageToGroup(groupId, msg); // Without retry

                        UNPUSH_GROUPS.remove(groupId); // 从未推送中移除（说明已经推送过）

                        totalUserCount += userCount;
                        groupCount++;

                        Thread.sleep(1000 * 10);
                    } catch (final Exception e) {
                        LOGGER.error("群发异常", e);
                    }
                }

                LOGGER.info("一共推送了 [" + groupCount + "] 个群，覆盖 [" + totalUserCount + "] 个 QQ");

                return;
            }

            // Push to the specified groups
            final String[] groups = pushGroupsConf.split(",");
            for (final Map.Entry<Long, Group> entry : QQ_GROUPS.entrySet()) {
                final Group group = entry.getValue();
                final String name = group.getName();

                if (Strings.contains(name, groups)) {
                    final GroupInfo groupInfo = bao.getGroupInfo(group.getCode());
                    final int userCount = groupInfo.getUsers().size();
                    if (userCount < 100) {
                        continue;
                    }

                    LOGGER.info("Pushing [msg=" + msg + "] to QQ qun [" + group.getName() + "]");
                    bao.sendMessageToGroup(group.getId(), msg); // Without retry

                    Thread.sleep(1000 * 10);
                }
            }
        } catch (final Exception e) {
            LOGGER.error("Push message [" + msg + "] to groups failed", e);
        }
    }

    public void onQQGroupMessage(GroupMessage message) {
        long groupId = message.getGroupId();

        String content = message.getContent();
        String userName = Long.toHexString(message.getUserId());
        String msg = "";
        QQGroup group = qqGroupRepository.findByGroupId(groupId);
        if (null != group && null != group.getOpenFlag() && group.getOpenFlag().intValue() == 1) {//开启聊天
            msg = robotService.getReplyContent(userName, content);
        }else{
            reloadDiscusses();
        }
        if (StringUtils.isBlank(msg)) {
            return;
        }
        msg = generateAds(msg, "group", groupId);
        bao.sendMessageToGroup(groupId, msg);
    }

    /**
     * 生成广告
     *
     * @param message
     * @param type
     * @param id
     * @return
     */
    private String generateAds(String message, String type, Long id) {
        if (RandomUtils.nextFloat() >= 0.9) {
            long now = System.currentTimeMillis();
            List<QQAds> qqAdses = qqAdsRepository.findAll();
            if (type.equals("group")) {//群广告
                Long latestAdTime = 0l;
                QQGroup group = qqGroupRepository.findByGroupId(id);
                if (null != group && null != group.getLastAdTime()) {
                    latestAdTime = group.getLastAdTime().getTime();
                }
                if (now - latestAdTime > 1000 * 60 * 30) {
                    message = message + "\n\n" + qqAdses.get(RandomUtils.nextInt(qqAdses.size())).getContent();
                    group.setLastAdTime(new Date());
                    qqGroupRepository.save(group);
                }
            } else if (type.equals("discuss")) {
                Long latestAdTime = 0l;
                QQDiscuss discuss = qqDiscussRepository.findByDiscussId(id);
                if (null != discuss && null != discuss.getLastAdTime()) {
                    latestAdTime = discuss.getLastAdTime().getTime();
                }
                if (now - latestAdTime > 1000 * 60 * 30) {
                    message = message + "\n\n" + qqAdses.get(RandomUtils.nextInt(qqAdses.size())).getContent();
                    discuss.setLastAdTime(new Date());
                    qqDiscussRepository.save(discuss);
                }
            }
        }
        return message;
    }

    public void onQQDiscussMessage(final DiscussMessage message) {
        long discussId = message.getDiscussId();

        String content = message.getContent();
        String userName = Long.toHexString(message.getUserId());
        String msg = "";
        QQDiscuss discuss = qqDiscussRepository.findByDiscussId(discussId);
        if (null != discuss && null != discuss.getOpenFlag() && discuss.getOpenFlag().intValue() == 1) {//开启聊天
            msg = robotService.getReplyContent(userName, content);
        }else

        if (StringUtils.isBlank(msg)) {
            return;
        }
        msg = generateAds(msg, "discuss", discussId);
        bao.sendMessageToDiscuss(discussId, msg);
    }

    private void reloadGroups() {
        final List<Group> groups = bao.getGroupList();
        QQ_GROUPS.clear();
        GROUP_AD_TIME.clear();
        UNPUSH_GROUPS.clear();

        List<QQGroup> haves = qqGroupRepository.findAll();
        List<QQGroup> qqGroups = new ArrayList<>();
        for (Group group : groups) {
            boolean flag = true;
            for (QQGroup qqGroup : haves) {
                if (qqGroup.getGroupId().longValue() == group.getId()) {
                    flag = false;
                }
            }
            if (flag) {
                QQGroup qqGroup = new QQGroup();
                qqGroup.setCreateTime(new Date());
                qqGroup.setGroupId(group.getId());
                qqGroup.setGroupName(group.getName());
                qqGroup.setUin(bao.getUin());
                qqGroups.add(qqGroup);
            }
        }
        qqGroupRepository.save(qqGroups);

        final StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append("Reloaded groups: \n");
        for (final Group g : groups) {
            QQ_GROUPS.put(g.getId(), g);
            GROUP_AD_TIME.put(g.getId(), 0L);
            UNPUSH_GROUPS.add(g.getId());

            msgBuilder.append("    ").append(g.getName()).append(": ").append(g.getId()).append("\n");
        }

        LOGGER.info(msgBuilder.toString());
    }

    private void reloadDiscusses() {
        List<Discuss> discusses = bao.getDiscussList();

        List<QQDiscuss> haves = qqDiscussRepository.findAll();
        List<QQDiscuss> qqDiscusses = new ArrayList<>();
        for (Discuss discuss : discusses) {
            boolean flag = true;
            for (QQDiscuss qqDiscuss : haves) {
                if (qqDiscuss.getDiscussId().longValue() == discuss.getId()) {
                    flag = false;
                }
            }
            if (flag) {
                QQDiscuss qqDiscuss = new QQDiscuss();
                qqDiscuss.setCreateTime(new Date());
                qqDiscuss.setDiscussId(discuss.getId());
                qqDiscuss.setDiscussName(discuss.getName());
                qqDiscuss.setUin(bao.getUin());
                qqDiscusses.add(qqDiscuss);
            }
        }
        qqDiscussRepository.save(qqDiscusses);


        final StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append("Reloaded discusses: \n");
        for (final Discuss d : discusses) {
            QQ_DISCUSSES.put(d.getId(), d);
            DISCUSS_AD_TIME.put(d.getId(), 0L);

            msgBuilder.append("    ").append(d.getName()).append(": ").append(d.getId()).append("\n");
        }

        LOGGER.info(msgBuilder.toString());
    }

}
