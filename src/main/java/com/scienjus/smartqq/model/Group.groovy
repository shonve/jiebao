package com.scienjus.smartqq.model

import com.alibaba.fastjson.annotation.JSONField

/**
 * 群
 * @author ScienJus
 * @date 2015/12/18.
 */
public class Group {

    @JSONField(name = "gid")
    long id;

    String name;

    long flag;

    long code;

}
