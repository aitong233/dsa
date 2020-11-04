package com.yutang.game.turntable.presenter

import com.qpyy.libcommon.bean.TurntableLuckyRank
import com.qpyy.libcommon.http.BaseObserver
import com.yutang.game.turntable.bean.GameInfoModel
import com.yutang.game.turntable.bean.LuckyModel
import com.yutang.game.turntable.contacts.TurntableContacts
import com.yutang.game.turntable.contacts.TurntableHelpContacts
import com.yutang.game.turntable.contacts.TurntableLuckyRankContacts
import com.yutang.game.turntable.net.ApiClient
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class TurntableLuckyRankPresenter(var view: TurntableLuckyRankContacts.View?) : TurntableLuckyRankContacts.Presenter {
    val compositeDisposable = CompositeDisposable()

    fun getLuckyRank() {
        ApiClient.getInstance().getLuckyRank(object : BaseObserver<ArrayList<TurntableLuckyRank>>() {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: ArrayList<TurntableLuckyRank>) {
                view?.onLuckyRankSuccess(t)
            }
        })
    }



}