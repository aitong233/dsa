package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.CharmRankingResp;
import com.qpyy.room.bean.WealthRankingResp;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.contacts
 * 创建人 黄强
 * 创建时间 2020/7/30 11:22
 * 描述 describe
 */
public class DataListContacts {

    public interface View extends IView<Activity> {

        //榜一更新
        void setNo1(CharmRankingResp.ListsBean listsBean);
        void setNo1(WealthRankingResp.ListsBean listsBean);

        //榜二更新
        void setNo2(CharmRankingResp.ListsBean listsBean);
        void setNo2(WealthRankingResp.ListsBean listsBean);

        //榜三更新
        void setNo3(CharmRankingResp.ListsBean listsBean);
        void setNo3(WealthRankingResp.ListsBean listsBean);

        //魅力榜和魅力榜界面更新
        void setCharmView(List<CharmRankingResp.ListsBean> listsBeans);
        void setWealthView(List<WealthRankingResp.ListsBean> listsBeans);

    }
    public interface IRoomDataListPre extends IPresenter {

        //定义魅力榜数据访问接口
        void getCharmListInfo(String roomId,int type);

        //定义财富榜数据访问接口
        void getWealthListInfo(String roomId,int type);
    }
}
