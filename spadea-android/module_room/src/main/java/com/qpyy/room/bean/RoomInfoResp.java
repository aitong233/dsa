package com.qpyy.room.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.bean
 * 创建人 王欧
 * 创建时间 2020/7/24 2:58 PM
 * 描述 describe
 */
@Data
public class RoomInfoResp implements Serializable {
    private RoomBean room_info;
    private RoomOwnerBean owner_info;
    private RoomUserBean user_info;
    private List<BannerItem> banner;

    //弹出麦位操作弹出
    public boolean isWheatManager() {
        return (room_info.getRole() == 1 || room_info.getRole() == 2) && (user_info.getPit() == 1 || user_info.getPit() == 9);
    }
    //管理权限
    public boolean isManager() {
        return room_info.getRole() == 1 || room_info.getRole() == 2;
    }
}
