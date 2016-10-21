package com.scienjus.smartqq.model

import com.alibaba.fastjson.annotation.JSONField

/**
 * 用户
 * @author ScienJus
 * @date 2015/12/24.
 */
public class UserInfo {

    Birthday birthday;

    String phone;

    String occupation;

    String college;

    String uin;

    int blood;

    String lnick;   //签名

    String homepage;

    @JSONField(name = "vip_info")
    int vipInfo;

    String city;

    String country;

    String province;

    String personal;

    int shengxiao;

    String nick;

    String email;

    String account;

    String gender;

    String mobile;

}
