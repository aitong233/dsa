package com.spadea.xqipao.ui.game.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.CatFishingModel;
import com.spadea.xqipao.data.CatHelpModel;
import com.spadea.xqipao.data.EggGiftModel;
import com.spadea.xqipao.data.WinJackpotModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.utils.dialog.CatFishingHelpDialog;
import com.spadea.xqipao.utils.dialog.CatFishingInJackpotDialog;
import com.spadea.xqipao.utils.dialog.CatFishingJackpotDialog;
import com.spadea.xqipao.utils.dialog.CatFishingRankingDialog;
import com.spadea.xqipao.utils.dialog.PropDialog;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.game.contacts.CatFishingContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class CatFishingPresenter extends BasePresenter<CatFishingContacts.View> implements CatFishingContacts.ICatFishingPre {


    private CatFishingRankingDialog catFishingRankingDialog;
    private CatFishingHelpDialog catFishingHelpDialog;
    private CatFishingJackpotDialog catFishingJackpotDialog;
    private PropDialog propDialog;

    private CatFishingInJackpotDialog catFishingInJackpotDialog;


    public CatFishingPresenter(CatFishingContacts.View view, Context context) {
        super(view, context);
    }


    private void showCatFinshing(List<CatFishingModel> catFishingModels) {
        if (catFishingRankingDialog == null) {
            catFishingRankingDialog = new CatFishingRankingDialog(mContext);
        }
        catFishingRankingDialog.setData(catFishingModels);
        catFishingRankingDialog.show();
    }

    @Override
    public void getWinRanking() {
//        api.getWinRanking(MyApplication.getInstance().getToken(), new BaseObserver<List<CatFishingModel>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                addDisposable(d);
//            }
//
//            @Override
//            public void onNext(List<CatFishingModel> catFishingModels) {
//                showCatFinshing(catFishingModels);
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }

    @Override
    public void getCatHelp() {
        MvpRef.get().showLoadings();
        api.getCatHelp(MyApplication.getInstance().getToken(), new BaseObserver<CatHelpModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(CatHelpModel catHelpModel) {
                showCatHelp(catHelpModel.getContent());
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void getCatWinJackpot() {
//        MvpRef.get().showLoadings();
//        api.getCatWinJackpot(MyApplication.getInstance().getToken(), new BaseObserver<List<WinJackpotModel>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                addDisposable(d);
//            }
//
//            @Override
//            public void onNext(List<WinJackpotModel> winJackpotModels) {
//                showCatJackpot(winJackpotModels);
//            }
//
//            @Override
//            public void onComplete() {
//                MvpRef.get().disLoadings();
//            }
//        });
    }

    @Override
    public void getPropList() {

    }

    @Override
    public void getFishInfo() {
//        api.getFishInfo(new BaseObserver<FishInfoBean>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                addDisposable(d);
//            }
//
//            @Override
//            public void onNext(FishInfoBean fishInfoBean) {
//                MvpRef.get().setFishInfo(fishInfoBean);
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }

    @Override
    public void startFishing(int num) {
//        MvpRef.get().showLoadings();
//        api.startFishing(MyApplication.getInstance().getToken(), num, new BaseObserver<List<EggGiftModel>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                addDisposable(d);
//            }
//
//            @Override
//            public void onNext(List<EggGiftModel> eggGiftModels) {
//                if (eggGiftModels==null||eggGiftModels.size()==0){
//                    ToastUtils.showShort("很遗憾未能中奖");
//                }else {
//                    showInJackpot(eggGiftModels);
//                }
//                MvpRef.get().startFishingSuccess(num);
//            }
//
//            @Override
//            public void onComplete() {
//             MvpRef.get().disLoadings();
//            }
//        });
    }


    private void showCatHelp(String text) {
        if (catFishingHelpDialog == null) {
            catFishingHelpDialog = new CatFishingHelpDialog(mContext);
        }
        catFishingHelpDialog.setData(text);
        catFishingHelpDialog.show();
    }

    private void showCatJackpot(List<WinJackpotModel> list) {
        if (catFishingJackpotDialog == null) {
            catFishingJackpotDialog = new CatFishingJackpotDialog(mContext);
        }
        catFishingJackpotDialog.setData(list);
        catFishingJackpotDialog.show();
    }


    private void showProp(){
        if (propDialog==null){
            propDialog = new PropDialog(mContext);
        }
        propDialog.show();
    }


    private void showInJackpot(List<EggGiftModel> eggGiftModels){
      if (catFishingInJackpotDialog==null){
          catFishingInJackpotDialog = new CatFishingInJackpotDialog(mContext);
      }
        catFishingInJackpotDialog.setData(eggGiftModels);
        catFishingInJackpotDialog.show();
    }
}
