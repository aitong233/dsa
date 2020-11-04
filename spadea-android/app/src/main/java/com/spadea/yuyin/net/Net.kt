package com.spadea.yuyin.net

import android.content.Context
import android.text.TextUtils
import com.spadea.yuyin.base.BaseBean
import com.spadea.yuyin.widget.MyProgressDialog
import com.spadea.yuyin.MyApplication
import com.spadea.yuyin.R
import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpParams
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import java.io.File

/**
 * Copyright (c) 1
 *
 * @Description
 * @Author         1
 * @Copyright      Copyright (c) 1
 * @Date           $date$ $time$
 */
object Net {
    fun post(activity: Context?, url: String, map: Map<String, Any?>, netCallback: Callback) {
        var params = HttpParams()
        if (!TextUtils.isEmpty(MyApplication.getInstance().token)) {
            params.put("token", MyApplication.getInstance().token)
        }
        for ((k, v) in map) {
            if (v is File) {
                params.put(k, v)
            } else {
                params.put(k, v.toString())
            }
        }
        OkGo.post<BaseBean<Any>>(url).tag(activity).params(params).execute(object : OkGoCallback<BaseBean<Any>>() {
            override fun onSuccess(response: Response<BaseBean<Any>>) {
                super.onSuccess(response)
                netCallback.onSuccess(response?.body()?.data)
                netCallback.onComplete()
                if (response?.body()?.status ?: 1 == 1)
                    netCallback.onMessage(1, response?.body()?.info ?: "")
            }

            override fun onStart(request: Request<BaseBean<Any>, out Request<*, *>>?) {
                super.onStart(request)
                netCallback.onStart()
            }

            override fun onFinish() {
                super.onFinish()
            }

            override fun onError(response: Response<BaseBean<Any>>) {
                super.onError(response)
                netCallback.onError(response.exception)
                if (response.exception is ApiException) {
                    when ((response.exception as ApiException).code) {
                        -1 -> {
                            MyApplication.getInstance().reLogin()
                        }
                        else -> {
                            netCallback.onComplete()
                        }
                    }
                } else {
                    netCallback.onComplete()
                }
            }
        })
    }

    abstract class Callback(context: Context?, var isShow: Boolean = false, tag: String = context?.getString(R.string.connecting) ?: "") {
        constructor() : this(null, false, "")
        constructor(context: Context?, isShow: Boolean) : this(context, isShow, context?.getString(R.string.connecting) ?: "")

        lateinit var mDialog: MyProgressDialog

        init {
            if (isShow) {
                mDialog = MyProgressDialog(context)
                mDialog?.setCanceledOnTouchOutside(false)
            }
        }

        open fun onStart() {
            try {
                if (isShow && !mDialog?.isShowing)
                    mDialog?.show()
            } catch(e: Exception) {
            }
        }

        open fun onComplete() {
            try {
                if (isShow && mDialog?.isShowing)
                    mDialog?.dismiss()
            } catch(e: Exception) {
            }
        }

        open fun onMessage(status: Int, msg: String) {

        }

        abstract fun onSuccess(t: Any?)
        abstract fun onError(apiException: Throwable?)

    }
}