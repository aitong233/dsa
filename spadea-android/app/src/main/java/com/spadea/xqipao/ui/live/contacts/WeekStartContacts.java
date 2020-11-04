package com.spadea.xqipao.ui.live.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.WeekStarModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class WeekStartContacts {

    public interface View extends IView<FragmentActivity> {
        void getWeekStarListSuccess(WeekStarModel weekStarModel);
        void networkCompletion();
    }

    public interface IWeekStartPre extends IPresenter {
        void getWeekStarList();
    }

}
