package com.spadea.yuyin.ui.fragment2.setting.mobilebind

import android.content.Context
import android.os.CountDownTimer
import com.spadea.yuyin.MyApplication
import com.spadea.yuyin.R
import com.spadea.yuyin.base.BaseActivity
import com.spadea.yuyin.util.Constants
import kotlinx.android.synthetic.main.activity_mobile_bind.*
import kotlinx.android.synthetic.main.layout_topbar.*

class MobileBindActivity : BaseActivity<MobileBindContract.Present>(), MobileBindContract.View {

    override val mPresenter: MobileBindContract.Present
        get() = MobileBindPresent().also { it.attachView(this) }
    override val layoutRes: Int
        get() = R.layout.activity_mobile_bind
    var timer: CountDownTimer? = null
    var time: Long = Constants.CODE_TIME

    override fun initAll() {
        tv_title.text = "手机绑定"
        timer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tv_smsCode?.text = millisUntilFinished.div(1000).toString() + "s"
            }

            override fun onFinish() {
                tv_smsCode.text = "获取验证码"
                tv_smsCode.isEnabled = true
            }
        }
    }

    override fun setListener() {
        iv_left.setOnClickListener {
            onBackPressed()
        }
        tv_smsCode.setOnClickListener {
            mPresenter.smsCode(et_mobile.text.toString(), "4")
        }
        btn_submit.setOnClickListener {
            mPresenter?.bindingMobile(et_mobile.text.toString(), et_code.text.toString())
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

    override fun smsCode() {
        tv_smsCode.isEnabled = false
        timer?.start()
    }

    override fun bindingMobile() {
        MyApplication.getInstance().reLogin()
    }
}
