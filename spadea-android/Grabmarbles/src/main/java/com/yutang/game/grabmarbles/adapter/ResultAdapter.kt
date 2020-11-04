package com.yutang.game.grabmarbles.adapter

import android.graphics.Color
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yutang.game.grabmarbles.R
import com.yutang.game.grabmarbles.bean.Gift
import com.yutang.game.grabmarbles.bean.User
import kotlinx.android.synthetic.main.qdz_item_result.view.*

internal class ResultAdapter : BaseQuickAdapter<User, BaseViewHolder>(R.layout.qdz_item_result) {

    var gift: Gift? = null
    override fun convert(helper: BaseViewHolder, item: User) {
        if (item.is_pay == 1) {
            helper.setBackgroundRes(R.id.cl_root, R.drawable.qdz_bg_item_result_continue)
            helper.setVisible(R.id.iv_zuihoujielong, true)
        } else {
            helper.setBackgroundRes(R.id.cl_root, R.drawable.qdz_bg_item_result_normal)
            helper.setVisible(R.id.iv_zuihoujielong, false)
        }
        if (item.head_picture.isNotEmpty())
            Glide.with(mContext).load(item.head_picture).into(helper.itemView.iv_head)

        gift?.let {
            Glide.with(mContext).load(it.picture).into(helper.itemView.iv_candy)
        }
        helper.setText(R.id.tv_nickname, item.nickname)
        helper.setText(R.id.tv_id, "ID:" + item.user_code)
        helper.setText(R.id.tv_candy_count, "x" + item.reward.toString())
    }
}