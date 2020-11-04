package com.qpyy.libcommon.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomJoinMountModel {

    private String room_id;
    private String ride_url;
    //1公屏 2全屏
    private int show_type;

}