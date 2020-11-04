package com.qpyy.module.index.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.module.index.api.ApiClient;
import com.qpyy.module.index.bean.AttentionResp;
import com.qpyy.module.index.bean.ManageRoomResp;
import com.qpyy.module.index.bean.MyFootResp;
import com.qpyy.module.index.bean.RecommendAttentionResp;
import com.qpyy.module.index.contacts.ChatRoomMeContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class ChatRoomMePresenter extends BasePresenter<ChatRoomMeContacts.View> implements ChatRoomMeContacts.ISearchPre {

    public ChatRoomMePresenter(ChatRoomMeContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getAttentionList() {
        ApiClient.getInstance().attentionList(new BaseObserver<List<AttentionResp>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<AttentionResp> attentionResps) {
                MvpRef.get().setAttentionListData(attentionResps);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getRecommendAttentionList() {
        ApiClient.getInstance().recommendAttentionList(new BaseObserver<List<RecommendAttentionResp>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<RecommendAttentionResp> recommendAttentionResps) {
                MvpRef.get().setRecommendAttentionList(recommendAttentionResps);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void ghostAttention(List<RecommendAttentionResp> data) {
        MvpRef.get().showLoadings();
        String roomIds = getUserIdToString(data);
        ApiClient.getInstance().ghostAttention(roomIds, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().ghostAttentionSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void getManageLists() {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().manageLists(new BaseObserver<List<ManageRoomResp>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<ManageRoomResp> manageRoomResps) {
                MvpRef.get().setManageData(manageRoomResps);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void removeManage(String id, int postion) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().removeManage(id, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().removeManageSuccess(postion);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void getMyFoot(int page) {
        ApiClient.getInstance().getMyFoot(new BaseObserver<List<MyFootResp>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<MyFootResp> myFootResps) {
                MvpRef.get().setMyFootData(myFootResps,page);
            }

            @Override
            public void onComplete() {
                MvpRef.get().finishRefresh();
            }
        },page);
    }

    @Override
    public void delfoot() {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().delfoot(new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().delfootSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void removeFavorite(String roomId, int position) {
        ApiClient.getInstance().removeFavorite(roomId, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().removeFavoriteSuccess(position);
            }

            @Override
            public void onComplete() {

            }
        });
    }


    private String getUserIdToString(List<RecommendAttentionResp> data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                sb.append(",");
            }
            sb.append(data.get(i).getRoom_id());
        }
        return sb.toString();
    }
}
