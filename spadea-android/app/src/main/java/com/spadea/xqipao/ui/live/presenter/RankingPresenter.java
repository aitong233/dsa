package com.spadea.xqipao.ui.live.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.CharmModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.live.contacts.RankingContacts;

import io.reactivex.disposables.Disposable;

public class RankingPresenter extends BasePresenter<RankingContacts.View> implements RankingContacts.IRankingPre {

    public RankingPresenter(RankingContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getCharmList(String roomId, int type) {
        api.getCharmList(MyApplication.getInstance().getToken(), type, roomId, new BaseObserver<CharmModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(CharmModel charmModel) {
                LogUtils.e("网络请求成功：", charmModel);
                if (isViewAttach() && charmModel != null) {
                    MvpRef.get().setUserData(charmModel.getMy());
                    if (charmModel.getLists().size() == 1) {
                        MvpRef.get().setNo1(charmModel.getLists().get(0));
                    } else if (charmModel.getLists().size() == 2) {
                        MvpRef.get().setNo1(charmModel.getLists().get(0));
                        MvpRef.get().setNo2(charmModel.getLists().get(1));
                    } else if (charmModel.getLists().size() >= 3) {
                        MvpRef.get().setNo1(charmModel.getLists().get(0));
                        MvpRef.get().setNo2(charmModel.getLists().get(1));
                        MvpRef.get().setNo3(charmModel.getLists().get(2));
                        MvpRef.get().setListData(charmModel.getLists().subList(3, charmModel.getLists().size()));
                    }
                }
            }

            @Override
            public void onComplete() {
                MvpRef.get().networkCompletion();
            }
        });
    }

    @Override
    public void getWealthList(String roomId, int type) {
        api.getWealthList(MyApplication.getInstance().getToken(), type, roomId, new BaseObserver<CharmModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(CharmModel charmModel) {
                LogUtils.e("网络请求成功：", charmModel);
                if (isViewAttach() && charmModel != null) {
                    MvpRef.get().setUserData(charmModel.getMy());
                    if (charmModel.getLists().size() == 1) {
                        MvpRef.get().setNo1(charmModel.getLists().get(0));
                    } else if (charmModel.getLists().size() == 2) {
                        MvpRef.get().setNo1(charmModel.getLists().get(0));
                        MvpRef.get().setNo2(charmModel.getLists().get(1));
                    } else if (charmModel.getLists().size() >= 3) {
                        MvpRef.get().setNo1(charmModel.getLists().get(0));
                        MvpRef.get().setNo2(charmModel.getLists().get(1));
                        MvpRef.get().setNo3(charmModel.getLists().get(2));
                        MvpRef.get().setListData(charmModel.getLists().subList(3, charmModel.getLists().size()));
                    }
                }
            }

            @Override
            public void onComplete() {
                MvpRef.get().networkCompletion();
            }
        });
    }
}
