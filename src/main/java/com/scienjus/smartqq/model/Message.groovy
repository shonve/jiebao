package com.scienjus.smartqq.model

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject

/**
 * 消息.
 *
 */
public class Message {

    long time;

    String content;

    long userId;

    Font font;

    public Message(JSONObject json) {
        JSONArray cont = json.getJSONArray("content");
        this.font = cont.getJSONArray(0).getObject(1, Font.class);

        final int size = cont.size();
        final StringBuilder contentBuilder = new StringBuilder();
        for (int i = 1; i < size; i++) {
            contentBuilder.append(cont.getString(i));
        }
        this.content = contentBuilder.toString();

        this.time = json.getLongValue("time");
        this.userId = json.getLongValue("from_uin");
    }

}
