package com.spadea.yuyin.ui.fragment0.roomdetail.reprot

import com.spadea.yuyin.net.Net
import com.spadea.yuyin.net.UrlUtils
import com.spadea.yuyin.util.utilcode.ToastUtils
import java.io.File

class ReportPresent : ReportContract.Present() {
    override fun reportUser(user_id: String, remark: String, picture: String) {
        if (remark.isNullOrEmpty()) {
            ToastUtils.showShort("请输入举报理由")
            return
        }
        Net.post(mContext, UrlUtils().reportUser, mapOf("user_id" to user_id, "remark" to remark, "picture" to if (!picture.isNullOrEmpty()) File(picture) else ""), object : Net.Callback(mContext, true) {
            override fun onSuccess(t: Any?) {
                mView?.reportUser()
            }

            override fun onError(apiException: Throwable?) {

            }

            override fun onMessage(status: Int, msg: String) {
                super.onMessage(status, msg)
                ToastUtils.showShort(msg)
            }
        })
    }

    override fun tipOffRoom(id: String, remark: String, picture: String) {
        if (remark.isNullOrEmpty()) {
            ToastUtils.showShort("请输入举报理由")
            return
        }
        Net.post(mContext, UrlUtils().tipOffRoom, mapOf("id" to id, "remark" to remark, "picture" to if (!picture.isNullOrEmpty()) File(picture) else ""), object : Net.Callback(mContext, true) {
            override fun onSuccess(t: Any?) {
                mView?.tipOffRoom()
            }

            override fun onError(apiException: Throwable?) {

            }

            override fun onMessage(status: Int, msg: String) {
                super.onMessage(status, msg)
                ToastUtils.showShort(msg)
            }
        })
    }
}