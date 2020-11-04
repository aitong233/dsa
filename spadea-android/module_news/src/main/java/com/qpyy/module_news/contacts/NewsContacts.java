package com.qpyy.module_news.contacts;

import android.app.Activity;

import com.hyphenate.chat.EMConversation;
import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;

import java.util.List;

public final class NewsContacts {


    public interface View extends IView<Activity> {
        void conversationComplete(List<EMConversation> list);
    }

    public interface INewsPre extends IPresenter {
        void refreshConversation();
    }
}