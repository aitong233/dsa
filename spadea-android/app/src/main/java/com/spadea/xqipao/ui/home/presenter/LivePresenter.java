package com.spadea.xqipao.ui.home.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.BannerModel;
import com.spadea.xqipao.data.RoomTypeModel;
import com.spadea.xqipao.data.SignSwitchModel;
import com.spadea.xqipao.data.TopTwoModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.home.contacts.LiveContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class LivePresenter extends BasePresenter<LiveContacts.View> implements LiveContacts.ILivePre {

    public LivePresenter(LiveContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getOnline() {
        api.online(new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().onlineSuccess(s);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getBanners() {
        MvpRef.get().showLoadings();
        api.getBanners(new BaseObserver<List<BannerModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<BannerModel> bannerModels) {
                MvpRef.get().bannersSuccess(bannerModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void getRoomType() {
        api.roomType(new BaseObserver<List<RoomTypeModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<RoomTypeModel> roomTypeModels) {
                MvpRef.get().roomTypeSuccess(roomTypeModels);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getTopTwo() {
        api.getTopTwo(new BaseObserver<TopTwoModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(TopTwoModel topTwoModel) {
                MvpRef.get().setTopTwo(topTwoModel);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void signSwitch() {
        api.signSwitch(new BaseObserver<SignSwitchModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(SignSwitchModel signSwitchModel) {
                MyApplication.getInstance().labor = signSwitchModel.getLabor() == 1;
                MvpRef.get().signSwitch(signSwitchModel.getSign() == 1);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
