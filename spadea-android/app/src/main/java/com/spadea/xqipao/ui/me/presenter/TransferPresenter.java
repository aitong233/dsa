package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.TransferContacts;

import io.reactivex.disposables.Disposable;

public class TransferPresenter extends BasePresenter<TransferContacts.View> implements TransferContacts.ITransferPre {

    public TransferPresenter(TransferContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void userTransfer(String userId, String gold) {
        MvpRef.get().showLoadings();
        api.userTransfer(userId, gold, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().userTransferSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void imTransfer(String imid, String gold) {
        MvpRef.get().showLoadings();
        api.userTransferIM(imid, gold, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().userTransferSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

}
