package com.yutang.game.fudai.adapter

import android.view.View
import android.widget.ImageView
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.SpanUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.qpyy.libcommon.utils.ImageLoader
import com.yutang.game.fudai.R
import com.yutang.game.fudai.bean.CatFishingModel

class GiftRankAdapter : BaseQuickAdapter<CatFishingModel, BaseViewHolder>(R.layout.item_game_rank) {

    override fun convert(helper: BaseViewHolder, item: CatFishingModel) {
        val no = helper.adapterPosition + 1
        helper.setText(R.id.left, "")
        if (no == 1) {
            helper.setBackgroundRes(R.id.left, R.mipmap.icon_no1)
        } else if (no == 2) {
            helper.setBackgroundRes(R.id.left, R.mipmap.icon_no2)
        } else if (no == 3) {
            helper.setBackgroundRes(R.id.left, R.mipmap.icon_no3)
        } else {
            helper.setBackgroundRes(R.id.left, 0)
            helper.setText(R.id.left, no.toString())
        }
        helper.setText(R.id.name, item.nickname)
        ImageLoader.loadHead(mContext, helper.getView<View>(R.id.icon) as ImageView, item.head_picture)
        //helper.setText(R.id.right, new SpanUtils().append("总获得").setForegroundColor(Color.parseColor("#1A1A1A")).appendSpace(ConvertUtils.dp2px(4)).append(item.getNumber()).create());
        helper.setText(R.id.right, SpanUtils().appendSpace(ConvertUtils.dp2px(4f)).append(item.pay_back + "").create())
    }
}