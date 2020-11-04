package com.qpyy.room.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.qpyy.libcommon.bean.RechargeInfoModel
import com.qpyy.room.R
import kotlinx.android.synthetic.main.room_item_recharge.view.*

class RoomRechargeAdapter : BaseQuickAdapter<RechargeInfoModel, BaseViewHolder>(R.layout.room_item_recharge) {

    var selectedPosition = -1

    override fun convert(helper: BaseViewHolder, item: RechargeInfoModel) {
        helper.itemView.run {
            tv_money.text = "￥" + item.money
            tv_coin.text = item.number
            if (selectedPosition == helper.adapterPosition) {
                ll_pay_field.setBackgroundResource(R.drawable.room_bg_recharge_select)
            } else {
                ll_pay_field.setBackgroundResource(R.drawable.room_bg_recharge_normal)
            }
        }
    }
}