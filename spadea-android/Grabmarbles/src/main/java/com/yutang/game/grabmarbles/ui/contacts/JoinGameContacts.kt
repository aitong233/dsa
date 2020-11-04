package com.yutang.game.grabmarbles.ui.contacts

internal interface JoinGameContacts {

    interface View {
        fun onSuccess(h5: String)

        fun onError()
    }

    interface Presenter {
        fun joinGame()
    }

}