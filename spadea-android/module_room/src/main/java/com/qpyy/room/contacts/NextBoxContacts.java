package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.contacts
 * 创建人 黄强
 * 创建时间 2020/9/25 16:19
 * 描述 describe
 */
public class NextBoxContacts {

    public interface View extends IView<Activity> {

        void setContent(String content);
    }

    public interface INextBoxPre extends IPresenter {

    }
}
