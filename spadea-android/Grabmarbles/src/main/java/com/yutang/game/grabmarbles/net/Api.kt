package com.yutang.game.grabmarbles.net

import com.yutang.game.grabmarbles.GrabMarblesManager


internal object Api {
    //生产环境http://spritehbao.chenyol.cn/
    //开发环境http://spritehbao.game-center-test.yutangwl.com/

    private val BASE_URL: String = if (GrabMarblesManager.idDebug())
        ""
    else ""

    val API_RULER = BASE_URL + "red-envelope-rule"

    val API_ROOM_DETAIL = BASE_URL + "red-envelope-room-detail"

    val API_PAY_DEPOSIT = BASE_URL + "red-envelope-join"

    val API_CHECK = BASE_URL + "red-envelope-check"

    val API_RECORD = BASE_URL + "red-envelope-record"

}