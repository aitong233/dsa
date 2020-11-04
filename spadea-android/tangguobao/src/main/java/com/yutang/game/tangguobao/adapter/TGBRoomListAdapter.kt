package com.yutang.game.tangguobao.adapter

import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.bean.RoomInfo
import kotlinx.android.synthetic.main.tgb_item_room.view.*

class TGBRoomListAdapter : BaseQuickAdapter<RoomInfo, BaseViewHolder>(R.layout.tgb_item_room) {

    @SuppressLint("SetTextI18n")
    override fun convert(helper: BaseViewHolder, item: RoomInfo) {
        helper.itemView.run {
            tv_nickname.text = item.nickname
            if (item.head_picture.isNotEmpty()) {
                Glide.with(context).load(item.head_picture).into(iv_head)
            }
            tv_player_count.text = "${item.people_had_num}/${item.people_num}"
            tv_play_way.text = if (item.rule == 1) "最低接龙" else "最高接龙"
            tv_play_times.text = item.round_num.toString() + "局"
            tv_protect_money.text = (item.sugar_num * item.round_num).toString()
        }
    }
}