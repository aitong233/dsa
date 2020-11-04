package com.yutang.game.tangguobao.adapter

import android.graphics.Color
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.bean.PlayerRedPaper
import kotlinx.android.synthetic.main.tgb_item_record_detail.view.*

class TGBRecordDetailAdapter : BaseQuickAdapter<PlayerRedPaper, BaseViewHolder>(R.layout.tgb_item_record_detail) {


    override fun convert(helper: BaseViewHolder?, item: PlayerRedPaper) {
        helper?.itemView?.run {
            tv_candy_count.setTextColor(Color.parseColor("#CBE4E5"))
            tv_candy_count.text = item.sugar_num.toString()
            if (item.is_send) {
                iv_send.visibility = View.VISIBLE
                tv_candy_count.setTextColor(Color.parseColor("#D8A578"))
            } else {
                iv_send.visibility = View.GONE
            }
            if (item.is_best_luckly && !item.is_send) {
                iv_lucky.visibility = View.VISIBLE
                tv_candy_count.setTextColor(Color.parseColor("#FFE71A"))
            } else
                iv_lucky.visibility = View.GONE

            if (!item.head_picture.isBlank()) {
                Glide.with(context).load(item.head_picture).into(iv_head)
            } else {
                Log.e("TGBRecordDetailAdapter", "head_picture为空")
                iv_head.setImageResource(R.drawable.tgb_pic_head)
            }
        }
    }
}