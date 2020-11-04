package com.spadea.xqipao.utils.view.room.animation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.spadea.xqipao.utils.view.gift.GiftAnimListener;
import com.spadea.yuyin.R;

import java.util.LinkedList;
import java.util.Queue;

public class RoomGiftView extends FrameLayout implements Animation.AnimationListener, GiftAnimListener {

    private ItemRoomGiftView itemRoomGiftView1,itemRoomGiftView2,itemRoomGiftView3,itemRoomGiftView4,itemRoomGiftView5;
    private Animation giftItemInAnim1, giftItemInAnim2, giftItemInAnim3, giftItemInAnim4, giftItemInAnim5,
            giftItemOutAnim1, giftItemOutAnim2, giftItemOutAnim3, giftItemOutAnim4, giftItemOutAnim5;

    private static Queue<ItemRoomGiftBean> queue = new LinkedList<ItemRoomGiftBean>();


    public RoomGiftView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public RoomGiftView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RoomGiftView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_room_gift, this, true);

        giftItemInAnim1 = AnimationUtils.loadAnimation(context, R.anim.room_gift_in);
        giftItemInAnim1.setFillAfter(true);
        giftItemOutAnim1 = AnimationUtils.loadAnimation(context, R.anim.gift_out);
        giftItemOutAnim1.setFillAfter(true);

        giftItemInAnim2 = AnimationUtils.loadAnimation(context, R.anim.room_gift_in);
        giftItemInAnim2.setFillAfter(true);
        giftItemOutAnim2 = AnimationUtils.loadAnimation(context, R.anim.gift_out);
        giftItemOutAnim2.setFillAfter(true);


        giftItemInAnim3 = AnimationUtils.loadAnimation(context, R.anim.room_gift_in);
        giftItemInAnim3.setFillAfter(true);
        giftItemOutAnim3 = AnimationUtils.loadAnimation(context, R.anim.gift_out);
        giftItemOutAnim3.setFillAfter(true);


        giftItemInAnim4 = AnimationUtils.loadAnimation(context, R.anim.room_gift_in);
        giftItemInAnim4.setFillAfter(true);
        giftItemOutAnim4 = AnimationUtils.loadAnimation(context, R.anim.gift_out);
        giftItemOutAnim4.setFillAfter(true);


        giftItemInAnim5 = AnimationUtils.loadAnimation(context, R.anim.room_gift_in);
        giftItemInAnim5.setFillAfter(true);
        giftItemOutAnim5 = AnimationUtils.loadAnimation(context, R.anim.gift_out);
        giftItemOutAnim5.setFillAfter(true);

        itemRoomGiftView1 = findViewById(R.id.itemroomgiftview1);
        itemRoomGiftView2 = findViewById(R.id.itemroomgiftview2);
        itemRoomGiftView3 = findViewById(R.id.itemroomgiftview3);
        itemRoomGiftView4 = findViewById(R.id.itemroomgiftview4);
        itemRoomGiftView5 = findViewById(R.id.itemroomgiftview5);
        itemRoomGiftView1.setAnimListener(this);
        itemRoomGiftView2.setAnimListener(this);
        itemRoomGiftView3.setAnimListener(this);
        itemRoomGiftView4.setAnimListener(this);
        itemRoomGiftView5.setAnimListener(this);

        giftItemOutAnim1.setAnimationListener(this);
        giftItemOutAnim2.setAnimationListener(this);
        giftItemOutAnim3.setAnimationListener(this);
        giftItemOutAnim4.setAnimationListener(this);
        giftItemOutAnim5.setAnimationListener(this);
    }

    public void initQueue(){
        queue = new LinkedList<ItemRoomGiftBean>();
    }

    public void emptyQueue(){
        if (queue!=null) {
            queue.clear();
            queue=null;
        }
    }


    public void loadGift(ItemRoomGiftBean itemRoomGiftBean) {
        if (queue == null) return;
        addGift(itemRoomGiftBean);
    }

    private void addGift(ItemRoomGiftBean itemRoomGiftBean) {
        if (queue == null) return;
        if (queue.size() == 0) {
            queue.add(itemRoomGiftBean);
            showGift();
            return;
        }
        queue.add(itemRoomGiftBean);
    }

    private void showGift() {
        if (isEmpty()) return;
        if (itemRoomGiftView1.getState() == ItemRoomGiftView.GIFTITEM_DEFAULT) {
            itemRoomGiftView1.setData(getGift());
            itemRoomGiftView1.setVisibility(View.VISIBLE);
            itemRoomGiftView1.startAnimation(giftItemInAnim1);
            itemRoomGiftView1.startAnimation();
        } else if (itemRoomGiftView2.getState() == ItemRoomGiftView.GIFTITEM_DEFAULT) {
            itemRoomGiftView2.setData(getGift());
            itemRoomGiftView2.setVisibility(View.VISIBLE);
            itemRoomGiftView2.startAnimation(giftItemInAnim2);
            itemRoomGiftView2.startAnimation();
        } else if (itemRoomGiftView3.getState() == ItemRoomGiftView.GIFTITEM_DEFAULT) {
            itemRoomGiftView3.setData(getGift());
            itemRoomGiftView3.setVisibility(View.VISIBLE);
            itemRoomGiftView3.startAnimation(giftItemInAnim3);
            itemRoomGiftView3.startAnimation();
        } else if (itemRoomGiftView4.getState() == ItemRoomGiftView.GIFTITEM_DEFAULT) {
            itemRoomGiftView4.setData(getGift());
            itemRoomGiftView4.setVisibility(View.VISIBLE);
            itemRoomGiftView4.startAnimation(giftItemInAnim4);
            itemRoomGiftView4.startAnimation();
        } else if (itemRoomGiftView5.getState() == ItemRoomGiftView.GIFTITEM_DEFAULT) {
            itemRoomGiftView5.setData(getGift());
            itemRoomGiftView5.setVisibility(View.VISIBLE);
            itemRoomGiftView5.startAnimation(giftItemInAnim5);
            itemRoomGiftView5.startAnimation();
        }
    }

    public ItemRoomGiftBean getGift() {
        ItemRoomGiftBean itemRoomGiftBean = null;
        if (!isEmpty()) {
            // 获取队列首个礼物实体
            itemRoomGiftBean = queue.poll();
            // 移除队列首个礼物实体
        }
        return itemRoomGiftBean;
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
                itemRoomGiftView1.startAnimation(giftItemOutAnim1);
                break;
            case 2:
                itemRoomGiftView2.startAnimation(giftItemOutAnim2);
                break;
            case 3:
                itemRoomGiftView3.startAnimation(giftItemOutAnim3);
                break;
            case 4:
                itemRoomGiftView4.startAnimation(giftItemOutAnim4);
                break;
            case 5:
                itemRoomGiftView5.startAnimation(giftItemOutAnim5);
                break;
        }
    }
}
