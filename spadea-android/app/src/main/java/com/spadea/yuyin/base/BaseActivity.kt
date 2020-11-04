package com.spadea.yuyin.base

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager

import com.spadea.yuyin.widget.MyProgressDialog
import org.jetbrains.anko.inputMethodManager

/**
 * Copyright (c) 1
 *
 * @Description
 * @Author         1
 * @Copyright      Copyright (c) 1
 * @Date           $date$ $time$
 */
abstract class BaseActivity<out P : BasePresent<*>> : AppCompatActivity() {

    protected val TAG = javaClass.simpleName
    protected var dialog: MyProgressDialog? = null
    protected lateinit var mContext: Context
    abstract val mPresenter: P
    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(layoutRes)
        mContext = this
        initView()
    }

//    override fun onResume() {
//        super.onResume()
//        if (MyApplication.getInstance().isShow) {
//            startActivity<RoomActivity>()
//        }
//    }

    private fun initView() {
        initAll()
        setListener()
        processLogic()
    }

    protected abstract fun initAll()
    protected abstract fun setListener()
    protected abstract fun processLogic()

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

    protected fun hideSoftKeyboard(view: View) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    protected fun showSoftKeyboard(view: View) {
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    protected fun initDialog() {
        dialog = MyProgressDialog(mContext)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCanceledOnTouchOutside(false)
    }

    protected fun showDialog() {
        if (dialog != null && !(dialog?.isShowing ?: false))
            dialog?.show()
    }

    protected fun hideDialog() {
        if (dialog != null && dialog?.isShowing ?: false)
            dialog?.dismiss()
    }




}