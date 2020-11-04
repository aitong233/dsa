package com.qpyy.room.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hjq.toast.ToastUtils;
import com.hyphenate.util.NetUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.opensource.svgaplayer.SVGAImageView;
import com.qpyy.libcommon.adapter.MyFragmentPagerAdapter;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.bean.RoomBackgroundModel;
import com.qpyy.libcommon.bean.RoomKickOutModel;
import com.qpyy.libcommon.bean.RoomNameModel;
import com.qpyy.libcommon.bean.RoomPopularityModel;
import com.qpyy.libcommon.bean.RoomUserWheathModel;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.db.table.MusicTable;
import com.qpyy.libcommon.event.LoginOutEvent;
import com.qpyy.libcommon.event.RoomPasswordEvent;
import com.qpyy.libcommon.event.ZegoLogUploadEvent;
import com.qpyy.libcommon.service.EMqttService;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.libcommon.widget.MarqueeTextView;
import com.qpyy.libcommon.widget.ScrollViewPager;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.CurrentMusic;
import com.qpyy.room.bean.EndMusicEvent;
import com.qpyy.room.bean.MusicDelEvent;
import com.qpyy.room.bean.MusicIsPlay;
import com.qpyy.room.bean.MusicPauseEvent;
import com.qpyy.room.bean.MusicStartEvent;
import com.qpyy.room.bean.OpenMusicEvent;
import com.qpyy.room.bean.RoomBean;
import com.qpyy.room.bean.RoomBgBean;
import com.qpyy.room.bean.RoomInfoResp;
import com.qpyy.room.bean.RoomOwnerBean;
import com.qpyy.room.bean.RoomPitBean;
import com.qpyy.room.bean.RoomUserBean;
import com.qpyy.room.bean.WheatAnimEvent;
import com.qpyy.room.contacts.RoomContacts;
import com.qpyy.room.dialog.RoomGuideDialog;
import com.qpyy.room.dialog.RoomTipsView;
import com.qpyy.room.dialog.ShareDialog;
import com.qpyy.room.event.BaoWheatEvent;
import com.qpyy.room.event.NetEvent;
import com.qpyy.room.event.PasswordInputEvent;
import com.qpyy.room.event.RoomGreetingEvent;
import com.qpyy.room.event.RoomInputHideEvent;
import com.qpyy.libcommon.event.RoomOutEvent;
import com.qpyy.room.fragment.MusicListDialogFragment;
import com.qpyy.room.fragment.RoomFragment;
import com.qpyy.room.fragment.RoomOnlineFragment;
import com.qpyy.room.fragment.RoomPasswordSetDialogFragment;
import com.qpyy.room.fragment.RoomRankingFragment;
import com.qpyy.room.presenter.RoomPresenter;
import com.qpyy.room.widget.MusicView;
import com.qpyy.rtc.RtcManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.LIVE_ROOM)
public class RoomActivity extends BaseMvpActivity<RoomPresenter> implements RoomContacts.View {

    private final String TAG = "RoomActivity";
    @Autowired
    public String roomId;

    @Autowired
    public String password;

