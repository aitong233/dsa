package com.qpyy.room.presenter;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.ObjectUtils;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.CharmRankingResp;
import com.qpyy.room.bean.WealthRankingResp;
import com.qpyy.room.contacts.DataListContacts;
import com.qpyy.room.contacts.RankingTypeContacts;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.presenter
 * 创建人 黄强
 * 创建时间 2020/7/30 11:20
 * 描述 describe
 */
public class DataListPresenter extends BaseRoomPresenter<DataListContacts.View> implements DataListContacts.IRoomDataListPre {
    private static final String TAG = "DataListPresenter";

    public DataListPresenter(DataListContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void getCharmListInfo(String roomId, int type) {
        ApiClient.getInstance().getCharmList(roomId, type, new BaseObserver<CharmRankingResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: isOK");
                addDisposable(d);
            }

            @Override
            public void onNext(CharmRankingResp charmRankingResp) {
                //RxJava + Retrefit + mvp模式
                //charmRankingResp请求成功获取到的结果
                //获取魅力ListsBean数据
                List<CharmRankingResp.ListsBean> lists = charmRankingResp.getLists();
                if (!ObjectUtils.isEmpty(lists)) {//lists判空
                    //榜一
                    if (lists.size() > 0) {
                        MvpRef.get().setNo1(lists.get(0));
                    }
                    //榜二
                    if (lists.size() > 1) {
                        MvpRef.get().setNo2(lists.get(1));
                    }
                    //榜三
                    if (lists.size() > 2) {
                        MvpRef.get().setNo3(lists.get(2));
                    }
                    //第四名及后
                    if (lists.size() > 3) {
                        MvpRef.get().setCharmView(lists.subList(3, lists.size()));
                    }
                } else {
                    MvpRef.get().setNo1(new CharmRankingResp.ListsBean());
                    MvpRef.get().setNo2(new CharmRankingResp.ListsBean());
                    MvpRef.get().setNo3(new CharmRankingResp.ListsBean());
                    MvpRef.get().setCharmView(new ArrayList<>());
                }
                Log.d(TAG, "onNext:  getCharmListInfo isOK");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: isOK");
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(TAG, "onError: getCharmListInfo isError");
            }
        });
    }

    @Override
    public void getWealthListInfo(String roomId, int type) {
        ApiClient.getInstance().getWealthList(roomId, type, new BaseObserver<WealthRankingResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(WealthRankingResp wealthRankingResp) {
                //获取财富ListsBean数据
                List<WealthRankingResp.ListsBean> lists = wealthRankingResp.getLists();
                if (!ObjectUtils.isEmpty(lists)) {//lists判空
                    //榜一
                    if (lists.size() > 0) {
                        MvpRef.get().setNo1(lists.get(0));
                    }
                    //榜二
                    if (lists.size() > 1) {
                        MvpRef.get().setNo2(lists.get(1));
                    }
                    //榜三
                    if (lists.size() > 2) {
                        MvpRef.get().setNo3(lists.get(2));
                    }
                    //第四名及后
                    if (lists.size() > 3) {
                        MvpRef.get().setWealthView(lists.subList(3, lists.size()));
                    }
                }else {
                    MvpRef.get().setNo1(new WealthRankingResp.ListsBean());
                    MvpRef.get().setNo2(new WealthRankingResp.ListsBean());
                    MvpRef.get().setNo3(new WealthRankingResp.ListsBean());
                    MvpRef.get().setWealthView(new ArrayList<>());
                }
            }

            @Override
            public void onComplete() {

            }
        });

    }

}
