package com.spadea.yuyin.ui.fragment2.setting.mobilebind

import com.spadea.yuyin.base.BasePresent
import com.spadea.yuyin.base.BaseView

class MobileBindContract {
    interface View : BaseView {
        fun smsCode()
        fun bindingMobile()
    }

    abstract class Present : BasePresent<View>() {
        abstract fun smsCode(mobile: String, type: String)
        abstract fun bindingMobile(mobile: String, code: String)
    }
}