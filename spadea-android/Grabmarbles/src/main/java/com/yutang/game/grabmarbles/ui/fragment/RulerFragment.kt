package com.yutang.game.grabmarbles.ui.fragment

import android.text.Html
import com.yutang.game.grabmarbles.R
import com.yutang.game.grabmarbles.base.BaseDialogFragment
import com.yutang.game.grabmarbles.ui.contacts.RulerContacts
import com.yutang.game.grabmarbles.ui.presenter.RulerPresenter
import com.yutang.game.grabmarbles.utils.SizeUtils
import kotlinx.android.synthetic.main.qdz_fragment_ruler.*

internal class RulerFragment : BaseDialogFragment(), RulerContacts.View {

    val presenter = RulerPresenter()

    override fun getLayoutId(): Int {
        return R.layout.qdz_fragment_ruler
    }

    override fun initView() {
        presenter.bindView(this)
        presenter.getRuler()
    }

    override fun initListener() {
        iv_close.setOnClickListener { dismiss() }
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(
            SizeUtils.dp2px(context!!, 309f),
            SizeUtils.dp2px(context!!, 363f)
        )
    }


    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RulerFragment()
    }

    override fun getRulerSuccess(h5: String) {
        tv_ruler.text = Html.fromHtml(h5)
    }
}