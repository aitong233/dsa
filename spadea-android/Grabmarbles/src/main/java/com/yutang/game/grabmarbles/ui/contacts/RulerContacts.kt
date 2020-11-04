package com.yutang.game.grabmarbles.ui.contacts

internal interface RulerContacts {

    interface View {

        fun getRulerSuccess(h5: String)

    }

    interface Presenter {
        fun getRuler()
    }

}