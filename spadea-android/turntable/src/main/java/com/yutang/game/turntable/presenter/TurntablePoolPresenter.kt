package com.yutang.game.turntable.presenter

import com.qpyy.libcommon.http.BaseObserver
import com.yutang.game.turntable.bean.GamePoolModel
import com.yutang.game.turntable.contacts.TurntablePoolContacts
import com.yutang.game.turntable.net.ApiClient
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class TurntablePoolPresenter(var view: TurntablePoolContacts.View?) : TurntablePoolContacts.Presenter {

    val compositeDisposable = CompositeDisposable()

    fun getGamePool() {
        ApiClient.getInstance().getGamePool(object : BaseObserver<ArrayList<GamePoolModel>>() {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: ArrayList<GamePoolModel>) {
                view?.onPoolSuccess(t)
            }
        })
    }
}