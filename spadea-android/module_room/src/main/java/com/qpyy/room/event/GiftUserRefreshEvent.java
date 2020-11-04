package com.qpyy.room.event;

public class GiftUserRefreshEvent {
    public boolean addSelf;

    public int type;

    public GiftUserRefreshEvent(boolean addSelf, int type) {
        this.addSelf = addSelf;
        this.type = type;
    }

}
