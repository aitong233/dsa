package com.yutang.game.fudai.adapter

import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yutang.game.fudai.R
import com.yutang.game.fudai.bean.LuckGiftBean
import kotlinx.android.synthetic.main.fudai_item_result.view.*

class ResultAdapter : BaseQuickAdapter<LuckGiftBean.PrizeInfoBean, BaseViewHolder>(R.layout.fudai_item_result) {
    @SuppressLint("SetTextI18n")
    override fun convert(helper: BaseViewHolder, item: LuckGiftBean.PrizeInfoBean?) {
        helper.itemView.run {
            item?.let {
                Glide.with(context).load(it.picture).into(iv_gift)
                tv_gift_name.text = it.name
                tv_gift_count.text = "x " + it.count
                tv_price.text = it.price.toString()
            }
        }
    }
}