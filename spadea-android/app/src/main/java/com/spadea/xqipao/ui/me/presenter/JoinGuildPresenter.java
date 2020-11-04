package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.me.api.ApiClient;
import com.qpyy.module.me.bean.GuildResp;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.JoinGuildContacts;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.presenter
 * 创建人 王欧
 * 创建时间 2020/5/8 2:28 PM
 * 描述 describe
 */
public class JoinGuildPresenter extends BasePresenter<JoinGuildContacts.View> implements JoinGuildContacts.IJoinGuildPre {
    public JoinGuildPresenter(JoinGuildContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void guildInfo(String id) {
        ApiClient.getInstance().guildSearch(id, new BaseObserver<GuildResp>() {
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

            }
        });
    }

    @Override
    public void joinGuild(String id) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().guildJoin(id, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().joinSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
