package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.contacter
 * 创建人 王欧
 * 创建时间 2020/4/1 10:11 AM
 * 描述 describe
 */
public class MessageSettingContact {
    public interface View extends IView<Activity> {
        void setSuccess(int broadcast,int fans,int news_voice,int news_vibrate,int only_friend);
    }

    public interface MessageSettingPre extends IPresenter {
        void setting(int broadcast,int fans,int news_voice,int news_vibrate,int only_friend);
    }
}
