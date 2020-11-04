package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.RoomManageModel;
import com.spadea.xqipao.data.SearchUserModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.AddContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class AddPresenter extends BasePresenter<AddContacts.View> implements AddContacts.IAddPre {

    public AddPresenter(AddContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void searChUser(String roomId, String keyword, int type) {
        MvpRef.get().showLoadings();
        api.getSearChUser(MyApplication.getInstance().getToken(), roomId, keyword, type, new BaseObserver<List<SearchUserModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<SearchUserModel> searchUserModels) {
                MvpRef.get().setSearChUserData(searchUserModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                MvpRef.get().searchUserComplete();
            }
        });
    }

    @Override
    public void addManager(String roomId, String userId, SearchUserModel searchUserModel, int postion) {
        MvpRef.get().showLoadings();
        api.addManager(MyApplication.getInstance().getToken(), roomId, userId, new BaseObserver<RoomManageModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomManageModel s) {
                searchUserModel.setValue("1");
                MvpRef.get().success(searchUserModel, postion);
                MvpRef.get().addAdminSuccess(userId);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void deleteManager(String roomId, String userId, SearchUserModel searchUserModel, int postion) {
        MvpRef.get().showLoadings();
        api.deleteManager(MyApplication.getInstance().getToken(), roomId, userId, new BaseObserver<RoomManageModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomManageModel s) {
                searchUserModel.setValue("0");
                MvpRef.get().success(searchUserModel, postion);
                MvpRef.get().deleteManagerSuccess(userId);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void addRorbid(String roomId, String userId, SearchUserModel searchUserModel, int postion) {
        MvpRef.get().showLoadings();
        api.addRorbid(MyApplication.getInstance().getToken(), roomId, userId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                searchUserModel.setValue("1");
                MvpRef.get().success(searchUserModel, postion);
                MvpRef.get().addRorbidSuccess(userId);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void deleteForbid(String roomId, String userId, SearchUserModel searchUserModel, int postion) {
        MvpRef.get().showLoadings();
        api.deleteForbid(MyApplication.getInstance().getToken(), roomId, userId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                searchUserModel.setValue("0");
                MvpRef.get().success(searchUserModel, postion);
                MvpRef.get().deleteForbidSuccess(userId);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void getList(String roomId, int type) {
        MvpRef.get().showLoadings();
        api.getRoomList(roomId, type, new BaseObserver<List<SearchUserModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<SearchUserModel> searchUserModels) {
                MvpRef.get().setSearChUserData(searchUserModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                MvpRef.get().searchUserComplete();
            }
        });
    }
}
