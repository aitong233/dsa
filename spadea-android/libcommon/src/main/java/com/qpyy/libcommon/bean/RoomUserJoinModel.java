package com.qpyy.libcommon.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomUserJoinModel {

    private String room_id;
    private String user_id;
    private String nickname;
    private String rank_icon;
    private String nobility_icon;
    private int user_is_new;
    private int role;

}
