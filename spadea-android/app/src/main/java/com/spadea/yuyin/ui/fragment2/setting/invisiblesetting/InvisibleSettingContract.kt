package com.spadea.yuyin.ui.fragment2.setting.invisiblesetting

import com.spadea.yuyin.base.BasePresent
import com.spadea.yuyin.base.BaseView

class InvisibleSettingContract {
    interface View : BaseView {
        fun userSetCloaking(visit: Int, online: Int, chat: Int)
    }

    abstract class Present : BasePresent<View>() {
        abstract fun userSetCloaking(visit: Int, online: Int, chat: Int)
    }
}