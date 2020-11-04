package com.yutang.game.grabmarbles.ui.presenter

import com.yutang.game.grabmarbles.bean.QTGRecord
import com.yutang.game.grabmarbles.net.Api
import com.yutang.game.grabmarbles.ui.contacts.RecordContacts
import com.yutang.game.grabmarbles.utils.OKGoRequest

internal class RecordPresenter : RecordContacts.Presenter {

    var view: RecordContacts.View? = null

    fun bindView(view: RecordContacts.View) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    /**
     * 获取纪录列表
     */
    override fun getRecordList() {
        OKGoRequest.getRequest(
            Api.API_RECORD,
            object : OKGoRequest.CallBack<Array<QTGRecord>> {
                override fun onSuccess(data: Array<QTGRecord>) {
                    view?.onRecordSuccess(data)
                }

                override fun onError() {
                    view?.onError()
                }
            })
    }
}