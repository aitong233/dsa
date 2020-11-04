package com.yutang.game.tangguobao.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast

object ToastUtils {
    fun showShortToast(context: Context?, content: String) {
        if(content.isNotEmpty()){
            val toast = Toast.makeText(context, content, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}
