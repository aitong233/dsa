package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.OnlineListResp;
import com.qpyy.room.bean.RoomOnlineResp;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.contacts
 * 创建人 黄强
 * 创建时间 2020/7/25 11:53
 * 描述 describe
 */
public class RoomOnlineContacts {

    public interface View extends IView<Activity> {

        //加载在线列表视图
        void setOnlineListView(RoomOnlineResp listResps, int page);

        //数据加载完成
        void roomOnlineComplete();
    }
    public interface IRoomOnlinePre extends IPresenter {

        //获取房间在线人数接口
        void getOnlineList(String roomId,int page);
    }

}
