package com.qpyy.room.contacts;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.RoomBgBean;

import java.util.List;


/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.contacts
 * 创建人 王欧
 * 创建时间 2020/4/14 4:26 PM
 * 描述 describe
 */
public class RoomBackgroundContacts {
    public interface View extends IView<Activity> {
        void setSuccess(String bgPicture);
        void backgroundList(List<RoomBgBean> list);
    }

    public interface RoomBackgroudPre extends IPresenter {
        void setBg(String roomId,String bgPicture);
        void getBackgroundList();
    }
}
