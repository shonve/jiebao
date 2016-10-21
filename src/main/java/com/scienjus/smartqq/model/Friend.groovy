package com.scienjus.smartqq.model
/**
 * 好友
 * @author ScienJus
 * @date 2015/12/18.
 */
public class Friend {

    long userId;

    String markname = "";

    String nickname;

    boolean vip;

    int vipLevel;

    @Override
    public String toString() {
        return "Friend{" +
                "userId=" + userId +
                ", markname='" + markname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", vip=" + vip +
                ", vipLevel=" + vipLevel +
                '}';
    }
}
