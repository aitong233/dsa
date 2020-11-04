package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.libcommon.bean.GiftModel;
import com.qpyy.room.bean.GiftNumBean;
import com.qpyy.room.bean.RoomPitUserModel;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.contacts
 * 创建人 黄强
 * 创建时间 2020/8/6 13:32
 * 描述 describe
 */
public class RoomGiftContacts {

    public interface View extends IView<Activity> {
        void setBalanceMoney(String money);

        void setGiftNumBeanData(List<GiftNumBean> data);

        void setRoomPitUser(List<RoomPitUserModel> data);

        void setNextBoxState(boolean isOpen);
    }

    public interface IRoomGiftPre extends IPresenter {
        void getBalance();

        void giveGift(String userId, String giftId, String roomId, String pit, String num, GiftModel giftModel, int type);

        void giveBackGift(String userId, String giftId, String roomId, String pit, String num, GiftModel giftModel, int type);

        void getGiftNumBeanData(GiftModel giftModel);


        void getRoomPitUser(String roomId, String userId, boolean b);

        void giveBackGiftAll(String userId, String roomId, String pit);
    }
}
