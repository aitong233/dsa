package com.yutang.game.grabmarbles.ui.fragment

import android.os.Bundle
import com.yutang.game.grabmarbles.base.BaseDialogFragment
import com.yutang.game.grabmarbles.R
import com.yutang.game.grabmarbles.event.JoinGameSuccessEvent
import com.yutang.game.grabmarbles.ui.contacts.JoinGameContacts
import com.yutang.game.grabmarbles.ui.presenter.JoinGamePresenter
import com.yutang.game.grabmarbles.utils.SizeUtils
import kotlinx.android.synthetic.main.qdz_fragment_pay_deposit.*
import org.greenrobot.eventbus.EventBus

internal class PayDepositFragment : BaseDialogFragment(), JoinGameContacts.View {

    val presenter = JoinGamePresenter()

    override fun getLayoutId(): Int {
        return R.layout.qdz_fragment_pay_deposit
    }

    override fun initView() {
        presenter.bindView(this)
        val reward = arguments!!.getInt("reward")
        val giftName = arguments!!.getString("giftName", "弹珠")
        tv_deposit.text = "$reward 金币"
        tv_intro.text.toString().also {
            val replace = it.replace("弹珠", giftName)
            tv_intro.text = replace
        }
    }

    override fun initListener() {
        iv_confirm.setOnClickListener {
            presenter.joinGame()
        }
        iv_cancel.setOnClickListener { dismiss() }
    }

    override fun onSuccess(h5: String) {
        EventBus.getDefault().post(JoinGameSuccessEvent())
        dismiss()
    }

    override fun onError() {
        dismiss()
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(SizeUtils.dp2px(context!!, 297f), SizeUtils.dp2px(context!!, 207f))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    companion object {
        @JvmStatic
        fun newInstance(reward: Int, giftName: String) = PayDepositFragment()
            .apply {
                arguments = Bundle().also {
                    it.putInt("reward", reward)
                    it.putString("giftName", giftName)
                }
            }
    }

}