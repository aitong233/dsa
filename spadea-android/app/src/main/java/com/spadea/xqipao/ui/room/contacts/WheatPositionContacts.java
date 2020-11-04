package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.RowWheatModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class WheatPositionContacts {


    public interface View extends IView<FragmentActivity> {
        void setApplyWheatList(List<RowWheatModel> data);

        void agreeApplySuccess();

        void deleteApplySuccess();

        void agreeApplyAllSuccess();

        void applyWheatListComplete();
    }

    public interface IWheatPositionPre extends IPresenter {
        void applyWheatList(String roomId);

        void agreeApply(String id,String roomId);

        void deleteApply(String id,String roomId);

        void agreeApplyAll(String roomId);
    }
}
