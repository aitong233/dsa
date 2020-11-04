package com.spadea.xqipao.ui.chart.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module_news.bean.EmChatUserInfo;

public final class ChatMoreContacts {


    public interface View extends IView<Activity> {
        void userInfo(EmChatUserInfo userInfo);
    }

    public interface IChatMorePre extends IPresenter {
        void addUser2BlackList(String userId, String easeUserName);

        void getEmChatUserInfo(String emChatName);
    }
}