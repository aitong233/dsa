package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.bean.GiftModel;
import com.qpyy.libcommon.bean.RankInfo;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.GiftBackResp;
import com.qpyy.room.bean.GiftEvent;
import com.qpyy.room.bean.GiftNumBean;
import com.qpyy.room.bean.NextBoxContentResp;
import com.qpyy.room.bean.RoomPitUserModel;
import com.qpyy.room.contacts.RoomGiftContacts;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.presenter
 * 创建人 黄强
 * 创建时间 2020/8/6 13:32
 * 描述 describe
 */
public class RoomGiftPresenter extends BaseRoomPresenter<RoomGiftContacts.View> implements RoomGiftContacts.IRoomGiftPre {

    public RoomGiftPresenter(RoomGiftContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getBalance() {
        ApiClient.getInstance().getBalance(SpUtils.getToken(), new BaseObserver<String>() {
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
        ApiClient.getInstance().giveGift(SpUtils.getToken(), userId, giftId, roomId, pit, num, giftModel.getType(), new BaseObserver<RankInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RankInfo rankInfo) {
                getBalance();
                UserBean user = SpUtils.getUserInfo();
                user.setRank_info(rankInfo);
                SpUtils.saveUserInfo(user);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void giveBackGift(String userId, String giftId, String roomId, String pit, String num, GiftModel giftModel, int type) {
        ApiClient.getInstance().giveBackGift(SpUtils.getToken(), userId, giftId, roomId, pit, num, new BaseObserver<RankInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RankInfo rankInfo) {
                EventBus.getDefault().post(new GiftEvent());
                UserBean user = SpUtils.getUserInfo();
                user.setRank_info(rankInfo);
                SpUtils.saveUserInfo(user);
            }

            @Override
            public void onComplete() {

            }
        });
    }


    private List<GiftNumBean> normalList = new ArrayList<>();   //其他礼物列表

    private List<GiftNumBean> mhList = new ArrayList<>();   //盲盒数量列表
    @Override
    public void getGiftNumBeanData(GiftModel giftModel) {
        int giftModelType = giftModel.getType();
        if(normalList.size()!=0){
            if (giftModelType == 4 || giftModelType == 5 || giftModelType == 13) {    //是盲盒
                MvpRef.get().setGiftNumBeanData(mhList);
            }else {
                MvpRef.get().setGiftNumBeanData(normalList);
            }
            return;
        }

        ApiClient.getInstance().giftNumberSet(null, new BaseObserver<List<GiftNumBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<GiftNumBean> giftNumBeans) {
                normalList.addAll(giftNumBeans);
                ArrayList<GiftNumBean> giftList = new ArrayList<>();    //需要移除的礼物集合
                for (GiftNumBean giftNumBean : giftNumBeans) {
                    int number = Integer.parseInt(giftNumBean.getNumber());
                    if (number > 88 || number==0) {
                        giftList.add(giftNumBean);
                    }
                }
                giftNumBeans.removeAll(giftList);
                mhList.addAll(giftNumBeans);
                if (giftModelType == 4 || giftModelType == 5 || giftModelType == 13) {    //是盲盒
                    MvpRef.get().setGiftNumBeanData(mhList);
                }else {
                    MvpRef.get().setGiftNumBeanData(normalList);
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }


    @Override
    public void getRoomPitUser(String roomId, String userId, boolean addSelf) {
        ApiClient.getInstance().getRoomPitUser(roomId, userId, new BaseObserver<List<RoomPitUserModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<RoomPitUserModel> roomPitUserModels) {
                List<RoomPitUserModel> data = new ArrayList<>();
                for (RoomPitUserModel roomPitUserModel : roomPitUserModels) {
                    if (addSelf) {//是否需要添加自己
                        data.add(roomPitUserModel);
                    } else {
                        if (!roomPitUserModel.getUser_id().equals(SpUtils.getUserId())) {
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

    @Override
    public void giveBackGiftAll(String userId, String roomId, String pit) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().giveGiftBackAll(roomId, userId, pit, new BaseObserver<RankInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RankInfo rankInfo) {
                EventBus.getDefault().post(new GiftEvent());
                UserBean user = SpUtils.getUserInfo();
                user.setRank_info(rankInfo);
                SpUtils.saveUserInfo(user);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    public void userBackPack() {
        ApiClient.getInstance().userBackPack(SpUtils.getToken(), new BaseObserver<GiftBackResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(GiftBackResp resp) {
                EventBus.getDefault().post(resp);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getBoxInfo() {
        ApiClient.getInstance().getNextBoxContent(new BaseObserver<NextBoxContentResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(NextBoxContentResp nextBoxContentResp) {
                MvpRef.get().setNextBoxState(nextBoxContentResp.getStatus() == 1);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
