package com.qpyy.module.index.presenter;

import android.content.Context;

import com.blankj.utilcode.util.ObjectUtils;
import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.index.api.ApiClient;
import com.qpyy.module.index.bean.CharmRankingResp;
import com.qpyy.module.index.contacts.RankingListContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class RankingListPresenter extends BasePresenter<RankingListContacts.View> implements RankingListContacts.IRankingListPre {
    public RankingListPresenter(RankingListContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getCharmList(String roomId, int type) {
        ApiClient.getInstance().getCharmList(roomId, type, new BaseObserver<CharmRankingResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(CharmRankingResp resp) {
                List<CharmRankingResp.ListsBean> lists = resp.getLists();
                MvpRef.get().setMyInfo(resp.getMy());
                if (!ObjectUtils.isEmpty(lists)) {
                    MvpRef.get().setNo1(lists.get(0));
                    if (lists.size() > 1) {
                        MvpRef.get().setNo2(lists.get(1));
                    }
                    if (lists.size() > 2) {
                        MvpRef.get().setNo3(lists.get(2));
                    }
                    if (lists.size() > 3) {
                        MvpRef.get().setList(lists.subList(3, lists.size()));
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getWealthList(String roomId, int type) {
        ApiClient.getInstance().getWealthList(roomId, type, new BaseObserver<CharmRankingResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(CharmRankingResp resp) {
                List<CharmRankingResp.ListsBean> lists = resp.getLists();
                MvpRef.get().setMyInfo(resp.getMy());
                if (!ObjectUtils.isEmpty(lists)) {
                    MvpRef.get().setNo1(lists.get(0));
                    if (lists.size() > 1) {
                        MvpRef.get().setNo2(lists.get(1));
                    }
                    if (lists.size() > 2) {
                        MvpRef.get().setNo3(lists.get(2));
                    }
                    if (lists.size() > 3) {
                        MvpRef.get().setList(lists.subList(3, lists.size()));
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}