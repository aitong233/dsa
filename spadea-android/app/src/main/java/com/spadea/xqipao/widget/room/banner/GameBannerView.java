package com.spadea.xqipao.widget.room.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ScreenUtils;
import com.spadea.yuyin.util.utilcode.SizeUtils;
import com.spadea.xqipao.utils.LogUtils;

import java.util.LinkedList;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameBannerView extends FrameLayout implements Animation.AnimationListener {

    @BindView(R.id.tv_content)
    TextView tvContent;

    private static Queue<String> queue = new LinkedList<String>();
    private boolean b = false;
    private Context mContext;
    private TranslateAnimation translateAnimation;


    public GameBannerView(@NonNull Context context) {
        this(context, null);
    }

    public GameBannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameBannerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_game_banner, this, true);
        ButterKnife.bind(this);
    }

    public void initQueue(){
        queue = new LinkedList<>();
    }

    public void emptyQueue(){
        if (queue!=null) {
            queue.clear();
            queue=null;
        }
    }

    public void load(String data) {
        if (queue == null) {
            return;
        }
        if (queue.size() == 0 && !b) {
            showGift(data);
        } else {
            queue.add(data);
        }
    }

    private void showGift(String data) {
        b = true;
        setVisibility(VISIBLE);
        LogUtils.e("消息长度", data);
        tvContent.setText(Html.fromHtml(data));
        int length = tvContent.getText().length();
        float xx = Layout.getDesiredWidth(tvContent.getText(), tvContent.getPaint());
        LogUtils.e("消息长度****", xx);
        translateAnimation = new TranslateAnimation(ScreenUtils.getScreenWidth() - SizeUtils.dp2px(120), -xx, 0, 0);
        translateAnimation.setDuration(length * 150);
        translateAnimation.setAnimationListener(this);
        tvContent.startAnimation(translateAnimation);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        b = false;
        String gift = getGift();
        if (!TextUtils.isEmpty(gift)) {
            showGift(gift);
        } else {
            setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    private String getGift() {
        String data = null;
        if (!isEmpty()) {
            // 获取队列首个礼物实体
            data = queue.poll();
            // 移除队列首个礼物实体
        }
        return data;
    }

    /**
     * 礼物是否为空
     */
    public boolean isEmpty() {
        return queue == null || queue.size() == 0;
    }
}
