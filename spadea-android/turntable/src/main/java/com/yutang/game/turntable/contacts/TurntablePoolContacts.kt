package com.yutang.game.turntable.contacts

import com.yutang.game.turntable.bean.GamePoolModel

class TurntablePoolContacts {
    interface View{

        fun onPoolSuccess(t: ArrayList<GamePoolModel>)

    }

    interface Presenter
}