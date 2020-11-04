package com.yutang.game.tangguobao.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.bean.Sugar
import kotlinx.android.synthetic.main.tgb_item_grid_round.view.*

class TGBSugarGridAdapter(var context: Context) : BaseAdapter() {

    var data: List<Sugar> = ArrayList()

    var checkedPosition: Int = 0

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView
                ?: LayoutInflater.from(context).inflate(R.layout.tgb_item_grid_round, parent, false)
        view.tv_round.text = getItem(position).sugar_num.toString()
        if (checkedPosition == position) {
            view.iv_check_status.visibility = View.VISIBLE
        } else {
            view.iv_check_status.visibility = View.GONE
        }
        view.setOnClickListener {
            checkedPosition = position
            notifyDataSetChanged()
            onItemClickListener?.onItemClick(getItem(position).sugar_num)
        }
        return view
    }

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {

        fun onItemClick(sugarNum: Int)
    }

    fun getCheckedItem(): Sugar{
        return data[checkedPosition]
    }

    override fun getItem(position: Int): Sugar {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return data[position].sugar_id.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

    fun getCheckedId(): Int? {
        return if (this.data.isEmpty()) {
            null
        } else
            data[checkedPosition].sugar_id
    }

    fun setNewData(newData: List<Sugar>) {
        data = newData
        notifyDataSetChanged()
    }
}