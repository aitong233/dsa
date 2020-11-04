package com.yutang.game.turntable.utils

import android.content.res.Resources
import android.util.TypedValue

object SizeUtils {
    fun dp2px(resources: Resources,value: Float): Int {
        return (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value,
                resources.displayMetrics
        ) + 0.5f).toInt()
    }
}