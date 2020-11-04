package com.yutang.game.grabmarbles.utils

import android.content.Context
import android.util.TypedValue

internal object SizeUtils {

    fun dp2px(context: Context, value: Float): Int {
        return (TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            context.resources.displayMetrics
        ) + 0.5f).toInt()
    }
}