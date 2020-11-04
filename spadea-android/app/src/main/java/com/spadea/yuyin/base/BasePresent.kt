package com.spadea.yuyin.base

import android.content.Context
import com.lzy.okgo.OkGo

/**
 * Copyright (c) 1
 *
 * @Description
 * @Author         1
 * @Copyright      Copyright (c) 1
 * @Date           $date$ $time$
 */
abstract class BasePresent<V : BaseView> {
    var mView: V? = null
    var mContext: Context? = null
    open fun attachView(view: V) {
        mView = view
        mContext = mView?.getContext()
    }

    open fun detachView() {
        OkGo.getInstance().cancelTag(mView)
        mView = null
    }
}