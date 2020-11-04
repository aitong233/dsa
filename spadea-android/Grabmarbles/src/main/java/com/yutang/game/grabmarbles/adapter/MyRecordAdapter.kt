package com.yutang.game.grabmarbles.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yutang.game.grabmarbles.R
import com.yutang.game.grabmarbles.bean.QTGRecord
import com.yutang.game.grabmarbles.utils.SizeUtils
import kotlinx.android.synthetic.main.qdz_item_my_record.view.*

internal class MyRecordAdapter :
    BaseQuickAdapter<QTGRecord, BaseViewHolder>(R.layout.qdz_item_my_record) {

    override fun convert(helper: BaseViewHolder, item: QTGRecord) {
        helper.itemView.run {
            val recordGridAdapter = RecordGridAdapter(mContext, item.gift.picture)
            recordGridAdapter.setNewData(item.user_list)
            tv_title.text = "第 ${item.serial} 期"
            gridView.adapter = recordGridAdapter
            val lines = item.user_list.size / 2
            gridView.layoutParams.apply {
                height = SizeUtils.dp2px(context, 50f * lines)
            }
        }
    }

}