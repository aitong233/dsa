package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.room.api.ApiClient;
import com.qpyy.room.bean.NextBoxContentResp;
import com.qpyy.room.contacts.NextBoxContacts;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.presenter
 * 创建人 黄强
 * 创建时间 2020/9/25 16:19
 * 描述 describe
 */
public class NextBoxPresenter extends BaseRoomPresenter<NextBoxContacts.View> implements NextBoxContacts.INextBoxPre {
    public NextBoxPresenter(NextBoxContacts.View view, Context context) {
        super(view, context);
    }

    public void getContent() {
        ApiClient.getInstance().getNextBoxContent(new BaseObserver<NextBoxContentResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(NextBoxContentResp nextBoxContentResp) {
                MvpRef.get().setContent(nextBoxContentResp.getContent());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
