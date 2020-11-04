package com.yutang.game.turntable.ui

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import com.alibaba.android.arouter.launcher.ARouter
import com.qpyy.libcommon.bean.TurntableLuckyRankEvent
import com.qpyy.libcommon.constant.ARouteConstants
import com.yutang.game.turntable.R
import com.yutang.game.turntable.base.BaseDialogFragment
import com.yutang.game.turntable.bean.GameInfoModel
import com.yutang.game.turntable.bean.GamePoolModel
import com.yutang.game.turntable.bean.GameSmashEvent
import com.yutang.game.turntable.bean.SmashModel
import com.yutang.game.turntable.contacts.TurntableContacts
import com.yutang.game.turntable.presenter.TurntablePresenter
import com.yutang.game.turntable.utils.SizeUtils
import kotlinx.android.synthetic.main.turntable_fragment_luckyrank.*
import kotlinx.android.synthetic.main.turntable_fragment_turntable.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TurntableDialogFragment : BaseDialogFragment(), TurntableContacts.View {

    lateinit var presenter: TurntablePresenter

    override fun initPresenter() {
        presenter = TurntablePresenter(this)
    }


    override fun getLayoutId(): Int {
        return R.layout.turntable_fragment_turntable
    }

    override fun initView() {

    }

    override fun initData() {
        super.initData()
        presenter.getGameInfo()
        presenter.getGamePool()
    }


    override fun initListener() {
        //跳转到手气榜
        iv_lucky_rank.setOnClickListener {
            TurntableLuckyRankFragment.newInstance().show(childFragmentManager)
        }
        iv_recharge.setOnClickListener {
            ARouter.getInstance().build(ARouteConstants.ME_BALANCE).navigation()
        }
        //跳转到奖池
        iv_pool.setOnClickListener {
            TurntablePoolFragment.newInstance().show(childFragmentManager)
        }
        //跳转到历史记录
        iv_record.setOnClickListener {
            TurntableRecordFragment.newInstance().show(childFragmentManager)
        }
        //跳转到帮助
        iv_help.setOnClickListener {
            TurntableHelpFragment.newInstance().show(childFragmentManager)
        }
    }

    val handler = Handler()

    var runnable: Runnable? = null

    /**
     * 抽奖
     */
    fun smash(number: Int) {
        presenter.smash(number)
        iv_btn_zuan_1.isClickable = false    //防止多次点击
        iv_btn_zuan_10.isClickable = false
        iv_btn_zuan_50.isClickable = false
        dialog.setCanceledOnTouchOutside(false)
    }


    override fun onSmashSuccess(t: ArrayList<SmashModel>, number: Int) {
        turntableView.startRotationAnim()
        runnable = Runnable {
            turntableView.stopRotationAnim()    //停止转盘动画
            presenter.getGameInfo() //重新拉取数据刷新余额
            val totalValue = caculateValue(t)
            TurntableResultFragment.newInstance(t, number, totalValue).show(childFragmentManager)
            iv_btn_zuan_1.isClickable = true
            iv_btn_zuan_10.isClickable = true
            iv_btn_zuan_50.isClickable = true
            dialog.setCanceledOnTouchOutside(true)
        }
        handler.postDelayed(runnable, 700)
    }

    override fun onInfoSuccess(t: GameInfoModel) {
        tv_money.text = t.money
        tv_price_1.text = t.price
        tv_price_10.text = t.price_2
        tv_price_50.text = t.price_3
        //抽奖
        iv_btn_zuan_1.setOnClickListener {
            smash(1)
        }
        iv_btn_zuan_10.setOnClickListener {
            smash(10)
        }
        iv_btn_zuan_50.setOnClickListener {
            smash(50)
        }
    }

    override fun onSmashError() {
        iv_btn_zuan_1.isClickable = true
        iv_btn_zuan_10.isClickable = true
        iv_btn_zuan_50.isClickable = true
        dialog.setCanceledOnTouchOutside(true)
        turntableView.stopRotationAnim()
    }

    override fun onPoolSuccess(t: ArrayList<GamePoolModel>) {
        turntableView.setGift(t)
    }

    fun caculateValue(t: ArrayList<SmashModel>): Int {
        var value = 0
        t.forEach {
            value += (it.number * it.price)
        }
        return value
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT)//动态设置宽高，必须设置，否则显示效果不一致
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        runnable?.let {
            handler.removeCallbacks(it)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun receivedLuckyBagEvent(event: GameSmashEvent) {
        smash(event.number)
    }

}