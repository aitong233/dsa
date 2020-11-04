package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.CategoriesModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class KnapsackContacts {


    public interface View extends IView<Activity> {
        void categoriesSuccess(List<CategoriesModel> data);
    }

    public interface IKnapsackPre extends IPresenter {
        void categories();
    }

}
