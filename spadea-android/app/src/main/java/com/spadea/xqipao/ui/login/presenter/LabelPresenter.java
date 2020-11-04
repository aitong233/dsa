package com.spadea.xqipao.ui.login.presenter;

import android.content.Context;

import com.spadea.xqipao.data.LabelModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.login.contacter.LabelContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class LabelPresenter extends BasePresenter<LabelContacts.View> implements LabelContacts.ILabelPre {

    public LabelPresenter(LabelContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void indexLabel(String categoryId, int p) {
        api.indexLabel(categoryId, p, new BaseObserver<List<LabelModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<LabelModel> labelModels) {
                MvpRef.get().indexLabelSuccess(categoryId, labelModels);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void addLabel(String ids) {
        MvpRef.get().showLoadings();
        api.addLabel(ids, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().addLabelSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
