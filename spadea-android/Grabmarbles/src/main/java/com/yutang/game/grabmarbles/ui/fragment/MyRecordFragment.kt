package com.yutang.game.grabmarbles.ui.fragment

import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.yutang.game.grabmarbles.base.BaseDialogFragment
import com.yutang.game.grabmarbles.R
import com.yutang.game.grabmarbles.adapter.MyRecordAdapter
import com.yutang.game.grabmarbles.bean.QTGRecord
import com.yutang.game.grabmarbles.ui.contacts.RecordContacts
import com.yutang.game.grabmarbles.ui.presenter.RecordPresenter
import com.yutang.game.grabmarbles.utils.SizeUtils
import kotlinx.android.synthetic.main.qdz_fragment_my_record.*

internal class MyRecordFragment : BaseDialogFragment(), RecordContacts.View {

    val presenter = RecordPresenter()

    val recordAdapter = MyRecordAdapter()

    override fun getLayoutId(): Int {
        return R.layout.qdz_fragment_my_record
    }

    override fun initView() {
        presenter.bindView(this)
        recyclerview.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = recordAdapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.getItemOffsets(outRect, view, parent, state)
                    val dp6 = SizeUtils.dp2px(context!!, 6f)
                    val childAdapterPosition = parent.getChildAdapterPosition(view)
                    if (childAdapterPosition > 0) {
                        outRect.top = dp6
                    }
                }
            })
        }
        presenter.getRecordList()
    }

    override fun initListener() {

        iv_close.setOnClickListener { dismiss() }

    }

    override fun onRecordSuccess(data: Array<QTGRecord>) {
        val arrayList = ArrayList<QTGRecord>()
        arrayList.addAll(data)
        recordAdapter.setNewData(arrayList)
    }

    override fun onError() {
        dismiss()
    }


    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(
            SizeUtils.dp2px(context!!, 318f),
            SizeUtils.dp2px(context!!, 359f)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MyRecordFragment()
    }


}