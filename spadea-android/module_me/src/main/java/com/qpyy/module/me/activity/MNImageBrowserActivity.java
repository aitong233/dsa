package com.qpyy.module.me.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.hjq.toast.ToastUtils;
import com.luck.picture.lib.photoview.PhotoView;
import com.qpyy.libcommon.utils.BitmapUtil;
import com.qpyy.module.me.R;
import com.qpyy.module.me.bean.XBannerData;
import com.qpyy.module.me.dialog.SaveImgDialog;
import com.qpyy.module.me.widget.MNGestureView;
import com.qpyy.module.me.widget.ProgressWheel;
import com.qpyy.module.me.widget.RecommendationVideo;
import com.qpyy.module.me.widget.SampleListener;
import com.qpyy.module.me.widget.ZoomOutPageTransformer;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoView;

import java.io.Serializable;
import java.util.List;


/**
 * 图片浏览的页面
 */
public class MNImageBrowserActivity extends AppCompatActivity {

    public final static String IntentKey_ImageList = "IntentKey_ImageList";
    public final static String IntentKey_CurrentPosition = "IntentKey_CurrentPosition";

    private Context context;

    private MNGestureView mnGestureView;
    private ViewPager viewPagerBrowser;
    private TextView tvNumShow;
    private RelativeLayout rl_black_bg;

    private SaveImgDialog mSaveImgDialog;

    private List<XBannerData> xBannerData;
    private int currentPosition = 0;

    public static void startActivity(Context context, List<XBannerData> data, int postion) {
        Intent intent = new Intent(context, MNImageBrowserActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(IntentKey_ImageList, (Serializable) data);
        bundle.putInt(IntentKey_CurrentPosition, postion);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setWindowFullScreen();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity_mnimage_browser);

        context = this;

        initIntent();

        initViews();

        initData();

        initViewPager();

    }

