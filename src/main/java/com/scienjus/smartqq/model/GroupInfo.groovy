package com.scienjus.smartqq.model
/**
 * 群资料
 * @author ScienJus
 * @date 2015/12/24.
 */
public class GroupInfo {

    long gid;

    long createtime;

    String memo;

    String name;

    long owner;

    String markname;

    List<GroupUser> users = new ArrayList<>();

    public void addUser(GroupUser user) {
        this.users.add(user);
    }

}
