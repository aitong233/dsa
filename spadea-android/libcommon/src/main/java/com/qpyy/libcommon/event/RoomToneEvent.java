package com.qpyy.libcommon.event;

import com.qpyy.libcommon.bean.Config;

import lombok.Data;

/**
 * @author xf
 */
@Data
public class RoomToneEvent {

    private String room_id;
    private int id;
    private Config config;
}
