package com.spadea.yuyin.ui.fragment2.setting.messagesetting

import android.content.Context
import com.spadea.yuyin.MyApplication
import com.spadea.yuyin.R
import com.spadea.yuyin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_message_setting.*
import kotlinx.android.synthetic.main.layout_topbar.*

class MessageSettingActivity : BaseActivity<MessageSettingContract.Present>(), MessageSettingContract.View {

    override val mPresenter: MessageSettingContract.Present
        get() = MessageSettingPresent().also { it.attachView(this) }
    override val layoutRes: Int
        get() = R.layout.activity_message_setting

    override fun initAll() {
        tv_title.text = "消息设置"
        setData()
    }

    override fun setListener() {
        iv_left.setOnClickListener { onBackPressed() }
        sw0.setOnCheckedChangeListener { compoundButton, b ->
            mPresenter?.userSetInform(if (b) 1 else 2, if (sw1.isChecked) 1 else 2)
        }
        sw1.setOnCheckedChangeListener { compoundButton, b ->
            mPresenter?.userSetInform(if (sw0.isChecked) 1 else 2, if (b) 1 else 2)
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
        setData()
    }

    override fun userSetInform(broadcast: Int, fans: Int) {
        var user = MyApplication.getInstance().user
        user.broadcast = broadcast
        user.fans = fans
        MyApplication.getInstance().user = user
    }

    fun setData() {
        sw0.isChecked = MyApplication.getInstance().user.broadcast == 1
        sw1.isChecked = MyApplication.getInstance().user.fans == 1
    }
}
