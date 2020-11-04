package com.spadea.xqipao.data.socket;

import com.spadea.xqipao.data.FaceBean;

public class RoomFaceModel {


    /**
     * room_id : 3
     * type : face1
     * message : {"number":0,"face_spectial":"https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/N4WsWKm4pS.gif","pit":"1","type":1}
     */

    private String room_id;
    private FaceBean data;

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public FaceBean getData() {
        return data;
    }

    public void setData(FaceBean data) {
        this.data = data;
    }
}
