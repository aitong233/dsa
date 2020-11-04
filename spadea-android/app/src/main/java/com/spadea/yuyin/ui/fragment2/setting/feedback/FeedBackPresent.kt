package com.spadea.yuyin.ui.fragment2.setting.feedback

import com.spadea.yuyin.net.Net
import com.spadea.yuyin.net.UrlUtils
import com.spadea.yuyin.util.utilcode.ToastUtils

class FeedBackPresent : FeedBackContract.Present() {
    override fun feedback(content: String) {
        if (content.isNullOrEmpty()) {
            ToastUtils.showShort("请输入反馈内容")
            return
        }
        Net.post(mContext, UrlUtils().feedback, mapOf("content" to content), object
            : Net.Callback(mContext, true) {
            override fun onSuccess(t: Any?) {
                mView?.feedback()
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