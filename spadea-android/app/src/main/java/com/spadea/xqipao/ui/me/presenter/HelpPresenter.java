package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.data.HelpModel;
import com.spadea.xqipao.data.HelpTitleModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.HelpContacter;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class HelpPresenter extends BasePresenter<HelpContacter.View> implements HelpContacter.IHelpPre {

    public HelpPresenter(HelpContacter.View view, Context context) {
        super(view, context);
    }

    @Override
    public void articleCategories() {
        MvpRef.get().showLoadings();
        api.articleCategories(new BaseObserver<List<HelpTitleModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<HelpTitleModel> helpTitleModels) {
                MvpRef.get().articleCategoriesSuccess(helpTitleModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void articleList(String articleCatId) {
        MvpRef.get().showLoadings();
        api.articleList(articleCatId, new BaseObserver<List<HelpModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<HelpModel> helpModels) {
                MvpRef.get().articleListSuccess(helpModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
