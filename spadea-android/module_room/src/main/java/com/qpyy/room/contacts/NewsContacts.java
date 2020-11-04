package com.qpyy.room.contacts;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.hyphenate.chat.EMConversation;
import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.NewsModel;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.contacts
 * 创建人 黄强
 * 创建时间 2020/8/10 11:31
 * 描述 describe
 */
public class NewsContacts {

    public interface View extends IView<Activity> {
        void userNewsSuccess(NewsModel newsModel);
        void conversationComplete(List<EMConversation> list);
    }

    public interface INewsPre extends IPresenter {
        void userNews();
        void refreshConversation();
    }
}
