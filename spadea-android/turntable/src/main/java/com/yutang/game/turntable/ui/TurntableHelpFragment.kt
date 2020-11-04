package com.yutang.game.turntable.ui

import android.os.Build
import android.support.annotation.RequiresApi
import android.text.Html
import com.yutang.game.turntable.R
import com.yutang.game.turntable.base.BaseDialogFragment
import com.yutang.game.turntable.bean.GameHelpModel
import com.yutang.game.turntable.contacts.TurntableHelpContacts
import com.yutang.game.turntable.presenter.TurntableHelpPresenter
import com.yutang.game.turntable.utils.SizeUtils
import kotlinx.android.synthetic.main.turntable_fragment_help.*

class TurntableHelpFragment : BaseDialogFragment(), TurntableHelpContacts.View {

    lateinit var presenter: TurntableHelpPresenter

    override fun initPresenter() {
        presenter = TurntableHelpPresenter(this)

    }

    override fun getLayoutId(): Int {
        return R.layout.turntable_fragment_help
    }

    override fun initView() {
        presenter.getGameHelp()
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(
                SizeUtils.dp2px(resources, 337f), //动态设置宽高，必须设置，否则显示效果不一致
                SizeUtils.dp2px(resources, 506f)
        )
    }


    override fun onGetHelpSuccess(t: GameHelpModel) {
        val charSequence = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(t.content, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(t.content)
        }
        tv_help.text = charSequence
    }

    companion object {
        fun newInstance() = TurntableHelpFragment()
    }
}