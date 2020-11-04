package com.spadea.xqipao.data;

import java.io.Serializable;

public class WheatModel implements Serializable {

    private ListBean list;
    private String otherUser;
    private RoomExtraModel extra_info;

    public RoomExtraModel getExtra_info() {
        return extra_info;
    }

    public void setExtra_info(RoomExtraModel extra_info) {
        this.extra_info = extra_info;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public String getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(String otherUser) {
        this.otherUser = otherUser;
    }


}
