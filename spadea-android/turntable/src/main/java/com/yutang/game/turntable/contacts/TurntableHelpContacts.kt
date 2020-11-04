package com.yutang.game.turntable.contacts

import com.yutang.game.turntable.bean.GameHelpModel

class TurntableHelpContacts {
    interface View {
        fun onGetHelpSuccess(t: GameHelpModel)
    }

    interface Presenter
}