package com.spadea.xqipao.ui.room.contacts;

import android.support.v4.app.FragmentActivity;

import com.spadea.xqipao.data.GiftModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

public final class RoomUserGiftContacts {


    public interface View extends IView<FragmentActivity> {
        void setBalanceMoney(String money);

        void giveGiftSuccess(GiftModel giftModel, String userId, String num, int type);
    }

    public interface IRoomGiftPre extends IPresenter {
        void getBalance();

        void giveGift(String userId, String giftId, String roomId, String pit, String num, GiftModel giftModel ,int type);

        void giveBackGift(String userId, String giftId, String roomId, String pit, String num, GiftModel giftModel ,int type);
    }
}
