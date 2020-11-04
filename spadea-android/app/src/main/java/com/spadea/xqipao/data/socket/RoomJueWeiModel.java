package com.spadea.xqipao.data.socket;

import com.spadea.xqipao.data.ApproachBean;

public class RoomJueWeiModel {

    /**
     * room_id : 5
     * data : {"headPicture":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKJq9Kq5vXanJDRNTOnMiaAVgXFqgmZ8qhxcLG4rCxdsdbKgl8E0K7JnAL4JGqPOcO2LJcSsyEPQjA/132","nobilityId":2,"nobilityName":"伯爵","userId":"2","userName":"因为一个人"}
     */
    private String room_id;
    private ApproachBean data;
    private int display;

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public ApproachBean getData() {
        return data;
    }

    public void setData(ApproachBean data) {
        this.data = data;
    }
}
