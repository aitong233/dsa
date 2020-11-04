package com.spadea.yuyin.ui.fragment2.setting.mobilebind

import android.text.TextUtils
import com.spadea.yuyin.net.Net
import com.spadea.yuyin.net.UrlUtils
import com.spadea.yuyin.util.utilcode.ToastUtils

class MobileBindPresent : MobileBindContract.Present() {

    override fun bindingMobile(mobile: String, code: String) {
        if (TextUtils.isEmpty(mobile)) {
            ToastUtils.showShort("请输入手机号")
            return
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtils.showShort("请输入验证码")
            return
        }
        Net.post(mContext, UrlUtils().bindingMobile, mapOf("mobile" to mobile, "code" to code), object
            : Net.Callback() {
            override fun onSuccess(t: Any?) {
                mView?.bindingMobile()
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

    override fun smsCode(mobile: String, type: String) {
        if (TextUtils.isEmpty(mobile)) {
            ToastUtils.showShort("请输入手机号")
            return
        }
        Net.post(mContext, UrlUtils().smsCode, mapOf("mobile" to mobile, "type" to type), object
            : Net.Callback() {
            override fun onSuccess(t: Any?) {
                mView?.smsCode()
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