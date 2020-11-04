package com.spadea.xqipao.ui.home.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ActivityUtils;
import com.hyphenate.chat.EMClient;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.event.LogOutEvent;
import com.qpyy.libcommon.service.EMqttService;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.module.index.fragment.IndexFragment;
import com.qpyy.module.me.dialog.NewUserGiftDialog;
import com.qpyy.module.me.fragment.MeFragment;
import com.qpyy.libcommon.event.RoomOutEvent;
import com.qpyy.room.bean.NewsMessageEvent;
import com.spadea.yuyin.MyApplication;
import com.tencent.bugly.beta.Beta;
import com.yutang.game.grabmarbles.GrabMarblesManager;
import com.spadea.yuyin.BuildConfig;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.Constants;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.PreferencesUtils;
import com.spadea.yuyin.util.utilcode.BarUtils;
import com.spadea.yuyin.util.utilcode.LogUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.AppUpdateModel;
import com.spadea.xqipao.data.NewsModel;
import com.spadea.xqipao.data.NewsTabReEvent;
import com.spadea.xqipao.data.even.BackHomeEvent;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.home.contacts.HomeContacts;
import com.spadea.xqipao.ui.home.fragment.NewsFragment;
import com.spadea.xqipao.ui.home.presenter.HomePresenter;
import com.spadea.xqipao.ui.room.activity.LiveRoomActivity;
import com.spadea.xqipao.utils.SPUtil;
import com.spadea.xqipao.utils.dialog.AppUpdateDialog;
import com.spadea.xqipao.widget.DropView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.MAIN, name = "主界面")
public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContacts.View {


    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.iv_news)
    ImageView ivNews;
    @BindView(R.id.tv_news)
    TextView tvNews;
    @BindView(R.id.rl_news)
    RelativeLayout rlNews;
    @BindView(R.id.iv_me)
    ImageView ivMe;
    @BindView(R.id.tv_me)
    TextView tvMe;
    @BindView(R.id.rl_me)
    RelativeLayout rlMe;
    @BindView(R.id.rl_index)
    RelativeLayout rlIndex;
    @BindView(R.id.iv_index)
    ImageView ivIndex;
    @BindView(R.id.tv_index)
    TextView tvIndex;
    @BindView(R.id.tv_message0)
    TextView tvMessage;
    @BindView(R.id.ll_left)
    LinearLayout llLeft;
    @BindView(R.id.riv)
    RoundedImageView riv;
    @BindView(R.id.iv_guanbi)
    ImageView ivGuanbi;
    @BindView(R.id.ll)
    DropView ll;
    @BindView(R.id.bg)
    View bg;

    // 0 消息  1直播间  2 个人中心
    private int index = -1;
    private NewsFragment newsFragment;
    private MeFragment meFragment;
    public IndexFragment liveFragment;
    private AppUpdateDialog appUpdateDialog;

    @Autowired
    public String giftBagUrl;

    public HomeActivity() {
        super(R.layout.activity_home);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //用于处理fragment重叠的问题
//        super.onSaveInstanceState(outState);
    }

    @Override
    protected void initData() {
        EMqttService.startService(this);
        EMqttService.subscribeUser(BaseApplication.getIns().getUser().getUser_id());
        newsFragment = NewsFragment.newInstance("消息");
        liveFragment = IndexFragment.newInstance();
        meFragment = MeFragment.newInstance("我的");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout, newsFragment, "newsFragment")
                .add(R.id.frame_layout, liveFragment, "liveFragment")
                .add(R.id.frame_layout, meFragment, "meFragment")
                .show(liveFragment)
                .hide(newsFragment)
                .hide(meFragment)
                .commitAllowingStateLoss();
        MvpPre.initData();

        boolean isFirst = SPUtil.getBooleanDefultFalse(Constant.Channel.ISFIRST);
        if (!isFirst) {
            MvpPre.randomHotRoom();
            SPUtil.saveboolean(Constant.Channel.ISFIRST, true);
        }
