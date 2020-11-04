package com.spadea.xqipao.utils.view.room.animation;

public class UserJoinRoomBean {


    public UserJoinRoomBean(String nickName, int rankId, String rankName, int nobilityId, String nobilityName) {
        this.nickName = nickName;
        this.rankId = rankId;
        this.rankName = rankName;
    }

    private String nickName;
    private int rankId;
    private String rankName;
    private int nobilityId;
    private String nobilityName;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }
}
