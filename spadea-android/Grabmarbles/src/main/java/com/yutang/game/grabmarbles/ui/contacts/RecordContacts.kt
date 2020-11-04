package com.yutang.game.grabmarbles.ui.contacts

import com.yutang.game.grabmarbles.bean.QTGRecord

internal interface RecordContacts {

    interface View {

        fun onRecordSuccess(string: Array<QTGRecord>)

        fun onError()

    }

    interface Presenter {
        fun getRecordList()
    }

}