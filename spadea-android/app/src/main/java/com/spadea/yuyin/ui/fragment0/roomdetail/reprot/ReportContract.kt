package com.spadea.yuyin.ui.fragment0.roomdetail.reprot

import com.spadea.yuyin.base.BasePresent
import com.spadea.yuyin.base.BaseView

class ReportContract {
    interface View : BaseView {
        fun reportUser()
        fun tipOffRoom()
    }

    abstract class Present : BasePresent<View>() {
        abstract fun reportUser(user_id: String, remark: String, picture: String)
        abstract fun tipOffRoom(id: String, remark: String, picture: String)
    }
}