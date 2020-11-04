package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.LatelyVisitInfo;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.LatelyVisitContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class LatelyVisitPresenter extends BasePresenter<LatelyVisitContacts.View> implements LatelyVisitContacts.ILatelyVisitPre {
    public LatelyVisitPresenter(LatelyVisitContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void comeUser(int pager) {
        api.comeUser(MyApplication.getInstance().getToken(), pager, new BaseObserver<List<LatelyVisitInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<LatelyVisitInfo> latelyVisitInfos) {
                MvpRef.get().setComeUserData(latelyVisitInfos);
            }

            @Override
            public void onComplete() {
                MvpRef.get().comeUserComplete();
            }
        });
    }
}
