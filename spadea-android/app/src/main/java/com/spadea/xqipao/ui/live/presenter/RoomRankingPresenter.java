package com.spadea.xqipao.ui.live.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.RoomRankingModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.live.contacts.RoomRankingContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class RoomRankingPresenter extends BasePresenter<RoomRankingContacts.View> implements RoomRankingContacts.IRoomRankingPre {

    public RoomRankingPresenter(RoomRankingContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getRoomRankingList() {
        api.getRoomRankingList(MyApplication.getInstance().getToken(), new BaseObserver<List<RoomRankingModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<RoomRankingModel> listsBeans) {
                if (listsBeans != null && listsBeans.size() != 0) {
                    switch (listsBeans.size()) {
                        case 1:
                            MvpRef.get().setNo1(listsBeans.get(0));
                            break;
                        case 2:
                            MvpRef.get().setNo1(listsBeans.get(0));
                            MvpRef.get().setNo2(listsBeans.get(1));
                            break;
                        case 3:
                            MvpRef.get().setNo1(listsBeans.get(0));
                            MvpRef.get().setNo2(listsBeans.get(1));
                            MvpRef.get().setNo3(listsBeans.get(2));
                            MvpRef.get().setListData(listsBeans.subList(3, listsBeans.size()));
                            break;
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);

            }

            @Override
            public void onComplete() {
                MvpRef.get().networkCompletion();
            }
        });
    }


}
