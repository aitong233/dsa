package com.yutang.game.tangguobao.ui

import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.base.BaseDialogFragment
import com.yutang.game.tangguobao.utils.SizeUtils
import kotlinx.android.synthetic.main.tgb_fragment_in_game.*

class TGBInGameFragment : BaseDialogFragment() {

    override fun getLayoutId(): Int = R.layout.tgb_fragment_in_game

    override fun initView() {
    }

    override fun initListener() {
        iv_confirm.setOnClickListener { dismiss() }

    }

    override fun initData() {
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(
                SizeUtils.dp2px(context!!, 289f), //动态设置宽高，必须设置，否则显示效果不一致
                SizeUtils.dp2px(context!!, 166f)
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = TGBInGameFragment()
    }
}