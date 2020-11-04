package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.AgreeApplyModel;
import com.spadea.xqipao.data.RowWheatModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.WheatPositionContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class WheatPositionContactsPresenter extends BasePresenter<WheatPositionContacts.View> implements WheatPositionContacts.IWheatPositionPre {

    public WheatPositionContactsPresenter(WheatPositionContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void applyWheatList(String roomId) {
        api.applyWheatList(MyApplication.getInstance().getToken(), roomId, new BaseObserver<List<RowWheatModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<RowWheatModel> rowWheatModels) {
                MvpRef.get().setApplyWheatList(rowWheatModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().applyWheatListComplete();
            }
        });
    }

    @Override
    public void agreeApply(String id, String roomId) {
        api.agreeApply(MyApplication.getInstance().getToken(), id,roomId, new BaseObserver<AgreeApplyModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(AgreeApplyModel s) {
                MvpRef.get().agreeApplySuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void deleteApply(String id, String roomId) {
        api.deleteApply(MyApplication.getInstance().getToken(), id, roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().deleteApplySuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void agreeApplyAll(String roomId) {
        api.agreeApplyAll(MyApplication.getInstance().getToken(), roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().agreeApplyAllSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
