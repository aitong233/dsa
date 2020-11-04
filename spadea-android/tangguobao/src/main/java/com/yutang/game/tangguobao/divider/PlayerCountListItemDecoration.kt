package com.yutang.game.tangguobao.divider

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.utils.SizeUtils

class PlayerCountListItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    var mHeight: Int = 0
    var dp4: Int = 0
    var dp91: Int = 0
    val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.tgb_divider_player_count)
    val paint = Paint()

    init {
        mHeight = SizeUtils.dp2px(context, 1f)
        dp4 = SizeUtils.dp2px(context, 4f)
        dp91 = SizeUtils.dp2px(context, 91f)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            if (i == 0) {
                continue
            }
            val childAt = parent.getChildAt(i)
            val rect = Rect(0, 0, bitmap.width, bitmap.height)
            val rect2 = Rect(dp4, childAt.top - mHeight, childAt.right - dp4, childAt.top)
            c.drawBitmap(bitmap, rect, rect2, paint)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val childAdapterPosition = parent.getChildAdapterPosition(view)
        if (childAdapterPosition > 0) {
            outRect.top = mHeight
        }
    }
}