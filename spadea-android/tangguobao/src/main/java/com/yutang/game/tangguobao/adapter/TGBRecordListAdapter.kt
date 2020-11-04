package com.yutang.game.tangguobao.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.bean.GameLog
import com.yutang.game.tangguobao.divider.RecordItemDecoration
import kotlinx.android.synthetic.main.tgb_item_record_list.view.*
import java.text.SimpleDateFormat
import java.util.*

class TGBRecordListAdapter : BaseQuickAdapter<GameLog, BaseViewHolder>(R.layout.tgb_item_record_list) {

    val format = SimpleDateFormat("yyyy.MM.dd  HH:mm", Locale.CHINA)

    @SuppressLint("SetTextI18n")
    override fun convert(helper: BaseViewHolder?, item: GameLog) {
        helper?.itemView?.run {

            tv_time.text = format.format(Date(item.create_at * 1000))

            tv_play_times.text = item.round_num.toString() + "局（" + item.money + "）"

            tv_get_sugar_num.text = "获得弹珠：" + item.get_sugar_num

            tv_send_sugar_num.text = "发送弹珠：" + item.send_sugar_num

            val recordDetailAdapter: TGBRoomRecordAdapter = if (rv_detail.adapter == null) {
                val tgbRecordDetailAdapter = TGBRoomRecordAdapter()
                rv_detail.layoutManager = LinearLayoutManager(context)
                rv_detail.adapter = tgbRecordDetailAdapter
                rv_detail.addItemDecoration(RecordItemDecoration(context))
                tgbRecordDetailAdapter
            } else {
                rv_detail.adapter as TGBRoomRecordAdapter
            }
            recordDetailAdapter.setNewData(item.room_log.room_game_round)

            if (item.showDetail) { //显示状态，点击折叠
                fl_detail.visibility = View.VISIBLE
                iv_expand.rotation = 180f
            } else {
                fl_detail.visibility = View.GONE
                iv_expand.rotation = 0f
            }
            setOnClickListener {
                Log.e("showDetail", item.showDetail.toString() + helper.adapterPosition)
                if (item.showDetail) { //显示状态，点击折叠
                    item.showDetail = false
                    fl_detail.visibility = View.GONE
                    iv_expand.rotation = 0f
                } else {
                    item.showDetail = true
                    fl_detail.visibility = View.VISIBLE
                    iv_expand.rotation = 180f
                }
            }
        }
    }
}