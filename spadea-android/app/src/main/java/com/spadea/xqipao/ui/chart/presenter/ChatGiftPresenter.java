package com.spadea.xqipao.ui.chart.presenter;

import android.content.Context;

import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.bean.GiftModel;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module_news.api.ApiClient;
import com.qpyy.module_news.bean.GiftNumBean;
import com.qpyy.module_news.event.GiftMsgEvent;
import com.spadea.xqipao.ui.chart.contacts.ChatGiftContacts;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class ChatGiftPresenter extends BasePresenter<ChatGiftContacts.View> implements ChatGiftContacts.IChatGiftPre {
    public ChatGiftPresenter(ChatGiftContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void giftWall() {
        ApiClient.getInstance().giftWall(new BaseObserver<List<GiftModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
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
    public void giveGift(GiftModel giftModel, String user_id, String number) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().giveGift(giftModel.getId(), user_id, number, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                getBalance();
                ToastUtils.show("礼物赠送成功");
                EventBus.getDefault().post(new GiftMsgEvent(giftModel, number));
                MvpRef.get().pop();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    private List<GiftNumBean> normalList = new ArrayList<>();   //其他礼物列表

    private List<GiftNumBean> mhList = new ArrayList<>();   //盲盒数量列表

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
                for (GiftNumBean giftNumBean : giftNumBeans) {
                    int number = Integer.parseInt(giftNumBean.getNumber());
                    if (number == 0) {  //移除自定义选项
                        giftNumBeans.remove(giftNumBean);
                        break;
                    }
                }
                normalList.addAll(giftNumBeans);
                ArrayList<GiftNumBean> giftList = new ArrayList<>();    //需要移除的礼物集合
                for (GiftNumBean giftNumBean : giftNumBeans) {
                    int number = Integer.parseInt(giftNumBean.getNumber());
                    if (number > 88) {
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
    public void getBalance() {
        api.getBalance(new BaseObserver<String>() {
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
}