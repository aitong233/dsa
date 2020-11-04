package com.yutang.game.tangguobao.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.blankj.utilcode.util.LogUtils
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.base.BaseDialogFragment
import com.yutang.game.tangguobao.bean.*
import com.yutang.game.tangguobao.utils.SizeUtils
import com.yutang.game.tangguobao.utils.ToastUtils
import kotlinx.android.synthetic.main.tgb_fragment_room_info.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TGBRoomInfoFragment : BaseDialogFragment() {

    var roomInfo: RoomInfo? = null

    override fun getLayoutId(): Int {
        return R.layout.tgb_fragment_room_info
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        roomInfo = arguments?.getSerializable("roomInfo") as RoomInfo
        roomInfo?.run {
            tv_player_count.text = "${people_had_num}/${people_num}"
            tv_play_times.text = "${round_num}局"
            tv_money.text = "$sugar_num"
            tv_ruler.text = if (rule == 1) "最低接龙" else "最高接龙"
            tv_bond.text = "${sugar_num * round_num}"
        }
    }

    private var lastClickTime = 0L

    private var currentClickTime = 0L
    override fun initListener() {
        iv_cancel.setOnClickListener { dismiss() }
        iv_enter.setOnClickListener {
            if (currentClickTime != 0L) {   //防止连续点击
                lastClickTime = currentClickTime
                currentClickTime = System.currentTimeMillis()
                if (currentClickTime - lastClickTime < 500L) {
                    LogUtils.e("点击间隔" + (currentClickTime - lastClickTime))
                    return@setOnClickListener
                }
            } else {
                currentClickTime = System.currentTimeMillis()
            }
            EventBus.getDefault().post(JoinRoomEvent(roomInfo!!.room_id))   //加入房间
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(joinRoomRsp: JoinRoomRsp) {
        val status = joinRoomRsp.status
        when (status.code) {
            0 -> {
                dismiss()
            }
            6 -> {
                TGBRoomFragment.newInstance(joinRoomRsp.room_id).show(childFragmentManager)
                dismiss()
            }
            else -> {
                ToastUtils.showShortToast(context, status.msg)
                dismiss()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(
                SizeUtils.dp2px(context!!, 289f), //动态设置宽高，必须设置，否则显示效果不一致
                SizeUtils.dp2px(context!!, 255f)
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(roomInfo: RoomInfo) = TGBRoomInfoFragment().also {
            val bundle = Bundle()
            bundle.putSerializable("roomInfo", roomInfo)
            it.arguments = bundle
        }
    }
}