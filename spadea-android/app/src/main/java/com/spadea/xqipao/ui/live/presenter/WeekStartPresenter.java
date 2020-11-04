package com.spadea.xqipao.ui.live.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.WeekStarModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.live.contacts.WeekStartContacts;

import io.reactivex.disposables.Disposable;

public class WeekStartPresenter extends BasePresenter<WeekStartContacts.View> implements WeekStartContacts.IWeekStartPre {

    public WeekStartPresenter(WeekStartContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getWeekStarList() {
        MvpRef.get().showLoadings();
        api.getWeekStarList(MyApplication.getInstance().getToken(), new BaseObserver<WeekStarModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(WeekStarModel weekStarModel) {
                MvpRef.get().getWeekStarListSuccess(weekStarModel);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                LogUtils.e("网络请求错误", e.getMessage());
            }

            @Override
            public void onComplete() {
                MvpRef.get().networkCompletion();
                MvpRef.get().disLoadings();
            }
        });
    }
}
