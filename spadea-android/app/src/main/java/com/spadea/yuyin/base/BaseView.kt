package com.spadea.yuyin.base

import android.content.Context

/**
 * Copyright (c) 1
 *
 * @Description
 * @Author         1
 * @Copyright      Copyright (c) 1
 * @Date           $date$ $time$
 */
interface BaseView {
    fun getContext(): Context
    fun onEmpty()
    fun onError()
}