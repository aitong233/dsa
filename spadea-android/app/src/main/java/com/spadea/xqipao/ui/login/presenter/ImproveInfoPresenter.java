package com.spadea.xqipao.ui.login.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.me.api.ApiClient;
import com.qpyy.module.me.bean.UserFillResp;
import com.spadea.xqipao.ui.login.contacter.ImproveInfoContacts;

import io.reactivex.disposables.Disposable;

public class ImproveInfoPresenter extends BasePresenter<ImproveInfoContacts.View> implements ImproveInfoContacts.IImproveInfoPre {
    public ImproveInfoPresenter(ImproveInfoContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void fill(String user_no, String nickname, String sex) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().userFill(user_no, nickname, sex, new BaseObserver<UserFillResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(UserFillResp s) {
                MvpRef.get().success(s);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}