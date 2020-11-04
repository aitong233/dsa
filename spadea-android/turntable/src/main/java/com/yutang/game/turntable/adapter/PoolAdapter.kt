package com.yutang.game.turntable.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yutang.game.turntable.R
import com.yutang.game.turntable.bean.GameLogModel
import com.yutang.game.turntable.bean.GamePoolModel
import kotlinx.android.synthetic.main.turntable_item_pool.view.*

class PoolAdapter : BaseQuickAdapter<GamePoolModel, BaseViewHolder>(R.layout.turntable_item_pool) {
    override fun convert(helper: BaseViewHolder?, item: GamePoolModel) {
        helper?.itemView?.run {
            Glide.with(context).load(item.picture).into(iv_gift)
            Glide.with(context).load(item.gift_frame).into(iv_frame)
            tv_gift_name.text = item.name
            tv_price.text = item.price.toString()
        }

    }
}