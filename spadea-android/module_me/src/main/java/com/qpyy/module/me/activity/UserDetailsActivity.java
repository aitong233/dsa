package com.qpyy.module.me.activity;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.SlidingTabLayout;
import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.adapter.MyFragmentPagerAdapter;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.constant.ImgConstants;
import com.qpyy.libcommon.utils.ImageLoader;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.libcommon.utils.MediaPlayerUtiles;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.libcommon.widget.AgeView;
import com.qpyy.libcommon.widget.DecorationHeadView;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.bean.PhotoWallResp;
import com.qpyy.module.me.bean.UserHomeResp;
import com.qpyy.module.me.bean.XBannerData;
import com.qpyy.module.me.contacts.UserDetailsConacts;
import com.qpyy.module.me.dialog.UserDetailsMoreDialog;
import com.qpyy.module.me.fragment.UserDataFragment;
import com.qpyy.module.me.fragment.UserGiftWallFragment;
import com.qpyy.module.me.fragment.UserPhotoWallFragment;
import com.qpyy.module.me.fragment.UserRoomFragment;
import com.qpyy.module.me.presenter.UserDetailsPresenter;
import com.qpyy.module.me.widget.AppBarStateChangeListener;
import com.qpyy.module.me.widget.FollowView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.USER_DETAILS, name = "个人资料详情页")
public class UserDetailsActivity extends BaseMvpActivity<UserDetailsPresenter> implements UserDetailsConacts.View, FollowView.OnFollowClickListener, UserDetailsMoreDialog.OnUserDetailsMoreListener {

    @BindView(R2.id.xbanner)
    XBanner xbanner;
    @BindView(R2.id.rl_living)
    LinearLayout rlLiving;
    @BindView(R2.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R2.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R2.id.iv_grade)
    ImageView ivGrade;
    @BindView(R2.id.age_view)
    AgeView ageView;

