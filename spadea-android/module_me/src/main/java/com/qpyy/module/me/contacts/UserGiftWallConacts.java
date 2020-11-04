package com.qpyy.module.me.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.me.bean.GiftBean;

import java.util.List;

public final class UserGiftWallConacts {


    public interface View extends IView<Activity> {
        void setGiftWall(List<GiftBean> data);
        void finishRefresh();
    }


    public interface IUserGiftWallPre extends IPresenter {
        void giftWall(String userId);
    }
}
