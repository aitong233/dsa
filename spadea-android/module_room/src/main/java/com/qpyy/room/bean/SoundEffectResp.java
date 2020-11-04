package com.qpyy.room.bean;

import com.qpyy.libcommon.bean.Config;

import java.io.Serializable;

import lombok.Data;

@Data
public class SoundEffectResp implements Serializable {

    private String id;
    private String name;
    private String info;
    private String room_type;
    private String rank_id;
    private String status;
    private String add_time;
    private String icon;
    private String icon_select;
    private Config config;


}
