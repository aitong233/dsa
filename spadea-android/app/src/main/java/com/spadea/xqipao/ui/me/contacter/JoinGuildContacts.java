package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.qpyy.module.me.bean.GuildResp;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.contacter
 * 创建人 王欧
 * 创建时间 2020/5/8 2:14 PM
 * 描述 describe
 */
public class JoinGuildContacts {
    public interface View extends IView<Activity> {
        void guildInfo(GuildResp guildResp);
        void joinSuccess();
    }

    public interface IJoinGuildPre extends IPresenter {
        void guildInfo(String id);
        void joinGuild(String id);
    }
}
