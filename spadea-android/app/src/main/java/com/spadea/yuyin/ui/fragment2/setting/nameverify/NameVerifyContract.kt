package com.spadea.yuyin.ui.fragment2.setting.nameverify

import com.spadea.yuyin.base.BasePresent
import com.spadea.yuyin.base.BaseView

class NameVerifyContract {
    interface View : BaseView {
        fun realNameAuthentication()
    }

    abstract class Present : BasePresent<View>() {
        abstract fun realNameAuthentication(identity_number: String, real_name: String)
    }
}