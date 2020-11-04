package com.qpyy.module.index.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.module.index.api.ApiClient;
import com.qpyy.module.index.bean.RecordSection;
import com.qpyy.module.index.bean.RoomResultResp;
import com.qpyy.module.index.bean.SearchResp;
import com.qpyy.module.index.bean.UserResultResp;
import com.qpyy.module.index.contacts.SearchContacts;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class SearchPresenter extends BasePresenter<SearchContacts.View> implements SearchContacts.ISearchPre {

    private List<String> mHistory = new ArrayList<>();

    public SearchPresenter(SearchContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void getSearchHistory() {
        String data = SpUtils.getSearchHistory();
        if (!StringUtils.isEmpty(data)) {
            mHistory = JSON.parseArray(data, String.class);
        } else {
            mHistory.clear();
        }
        MvpRef.get().setSearchHistory(mHistory);
    }

    @Override
    public void saveSearchHistory(String keyWord) {
        mHistory.remove(keyWord);
        mHistory.add(0, keyWord);
        SpUtils.saveSearchHistory(JSON.toJSONString(mHistory));
    }

    @Override
    public void deleteSearchHistory() {
        SpUtils.saveSearchHistory("");
    }

    @Override
    public void search(String keyWord) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().getSearch(keyWord, new BaseObserver<SearchResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(SearchResp searchResp) {
                MvpRef.get().setSearch(searchResp);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void fuzzyQuery(String keyWord) {
        ApiClient.getInstance().getSearch(keyWord, new BaseObserver<SearchResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(SearchResp searchResp) {
                List<RecordSection> data = new ArrayList<>();
                RoomResultResp room_result = searchResp.getRoom_result();
                List<RoomResultResp.RoomResultInfo> list = room_result.getList();
                if (list != null && list.size() != 0) {
                    data.add(new RecordSection(true, "相关房间"));
                    for (RoomResultResp.RoomResultInfo item : list) {
                        data.add(new RecordSection(item.getRoom_name()));
                    }
                }
                List<UserResultResp> user_result = searchResp.getUser_result();
                if (user_result != null && user_result.size() != 0) {
                    data.add(new RecordSection(true, "相关用户"));
                    for (UserResultResp item : user_result) {
                        data.add(new RecordSection(item.getNickname()));
                    }
                }
                MvpRef.get().setFuzzyQuery(data);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void followUser(String userId, int type, int postion) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().followUser(userId, type, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().followUserSuccess(type, postion);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
