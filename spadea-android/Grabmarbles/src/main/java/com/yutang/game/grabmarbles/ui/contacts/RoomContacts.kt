package com.yutang.game.grabmarbles.ui.contacts

import com.yutang.game.grabmarbles.bean.RoomDetail


internal interface RoomContacts {

    interface View {

        fun getDetailSuccess(roomDetail: RoomDetail)

        fun onError()
    }

    interface Presenter {
        fun getRoomDetail()

    }

}