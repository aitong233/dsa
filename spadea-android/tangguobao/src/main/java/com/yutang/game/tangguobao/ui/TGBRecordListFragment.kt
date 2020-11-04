package com.yutang.game.tangguobao.ui

import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.adapter.TGBRecordListAdapter
import com.yutang.game.tangguobao.base.BaseDialogFragment
import com.yutang.game.tangguobao.bean.GetGameHistoryEvent
import com.yutang.game.tangguobao.bean.GetGameHistoryRsp
import com.yutang.game.tangguobao.utils.SizeUtils
import kotlinx.android.synthetic.main.tgb_fragment_record_list.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TGBRecordListFragment : BaseDialogFragment() {

    val adapter = TGBRecordListAdapter()

    override fun getLayoutId(): Int = R.layout.tgb_fragment_record_list

    override fun initView() {
        val dp10 = SizeUtils.dp2px(context!!, 10f)
        recyclerview.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerview.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.bottom = dp10
            }
        })
        recyclerview.adapter = adapter
    }

    override fun initListener() {
        super.initListener()
        iv_close.setOnClickListener {
            dismiss()
        }
    }

    override fun initData() {
        EventBus.getDefault().post(GetGameHistoryEvent())
    }

    companion object {
        @JvmStatic
        fun newInstance() = TGBRecordListFragment()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun getGameHistoryRsp(getGameHistoryRsp: GetGameHistoryRsp) {
        LogUtils.e("getGameHistoryRsp", GsonUtils.toJson(getGameHistoryRsp))
        if (getGameHistoryRsp.status.code == 0) {
            adapter.setNewData(getGameHistoryRsp.game_logs)
        }
    }

}