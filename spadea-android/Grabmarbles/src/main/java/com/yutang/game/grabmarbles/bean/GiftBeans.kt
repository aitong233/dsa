package com.yutang.game.grabmarbles.bean

data class Gift(
    val count: Int,
    val id: Int,
    val name: String,
    val picture: String,
    val special: String
)
internal data class Gift2(
    val count: String,
    val gift_id: String,
    val name: String,
    val picture: String,
    val special: String
)
