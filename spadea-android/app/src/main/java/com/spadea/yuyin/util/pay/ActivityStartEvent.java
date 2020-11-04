package com.spadea.yuyin.util.pay;

/**
 * Copyright (c) 1
 *
 * @Description
 * @Author 1
 * @Copyright Copyright (c) 1
 * @Date $date$ $time$
 */

public class ActivityStartEvent {
    public ActivityStartEvent(int type, String content) {
        this.type = type;
        this.content = content;
    }

    int type;
    String content;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
