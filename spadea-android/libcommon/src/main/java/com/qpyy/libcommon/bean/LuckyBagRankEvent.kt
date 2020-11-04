package com.qpyy.libcommon.bean

data class TurntableLuckyRankEvent(
        val rank_info: List<TurntableLuckyRank>
)

//{"user_id":3,"head_picture":"https:\/\/spriteyy.oss-cn-hangzhou.aliyuncs.com\/images\/default.png",
// "nickname":"\u7cbe\u7075\u7528\u6237906705","number":"50","type":1,"gift_id":276,
// "gift_name":"\u5b87\u5b99\u98de\u8239","gift_number":6,"add_time":"2020-10-26 17:36:58"}
data class TurntableLuckyRank(val user_id: Int, val head_picture: String, val number: String, val nickname: String,
                              val type: Int, val gift_id: Int, val gift_name: String, val gift_number: Int, val add_time: String)