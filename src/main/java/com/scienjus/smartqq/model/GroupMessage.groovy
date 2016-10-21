package com.scienjus.smartqq.model

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject

/**
 * 群消息.
 *
 */
public class GroupMessage {

    long groupId;

    long time;

    String content;

    long userId;

    Font font;

    public GroupMessage(JSONObject json) {
        JSONArray cont = json.getJSONArray("content");
        this.font = cont.getJSONArray(0).getObject(1, Font.class);

        final int size = cont.size();
        final StringBuilder contentBuilder = new StringBuilder();
        for (int i = 1; i < size; i++) {
            contentBuilder.append(cont.getString(i));
        }
        this.content = contentBuilder.toString();

        this.time = json.getLongValue("time");
        this.groupId = json.getLongValue("group_code");
        this.userId = json.getLongValue("send_uin");
    }

}
