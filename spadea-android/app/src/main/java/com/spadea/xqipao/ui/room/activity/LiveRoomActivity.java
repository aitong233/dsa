package com.spadea.xqipao.ui.room.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ThreadUtils;
import com.hyphenate.util.NetUtils;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.db.table.MusicTable;
import com.qpyy.room.event.OpenGuardEvent;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.ui.fragment0.roomdetail.reprot.ReportActivity;
import com.spadea.yuyin.ui.fragment2.setting.feedback.FeedBackActivity;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.oss.OSSOperUtils;
import com.spadea.yuyin.util.utilcode.BarUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.ApproachBean;
import com.spadea.xqipao.data.EmojiModel;
import com.spadea.xqipao.data.FaceBean;
import com.spadea.xqipao.data.FmApplyWheatResp;
import com.spadea.xqipao.data.GiftBean;
import com.spadea.xqipao.data.ProtectedItemBean;
import com.spadea.xqipao.data.RoomBean;
import com.spadea.xqipao.data.RoomBgBean;
import com.spadea.xqipao.data.RoomDetailBean;
import com.spadea.xqipao.data.RoomOwnerBean;
import com.spadea.xqipao.data.RoomPitBean;
import com.spadea.xqipao.data.RoomPollModel;
import com.spadea.xqipao.data.RoomUserBean;
import com.spadea.xqipao.data.SvgaModel;
import com.spadea.xqipao.data.even.EaseInitEvent;
import com.spadea.xqipao.data.even.EaseMesgEvent;
import com.spadea.xqipao.data.even.PlayMusicEvent;
import com.spadea.xqipao.data.even.RoomInputEvent;
import com.spadea.xqipao.data.even.RoomOutEvent;
import com.spadea.xqipao.data.even.RoomShowCatEvent;
import com.spadea.xqipao.data.socket.RoomApproachModel;
import com.spadea.xqipao.data.socket.RoomGiftModel;
import com.spadea.xqipao.data.socket.WeekStarInModel;
import com.spadea.xqipao.manager.ChatRoomEventListener;
import com.spadea.xqipao.manager.ChatRoomManager;
import com.spadea.xqipao.manager.emqtt.EmqttManager;
import com.spadea.xqipao.manager.emqtt.RoomEmqttEventListener;
import com.spadea.xqipao.ui.home.activity.HomeActivity;
import com.spadea.xqipao.ui.room.contacts.LiveRoomContacts;
import com.spadea.xqipao.ui.room.fragment.BaseRoomFragment;
import com.spadea.xqipao.ui.room.fragment.EmojDialogFragment;
import com.spadea.xqipao.ui.room.fragment.EmotionRoomFragment;
import com.spadea.xqipao.ui.room.fragment.GameDialogFragment;
import com.spadea.xqipao.ui.room.fragment.HoldingWheatFragment;
import com.spadea.xqipao.ui.room.fragment.MusicListFragment;
import com.spadea.xqipao.ui.room.fragment.OnlineFragment;
import com.spadea.xqipao.ui.room.fragment.RoomBackgroudDialogFragment;
import com.spadea.xqipao.ui.room.fragment.RoomFragmentListener;
import com.spadea.xqipao.ui.room.fragment.RoomGiftFragment;
import com.spadea.xqipao.ui.room.fragment.RoomGuardListDialogFragment;
import com.spadea.xqipao.ui.room.fragment.RoomHostListDialogFragment;
import com.spadea.xqipao.ui.room.fragment.RoomRankingFragment;
import com.spadea.xqipao.ui.room.fragment.RoomUserInfoDialogFragment;
import com.spadea.xqipao.ui.room.fragment.RoomUserPermissionDialogFragment;
import com.spadea.xqipao.ui.room.fragment.StationRoomFragment;
import com.spadea.xqipao.ui.room.fragment.WheatDialogFragment;
import com.spadea.xqipao.ui.room.fragment.WheatPositionDialogFragment;
import com.spadea.xqipao.ui.room.fragment.WheatToolDialogFragment;
import com.spadea.xqipao.ui.room.presenter.LiveRoomPresenter;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.utils.SPUtil;
import com.spadea.xqipao.utils.dialog.RoomPasswordDialog;
import com.spadea.xqipao.utils.dialog.RoomSettingPasswordDialog;
import com.spadea.xqipao.utils.dialog.TunerSheetDialog;
import com.spadea.xqipao.utils.dialog.room.OpenGuardDialog;
import com.spadea.xqipao.utils.dialog.room.RoomMoreDialog;
import com.spadea.xqipao.utils.dialog.room.RoomOpMoreDialog;
import com.spadea.xqipao.utils.dialog.room.RoomPlayingDialog;
import com.spadea.xqipao.utils.dialog.room.ShareRoomDialog;
import com.spadea.xqipao.utils.handler.HandlerUtil;
import com.spadea.xqipao.utils.popupwindow.RoomMorePopupWindow;
import com.spadea.xqipao.utils.view.room.MusicView;
import com.spadea.xqipao.utils.view.room.RoomHeadView;
import com.spadea.xqipao.utils.view.room.animation.ItemRoomGiftBean;
import com.spadea.xqipao.utils.view.room.animation.RoomGiftView;
import com.spadea.xqipao.utils.view.room.animation.SvgaAnimationView;
import com.spadea.xqipao.utils.view.room.approach.ApproachView;
import com.spadea.xqipao.widget.room.banner.GameBannerView;
import com.spadea.xqipao.widget.room.banner.GiftBannerView;
import com.qpyy.libcommon.event.LoginOutEvent;
import com.qpyy.room.event.NetEvent;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.live.activity.MusicSearchActivity;
import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;
import com.spadea.xqipao.ui.room.dialog.RoomInputDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.agora.rtc.Constants;

