package com.spadea.xqipao.data;

public class ApproachBean {

    private String userId;
    private String userName;
    private int nobilityId;
    private String nobilityName;
    private String headPicture;
    private int display;

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNobilityId() {
        return nobilityId;
    }

    public void setNobilityId(int nobilityId) {
        this.nobilityId = nobilityId;
    }

    public String getNobilityName() {
        return nobilityName;
    }

    public void setNobilityName(String nobilityName) {
        this.nobilityName = nobilityName;
    }

    @Override
    public String toString() {
        return "ApproachBean{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", nobilityId='" + nobilityId + '\'' +
                ", nobilityName='" + nobilityName + '\'' +
                ", headPicture='" + headPicture + '\'' +
                '}';
    }
}
