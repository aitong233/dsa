package com.yutang.game.tangguobao.divider

import android.content.Context
import android.graphics.*
import android.support.v7.widget.RecyclerView
import android.view.View
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.utils.SizeUtils

class RecordItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {
    var dividerStartX: Int = SizeUtils.dp2px(context, 7f)
    var dp3: Int = SizeUtils.dp2px(context, 3f)
    var dp4: Int = SizeUtils.dp2px(context, 4f)
    val dp9: Int = SizeUtils.dp2px(context, 9f)
    val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.tgb_divider_record_round)
    val paint = Paint()

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            if (i == 0) {
                continue
            }
            val childAt = parent.getChildAt(i)
            val rect = Rect(0, 0, bitmap.width, bitmap.height)
            val rect2 = Rect(dividerStartX, childAt.top - dp4, childAt.right - 2 * dividerStartX, childAt.top - dp3)
            c.drawBitmap(bitmap, rect, rect2, paint)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val childAdapterPosition = parent.getChildAdapterPosition(view)
        if (childAdapterPosition == 0) {
            outRect.top = dp9
        }
        if (childAdapterPosition > 0) {
            outRect.top = dp4
        }
    }
}