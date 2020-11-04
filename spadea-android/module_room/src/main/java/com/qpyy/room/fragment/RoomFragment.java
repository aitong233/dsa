package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.bumptech.glide.Glide;
import com.hjq.toast.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.bean.RoomApplyWheatCountModel;
import com.qpyy.libcommon.bean.RoomBannedModel;
import com.qpyy.libcommon.bean.RoomDownWheatModel;
import com.qpyy.libcommon.bean.RoomFishingModel;
import com.qpyy.libcommon.bean.RoomGiveGiftModel;
import com.qpyy.libcommon.bean.RoomJoinMountModel;
import com.qpyy.libcommon.bean.RoomJoinNobilityModel;
import com.qpyy.libcommon.bean.RoomManagerModel;
import com.qpyy.libcommon.bean.RoomNoticeModel;
import com.qpyy.libcommon.bean.RoomStarModel;
import com.qpyy.libcommon.bean.RoomWheatModel;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.event.PublicScreenEvent;
import com.qpyy.libcommon.event.QDZGameControlEvent;
import com.qpyy.libcommon.event.RoomBeckoningEvent;
import com.qpyy.libcommon.event.RoomGiftEvent;
import com.qpyy.libcommon.event.RoomGuardEvent;
import com.qpyy.libcommon.event.RoomToneEvent;
import com.qpyy.libcommon.event.RoomUserBanWheatEvent;
import com.qpyy.libcommon.event.RoomWheatEvent;
import com.qpyy.libcommon.event.UserDetailShowEvent;
import com.qpyy.libcommon.event.UserInfoShowEvent;
import com.qpyy.libcommon.service.EMqttService;
import com.qpyy.libcommon.service.EmqttState;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.libcommon.utils.OnClickUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.libcommon.widget.dialog.CommonDialog;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.BannerItem;
import com.qpyy.room.bean.DownWheatEvent;
import com.qpyy.room.bean.EffectEvent;
import com.qpyy.room.bean.MusicIsPlay;
import com.qpyy.room.bean.NewsMessageEvent;
import com.qpyy.room.bean.NewsModel;
import com.qpyy.room.bean.RoomBean;
import com.qpyy.room.bean.RoomInfoResp;
import com.qpyy.room.bean.RoomInputEvent;
import com.qpyy.room.bean.RoomPitBean;
import com.qpyy.room.bean.RoomVoiceState;
import com.qpyy.room.bean.UpdateRoom;
import com.qpyy.room.contacts.RoomFragmentContacts;
import com.qpyy.room.dialog.RoomTipsView;
import com.qpyy.room.event.ApplyWaitEvent;
import com.qpyy.room.event.RoomMixEvent;
import com.qpyy.room.event.RoomPlayingEvent;
import com.qpyy.room.event.UserDownWheatEvent;
import com.qpyy.room.presenter.RoomFragmentPresenter;
import com.qpyy.room.widget.BigGiftAnimView;
import com.qpyy.room.widget.GameAnimView;
import com.qpyy.room.widget.GuardAnimView;
import com.qpyy.room.widget.RoomMessageInputMenu;
import com.qpyy.room.widget.SmallGiftAnimLayout;
import com.qpyy.room.widget.SvgaAnimView;
import com.qpyy.rtc.RtcConstants;
import com.qpyy.rtc.RtcDestroyCallback;
import com.qpyy.rtc.RtcManager;
import com.stx.xhb.xbanner.XBanner;
import com.yutang.game.fudai.fragment.EggGameDialogFragment;
import com.yutang.game.grabmarbles.GrabMarblesManager;
import com.yutang.game.tangguobao.TGBManager;
import com.yutang.game.tangguobao.bean.BaseReq;
import com.yutang.game.tangguobao.bean.CreateRoomEvent;
import com.yutang.game.tangguobao.bean.CreateRoomReq;
import com.yutang.game.tangguobao.bean.GameRuleReq;
import com.yutang.game.tangguobao.bean.GetGameHistoryEvent;
import com.yutang.game.tangguobao.bean.GetGameHistoryReq;
import com.yutang.game.tangguobao.bean.GetGameRulerEvent;
import com.yutang.game.tangguobao.bean.GetRoomConfigEvent;
import com.yutang.game.tangguobao.bean.GetRoomGameLogEvent;
import com.yutang.game.tangguobao.bean.GetRoomGameLogReq;
import com.yutang.game.tangguobao.bean.GetRoomListEvent;
import com.yutang.game.tangguobao.bean.GetRoomListReq;
import com.yutang.game.tangguobao.bean.JoinRoomEvent;
import com.yutang.game.tangguobao.bean.JoinRoomReq;
import com.yutang.game.tangguobao.bean.OpenRedParperEvent;
import com.yutang.game.tangguobao.bean.OpenRedParperReq;
import com.yutang.game.tangguobao.bean.OperateRoomEvent;
import com.yutang.game.tangguobao.bean.OperateRoomReq;
import com.yutang.game.tangguobao.bean.ReconnRoomReq;
import com.yutang.game.tangguobao.bean.ReconnectEvent;
import com.yutang.game.tangguobao.bean.RoomConfReq;
import com.yutang.game.tangguobao.bean.SubscribeBigRoomTopicEvent;
import com.yutang.game.tangguobao.bean.SubscribeRoomTopicEvent;
import com.yutang.game.tangguobao.bean.UnSubscribeRoomTopicEvent;
import com.yutang.game.tangguobao.ui.TGBRoomListFragment;
import com.yutang.game.turntable.ui.TurntableDialogFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.fragment
 * 创建人 王欧
 * 创建时间 2020/8/4 1:02 PM
 * 描述 describe
 */
