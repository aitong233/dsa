package com.spadea.yuyin.util

import android.widget.TextView
import com.spadea.yuyin.R

class LableUtils {
    fun setlable(s: String?, tv_lable: TextView?) {
        tv_lable?.text = ""
        when (s) {
            "官方" -> {
                tv_lable?.setBackgroundResource(R.drawable.home_icon_dt)
            }
            "男神" -> {
                tv_lable?.setBackgroundResource(R.drawable.icon_lan)
            }
            "女神" -> {
                tv_lable?.setBackgroundResource(R.drawable.icon_fen)
            }
            "电台" -> {
                tv_lable?.setBackgroundResource(R.drawable.icon_qing)
            }
            "娱乐" -> {
                tv_lable?.setBackgroundResource(R.drawable.icon_zi)
            }
            "交友" -> {
                tv_lable?.setBackgroundResource(R.drawable.icon_hong)
            }
            "点唱" -> {
                tv_lable?.setBackgroundResource(R.drawable.icon_cheng)
            }
            "相亲" -> {
                tv_lable?.setBackgroundResource(R.drawable.icon_qin)
            }
            "派单" -> {
                tv_lable?.setBackgroundResource(R.drawable.icon_lv)
            }
            "聊天" -> {
                tv_lable?.setBackgroundResource(R.drawable.icon_red)
            }
            "开黑" -> {
                tv_lable?.setBackgroundResource(R.drawable.icon_blue)
            }
            "K歌" -> {
                tv_lable?.setBackgroundResource(R.drawable.icon_zise)
            }
            else -> {
                tv_lable?.setBackgroundResource(R.drawable.talk_icon_rad)
                tv_lable?.text = s
            }
        }
    }
}