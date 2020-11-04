package com.yutang.game.grabmarbles.bean

data class ResponseData<T>(
    val status: Int = 0,
    val info: String? = null,
    val data: T? = null
) {

}