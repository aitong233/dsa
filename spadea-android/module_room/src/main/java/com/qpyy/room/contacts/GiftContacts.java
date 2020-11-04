package com.qpyy.room.contacts;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.libcommon.bean.GiftModel;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.contacts
 * 创建人 黄强
 * 创建时间 2020/8/6 15:06
 * 描述 describe
 */
public class GiftContacts {

    public interface View extends IView<Activity> {

        void setData(List<GiftModel> data);
    }

    public interface IGiftPre extends IPresenter {

        void giftWall();//获取礼物图

        void userBackPack();//用户背包
    }
}
