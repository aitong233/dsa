package com.qpyy.room.event;

import com.hyphenate.util.NetUtils;

public class NetEvent {

    private NetUtils.Types type;

    public NetEvent(NetUtils.Types type) {
        this.type = type;
    }

    public NetEvent() {

    }

    public NetUtils.Types getType() {
        return type;
    }

    public void setType(NetUtils.Types type) {
        this.type = type;
    }
}
