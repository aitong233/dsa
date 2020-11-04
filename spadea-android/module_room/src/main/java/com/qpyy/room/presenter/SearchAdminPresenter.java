package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.RoomAdminModel;
import com.qpyy.room.bean.SearchUserModel;
import com.qpyy.room.contacts.SearchAdminContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.presenter
 * 创建人 黄强
 * 创建时间 2020/7/30 18:58
 * 描述 describe
 */
public class SearchAdminPresenter extends BasePresenter<SearchAdminContacts.View> implements SearchAdminContacts.SearchAdminIpre {

    public SearchAdminPresenter(SearchAdminContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void searchUser(String roomId, String keyword, int type) {
        ApiClient.getInstance().getSearChUser(SpUtils.getToken(), roomId, keyword, type, new BaseObserver<List<SearchUserModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<SearchUserModel> searchUserModels) {
                MvpRef.get().setSearchUserData(searchUserModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().searchUserComplete();
            }
        });
    }

    @Override
    public void addAdmin(String roomId, String userId, SearchUserModel searchUserModel, int position) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().addManager(SpUtils.getToken(), roomId, userId, new BaseObserver<RoomAdminModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomAdminModel roomAdminModel) {
                searchUserModel.setValue("1");
                MvpRef.get().success(searchUserModel, position);
                MvpRef.get().addAdminSuccess(userId);
            }


            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void deleteAdmin(String roomId, String userId, SearchUserModel searchUserModel, int position) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().deleteManager(SpUtils.getToken(), roomId, userId, new BaseObserver<RoomAdminModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(RoomAdminModel roomAdminModel) {
                searchUserModel.setValue("0");
                MvpRef.get().success(searchUserModel, position);
                MvpRef.get().deleteAdminSuccess(userId);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void addBlacklist(String roomId, String userId, SearchUserModel searchUserModel, int position) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().addRorbid(SpUtils.getToken(), roomId, userId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                searchUserModel.setValue("1");
                MvpRef.get().success(searchUserModel, position);
                MvpRef.get().addBlacklistSuccess(userId);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void deleteBlacklist(String roomId, String userId, SearchUserModel searchUserModel, int position) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().deleteForbid(SpUtils.getToken(), roomId, userId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                searchUserModel.setValue("0");
                MvpRef.get().success(searchUserModel, position);
                MvpRef.get().deleteBlacklistSuccess(userId);
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
        ApiClient.getInstance().getRoomList(roomId, type, new BaseObserver<List<SearchUserModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<SearchUserModel> searchUserModels) {
                MvpRef.get().setSearchUserData(searchUserModels);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
                MvpRef.get().searchUserComplete();
            }
        });
    }

    @Override
    public void kickOut(String userId, String roomId, String userName) {
        ApiClient.getInstance().kickOut(SpUtils.getToken(), userId, roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().kickOutSuccess(userName);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
