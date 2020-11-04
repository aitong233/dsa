package com.qpyy.room.bean;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.bean
 * 创建人 王欧
 * 创建时间 2020/8/16 4:58 PM
 * 描述 describe
 */
public class RoomSceneItem {

    /**
     * id :
     * name :
     * info :
     * room_type :
     * rank_id :
     * config : null
     * status :
     * add_time :
     * icon :
     * icon_select :
     */

    private int id;
    private String name;
    private String info;
    private String room_type;
    private String rank_id;
    private Object config;
    private String status;
    private String add_time;
    private String icon;
    private String icon_select;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getRank_id() {
        return rank_id;
    }

    public void setRank_id(String rank_id) {
        this.rank_id = rank_id;
    }

    public Object getConfig() {
        return config;
    }

    public void setConfig(Object config) {
        this.config = config;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon_select() {
        return icon_select;
    }

    public void setIcon_select(String icon_select) {
        this.icon_select = icon_select;
    }
}
