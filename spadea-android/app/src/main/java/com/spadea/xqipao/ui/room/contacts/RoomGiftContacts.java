package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.GiftModel;
import com.spadea.xqipao.data.GiftNumBean;
import com.spadea.xqipao.data.RoomPitUserModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class RoomGiftContacts {


    public interface View extends IView<FragmentActivity> {
        void setBalanceMoney(String money);

        void giveGiftSuccess(GiftModel giftModel, String num, int type);

        void setGiftNumBeanData(List<GiftNumBean> data);

        void setRoomPitUser(List<RoomPitUserModel> data);
    }

    public interface IRoomGiftPre extends IPresenter {
        void getBalance();

        void giveGift(String userId, String giftId, String roomId, String pit, String num, GiftModel giftModel, int type);

        void giveBackGift(String userId, String giftId, String roomId, String pit, String num, GiftModel giftModel, int type);

        void getGiftNumBeanData(GiftModel giftModel);



        void getRoomPitUser(String roomId, String userId, boolean b);
    }
}
