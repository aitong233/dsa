package com.qpyy.room.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hyphenate.chat.EMMessage;

public class EMMessageInfo implements MultiItemEntity {

    private EMMessage emMessage;

    private int custom = 0;//没有对应的type会闪退，改为默认值为1

    @Override
    public int getItemType() {
        if (emMessage != null) {
            return emMessage.getIntAttribute("action", 0);
        }
        return 0;
    }

    public int getCustom() {
        return custom;
    }

    public void setCustom(int custom) {
        this.custom = custom;
    }

    public EMMessageInfo(EMMessage emMessage) {
        this.emMessage = emMessage;
    }

    public EMMessage getEmMessage() {
        return emMessage;
    }

    public void setEmMessage(EMMessage emMessage) {
        this.emMessage = emMessage;
    }
}
