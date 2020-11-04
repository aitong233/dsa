package com.yutang.game.turntable.ui

import android.support.v7.widget.LinearLayoutManager
import com.yutang.game.turntable.R
import com.yutang.game.turntable.adapter.RecordAdapter
import com.yutang.game.turntable.base.BaseDialogFragment
import com.yutang.game.turntable.bean.GameLogModel
import com.yutang.game.turntable.contacts.TurntableRecordContacts
import com.yutang.game.turntable.presenter.TurntableRecordPresenter
import com.yutang.game.turntable.presenter.TurntableResultPresenter
import com.yutang.game.turntable.utils.SizeUtils
import kotlinx.android.synthetic.main.turntable_fragment_record.*

class TurntableRecordFragment : BaseDialogFragment(), TurntableRecordContacts.View {

    val adapter = RecordAdapter()

    lateinit var presenter: TurntableRecordPresenter

    override fun initPresenter() {
        presenter = TurntableRecordPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.turntable_fragment_record
    }

    override fun initView() {
        presenter.getGameLog()
        rv_record.layoutManager = LinearLayoutManager(context)
        rv_record.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(
                SizeUtils.dp2px(resources, 337f), //动态设置宽高，必须设置，否则显示效果不一致
                SizeUtils.dp2px(resources, 506f)
        )
    }

    companion object {
        fun newInstance() = TurntableRecordFragment()
    }

    override fun onGetLogSuccess(list: List<GameLogModel>) {
        adapter.setNewData(list)
    }
}