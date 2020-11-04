package com.yutang.game.tangguobao.widget

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.yutang.game.tangguobao.R
import kotlinx.android.synthetic.main.tgb_dialog_message.*
import kotlinx.android.synthetic.main.tgb_dialog_message.view.*

class MsgDialog(context: Context) : Dialog(context, R.style.defaultDialogStyle) {

    var tvTitle: TextView  //标题

    var listener: OnBtnClickListener? = null

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.tgb_dialog_message, null, false)
        setContentView(view)
        (view.parent as View).setBackgroundColor(Color.parseColor("#00000000"))
        view.run {
            tvTitle = tv_msg
            tv_left.setOnClickListener {
                listener?.onCancel()
            }
            tv_right.setOnClickListener {
                listener?.onConfirm()
            }
        }
    }

    fun setTitle(title: String) {
        tv_msg.text = title
    }

    interface OnBtnClickListener {

        fun onConfirm()

        fun onCancel()
    }

}