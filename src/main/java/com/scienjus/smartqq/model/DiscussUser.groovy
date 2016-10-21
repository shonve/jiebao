package com.scienjus.smartqq.model
/**
 * 讨论组成员
 * @author ScienJus
 * @date 2015/12/24.
 */
public class DiscussUser {

    long uin;

    String nick;

    int clientType;

    String status;

    @Override
    public String toString() {
        return "DiscussUser{" +
                "uin=" + uin +
                ", nick='" + nick + '\'' +
                ", clientType='" + clientType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
