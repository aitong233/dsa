package com.spadea.yuyin.ui.fragment2.setting.feedback

import android.content.Context
import com.spadea.yuyin.R
import com.spadea.yuyin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_feed_back.*
import kotlinx.android.synthetic.main.layout_topbar.*

class FeedBackActivity : BaseActivity<FeedBackContract.Present>(), FeedBackContract.View {

    override val mPresenter: FeedBackContract.Present
        get() = FeedBackPresent().also { it.attachView(this) }
    override val layoutRes: Int
        get() = R.layout.activity_feed_back

    override fun initAll() {
        tv_title.text = "意见反馈"
    }

    override fun setListener() {
        iv_left.setOnClickListener { onBackPressed() }
        btn_submit.setOnClickListener {
            mPresenter?.feedback(et.text.toString())
        }
    }

    override fun processLogic() {

    }

    override fun getContext(): Context {
        return mContext
    }

    override fun onEmpty() {

    }

    override fun onError() {

    }

    override fun feedback() {
        finish()
    }
}
