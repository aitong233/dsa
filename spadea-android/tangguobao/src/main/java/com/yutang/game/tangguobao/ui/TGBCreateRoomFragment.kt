package com.yutang.game.tangguobao.ui

import android.annotation.SuppressLint
import android.widget.Toast
import com.blankj.utilcode.util.LogUtils
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.adapter.TGBRoundGridAdapter
import com.yutang.game.tangguobao.adapter.TGBSugarGridAdapter
import com.yutang.game.tangguobao.base.BaseDialogFragment
import com.yutang.game.tangguobao.bean.*
import com.yutang.game.tangguobao.utils.SizeUtils
import com.yutang.game.tangguobao.utils.ToastUtils
import com.yutang.game.tangguobao.widget.ItemClickListener
import com.yutang.game.tangguobao.widget.PlayerCountPopupWindow
import kotlinx.android.synthetic.main.tgb_fragment_create_room.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TGBCreateRoomFragment : BaseDialogFragment() {

    var popupWindow: PlayerCountPopupWindow? = null
    var tgbRoundGridAdapter: TGBRoundGridAdapter? = null
    var tgbSugarGridAdapter: TGBSugarGridAdapter? = null

    override fun getLayoutId(): Int = R.layout.tgb_fragment_create_room

    override fun initView() {}

    override fun initListener() {
        val dp1 = SizeUtils.dp2px(context!!, 1.5f)
        ll_player_count.setOnClickListener {
            popupWindow?.run {
                if (isShowing)
                    return@setOnClickListener
                else
                    popupWindow?.showAsDropDown(it, dp1, 0)
            }
        }
        iv_cancel.setOnClickListener {
            dismiss()
        }
        iv_create.setOnClickListener {
            if (currentClickTime != 0L) {
                lastClickTime = currentClickTime
                currentClickTime = System.currentTimeMillis()
                if (currentClickTime - lastClickTime < 500L) {
                    LogUtils.e("点击间隔" + (currentClickTime - lastClickTime))
                    return@setOnClickListener
                }
            } else {
                currentClickTime = System.currentTimeMillis()
            }
            if (tgbSugarGridAdapter?.getCheckedId() == null) {
                ToastUtils.showShortToast(context, "弹珠袋不存在")
                return@setOnClickListener
            }
            val sugar_id = tgbSugarGridAdapter?.getCheckedId()
            val people_num = popupWindow?.getPeopleNum() ?: return@setOnClickListener
            val round_id = tgbRoundGridAdapter?.getCheckedId() ?: return@setOnClickListener
            val rule = if (rb_low_jielong.isChecked) 1 else 2
            EventBus.getDefault().post(CreateRoomEvent(sugar_id!!, people_num, round_id, rule))
        }
    }

    var lastClickTime = 0L

    var currentClickTime = 0L


    override fun initData() {
        EventBus.getDefault().post(GetRoomConfigEvent())
    }

    fun initPopupWindow(minPeople: Int, maxPeople: Int) {
        val dp99 = SizeUtils.dp2px(context!!, 99f)
        val dp80 = SizeUtils.dp2px(context!!, 80f)
        popupWindow = PlayerCountPopupWindow(context!!, minPeople, maxPeople, object : ItemClickListener {
            override fun onClick(s: String) {
                tv_player.text = s
                popupWindow?.dismiss()
            }
        }).apply {
            isOutsideTouchable = true
            width = dp99
            height = dp80
        }
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(
                SizeUtils.dp2px(context!!, 289f), //动态设置宽高，必须设置，否则显示效果不一致
                SizeUtils.dp2px(context!!, 276f)
        )
    }

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(roomConfRsp: RoomConfRsp) {
        val status = roomConfRsp.status
        when (status.code) {
            0 -> {
                setGridRound(roomConfRsp.rounds)
                setGridSugar(roomConfRsp.sugars)
                rb_money_500.text = (tgbSugarGridAdapter!!.getCheckedItem().sugar_num * tgbRoundGridAdapter!!.getCheckedItem().round_num).toString() + "金币"
                initPopupWindow(roomConfRsp.min_people, roomConfRsp.max_people)
                tv_player.text = roomConfRsp.min_people.toString() + "人"
            }

            else -> {
                ToastUtils.showShortToast(context, status.msg)
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(createRoomRsp: CreateRoomRsp) {
        val status = createRoomRsp.status
        when (status.code) {
            0 -> {
                dismiss()
            }
            6 -> {
                EventBus.getDefault().post(ReconnectEvent(createRoomRsp.room_id))
                ToastUtils.showShortToast(context, status.msg)
                dismiss()
            }
            else -> {
                ToastUtils.showShortToast(context, status.msg)
            }
        }
    }

    private fun setGridSugar(sugars: List<Sugar>) {
        val sugarLines: Int = if (sugars.size % 3 > 0) {
            sugars.size / 3 + 1
        } else {
            sugars.size / 3
        }
        tgbSugarGridAdapter = TGBSugarGridAdapter(gv_sugar.context)
        tgbSugarGridAdapter?.setNewData(sugars)
        gv_sugar.layoutParams.height = SizeUtils.dp2px(gv_sugar.context, 25f * (sugarLines))
        gv_sugar.adapter = tgbSugarGridAdapter
        tgbSugarGridAdapter?.onItemClickListener = object : TGBSugarGridAdapter.OnItemClickListener {
            override fun onItemClick(sugarNum: Int) {
                tgbRoundGridAdapter?.run {
                    val round = data[checkedPosition]
                    rb_money_500.text = (sugarNum * round.round_num).toString() + "气泡币"
                }
            }
        }
    }

    private fun setGridRound(rounds: List<Round>) {
        tgbRoundGridAdapter = TGBRoundGridAdapter(gv_round.context)
        tgbRoundGridAdapter?.setNewData(rounds)
        val roundLines: Int = if (rounds.size % 3 > 0) {
            rounds.size / 3 + 1
        } else {
            rounds.size / 3
        }
        gv_round.layoutParams.height = SizeUtils.dp2px(gv_round.context, 25f * (roundLines))
        gv_round.adapter = tgbRoundGridAdapter
        tgbRoundGridAdapter?.onItemClickListener = object : TGBRoundGridAdapter.OnItemClickListener {
            override fun onItemClick(roundNum: Int) {
                tgbSugarGridAdapter?.run {
                    val sugar = data[checkedPosition]
                    rb_money_500.text = (roundNum * sugar.sugar_num).toString() + "气泡币"
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TGBCreateRoomFragment()
    }
}
