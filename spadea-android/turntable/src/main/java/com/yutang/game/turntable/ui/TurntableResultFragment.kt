package com.yutang.game.turntable.ui

import android.os.Bundle
import com.yutang.game.turntable.R
import com.yutang.game.turntable.adapter.ResultAdapter
import com.yutang.game.turntable.base.BaseDialogFragment
import com.yutang.game.turntable.bean.GameSmashEvent
import com.yutang.game.turntable.bean.SmashModel
import com.yutang.game.turntable.contacts.TurntableResultContacts
import com.yutang.game.turntable.presenter.TurntableResultPresenter
import kotlinx.android.synthetic.main.turntable_fragment_result.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@Suppress("UNCHECKED_CAST")
class TurntableResultFragment : BaseDialogFragment(), TurntableResultContacts.View {

    lateinit var presenter: TurntableResultPresenter

    var adapter = ResultAdapter()
    var number = 1


    override fun initPresenter() {
        presenter = TurntableResultPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.turntable_fragment_result
    }

    override fun initView() {
        dialog.setCanceledOnTouchOutside(false)
        rv.adapter = adapter
        arguments?.run {
            val result = getSerializable("result") as ArrayList<SmashModel>
            adapter.setNewData(result)
            tv_total_value.text = getInt("totalValue").toString()
            number = getInt("number")
            when (number) {
                1 -> {
                    iv_smash.setBackgroundResource(R.mipmap.turntable_result_btn_zuan_1)
                }
                10 -> {
                    iv_smash.setBackgroundResource(R.mipmap.turntable_result_btn_zuan_10)
                }
                50 -> {
                    iv_smash.setBackgroundResource(R.mipmap.turntable_result_btn_zuan_50)
                }
            }
        }

    }

    override fun initListener() {
        iv_smash.setOnClickListener {
            dismiss()
            EventBus.getDefault().post(GameSmashEvent(number))
        }
        iv_confirm.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        fun newInstance(t: ArrayList<SmashModel>, number: Int, totalValue: Int) = TurntableResultFragment().also {
            it.arguments = Bundle().apply {
                putSerializable("result", t)
                putInt("totalValue", totalValue)
                putInt("number", number)
            }
        }
    }
}