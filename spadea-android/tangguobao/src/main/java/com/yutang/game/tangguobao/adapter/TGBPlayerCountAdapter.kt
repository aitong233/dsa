package com.yutang.game.tangguobao.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yutang.game.tangguobao.R

class TGBPlayerCountAdapter : BaseQuickAdapter<Int, BaseViewHolder>(R.layout.tgb_item_player_count) {

    override fun convert(helper: BaseViewHolder, item: Int?) {
        helper.setText(R.id.tv_player_count, item.toString() + "人")
    }
}