package com.qpyy.libcommon.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBanWheatModel {


    private String room_id;
    private String user_id;
    private String action;
    private String pit_number;


}
