package com.scienjus.smartqq.model

import com.alibaba.fastjson.annotation.JSONField

/**
 * 讨论组资料
 * @author ScienJus
 * @date 2015/12/24.
 */
public class DiscussInfo {

    @JSONField(name = "did")
    long id;

    @JSONField(name = "discu_name")
    String name;

    List<DiscussUser> users = new ArrayList<>();

    public void addUser(DiscussUser user) {
        this.users.add(user);
    }

}
