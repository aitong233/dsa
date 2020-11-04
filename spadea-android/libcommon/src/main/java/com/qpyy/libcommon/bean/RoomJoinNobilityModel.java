package com.qpyy.libcommon.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomJoinNobilityModel {

    /**
     * user_id : 547900
     * nobility_name : 爵位名称
     * nobility_id : 2
     * avatar : 头像url
     * nickname : titititi
     */

    private String user_id;
    private String nobility_name;
    private String nobility_id;
    private String avatar;
    private String nickname;
    private String special;
    private String sex; //1男2女


}