    @BindView(R2.id.tv_voice_leng)
    TextView tvVoiceLeng;
    @BindView(R2.id.rl_voice)
    LinearLayout rlVoice;
    @BindView(R2.id.tv_voice_null)
    TextView tvVoiceNull;
    @BindView(R2.id.iv_nobility)
    ImageView ivNobility;
    @BindView(R2.id.tv_signature)
    TextView tvSignature;
    @BindView(R2.id.tv_follow)
    TextView tvFollow;
    @BindView(R2.id.tv_fans)
    TextView tvFans;
    @BindView(R2.id.riv_user_head)
    DecorationHeadView rivUserHead;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.ctlTitle)
    CollapsingToolbarLayout ctlTitle;
    @BindView(R2.id.sliding_tab_layout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R2.id.abl)
    AppBarLayout abl;
    @BindView(R2.id.viewpager)
    ViewPager viewpager;
    @BindView(R2.id.cl_content)
    CoordinatorLayout clContent;
    @BindView(R2.id.follow)
    FollowView follow;
    @BindView(R2.id.tv_chart)
    TextView tvChart;
    @BindView(R2.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R2.id.iv_play)
    ImageView ivPlay;
    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.iv_more)
    ImageView ivMore;
    @BindView(R2.id.iv_edit)
    ImageView ivEdit;
    @BindView(R2.id.iv_yuliao)
    ImageView ivYuliao;

    @Autowired
    public String userId;

    @Autowired
    public String emchatUsername;

    private String[] title = new String[]{"资料", "礼物墙",  "照片墙","Ta的房间"};
    private UserHomeResp mUserHomeResp;
    private UserDetailsMoreDialog mUserDetailsMoreDialog;

    @Override
    protected UserDetailsPresenter bindPresenter() {
        return new UserDetailsPresenter(this, this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        MvpPre.getUserDetails(this.userId, this.emchatUsername);
    }

    @Override
    protected void initView() {
        super.initView();
        ivPlay.setBackgroundResource(R.drawable.me_play_anim);
        abl.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    tvTitle.setVisibility(View.GONE);
                    ivBack.setImageResource(R.mipmap.ic_back_white);
                    ivMore.setImageResource(R.mipmap.icon_more);
                    ivEdit.setColorFilter(Color.WHITE);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    ivBack.setImageResource(R.mipmap.me_arrow_black);
                    ivMore.setImageResource(R.mipmap.news_icon_more_black);
                    ivEdit.setColorFilter(Color.BLACK);
                } else {
                    //中间状态
                }
            }
        });
        xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                if (mUserHomeResp != null) {
                    List<XBannerData> xBannerData = getXBannerData(mUserHomeResp.getUser_photo());
                    MNImageBrowserActivity.startActivity(UserDetailsActivity.this, xBannerData, position);
                }
            }
        });
        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                XBannerData xBannerData = (XBannerData) model;
                ImageView ivStart = view.findViewById(R.id.iv_voice_start);
                ImageView imageView = view.findViewById(R.id.iv_img);
                if (xBannerData.getType() == 1) {
                    ivStart.setVisibility(View.VISIBLE);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    ImageLoader.loadImage(getApplicationContext(), imageView, xBannerData.getVedioCover());
                } else {
                    ivStart.setVisibility(View.GONE);
                    ImageUtils.loadCenterCrop(xBannerData.getUrl(), imageView);
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.me_activity_user_details;
    }


    @Override
    public void setUserDetails(UserHomeResp data) {
        this.userId = data.getUser_id();
        this.emchatUsername = data.getEmchat_username();
        if (this.mUserHomeResp == null) {
            List<Fragment> fragmentList = new ArrayList<>();
            fragmentList.add(UserDataFragment.newInstance(userId));
            fragmentList.add(UserGiftWallFragment.newInstance(userId));
            fragmentList.add(UserPhotoWallFragment.newInstance(userId));
            fragmentList.add(UserRoomFragment.newInstance(userId));
            viewpager.setAdapter(new MyFragmentPagerAdapter(fragmentList, getSupportFragmentManager()));
            slidingTabLayout.setViewPager(viewpager, title);
            slidingTabLayout.setCurrentTab(0);
            ImageLoader.loadIcon(this, ivYuliao, ImgConstants.WHITE_IMG);
        }
        this.mUserHomeResp = data;
        String id = SpUtils.getUserId();
        if (userId.equals(id)) {
            llBottom.setVisibility(View.GONE);
            ivEdit.setVisibility(View.VISIBLE);
        } else {
            llBottom.setVisibility(View.VISIBLE);
            ivEdit.setVisibility(View.GONE);
        }
        tvTitle.setText(data.getNickname());
        tvNickName.setText(data.getNickname());
        tvFollow.setText(data.getFollow_count());
        tvFans.setText(data.getFans_count());
        ageView.setData(data.getSex(), data.getAge());
        ImageLoader.loadIcon(this, ivGrade, data.getLevel_icon());
        rivUserHead.setData(data.getHead_picture(), data.getPicture(), null);
        rivUserHead.setOnline("1".equals(data.getIs_online()));
        tvSignature.setText(TextUtils.isEmpty(data.getSignature()) ? "ta很懒还没留下什么呢" : data.getSignature());
        follow.setFollow(data.getFollow());
        follow.setOnFollowClickListener(this);
        if (!TextUtils.isEmpty(data.getNobility_image())) {
            ivNobility.setVisibility(View.VISIBLE);
            ImageLoader.loadImage(this, ivNobility, data.getNobility_image());
        } else {
            ivNobility.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(data.getIntro_voice())) {
            rlVoice.setVisibility(View.GONE);
            tvVoiceNull.setVisibility(View.VISIBLE);
        } else {
            rlVoice.setVisibility(View.VISIBLE);
            tvVoiceNull.setVisibility(View.GONE);
            tvVoiceLeng.setText(data.getIntro_voice_time() + "”");
        }

        List<XBannerData> xBannerData = getXBannerData(data.getUser_photo());
        xbanner.setBannerData(R.layout.me_xbanner, xBannerData);

        if (TextUtils.isEmpty(data.getRoom_id_current()) || "0".equals(data.getRoom_id_current())) {
            rlLiving.setVisibility(View.INVISIBLE);
        } else {
            rlLiving.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFail() {
        ToastUtils.show("该用户不存在");
        finish();
    }

    @Override
    public void followSuccess(String type) {
        follow.setFollow(type);
    }

    /**
     * 拉黑成功
     */
    @Override
    public void addBlackUserSuccess() {

    }

    private List<XBannerData> getXBannerData(PhotoWallResp photoWallResp) {
        List<XBannerData> xBannerData = new ArrayList<>();
        if (!TextUtils.isEmpty(photoWallResp.getVedio_cover())) {
            xBannerData.add(new XBannerData(1, photoWallResp.getVedio(), photoWallResp.getVedio_cover()));
        }
        if (!TextUtils.isEmpty(photoWallResp.getAvatar())) {
            xBannerData.add(new XBannerData(0, photoWallResp.getAvatar(), ""));
        }
        for (PhotoWallResp.GiftResp item : photoWallResp.getList()) {
            xBannerData.add(new XBannerData(0, item.getUrl(), ""));
        }
        return xBannerData;
    }


    private boolean isPlay = false;

    @OnClick({R2.id.rl_voice, R2.id.tv_chart, R2.id.iv_more, R2.id.iv_back, R2.id.rl_living, R2.id.iv_edit})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rl_voice) {
            if (mUserHomeResp != null && !TextUtils.isEmpty(mUserHomeResp.getIntro_voice())) {
                AnimationDrawable mAnimation = (AnimationDrawable) ivPlay.getBackground();
                if (isPlay) {
                    isPlay = false;
                    MediaPlayerUtiles.getInstance().stopAudio();
                    ivPlay.post(new Runnable() {
                        @Override
                        public void run() {
                            mAnimation.selectDrawable(0);
                            mAnimation.stop();
                        }
                    });
                } else {
                    MediaPlayerUtiles.getInstance().playAudio(mUserHomeResp.getIntro_voice(), new MediaPlayerUtiles.OnMediaPlayerListener() {
                        @Override
                        public void onStartPlay() {
                            if (ivPlay != null) {
                                isPlay = true;
                                ivPlay.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mAnimation.start();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCompletion() {
                            if (ivPlay != null) {
                                isPlay = false;
                                ivPlay.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mAnimation.selectDrawable(0);
                                        mAnimation.stop();
                                    }
                                });
                            }
                        }
                    });
                }

            }
        } else if (id == R.id.tv_chart) {
            if (mUserHomeResp != null) {
                if ("1".equals(mUserHomeResp.getOnly_friend())) {
                    ToastUtils.show("对方只接受来自好友的消息");
                } else {
                    ARouter.getInstance().build(ARouteConstants.HOME_CHART)
                            .withString("userId", mUserHomeResp.getEmchat_username())
                            .withString("nickname", mUserHomeResp.getNickname())
                            .withString("avatar", mUserHomeResp.getHead_picture())
                            .navigation();
                }
            }
        } else if (id == R.id.iv_more) {
            if (mUserDetailsMoreDialog == null) {
                mUserDetailsMoreDialog = new UserDetailsMoreDialog(this);
                mUserDetailsMoreDialog.addOnUserDetailsMoreListener(this);
            }
            mUserDetailsMoreDialog.show();
        } else if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.rl_living) {
            if (mUserHomeResp != null) {
                ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", mUserHomeResp.getRoom_id_current()).navigation();
            }
        } else if (id == R.id.iv_edit) {
            ARouter.getInstance().build(ARouteConstants.ME_MY_EDIT_INFO).navigation();
        }
    }

    /**
     * 添加关注
     */
    @Override
    public void addFollow() {
        MvpPre.follow(userId, "1");
    }

    /**
     * 取消关注
     */
    @Override
    public void cancelFollow() {
        MvpPre.follow(userId, "2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayerUtiles.getInstance().stopAudio();
    }

    /**
     * 拉黑
     */
    @Override
    public void onBlock() {
        if (SpUtils.getUserId().equals(userId)) {
            ToastUtils.show("不能拉黑自己");
            return;
        }
        MvpPre.addBlackUser(userId, 1);
    }

    /**
     * 举报
     */
    @Override
    public void onReport() {
        if (SpUtils.getUserId().equals(userId)) {
            ToastUtils.show("不能举报自己");
            return;
        }
        ARouter.getInstance().build(ARouteConstants.CHAT_REPORT).withString("userId", userId).navigation();
    }
}
