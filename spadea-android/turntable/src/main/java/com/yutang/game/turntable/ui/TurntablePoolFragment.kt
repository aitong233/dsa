package com.yutang.game.turntable.ui

import com.yutang.game.turntable.R
import com.yutang.game.turntable.adapter.PoolAdapter
import com.yutang.game.turntable.base.BaseDialogFragment
import com.yutang.game.turntable.bean.GamePoolModel
import com.yutang.game.turntable.contacts.TurntablePoolContacts
import com.yutang.game.turntable.presenter.TurntablePoolPresenter
import com.yutang.game.turntable.utils.SizeUtils
import kotlinx.android.synthetic.main.turntable_fragment_pool.*

class TurntablePoolFragment : BaseDialogFragment(), TurntablePoolContacts.View {

    val adapter = PoolAdapter()

    lateinit var presenter: TurntablePoolPresenter

    override fun initPresenter() {
        presenter = TurntablePoolPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.turntable_fragment_pool
    }

    override fun initView() {
        rv.adapter = adapter
    }

    override fun initData() {
        presenter.getGamePool()
    }


    override fun onPoolSuccess(t: ArrayList<GamePoolModel>) {
        adapter.setNewData(t)
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(SizeUtils.dp2px(resources, 337f),
                SizeUtils.dp2px(resources, 506f))//动态设置宽高，必须设置，否则显示效果不一致
    }

    companion object {
        fun newInstance() = TurntablePoolFragment()
    }
}