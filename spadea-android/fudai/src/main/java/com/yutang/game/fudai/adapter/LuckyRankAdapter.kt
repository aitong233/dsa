package com.yutang.game.fudai.adapter

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.qpyy.libcommon.utils.ImageLoader
import com.yutang.game.fudai.R
import com.qpyy.libcommon.bean.LuckyPackLuckyRankItemBean

class LuckyRankAdapter : BaseQuickAdapter<LuckyPackLuckyRankItemBean, BaseViewHolder>(R.layout.item_game_lucky_rank) {

    override fun convert(helper: BaseViewHolder, item: LuckyPackLuckyRankItemBean) {
        ImageLoader.loadHead(mContext, helper.getView(R.id.civ_head), item.head_picture)
        helper.setText(R.id.tv_nickname, item.nickname)
        helper.setText(R.id.tv_gift_count, "X" + item.gift_number)
        helper.setText(R.id.tv_gift_name, item.gift_name)
        when (item.lucky_bag_type) {
            "1" -> {
                helper.setText(R.id.tv_lucky_bag_number, "银福袋X" + item.number)
                helper.setTextColor(R.id.tv_lucky_bag_number, Color.parseColor("#FF4182F9"))
            }
            "2" -> {
                helper.setText(R.id.tv_lucky_bag_number, "金福袋X" + item.number)
                helper.setTextColor(R.id.tv_lucky_bag_number, Color.parseColor("#FFFFC61B"))
            }
            "3" -> {
                helper.setText(R.id.tv_lucky_bag_number, "钻福袋X" + item.number)
                helper.setTextColor(R.id.tv_lucky_bag_number, Color.parseColor("#FF9650FF"))
            }
        }
        helper.setText(R.id.tv_time, item.add_time)
    }
}