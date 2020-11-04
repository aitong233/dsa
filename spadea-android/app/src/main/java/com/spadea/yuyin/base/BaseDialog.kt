package com.spadea.yuyin.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.spadea.yuyin.R

/**
 * Copyright (c) 1
 *
 * @Description
 * @Author         1
 * @Copyright      Copyright (c) 1
 * @Date           $date$ $time$
 */
abstract class BaseDialog(mContext: Context?, res: Int) : Dialog(mContext, res) {

    constructor(mContext: Context) : this(mContext, R.style.custom_dialog)

    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        initView()
    }

    private fun initView() {
        initLogic()
        setListener()
        processLogic()
    }


    protected abstract fun initLogic()
    protected abstract fun setListener()
    protected abstract fun processLogic()
}