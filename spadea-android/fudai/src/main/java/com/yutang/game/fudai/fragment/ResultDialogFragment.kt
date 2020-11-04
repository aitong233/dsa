package com.yutang.game.fudai.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Gravity
import android.view.View
import com.yutang.game.fudai.R
import com.yutang.game.fudai.adapter.ResultAdapter
import com.yutang.game.fudai.base.BaseDialogFragment
import com.yutang.game.fudai.bean.GetGameResultEvent
import com.yutang.game.fudai.bean.LuckGiftBean
import com.yutang.game.fudai.contacts.GameResultContacts
import com.yutang.game.fudai.presenter.GameResultPresenter
import kotlinx.android.synthetic.main.fudai_fragment_result.*
import kotlinx.android.synthetic.main.fudai_fragment_result.view.*
import org.greenrobot.eventbus.EventBus

@Suppress("UNCHECKED_CAST")
class ResultDialogFragment : BaseDialogFragment<GameResultPresenter>(), GameResultContacts.View {

    val adapter = ResultAdapter()

    companion object {
        fun newInstance(prize_info: ArrayList<LuckGiftBean.PrizeInfoBean>, num: Int, type: Int) = ResultDialogFragment().also {
            it.arguments = Bundle().apply {
                putSerializable("prize_info", prize_info)
                putInt("num", num)
                putInt("type", type)
            }
        }
    }

    override fun startFishingSuccess(num: Int) {
    }

    override fun gameResult(eggGiftModels: LuckGiftBean?, num: Int, type: Int) {
        eggGiftModels?.prize_info?.run {
            adapter.setNewData(this)
            var totalValue = 0
            forEach { prizeInfoBean ->
                val price = prizeInfoBean.count * prizeInfoBean.price
                totalValue += price
            }
            tv_total.text = totalValue.toString()
        }
    }

    override fun disLoadings() {
    }

    override fun getSelfActivity(): FragmentActivity {
        return activity!!
    }

    override fun showLoadings() {
    }

    override fun showLoadings(content: String?) {
    }

    override fun initData() {
    }

    override fun initView(rootView: View?) {
        val window = dialog.window
        window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setGravity(Gravity.CENTER)
        }
        rootView?.run {
            rv_result.layoutManager = GridLayoutManager(context, 3)
            rv_result.adapter = adapter
            arguments?.let {
                val data = it.getSerializable("prize_info")
                val num = it.getInt("num")
                val type = it.getInt("type")
                val newData = data as ArrayList<LuckGiftBean.PrizeInfoBean>
                when (num) {
                    1 -> btn_open.setImageResource(R.drawable.fudai_btn_open_one)
                    10 -> btn_open.setImageResource(R.drawable.fudai_btn_open_ten)
                    100 -> btn_open.setImageResource(R.drawable.fudai_btn_open_one_hundred)
                }
                adapter.setNewData(newData)
                var totalValue = 0
                newData.forEach { prizeInfoBean ->
                    val price = prizeInfoBean.count * prizeInfoBean.price
                    totalValue += price
                }
                tv_total.text = totalValue.toString()
                btn_open.setOnClickListener {
                    dismiss()
                    EventBus.getDefault().post(GetGameResultEvent(num, type))
                }
                btn_confirm.setOnClickListener {
                    dismiss()
                }
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fudai_fragment_result
    }

    override fun bindPresenter(): GameResultPresenter {
        return GameResultPresenter(this, context!!)
    }
}