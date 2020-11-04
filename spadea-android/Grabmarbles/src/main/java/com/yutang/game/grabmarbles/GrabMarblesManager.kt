package com.yutang.game.grabmarbles

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.support.v4.app.FragmentManager
import android.util.Log
import com.yutang.game.grabmarbles.event.CloseGameEvent
import com.yutang.game.grabmarbles.net.Api
import com.yutang.game.grabmarbles.ui.fragment.GrabMarblesFragment
import com.yutang.game.grabmarbles.utils.OKGoRequest
import org.greenrobot.eventbus.EventBus

object GrabMarblesManager {

    private val TAG = "抢弹珠"

    var application: Application? = null

    lateinit var sp: SharedPreferences

    fun init(application: Application, isDebug: Boolean) {
        GrabMarblesManager.application = application
        sp = application.getSharedPreferences("GrabMarbles", Context.MODE_PRIVATE)
        sp.edit().putBoolean("isDebug", isDebug).apply()
    }

    fun setToken(token: String) {
        if (checkInit()) {
            sp.edit().putString("token", token).apply()
        }
    }

    internal fun idDebug(): Boolean {
        return !checkInit() || sp.getBoolean("isDebug", true)
    }

    fun setUserId(userId: String) {
        if (checkInit()) {
            sp.edit().putString("userId", userId).apply()
        }
    }

    fun getUserId(): String {
        if (checkInit()) {
            val userId = sp.getString("userId", "")
            if (userId.isNullOrEmpty())
                Log.e(TAG, "未设置userId")
            return userId!!
        }
        return ""
    }

    /**
     * 显示游戏
     */
    fun showGame(fragmentManager: FragmentManager?) {
        GrabMarblesFragment.newInstance().show(fragmentManager)
    }

    fun getToken(): String {
        if (checkInit()) {
            val token = sp.getString("token", "")
            if (token.isNullOrEmpty())
                Log.e(TAG, "未设置token")
            return token!!
        }
        return ""
    }

    private fun checkInit(): Boolean {
        if (application == null) {
            Log.e(TAG, "未初始化")
            return false
        }
        if (this::sp.isLateinit) {
            sp = application!!.getSharedPreferences("GrabMarbles", Context.MODE_PRIVATE)
        }
        return true
    }

    fun checkGameStatus(callback: OnGameStatusCallback) {
        OKGoRequest.getRequest(
            Api.API_CHECK,
            object : OKGoRequest.CallBack<Boolean> {
                override fun onSuccess(data: Boolean) {
                    callback.onSuccess(data)
                }

                override fun onError() {}
            })
    }

    interface OnGameStatusCallback {
        fun onSuccess(isOpen: Boolean)
    }

    fun closeGame() {
        EventBus.getDefault().post(CloseGameEvent())
    }
}