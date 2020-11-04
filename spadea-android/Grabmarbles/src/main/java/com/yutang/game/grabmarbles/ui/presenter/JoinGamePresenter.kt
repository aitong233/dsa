package com.yutang.game.grabmarbles.ui.presenter

import com.yutang.game.grabmarbles.net.Api
import com.yutang.game.grabmarbles.ui.contacts.JoinGameContacts
import com.yutang.game.grabmarbles.utils.OKGoRequest

internal class JoinGamePresenter : JoinGameContacts.Presenter {

    var view: JoinGameContacts.View? = null

    fun bindView(view: JoinGameContacts.View) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    override fun joinGame() {
        OKGoRequest.getRequest(Api.API_PAY_DEPOSIT,  object :
            OKGoRequest.CallBack<String> {
            override fun onSuccess(data: String) {
                view?.onSuccess(data)
            }

            override fun onError() {
                view?.onError()
            }

        })
    }
}