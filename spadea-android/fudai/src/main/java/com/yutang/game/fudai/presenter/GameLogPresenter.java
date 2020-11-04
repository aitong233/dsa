package com.yutang.game.fudai.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.http.BaseObserver;
import com.yutang.game.fudai.bean.GameLog;
import com.yutang.game.fudai.contacts.GameLogContacts;
import com.yutang.game.fudai.net.ApiClient;

import java.util.List;

import io.reactivex.disposables.Disposable;


public class GameLogPresenter extends BasePresenter<GameLogContacts.View> implements GameLogContacts.GameLogPre {
    public GameLogPresenter(GameLogContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getGameLog() {
        ApiClient.getInstance().gameLog(new BaseObserver<List<GameLog>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<GameLog> gameLogs) {
                MvpRef.get().gameLog(gameLogs);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
