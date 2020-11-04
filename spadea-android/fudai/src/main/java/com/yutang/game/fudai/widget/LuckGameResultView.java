package com.yutang.game.fudai.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.blankj.utilcode.util.SizeUtils;
import com.qpyy.libcommon.utils.ImageLoader;
import com.yutang.game.fudai.R;
import com.yutang.game.fudai.bean.EggGiftModel;
import com.yutang.game.fudai.bean.LuckGiftBean;

import java.util.List;
import java.util.Random;

public class LuckGameResultView extends FrameLayout {

    public LuckGameResultView(@NonNull Context context) {
        super(context);
    }

    public LuckGameResultView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LuckGameResultView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int sx = 1000;
    int sy = 1000;
    int tx = 1000;
    int ty = 1000;
    int w = 1000;
    int h = 1000;

    int size;

    public void initPoint(int sx, int sy, int tx, int ty, int w, int h) {
        this.sx = sx;
        this.sy = sy;
        this.tx = tx;
        this.ty = ty + SizeUtils.dp2px(30);
        this.w = w;
        this.h = h;
        size = w / 5;
    }


    int point;

    void init() {

    }


    int[] bgs = {R.mipmap.luck_gifbg5, R.mipmap.luck_gifbg4, R.mipmap.luck_gifbg7, R.mipmap.luck_gifxin, R.mipmap.luck_gifyuan};
    // {R.mipmap.pop_bg1,R.mipmap.pop_bg2,R.mipmap.pop_bg3};
    int[] pops = {R.mipmap.pop_gif1, R.mipmap.pop_gif2, R.mipmap.pop_gif3, R.mipmap.pop_gif4, R.mipmap.pop_gif5, R.mipmap.pop_gif6, R.mipmap.pop_gif7, R.mipmap.pop_gif8};


    public void addView(boolean isBlue, List<LuckGiftBean.PrizeInfoBean> gifts) {
        int pos = 0;
        int line = 0;
        int s = gifts.size();
        int dl = 20;
        for (int i = 0; i < s; i++) {
            pos++;
            if (pos >= 5) {
                pos = 0;
                line++;
                if (line >= 5) line = 0;
            }

            int x = pos * size + size + (pos * new Random().nextInt(8) + 3);
            int y = ty + line * SizeUtils.dp2px(60) + (new Random().nextBoolean() ? -new Random().nextInt(30) : new Random().nextInt(30));
            View gifv = LayoutInflater.from(getContext()).inflate(R.layout.game_reslut_item, null, false);
            View bg = gifv.findViewById(R.id.bg);
            View light = gifv.findViewById(R.id.light);
            ImageView gift = gifv.findViewById(R.id.gif);
            gifv.setAlpha(0);
            addView(gifv, new LayoutParams(SizeUtils.dp2px(60), SizeUtils.dp2px(60)));

            LuckGiftBean.PrizeInfoBean ng = gifts.get(i);
            int price = ng.getPrice();
            if (isBlue) {
                if (price <= 66) {
                    bg.setBackgroundResource(R.mipmap.luck_gifbg5);//白色方块
                } else if (price <= 1314) {
                    bg.setBackgroundResource(R.mipmap.luck_gifbg3);//蓝色方块
                } else if (price < 20200) {
                    bg.setBackgroundResource(R.mipmap.luck_gifbg4);//绿色方块
                } else if (price < 52000) {
                    bg.setBackgroundResource(R.mipmap.luck_gifbg7);//白色圆形
                } else {
                    bg.setBackgroundResource(R.mipmap.luck_gifxin);//红心
                }
            } else {
                if (price <= 188) {
                    bg.setBackgroundResource(R.mipmap.luck_gifbg5);//白色方块
                } else if (price <= 1314) {
                    bg.setBackgroundResource(R.mipmap.luck_gifbg3);//蓝色方块
                } else if (price <= 9999) {
                    bg.setBackgroundResource(R.mipmap.luck_gifbg4);//绿色方块
                } else if (price <= 33440) {
                    bg.setBackgroundResource(R.mipmap.luck_gifbg_cheng);//橙色圆形
                } else if (price <= 52000) {
                    bg.setBackgroundResource(R.mipmap.luck_gifbg1);//黄色方形
                } else if (price <= 99999) {
                    bg.setBackgroundResource(R.mipmap.luck_gifyuan);//红圆
                } else {
                    bg.setBackgroundResource(R.mipmap.luck_gifxin);//红心
                }
            }

            bg.setBackground(null);

            ImageLoader.loadImage(getContext(), gift, ng.getPicture());
            if (new Random().nextBoolean()) {
                light.setVisibility(VISIBLE);
                ObjectAnimator anim = ObjectAnimator.ofFloat(light, "rotation", 0f, 360f);
                anim.setDuration(1888);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(ValueAnimator.INFINITE);
                anim.start();
            } else light.setVisibility(GONE);

            gift.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Anim.throwV(gifv, sx, sy, x, y);
                }
            }, dl += 20);
        }
    }

    public void addList(List<EggGiftModel> list) {
        int count = 0;
        int px = point;

        for (int i = 0; i < list.size(); i++) {

        }


    }


}
