package com.yutang.game.tangguobao.ui

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.adapter.TGBRoomListAdapter
import com.yutang.game.tangguobao.base.BaseDialogFragment
import com.yutang.game.tangguobao.bean.*
import com.yutang.game.tangguobao.utils.SizeUtils
import com.yutang.game.tangguobao.utils.ToastUtils
import kotlinx.android.synthetic.main.tgb_fragment_room_list.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TGBRoomListFragment : BaseDialogFragment() {

    private val roomListAdapter = TGBRoomListAdapter()

    override fun getLayoutId(): Int {
        return R.layout.tgb_fragment_room_list
    }

    override fun initView() {
        recyclerview.layoutManager =
                GridLayoutManager(context, 3)
        recyclerview.adapter = roomListAdapter
        recyclerview.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.bottom = SizeUtils.dp2px(parent.context, 10f)
            }
        })
        roomListAdapter.emptyView = LayoutInflater.from(context).inflate(R.layout.tgb_layout_noroom, null)
    }

    override fun initListener() {
        roomListAdapter.setOnItemClickListener { adapter, view, position ->
            val item = roomListAdapter.getItem(position)
            TGBRoomInfoFragment.newInstance(item!!).show(childFragmentManager)
        }
        iv_close.setOnClickListener {
            dismiss()
        }
        iv_record.setOnClickListener {
            TGBRecordListFragment.newInstance().show(childFragmentManager)
        }
        iv_ruler.setOnClickListener {
            TGBRulerFragment.newInstance().show(childFragmentManager)
        }
        iv_create.setOnClickListener {
            TGBCreateRoomFragment.newInstance().show(childFragmentManager)
        }

        dialog.setOnKeyListener { dialog, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

    }


    override fun initData() {
        EventBus.getDefault().post(SubscribeBigRoomTopicEvent(true))
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(
                SizeUtils.dp2px(context!!, 338f), //动态设置宽高，必须设置，否则显示效果不一致
                SizeUtils.dp2px(context!!, 397f)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().post(SubscribeBigRoomTopicEvent(false))
    }


    var isInRoom = false

    /**
     * 游戏页面是否显示
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(inRoomEvent: InRoomEvent) {
        isInRoom = inRoomEvent.inRoom
    }

    /**
     * 订阅结果
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(subscribeBigRoomTopicStatusEvent: SubscribeBigRoomTopicStatusEvent) {
        if (subscribeBigRoomTopicStatusEvent.isSuccess) {
            EventBus.getDefault().post(GetRoomListEvent()) //获取房间列表
        } else {
            dismiss()
            ToastUtils.showShortToast(context, "订阅失败")
        }
    }

    /**
     * 获取房间列表
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(getRoomListRsp: GetRoomListRsp) {
        val status = getRoomListRsp.status
        when (status.code) {
            0 -> {
                roomListAdapter.setNewData(getRoomListRsp.room_info)
                if (!isInRoom && getRoomListRsp.room_id != 0) {
                    TGBRoomFragment.newInstance(getRoomListRsp.room_id).show(childFragmentManager)
                }
            }
            else -> {
                ToastUtils.showShortToast(context, status.msg)
            }
        }
    }

    /**
     * 大厅广播
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun broadcastRoomRsp(broadcastRoomRsp: BroadcastRoomRsp) {
        EventBus.getDefault().removeStickyEvent(broadcastRoomRsp)
        when(broadcastRoomRsp.status.code){
            2000,2001,2002,2005,2006 ->{    //创建房间，加入房间，离开房间，房间解散,游戏开始
                EventBus.getDefault().post(GetRoomListEvent()) //获取房间列表
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = TGBRoomListFragment()
    }

}