//        MvpPre.loginIm();
        if (!TextUtils.isEmpty(giftBagUrl)) {
            new NewUserGiftDialog(this, giftBagUrl).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    @Override
    protected void initView() {

        EventBus.getDefault().register(this);
        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setAndroidNativeLightStatusBar(this, true);
        tvIndex.setSelected(true);
        GrabMarblesManager.INSTANCE.setToken(SpUtils.getToken());
        GrabMarblesManager.INSTANCE.setUserId(SpUtils.getUserId());
    }

    @Override
    protected HomePresenter bindPresenter() {
        return new HomePresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @Override
    protected void setListener() {
        super.setListener();
    }


    @OnClick({R.id.rl_news, R.id.rl_me, R.id.rl_index, R.id.iv_guanbi, R.id.riv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_news:
                selectShow(0);
                break;
            case R.id.rl_me:
                selectShow(2);
                break;
            case R.id.rl_index:
                selectShow(1);
                break;
            case R.id.iv_guanbi:
                ll.setVisibility(View.INVISIBLE);
                MyApplication.getInstance().isShow = false;
                MyApplication.getInstance().isPlaying = false;
                EventBus.getDefault().post(new RoomOutEvent());
                break;
            case R.id.riv:
                String roomId = MyApplication.getInstance().playId;
                if (!TextUtils.isEmpty(roomId)) {
                    LiveRoomActivity.start(this, roomId);
                }
                break;
        }
    }


    private void reset() {
        tvIndex.setSelected(false);
        tvNews.setSelected(false);
        tvMe.setSelected(false);
        ivNews.setBackgroundResource(R.mipmap.tab_news_un_select);
        ivIndex.setBackgroundResource(R.mipmap.tab_index_un_select);
        ivMe.setBackgroundResource(R.mipmap.tab_me_un_select);
    }

    private void selectShow(int postion) {
        if (postion == index) {
            if (postion == 0) {
                EventBus.getDefault().post(new NewsTabReEvent());
            } else {
                return;
            }
        }
        reset();
        this.index = postion;
        switch (postion) {
            case 0:
                tvNews.setSelected(true);
                ivNews.setBackgroundResource(R.mipmap.tab_news_select);
//                starTabAnim(ivNews);
                getSupportFragmentManager().beginTransaction()
                        .show(newsFragment)
                        .hide(liveFragment)
                        .hide(meFragment)
                        .commitAllowingStateLoss();
                break;
            case 1:
                tvIndex.setSelected(true);
                ivIndex.setBackgroundResource(R.mipmap.tab_index_select);
//                starTabAnim(ivIndex);
                getSupportFragmentManager().beginTransaction()
                        .show(liveFragment)
                        .hide(newsFragment)
                        .hide(meFragment)
                        .commitAllowingStateLoss();
                break;
            case 2:
                tvMe.setSelected(true);
                ivMe.setBackgroundResource(R.mipmap.tab_me_select);
//                starTabAnim(ivMe);
                getSupportFragmentManager().beginTransaction()
                        .show(meFragment)
                        .hide(newsFragment)
                        .hide(liveFragment)
                        .commitAllowingStateLoss();
                break;
        }
    }

    private void starTabAnim(ImageView iv) {
        //获取背景，并将其强转成AnimationDrawable
        AnimationDrawable animationDrawable = (AnimationDrawable) iv.getBackground();
        //开启帧动画
        animationDrawable.start();
    }


    private long lastTime;
    private long duration = 2000L;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - lastTime) > duration) {
            ToastUtils.showShort("再按一次退出应用");
            lastTime = currentTime;
        } else {
            MyApplication.getInstance().isPlaying = false;
            MyApplication.getInstance().isShow = false;
            ivGuanbi.performClick();
            ActivityUtils.startHomeActivity();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MvpPre.loginIm();
        LogUtils.i("HomeActivity", "onResume");
        Beta.checkAppUpgrade(false, false);
        MvpPre.appUpdate();
        MvpPre.signSwitch();
        refreshUnread();
        boolean b = MyApplication.getInstance().isPlaying;
        if (b) {
            ImageLoader.loadHead(HomeActivity.this, riv, MyApplication.getInstance().playCover);
            riv.postDelayed(mRivAnimationTask, 1000);
            ll.setVisibility(View.VISIBLE);
        } else {
            ll.setVisibility(View.INVISIBLE);
        }
        if (MyApplication.getInstance().isPlaying && MyApplication.getInstance().isShow) {
            LiveRoomActivity.start(this, MyApplication.getInstance().playId);
        }
        LogUtils.i("isPlay", "onResume: " + b + "view:" + ll.getVisibility());
    }

    @Override
    protected void onStop() {
        super.onStop();
        riv.removeCallbacks(mRivAnimationTask);
        riv.clearAnimation();
    }

    private Runnable mRivAnimationTask = new Runnable() {
        @Override
        public void run() {
            Animation rivRotateAnimation = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.rotate_anim);
            riv.startAnimation(rivRotateAnimation);
        }
    };

    private void refreshUnread() {
        MvpPre.userNews();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void newMessage(NewsMessageEvent newsMessageEvent) {
        refreshUnread();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void logOut(LogOutEvent logOutEvent) {
        MyApplication.getInstance().reLogin();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void backHomeEvent(BackHomeEvent event) {
        rlIndex.post(new Runnable() {
            @Override
            public void run() {
                rlIndex.performClick();
            }
        });

    }

    @Override
    public void appUpdate(AppUpdateModel appUpdateModel) {
        if (appUpdateModel.getVersionCode() > BuildConfig.VERSION_CODE) {
            if (appUpdateDialog == null) {
                appUpdateDialog = new AppUpdateDialog(this);
                appUpdateDialog.setAppUpdateModel(appUpdateModel);
            }
            if (appUpdateModel.getForceUpdate() == 1) {
                appUpdateDialog.setCanceledOnTouchOutside(false);
            } else {
                appUpdateDialog.setCanceledOnTouchOutside(true);
            }
            appUpdateDialog.show();
        }
    }

    @Override
    public void setRandomHotRoom(String roomId) {
        if (!TextUtils.isEmpty(roomId)) {
            LiveRoomActivity.start(this, roomId);
        }
    }

    @Override
    public void userNewsSuccess(NewsModel newsModel) {
        int orderCount = PreferencesUtils.getInt(MyApplication.getInstance(), Constants.ORDER_NEWS_COUNT, 0);
        int unreadMessageCount = EMClient.getInstance().chatManager().getUnreadMessageCount() + newsModel.getCount() + orderCount;
        if (unreadMessageCount == 0) {
            tvMessage.setVisibility(View.GONE);
            tvMessage.setText("0");
        } else {
            tvMessage.setVisibility(View.VISIBLE);
            tvMessage.setText(String.valueOf(unreadMessageCount));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
