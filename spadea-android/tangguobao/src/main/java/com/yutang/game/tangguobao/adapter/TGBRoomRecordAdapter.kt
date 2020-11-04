package com.yutang.game.tangguobao.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.bean.RoomGameRound
import kotlinx.android.synthetic.main.tgb_item_round_record.view.*

class TGBRoomRecordAdapter : BaseQuickAdapter<RoomGameRound, BaseViewHolder>(R.layout.tgb_item_round_record) {

    @SuppressLint("SetTextI18n")
    override fun convert(helper: BaseViewHolder, item: RoomGameRound) {
        helper.itemView.run {
            val recordDetailAdapter: TGBRecordDetailAdapter = if (rv.adapter == null) {
                val tgbRecordDetailAdapter = TGBRecordDetailAdapter()
                rv.layoutManager = GridLayoutManager(context,5)
                rv.adapter = tgbRecordDetailAdapter
                tgbRecordDetailAdapter
            } else {
                rv.adapter as TGBRecordDetailAdapter
            }
            tv_round.text = "第${item.round_id}局"
            recordDetailAdapter.setNewData(item.player_ifs)
        }

    }
}