package com.yutang.game.tangguobao.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

internal object SizeUtils {

    fun dp2px(context: Context, value: Float): Int {
        return (TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            context.resources.displayMetrics
        ) + 0.5f).toInt()
    }
    fun sp2px(resources: Resources,spValue: Float): Int {

        return (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                spValue,
                resources.displayMetrics
        ) + 0.5f).toInt()
    }

}