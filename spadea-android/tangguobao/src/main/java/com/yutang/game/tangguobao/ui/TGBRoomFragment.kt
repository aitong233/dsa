package com.yutang.game.tangguobao.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.opensource.svgaplayer.SVGACallback
import com.opensource.svgaplayer.SVGAImageView
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAVideoEntity
import com.yutang.game.tangguobao.R
import com.yutang.game.tangguobao.TGBManager
import com.yutang.game.tangguobao.adapter.TGBPlayerListAdapter
import com.yutang.game.tangguobao.base.BaseDialogFragment
import com.yutang.game.tangguobao.bean.*
import com.yutang.game.tangguobao.utils.SizeUtils
import com.yutang.game.tangguobao.utils.ToastUtils
import com.yutang.game.tangguobao.widget.MsgDialog
import kotlinx.android.synthetic.main.tgb_fragment_room.*
import kotlinx.android.synthetic.main.tgb_item_player.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TGBRoomFragment : BaseDialogFragment() {

    private var room_id: Int = 0

    private var isOwner = false //是房主

    private val CANCEL_MSG: Int = 1

    private val OPEN_PACK_MSG: Int = 2

    private val NEXT_ROUND_TIME_MSG: Int = 3
    private var lastOpenUserId: Int = 0 //最后一个开启红包的用户id

    private var cancelTime = 0  //准备倒计时

    private var openPackTime = 0  //开红包倒计时

    private var nextRoundTime = 5 //下一轮倒计时,默认为5

    lateinit var room_info: RoomInfo

    lateinit var svgaParser: SVGAParser

    private val adpter = TGBPlayerListAdapter()

    override fun getLayoutId(): Int {
        return R.layout.tgb_fragment_room
    }

    override fun initView() {
        room_id = arguments!!.getInt("room_id")
        EventBus.getDefault().post(SubscribeRoomTopicEvent(room_id))
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = adpter
        svgaParser = SVGAParser.shareParser().apply { init(context!!) }
    }


    override fun initListener() {
        iv_record.setOnClickListener {
            TGBRoomRecordFragment.newInstance(room_info.room_id).show(childFragmentManager)
        }
        iv_back.setOnClickListener {//点击离开 3离开
            when (room_info.status) {
                2 -> {
                    TGBInGameFragment.newInstance().show(childFragmentManager)
                    EventBus.getDefault().post(OperateRoomEvent(room_info.room_id, 3))
                }
                else -> {
                    if (isOwner) {
                        showOwnerLeaveRoomDialog()
                    } else {
                        EventBus.getDefault().post(OperateRoomEvent(room_info.room_id, 3))
                    }
                }
            }
        }
        btn_prepare.setOnClickListener {//准备
            EventBus.getDefault().post(OperateRoomEvent(room_info.room_id, 1))
            showPreparedUI()
        }
        iv_cancel_prepare.setOnClickListener {//取消准备
            if (isOwner) {
                showOwnerCancelPrepareDialog()
            } else {
                EventBus.getDefault().post(OperateRoomEvent(room_info.room_id, 2))
            }
        }
        iv_start.setOnClickListener {//开红包
            iv_start.visibility = View.GONE
            EventBus.getDefault().post(OpenRedParperEvent(room_info.room_id))
        }
        //禁止系统返回键
        dialog.setOnKeyListener { dialog, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    override fun initData() {
        EventBus.getDefault().post(ReconnectEvent(room_id))
    }

    var leaveDialog: MsgDialog? = null

    private fun showOwnerLeaveRoomDialog() {
        if (leaveDialog == null) {
            leaveDialog = MsgDialog(context!!).apply {
                setTitle("房主离开将解散房间")
                listener = object : MsgDialog.OnBtnClickListener {
                    override fun onConfirm() {
                        leaveDialog?.dismiss()
                        EventBus.getDefault().post(OperateRoomEvent(room_info.room_id, 3))
                    }

                    override fun onCancel() {
                        leaveDialog?.dismiss()
                    }
                }
            }
        }
        leaveDialog?.show()
    }

    private var cancelDialog: MsgDialog? = null

    private fun showOwnerCancelPrepareDialog() {
        if (cancelDialog == null) {
            cancelDialog = MsgDialog(context!!).apply {
                setTitle("房主取消准备将解散房间")
                listener = object : MsgDialog.OnBtnClickListener {
                    override fun onConfirm() {
                        cancelDialog?.dismiss()
                        EventBus.getDefault().post(OperateRoomEvent(room_info.room_id, 2))
                    }

                    override fun onCancel() {
                        cancelDialog?.dismiss()
                    }
                }
            }
        }
        cancelDialog?.show()
    }

    private fun startGame() {
        svgaStartGame.startAnimation()
    }

    private fun showTimeOpenPack(endTime: Int) {
        openPackTime = endTime
        handler.removeMessages(OPEN_PACK_MSG)
        handler.removeMessages(NEXT_ROUND_TIME_MSG)
        handler.sendEmptyMessage(OPEN_PACK_MSG)
    }

    /**
     * 设置当前用户信息
     */
    private fun setUserStatus(players: List<PlayerInfo>, endTime: Int) {
        handler.removeCallbacksAndMessages(1)
        val userId = TGBManager.getUserId()
        players.forEach {
            if (it.user_id == userId) {
                when (room_info.status) {
                    1 -> {    //准备阶段
                        when (it.status) {
                            1 -> {  //已准备
                                showPreparedUI()
                            }
                            2 -> {  //未准备
                                showUnPreparedUI(it.cancel_time)
                            }
                        }
                    }
                    2 -> {    //游戏进行中
                        if (endTime > 0) {  //倒计时状态
                            showTimeOpenPack(endTime)
                            if (it.is_open) {
                                showResultUI()
                            } else {
                                showInGameUI()
                            }
                        } else {
                            showResultUI()
                        }
                    }
                    3 -> {    //已结算结束
                    }
                }
            }
        }
    }

    /**
     * 显示开始准备倒计时
     */
    private fun startTimer(cancelTime: Int) {
        this.cancelTime = cancelTime
        handler.sendEmptyMessage(CANCEL_MSG)
    }

    @SuppressLint("SetTextI18n")
    val handler: Handler = Handler(Handler.Callback { msg ->
        msg?.let {
            when (it.what) {
                CANCEL_MSG -> {
                    if (cancelTime == 0) {
                        dismiss()
                        ToastUtils.showShortToast(context, "您已被踢出房间")
                    }
                    tv_prepare_time.text = "($cancelTime)"
                    cancelTime--
                    sendTimeCountDown()
                }
                OPEN_PACK_MSG -> {
                    tv1.text = "抢弹珠倒计时："
                    tv_open_time.text = "$openPackTime"
                    when (openPackTime) {
                        0 -> {    //倒计时结束，显示所有背包弹珠数
                            iv_start.visibility = View.GONE
                            room_info.players.forEachIndexed { index, playerInfo ->
                                if (!playerInfo.is_open) {
                                    val tvSugarNum = adpter.getViewByPosition(recyclerview, index, R.id.tv_candy_num) as TextView
                                    val openSVGA = adpter.getViewByPosition(recyclerview, index, R.id.svga) as SVGAImageView
                                    playSmallAnimation(openSVGA, playerInfo, tvSugarNum)
                                    if (playerInfo.user_id == TGBManager.getUserId()) {
                                        playBigAnimation()
                                    }
                                }
                            }
                            removeOpenMsg()
                        }
                        else -> {
                            openPackTime--
                            sendOpenPackTimeCountDown()
                        }
                    }
                }
                NEXT_ROUND_TIME_MSG -> {
                    tv1.text = "下轮开启："
                    tv_open_time.text = "$nextRoundTime"
                    if (nextRoundTime == 0) {
                        removeNextRoundMsg()
                    } else {
                        nextRoundTime--
                        sendNextRoundTimeCountDown()
                    }
                }
                else -> {
                }
            }
        }
        false
    })

    private fun removeOpenMsg() {
        handler.removeMessages(OPEN_PACK_MSG)
    }

    private fun removeNextRoundMsg() {
        handler.removeMessages(NEXT_ROUND_TIME_MSG)
    }

    private fun sendOpenPackTimeCountDown() {
        handler.sendEmptyMessageDelayed(OPEN_PACK_MSG, 1000)
    }

    private fun sendNextRoundTimeCountDown() {
        handler.sendEmptyMessageDelayed(NEXT_ROUND_TIME_MSG, 1000)
    }

    private fun sendTimeCountDown() {
        handler.sendEmptyMessageDelayed(CANCEL_MSG, 1000)
    }

    /**
     * 显示游戏中
     */
    private fun showInGameUI() {
        tv_result_1.visibility = View.GONE
        tv_result_2.visibility = View.GONE
        btn_prepare.visibility = View.GONE
        iv_cancel_prepare.visibility = View.GONE
        iv_start.visibility = View.VISIBLE
    }

    /**
     * 显示已准备UI
     */
    private fun showPreparedUI() {
        handler.removeMessages(CANCEL_MSG)
        iv_start.visibility = View.GONE
        tv_result_1.visibility = View.GONE
        tv_result_2.visibility = View.GONE
        btn_prepare.visibility = View.GONE
        iv_cancel_prepare.visibility = View.VISIBLE
    }

    /**
     * 显示未准备UI
     */
    private fun showUnPreparedUI(cancelTime: Int) {
        iv_start.visibility = View.GONE
        tv_result_1.visibility = View.GONE
        tv_result_2.visibility = View.GONE
        iv_cancel_prepare.visibility = View.GONE
        iv_start.visibility = View.GONE
        btn_prepare.visibility = View.VISIBLE
        if (!isOwner)   //房主取消后，直接解散，不需要倒计时
            startTimer(cancelTime)
    }


    /**
     * 合并数据
     */
    private fun mergePlayerInfo(players: List<PlayerInfo>, roundGames: List<PlayerGameIf>) {
        players.forEach { playerInfo ->
            roundGames.forEach { playerGameIf ->
                if (playerInfo.user_id == playerGameIf.user_id) {
                    playerInfo.is_open = playerGameIf.is_open
                    playerInfo.sugar_num = playerGameIf.sugar_num
                    playerInfo.send_sugar = playerGameIf.send_sugar
                    if (playerInfo.user_id == TGBManager.getUserId()) {
                        setResultData(playerInfo.sugar_num)
                    }
                }
            }
        }
    }

    private fun setResultData(sugarNum: Int) {
        tv_result_2.text = sugarNum.toString()
    }

    /**
     * 设置接龙玩家信息
     */
    private fun setSendPlayer(playerInfo: PlayerInfo?) {
        playerInfo?.run {
            if (head_picture.isNotEmpty()) {
                Glide.with(layout_jielong_player.iv_head.context).load(head_picture).into(layout_jielong_player.iv_head)
            }
            layout_jielong_player.tv_candy_num.text = playerInfo.sugar_num.toString()
            layout_jielong_player.iv_prepare_status.visibility = View.GONE
            layout_jielong_player.tv_id.text = "ID:" + playerInfo.user_code
            layout_jielong_player.visibility = View.VISIBLE
            if (user_id == TGBManager.getUserId()) {
                layout_jielong_player.cl_bg.setBackgroundResource(R.drawable.tgb_bg_item_self)
            } else {
                layout_jielong_player.cl_bg.setBackgroundResource(R.drawable.tgb_bg_item_other)
            }
        }
    }

    /**
     * 播放动画
     */
    private fun playBigAnimation() {
        svgaSelfopen.callback = object : SVGACallback {
            override fun onFinished() {
                showResultUI()
            }

            override fun onPause() {
            }

            override fun onRepeat() {
            }

            override fun onStep(frame: Int, percentage: Double) {
            }
        }
        svgaSelfopen.startAnimation()
    }

    /**
     * 开红包
     */
    private fun openRedBag(roundGames: List<PlayerGameIf>) {
        roundGames.forEachIndexed { index, playerGameIf ->
            room_info.players.forEachIndexed { i: Int, playerInfo: PlayerInfo ->
                if (playerInfo.user_id == playerGameIf.user_id && playerGameIf.is_open && playerGameIf.is_open != playerInfo.is_open) {
                    val tvSugarNum = adpter.getViewByPosition(recyclerview, i, R.id.tv_candy_num) as TextView
                    val openSVGA = adpter.getViewByPosition(recyclerview, i, R.id.svga) as SVGAImageView
                    playerInfo.is_open = true
                    playSmallAnimation(openSVGA, playerInfo, tvSugarNum)
                    if (playerInfo.user_id == TGBManager.getUserId()) { //是自己播放大动画
                        playBigAnimation()
                    }
                }
            }
        }


    }

    private fun checkIsAllOpen(): Boolean {
        var isAllOpen = true
        room_info.players.forEach {
            if (!it.is_open) {
                isAllOpen = false
            }
        }
        if (isAllOpen) {
            showNextRoundTime()
        }
        return isAllOpen
    }

    /**
     * 开始显示下一轮开始时间
     */
    private fun showNextRoundTime() {
        nextRoundTime = 5
        handler.removeMessages(OPEN_PACK_MSG)
        handler.sendEmptyMessage(NEXT_ROUND_TIME_MSG)
    }

    fun playSmallAnimation(openSVGA: SVGAImageView, playerInfo: PlayerInfo, tvSugarNum: TextView) {
        playerInfo.is_open = true
        lastOpenUserId = playerInfo.user_id
        val checkIsAllOpen = checkIsAllOpen()
        svgaParser.decodeFromAssets("openpack.svga", object : SVGAParser.ParseCompletion {
            override fun onComplete(videoItem: SVGAVideoEntity) {
                openSVGA.setVideoItem(videoItem)
                openSVGA.startAnimation()
                openSVGA.callback = object : SVGACallback {
                    override fun onFinished() {
                        tvSugarNum.text = playerInfo.sugar_num.toString()
                        if (checkIsAllOpen && playerInfo.user_id == lastOpenUserId) {
                            setSendPlayer(room_info.players)
                        }
                    }

                    override fun onPause() {
                    }

                    override fun onRepeat() {
                    }

                    override fun onStep(frame: Int, percentage: Double) {
                    }
                }
            }

            override fun onError() {
            }

        })

    }

    private fun showResultUI() {
        iv_start.visibility = View.GONE
        btn_prepare.visibility = View.GONE
        iv_cancel_prepare.visibility = View.GONE
        tv_result_1.visibility = View.VISIBLE
        tv_result_2.visibility = View.VISIBLE
    }


    /**
     * 设置房间数据
     */
    private fun setRoomInfo(reconnRoomRsp: ReconnRoomRsp) {
        room_info = reconnRoomRsp.room_if
        isOwner = room_info.user_id == TGBManager.getUserId()
        tv_round.text = "局数：${reconnRoomRsp.current_round}/" + room_info.round_num
        tv_player_count.text = "人数：" + room_info.people_had_num + "/" + room_info.people_num
        tv_ruler.text = if (room_info.rule == 1) "最低接龙：" else "最高接龙："
        tv_candy_count.text = room_info.sugar_num.toString()
        mergePlayerInfo(room_info.players, reconnRoomRsp.round_games)
        adpter.setNewData(room_info.players)
        setUserStatus(room_info.players, reconnRoomRsp.end_time)    //设置用户状态
    }

    private fun setSendPlayer(playerInfo: List<PlayerInfo>) {
        playerInfo.forEach {
            if (it.send_sugar > 0) {
                setSendPlayer(it)
            }
        }
    }

    /**
     * 重连
     */
    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(operateRoomRsp: OperateRoomRsp) {
        val status = operateRoomRsp.status
        when (status.code) {
            0 -> {
            }
            10 -> {
                dismiss()
                ToastUtils.showShortToast(context, status.msg)
                EventBus.getDefault().post(GetRoomListEvent()) //获取房间列表
            }
            14 -> {
            }
            else -> {
                ToastUtils.showShortToast(context, status.msg)
            }
        }
    }

    /**
     * 重连
     */
    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onMessageEvent(reconnRoomRsp: ReconnRoomRsp) {
        EventBus.getDefault().removeStickyEvent(reconnRoomRsp)
        val status = reconnRoomRsp.status
        when (status.code) {
            0 -> {
                setRoomInfo(reconnRoomRsp)
            }
            10 -> {
                dismiss()
            }
            else -> {
                ToastUtils.showShortToast(context, "弹珠袋不存在")
            }
        }
    }

    /**
     * 重新订阅
     */
    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(reconnectEvent: EmqttServiceReconnectEvent) {
        EventBus.getDefault().post(SubscribeRoomTopicEvent(room_id))
        EventBus.getDefault().post(ReconnectEvent(room_id))
    }

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(broadcastRoomRsp: BroadcastGameRsp) {
        val status = broadcastRoomRsp.status
        val roomIf = broadcastRoomRsp.room_if
        when (status.code) {
            10 -> {
                dismiss()
            }
            2001 -> {   //加入房间
                room_info = roomIf
                adpter.setNewData(roomIf.players)
                tv_player_count.text = "人数：" + roomIf.people_had_num + "/" + roomIf.people_num
            }
            2002 -> {   //离开房间
                room_info = roomIf
                if (roomIf.out_user_id == TGBManager.getUserId()) {
                    dismiss()
                    return
                }
                adpter.setNewData(roomIf.players)
                tv_player_count.text = "人数：" + roomIf.people_had_num + "/" + roomIf.people_num
            }
            2003 -> {   //取消准备
                room_info = roomIf
                adpter.setNewData(roomIf.players)
                setUserStatus(roomIf.players, broadcastRoomRsp.end_time)
            }
            2004 -> {   //玩家准备
                room_info = roomIf
                adpter.setNewData(roomIf.players)
                setUserStatus(roomIf.players, broadcastRoomRsp.end_time)
            }
            2005 -> {   //解散房间
                ToastUtils.showShortToast(context, "房间已解散！")
                dismiss()
            }
            2006 -> {   //开始游戏
                cancelDialog?.dismiss()
                leaveDialog?.dismiss()
                adpter.data.forEach {
                    it.status = 3
                }
                startGame()
            }
            2007 -> {   //开启下一轮
                room_info.status = 2
                layout_jielong_player.visibility = View.GONE
                tv_round.text = "局数：${broadcastRoomRsp.current_round}/${room_info.round_num}"
                mergePlayerInfo(room_info.players, broadcastRoomRsp.round_games)
                adpter.setNewData(room_info.players)
                setUserStatus(room_info.players, broadcastRoomRsp.end_time)
            }
            2008 -> {   //打开红包
                openRedBag(broadcastRoomRsp.round_games)
            }
            2009 -> {   //游戏结束
                TGBResultFragment.newInstance(UserResultList(broadcastRoomRsp.user_results)).show(fragmentManager)
                dismiss()
            }
        }
    }

    override fun show(fragmentManager: FragmentManager?) {
        show(fragmentManager, "TGBRoomFragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().post(InRoomEvent(true))
    }

    override fun onResume() {
        super.onResume()
        dialog.window?.setLayout(
                SizeUtils.dp2px(context!!, 332f), //动态设置宽高，必须设置，否则显示效果不一致
                SizeUtils.dp2px(context!!, 420f)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeMessages(CANCEL_MSG)
        handler.removeMessages(NEXT_ROUND_TIME_MSG)
        handler.removeMessages(OPEN_PACK_MSG)
        EventBus.getDefault().post(InRoomEvent(false))
        EventBus.getDefault().post(UnSubscribeRoomTopicEvent(room_id))
        cancelDialog?.dismiss()
        leaveDialog?.dismiss()
    }

    companion object {
        @JvmStatic
        fun newInstance(room_id: Int) = TGBRoomFragment().also {
            it.arguments = Bundle().apply {
                putInt("room_id", room_id)
            }
        }
    }

}