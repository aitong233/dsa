package com.spadea.xqipao.data.even;

import com.spadea.xqipao.utils.view.room.RoomHeadInfo;
import com.spadea.xqipao.data.HousePitBean;
import com.spadea.xqipao.data.HouseUserBean;

import java.util.List;


public class RoomIfoEvent {

    private String type;
    private HouseUserBean houseUserBean;
    private List<HousePitBean> housePitBeanList;
    private String otherUser;
    private int cardiac = 1;  //心动值显示
    private String wheat = "1";   // 1自由麦模式   2排麦模式
    private boolean isStayHost = false;  //是否在主持位
    private boolean isWheat = false; //是否在麦位上
    private boolean isHostPeople = false; //主持位是否有人
    private String toChatRooms;
    private String applyCount;
    private HousePitBean userHousePit;  //当前用户所在麦位
    private RoomHeadInfo roomHeadInfo;



    public RoomHeadInfo getRoomHeadInfo() {
        return roomHeadInfo;
    }

    public void setRoomHeadInfo(RoomHeadInfo roomHeadInfo) {
        this.roomHeadInfo = roomHeadInfo;
    }

    public HousePitBean getUserHousePit() {
        return userHousePit;
    }

    public void setUserHousePit(HousePitBean userHousePit) {
        this.userHousePit = userHousePit;
    }

    public String getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(String applyCount) {
        this.applyCount = applyCount;
    }

    public String getToChatRooms() {
        return toChatRooms;
    }

    public void setToChatRooms(String toChatRooms) {
        this.toChatRooms = toChatRooms;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HouseUserBean getHouseUserBean() {
        return houseUserBean;
    }

    public void setHouseUserBean(HouseUserBean houseUserBean) {
        this.houseUserBean = houseUserBean;
    }

    public List<HousePitBean> getHousePitBeanList() {
        return housePitBeanList;
    }

    public void setHousePitBeanList(List<HousePitBean> housePitBeanList) {
        this.housePitBeanList = housePitBeanList;
    }

    public String getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(String otherUser) {
        this.otherUser = otherUser;
    }

    public int getCardiac() {
        return cardiac;
    }

    public void setCardiac(int cardiac) {
        this.cardiac = cardiac;
    }


    public String getWheat() {
        return wheat;
    }

    public void setWheat(String wheat) {
        this.wheat = wheat;
    }

    public boolean isStayHost() {
        return isStayHost;
    }

    public void setStayHost(boolean stayHost) {
        isStayHost = stayHost;
    }

    public boolean isWheat() {
        return isWheat;
    }

    public void setWheat(boolean wheat) {
        isWheat = wheat;
    }


    public boolean isHostPeople() {
        return isHostPeople;
    }

    public void setHostPeople(boolean hostPeople) {
        isHostPeople = hostPeople;
    }
}
