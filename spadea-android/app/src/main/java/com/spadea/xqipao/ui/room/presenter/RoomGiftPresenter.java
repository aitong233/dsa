package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.GiftModel;
import com.spadea.xqipao.data.GiftNumBean;
import com.spadea.xqipao.data.RoomPitUserModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.qpyy.libcommon.bean.RankInfo;
import com.qpyy.libcommon.bean.UserBean;
import com.spadea.xqipao.ui.room.contacts.RoomGiftContacts;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class RoomGiftPresenter extends BasePresenter<RoomGiftContacts.View> implements RoomGiftContacts.IRoomGiftPre {

    public RoomGiftPresenter(RoomGiftContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getBalance() {
        api.getBalance(MyApplication.getInstance().getToken(), new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().setBalanceMoney(s);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void giveGift(String userId, String giftId, String roomId, String pit, String num, GiftModel giftModel, int type) {
        api.giveGift(MyApplication.getInstance().getToken(), userId, giftId, roomId, pit, num, new BaseObserver<RankInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RankInfo rankInfo) {
                MvpRef.get().giveGiftSuccess(giftModel, num, type);
                UserBean user = MyApplication.getInstance().getUser();
                user.setRank_info(rankInfo);
                MyApplication.getInstance().setUser(user);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void giveBackGift(String userId, String giftId, String roomId, String pit, String num, GiftModel giftModel, int type) {
        api.giveBackGift(MyApplication.getInstance().getToken(), userId, giftId, roomId, pit, num, new BaseObserver<RankInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RankInfo rankInfo) {
                MvpRef.get().giveGiftSuccess(giftModel, num, type);
                UserBean user = MyApplication.getInstance().getUser();
                user.setRank_info(rankInfo);
                MyApplication.getInstance().setUser(user);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getGiftNumBeanData(GiftModel giftModel) {
        List<GiftNumBean> giftNumBeanList = new ArrayList<>();
        if (giftModel != null) {
            giftNumBeanList.add(new GiftNumBean(String.valueOf(giftModel.getNumber()), "全部礼物"));
        }
        giftNumBeanList.add(new GiftNumBean("1", "一心一意"));
        giftNumBeanList.add(new GiftNumBean("10", "十全十美"));
        giftNumBeanList.add(new GiftNumBean("66", "一切顺利"));
        giftNumBeanList.add(new GiftNumBean("99", "天长地久"));
        giftNumBeanList.add(new GiftNumBean("188", "要抱抱"));
        giftNumBeanList.add(new GiftNumBean("520", "我爱你"));
        giftNumBeanList.add(new GiftNumBean("1314", "一生一世"));
        giftNumBeanList.add(new GiftNumBean("3344", "三生三世"));
        MvpRef.get().setGiftNumBeanData(giftNumBeanList);
    }


    @Override
    public void getRoomPitUser(String roomId, String userId, boolean b) {
        api.getRoomPitUser(roomId, userId, new BaseObserver<List<RoomPitUserModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<RoomPitUserModel> roomPitUserModels) {
                List<RoomPitUserModel> data = new ArrayList<>();
                for (RoomPitUserModel roomPitUserModel : roomPitUserModels) {
                    if (!b) {
                        data.add(roomPitUserModel);
                    } else {
                        if (!roomPitUserModel.getUser_id().equals(MyApplication.getInstance().getUser().getUser_id())) {
                            data.add(roomPitUserModel);
                        }
                    }
                }
                MvpRef.get().setRoomPitUser(data);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
