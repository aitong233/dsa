package com.yutang.game.tangguobao.ui

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.adapter.TGBResultAdapter
import com.yutang.game.tangguobao.base.BaseDialogFragment
import com.yutang.game.tangguobao.bean.UserResultList
import com.yutang.game.tangguobao.utils.SizeUtils
import kotlinx.android.synthetic.main.tgb_fragment_result.*

class TGBResultFragment : BaseDialogFragment() {

    val adapter = TGBResultAdapter()

    override fun getLayoutId(): Int {
        return R.layout.tgb_fragment_result
    }

    override fun initView() {
        recyclerview.layoutManager = GridLayoutManager(context!!, 2)
        recyclerview.adapter = adapter
    }

    override fun initListener() {
        iv_close.setOnClickListener {
            dismiss()
        }
    }

    override fun initData() {
        super.initData()
        val userResultList = arguments!!.getSerializable("userResults") as UserResultList
        adapter.setNewData(userResultList.user_results)
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(
                SizeUtils.dp2px(context!!, 309f), //动态设置宽高，必须设置，否则显示效果不一致
                SizeUtils.dp2px(context!!, 330f)
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(userResults: UserResultList) = TGBResultFragment().also {
            val bundle = Bundle()
            bundle.putSerializable("userResults", userResults)
            it.arguments = bundle
        }
    }
}