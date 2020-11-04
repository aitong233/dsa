package com.spadea.xqipao.ui.login.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.LabelModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class LabelContacts {


    public interface View extends IView<Activity> {
        void indexLabelSuccess(String categoryId, List<LabelModel> data);

        void addLabelSuccess();
    }

    public interface ILabelPre extends IPresenter {
        void indexLabel(String categoryId, int p);

        void addLabel(String ids);
    }

}
