package com.yutang.game.grabmarbles.bean

internal data class RoomDetail(
    val gift: Gift,
    val has_join: Boolean,
    val join_user_count: Int,
    val pay_rule: String,
    val reward_count: Int,
    val remain_seconds: Int,
    val serial: Int,
    val status: Int,
    val user_count_limit: Int,
    val base_money: Int,
    val user_list: List<User>
)