    private void setWindowFullScreen() {
        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= 19) {
            // 虚拟导航栏透明
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void initIntent() {
        xBannerData = (List<XBannerData>) getIntent().getExtras().getSerializable(IntentKey_ImageList);
        currentPosition = getIntent().getExtras().getInt(IntentKey_CurrentPosition);
    }

    private void initViews() {
        viewPagerBrowser = findViewById(R.id.viewPagerBrowser);
        mnGestureView = findViewById(R.id.mnGestureView);
        tvNumShow = findViewById(R.id.tvNumShow);
        rl_black_bg = findViewById(R.id.rl_black_bg);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        tvNumShow.setText(String.valueOf((currentPosition + 1) + "/" + xBannerData.size()));
    }

    private void initViewPager() {
        viewPagerBrowser.setAdapter(new MyAdapter());
        viewPagerBrowser.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPagerBrowser.setCurrentItem(currentPosition);
        viewPagerBrowser.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                tvNumShow.setText(String.valueOf((position + 1) + "/" + xBannerData.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mnGestureView.setOnSwipeListener(new MNGestureView.OnSwipeListener() {
            @Override
            public void downSwipe() {
                finishBrowser();
            }

            @Override
            public void onSwiping(float deltaY) {
                tvNumShow.setVisibility(View.GONE);

                float mAlpha = 1 - deltaY / 500;
                if (mAlpha < 0.3) {
                    mAlpha = 0.3f;
                }
                if (mAlpha > 1) {
                    mAlpha = 1;
                }
                rl_black_bg.setAlpha(mAlpha);
            }

            @Override
            public void overSwipe() {
                tvNumShow.setVisibility(View.VISIBLE);
                rl_black_bg.setAlpha(1);
            }
        });
    }

    private void finishBrowser() {
        tvNumShow.setVisibility(View.GONE);
        rl_black_bg.setAlpha(0);
        finish();
        this.overridePendingTransition(0, R.anim.me_browser_exit_anim);
    }

    @Override
    public void onBackPressed() {
        finishBrowser();
    }


    private class MyAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public MyAdapter() {
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return xBannerData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            View inflate = layoutInflater.inflate(R.layout.me_mn_image_browser_item_show_image, container, false);
            RelativeLayout rlImg = inflate.findViewById(R.id.rl_img);
            RelativeLayout rlVideo = inflate.findViewById(R.id.rl_video);
            PhotoView imageView = inflate.findViewById(R.id.photoImageView);
            ProgressWheel progressWheel = inflate.findViewById(R.id.progressWheel);
            RelativeLayout rlImagePlaceholderBg = inflate.findViewById(R.id.rl_image_placeholder_bg);
            ImageView ivFail = inflate.findViewById(R.id.iv_fail);
            RecommendationVideo recommendationVideo = inflate.findViewById(R.id.video);
            ivFail.setVisibility(View.GONE);
            XBannerData item = xBannerData.get(position);
            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mSaveImgDialog == null) {
                        mSaveImgDialog = new SaveImgDialog(MNImageBrowserActivity.this);
                    }
                    mSaveImgDialog.addOnSaveImgListener(new SaveImgDialog.OnSaveImgListener() {
                        @Override
                        public void onStartSaveImg() {
                            Glide.with(MNImageBrowserActivity.this)
                                    .asBitmap()
                                    .load(item.getUrl())
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                            if (resource != null) {
                                                boolean b = BitmapUtil.saveBitmapToSdCard(MNImageBrowserActivity.this, resource, String.valueOf(System.currentTimeMillis()));
                                                if (b) {
                                                    ToastUtils.show("保存成功");
                                                } else {
                                                    ToastUtils.show("保存失败请重试");
                                                }
                                            } else {
                                                ToastUtils.show("保存失败请重试");
                                            }
                                        }
                                    });
                        }
                    });
                    mSaveImgDialog.show();
                    return false;
                }
            });
            if (item.getType() == 0) {
                rlImg.setVisibility(View.VISIBLE);
                rlVideo.setVisibility(View.GONE);
                Glide.with(context).load(item.getUrl()).thumbnail(0.2f).listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressWheel.setVisibility(View.GONE);
                        ivFail.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressWheel.setVisibility(View.GONE);
                        rlImagePlaceholderBg.setVisibility(View.GONE);
                        ivFail.setVisibility(View.GONE);
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                        imageView.setLayoutParams(params);
                        return false;
                    }
                }).into(imageView);
                if (GSYVideoManager.instance().getMediaPlayer() != null && GSYVideoManager.instance().getMediaPlayer().isPlaying()) {
                    GSYVideoManager.instance().getMediaPlayer().pause();
                }
            } else {
                rlImg.setVisibility(View.GONE);
                rlVideo.setVisibility(View.VISIBLE);
                GSYVideoOptionBuilder builder = new GSYVideoOptionBuilder();
                builder.setIsTouchWiget(true)//是否可以滑动界面改变进度，声音等
                        .setUrl(item.getUrl())
                        .setSetUpLazy(true)//lazy可以防止滑动卡顿
                        .setCacheWithPlay(true)//是否边缓存，m3u8等无效
                        .setRotateViewAuto(true)//是否开启自动旋转
                        .setLockLand(false)
                        .setPlayTag(item.getUrl())
                        .setShowFullAnimation(false)//是否使用全屏动画效果
                        .setNeedLockFull(true)//是否需要全屏锁定屏幕功能
                        .setStandardVideoAllCallBack(new SampleListener() {
                            @Override
                            public void onPrepared(String url, Object... objects) {
                                super.onPrepared(url, objects);

                            }

                            @Override
                            public void onQuitFullscreen(String url, Object... objects) {
                                super.onQuitFullscreen(url, objects);

                            }

                            @Override
                            public void onEnterFullscreen(String url, Object... objects) {
                                super.onEnterFullscreen(url, objects);
                            }

                            @Override
                            public void onAutoComplete(String url, Object... objects) {


                            }
                        })
                        .build(recommendationVideo);

                //直接进行播放
                recommendationVideo.startPlayLogic();
            }


            container.addView(inflate);
            return inflate;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放所有
        GSYVideoView.releaseAllVideos();
    }
}
