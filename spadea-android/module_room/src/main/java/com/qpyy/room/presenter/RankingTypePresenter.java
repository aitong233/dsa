package com.qpyy.room.presenter;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.ObjectUtils;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.CharmRankingResp;
import com.qpyy.room.bean.WealthRankingResp;
import com.qpyy.room.contacts.RankingTypeContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.presenter
 * 创建人 黄强
 * 创建时间 2020/7/24 17:19
 * 描述 describe
 */
public class RankingTypePresenter extends BaseRoomPresenter<RankingTypeContacts.View> implements RankingTypeContacts.IRoomRankingTypePre {


    public RankingTypePresenter(RankingTypeContacts.View view, Context context) {
        super(view, context);
    }
}
