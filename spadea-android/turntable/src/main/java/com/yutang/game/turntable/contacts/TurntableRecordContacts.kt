package com.yutang.game.turntable.contacts

import com.yutang.game.turntable.bean.GameLogModel

class TurntableRecordContacts {
    interface View {
        fun onGetLogSuccess(list: List<GameLogModel>)
    }

    interface Presenter
}