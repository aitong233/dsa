package com.yutang.game.grabmarbles.widget

import android.content.ComponentCallbacks
import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.webkit.WebView

/**
 * 此webView为了解决适配问题、在使用适配方案的项目中使用webView会density 复原，原因是由于 WebView 初始化的时候会还原 density 的值导致适配失效
 * Created by Administrator on 2018/11/10.
 */
internal class MyWebView : WebView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    )


    override fun setOverScrollMode(mode: Int) {
        super.setOverScrollMode(mode)
    }

    init {
        setCustomDensity(context)
    }

    var sNoncompatDensity: Float = 0f
    var sNoncompatScaledDensity: Float = 0f
    var oldDensityDpi: Int = 0
    val callbacks = object : ComponentCallbacks {
        override fun onLowMemory() {
        }

        override fun onConfigurationChanged(newConfig: Configuration?) {
            if (newConfig != null && newConfig.fontScale > 0) {
                sNoncompatScaledDensity =
                    context.applicationContext.resources.displayMetrics.scaledDensity
            }
        }
    }

    /**
     * 今日头条适配方案
     * 以宽度360dp为设计图尺寸
     */
    fun setCustomDensity(context: Context) {
        val application = context.applicationContext
        val appDisplayMetrics = application.resources.displayMetrics

        if (sNoncompatDensity == 0f) {
            sNoncompatDensity = appDisplayMetrics.density
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity
            oldDensityDpi = appDisplayMetrics.densityDpi
            application.registerComponentCallbacks(callbacks)

            val targetDensity = appDisplayMetrics.widthPixels / 360 //关键代码
            val targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity)
            val targetDensityDpi = 160 * targetDensity

            appDisplayMetrics.density = targetDensity.toFloat()
            appDisplayMetrics.scaledDensity = targetScaledDensity
            appDisplayMetrics.densityDpi = targetDensityDpi

            val fragmentDisplayMetrics = context.resources.displayMetrics
            fragmentDisplayMetrics.density = targetDensity.toFloat()
            fragmentDisplayMetrics.scaledDensity = targetScaledDensity
            fragmentDisplayMetrics.densityDpi = targetDensityDpi
        }
    }

    fun resetCustomDensity() {
        val appDisplayMetrics = context.applicationContext.resources.displayMetrics
        appDisplayMetrics.density = sNoncompatDensity
        appDisplayMetrics.scaledDensity = sNoncompatScaledDensity
        appDisplayMetrics.densityDpi = oldDensityDpi
        val fragmentDisplayMetrics = context.resources.displayMetrics
        fragmentDisplayMetrics.density = sNoncompatDensity
        fragmentDisplayMetrics.scaledDensity = sNoncompatScaledDensity
        fragmentDisplayMetrics.densityDpi = oldDensityDpi
    }

    fun removeCallbacks() {
        context.applicationContext.unregisterComponentCallbacks(callbacks)
    }
}