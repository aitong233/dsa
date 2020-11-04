package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.GiftModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class GiftContacts {

    public interface View extends IView<FragmentActivity> {

        void setData(List<GiftModel> data);
    }

    public interface IGiftPre extends IPresenter {

        void giftWall();

        void userBackPack();
    }
}
