package com.spadea.xqipao.ui.room.presenter;

import android.content.Context;

import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.EmojiModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.room.contacts.EmojContacts;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class EmojPresenter extends BasePresenter<EmojContacts.View> implements EmojContacts.IEmojPre {

    public EmojPresenter(EmojContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getFaceList() {
        api.getFaceList(MyApplication.getInstance().getToken(), new BaseObserver<List<EmojiModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<EmojiModel> emojiModels) {
                MvpRef.get().setFraceListData(emojiModels);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
