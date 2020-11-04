package com.yutang.game.turntable.presenter

import com.qpyy.libcommon.http.BaseObserver
import com.yutang.game.turntable.bean.GameHelpModel
import com.yutang.game.turntable.bean.GamePoolModel
import com.yutang.game.turntable.contacts.TurntableContacts
import com.yutang.game.turntable.contacts.TurntableHelpContacts
import com.yutang.game.turntable.net.ApiClient
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class TurntableHelpPresenter(var view: TurntableHelpContacts.View?) : TurntableHelpContacts.Presenter {
    val compositeDisposable = CompositeDisposable()

    fun getGameHelp() {
        ApiClient.getInstance().getGameHelp(object : BaseObserver<GameHelpModel>() {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: GameHelpModel) {
                view?.onGetHelpSuccess(t)
            }
        })
    }
}