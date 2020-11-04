package com.yutang.game.tangguobao.adapter

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.TGBManager
import com.yutang.game.tangguobao.bean.PlayerInfo
import kotlinx.android.synthetic.main.tgb_item_player.view.*
import kotlinx.android.synthetic.main.tgb_item_player.view.iv_head
import kotlinx.android.synthetic.main.tgb_item_record_detail.view.*

class TGBPlayerListAdapter : BaseQuickAdapter<PlayerInfo, BaseViewHolder>(R.layout.tgb_item_player) {

    val userId = TGBManager.getUserId()

    override fun convert(helper: BaseViewHolder, item: PlayerInfo) {
        helper.itemView.run {
            if (!item.head_picture.isBlank()) {
                Glide.with(context).load(item.head_picture).into(iv_head)
            } else {
                iv_head.setImageResource(R.drawable.tgb_pic_head)
            }
            if (item.is_open) {
                tv_candy_num.text = item.sugar_num.toString()
            } else {
                tv_candy_num.text = "0"
            }
            when (item.status) {
                1 -> iv_prepare_status.visibility = View.VISIBLE
                2 -> iv_prepare_status.visibility = View.GONE
                3 -> iv_prepare_status.visibility = View.GONE
            }
            tv_id.text = "ID:" + item.user_code

            if (item.user_id == userId) {
                cl_bg.setBackgroundResource(R.drawable.tgb_bg_item_self)
            } else {
                cl_bg.setBackgroundResource(R.drawable.tgb_bg_item_other)
            }
        }
    }
}