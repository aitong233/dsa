package com.spadea.xqipao.ui.home.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.BannerModel;
import com.spadea.xqipao.data.RoomTypeModel;
import com.spadea.xqipao.data.TopTwoModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class LiveContacts {


    public interface View extends IView<FragmentActivity> {
        void onlineSuccess(String number);

        void bannersSuccess(List<BannerModel> data);

        void roomTypeSuccess(List<RoomTypeModel> data);

        void setTopTwo(TopTwoModel data);

        void signSwitch(boolean open);

    }

    public interface ILivePre extends IPresenter {
        void getOnline();

        void getBanners();

        void getRoomType();

        void getTopTwo();

        void signSwitch();

    }

}
