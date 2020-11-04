package com.yutang.game.turntable.adapter

import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yutang.game.turntable.R
import com.yutang.game.turntable.bean.SmashModel
import kotlinx.android.synthetic.main.turntable_item_result.view.*

class ResultAdapter : BaseQuickAdapter<SmashModel, BaseViewHolder>(R.layout.turntable_item_result) {
    @SuppressLint("SetTextI18n")
    override fun convert(helper: BaseViewHolder?, item: SmashModel) {
        helper?.itemView?.run {
            Glide.with(context).load(item.gift_frame).into(iv_frame)
            Glide.with(context).load(item.picture).into(iv_gift)
            tv_gift_name.text = item.prize_title
            tv_gift_number.text = "x" + item.number
            tv_price.text = item.price.toString()
        }
    }
}