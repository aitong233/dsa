package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.MessageSettingContact;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.presenter
 * 创建人 王欧
 * 创建时间 2020/4/1 10:14 AM
 * 描述 describe
 */
public class MessageSettingPresenter extends BasePresenter<MessageSettingContact.View> implements MessageSettingContact.MessageSettingPre {
    public MessageSettingPresenter(MessageSettingContact.View view, Context context) {
        super(view, context);
    }

    @Override
    public void setting(int broadcast, int fans, int news_voice, int news_vibrate,int only_friend) {
        MvpRef.get().showLoadings();
        api.messageSetting(broadcast, fans, news_voice, news_vibrate,only_friend, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().setSuccess(broadcast,fans,news_voice,news_vibrate,only_friend);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
