package com.yutang.game.grabmarbles.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.internal.`$Gson$Types`.newParameterizedTypeWithOwner
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.yutang.game.grabmarbles.GrabMarblesManager
import com.yutang.game.grabmarbles.bean.ResponseData
import java.lang.reflect.Type


internal object OKGoRequest {
    //内联函数
    inline fun <reified T> getRequest(url: String, callback: CallBack<T>) {
        OkGo.get<String>(url).params("token", GrabMarblesManager.getToken())
            .execute(object : StringCallback() {
                override fun onSuccess(response: Response<String>) {
                    try {
                        val type: Type = newParameterizedTypeWithOwner( //反射出ResponseData<T>的Type
                            null,
                            ResponseData::class.java,
                            T::class.java
                        )
                        val gson = Gson()
                        val responseData =
                            gson.fromJson<ResponseData<T>>(
                                response.body(),
                                type
                            )
                        if (responseData.status == 1) {
                            responseData.data?.let {
                                val toJson = gson.toJson(it)
                                Log.e("tojson", toJson)
                                callback.onSuccess(it)
                            }
                        } else {
                            responseData.info?.let {
                                ToastUtils.showToast(it)
                            }
                            callback.onError()
                        }
                    } catch (e: Exception) {
                        Log.e("网络请求", "json解析错误")
                        e.printStackTrace()
                        callback.onError()
                    }
                }

                override fun onError(response: Response<String>) {
                    callback.onError()
                    try {
                        val message = response.message()
                        if (message != null)
                            Log.e("网络请求", message)
                        else
                            response.exception.printStackTrace()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }

    interface CallBack<T> {
        fun onSuccess(data: T)

        fun onError()
    }
}