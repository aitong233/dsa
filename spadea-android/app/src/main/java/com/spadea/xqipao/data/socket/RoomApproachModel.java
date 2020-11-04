package com.spadea.xqipao.data.socket;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.data.socket
 * 创建人 王欧
 * 创建时间 2020/5/29 3:08 PM
 * 描述 describe
 */
public class RoomApproachModel {

    /**
     * room_id : 4
     * ride : http://yutangyuyin1.oss-cn-hangzhou.aliyuncs.com/admin_images/5ed09adb95965.svga
     * user_id : 9
     * nobility_url : https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/nobility/6.svga
     */

    private String room_id;
    private String ride;
    private int user_id;
    private String nobility_url;
    /**
     * nickname : 763943
     * nobility_name : 帝皇
     */

    private String nickname;
    private String nobility_name;


    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRide() {
        return ride;
    }

    public void setRide(String ride) {
        this.ride = ride;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNobility_url() {
        return nobility_url;
    }

    public void setNobility_url(String nobility_url) {
        this.nobility_url = nobility_url;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNobility_name() {
        return nobility_name;
    }

    public void setNobility_name(String nobility_name) {
        this.nobility_name = nobility_name;
    }
}
