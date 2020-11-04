package com.yutang.game.tangguobao.adapter

import android.annotation.SuppressLint
import android.view.View
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.bean.UserResult
import kotlinx.android.synthetic.main.tgb_item_result.view.*

class TGBResultAdapter : BaseQuickAdapter<UserResult, BaseViewHolder>(R.layout.tgb_item_result) {
    @SuppressLint("SetTextI18n")
    override fun convert(helper: BaseViewHolder?, item: UserResult) {

        helper?.itemView?.run {
            if (item.is_best)
                iv_winner.visibility = View.VISIBLE
            else
                iv_winner.visibility = View.GONE

            if (item.head_picture.isNotEmpty()) {
                Glide.with(context).load(item.head_picture).into(civ_head)
            } else {
                civ_head.setImageResource(R.drawable.tgb_pic_head)
            }

            tv_get_sugar_num.text = "获得弹珠：" + item.get_suger_num

            tv_lucky_times.text = item.max_count.toString()

            tv_jielong_times.text = item.send_count.toString()

        }
    }
}