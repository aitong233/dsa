package com.yutang.game.turntable.contacts

import com.qpyy.libcommon.bean.TurntableLuckyRank
import com.yutang.game.turntable.bean.LuckyModel
import java.util.ArrayList

class TurntableLuckyRankContacts {
    interface View {
        fun onLuckyRankSuccess(t: ArrayList<TurntableLuckyRank>)
    }

    interface Presenter
}