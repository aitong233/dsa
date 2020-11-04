package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.me.api.ApiClient;
import com.qpyy.module.me.bean.GuildResp;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.MyGuildContacts;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.presenter
 * 创建人 王欧
 * 创建时间 2020/5/8 3:43 PM
 * 描述 describe
 */
public class MyGuildPresenter extends BasePresenter<MyGuildContacts.View> implements MyGuildContacts.MyGuildPre {
    public MyGuildPresenter(MyGuildContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getGuildInfo() {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().guildInfo(new BaseObserver<GuildResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(GuildResp guildResp) {
                MvpRef.get().guildInfo(guildResp);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });

    }

    @Override
    public void quitGuild(String id) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().guildQuit(new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().quitSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
