package com.spadea.yuyin.ui.fragment2.setting.messagesetting

import com.spadea.yuyin.net.Net
import com.spadea.yuyin.net.UrlUtils
import com.spadea.yuyin.util.utilcode.ToastUtils

class MessageSettingPresent : MessageSettingContract.Present() {
    override fun userSetInform(broadcast: Int, fans: Int) {
        Net.post(mContext, UrlUtils().userSetInform, mapOf("broadcast" to broadcast, "fans" to fans), object
            : Net.Callback(mContext, true) {
            override fun onSuccess(t: Any?) {
                mView?.userSetInform(broadcast, fans)
            }

            override fun onError(apiException: Throwable?) {
                mView?.onError()
            }

            override fun onMessage(status: Int, msg: String) {
                super.onMessage(status, msg)
                ToastUtils.showShort(msg)
            }
        })
    }
}