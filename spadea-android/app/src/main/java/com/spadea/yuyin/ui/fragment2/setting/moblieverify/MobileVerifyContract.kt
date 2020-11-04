package com.spadea.yuyin.ui.fragment2.setting.moblieverify

import com.spadea.yuyin.base.BasePresent
import com.spadea.yuyin.base.BaseView

class MobileVerifyContract {
    interface View : BaseView {
        fun smsCode()
        fun checkOldMobile()
    }

    abstract class Present : BasePresent<View>() {
        abstract fun smsCode(mobile: String, type: String)
        abstract fun checkOldMobile(mobile: String, code: String)
    }
}