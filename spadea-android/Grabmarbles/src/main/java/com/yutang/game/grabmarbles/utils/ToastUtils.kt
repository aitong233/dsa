package com.yutang.game.grabmarbles.utils


import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.yutang.game.grabmarbles.GrabMarblesManager
import com.yutang.game.grabmarbles.R

object ToastUtils {

    fun showToast(string: String) {
        GrabMarblesManager.application?.let {
            val toast = Toast.makeText(it, string, Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.view.setBackgroundColor(Color.GRAY);
            toast.view.setBackgroundResource(R.drawable.qdz_shape_bg_my_record);
            val text: TextView =
                toast.view.findViewById<View>(android.R.id.message) as TextView
            text.run {
                maxEms = 8
                gravity = Gravity.CENTER
                setTextColor(Color.BLACK)
            };
            toast.show()
        }
    }
}