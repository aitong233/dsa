package com.yutang.game.tangguobao.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.adapter.TGBRoomRecordAdapter
import com.yutang.game.tangguobao.base.BaseDialogFragment
import com.yutang.game.tangguobao.bean.GetRoomGameLogEvent
import com.yutang.game.tangguobao.bean.GetRoomGameLogRsp
import com.yutang.game.tangguobao.divider.RecordItemDecoration
import com.yutang.game.tangguobao.utils.SizeUtils
import kotlinx.android.synthetic.main.tgb_fragment_room_record.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TGBRoomRecordFragment : BaseDialogFragment() {

    val adpter = TGBRoomRecordAdapter()

    var roomId: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.tgb_fragment_room_record
    }

    override fun initView() {
        roomId = arguments!!.getInt("roomId")
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = adpter
        recyclerview.addItemDecoration(RecordItemDecoration(activity!!))

    }

    override fun initListener() {
        iv_close.setOnClickListener {
            dismiss()
        }
    }

    override fun initData() {
        EventBus.getDefault().post(GetRoomGameLogEvent(roomId))
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(
                SizeUtils.dp2px(context!!, 306f), //动态设置宽高，必须设置，否则显示效果不一致
                SizeUtils.dp2px(context!!, 267f)
        )
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onGetRoomGameLog(getRoomGameLogRsp: GetRoomGameLogRsp) {
        val roomLog = getRoomGameLogRsp.room_log
        roomLog?.run {
            adpter.setNewData(room_game_round)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(roomId: Int) = TGBRoomRecordFragment().also {
            it.arguments = Bundle().apply { putInt("roomId", roomId) }
        }
    }
}