public class RoomFragment extends BaseMvpFragment<RoomFragmentPresenter> implements RoomFragmentContacts.View, XBanner.OnItemClickListener {

    @BindView(R2.id.container)
    FrameLayout mContainer;
    @BindView(R2.id.tv_wheat)
    TextView mTvWheat;
    @BindView(R2.id.tv_wheat_queue)
    TextView mTvWheatQueue;
    @BindView(R2.id.iv_mic)
    ImageView mIvMic;
    @BindView(R2.id.iv_chat)
    ImageView mIvChat;
    @BindView(R2.id.iv_emoji)
    ImageView mIvEmoji;
    @BindView(R2.id.iv_message)
    ImageView mIvMessage;
    @BindView(R2.id.iv_more)
    ImageView mIvMore;
    @BindView(R2.id.iv_qdz)
    ImageView mIvQDZ;
    @BindView(R2.id.iv_gift)
    ImageView mIvGift;
    @BindView(R2.id.ll_bottom)
    LinearLayout mLlBottom;
    @BindView(R2.id.ease_container)
    FrameLayout mEaseContainer;
    @BindView(R2.id.tv_play)
    TextView mTvPlay;
    @BindView(R2.id.gav)
    GuardAnimView mGuardAnimView;
    @BindView(R2.id.bgav)
    BigGiftAnimView mBigGiftAnimView;
    @BindView(R2.id.game_anim_view)
    GameAnimView mGameAnimView;
    @BindView(R2.id.sgal)
    SmallGiftAnimLayout mSmallGiftAnimLayout;
    @BindView(R2.id.svga_gift)
    SvgaAnimView mSvgaGift;
    @BindView(R2.id.svga_ride)
    SvgaAnimView mSvgaRide;
    @BindView(R2.id.svga_nobility)
    SvgaAnimView mSvgaNobility;
    @BindView(R2.id.banner)
    XBanner mXBanner;
    @BindView(R2.id.input_menu)
    RoomMessageInputMenu inputMenu;
    @BindView(R2.id.ll_input)
    LinearLayout llMenu;

    private RoomInfoResp mRoomInfoResp;
    private String roomId;
    private int role;
    private int isGameRoom;
    private String password;
    private boolean isPlay = false;//音乐播放状态(默认关闭)
    private boolean voiceState = true;//声音状态(默认开启)
    private UserInfoDialogFragment userInfoDialogFragment;
    private MyInfoDialogFragment myInfoDialogFragment;
    private GameDialogFragment gameDialogFragment;


