package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.HelpModel;
import com.spadea.xqipao.data.HelpTitleModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class HelpContacter {

    public interface View extends IView<Activity> {
        void articleCategoriesSuccess(List<HelpTitleModel> data);

        void articleListSuccess(List<HelpModel> data);
    }

    public interface IHelpPre extends IPresenter {
        void articleCategories();

        void articleList(String articleCatId);
    }

}
