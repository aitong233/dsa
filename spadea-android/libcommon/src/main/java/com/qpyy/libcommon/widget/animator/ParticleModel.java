package com.qpyy.libcommon.widget.animator;

import android.graphics.Point;
import android.graphics.Rect;

import com.blankj.utilcode.util.ConvertUtils;

import java.util.Random;

/**
 * 说明：爆破粒子，每个移动与渐变的小块
 * 作者：Jian
 * 时间：2017/12/26.
 */
class ParticleModel {
    // 默认小球宽高
    static final int PART_WH = ConvertUtils.dp2px(5);
    // 随机数，随机出位置和大小
    static Random random = new Random();
    //center x of circle
    float cx;
    //center y of circle
    float cy;
    // 半径
    float radius;
    // 颜色
    int color;
    // 透明度
    float alpha;
    // 整体边界
    Rect mBound;

    ParticleModel(int color, Rect bound, Point point) {
        int row = point.y; //行是高
        int column = point.x; //列是宽

        this.mBound = bound;
        this.color = color;
        this.alpha = 1f;
        this.radius = PART_WH;
        this.cx = bound.left + PART_WH * column;
        this.cy = bound.top + PART_WH * row;
    }

    // 每一步动画都得重新计算出自己的状态值
    void advance(float factor) {
        cx = cx + factor * random.nextInt(mBound.width()) * (random.nextFloat() - 0.5f);
        cy = cy + factor * random.nextInt(mBound.height() / 2);

        radius = radius - factor * random.nextInt(2);

        alpha = (1f - factor) * (1 + random.nextFloat());
    }
}