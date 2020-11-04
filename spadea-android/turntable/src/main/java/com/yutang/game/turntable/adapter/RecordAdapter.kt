package com.yutang.game.turntable.adapter

import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yutang.game.turntable.R
import com.yutang.game.turntable.bean.GameLogModel
import kotlinx.android.synthetic.main.turntable_item_record.view.*

class RecordAdapter : BaseQuickAdapter<GameLogModel, BaseViewHolder>(R.layout.turntable_item_record) {
    @SuppressLint("SetTextI18n")
    override fun convert(helper: BaseViewHolder?, item: GameLogModel) {
        helper?.itemView?.run {
            tv_time.text = item.add_time
            tv_gift.text = item.name + "x" + item.number
            Glide.with(context).load(item.picture).into(iv_gift)
        }
    }
}