package com.spadea.yuyin.ui.fragment2.setting.nameverify

import android.text.TextUtils
import com.spadea.yuyin.MyApplication
import com.spadea.yuyin.net.Net
import com.spadea.yuyin.net.UrlUtils
import com.spadea.yuyin.util.utilcode.ToastUtils

class NameVerifyPresent : NameVerifyContract.Present() {
    override fun realNameAuthentication(identity_number: String, real_name: String) {
        if (TextUtils.isEmpty(real_name)) {
            ToastUtils.showShort("请输入真实姓名")
            return
        }
        if (TextUtils.isEmpty(identity_number)) {
            ToastUtils.showShort("请输入身份证号")
            return
        }
        Net.post(mContext, UrlUtils().realNameAuthentication, mapOf("identity_number" to identity_number, "real_name" to real_name), object
            : Net.Callback(mContext, true) {
            override fun onSuccess(t: Any?) {
                var user = MyApplication.getInstance().user
                user.real_name = real_name
                user.identity_number = identity_number
                MyApplication.getInstance().user = user
                mView?.realNameAuthentication()
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