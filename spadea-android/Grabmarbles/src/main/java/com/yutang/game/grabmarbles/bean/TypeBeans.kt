package com.yutang.game.grabmarbles.bean

internal data class Type1Bean(
    val gift: Gift,
    val join_user_count: Int,
    val pay_rule: String,
    val serial: Int,
    val start_time: String,
    val user_count_limit: Int,
    val base_money: Int
)

internal data class Type2Bean(
    val gift: Gift,
    val join_user_count: Int,
    val remain_seconds: Int,
    val serial: Int,
    val start_time: String,
    val user_count_limit: Int,
    val base_money: Int,
    val user_list: List<User>
)

internal data class Type3Bean(
    val join_user_count: Int,
    val serial: Int,
    val start_time: String,
    val user_count_limit: Int
)

internal data class Type4Bean(
    val remain_seconds: Int,
    val serial: Int,
    val start_time: String
)

