package com.yutang.game.fudai.presenter;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.SpUtils;
import com.yutang.game.fudai.bean.CatHelpModel;
import com.yutang.game.fudai.bean.FishInfoBean;
import com.yutang.game.fudai.bean.LuckGiftBean;
import com.yutang.game.fudai.bean.WinJackpotModel;
import com.yutang.game.fudai.contacts.EggGameDialogContacts;
import com.yutang.game.fudai.net.ApiClient;

import java.util.List;

import io.reactivex.disposables.Disposable;


public class EggGamePresenter extends BasePresenter<EggGameDialogContacts.View> implements EggGameDialogContacts.EggGamePre {

    private boolean canRequest=true;

    public EggGamePresenter(EggGameDialogContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getFishInfo(String type) {
        ApiClient.getInstance().getFishInfo(type,new BaseObserver<FishInfoBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(FishInfoBean fishInfoBean) {
                MvpRef.get().setFishInfo(fishInfoBean);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void startFishing(int num,int type) {
        if (!canRequest){
            return;
        }
        canRequest=false;
        //MvpRef.get().showLoadings();
        ApiClient.getInstance().startFishing(SpUtils.getToken(), num,type, new BaseObserver<LuckGiftBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(LuckGiftBean eggGiftModels) {
                if (eggGiftModels == null || eggGiftModels.getPrize_info()==null||eggGiftModels.getPrize_info().size() == 0) {
                    ToastUtils.showShort("很遗憾未能中奖");
                } else {
                    MvpRef.get().gameResult(eggGiftModels,num,type);
                }
                MvpRef.get().startFishingSuccess(num);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                canRequest=true;
            }
        });
    }

    @Override
    public void getRules() {
        ApiClient.getInstance().getCatHelp(SpUtils.getToken(), new BaseObserver<CatHelpModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(CatHelpModel catHelpModel) {
                MvpRef.get().gameRule(catHelpModel);
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void getPool(String type) {
        ApiClient.getInstance().getCatWinJackpot(SpUtils.getToken(),type, new BaseObserver<List<WinJackpotModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<WinJackpotModel> winJackpotModels) {
                MvpRef.get().poolList(winJackpotModels);
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
