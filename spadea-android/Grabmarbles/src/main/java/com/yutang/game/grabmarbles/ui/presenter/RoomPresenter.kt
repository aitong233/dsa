package com.yutang.game.grabmarbles.ui.presenter

import com.yutang.game.grabmarbles.bean.RoomDetail
import com.yutang.game.grabmarbles.net.Api
import com.yutang.game.grabmarbles.ui.contacts.RoomContacts
import com.yutang.game.grabmarbles.utils.OKGoRequest

internal class RoomPresenter : RoomContacts.Presenter {

    var view: RoomContacts.View? = null

    fun bindView(view: RoomContacts.View) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    override fun getRoomDetail() {
        OKGoRequest.getRequest(
            Api.API_ROOM_DETAIL, object : OKGoRequest.CallBack<RoomDetail> {
                override fun onSuccess(data: RoomDetail) {
                    view?.getDetailSuccess(data)
                }

                override fun onError() {
                    view?.onError()
                }
            })
    }
}