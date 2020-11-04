package com.qpyy.libcommon.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBannedModel {

    private String room_id;
    private String user_id;
    //1禁言2解禁
    private String action;

}
