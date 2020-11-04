package com.spadea.yuyin.ui.fragment2.setting.invisiblesetting

import com.spadea.yuyin.net.Net
import com.spadea.yuyin.net.UrlUtils
import com.spadea.yuyin.util.utilcode.ToastUtils

class InvisibleSettingPresent : InvisibleSettingContract.Present() {
    override fun userSetCloaking(visit: Int, online: Int, chat: Int) {
        Net.post(mContext, UrlUtils().userSetCloaking, mapOf("visit" to visit, "online" to online, "chat" to chat), object
            : Net.Callback(mContext, true) {
            override fun onSuccess(t: Any?) {
                mView?.userSetCloaking(visit, online, chat)
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