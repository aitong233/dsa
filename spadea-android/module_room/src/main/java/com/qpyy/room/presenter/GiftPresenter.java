package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.bean.GiftModel;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.GiftBackResp;
import com.qpyy.room.contacts.GiftContacts;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.presenter
 * 创建人 黄强
 * 创建时间 2020/8/6 15:06
 * 描述 describe
 */
public class GiftPresenter extends BaseRoomPresenter<GiftContacts.View> implements GiftContacts.IGiftPre {


    public GiftPresenter(GiftContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void giftWall() {
        ApiClient.getInstance().giftWall(SpUtils.getToken(), new BaseObserver<List<GiftModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<GiftModel> giftModels) {
                MvpRef.get().setData(giftModels);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void userBackPack() {
        ApiClient.getInstance().userBackPack(SpUtils.getToken(), new BaseObserver<GiftBackResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(GiftBackResp resp) {
                EventBus.getDefault().post(resp);
                MvpRef.get().setData(resp.getList());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}