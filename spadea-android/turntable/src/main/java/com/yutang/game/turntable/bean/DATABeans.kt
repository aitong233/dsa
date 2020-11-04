package com.yutang.game.turntable.bean

import java.io.Serializable

data class GameHelpModel(val title: String, val content: String)

data class GamePoolModel(val gift_id: String, val name: String, val picture: String, val price: Int, val gift_frame: String) {

}

data class GameInfoModel(val money: String, val price: String, val price_2: String, val price_3: String, val big_prize_show: Boolean)

data class GameLogModel(val user_id: String, val gift_id: String, val add_time: String, val name: String, val picture: String, val price: String, val number: Int)

data class SmashModel(val gift_id: String, val prize_title: String, val picture: String, val number: Int, val price: Int, val gift_frame: String) : Serializable

//{"number":"1","type":"1","nickname":"\u7cbe\u7075\u7528\u6237906705",
// "head_picture":"https:\/\/spriteyy.oss-cn-hangzhou.aliyuncs.com\/images\/default.png",
// "gift_name":"\u751c\u871c\u7269\u8bed","gift_number":"1","price":"13140",
// "gift_frame":"http:\/\/eartest.oss-cn-hangzhou.aliyuncs.com\/admin_images\/5f94038bce326.png"}
data class LuckyModel(val number: Int, val type: Int, val nickname: String, val head_picture: String,
                      val gift_name: String, val gift_number: Int, val price: String, val gift_frame: String)

data class GameSmashEvent(val number: Int)