package com.yutang.game.grabmarbles.bean

internal data class User(
    val head_picture: String,
    val is_pay: Int,
    val nickname: String,
    val reward: Int,
    val user_code: Int,
    val user_id: Int
)

data class User2(
    val head_picture: String,
    val is_continue: String,
    val nickname: String,
    val reward: String,
    val user_code: String,
    val user_id: String
)