package com.qpyy.libcommon.bean;

import android.text.TextUtils;

import java.io.Serializable;

public class TransferUserModel implements Serializable {


    /**
     * nickname :
     * user_code :
     * head_picture :
     */

    private String nickname;
    private String user_code;
    private String head_picture;
    private String user_id;
    private String im_id;


    public TransferUserModel(String nickname, String user_code, String head_picture, String im_id) {
        this.nickname = nickname;
        this.user_code = user_code;
        this.head_picture = head_picture;
        this.im_id = im_id;
    }

    public TransferUserModel() {
    }

    public boolean isIM(){
        return !TextUtils.isEmpty(im_id);
    }

    public String getIm_id() {
        return im_id;
    }

    public void setIm_id(String im_id) {
        this.im_id = im_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getHead_picture() {
        return head_picture;
    }

    public void setHead_picture(String head_picture) {
        this.head_picture = head_picture;
    }
}
