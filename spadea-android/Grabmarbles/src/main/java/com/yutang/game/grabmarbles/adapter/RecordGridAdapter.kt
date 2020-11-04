package com.yutang.game.grabmarbles.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.yutang.game.grabmarbles.GrabMarblesManager
import com.yutang.game.grabmarbles.R
import com.yutang.game.grabmarbles.bean.User2
import kotlinx.android.synthetic.main.qdz_item_record_grid.view.*


internal class RecordGridAdapter(val context: Context, val giftPicture: String) : BaseAdapter() {

    private var data = arrayListOf<User2>()

    private val userId = GrabMarblesManager.getUserId()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.qdz_item_record_grid, parent, false)
        val item = getItem(position)
        if (item.head_picture.isNotEmpty())
            Glide.with(context).load(item.head_picture).into(view.iv_head)
        Glide.with(context).load(giftPicture).into(view.iv_candy)
        view.tv_nickname.text = item.nickname
        val isContinue = item.is_continue.toBoolean()
        if (isContinue)
            view.iv_jielong.visibility = View.VISIBLE
        else
            view.iv_jielong.visibility = View.GONE
        view.tv_candy_count.text = "x" + item.reward
        if (item.user_id == userId)
            view.tv_nickname.setTextColor(Color.parseColor("#FFAB60E0"))
        else
            view.tv_nickname.setTextColor(Color.parseColor("#FFB603BD"))
        return view
    }

    override fun getItem(position: Int): User2 {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getCount(): Int {
        return data.size
    }


    fun addData(t: User2) {
        data.add(t)
        notifyDataSetChanged()
    }

    fun setNewData(newData: List<User2>) {
        data = newData as ArrayList<User2>
        notifyDataSetChanged()
    }
}