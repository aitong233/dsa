package com.spadea.yuyin.ui.fragment2.setting.messagesetting

import com.spadea.yuyin.base.BasePresent
import com.spadea.yuyin.base.BaseView

class MessageSettingContract {
    interface View : BaseView {
        fun userSetInform(broadcast: Int, fans: Int)
    }

    abstract class Present : BasePresent<View>() {
        abstract fun userSetInform(broadcast: Int, fans: Int)
    }
}