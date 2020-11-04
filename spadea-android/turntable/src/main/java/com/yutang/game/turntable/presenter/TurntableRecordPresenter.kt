package com.yutang.game.turntable.presenter

import com.qpyy.libcommon.http.BaseObserver
import com.yutang.game.turntable.bean.GameHelpModel
import com.yutang.game.turntable.bean.GameLogModel
import com.yutang.game.turntable.contacts.TurntableContacts
import com.yutang.game.turntable.contacts.TurntableHelpContacts
import com.yutang.game.turntable.contacts.TurntableRecordContacts
import com.yutang.game.turntable.net.ApiClient
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class TurntableRecordPresenter(var view: TurntableRecordContacts.View?) : TurntableRecordContacts.Presenter {
    val compositeDisposable = CompositeDisposable()

    fun getGameLog() {
        ApiClient.getInstance().getGameLog(object : BaseObserver<List<GameLogModel>>() {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: List<GameLogModel>) {
                view?.onGetLogSuccess(t)
            }
        })
    }



}