package com.yutang.game.turntable.contacts

import com.yutang.game.turntable.bean.GameInfoModel
import com.yutang.game.turntable.bean.GamePoolModel
import com.yutang.game.turntable.bean.SmashModel

class TurntableContacts {
    interface View {
        fun onSmashSuccess(t: ArrayList<SmashModel>, number: Int)
        fun onPoolSuccess(t: ArrayList<GamePoolModel>)
        fun onInfoSuccess(t: GameInfoModel)
        fun onSmashError()
    }

    interface Presenter
}