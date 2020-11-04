package com.yutang.game.grabmarbles.ui.presenter

import com.yutang.game.grabmarbles.net.Api
import com.yutang.game.grabmarbles.ui.contacts.RulerContacts
import com.yutang.game.grabmarbles.utils.OKGoRequest

internal class RulerPresenter : RulerContacts.Presenter {

    var view: RulerContacts.View? = null

    fun bindView(view: RulerContacts.View) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    override fun getRuler() {
        OKGoRequest.getRequest(
            Api.API_RULER,
            object : OKGoRequest.CallBack<String> {
                override fun onSuccess(data: String) {
                    view?.getRulerSuccess(data)
                }

                override fun onError() {


                }
            })
    }
}