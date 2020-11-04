package com.yutang.game.grabmarbles.base

import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yutang.game.grabmarbles.GrabMarblesManager
import com.yutang.game.grabmarbles.event.CloseGameEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseDialogFragment : DialogFragment() {

    var application: Application? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        removeBg()
        application?.let {
            setCustomDensity(this, it)
        }
        return LayoutInflater.from(activity).inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().register(this)
        initView()
        initListener()
        initData()
    }

    open fun initData() {

    }

    open fun initListener() {

    }

    private fun removeBg() {
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.application = GrabMarblesManager.application

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()
    }

    fun show(fragmentManager: FragmentManager?) {
        fragmentManager?.let {
            show(it, javaClass.name)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        GrabMarblesManager.application?.unregisterComponentCallbacks(callbacks)
        application?.let { removeCustom(it) }
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(s: String) { /* Do something */
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(s: CloseGameEvent) { /* Do something */
        dismiss()
    }


    /**
     * 今日头条适配方案
     * 以宽度360dp为设计图尺寸
     */
    var sNoncompatDensity: Float = 0f
    var sNoncompatScaledDensity: Float = 0f
    var oldDensityDpi: Int = 0
    val callbacks = object : ComponentCallbacks {
        override fun onLowMemory() {
        }

        override fun onConfigurationChanged(newConfig: Configuration?) {
            if (newConfig != null && newConfig.fontScale > 0) {
                sNoncompatScaledDensity =
                    application?.resources?.displayMetrics?.scaledDensity ?: 0f
            }
        }
    }

    fun setCustomDensity(fragment: Fragment, application: Application) {
        val appDisplayMetrics = application.resources.displayMetrics
        Log.e("今日头条", "setCustomDensity")
        Log.e("今日头条", "density:" + appDisplayMetrics.density)
        Log.e("今日头条", "scaledDensity:" + appDisplayMetrics.scaledDensity)
        Log.e("今日头条", "densityDpi:" + appDisplayMetrics.densityDpi)
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

            val fragmentDisplayMetrics = fragment.resources.displayMetrics
            fragmentDisplayMetrics.density = targetDensity.toFloat()
            fragmentDisplayMetrics.scaledDensity = targetScaledDensity
            fragmentDisplayMetrics.densityDpi = targetDensityDpi

            Log.e("今日头条", "density:" + appDisplayMetrics.density)
            Log.e("今日头条", "scaledDensity:" + appDisplayMetrics.scaledDensity)
            Log.e("今日头条", "densityDpi:" + appDisplayMetrics.densityDpi)
        }
    }

    fun removeCustom(application: Application) {
        Log.e("今日头条", "removeCustom")
        val appDisplayMetrics = application.resources.displayMetrics
        appDisplayMetrics.density = sNoncompatDensity
        appDisplayMetrics.scaledDensity = sNoncompatScaledDensity
        appDisplayMetrics.densityDpi = oldDensityDpi
        val fragmentDisplayMetrics = resources.displayMetrics
        fragmentDisplayMetrics.density = sNoncompatDensity
        fragmentDisplayMetrics.scaledDensity = sNoncompatScaledDensity
        fragmentDisplayMetrics.densityDpi = oldDensityDpi
        Log.e("今日头条", "density:" + appDisplayMetrics.density)
        Log.e("今日头条", "scaledDensity:" + appDisplayMetrics.scaledDensity)
        Log.e("今日头条", "densityDpi:" + appDisplayMetrics.densityDpi)
    }
}