    public static RoomFragment newInstance(RoomInfoResp roomInfo, String password) {

        Bundle args = new Bundle();
        args.putSerializable("roomInfo", roomInfo);
        args.putString("password", password);
        RoomFragment fragment = new RoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        mTvPlay.removeCallbacks(playRunnable);
        super.onDestroyView();
    }


    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        mRoomInfoResp = (RoomInfoResp) arguments.getSerializable("roomInfo");
        isGameRoom = mRoomInfoResp.getRoom_info().getIs_game_room();
        password = arguments.getString("password");
        role = mRoomInfoResp.getRoom_info().getRole();
        roomId = mRoomInfoResp.getRoom_info().getRoom_id();
        roomInfo(mRoomInfoResp);
    }

    @Override
    protected RoomFragmentPresenter bindPresenter() {
        return new RoomFragmentPresenter(this, getActivity());
    }

    private Runnable playRunnable;

    @Override
    protected void initData() {
        RoomBean roomBean = mRoomInfoResp.getRoom_info();
        updateBottom();
        mXBanner.setBannerData(R.layout.room_image_banner, mRoomInfoResp.getBanner());
        if (roomBean.getRoom_type() == 1) {
            FragmentUtils.add(getChildFragmentManager(), StationRoomFragment.newInstance(mRoomInfoResp), R.id.container);
        } else {
            FragmentUtils.add(getChildFragmentManager(), EmotionRoomFragment.newInstance(mRoomInfoResp), R.id.container);
        }
        FragmentUtils.add(getChildFragmentManager(), PublicScreenEaseChatFragment.newInstance(mRoomInfoResp), R.id.ease_container);

        //玩法显示时
        if (mTvPlay.getVisibility() == View.VISIBLE) {
            playRunnable = new Runnable() {
                @Override
                public void run() {
                    RoomTipsView.showWithDelay(getActivity(), mTvPlay, String.format("欢迎来到%s房间", roomBean.getRoom_name()), roomBean.getPlaying());
                }
            };
            mTvPlay.post(playRunnable);

        }
        llMenu.performClick();
    }

    private void refreshUnread() {
        MvpPre.userNews();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void newMessage(NewsMessageEvent newsMessageEvent) {
        refreshUnread();
    }

    /**
     * 更新上麦按钮和麦克风
     */
    private void updateBottom() {
        mTvWheatQueue.setBackgroundResource(R.mipmap.room_ic_mic);//默认设置为圆圈
        if ("2".equals(mRoomInfoResp.getRoom_info().getWheat())) {//排麦模式
            mTvWheat.setVisibility(View.INVISIBLE);//隐藏自由模式按钮
            mTvWheatQueue.setVisibility(View.VISIBLE);//开启排麦模式按钮
            if (mRoomInfoResp.isManager()) {//管理员
                MvpPre.applyWheatUsers(roomId);
//                mTvWheatQueue.setText(mRoomInfoResp.getRoom_info().getApply_count());//获取正在排麦人数
            } else {//非管理员
                if (mRoomInfoResp.getUser_info().getApply_wait() == 1) {
                    mTvWheatQueue.setBackgroundResource(R.mipmap.room_ic_mic);//显示排麦中
                    mTvWheatQueue.setText("排麦中");
                } else {
                    if (mRoomInfoResp.getUser_info().getPit() > 0) {
                        mTvWheatQueue.setText("下麦");
                    } else {
                        mTvWheatQueue.setText("上麦");
                    }

                }
            }
        } else {//自由模式
            mTvWheat.setVisibility(View.VISIBLE);//显示排麦模式按钮
            mTvWheatQueue.setVisibility(View.INVISIBLE);//隐藏自由模式按钮
            if (mRoomInfoResp.getUser_info().getPit() == 0) {//不在麦位上
                mTvWheat.setText("上麦");
            } else {//在麦位上
                mTvWheat.setText("下麦");
            }
        }
        if (mRoomInfoResp.getRoom_info().getRoom_type() == 1) {//电台房
            mTvWheat.setVisibility(View.VISIBLE);//显示排麦模式按钮
            mTvWheatQueue.setVisibility(View.INVISIBLE);//隐藏自由模式按钮
            if (mRoomInfoResp.getUser_info().getPit() == 0) {//不在麦位上
                mTvWheat.setText("上麦");
                mTvWheat.setVisibility(View.GONE);
            } else {//在麦位上
                mTvWheat.setText("下麦");
                mTvWheat.setVisibility(View.VISIBLE);
            }
        }
        if (mRoomInfoResp.getUser_info().getPit() == 0) {//不在麦位上
            mIvMic.setVisibility(View.GONE);//隐藏话筒
        } else {//在麦位上
            mIvMic.setVisibility(View.VISIBLE);//话筒显示
        }
    }

    @Override
    protected void initView() {
        mXBanner.loadImage((banner, model, view, position) -> {
            BannerItem bannerModel = (BannerItem) model;
            Glide.with(getContext()).load(bannerModel.getPicture()).into((ImageView) view);
        });
        mXBanner.setOnItemClickListener(this);
        setEffectSwitch(SpUtils.getOpenEffect() == 1 ? new EffectEvent(true) : new EffectEvent(false));
        Glide.with(mIvQDZ.getContext()).load(R.drawable.qqq_icon).into(mIvQDZ);
        GrabMarblesManager.INSTANCE.checkGameStatus(b -> {
            if (b) {
                mIvQDZ.setVisibility(View.VISIBLE);
            } else {
                mIvQDZ.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_fragment_room;
    }

    @OnClick({R2.id.tv_wheat, R2.id.iv_chat, R2.id.iv_emoji, R2.id.iv_message, R2.id.iv_more,
            R2.id.iv_gift, R2.id.tv_play, R2.id.iv_mic, R2.id.tv_wheat_queue, R2.id.ll_input, R2.id.iv_qdz})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_wheat) {//自由模式按钮
            operationWheat();
        } else if (id == R.id.ll_input) {
            llMenu.setVisibility(View.GONE);
            inputMenu.dismiss();
        } else if (id == R.id.tv_wheat_queue) {//排麦模式按钮
            doWheatWait();
        } else if (id == R.id.iv_chat) {
            if (mRoomInfoResp.getUser_info().getBanned() == 1) {
                ToastUtils.show("您已经被禁言");
            } else {
                llMenu.setVisibility(View.VISIBLE);
                inputMenu.show();
            }
        } else if (id == R.id.iv_emoji) {
            if (!OnClickUtils.isFastDoubleClick(R.id.iv_gift)) {
                EmojDialogFragment.newInstance(roomId, String.valueOf(mRoomInfoResp.getUser_info().getPit()), isGameRoom).show(getChildFragmentManager());
            }
        } else if (id == R.id.iv_message) {
            RoomMessageDialogFragment.newInstance("消息").show(getChildFragmentManager(), "RoomMessageDialogFragment");
        } else if (id == R.id.iv_more) {
            if (!OnClickUtils.isFastDoubleClick(R.id.iv_more)) {
                if (mRoomInfoResp.getRoom_info().getRoom_type() == 1) {
                    RoomToolDialogFragment.newInstance(mRoomInfoResp, password, isPlay, true, voiceState).show(getChildFragmentManager(), "RoomToolTableDialog");
                } else {
                    RoomToolDialogFragment.newInstance(mRoomInfoResp, password, isPlay, false, voiceState).show(getChildFragmentManager(), "RoomToolTableDialog");
                }
            }
        } else if (id == R.id.iv_gift) {
            if (!OnClickUtils.isFastDoubleClick(R.id.iv_gift)) {
                RoomGiftDialogFragment.newInstance("", roomId).show(getChildFragmentManager());
            }
        } else if (id == R.id.iv_mic) {
            if (mRoomInfoResp.getUser_info().getVoice() == 1) {
                MvpPre.switchMic(roomId, String.valueOf(mRoomInfoResp.getUser_info().getPit()), 2);
            } else {
                MvpPre.switchMic(roomId, String.valueOf(mRoomInfoResp.getUser_info().getPit()), 1);
            }
        } else if (id == R.id.tv_play) {
            RoomBean roomBean = mRoomInfoResp.getRoom_info();
            if (roomBean != null) {
                RoomTipsView.show(getActivity(), mTvPlay, String.format("欢迎来到%s房间", roomBean.getRoom_name()), roomBean.getPlaying());
            }
        } else if (id == R.id.iv_qdz) {
            GrabMarblesManager.INSTANCE.showGame(getChildFragmentManager());
        }
    }


    /**
     * 排麦模式下按钮点击事件
     */
    private void doWheatWait() {
        if (role == 3) {
            //普通用户不在麦位上
            if (mRoomInfoResp.getUser_info().getPit() == 0) {
                if (mRoomInfoResp.getUser_info().getApply_wait() == 1) {
                    WaitForDialogFragment.newInstance(roomId, role).show(getChildFragmentManager());
                } else {
                    showConfirmApplyWait();
                }
            } else {
                //普通用户在麦位上
                MvpPre.downWheat(roomId);
            }
        } else {
            WaitForDialogFragment.newInstance(roomId, role).show(getChildFragmentManager());
        }
    }

    CommonDialog commonDialog;

    private void showConfirmApplyWait() {
        if (commonDialog == null) {
            commonDialog = new CommonDialog(getContext());
            commonDialog.setContent("是否加入当前麦序队列");
            commonDialog.setmOnClickListener(new CommonDialog.OnClickListener() {
                @Override
                public void onLeftClick() {

                }

                @Override
                public void onRightClick() {
                    MvpPre.applyWheatWait(roomId, "");
                }
            });
        }
        if (!commonDialog.isShowing()) {
            commonDialog.show();
        }
    }


    /**
     * 自由模式下按钮点击事件
     */
    public void operationWheat() {
        if (mRoomInfoResp.getUser_info().getPit() > 0) {//如果在麦位上 点击就下麦
            MvpPre.downWheat(roomId);
        } else {
            MvpPre.applyWheat(roomId, "");
        }
    }

    //排中
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dialogOperationWheat(ApplyWaitEvent event) {
        mRoomInfoResp.getUser_info().setApply_wait(event.success ? 1 : 0);
        updateBottom();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessage(QDZGameControlEvent event) {
        if (event.getAction() == 1) {
            mIvQDZ.setVisibility(View.VISIBLE);
        } else {
            mIvQDZ.setVisibility(View.GONE);
            GrabMarblesManager.INSTANCE.closeGame();
        }
    }

    /**
     * 点击自己弹窗下麦
     *
     * @param downWheat
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dialogOperationWheat(DownWheatEvent downWheat) {
        if (mRoomInfoResp.getUser_info().getPit() == 0) {
            if ("2".equals(mRoomInfoResp.getRoom_info().getWheat())) {
                MvpPre.applyWheatWait(roomId, "");
            } else {
                MvpPre.applyWheat(roomId, "");
            }
        } else {
            //普通用户在麦位上
            MvpPre.downWheat(roomId);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dialogOperationWheat(RoomInputEvent roomInputEvent) {
        MvpPre.sendTxtMessage("", "1", roomInputEvent.text, roomId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void musicIsPlay(MusicIsPlay musicIsPlay) {
        isPlay = musicIsPlay.isPlay();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void roomInfo(RoomInfoResp resp) {
        mRoomInfoResp = resp;
        updateBottom();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void roomPlaying(RoomPlayingEvent event) {
        mRoomInfoResp.getRoom_info().setPlaying(event.value);
    }

    /**
     * 横幅礼物通知c
     *
     * @param roomGiftEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomGiftEvent roomGiftEvent) {
        if (roomId.equals(roomGiftEvent.getRoomId())) {
            mBigGiftAnimView.addAnim(roomGiftEvent);
        }
    }

    /**
     * 房间礼物通知
     *
     * @param giftListBean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomGiveGiftModel.GiftListBean giftListBean) {
        mSmallGiftAnimLayout.addGift(giftListBean);
        if (!TextUtils.isEmpty(giftListBean.getSpecial())) {
            mSvgaGift.load(giftListBean.getSpecial());
        }
    }

    /**
     * 开通守护通知
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomGuardEvent event) {
        if (roomId.equals(event.getRoom_id())) {
            mGuardAnimView.addAnim(event);
        }
    }

    /**
     * 小猫钓鱼通知
     *
     * @param roomFishingModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomFishingModel roomFishingModel) {
        LogUtils.e("RoomFishingModel", roomFishingModel.toString());
        mGameAnimView.addAnim(roomFishingModel);
    }

    /**
     * 爵位用户进场特效
     *
     * @param roomJoinNobilityModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void roomPopularity(RoomJoinNobilityModel roomJoinNobilityModel) {
        mSvgaNobility.load(roomJoinNobilityModel.getSpecial());
    }

    /**
     * 坐骑进场特效
     *
     * @param roomJoinMountModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void roomJoinMount(RoomJoinMountModel roomJoinMountModel) {
        if (!roomId.equals(roomJoinMountModel.getRoom_id())) {
            return;
        }
        if (roomJoinMountModel.getShow_type() == 1) {
            mSvgaRide.load(roomJoinMountModel.getRide_url());
        } else {
            mSvgaGift.load(roomJoinMountModel.getRide_url());
        }
    }

    /**
     * 设置房间管理员
     *
     * @param roomManagerModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomManagerModel roomManagerModel) {
        if (roomId.equals(roomManagerModel.getRoom_id())) {
            this.role = roomManagerModel.getType();
            mRoomInfoResp.getRoom_info().setRole(role);
        }
    }


//    /**
//     * 用户禁麦
//     *
//     * @param roomUserBanWheatEvent
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void subscribeMessages(RoomUserBanWheatEvent roomUserBanWheatEvent) {
//        if (roomId.equals(roomUserBanWheatEvent.getRoomId())) {
//            mRoomInfoResp.getUser_info().setShutup(roomUserBanWheatEvent.isBanWheat() ? 1 : 2);
//            mIvMic.setImageResource(roomUserBanWheatEvent.isBanWheat() ? R.mipmap.room_ic_bottom_mic_off : R.mipmap.room_ic_bottom_mic_on);
//        }
//
//    }

    /**
     * 用户禁麦
     *
     * @param roomUserBanWheatEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomUserBanWheatEvent roomUserBanWheatEvent) {
        if (roomId.equals(roomUserBanWheatEvent.getRoomId())) {
            mRoomInfoResp.getUser_info().setShutup(roomUserBanWheatEvent.isBanWheat() ? 1 : 2);
            if (roomUserBanWheatEvent.isBanWheat()) {
                switchMic(2);
            }
        }

    }


    /**
     * 周星进入房间
     *
     * @param roomStarModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomStarModel roomStarModel) {
        if (roomId.equals(roomStarModel.getRoom_id())) {
            mGuardAnimView.addAnim(roomStarModel);
        }
    }

    /**
     * 开关公屏 1开2关
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(PublicScreenEvent event) {
        if (roomId.equals(event.getRoom_id())) {
            mRoomInfoResp.getRoom_info().setChat_status(event.getStatus());
        }
    }

    /**
     * 上麦模式变化通知 1自由2排麦
     *
     * @param roomWheatEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomWheatEvent roomWheatEvent) {
        if (roomId.equals(roomWheatEvent.getRoomId())) {
            mRoomInfoResp.getRoom_info().setWheat(roomWheatEvent.isFree() ? "1" : "2");
            if (roomWheatEvent.isFree()) {    //切换到自由麦模式，自动取消排麦
                mRoomInfoResp.getUser_info().setApply_wait(0);
            }
            updateBottom();
        }
    }

    /**
     * 用户是否禁言 1禁言2解禁
     *
     * @param roomBannedModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomBannedModel roomBannedModel) {
        if (roomId.equals(roomBannedModel.getRoom_id()) && SpUtils.getUserId().equals(roomBannedModel.getUser_id())) {
            mRoomInfoResp.getUser_info().setBanned(Integer.parseInt(roomBannedModel.getAction()));
        }
    }

    /**
     * 上麦申请人数变化
     *
     * @param roomApplyWheatCountModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomApplyWheatCountModel roomApplyWheatCountModel) {
        if (roomId.equals(roomApplyWheatCountModel.getRoom_id())) {
//            移除列表中包含自己
            if (roomApplyWheatCountModel.getUser_ids() != null && roomApplyWheatCountModel.getUser_ids().contains(SpUtils.getUserId())) {
                mRoomInfoResp.getUser_info().setApply_wait(0);
            }
            mRoomInfoResp.getRoom_info().setApply_count(roomApplyWheatCountModel.getCount());
            updateBottom();
        }
    }

    /**
     * 用户上麦
     *
     * @param roomWheatModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomWheatModel roomWheatModel) {
        if (!roomId.equals(roomWheatModel.getRoom_id()) || !SpUtils.getUserId().equals(roomWheatModel.getUser_id())) {
            return;
        }
        mRoomInfoResp.getUser_info().setApply_wait(0);
        mRoomInfoResp.getUser_info().setPit(Integer.parseInt(roomWheatModel.getPit_number()));
        RtcManager.getInstance().applyWheat(String.format("%s_%s", roomWheatModel.getRoom_id(), roomWheatModel.getUser_id()));
        updateBottom();
        //开启声波监听
        RtcManager.getInstance().setSoundLevelMonitor(true);
        mIvMic.setImageResource(R.mipmap.room_ic_bottom_mic_off);
    }

    /**
     * 用户下麦
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(UserDownWheatEvent event) {
        mRoomInfoResp.getUser_info().setPit(0);
        updateBottom();
        //关闭声波监听
        RtcManager.getInstance().setSoundLevelMonitor(false);
        mIvMic.setImageResource(R.mipmap.room_ic_bottom_mic_off);
    }

    /**
     * 用户下麦
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomDownWheatModel event) {
        if (roomId.equals(event.getRoom_id()) && SpUtils.getUserId().equals(event.getUser_id())) {
            subscribeMessages(new UserDownWheatEvent());
        }
    }

    @Override
    public void onItemClick(XBanner banner, Object model, View view, int position) {
        BannerItem item = (BannerItem) model;
        if (item.getType() == 1) {//小猫钓鱼
            ARouter.getInstance().build(ARouteConstants.CAT_FISH_GAME).navigation();
        } else if (item.getType() == 2) {//球球大作战
            if (gameDialogFragment == null) {
                gameDialogFragment = GameDialogFragment.newInstance();
                if (!TextUtils.isEmpty(mRoomInfoResp.getUser_info().getBall())) {
                    gameDialogFragment.setBall(mRoomInfoResp.getUser_info().getBall());
                }
            }
            gameDialogFragment.setPitNumber(String.valueOf(mRoomInfoResp.getUser_info().getPit()));
            gameDialogFragment.setRoomId(roomId);
            if (!gameDialogFragment.isAdded()) {
                gameDialogFragment.show(getFragmentManager(), "mGameDialogFragment");
            }
//            GameDialogFragment.newInstance(roomId, String.valueOf(mRoomInfoResp.getUser_info().getPit())).show(getFragmentManager(), "mGameDialogFragment");
        } else if (item.getType() == 3) {   //福袋
            new EggGameDialogFragment().show(getFragmentManager());
//
        } else if (item.getType() == 4) {
            DialogFragment dialogFragment = (DialogFragment) ARouter.getInstance().build(ARouteConstants.BOSS_GAME).withString("url", item.getUrl()).navigation();
            dialogFragment.show(getChildFragmentManager(), "BossGameDialog");
        } else if (item.getType() == 21) {
            TGBRoomListFragment.newInstance().show(getChildFragmentManager());
        } else if (item.getType() == 22) {
            new TurntableDialogFragment().show(getChildFragmentManager());
        } else {
            if (!TextUtils.isEmpty(item.getUrl())) {
                ARouter.getInstance().build(ARouteConstants.H5).withString("url", item.getUrl()).navigation();
            }
        }
    }

    /**
     * emqtt重连成功
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reconnectSuccess(EmqttState state) {
        if (state == EmqttState.CONNECTED) {
            MvpPre.getRoomInfo(roomId);
            subscribeRoomRedPag();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showRoomInfo(UserInfoShowEvent event) {
        if (!OnClickUtils.isFastDoubleClick()) {
            if (userInfoDialogFragment != null) {
                userInfoDialogFragment.dismiss();
            }
            //如果点击的是自己
            if (SpUtils.getUserId().equals(event.userId)) {
                myInfoDialogFragment = MyInfoDialogFragment.newInstance(roomId);
                myInfoDialogFragment.show(getChildFragmentManager(), "MyInfoDialogFragment");
            } else {
                List<RoomPitBean> pit_list = mRoomInfoResp.getRoom_info().getPit_list();
                for (RoomPitBean roomPitBean : pit_list) {
                    if (event.userId.equals(roomPitBean.getUser_id())) {
                        userInfoDialogFragment = UserInfoDialogFragment.newInstance(roomId, event.userId, mRoomInfoResp.isManager(), roomPitBean.getShutup());
                        userInfoDialogFragment.show(getChildFragmentManager());
                        return;
                    }
                }
                userInfoDialogFragment = UserInfoDialogFragment.newInstance(roomId, event.userId, mRoomInfoResp.isManager(), "2");
                userInfoDialogFragment.show(getChildFragmentManager());
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showUserDetail(UserDetailShowEvent event) {
        ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", event.userId).navigation();
    }

    /**
     * 更新房间信息
     *
     * @param updateRoom
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateRoomInfo(UpdateRoom updateRoom) {
        MvpPre.roomUpdate(updateRoom.getMap(), updateRoom.getRoomId());
    }

    @Override
    public void switchMic(int type) {
        mRoomInfoResp.getUser_info().setVoice(type);
        if (type == 1) {
            mIvMic.setImageResource(R.mipmap.room_ic_bottom_mic_on);
            RtcManager.getInstance().muteLocalAudioStream(false);
        } else {
            RtcManager.getInstance().muteLocalAudioStream(true);
            mIvMic.setImageResource(R.mipmap.room_ic_bottom_mic_off);
        }
    }

    @Override
    public void userNewsSuccess(NewsModel newsModel) {
        int unreadMessageCount = EMClient.getInstance().chatManager().getUnreadMessageCount() + newsModel.getCount();
        LogUtils.e("onMessageReceived", "unreadMessageCount:" + unreadMessageCount);
        int count = newsModel.getCount();

        if (unreadMessageCount + count > 0) {
            mIvMessage.setBackgroundResource(R.drawable.room_message_red_point);
        } else {
            mIvMessage.setBackgroundResource(R.mipmap.room_ic_bottom_message);
        }
    }

    @Override
    public void setUserCount(String userCount) {
        mTvWheatQueue.setText(userCount);
    }

    @Override
    public void onResume() {
        super.onResume();
        MvpPre.userNews();
    }

    /**
     * 房间心动值开关变化通知 1开2关
     *
     * @param roomBeckoningEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomBeckoningEvent roomBeckoningEvent) {
        if (roomId.equals(roomBeckoningEvent.getRoomId())) {
            mRoomInfoResp.getRoom_info().setCardiac(roomBeckoningEvent.isOpen() ? 1 : 2);
        }
    }

    /**
     * 房间声音开放状态 true 开 false关
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomVoiceState roomVoiceState) {
        voiceState = roomVoiceState.isVoiceState();
    }

    /**
     * 调音台设置
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomMixEvent event) {
        mRoomInfoResp.getUser_info().setMixer(event.id);
    }

    /**
     * 修改房间公告
     *
     * @param roomNoticeModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomNoticeModel roomNoticeModel) {
        if (roomId.equals(roomNoticeModel.getRoom_id())) {
            mRoomInfoResp.getRoom_info().setPlaying(roomNoticeModel.getPlaying());
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void roomToneEvent(RoomToneEvent roomToneEvent) {
        if (!roomId.equals(roomToneEvent.getRoom_id())) {
            return;
        }
        RtcManager.getInstance().destroy(new RtcDestroyCallback() {
            @Override
            public void onDestroySuccess() {
                UserBean userBean = BaseApplication.getIns().getUser();
                RtcManager.getInstance().init(RtcConstants.RtcType_ZEGO, roomToneEvent.getId(), roomToneEvent.getConfig());
                RtcManager.getInstance().loginRoom(roomId, userBean.getUser_id(), userBean.getNickname(), "");
                if (mRoomInfoResp.getUser_info().getPit() != 0) {
                    RtcManager.getInstance().applyWheat(String.format("%s_%s", roomId, userBean.getUser_id()));
                }
                mRoomInfoResp.getRoom_info().setVoice_set(roomToneEvent.getId());
                mIvMic.setImageResource(R.mipmap.room_ic_bottom_mic_off);
            }
        });
    }

    /**
     * 特效设置
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setEffectSwitch(EffectEvent event) {
        if (event.isEffectOn()) {//特效开启
            if (!mGuardAnimView.animEnded) {
                mGuardAnimView.setVisibility(View.VISIBLE);
            }
            if (!mBigGiftAnimView.animEnded) {
                mBigGiftAnimView.setVisibility(View.VISIBLE);
            }
            if (!mGameAnimView.animEnded) {
                mGameAnimView.setVisibility(View.VISIBLE);
            }
            mSvgaGift.setVisibility(View.VISIBLE);
            mSvgaNobility.setVisibility(View.VISIBLE);
            mSvgaRide.setVisibility(View.VISIBLE);

        } else {
            mGuardAnimView.closeEffect();
            mBigGiftAnimView.closeEffect();
            mGameAnimView.closeEffect();
            mSvgaGift.closeEffect();
            mSvgaNobility.closeEffect();
            mSvgaRide.closeEffect();

            mGuardAnimView.setVisibility(View.GONE);
            mBigGiftAnimView.setVisibility(View.GONE);
            mGameAnimView.setVisibility(View.GONE);
            mSvgaGift.setVisibility(View.GONE);
            mSvgaNobility.setVisibility(View.GONE);
            mSvgaRide.setVisibility(View.GONE);
        }
    }

    /**
     * 以下部分为糖果包游戏
     */
    private void subscribeRoomRedPag() {
        String userId = SpUtils.getUserId();
        TGBManager.INSTANCE.setUserId(Integer.parseInt(userId));
        EMqttService.subscribe("sugarserver/login/room_config/" + userId);
        EMqttService.subscribe("sugarserver/login/get_room_list/" + userId);
        EMqttService.subscribe("sugarserver/room/broadcast/" + roomId);
        EMqttService.subscribe("sugarserver/room/join_room/" + userId);
        EMqttService.subscribe("sugarserver/room/game_rule/" + userId);
        EMqttService.subscribe("sugarserver/room/create_room/" + userId);
        EMqttService.subscribe("sugarserver/room/reconn_room/" + userId);
        EMqttService.subscribe("sugarserver/room/operate_room/" + userId);
        EMqttService.subscribe("sugarserver/room/open_red_parper/" + userId);
        EMqttService.subscribe("sugarserver/room/get_game_history/" + userId);
        EMqttService.subscribe("sugarserver/room/get_room_game_log/" + userId);
    }

    private void cleanSubscribeRoomRedPag() {
        String userId = SpUtils.getUserId();
        EMqttService.cleanSubscribe("sugarserver/login/room_config/" + userId);
        EMqttService.cleanSubscribe("sugarserver/login/get_room_list/" + userId);
        EMqttService.cleanSubscribe("sugarserver/room/broadcast/" + roomId);
        EMqttService.cleanSubscribe("sugarserver/room/join_room/" + userId);
        EMqttService.cleanSubscribe("sugarserver/room/game_rule/" + userId);
        EMqttService.cleanSubscribe("sugarserver/room/create_room/" + userId);
        EMqttService.cleanSubscribe("sugarserver/room/reconn_room/" + userId);
        EMqttService.cleanSubscribe("sugarserver/room/operate_room/" + userId);
        EMqttService.cleanSubscribe("sugarserver/room/open_red_parper/" + userId);
        EMqttService.cleanSubscribe("sugarserver/room/get_game_history/" + userId);
        EMqttService.cleanSubscribe("sugarserver/room/get_room_game_log/" + userId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getRoomList(SubscribeBigRoomTopicEvent event) {
        if (event.getSubscribe()) {
            subscribeRoomRedPag();
        } else {
            cleanSubscribeRoomRedPag();
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getRoomList(GetRoomListEvent getRoomListEvent) {
        GetRoomListReq getRoomListReq = new GetRoomListReq(getBaseReq(), Integer.parseInt(roomId));
        EMqttService.publish("sugar/login/get_room_list", GsonUtils.toJson(getRoomListReq));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getRoomConfig(GetRoomConfigEvent getRoomConfigEvent) {
        RoomConfReq roomConfReq = new RoomConfReq(getBaseReq());
        EMqttService.publish("sugar/login/room_config", GsonUtils.toJson(roomConfReq));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void createRoom(CreateRoomEvent createRoomEvent) {
        CreateRoomReq createRoomReq = new CreateRoomReq(getBaseReq(), Integer.parseInt(roomId), createRoomEvent.getSugar_id(), createRoomEvent.getPeople_num(), createRoomEvent.getRound_id(), createRoomEvent.getRule());
        EMqttService.publish("sugar/room/create_room", GsonUtils.toJson(createRoomReq));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void joinRoom(JoinRoomEvent joinRoomEvent) {
        JoinRoomReq joinRoomReq = new JoinRoomReq(getBaseReq(), Integer.parseInt(roomId), joinRoomEvent.getRoom_id());
        EMqttService.publish("sugar/room/join_room", GsonUtils.toJson(joinRoomReq));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeRoomTopic(SubscribeRoomTopicEvent subscribeRoomTopicEvent) {
        Log.e("subscribeRoomTopic", "subscribeRoomTopic");
        EMqttService.subscribe("sugarserver/room/broadcast/" + roomId + "/" + subscribeRoomTopicEvent.getRoom_id());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void unSubscribeRoomTopic(UnSubscribeRoomTopicEvent unSubscribeRoomTopicEvent) {
        EMqttService.cleanSubscribe("sugarserver/room/broadcast/" + roomId + "/" + unSubscribeRoomTopicEvent.getRoom_id());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reconnect(ReconnectEvent reconnectEvent) {
        ReconnRoomReq reconnRoomReq = new ReconnRoomReq(getBaseReq(), reconnectEvent.getRoom_id());
        EMqttService.publish("sugar/room/reconn_room", GsonUtils.toJson(reconnRoomReq));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getGameRulerEvent(GetGameRulerEvent getGameRulerEvent) {
        GameRuleReq gameRuleReq = new GameRuleReq(getBaseReq());
        EMqttService.publish("sugar/room/game_rule", GsonUtils.toJson(gameRuleReq));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void operateRoomEvent(OperateRoomEvent operateRoomEvent) {
        OperateRoomReq operateRoomReq = new OperateRoomReq(getBaseReq(), Integer.parseInt(roomId), operateRoomEvent.getRoom_id(), operateRoomEvent.getOperate_type());
        EMqttService.publish("sugar/room/operate_room", GsonUtils.toJson(operateRoomReq));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void openRedParperEvent(OpenRedParperEvent openRedParperEvent) {
        OpenRedParperReq openRedParperReq = new OpenRedParperReq(getBaseReq(), openRedParperEvent.getRoom_id());
        EMqttService.publish("sugar/room/open_red_parper", GsonUtils.toJson(openRedParperReq));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getRoomGameLogEvent(GetRoomGameLogEvent getRoomGameLogEvent) {
        GetRoomGameLogReq getRoomGameLogReq = new GetRoomGameLogReq(getBaseReq(), getRoomGameLogEvent.getRoom_id());
        EMqttService.publish("sugar/room/get_room_game_log", GsonUtils.toJson(getRoomGameLogReq));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getGameHistoryReq(GetGameHistoryEvent getGameHistoryEvent) {
        GetGameHistoryReq getGameHistoryReq = new GetGameHistoryReq(getBaseReq());
        EMqttService.publish("sugar/room/get_game_history", GsonUtils.toJson(getGameHistoryReq));
    }

    BaseReq getBaseReq() {
        return new BaseReq(Integer.parseInt(SpUtils.getUserId()), SpUtils.getToken());
    }

}
