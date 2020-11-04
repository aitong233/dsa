package com.qpyy.room.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.bean
 * 创建人 黄强
 * 创建时间 2020/8/18 23:11
 * 描述 describe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomMessageBean {
    private String user_id;
    private String token;
    private String type;
    private String content;
    private String room_id;
}
