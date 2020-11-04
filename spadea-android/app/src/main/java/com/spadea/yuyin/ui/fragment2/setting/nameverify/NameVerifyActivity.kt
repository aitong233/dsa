package com.spadea.yuyin.ui.fragment2.setting.nameverify

import android.content.Context
import android.view.View
import com.spadea.yuyin.MyApplication
import com.spadea.yuyin.R
import com.spadea.yuyin.base.BaseActivity
import com.spadea.yuyin.util.utilcode.StringUtils
import kotlinx.android.synthetic.main.activity_name_verify.*
import kotlinx.android.synthetic.main.layout_topbar.*

class NameVerifyActivity : BaseActivity<NameVerifyContract.Present>(), NameVerifyContract.View {

    override val mPresenter: NameVerifyContract.Present
        get() = NameVerifyPresent().also { it.attachView(this) }
    override val layoutRes: Int
        get() = R.layout.activity_name_verify

    override fun initAll() {
        tv_title.text = "实名认证"
        if (!MyApplication.getInstance().user.identity_number.isNullOrEmpty()) {
            tv_hint.visibility = View.GONE
            et_name.isEnabled = false
            et_card.isEnabled = false
            et_name.setText(StringUtils.userNameReplaceWithStar(MyApplication.getInstance().user.real_name))
            et_card.setText(StringUtils.idCardReplaceWithStar(MyApplication.getInstance().user.identity_number))
            btn_submit.visibility = View.GONE
        }
    }

    override fun setListener() {
        iv_left.setOnClickListener { onBackPressed() }
        btn_submit.setOnClickListener {
            mPresenter?.realNameAuthentication(et_card.text.toString(), et_name.text.toString())
        }
    }

    override fun processLogic() {

    }

    override fun getContext(): Context {
        return mContext
    }

    override fun onEmpty() {

    }

    override fun onError() {

    }

    override fun realNameAuthentication() {
        finish()
    }
}
