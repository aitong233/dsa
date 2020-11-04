package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.CategoriesModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.KnapsackContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class KnapsackPresenter extends BasePresenter<KnapsackContacts.View> implements KnapsackContacts.IKnapsackPre {

    public KnapsackPresenter(KnapsackContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void categories() {
        api.categories(new BaseObserver<List<CategoriesModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<CategoriesModel> categoriesModels) {
                MvpRef.get().categoriesSuccess(categoriesModels);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
