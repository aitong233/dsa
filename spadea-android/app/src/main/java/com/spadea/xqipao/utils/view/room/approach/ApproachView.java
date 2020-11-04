package com.spadea.xqipao.utils.view.room.approach;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.spadea.yuyin.R;
import com.spadea.xqipao.data.ApproachBean;
import com.spadea.xqipao.data.RoomInAnimModel;
import com.spadea.xqipao.data.socket.WeekStarInModel;
import com.spadea.xqipao.utils.view.gift.GiftAnimListener;

import java.util.LinkedList;
import java.util.Queue;

public class ApproachView extends FrameLayout implements Animation.AnimationListener, GiftAnimListener {


    private ItemApproachView firstItemApproachView, lastItemApproachView;
    private Animation firstGiftItemInAnim, firstGiftItemOutAnim;
    private Animation lastGiftItemInAnim, lastGiftItemOutAnim;

    private static Queue<RoomInAnimModel> queue = new LinkedList<>();

    public ApproachView(Context context) {
        super(context);
        initView(context);
    }

    public ApproachView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ApproachView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_approach, this, true);
        firstGiftItemInAnim = AnimationUtils.loadAnimation(context, R.anim.room_gift_in);
        firstGiftItemInAnim.setFillAfter(true);
        firstGiftItemOutAnim = AnimationUtils.loadAnimation(context, R.anim.gift_out);
        firstGiftItemOutAnim.setFillAfter(true);

        lastGiftItemInAnim = AnimationUtils.loadAnimation(context, R.anim.room_gift_in);
        lastGiftItemInAnim.setFillAfter(true);
        lastGiftItemOutAnim = AnimationUtils.loadAnimation(context, R.anim.gift_out);
        lastGiftItemOutAnim.setFillAfter(true);

        firstItemApproachView = findViewById(R.id.first_itemapproachview);
        lastItemApproachView = findViewById(R.id.last_itemapproachview);
        firstItemApproachView.setIndex(1);
        lastItemApproachView.setIndex(2);

        firstItemApproachView.setAnimListener(this);
        lastItemApproachView.setAnimListener(this);

        firstGiftItemOutAnim.setAnimationListener(this);
        lastGiftItemOutAnim.setAnimationListener(this);
    }

    public void initQueue() {
        queue = new LinkedList<>();
    }

    public void emptyQueue() {
        if (queue != null) {
            queue.clear();
            queue = null;
        }
    }

    public void loadGift(WeekStarInModel weekStarInModel) {
        addGift(new RoomInAnimModel<>(2, weekStarInModel));
    }

    public void loadGift(ApproachBean approachBean) {
        addGift(new RoomInAnimModel<>(1, approachBean));
    }

    private void addGift(RoomInAnimModel approachBean) {
        if (queue == null) {
            return;
        }
        if (queue.size() == 0) {
            queue.add(approachBean);
            showGift();
        } else {
            queue.add(approachBean);
        }
    }

    private void showGift() {
        if (isEmpty()) return;
        if (firstItemApproachView.getState() == ItemApproachView.GIFTITEM_DEFAULT) {
            firstItemApproachView.setData(getGift());
            firstItemApproachView.setVisibility(View.VISIBLE);
            firstItemApproachView.startAnimation(firstGiftItemInAnim);
            firstItemApproachView.startAnimation();
        } else if (lastItemApproachView.getState() == ItemApproachView.GIFTITEM_DEFAULT) {
            lastItemApproachView.setData(getGift());
            lastItemApproachView.setVisibility(View.VISIBLE);
            lastItemApproachView.startAnimation(lastGiftItemInAnim);
            lastItemApproachView.startAnimation();
        }
    }

    public RoomInAnimModel getGift() {
        RoomInAnimModel roomInAnimModel = null;
        if (!isEmpty()) {
            // 获取队列首个礼物实体
            roomInAnimModel = queue.poll();
            // 移除队列首个礼物实体
        }
        return roomInAnimModel;
    }

    /**
     * 礼物是否为空
     */
    public boolean isEmpty() {
        return queue == null || queue.size() == 0;
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        showGift();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void giftAnimEnd(int position) {
        switch (position) {
            case 1:
                firstItemApproachView.startAnimation(firstGiftItemOutAnim);
                break;
            case 2:
                lastItemApproachView.startAnimation(lastGiftItemOutAnim);
                break;
        }
    }
}
