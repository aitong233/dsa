package com.yutang.game.fudai.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.SpUtils;
import com.yutang.game.fudai.bean.CatFishingModel;
import com.qpyy.libcommon.bean.LuckyPackLuckyRankItemBean;
import com.yutang.game.fudai.contacts.GameRankContacts;
import com.yutang.game.fudai.net.ApiClient;

import java.util.List;

import io.reactivex.disposables.Disposable;


public class GameRankPresenter extends BasePresenter<GameRankContacts.View> implements GameRankContacts.GameRankPre {
    public GameRankPresenter(GameRankContacts.View view, Context context) {
        super(view, context);
    }


    public void getLuckyList(int type) { //1.礼物榜 2.手气榜

        ApiClient.getInstance().getLuckyList(SpUtils.getToken(),type, new BaseObserver<List<LuckyPackLuckyRankItemBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<LuckyPackLuckyRankItemBean> luckyRankList) {
                MvpRef.get().luckyRankList(type,luckyRankList);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
