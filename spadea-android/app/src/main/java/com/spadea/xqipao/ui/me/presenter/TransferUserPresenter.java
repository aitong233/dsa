package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.qpyy.libcommon.bean.TransferUserModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.TransferUserContacter;

import io.reactivex.disposables.Disposable;

public class TransferUserPresenter extends BasePresenter<TransferUserContacter.View> implements TransferUserContacter.ITransferUserPre {
    public TransferUserPresenter(TransferUserContacter.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getTransferUser(String userCode) {
        MvpRef.get().showLoadings();
        api.transferUser(userCode, new BaseObserver<TransferUserModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(TransferUserModel transferUserModel) {
                MvpRef.get().setTransferUser(transferUserModel);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
