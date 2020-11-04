package com.yutang.game.tangguobao

import com.blankj.utilcode.util.SPUtils

object TGBManager {

    fun setUserId(userId: Int) {
        SPUtils.getInstance("tgb").put("userId", userId)
    }

    fun getUserId(): Int {
        return SPUtils.getInstance("tgb").getInt("userId")
    }
}