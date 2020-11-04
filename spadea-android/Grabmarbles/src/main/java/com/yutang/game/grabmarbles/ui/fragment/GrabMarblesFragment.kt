package com.yutang.game.grabmarbles.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.yutang.game.grabmarbles.base.BaseDialogFragment
import com.yutang.game.grabmarbles.GrabMarblesManager
import com.yutang.game.grabmarbles.R
import com.yutang.game.grabmarbles.adapter.ResultAdapter
import com.yutang.game.grabmarbles.bean.*
import com.yutang.game.grabmarbles.event.JoinGameSuccessEvent
import com.yutang.game.grabmarbles.event.QDZMqttEvent
import com.yutang.game.grabmarbles.ui.contacts.RoomContacts
import com.yutang.game.grabmarbles.ui.presenter.RoomPresenter
import com.yutang.game.grabmarbles.utils.SizeUtils
import kotlinx.android.synthetic.main.qdz_fragment_game.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.json.JSONObject

internal class GrabMarblesFragment : BaseDialogFragment(), RoomContacts.View {

    var baseMoney = 0

    var giftName: String = "弹珠"

    var hasJoin = false

    val adapter = ResultAdapter()

    val presenter = RoomPresenter()

    val userId = GrabMarblesManager.getUserId()

    override fun getLayoutId(): Int {
        return R.layout.qdz_fragment_game
    }

    override fun initView() {
        presenter.bindView(this)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = adapter
        recyclerview.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                val dp7 = SizeUtils.dp2px(parent.context, 7f)
                val childAdapterPosition = parent.getChildAdapterPosition(view)
                if (childAdapterPosition != 0) {
                    outRect.top = dp7
                }
            }
        })
    }


    override fun initListener() {
        iv_close.setOnClickListener { dismiss() }
        iv_qiang.setOnClickListener {
            PayDepositFragment.newInstance(baseMoney, giftName).show(childFragmentManager)
        }
        iv_ruler.setOnClickListener { RulerFragment.newInstance().show(childFragmentManager) }
        iv_record.setOnClickListener { MyRecordFragment.newInstance().show(childFragmentManager) }
    }

    override fun initData() {
        super.initData()
        presenter.getRoomDetail()
    }

    @SuppressLint("SetTextI18n")
    override fun getDetailSuccess(roomDetail: RoomDetail) {
        when (roomDetail.status) {
            1 -> {
                roomDetail.run {
                    tv_ruler.text = pay_rule
                    tv_current_player_count.text = "$join_user_count/$user_count_limit"
                    hasJoin = has_join
                    gift.run {
                        tv_candy_count.text = count.toString()
                        cl_info.visibility = View.VISIBLE
                        tv_gift_name.text = name + "数量:"
                        giftName = name
                        if (hasJoin) {
                            tv_wait.visibility = View.VISIBLE
                            iv_qiang.visibility = View.GONE
                        } else {
                            iv_qiang.visibility = View.VISIBLE
                            baseMoney = base_money
                        }
                    }
                }
            }
            2 -> {
                reset()
                if (roomDetail.has_join) {
                    ll_result_join.visibility = View.VISIBLE
                    roomDetail.gift.run {
                        Glide.with(context!!).load(picture).into(iv_gift)
                        roomDetail.user_list.forEach {
                            if (it.user_id.toString() == userId) {
                                tv_candy_result.text = "x${it.reward}"

                            }
                        }
                    }
                } else {
                    tv_result_not_join.visibility = View.VISIBLE
                }
                ll_time.visibility = View.VISIBLE
                fl_detail.visibility = View.VISIBLE
                tv_detail.visibility = View.VISIBLE
                tv_detail.text =
                    "${roomDetail.join_user_count}/${roomDetail.user_count_limit}"
                recyclerview.visibility = View.VISIBLE
                tv_time.text = roomDetail.remain_seconds.toString()
                adapter.gift = roomDetail.gift
                adapter.setNewData(roomDetail.user_list)
            }
            3 -> {
                dismiss()
            }
        }
    }

    override fun onError() {
        dismiss()
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(
            SizeUtils.dp2px(context!!, 318f), //动态设置宽高，必须设置，否则显示效果不一致
            SizeUtils.dp2px(context!!, 396f)
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    fun reset() {
        cl_info.visibility = View.GONE
        tv_wait.visibility = View.GONE
        iv_qiang.visibility = View.GONE
        ll_result_join.visibility = View.GONE
        tv_result_not_join.visibility = View.GONE
        ll_time.visibility = View.GONE
        fl_detail.visibility = View.GONE
        tv_detail.visibility = View.GONE
        recyclerview.visibility = View.GONE
    }

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMqttEvent(event: QDZMqttEvent) {
        EventBus.getDefault().removeStickyEvent(event)
        Log.e("onMqttEvent", event.json)
        val gson = Gson()
        val jsonObject = JSONObject(event.json)
        val type = jsonObject.getInt("type")
        val msg = jsonObject.getString("msg")
        when (type) {
            1 -> {
                reset()
                hasJoin = false
                cl_info.visibility = View.VISIBLE
                iv_qiang.visibility = View.VISIBLE
                val type1 = gson.fromJson(msg, Type1Bean::class.java)
                tv_ruler.text = type1.pay_rule
                tv_current_player_count.text = "${type1.join_user_count}/${type1.user_count_limit}"
                tv_gift_name.text = "${type1.gift.name}数量:"
                giftName = type1.gift.name
                tv_candy_count.text = type1.gift.count.toString()
                baseMoney = type1.base_money
            }
            2 -> {
                val type2 = gson.fromJson(msg, Type2Bean::class.java)
                reset()
                if (hasJoin) {
                    ll_result_join.visibility = View.VISIBLE
                    type2.gift.run {
                        Glide.with(context!!).load(picture).into(iv_gift)
                        type2.user_list.forEach {
                            if (it.user_id.toString() == userId) {
                                tv_candy_result.text = "x${it.reward}"
                            }
                        }

                    }
                } else {
                    tv_result_not_join.visibility = View.VISIBLE
                }
                baseMoney = type2.base_money
                ll_time.visibility = View.VISIBLE
                fl_detail.visibility = View.VISIBLE
                tv_detail.visibility = View.VISIBLE
                tv_detail.text = "${type2.join_user_count}/${type2.user_count_limit}"
                recyclerview.visibility = View.VISIBLE
                tv_time.text = type2.remain_seconds.toString()
                adapter.gift = type2.gift
                adapter.setNewData(type2.user_list)
            }
            3 -> {
                val type3 = gson.fromJson(msg, Type3Bean::class.java)
                tv_current_player_count.text = "${type3.join_user_count}/${type3.user_count_limit}"
            }
            4 -> {
                val type4 = gson.fromJson(msg, Type4Bean::class.java)
                tv_time.text = type4.remain_seconds.toString()
            }
            5 -> {
                dismiss()
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onJoinGameSuccess(joinGameSuccessEvent: JoinGameSuccessEvent) {
        hasJoin = true
        iv_qiang.visibility = View.GONE
        tv_wait.visibility = View.VISIBLE
    }

    companion object {
        @JvmStatic
        fun newInstance() = GrabMarblesFragment()
    }

}