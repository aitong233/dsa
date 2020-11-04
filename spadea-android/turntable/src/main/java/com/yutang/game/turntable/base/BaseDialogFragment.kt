package com.yutang.game.turntable.base

import android.app.Application
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseDialogFragment : DialogFragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setCustomDensity(this)
        removeBg()
        return LayoutInflater.from(context).inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog.setCanceledOnTouchOutside(true)
        initPresenter()
        initView()
        initListener()
        initData()
    }

    abstract fun initPresenter()

    abstract fun getLayoutId(): Int

    abstract fun initView()

    open fun initListener() {

    }

    open fun initData() {

    }

    private fun removeBg() {
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        removeCustom()
    }

    open fun show(fragmentManager: FragmentManager?) {
        fragmentManager?.let {
            show(it, javaClass.name)
        }
    }

    /**
     * 今日头条适配方案
     * 以宽度375dp为设计图尺寸
     */
    var sNoncompatDensity: Float = 0f
    var sNoncompatScaledDensity: Float = 0f
    var oldDensityDpi: Int = 0

    fun setCustomDensity(fragment: Fragment) {
        val appDisplayMetrics = context?.applicationContext?.resources?.displayMetrics
        appDisplayMetrics?.let {
            if (sNoncompatDensity == 0f) {
                sNoncompatDensity = it.density
                sNoncompatScaledDensity = it.scaledDensity
                oldDensityDpi = it.densityDpi

                val targetDensity = it.widthPixels / 375f //关键代码
                val targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity)
                val targetDensityDpi = 160 * targetDensity

                val fragmentDisplayMetrics = fragment.resources.displayMetrics
                fragmentDisplayMetrics.density = targetDensity
                fragmentDisplayMetrics.scaledDensity = targetScaledDensity
                fragmentDisplayMetrics.densityDpi = targetDensityDpi.toInt()
            }
        }
    }

    fun removeCustom() {
        val fragmentDisplayMetrics = resources.displayMetrics
        fragmentDisplayMetrics.density = sNoncompatDensity
        fragmentDisplayMetrics.scaledDensity = sNoncompatScaledDensity
        fragmentDisplayMetrics.densityDpi = oldDensityDpi
    }

}