package com.yutang.game.tangguobao.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.PopupWindow
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.adapter.TGBPlayerCountAdapter
import com.yutang.game.tangguobao.divider.PlayerCountListItemDecoration

class PlayerCountPopupWindow(context: Context, minPeople: Int, maxPeople: Int, itemClickListener: ItemClickListener) : PopupWindow() {
    val adapter = TGBPlayerCountAdapter()
    var playerCounts = ArrayList<Int>()
    var checkedPosition: Int = 0

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.tgb_popupwindow_player_count, null, false)
        val recyclerView = view as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(PlayerCountListItemDecoration(context))
        recyclerView.adapter = adapter
        for (i in minPeople..maxPeople) {
            playerCounts.add(i)
        }
        adapter.setNewData(playerCounts)
        adapter.setOnItemClickListener { adapter, view, position ->
            checkedPosition = position
            itemClickListener.onClick(playerCounts[position].toString() + "人")
        }
        contentView = view
    }

    fun getPeopleNum(): Int? {
        return playerCounts[checkedPosition]
    }
}

interface ItemClickListener {
    fun onClick(s: String)
}