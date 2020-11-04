package com.yutang.game.turntable.ui

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import com.qpyy.libcommon.bean.TurntableLuckyRank
import com.qpyy.libcommon.bean.TurntableLuckyRankEvent
import com.yutang.game.turntable.R
import com.yutang.game.turntable.adapter.LuckyRankAdapter
import com.yutang.game.turntable.base.BaseDialogFragment
import com.yutang.game.turntable.bean.LuckyModel
import com.yutang.game.turntable.contacts.TurntableLuckyRankContacts
import com.yutang.game.turntable.presenter.TurntableLuckyRankPresenter
import com.yutang.game.turntable.utils.SizeUtils
import kotlinx.android.synthetic.main.turntable_fragment_luckyrank.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import kotlin.collections.ArrayList

class TurntableLuckyRankFragment : BaseDialogFragment(), TurntableLuckyRankContacts.View {

    lateinit var presenter: TurntableLuckyRankPresenter

    val adapter = LuckyRankAdapter()

    override fun initPresenter() {
        presenter = TurntableLuckyRankPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.turntable_fragment_luckyrank
    }

    override fun initView() {
        rv.adapter = adapter
        rv.addItemDecoration(object : RecyclerView.ItemDecoration() {
            val dp1 = SizeUtils.dp2px(resources, 1f)
            val dp9 = SizeUtils.dp2px(resources, 9f)
            val paint = Paint()
            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.turntable_divider)

            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                super.onDraw(c, parent, state)
                val childCount = parent.childCount
                for (i in 0 until childCount) {
                    if (i == childCount - 1) {
                        continue
                    }
                    val childAt = parent.getChildAt(i)
                    val rect = Rect(0, 0, bitmap.width, bitmap.height)
                    val rect2 = Rect(dp9, childAt.bottom, childAt.right - dp9, childAt.bottom + dp1)
                    c.drawBitmap(bitmap, rect, rect2, paint)
                }
            }

            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                val childAdapterPosition = parent.getChildAdapterPosition(view)
                val childCount = parent.childCount
                when (childAdapterPosition) {
                    childCount - 1 -> {
                    }
                    else -> {
                        outRect.bottom = dp1
                    }
                }
            }
        })
    }

    override fun initData() {
        presenter.getLuckyRank()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(SizeUtils.dp2px(resources, 337f),
                SizeUtils.dp2px(resources, 506f))//动态设置宽高，必须设置，否则显示效果不一致
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    companion object {
        fun newInstance() = TurntableLuckyRankFragment()
    }

    override fun onLuckyRankSuccess(t: ArrayList<TurntableLuckyRank>) {
        adapter.setNewData(t)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun receivedLuckyBagEvent(event: TurntableLuckyRankEvent) {
        adapter.addData(0, event.rank_info)
        rv.scrollToPosition(0)
    }
}