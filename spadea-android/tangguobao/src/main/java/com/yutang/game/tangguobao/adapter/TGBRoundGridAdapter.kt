package com.yutang.game.tangguobao.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.bean.Round
import kotlinx.android.synthetic.main.tgb_item_grid_round.view.*

class TGBRoundGridAdapter(var context: Context) : BaseAdapter() {

    var data: List<Round> = ArrayList()

    var checkedPosition: Int = 0


    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView
                ?: LayoutInflater.from(context).inflate(R.layout.tgb_item_grid_round, parent, false)
        view.tv_round.text = "${getItem(position).round_num}局"
        if (checkedPosition == position) {
            view.iv_check_status.visibility = View.VISIBLE
        } else {
            view.iv_check_status.visibility = View.GONE
        }
        view.setOnClickListener {
            checkedPosition = position
            notifyDataSetChanged()
            onItemClickListener?.onItemClick(getItem(position).round_num)
        }
        return view
    }

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {

        fun onItemClick(roundNum: Int)
    }

    override fun getItem(position: Int): Round {
        return data[position]
    }

    fun getCheckedItem(): Round {
        return data[checkedPosition]
    }

    override fun getItemId(position: Int): Long {
        return data[position].round_id.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

    fun getCheckedId(): Int? {
        return if (this.data.isEmpty()) {
            null
        } else
            data[checkedPosition].round_id
    }

    fun setNewData(newData: List<Round>) {
        data = newData
        notifyDataSetChanged()
    }
}