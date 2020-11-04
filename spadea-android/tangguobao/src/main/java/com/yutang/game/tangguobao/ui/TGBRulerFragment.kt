package com.yutang.game.tangguobao.ui

import android.text.Html
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.base.BaseDialogFragment
import com.yutang.game.tangguobao.bean.GameRuleRsp
import com.yutang.game.tangguobao.bean.GetGameRulerEvent
import com.yutang.game.tangguobao.utils.SizeUtils
import kotlinx.android.synthetic.main.tgb_fragment_ruler.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TGBRulerFragment : BaseDialogFragment() {

    override fun getLayoutId(): Int {
        return R.layout.tgb_fragment_ruler
    }

    override fun initView() {
    }

    override fun initListener() {
        super.initListener()
        iv_close.setOnClickListener { dismiss() }
    }

    override fun initData() {
        EventBus.getDefault().post(GetGameRulerEvent())
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(ruleRsp: GameRuleRsp) {
        tv_context.text = Html.fromHtml(ruleRsp.context)
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(
                SizeUtils.dp2px(context!!, 309f), //动态设置宽高，必须设置，否则显示效果不一致
                SizeUtils.dp2px(context!!, 256f)
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = TGBRulerFragment()
    }
}