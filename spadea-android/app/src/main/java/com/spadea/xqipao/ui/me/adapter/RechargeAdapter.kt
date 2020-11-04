package com.spadea.xqipao.ui.me.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.spadea.yuyin.R
import com.qpyy.libcommon.bean.RechargeInfoModel
import kotlinx.android.synthetic.main.item_recharge_info.view.*

class RechargeAdapter : BaseQuickAdapter<RechargeInfoModel, BaseViewHolder>(R.layout.item_recharge_info) {
    override fun convert(helper: BaseViewHolder, item: RechargeInfoModel) {
        helper.itemView.run {
            tv_money.text = "￥" + item.money
            tv_coin.text = "获得${item.number}金币"
        }
    }
}