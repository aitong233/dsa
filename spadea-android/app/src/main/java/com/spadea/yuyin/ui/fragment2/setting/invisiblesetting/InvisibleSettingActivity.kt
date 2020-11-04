package com.spadea.yuyin.ui.fragment2.setting.invisiblesetting

import android.content.Context
import com.spadea.yuyin.MyApplication
import com.spadea.yuyin.R
import com.spadea.yuyin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_invisible_setting.*
import kotlinx.android.synthetic.main.layout_topbar.*

class InvisibleSettingActivity : BaseActivity<InvisibleSettingContract.Present>(), InvisibleSettingContract.View {

    override val mPresenter: InvisibleSettingContract.Present
        get() = InvisibleSettingPresent().also { it.attachView(this) }
    override val layoutRes: Int
        get() = R.layout.activity_invisible_setting

    override fun initAll() {
        tv_title.text = "隐身设置"
        setData()
    }

    override fun setListener() {
        iv_left.setOnClickListener {
            onBackPressed()
        }
        sw0.setOnCheckedChangeListener { compoundButton, b ->
            mPresenter?.userSetCloaking(if (b) 1 else 2, if (sw1.isChecked) 1 else 2, if (sw2.isChecked) 1 else 2)
        }
        sw1.setOnCheckedChangeListener { compoundButton, b ->
            mPresenter?.userSetCloaking(if (sw0.isChecked) 1 else 2, if (b) 1 else 2, if (sw2.isChecked) 1 else 2)
        }
        sw2.setOnCheckedChangeListener { compoundButton, b ->
            mPresenter?.userSetCloaking(if (sw0.isChecked) 1 else 2, if (sw1.isChecked) 1 else 2, if (b) 1 else 2)
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

    override fun userSetCloaking(visit: Int, online: Int, chat: Int) {
        var user = MyApplication.getInstance().user
        user.visit = visit
        user.online = online
        user.chat = chat
        MyApplication.getInstance().user = user
    }

    fun setData() {
        sw0.isChecked = MyApplication.getInstance().user.visit == 1
        sw1.isChecked = MyApplication.getInstance().user.online == 1
        sw2.isChecked = MyApplication.getInstance().user.chat == 1
    }
}
