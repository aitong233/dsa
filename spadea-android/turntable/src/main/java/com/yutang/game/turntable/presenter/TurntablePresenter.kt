package com.yutang.game.turntable.presenter

import com.qpyy.libcommon.http.BaseObserver
import com.yutang.game.turntable.bean.GameInfoModel
import com.yutang.game.turntable.bean.GamePoolModel
import com.yutang.game.turntable.bean.SmashModel
import com.yutang.game.turntable.contacts.TurntableContacts
import com.yutang.game.turntable.net.ApiClient
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class TurntablePresenter(var view: TurntableContacts.View?) : TurntableContacts.Presenter {
    val compositeDisposable = CompositeDisposable()

    fun smash(number: Int) {
        ApiClient.getInstance().smash(number, object : BaseObserver<ArrayList<SmashModel>>() {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: ArrayList<SmashModel>) {
                view?.onSmashSuccess(t, number)
            }

            override fun onErrorCode(code: Int) {
                super.onErrorCode(code)
                view?.onSmashError()

            }

            override fun onError(e: Throwable) {
                super.onError(e)
                view?.onSmashError()
            }
        })
    }

    fun getGameInfo() {
        ApiClient.getInstance().getGameInfo(object : BaseObserver<GameInfoModel>() {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: GameInfoModel) {
                view?.onInfoSuccess(t)
            }
        })
    }

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