public class LiveRoomActivity extends BaseActivity<LiveRoomPresenter> implements LiveRoomContacts.View, RoomPasswordDialog.RoomPasswordListener, RoomHeadView.RoomHeadViewClickListener, RoomMoreDialog.RoomMoreListener
        , RoomFragmentListener, TunerSheetDialog.TunreOnIremClick, ChatRoomEventListener, RoomEmqttEventListener, EmojDialogFragment.EmojSelectListener, GiftBannerView.GiftBannerViewOnClickListener, RoomMorePopupWindow.RoomMorePopupClickListener,
        RoomSettingPasswordDialog.RoomPasswordListener {


    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.rv_roomheadview)
    RoomHeadView rvRoomheadview;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.svgaanimationview)
    SvgaAnimationView svgaanimationview;
    @BindView(R.id.roomgiftview)
    RoomGiftView roomgiftview;
    @BindView(R.id.iv_music)
    ImageView ivMusic;
    @BindView(R.id.rl_music_state)
    RelativeLayout rlMusicState;
    @BindView(R.id.musicview)
    MusicView musicview;
    @BindView(R.id.approachview)
    ApproachView approachview;
    @BindView(R.id.gift_banner_view)
    GiftBannerView giftBannerView;
    @BindView(R.id.fullserviceview)
    GameBannerView gameBannerView;

    private BaseRoomFragment baseRoomFragment;
    private EmojDialogFragment mEmojDialogFragment;
    private RoomSettingPasswordDialog roomSettingPasswordDialog;//设置房间密码
    private RoomBackgroudDialogFragment mRoomBackgroudDialogFragment;

    @Autowired(name = "roomId")
    public String mRoomId;
    public String mPassword;

    private RoomInputDialog mRoomInputDialog;
    private RoomMoreDialog mRoomMoreDialog;
    private ShareRoomDialog mShareRoomDialog;
    private RoomPasswordDialog mRoomPasswordDialog;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private GameDialogFragment mGameDialogFragment;
    private RoomOpMoreDialog mRoomOpMoreDialog;

    //*********************************************************//
    private int role = 3; //3普通用户  2 管理员  1房主
    private List<RoomPitBean> mPitList = new ArrayList<>();
    private boolean isPaly = false; //播放状态
    private Animation mAnimation;  //旋转动画
    private MusicTable mMusicTable;//当前播放音乐
    private RoomBean mRoomBean;  //房间信息
    private RoomOwnerBean mRoomOwnerBean; //房主信息
    private RoomUserBean mRoomUserBean;   //房间用户信息
    private ChatRoomManager mManager;
    private HoldingWheatFragment mHoldingWheatFragment;
    private EmqttManager emqttManager;
    private boolean isOnNewIntent;

    //*********************************************************//

    public static void start(Activity activity, String roomId) {
        ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", roomId).addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT).navigation();
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        String id = intent.getStringExtra("roomId");
        if (mRoomId.equals(id) || TextUtils.isEmpty(id)) {
            super.onNewIntent(intent);
        } else {
            isOnNewIntent = true;
            releaseRoom();
            startActivity(intent);
            LiveRoomActivity.this.finish();
        }
    }


    @Override
    protected void initView() {
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        EventBus.getDefault().register(this);
        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setAndroidNativeLightStatusBar(this, false);
        mRoomId = getIntent().getStringExtra("roomId");
    }

    @Override
    protected void initData() {
        MvpPre.roomGetIn(mRoomId, "");
    }

    public LiveRoomActivity() {
        super(R.layout.activity_live);
    }

    @Override
    protected void setListener() {
        rvRoomheadview.setmRoomHeadViewClickListener(this);
        giftBannerView.setmGiftBannerViewOnClickListener(this);
        musicview.addOnItemClickListener(new MusicView.OnItemClickListener() {
            @Override
            public void minimize() {
                musicview.setVisibility(View.GONE);
                rlMusicState.setVisibility(View.VISIBLE);
            }

            @Override
            public void openMusicList() {
                musicview.setVisibility(View.GONE);
                rlMusicState.setVisibility(View.VISIBLE);
                MusicListFragment.newInstance(mMusicTable == null ? -1 : mMusicTable.getSongid()).show(getSupportFragmentManager());
            }

            @Override
            public void palyMusic(MusicTable musicTable) {
                mMusicTable = musicTable;
                mManager.getRtcManager().startAudioMixing(musicTable.getUrl());

            }

            @Override
            public void stopPlay() {
                mManager.getRtcManager().pauseAudioMixing();
            }

            @Override
            public void setMmusicvolume(int progress) {
                SPUtil.saveInt(Constant.Channel.VOLUME, progress);
                mManager.getRtcManager().adjustAudioMixingVolume(progress);
            }
        });
    }

    @Override
    protected LiveRoomPresenter bindPresenter() {
        return new LiveRoomPresenter(this, this);
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }


    @Override
    public void roomAuthSuccess() {
        if (mRoomPasswordDialog == null) {
            mRoomPasswordDialog = new RoomPasswordDialog(this);
            mRoomPasswordDialog.addRoomPasswordListener(this);
        }
        mRoomPasswordDialog.show();
    }

    @Override
    public void setRoomData(RoomDetailBean data, boolean b) {
        initializeAgoraEngine(mRoomId);
        this.mRoomBean = data.getRoom_info();
        this.mRoomOwnerBean = data.getOwner_info();
        this.mRoomUserBean = data.getUser_info();
        this.role = mRoomBean.getRole();
        this.mPitList = mRoomBean.getPit_list();
        if (b) {
            setUpViewPager();
        }
        //如果用户不在麦位，关闭麦克风
        if (data.getUser_info() == null || data.getUser_info().getPit() == 0) {
            try {
                mManager.muteMic(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (data.getUser_info() != null && data.getUser_info().getPit() != 0) {
            if (mManager != null) {
                mManager.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
                mManager.muteMic(true);
            }
        }
        if (baseRoomFragment == null || !baseRoomFragment.isAdded()) {
            MvpPre.quitRoom(mRoomId);
            return;
        }
        //设置房间基本信息
        rvRoomheadview.setVisibility(View.VISIBLE);
        rvRoomheadview.setRoomeId(mRoomBean.getRoom_code());
        rvRoomheadview.setRoomePopularity(mRoomBean.getPopularity());
        rvRoomheadview.setRoomeType(mRoomBean.getLabel_name());
        rvRoomheadview.setRoomLock(mRoomBean.getIs_password());
        rvRoomheadview.setRoomName(mRoomBean.getRoom_name());
        rvRoomheadview.setRoomFavorite(mRoomUserBean.getFavorite());
        if (baseRoomFragment instanceof StationRoomFragment) {
            ((StationRoomFragment) baseRoomFragment).setPlaying(mRoomBean.getPlaying());
        }
        int showCat = 0;
        if (mRoomUserBean != null) {
            showCat = mRoomUserBean.getShow_cat();
        }
        baseRoomFragment.setShowCat(showCat);
        baseRoomFragment.setWheatData(mRoomBean.getPit_list());
        baseRoomFragment.showCardiac(mRoomBean.getCardiac());
        baseRoomFragment.setContribution(mRoomBean.getContribution());
        baseRoomFragment.setUserData(mRoomUserBean.getPit());
        baseRoomFragment.setMaiXu(mRoomBean.getWheat());
        baseRoomFragment.setMaiXuCount(mRoomBean.getApply_count());
        baseRoomFragment.showMore(role == 1 || (role == 2 && mRoomUserBean.getPit() == 9) || mRoomUserBean.getPit() != 0);
        baseRoomFragment.showView();
        if (b) {
            baseRoomFragment.addChatRoom(mRoomBean.getChatrooms(), role);
            initializeRoomSocket();
        }
        setUpBg();
        MyApplication.getInstance().isPlaying = true;
        MyApplication.getInstance().playId = mRoomId;
        MyApplication.getInstance().playName = mRoomBean.getRoom_name();
        MyApplication.getInstance().playCover = mRoomOwnerBean.getHead_picture();

    }

    private void setUpBg() {
        if (TextUtils.isEmpty(mRoomBean.getBg_picture())) {
            ImageLoader.loadImageBlurBg(this, mRoomOwnerBean.getHead_picture(), ivBg);
        } else {
            ImageLoader.loadRoomBg(this, ivBg, mRoomBean.getBg_picture());
        }
    }

    private void setUpViewPager() {
        mFragmentList.add(OnlineFragment.newInstance(mRoomId));
        if (mRoomBean != null && mRoomBean.getIs_fm() == 1) {
            mFragmentList.add(baseRoomFragment = StationRoomFragment.newInstance());
        } else {
            mFragmentList.add(baseRoomFragment = EmotionRoomFragment.newInstance());
        }
        mFragmentList.add(RoomRankingFragment.newInstance(mRoomId));
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(mFragmentList, getSupportFragmentManager());
        viewpager.setAdapter(myFragmentPagerAdapter);
        viewpager.setCurrentItem(1);
    }

    /**
     * 声网初始化
     *
     * @param roomId
     */
    private void initializeAgoraEngine(String roomId) {
        mManager = ChatRoomManager.instance();
        mManager.setListener(this);
        mManager.joinChannel(roomId);
    }

    private void initializeRoomSocket() {
        emqttManager = EmqttManager.instance(mRoomId);
        emqttManager.onStart();
        emqttManager.setRoomSocketEventListener(this);
        emqttManager.subscribe();
    }

    /**
     * 获取房间信息失败
     */
    @Override
    public void roomEnterFail() {
        releaseRoom();
        this.mRoomId = "";
        finish();
    }

    @Override
    public void removeFavoriteSuccess() {
        ToastUtils.showShort("取消收藏成功");
        if (rvRoomheadview != null) {
            rvRoomheadview.setRoomFavorite(0);
        }
    }

    @Override
    public void addRoomCollectSuccess() {
        ToastUtils.showShort("添加收藏成功");
        if (rvRoomheadview != null) {
            rvRoomheadview.setRoomFavorite(1);
        }
    }


    /**
     * 禁麦成功
     *
     * @param type
     * @param userName
     */
    @Override
    public void shutUp(String type, String userName, String userId) {
        if (baseRoomFragment != null)
            baseRoomFragment.sendOperationMessage(userName + (type.equals("1") ? "被禁麦" : "取消禁麦"));
    }


    /**
     * 抱人上麦发送socket
     *
     * @param userId
     */
    @Override
    public void getOnWheat(String userId) {
        if (emqttManager != null) {
            emqttManager.getUserOnWheat(userId);
        }
    }


    @Override
    public void sendStartGame(String text) {
        baseRoomFragment.sendActionMessage(text);
        if (mRoomUserBean.getPit() != 0 && baseRoomFragment != null) {
            baseRoomFragment.showGame(String.valueOf(mRoomUserBean.getPit()));
            baseRoomFragment.sendStartGameCmdMsg(String.valueOf(mRoomUserBean.getPit()));
        }
    }

    @Override
    public void sendOverGame(String text) {
        baseRoomFragment.sendActionMessage(text);
        if (mRoomUserBean.getPit() != 0 && baseRoomFragment != null) {
            baseRoomFragment.overGame(String.valueOf(mRoomUserBean.getPit()));
            baseRoomFragment.sendOverGameCmdMsg(String.valueOf(mRoomUserBean.getPit()));
        }
    }

    @Override
    public void sendGameData(String content, String qiu1, String qiu2, String qiu3) {
        if (mRoomUserBean.getPit() != 0 && baseRoomFragment != null) {
            baseRoomFragment.openGame(String.valueOf(mRoomUserBean.getPit()), qiu1, qiu2, qiu3);
            baseRoomFragment.sendOpenGameQiuMsg(String.valueOf(mRoomUserBean.getPit()), content, qiu1, qiu2, qiu3);
        }
    }

    /**
     * 申请上麦成功    主持位没人的时候直接可以上麦
     *
     * @param state
     * @param pitNumber
     */
    @Override
    public void applyWheatWaitSuccess(String state, String pitNumber) {
        if (!state.equals("1")) {
            ToastUtils.showShort("申请成功");
        }
    }

    /**
     * 退出房间成功
     */
    @Override
    public void quitRoomSuccess() {
        releaseRoom();
        this.mRoomId = "";
//        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    private void releaseRoom() {
        if (mManager != null) {
            mManager.leaveChannel();
        }
        if (baseRoomFragment != null) {
            baseRoomFragment.leaveChatRoom();
        }
        if (emqttManager != null) {
            emqttManager.cleanSubscribe();
        }
        MyApplication.getInstance().isShow = false;
        MyApplication.getInstance().isPlaying = false;
    }

//    /**
//     * 抱下麦成功
//     *
//     * @param userName
//     * @param pitNumber
//     */
//    @Override
//    public void downUserWheat(String userName, String pitNumber) {
//        if (baseRoomFragment != null)
//            baseRoomFragment.sendOperationMessage(userName + "下了" + (pitNumber.equals("9") ? "主持" : pitNumber + "号") + "麦");
//    }

    /**
     * 添加禁言成功
     *
     * @param userId
     */
    @Override
    public void addRoomBanned(String userId, String userName) {
        if (baseRoomFragment != null)
            baseRoomFragment.sendOperationMessage(userName + "被禁言");
    }

    /**
     * 取消禁言
     *
     * @param userId
     * @param userName
     */
    @Override
    public void cancelRoomBanned(String userId, String userName) {
        if (baseRoomFragment != null)
            baseRoomFragment.sendOperationMessage(userName + "取消禁言");
    }

    /**
     * 输入密码回调
     *
     * @param password
     */
    @Override
    public void setRoomPassword(String password) {
        this.mPassword = password;
        MvpPre.roomGetIn(mRoomId, password);
    }

    /**
     * 取消输入密码
     */
    @Override
    public void onCancel() {
        finish();
    }


    /**
     * 收藏房间
     *
     * @param favorite
     */
    @Override
    public void onCollection(int favorite) {
        if (favorite == 1) {
            MvpPre.addRoomCollect(mRoomId);
        } else {
            MvpPre.removeFavorite(mRoomId);
        }
    }

    /**
     * 显示更多
     */
    @Override
    public void onMore() {
        if (mRoomMoreDialog == null) {
            mRoomMoreDialog = new RoomMoreDialog(this);
            mRoomMoreDialog.setmRoomMoreListener(this);
        }
        mRoomMoreDialog.show();
    }

    /**
     * 返回
     */
    @Override
    public void back() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        MyApplication.getInstance().isShow = false;
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    /**
     * 举报房间
     */
    @Override
    public void roomReport() {
        Intent intent = new Intent(this, ReportActivity.class);
        intent.putExtra("id", mRoomId);
        startActivityForResult(intent, 0);
    }

    /**
     * 退出房间
     */
    @Override
    public void roomExit() {
        MvpPre.quitRoom(mRoomId);
    }

    /**
     * 意见反馈
     */
    @Override
    public void roomSetting() {
        startActivityForResult(new Intent(this, FeedBackActivity.class), 0);
    }

    /**
     * 邀请好友
     */
    @Override
    public void roomInvitation() {
        if (mShareRoomDialog == null) {
            mShareRoomDialog = new ShareRoomDialog(this);
        }
        mShareRoomDialog.show();
    }

    /**
     * 分享房间
     */
    @Override
    public void roomShare() {
        if (mShareRoomDialog == null) {
            mShareRoomDialog = new ShareRoomDialog(this);
        }
        mShareRoomDialog.show();
    }


    @Override
    public void givingGifts(String userId) {
        RoomGiftFragment roomGiftFragment = RoomGiftFragment.newInstance(userId, mRoomId);
        roomGiftFragment.show(getSupportFragmentManager());
    }

    @Override
    public String getUserId() {
        return MyApplication.getInstance().getUser().getUser_id();
    }

    /**
     * 发送送礼物消息
     *
     * @param userName
     * @param giftId
     * @param giftPic
     * @param giftName
     * @param giftPrice
     * @param giftSpectial
     * @param giftMum
     * @param pits
     */
    @Override
    public void sendGiftMessage(String userName, String giftId, String giftPic, String giftName, String giftPrice, String giftSpectial, String giftMum, String pits) {
        if (baseRoomFragment != null)
            baseRoomFragment.sendGiftMessage(userName, giftId, giftPic, giftName, giftPrice, giftSpectial, giftMum, pits);
    }

    /**
     * 查看麦序
     */
    @Override
    public void rowWheat() {
        if (mRoomUserBean.getPit() == 9 || role == 1) {
            //管理麦序
            WheatPositionDialogFragment wheatPositionDialogFragment = WheatPositionDialogFragment.newInstance(mRoomId);
            wheatPositionDialogFragment.show(getSupportFragmentManager());
        } else {
            //查看麦序人数
            WheatDialogFragment wheatDialogFragment = WheatDialogFragment.newInstance(mRoomId);
            wheatDialogFragment.show(getSupportFragmentManager());
        }
    }

    @Override
    protected void onDestroy() {
        if (mRoomInputDialog != null) {
            mRoomInputDialog.release();
        }
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    @Override
    public void showRoomMore() {
        if (mRoomOpMoreDialog == null) {
            mRoomOpMoreDialog = new RoomOpMoreDialog(this);
            mRoomOpMoreDialog.setRoomMorePopupClickListener(this);
        }
        if (!mRoomOpMoreDialog.isShowing()) {
            mRoomOpMoreDialog.setData(role == 2 || role == 1, mRoomUserBean.getPit() != 0);
            mRoomOpMoreDialog.show();
        }
    }

    /**
     * 获取用户信息弹窗
     *
     * @param userId
     */
    @Override
    public void getRoomUserInfo(String userId) {
        if (!userId.equals(MyApplication.getInstance().getUser().getUser_id()) && (role == 1 || mRoomUserBean.getPit() == 9)) {
            RoomUserPermissionDialogFragment.newInstance(mRoomId, userId).show(getSupportFragmentManager());
        } else {
            RoomUserInfoDialogFragment.newInstance(mRoomId, userId, false).show(getSupportFragmentManager());
        }
    }

    @Override
    public void guardListClick() {
        boolean isHost = false;
        if (mPitList != null && mPitList.size() > 0) {
            isHost = MyApplication.getInstance().getUser().getUser_id().equals(mPitList.get(mPitList.size() - 1).getUser_id());
        }
        RoomGuardListDialogFragment.newInstance(mRoomId, isHost).show(getSupportFragmentManager());
    }

    @Override
    public void hostListClick() {
        RoomHostListDialogFragment.newInstance(mRoomId).show(getSupportFragmentManager());
    }

    @Override
    public void playingClick() {
        new RoomPlayingDialog(this, mRoomBean.getPlaying()).show();
    }


    /**
     * 显示调音台
     */
    private TunerSheetDialog mTunerSheetDialog;


    /**
     * 弹窗
     */
    @Override
    public void sendMesg() {
        if (mRoomUserBean.getBanned() == 2) {
            if (mRoomInputDialog == null) {
                mRoomInputDialog = new RoomInputDialog(this, "");
            }
            mRoomInputDialog.show();
        } else {
            ToastUtils.showShort("您已经被禁言");
        }
    }

    /**
     * 发送表情
     */
    @Override
    public void onEmoj() {
        if (mEmojDialogFragment == null) {
            mEmojDialogFragment = EmojDialogFragment.newInstance();
            mEmojDialogFragment.setEmojSelectListener(this);
        }
        if (!mEmojDialogFragment.isAdded()) {
            mEmojDialogFragment.show(getSupportFragmentManager());
        }
    }

    /**
     * 上麦
     */
    @Override
    public void applyWheat(String pitNumber) {
        MvpPre.applyWheat(mRoomId, pitNumber);
    }

    /**
     * 申请上麦
     *
     * @param pitNumber
     */
    @Override
    public void applyWheatWait(String pitNumber) {
        MvpPre.applyWheatWait(mRoomId, pitNumber);
    }

    /**
     * 下麦
     */
    @Override
    public void downWheat() {
        MvpPre.downWheat(mRoomId);
    }


    /**
     * 设备静音
     *
     * @param b
     */
    @Override
    public void muteAllRemoteAudioStreams(boolean b) {
        if (mManager != null)
            mManager.getRtcManager().muteAllRemoteAudioStreams(b);
    }

    /**
     * 静音
     */
    @Override
    public void closedWheat() {
        if (mRoomUserBean != null && mRoomUserBean.getShutup() == 2) {
            if (mRoomUserBean.getVoice() == 0) {
                mManager.muteMic(false);
                mRoomUserBean.setVoice(1);
                baseRoomFragment.setWheatState(1);
            } else {
                mManager.muteMic(true);
                mRoomUserBean.setVoice(0);
                baseRoomFragment.setWheatState(0);
            }
        } else {
            ToastUtils.showShort("您已经被禁麦");
        }
    }


    /**
     * 没人麦位操作
     *
     * @param pitNumber
     */
    @Override
    public void showRoomWheat(String pitNumber) {
        if (role == 1 || (role == 2 && mRoomUserBean.getPit() == 9)) {
            WheatToolDialogFragment.newInstance(mRoomId, pitNumber).show(getSupportFragmentManager());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void roomApproach(RoomApproachModel roomApproachModel) {
        if (roomApproachModel != null && mRoomId.equals(roomApproachModel.getRoom_id()) && roomApproachModel.getRide() != null) {
            if (svgaanimationview != null) {
                svgaanimationview.load(new SvgaModel(roomApproachModel.getNobility_url(), SvgaModel.TYPE_JUE, roomApproachModel.getRide(), roomApproachModel.getNickname(), roomApproachModel.getNobility_name()));
            }
        }
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void roomBgPreview(RoomBgBean event) {
        if (TextUtils.isEmpty(event.getPicture())) {
            if (TextUtils.isEmpty(event.getId())) {
                setUpBg();
            } else {
                ImageLoader.loadImageBlurBg(this, MyApplication.getInstance().getUser().getHead_picture(), ivBg);
            }
        } else {
            ImageLoader.loadRoomBg(this, ivBg, event.getPicture());
        }
    }





    /**
     * 环信初始化成功
     *
     * @param easeInitEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void easeInit(EaseInitEvent easeInitEvent) {
        MyApplication.getInstance().isShow = true;
        if (baseRoomFragment != null) {
            baseRoomFragment.sendOfficialNoticeMessage(mRoomBean.getOfficial_notice());
            if (!TextUtils.isEmpty(mRoomBean.getGreeting())) {
                baseRoomFragment.sendGreetingMessage(mRoomBean.getGreeting());
            }
            baseRoomFragment.sendWelcomeMessage(MyApplication.getInstance().getUser().getNickname());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void roomShowCat(RoomShowCatEvent event) {
        if (baseRoomFragment != null) {
            baseRoomFragment.setShowCat(event.showCat);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void roomInputSend(RoomInputEvent event) {
        MvpPre.filterMessage(event.text);
    }


    /**
     * 音调
     *
     * @param type
     */
    @Override
    public void setLocalVoiceChanger(int type) {
        if (mManager != null)
            mManager.setLocalVoiceChanger(type);
    }

    /**
     * 演唱会
     *
     * @param type
     */
    @Override
    public void setLocalVoiceReverbPreset(int type) {
        if (mManager != null)
            mManager.setLocalVoiceReverbPreset(Constants.AUDIO_REVERB_OFF);
    }

    /**
     * 原声
     */
    @Override
    public void setClodeLocalVoice() {
        if (mManager != null)
            mManager.setClodeLocalVoice();
    }

    /**
     * 开启耳返
     *
     * @param b
     */
    @Override
    public void enableInEarMonitoring(boolean b) {
        if (mManager != null)
            mManager.enableInEarMonitoring(b);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receivedWheatGift(GiftBean giftBean) {
        if (mRoomId != null && !mRoomId.equals(giftBean.getRoom_id())) {
            return;
        }
        String[] split = giftBean.getPits().split(",");
        for (String str : split) {
            try {
                baseRoomFragment.showGift(Integer.parseInt(str), giftBean.getPicture());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void easeMesgEvent(EaseMesgEvent easeMesgEvent) {
        getRoomUserInfo(easeMesgEvent.getUserId());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void palyMusic(MusicTable musicTable) {
        musicview.setData(musicTable);
        this.mMusicTable = musicTable;
        if (mManager != null) {
            mManager.getRtcManager().startAudioMixing(musicTable.getUrl());
        }
    }

    /**
     * 网络变化监听
     *
     * @param netEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void netEvent(NetEvent netEvent) {
        if (netEvent.getType() == NetUtils.Types.NONE) {
            ToastUtils.showShort("网络已断开");
        } else {
            ToastUtils.showShort("网络已连接");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginOut(LoginOutEvent loginOutEvent) {
        releaseRoom();
        this.finish();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void roomOut(RoomOutEvent roomOutEvent) {
        MvpPre.quitRoom(mRoomId);
    }

    @OnClick({R.id.rl_music_state})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_music_state:
                if (MvpPre.getLocalMusicCount() == 0) {
                    startActivityForResult(new Intent(this, MusicSearchActivity.class), 0);
                } else {
                    List<MusicTable> lovalMusicData = MvpPre.getLovalMusicData();
                    if (mMusicTable == null) {
                        mMusicTable = lovalMusicData.get(0);
                    }
                    musicview.setMusicList(lovalMusicData);
                    musicview.setPalyState(isPaly);
                    musicview.setData(mMusicTable);
                    musicview.setVisibility(View.VISIBLE);
                    rlMusicState.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        LogUtils.i("LiveRoomLife", "onResume");
        super.onResume();
        MyApplication.getInstance().isShow = true;
        try {
            roomgiftview.initQueue();
            giftBannerView.initQueue();
            svgaanimationview.initQueue();
            gameBannerView.initQueue();
            approachview.initQueue();
        } catch (Exception e) {
            LogUtils.e("onResume", e);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        LogUtils.i("LiveRoomLife", "onStop");
        super.onStop();
        if (isOnNewIntent) {
            LogUtils.i("LiveRoomLife", "不清空队列");
        } else {
            try {
                roomgiftview.emptyQueue();
                giftBannerView.emptyQueue();
                svgaanimationview.emptyQueue();
                gameBannerView.emptyQueue();
                approachview.emptyQueue();
            } catch (Exception e) {
                LogUtils.e("onStop", e);
            }
        }
        isOnNewIntent = false;
    }

    @Override
    public void startQiuGame() {
        if (mGameDialogFragment == null) {
            mGameDialogFragment = GameDialogFragment.newInstance();
        }
        if (!mGameDialogFragment.isAdded()) {
            mGameDialogFragment.show(getSupportFragmentManager(), "0");
        }
    }


    @Override
    public void operationWheat() {
        if (mRoomUserBean.getPit() == 0) {
            if (mRoomBean.getWheat().equals("1")) {
                MvpPre.applyWheat(mRoomId, "");
            } else {
                MvpPre.applyWheatWait(mRoomId, "");
            }
        } else {
            MvpPre.downWheat(mRoomId);
        }
    }

    @Override
    public void addWheat(boolean isHostWheat, String pitNumber) {
        if (mRoomUserBean.getPit() == 0) {
            if (mRoomBean.getIs_fm() == 1) {
                MvpPre.applyWheatFm(mRoomId, pitNumber);
            } else {
                if (mRoomBean.getWheat().equals("1")) {
                    MvpPre.applyWheat(mRoomId, pitNumber);
                } else {
                    MvpPre.applyWheatWait(mRoomId, pitNumber);
                }
            }
        } else {
            if (role == 1 || (role == 2 && mRoomUserBean.getPit() == 9)) {
                WheatToolDialogFragment.newInstance(mRoomId, pitNumber).show(getSupportFragmentManager());
            } else {
                if (mRoomBean.getIs_fm() == 1) {
                    MvpPre.applyWheatFm(mRoomId, pitNumber);
                } else {
                    if (mRoomBean.getWheat().equals("1")) {
                        MvpPre.applyWheat(mRoomId, pitNumber);
                    } else {
                        MvpPre.applyWheatWait(mRoomId, pitNumber);
                    }
                }
            }
        }
    }

    @Override
    public void pitCountDown(String pitNumber, String time) {
        MvpPre.pitCountDown(mRoomId, pitNumber, time);
    }


    @Override
    public void onAudioMixingStateChanged(int state) {
        switch (state) {
            case Constants.MEDIA_ENGINE_AUDIO_EVENT_MIXING_PLAY:
                EventBus.getDefault().post(new PlayMusicEvent(mMusicTable));
                isPaly = true;
                if (musicview != null) {
                    musicview.setPalyState(isPaly);
                    ivMusic.startAnimation(mAnimation);
                }
                break;
            case Constants.MEDIA_ENGINE_AUDIO_EVENT_MIXING_PAUSED:
                isPaly = false;
                if (musicview != null) {
                    musicview.setPalyState(isPaly);
                    ivMusic.clearAnimation();
                }
                break;
            case Constants.MEDIA_ENGINE_AUDIO_EVENT_MIXING_STOPPED:
                isPaly = false;
                if (musicview != null) {
                    musicview.setPalyState(isPaly);
                    ivMusic.clearAnimation();
                    if (mRoomUserBean.getPit() != 0) {
                        int playPattern = SPUtil.getInt("playPattern", 1);
                        if (playPattern == 1) {
                            musicview.next();
                        } else {
                            if (mMusicTable != null) {
                                mManager.getRtcManager().startAudioMixing(mMusicTable.getUrl());
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onAudioVolumeIndication(String userId, int volume) {
        if (mRoomUserBean.getPit() != 0 && userId.equals("0")) {
            if (volume == 0) {
                baseRoomFragment.showVolumeTips(String.valueOf(mRoomUserBean.getPit()), false);
            } else {
                baseRoomFragment.showVolumeTips(String.valueOf(mRoomUserBean.getPit()), true);
            }
        } else {
            for (RoomPitBean item : mPitList) {
                if (!TextUtils.isEmpty(item.getUser_id()) && item.getUser_id().equals(userId)) {
                    if (volume == 0) {
                        baseRoomFragment.showVolumeTips(item.getPit_number(), false);
                    } else {
                        baseRoomFragment.showVolumeTips(item.getPit_number(), true);
                    }
                }
            }
        }
    }

    /**
     * emqtt重连成功
     */
    @Override
    public void reconnectSuccess() {
        if (emqttManager != null) {
            emqttManager.subscribe();
        }
        if (!isFinishing()) {
            MvpPre.getInRoomInfo(mRoomId);
        }
    }


    @Override
    public void roomApplyWheat(List<RoomPitBean> data) {
        this.mPitList = data;
        if (baseRoomFragment != null)
            baseRoomFragment.setWheatData(data);
    }


    @Override
    public void roomDeleteCardiac(String pitNumber) {
        if (baseRoomFragment != null)
            baseRoomFragment.roomDeleteCardiac(pitNumber);
    }

    @Override
    public void roomDeleteAllCardiac() {
        if (baseRoomFragment != null) {
            baseRoomFragment.roomDeleteAllCardiac();
        }
    }

    @Override
    public void roomUserPit(String pitNumber) {
        upperWheatOperation(pitNumber);
    }

    @Override
    public void roomBanned(String action) {
        if (mRoomUserBean != null) {
            mRoomUserBean.setBanned(Integer.parseInt(action));
        }
    }

    /**
     * 禁麦
     *
     * @param action
     */
    @Override
    public void roomShutup(String action, String pitNumber) {
        if (mManager != null) {
            mManager.muteMic(true);
        }
        if (mRoomUserBean != null) {
            mRoomUserBean.setShutup(Integer.parseInt(action));
            mRoomUserBean.setVoice(action.equals("1") ? 1 : 0);
        }
        if (baseRoomFragment != null) {
            baseRoomFragment.setWheatState(0);
        }
    }

    @Override
    public void roomClosePit(String pitNumber, String action) {
        if (baseRoomFragment != null) {
            baseRoomFragment.roomClosePit(pitNumber, action);
        }
    }

    /**
     * //3普通用户  2 管理员  1房主
     */
    @Override
    public void roomAddManager() {
        this.role = 2;
        if (baseRoomFragment != null) {
            baseRoomFragment.showMore(role == 1 || (role == 2 && mRoomUserBean.getPit() == 9) || mRoomUserBean.getPit() != 0);
        }
    }

    @Override
    public void roomDeleteManager() {
        this.role = 3;
        if (baseRoomFragment != null) {
            baseRoomFragment.showMore(role == 1 || (role == 2 && mRoomUserBean.getPit() == 9) || mRoomUserBean.getPit() != 0);
        }
    }

    @Override
    public void roomPassword(String action) {
        this.mRoomBean.setIs_password(Integer.parseInt(action));
        if (rvRoomheadview != null) {
            rvRoomheadview.setRoomLock(mRoomBean.getIs_password());
        }
    }

    @Override
    public void roomCardiacSwitch(String action) {
        this.mRoomBean.setCardiac(Integer.parseInt(action));
        if (baseRoomFragment != null) {
            baseRoomFragment.showCardiac(mRoomBean.getCardiac());
        }
    }


    @Override
    public void roomMaiXu(String action) {
        if (mRoomBean != null) {
            mRoomBean.setWheat(action);
        }
        if (baseRoomFragment != null) {
            baseRoomFragment.setMaiXu(mRoomBean.getWheat());
        }
    }

    /**
     * 上麦
     *
     * @param pit_number
     */
    @Override
    public void roomUpWheat(String pit_number) {
        upperWheatOperation(pit_number);
    }

    /**
     * 下麦
     *
     * @param pit_number
     */
    @Override
    public void roomDownWheat(String pit_number) {
        downWheatOperation(pit_number);
    }

    @Override
    public void roomApplyCount(String count) {
        if (baseRoomFragment != null)
            baseRoomFragment.setMaiXuCount(count);
    }

    /**
     * 踢出房间
     *
     * @param action
     */
    @Override
    public void roomKickOut(int action) {
        if (action == 2) {
            ToastUtils.showShort("您已被踢出房间");
            if (baseRoomFragment != null) {
                baseRoomFragment.sendOperationMessage(MyApplication.getInstance().getUser().getNickname() + "被踢出房间");
            }
        }
        releaseRoom();
        finish();
    }

    /**
     * 抱人上麦
     */
    @Override
    public void roomGetUserOnWheat() {
        if (mHoldingWheatFragment == null) {
            mHoldingWheatFragment = HoldingWheatFragment.newInstance(mRoomId);
        }
        if (!mHoldingWheatFragment.isAdded()) {
            mHoldingWheatFragment.show(getSupportFragmentManager());
        }
    }

    @Override
    public void roomFace(FaceBean message) {
        if (baseRoomFragment != null)
            baseRoomFragment.setExpression(message);
    }

    @Override
    public void roomJueWeiIn(ApproachBean approachBean) {
        ThreadUtils.executeByCached(new ThreadUtils.SimpleTask<String>() {
            @Nullable
            @Override
            public String doInBackground() throws Throwable {
                return OSSOperUtils.newInstance().fileExist(String.format("nobility/%s.svga", approachBean.getNobilityId()));
            }

            @Override
            public void onSuccess(@Nullable String result) {
                if (result == null) {
                    if (approachview != null) {
                        approachview.loadGift(approachBean);
                    }
                } else {
                    if (approachBean.getDisplay() == 1 && svgaanimationview != null) {
                        svgaanimationview.load(new SvgaModel(result, SvgaModel.TYPE_JUE, null, approachBean.getUserName(), approachBean.getNobilityName()));
                    }
                }
            }
        });
    }

    @Override
    public void roomGofis(String msg) {
        if (gameBannerView != null) {
            gameBannerView.load(msg);
        }
    }

    @Override
    public void roomGift(RoomGiftModel roomGiftModel) {
        if (giftBannerView != null) {
            giftBannerView.load(roomGiftModel);
        }
    }

    @Override
    public void roomGiftGive(ItemRoomGiftBean data) {
        if (roomgiftview != null) {
            roomgiftview.loadGift(data);
        }
    }

    @Override
    public void roomGiftShow(String special) {
        if (svgaanimationview != null) {
            svgaanimationview.load(special);
        }
    }

    @Override
    public void roomContribution(String contribution) {
        if (baseRoomFragment != null) {
            baseRoomFragment.setContribution(contribution);
        }
    }

    @Override
    public void roomCardiac(String pit_number, String rough_number) {
        if (baseRoomFragment != null) {
            baseRoomFragment.setWheatCardiac(pit_number, rough_number);
        }
    }

    /**
     * 房间名称
     *
     * @param roomName
     */
    @Override
    public void roomName(String roomName) {
        if (rvRoomheadview != null) {
            rvRoomheadview.setRoomName(roomName);
        }
    }

    /**
     * 清理公屏
     */
    @Override
    public void roomPublicScreen() {
        if (baseRoomFragment != null) {
            baseRoomFragment.clearPublic();
        }
    }

    @Override
    public void roomUserShutup(String pit_number, String action) {
        if (baseRoomFragment != null) {
            baseRoomFragment.roomShutup(pit_number, Integer.valueOf(action));
        }
    }

    @Override
    public void roomCountDown(String pitNumber, String time) {
        if (baseRoomFragment != null) {
            baseRoomFragment.showPitCountDown(pitNumber, time);
        }
    }

    @Override
    public void setRoomBackground(String picture) {
        mRoomBean.setBg_picture(picture);
        setUpBg();
    }

    @Override
    public void setRoomPlaying(String playing) {
        mRoomBean.setPlaying(playing);
        if (baseRoomFragment instanceof StationRoomFragment) {
            ((StationRoomFragment) baseRoomFragment).setPlaying(mRoomBean.getPlaying());
        }
    }

    @Override
    public void weekStarIn(WeekStarInModel weekStarInModel) {
        if (approachview != null)
            approachview.loadGift(weekStarInModel);
    }


    @Override
    public void roomPopularity(String popularity) {
        if (rvRoomheadview != null) {
            rvRoomheadview.setRoomePopularity(popularity);
        }
    }

    /**
     * 上麦
     */
    private void upperWheatOperation(String pitNum) {
        if (mRoomUserBean != null) {
            mRoomUserBean.setPit(Integer.parseInt(pitNum));
            mRoomUserBean.setVoice(0);
        }
        if (mManager != null) {
            mManager.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
            mManager.muteMic(true);
        }
        if (baseRoomFragment != null) {
            baseRoomFragment.showBallGame(mRoomBean.getShow_ball_game());
            baseRoomFragment.setUserData(mRoomUserBean.getPit());
            baseRoomFragment.setWheatState(0);
            if (mRoomBean.getIs_fm() == 1) {
                String pitName = String.format("%s号麦", pitNum);
                if ("9".equals(pitNum)) {
                    pitName = "主持位";
                } else if ("5".equals(pitNum)) {
                    pitName = "黄金守护位";
                } else if ("6".equals(pitNum)) {
                    pitName = "白银守护位";
                } else if ("7".equals(pitNum) || "8".equals(pitNum)) {
                    pitName = "青铜守护位";
                }
                baseRoomFragment.sendActionMessage("上了" + pitName);
            } else {
                baseRoomFragment.sendActionMessage("上了" + (pitNum.equals("9") ? "主持" : pitNum + "号") + "麦");
            }
            baseRoomFragment.showMore(role == 1 || (role == 2 && mRoomUserBean.getPit() == 9) || mRoomUserBean.getPit() != 0);
        }
    }

    /**
     * 下麦
     *
     * @param pitNum
     */
    private void downWheatOperation(String pitNum) {
        if (mRoomUserBean != null) {
            mRoomUserBean.setPit(0);
            mRoomUserBean.setVoice(0);
        }
        if (mManager != null) {
            mManager.muteMic(true);
            mManager.setClientRole(Constants.CLIENT_ROLE_AUDIENCE);
        }
        if (baseRoomFragment != null) {
            baseRoomFragment.setWheatState(0);
            baseRoomFragment.showBallGame(0);
            baseRoomFragment.setUserData(mRoomUserBean.getPit());
            if (mRoomBean.getIs_fm() == 1) {
                String pitName = String.format("%s号麦", pitNum);
                if ("9".equals(pitNum)) {
                    pitName = "主持位";
                } else if ("5".equals(pitNum)) {
                    pitName = "黄金守护位";
                } else if ("6".equals(pitNum)) {
                    pitName = "白银守护位";
                } else if ("7".equals(pitNum) || "8".equals(pitNum)) {
                    pitName = "青铜守护位";
                }
                baseRoomFragment.sendActionMessage("下了" + pitName);
            } else {
                baseRoomFragment.sendActionMessage("下了" + (pitNum.equals("9") ? "主持" : pitNum + "号") + "麦");
            }

            baseRoomFragment.showMore(role == 1 || (role == 2 && mRoomUserBean.getPit() == 9) || mRoomUserBean.getPit() != 0);
        }
    }


    /**
     * 发送表情
     *
     * @param emojiModel
     */
    @Override
    public void onSelectEmoj(EmojiModel emojiModel) {
        if (mRoomUserBean.getPit() != 0) {
            //在麦位上
            if (emojiModel.getId().equals("0")) {
                MvpPre.roomPoll(mRoomId, true, emojiModel);
            } else {
                emqttManager.sendFace(String.valueOf(mRoomUserBean.getPit()), emojiModel.getSpecial(), mRoomId, 1, 0);
            }
        } else {
            //不在麦位上
            if (emojiModel.getId().equals("0")) {
                MvpPre.roomPoll(mRoomId, false, emojiModel);
            } else {
                baseRoomFragment.sendEmojiMessage(emojiModel.getId(), emojiModel.getPicture(), emojiModel.getName(), emojiModel.getSpecial(), "", false, role);
            }
        }
    }

    @Override
    public void onGiftBannerClick(String roomId) {
        if (!mRoomId.equals(roomId)) {
            LiveRoomActivity.start(this, roomId);
        }
    }


    /**
     * 调音台
     */
    @Override
    public void mixer() {
        if (mTunerSheetDialog == null) {
            mTunerSheetDialog = new TunerSheetDialog(this);
            mTunerSheetDialog.addTunreOnIremClick(this);
        }
        mTunerSheetDialog.show();
    }

    /**
     * 音乐播放器
     */
    @Override
    public void music() {
        if (rlMusicState != null) {
            rlMusicState.setVisibility(View.VISIBLE);
            MusicListFragment.newInstance(mMusicTable == null ? -1 : mMusicTable.getSongid()).show(getSupportFragmentManager());
        }
    }

    /**
     * 房间信息
     */
    @Override
    public void roomInfo() {
        if (role == 1) {
            ARouter.getInstance().build(ARouters.ROOM_ROOMINFO).withString("roomId", mRoomId).withInt("jurisdiction", 1).withString("password", mPassword).withInt("isFm", mRoomBean.getIs_fm()).navigation(LiveRoomActivity.this, 0);
        } else {
            ARouter.getInstance().build(ARouters.ROOM_ROOMINFO).withString("roomId", mRoomId).withInt("jurisdiction", 0).withString("password", mPassword).withInt("isFm", mRoomBean.getIs_fm()).navigation(LiveRoomActivity.this, 0);
        }
    }

    /**
     * 修改房间密码
     */
    @Override
    public void roomPassword() {
        if (roomSettingPasswordDialog == null) {
            roomSettingPasswordDialog = new RoomSettingPasswordDialog(this);
            roomSettingPasswordDialog.addRoomPasswordListener(this);
        }
        roomSettingPasswordDialog.show();
    }

    /**
     * 清理公屏
     */
    @Override
    public void clearPublic() {
        if (emqttManager != null) {
            emqttManager.sendClearScreen();
        }
    }

    /**
     * 设置房间背景
     */
    @Override
    public void roomBackgroud() {
        if (mRoomBackgroudDialogFragment == null) {
            mRoomBackgroudDialogFragment = RoomBackgroudDialogFragment.newInstance(mRoomId, mRoomBean.getBg_picture());
        }
        if (!mRoomBackgroudDialogFragment.isAdded()) {
            mRoomBackgroudDialogFragment.show(getSupportFragmentManager());
        }

    }

    @Override
    public void roomSettingPassword(String password) {
        MvpPre.setRoomPassword(mRoomId, password);
    }


    @Override
    public void setRoomPoll(RoomPollModel roomPoll, boolean b, EmojiModel emojiModel) {
        if (b) {
            emqttManager.sendFace(String.valueOf(mRoomUserBean.getPit()), emojiModel.getSpecial(), mRoomId, 0, roomPoll.getValue());
            HandlerUtil.getInstance(this).postDelayed(new Runnable() {
                @Override
                public void run() {
                    baseRoomFragment.sendDrawMessage(String.valueOf(roomPoll.getValue()), role);
                }
            }, 3000);
        } else {
            baseRoomFragment.sendEmojiMessage(emojiModel.getId(), emojiModel.getPicture(), emojiModel.getName(), emojiModel.getSpecial(), String.valueOf(roomPoll.getValue()), true, role);
        }
    }

    @Override
    public void piCountDownSuccess(String roomId, String pitNumber, String time) {
        emqttManager.sendCountDownTime(roomId, pitNumber, time);
    }

    @Override
    public void applyWheatFmCallback(FmApplyWheatResp resp) {
        if (resp.getState() != 1) {
            MvpPre.getProtectedList(resp.getType());
        } else {
//            EventBus.getDefault().post(new ApplyWheatSuccessEvent(resp.getPit_number()));
        }
    }



    @Override
    public void protectedList(List<ProtectedItemBean> list, int type) {
        if (mPitList == null || mPitList.size() == 0) {
            return;
        }
        RoomPitBean roomPitBean = mPitList.get(mPitList.size() - 1);
        new OpenGuardDialog(roomPitBean.getNickname(), type, this, list, new OpenGuardDialog.OnSelectedProtectListener() {
            @Override
            public void onSelectedProtect(String type) {
                MvpPre.openFmProtected(mRoomId, type, roomPitBean.getUser_id());
            }
        }).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void openGuardDialog(OpenGuardEvent event){
        MvpPre.getProtectedList(1);
    }

    @Override
    public void sendTextMsg(String msg) {
        if (baseRoomFragment != null) {
            baseRoomFragment.sendText(msg, role);
        }
    }
}
