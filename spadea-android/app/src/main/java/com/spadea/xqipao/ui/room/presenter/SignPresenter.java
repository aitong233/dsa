package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.xqipao.data.SignHistoryResp;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.SignContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.presenter
 * 创建人 王欧
 * 创建时间 2020/4/25 12:15 PM
 * 描述 describe
 */
public class SignPresenter extends BasePresenter<SignContacts.View> implements SignContacts.SignPre {
    public SignPresenter(SignContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getSignHistory() {
        api.getSignHostory(new BaseObserver<SignHistoryResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(SignHistoryResp resp) {
                MvpRef.get().signHistory(resp);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getRewordData() {
        api.getSignRewardList(new BaseObserver<List<SignHistoryResp.RewardData>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<SignHistoryResp.RewardData> list) {
                MvpRef.get().rewardList(list);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void signIn() {
        api.signIn(new BaseObserver<SignHistoryResp.RewardData>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(SignHistoryResp.RewardData s) {
                MvpRef.get().signInSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
