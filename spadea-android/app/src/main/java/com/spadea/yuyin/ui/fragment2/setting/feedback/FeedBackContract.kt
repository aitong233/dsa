package com.spadea.yuyin.ui.fragment2.setting.feedback

import com.spadea.yuyin.base.BasePresent
import com.spadea.yuyin.base.BaseView

class FeedBackContract {
    interface View : BaseView {
        fun feedback()
    }

    abstract class Present : BasePresent<View>() {
        abstract fun feedback(content: String)
    }
}