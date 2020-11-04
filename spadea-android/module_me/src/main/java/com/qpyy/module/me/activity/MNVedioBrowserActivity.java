package com.qpyy.module.me.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.qpyy.module.me.R;
import com.qpyy.module.me.widget.RecommendationVideo;
import com.qpyy.module.me.widget.SampleListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoView;

import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;


/**
 * 图片浏览的页面
 */
public class MNVedioBrowserActivity extends AppCompatActivity {

    public final static String IntentKey_VEDIOURL = "IntentKey_VEDIOURL";
    private String url;
    private RecommendationVideo video;
    private ImageView ivBack;


    public static void startActivity(Context context, String url) {
        Intent intent = new Intent(context, MNVedioBrowserActivity.class);
        intent.putExtra(IntentKey_VEDIOURL, url);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setWindowFullScreen();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity_mnvedio_browser);
        initIntent();
        initViews();
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
        url = getIntent().getStringExtra(IntentKey_VEDIOURL);
    }

    private void initViews() {
        video = findViewById(R.id.video);
        ivBack = findViewById(R.id.iv_back);
        //拖动视频之后返回一小段
        VideoOptionModel videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 1);
        List<VideoOptionModel> list = new ArrayList<>();
        list.add(videoOptionModel);
        GSYVideoManager.instance().setOptionModelList(list);

        String path = url;
        String tag = url;

        final GSYVideoOptionBuilder builder = new GSYVideoOptionBuilder();
        builder.setIsTouchWiget(true)//是否可以滑动界面改变进度，声音等
                .setUrl(path)
                .setSetUpLazy(true)//lazy可以防止滑动卡顿
                .setCacheWithPlay(true)//是否边缓存，m3u8等无效
                .setRotateViewAuto(true)//是否开启自动旋转
                .setLockLand(false)
                .setPlayTag(tag)
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
                .build(video);

        //直接进行播放
        video.startPlayLogic();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放所有
        video.setVideoAllCallBack(null);
        GSYVideoView.releaseAllVideos();
    }
}
