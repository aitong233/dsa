package com.spadea.yuyin.ui.fragment2.setting.moblieverify

import android.content.Context
import android.os.CountDownTimer
import com.spadea.yuyin.MyApplication
import com.spadea.yuyin.R
import com.spadea.yuyin.base.BaseActivity
import com.spadea.yuyin.ui.fragment2.setting.mobilebind.MobileBindActivity
import com.spadea.yuyin.util.Constants
import kotlinx.android.synthetic.main.activity_mobile_verify.*
import kotlinx.android.synthetic.main.layout_topbar.*
import org.jetbrains.anko.startActivity

class MobileVerifyActivity : BaseActivity<MobileVerifyContract.Present>(), MobileVerifyContract.View {

    override val mPresenter: MobileVerifyContract.Present
        get() = MobileVerifyPresent().also { it.attachView(this) }
    override val layoutRes: Int
        get() = R.layout.activity_mobile_verify
    var timer: CountDownTimer? = null
    var time: Long = Constants.CODE_TIME

    override fun initAll() {
        tv_title.text = "手机号验证"
        try {
            tv_hint.text = "更改绑定需要验证当前手机号:${MyApplication.getInstance().user.mobile.replaceRange(3, 7, "****")}，点击获取验证码"
        } catch (e: Exception) {
        }
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
            mPresenter.smsCode(MyApplication.getInstance().user.mobile, "3")
        }
        btn_next.setOnClickListener {
            mPresenter?.checkOldMobile(MyApplication.getInstance().user.mobile, et_code.text.toString())
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

    override fun checkOldMobile() {
        startActivity<MobileBindActivity>()
    }
}
