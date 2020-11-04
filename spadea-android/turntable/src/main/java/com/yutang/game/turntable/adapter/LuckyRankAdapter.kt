package com.yutang.game.turntable.adapter

import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.qpyy.libcommon.bean.TurntableLuckyRank
import com.yutang.game.turntable.R
import com.yutang.game.turntable.bean.LuckyModel
import kotlinx.android.synthetic.main.turntable_item_luckyrank.view.*

class LuckyRankAdapter : BaseQuickAdapter<TurntableLuckyRank, BaseViewHolder>(R.layout.turntable_item_luckyrank) {
    @SuppressLint("SetTextI18n")
    override fun convert(helper: BaseViewHolder?, item: TurntableLuckyRank) {
        helper?.itemView?.run {
            Glide.with(context).load(item.head_picture).into(civ_head)
            tv_nickname.text = item.nickname
            tv_times.text = "x" + item.number.toString()
            tv_gift_name.text = item.gift_name
            tv_gift_number.text = "x" + item.gift_number
        }
    }
}