    @BindView(R2.id.vp_room_pager)
    ScrollViewPager vpRoomPager;
    @BindView(R2.id.iv_bg)
    ImageView mIvBg;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.iv_room_share)
    ImageView mIvRoomShare;
    @BindView(R2.id.iv_room_love)
    ImageView mIvRoomLove;
    @BindView(R2.id.iv_room_notice)
    ImageView mIvRoomNotice;
    @BindView(R2.id.tv_room_name)
    MarqueeTextView mTvRoomName;
    @BindView(R2.id.tv_room_label)
    TextView mTvRoomLabel;
    @BindView(R2.id.tv_room_id_after_label)
    TextView mTvRoomIdAfterLabel;
    @BindView(R2.id.tv_room_id)
    TextView mTvRoomId;
    @BindView(R2.id.tv_hot)
    TextView mTvHot;
    @BindView(R2.id.riv_dialog_music_head_pic)
    RoundedImageView rivDialogMusicHeadPic;
    @BindView(R2.id.rl_music_min_view)
    RelativeLayout rlMusicMinView;
    @BindView(R2.id.music_view)
    MusicView musicView;

    //3普通用户  2 管理员  1房主
    private int role = 3;
    private List<RoomPitBean> mPitList = new ArrayList<>();
    /**
     * 房间信息
     */
    private RoomBean mRoomBean;
    //房主信息
    private RoomOwnerBean mRoomOwnerBean;
    //房间用户信息
    private RoomUserBean mRoomUserBean;
    //播放状态
    private boolean isPlay = false;
    //旋转动画
    private Animation rotateAnimation;
    //当前播放音乐
    private MusicTable mMusicTable;

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);


    @Override
    protected void onNewIntent(Intent intent) {
        String mRoomId = intent.getStringExtra("roomId");
        if (roomId.equals(mRoomId) || TextUtils.isEmpty(mRoomId)) {
            super.onNewIntent(intent);
        } else {
            releaseRoom();
            finish();
            startActivity(intent);
        }
    }

    /**
     * 释放当前房间
     */
    private void releaseRoom() {
        if (entrySuccess)
            RtcManager.getInstance().leaveChannel(roomId);
        RtcManager.getInstance().destroy(null);
        EMqttService.cleanSubscribeRoom(roomId);
        EMqttService.cleanSubscribeRoom();
        EMqttService.cleanSubscribe("red_envelope_single_room_real_time_data");
        BaseApplication.getIns().isPlaying = false;
        BaseApplication.getIns().isShow = false;
    }

    /**
     * 返回
     */

    @Override
    protected void initData() {
        MvpPre.initRtcManager(getApplication());
        EMqttService.subscribeRoom(roomId);
        EMqttService.subscribeRoom();
        EMqttService.subscribe("red_envelope_single_room_real_time_data");
        MvpPre.getRoomIn(roomId, password);
    }

    @Override
    protected void initView() {
        super.initView();
        vpRoomPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i > 0) {
                    EventBus.getDefault().post(new BaoWheatEvent(false));
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        setMusicListener();
    }

    @Override
    public boolean isLightMode() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        Log.d(TAG, "(Start)启动了===========================RoomActivity");
        return R.layout.room_activity_room;
    }

    @Override
    protected RoomPresenter bindPresenter() {
        return new RoomPresenter(this, this);
    }

    @Override
    public void roomInfo(RoomInfoResp resp) {
        entrySuccess = true;
        RoomBean roomBean = resp.getRoom_info();
        this.mRoomBean = roomBean;
        this.mRoomUserBean = resp.getUser_info();
        this.mRoomOwnerBean = resp.getOwner_info();
        this.mPitList = mRoomBean.getPit_list();
        setUpBg();
        showGuide();
        mIvRoomLove.setVisibility(resp.getUser_info().getFavorite() == 1 ? View.GONE : View.VISIBLE);
        mTvHot.setText(roomBean.getPopularity());
        mTvRoomName.setText(roomBean.getRoom_name());
        if (roomBean.getIs_password() == 1) {
            mTvRoomName.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.room_ic_room_lock), null);
        } else {
            mTvRoomName.setCompoundDrawables(null, null, null, null);
        }
        mTvRoomLabel.setText(roomBean.getLabel_name());
        mTvRoomId.setText(roomBean.getRoom_code());
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(RoomOnlineFragment.newInstance(resp));
        fragments.add(RoomFragment.newInstance(resp, password));
        fragments.add(RoomRankingFragment.newInstance(roomId));
        vpRoomPager.setAdapter(new MyFragmentPagerAdapter(fragments, getSupportFragmentManager()));
        vpRoomPager.setCurrentItem(1);

        BaseApplication.getIns().isPlaying = true;
        BaseApplication.getIns().playId = roomId;
        BaseApplication.getIns().playName = mRoomBean.getRoom_name();
        BaseApplication.getIns().playCover = mRoomOwnerBean.getHead_picture();
        BaseApplication.getIns().showSelf = mRoomBean.getIs_show_self() == 1;
        Log.d(TAG, "房间密码：" + resp.getRoom_info().getIs_password());

        //加入房间成功后开启mqtt心跳
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            if (EMqttService.isAlreadyConnected()) {
                MvpPre.mqttHeartBeat(roomId);
            }
        }, 1, 3, TimeUnit.MINUTES);
    }


    private void showGuide() {
        //判断是否需要引导
        if (mRoomUserBean.getGuide() == 0) {
            RoomGuideDialog.newInstance(roomId, 1).show(getSupportFragmentManager());//引导弹窗
        }
    }

    boolean entrySuccess = false;

    @Override
    public void enterFail() {
        entrySuccess = false;
        releaseRoom();
        finish();
    }

    @Override
    public void addFavoriteSuccess() {
        ToastUtils.show("关注成功");
        if (mRoomUserBean != null) {
            mRoomUserBean.setFavorite(1);
        }
        mIvRoomLove.setVisibility(View.GONE);
    }

    @Override
    public void quitSuccess() {
        releaseRoom();
        RtcManager.getInstance().removeRtcEventListener();
        finish();
    }

    @Override
    public void showPasswordDialog() {
        RoomPasswordSetDialogFragment roomPasswordSetDialogFragment = RoomPasswordSetDialogFragment.newInstance(true, roomId);
        roomPasswordSetDialogFragment.show(getSupportFragmentManager());
    }

    @Override
    public void playNextMusic() {
        musicView.next();
    }

    /**
     * 设备引擎销毁成功
     */
    @Override
    public void onRtcDestroySuccess() {

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        BaseApplication.getIns().isShow = false;
        ARouter.getInstance().build(ARouteConstants.MAIN).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP).navigation();//栈顶复用
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void inputPasswordComplete(PasswordInputEvent event) {
        RoomActivity.this.password = event.password;
        MvpPre.getRoomIn(roomId, password);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void roomBgPreview(RoomBgBean event) {
        if (TextUtils.isEmpty(event.getPicture())) {
            if (TextUtils.isEmpty(event.getId())) {
                setUpBg();
            } else {
                mRoomBean.setBg_picture(event.getPicture());
                ImageUtils.loadImageBlurBg(mRoomOwnerBean.getHead_picture(), mIvBg);
            }
        } else {
            ImageUtils.loadRoomBg(event.getPicture(), mIvBg);
        }
    }

    private void setUpBg() {
        if (TextUtils.isEmpty(mRoomBean.getBg_picture())) {
            ImageUtils.loadImageBlurBg(mRoomOwnerBean.getHead_picture(), mIvBg);
        } else {
            ImageUtils.loadRoomBg(mRoomBean.getBg_picture(), mIvBg);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void roomGreeting(RoomGreetingEvent event) {
        mRoomBean.setGreeting(event.value);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void roomOut(RoomOutEvent roomOutEvent) {
        MvpPre.quitRoom(roomId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        scheduledExecutorService.shutdown();
        scheduledExecutorService = null;
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        BaseApplication.getIns().isShow = true;
        BaseApplication.getIns().isPlaying = true;
        EventBus.getDefault().post(new WheatAnimEvent(true));
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().post(new WheatAnimEvent(false));
    }

    @OnClick({R2.id.iv_back, R2.id.iv_room_share, R2.id.iv_room_love, R2.id.iv_room_notice, R2.id.rl_music_min_view})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            onBackPressed();
        } else if (id == R.id.iv_room_share) {
            new ShareDialog(this).show();
        } else if (id == R.id.iv_room_love) {
            MvpPre.addFavorite(roomId);
        } else if (id == R.id.iv_room_notice) {
            if (mRoomBean != null) {
                RoomTipsView.show(this, mIvRoomNotice, String.format("欢迎来到%s房间", mRoomBean.getRoom_name()), mRoomBean.getGreeting());
            }
        } else if (id == R.id.rl_music_min_view) {
            if (!isPlay) {//如果音乐为停止状态，关闭隐藏
                rlMusicMinView.setVisibility(View.GONE);
                return;
            }
            if (MvpPre.getLocalMusicCount() == 0) {
                startActivityForResult(new Intent(this, SearchSongsActivity.class), 0);
            } else {
                List<MusicTable> lovalMusicData = MvpPre.getLovalMusicData();
                if (mMusicTable == null) {

                    mMusicTable = lovalMusicData.get(0);
                }
                musicView.setMusicList(lovalMusicData);
                musicView.setPalyState(isPlay);
                musicView.setData(mMusicTable);
                musicView.setVisibility(View.VISIBLE);
                rlMusicMinView.setVisibility(View.GONE);
            }
        }
    }


    /**
     * 人气变化
     *
     * @param roomPopularityModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void roomPopularity(RoomPopularityModel roomPopularityModel) {
        if (roomId.equals(roomPopularityModel.getRoom_id())) {
            mTvHot.setText(String.valueOf(roomPopularityModel.getPopularity()));
        }
    }

    /**
     * 房间密码变化通知 0取消密码1设置或修改密码
     *
     * @param roomPasswordEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomPasswordEvent roomPasswordEvent) {
        if (roomId.equals(roomPasswordEvent.getRoomId()) && roomPasswordEvent.isPassword()) {
//            showPasswordDialog();
            mRoomBean.setIs_password(roomPasswordEvent.isPassword() ? 1 : 0);
            if (mRoomBean.getIs_password() == 1) {
                mTvRoomName.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.room_ic_room_lock), null);
            } else {
                mTvRoomName.setCompoundDrawables(null, null, null, null);
            }
        }
    }

    /**
     * 修改房间名称
     *
     * @param roomNameModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomNameModel roomNameModel) {
        if (roomId.equals(roomNameModel.getRoom_id())) {
            mTvRoomName.setText(roomNameModel.getRoom_name());
        }
    }


    /**
     * 修改房间背景
     *
     * @param roomBackgroundModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomBackgroundModel roomBackgroundModel) {
        if (roomId.equals(roomBackgroundModel.getRoom_id())) {
            mRoomBean.setBg_picture(roomBackgroundModel.getBackground());
            setUpBg();
        }
    }

    /**
     * 踢出房间
     *
     * @param roomKickOutModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomKickOutModel roomKickOutModel) {
        if (roomId.equals(roomKickOutModel.getRoom_id()) && SpUtils.getUserId().equals(roomKickOutModel.getUser_id())) {
            MvpPre.quitRoom(roomId);
        }
    }


    /**
     * 抱用户上麦
     *
     * @param roomUserWheathModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomUserWheathModel roomUserWheathModel) {
    }


    /**
     * 音乐播放界面监听
     */
    protected void setMusicListener() {
        musicView.addOnItemClickListener(new MusicView.OnItemClickListener() {
            /**
             * 音乐播放窗口min
             */
            @Override
            public void minimize() {
                musicView.setVisibility(View.GONE);
                rlMusicMinView.setVisibility(View.VISIBLE);
            }

            /**
             * 开启音乐播放列表
             */
            @Override
            public void openMusicList() {
                musicView.setVisibility(View.GONE);
                rlMusicMinView.setVisibility(View.VISIBLE);
                MusicListDialogFragment.newInstance(mMusicTable == null ? -1 : mMusicTable.getSongid(), isPlay)
                        .show(getSupportFragmentManager(), "MusicListDialogFragment");
            }

            /**
             * 开始播放音乐
             * @param musicTable
             */
            @Override
            public void playMusic(MusicTable musicTable) {
                mMusicTable = musicTable;
                RtcManager.getInstance().startAudioMixing(musicTable.getUrl());
                musicView.setPalyState(true);
                isPlay = true;
                SpUtils.setPlayCurrentMusic(musicTable.getSongid());//记录当前播放的音乐
            }

            /**
             * 停止播放音乐
             */
            @Override
            public void stopPlay() {
                RtcManager.getInstance().stopAudioMixing();
                musicView.setPalyState(false);
                isPlay = false;
            }

            /**
             * 暂停播放音乐
             */
            @Override
            public void pausePlay() {
                RtcManager.getInstance().pauseAudioMixing();
                musicView.setPalyState(false);
                isPlay = false;
            }

            /**
             * 恢复播放音乐
             */
            @Override
            public void resumePlay() {
                RtcManager.getInstance().resumeAudioMixing();
                musicView.setPalyState(true);
                isPlay = true;
            }

            /**
             * 设置音量
             * @param progress
             */
            @Override
            public void setMusicVolume(int progress) {
                SpUtils.setChannelVolume(progress);
                RtcManager.getInstance().setAudioMixingVolume(progress);
            }
        });
    }


    /**
     * 音乐成功暂停/结束
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(MusicPauseEvent musicPauseEvent) {
        endRotateAnimation(rivDialogMusicHeadPic);
        isPlay = false;
        musicView.setPalyState(false);
        EventBus.getDefault().post(new MusicIsPlay(isPlay));
    }

    /**
     * 音乐成功播放
     *
     * @param musicPauseEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(MusicStartEvent musicPauseEvent) {
        startRotateAnimation(rivDialogMusicHeadPic);
        if (rlMusicMinView.getVisibility() != View.VISIBLE) {
            rlMusicMinView.setVisibility(View.VISIBLE);
        }
        isPlay = true;
        musicView.setPalyState(true);
        EventBus.getDefault().post(new MusicIsPlay(isPlay));
    }


    /**
     * 音乐列表删除某首音乐
     *
     * @param musicDelEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(MusicDelEvent musicDelEvent) {
        if (musicDelEvent.getSongId() == mMusicTable.getSongid()) {//删除了暂停的歌曲
            musicView.next();//播放下一首
        }
    }

    /**
     * 音乐列表选择某首音乐
     *
     * @param musicTable
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(MusicTable musicTable) {
        mMusicTable = musicTable;
        RtcManager.getInstance().startAudioMixing(musicTable.getUrl());
        startRotateAnimation(rivDialogMusicHeadPic);
        musicView.setPalyState(true);
        isPlay = true;
        SpUtils.setPlayCurrentMusic(musicTable.getSongid());//记录当前播放的音乐
    }

    /**
     * 列表点击当前正在播放的音乐
     *
     * @param currentMusic
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(CurrentMusic currentMusic) {
        if (currentMusic.isPlay()) {
            RtcManager.getInstance().pauseAudioMixing();
            endRotateAnimation(rivDialogMusicHeadPic);
            musicView.setPalyState(false);
            isPlay = false;
        } else {
            RtcManager.getInstance().resumeAudioMixing();
            startRotateAnimation(rivDialogMusicHeadPic);
            musicView.setPalyState(true);
            isPlay = true;
        }
    }

    /**
     * 显示音乐窗口
     *
     * @param openMusicEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(OpenMusicEvent openMusicEvent) {
        rlMusicMinView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏音乐窗口
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(EndMusicEvent endMusicEvent) {
        rlMusicMinView.setVisibility(View.GONE);
    }

    /**
     * 上传即构日志
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(ZegoLogUploadEvent event) {
        RtcManager.getInstance().uploadLog();
    }

    /**
     * 开启图片旋转动画
     */
    private void startRotateAnimation(View view) {
        rotateAnimation = AnimationUtils.loadAnimation(RoomActivity.this, R.anim.image_rotate);
        LinearInterpolator lin = new LinearInterpolator();
        rotateAnimation.setInterpolator(lin);
        view.setAnimation(rotateAnimation);
        view.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 关闭图片旋转动画
     */
    private void endRotateAnimation(View view) {
        view.clearAnimation();
        view.setAnimation(null);
        view.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 网络变化监听
     *
     * @param netEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void netEvent(NetEvent netEvent) {
        if (netEvent.getType() == NetUtils.Types.NONE) {
            ToastUtils.show("网络已断开");
        } else {
            ToastUtils.show("网络已连接");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginOut(LoginOutEvent loginOutEvent) {

        releaseRoom();
        this.finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void baoEvent(BaoWheatEvent event) {
        if (event.manager) {
            vpRoomPager.setCurrentItem(0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hideInput(RoomInputHideEvent event) {
        if (event.hide) {
            vpRoomPager.setScroll(false);
        } else {
            vpRoomPager.setScroll(true);
        }
    }
}
