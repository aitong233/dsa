package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.ProtectedRankingListResp;


/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.contacts
 * 创建人 王欧
 * 创建时间 2020/4/2 2:28 PM
 * 描述 describe
 */
public class RoomGuardListContact {
    public interface View extends IView<Activity> {
        void protectedRankingList(ProtectedRankingListResp resp);
    }

    public interface RoomGuardListPre extends IPresenter {
        void getProtectedRankingList(String roomId);
    }
}
