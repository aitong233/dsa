package com.yutang.game.fudai.contacts

import android.support.v4.app.FragmentActivity
import com.qpyy.libcommon.base.IPresenter
import com.qpyy.libcommon.base.IView
import com.yutang.game.fudai.bean.LuckGiftBean

class GameResultContacts {
    interface View : IView<FragmentActivity> {
        fun startFishingSuccess(num: Int)

        fun gameResult(eggGiftModels: LuckGiftBean?, num: Int, type: Int)

    }

    interface Presenter : IPresenter {

    }
}