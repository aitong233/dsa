package com.qpyy.libcommon.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomWheatModel {

    private String nickname;
    private String user_id;
    private String shutup;
    private String banned;
    private String head_picture;
    private String dress_picture;
    private String room_id;
    private String pit_number;
    private String sex;
    private int ball_state;//1开0关
}
