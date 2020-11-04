package com.spadea.xqipao.utils.view.room.animation;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spadea.xqipao.utils.view.gift.GiftAnimListener;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;


public class ItemRoomGiftView extends FrameLayout {

    private int index = 0;
    private LinearLayout linearLayout;
    private TextView tvFromUser;
    private TextView tvToUser;
    private ImageView ivGift;
    private ImageView ivNum;
    private TextView tvNumber;

    private Context mContext;
    private ItemRoomGiftBean itemRoomGiftBean;
    private String tag;

    public static final int SHOW_TIME = 3000;
    public static final int GIFTITEM_DEFAULT = 0;
    public static final int GIFTITEM_SHOW = 1;
    /**
     * 当前显示状态
     */
    public int state = GIFTITEM_DEFAULT;
    /**
     * 透明度动画(200ms), 连击动画(200ms)
     */

    private GiftAnimListener animListener;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    handler.removeCallbacksAndMessages(null);
                    state = GIFTITEM_DEFAULT;
                    if (animListener == null) break;
                    animListener.giftAnimEnd(index);
                    break;
            }
        }
    };


    public ItemRoomGiftView(@NonNull Context context) {
        super(context);
        initView(context, null);
    }


    public ItemRoomGiftView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ItemRoomGiftView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.item_room_gift, this, true);
        linearLayout = findViewById(R.id.ll);
        tvFromUser = findViewById(R.id.tv_from_user);
        tvToUser = findViewById(R.id.tv_to_user);
        ivGift = findViewById(R.id.iv_gift);
        ivNum = findViewById(R.id.iv_num);
        tvNumber = findViewById(R.id.tv_number);
        if (null == attrs) return;
        TypedArray typed = context.obtainStyledAttributes(attrs, R.styleable.ItemUserIntoView, 0, 0);
        if (null == typed) return;
        index = typed.getInteger(R.styleable.ItemUserIntoView_user_into_index, 0);
        typed.recycle();
    }

    public void setData(ItemRoomGiftBean itemRoomGiftBean) {
        this.itemRoomGiftBean = itemRoomGiftBean;
        tag = itemRoomGiftBean.toString();
        tvFromUser.setText(itemRoomGiftBean.getFormUser());
        tvToUser.setText(itemRoomGiftBean.getToUser());
        ImageLoader.loadImage(mContext, ivGift, itemRoomGiftBean.getGiftImgUrl());
        tvNumber.setVisibility(GONE);
        ivNum.setVisibility(GONE);
        switch (itemRoomGiftBean.getNum()) {
            case "1":
                ivNum.setVisibility(VISIBLE);
                ivNum.setImageResource(R.drawable.gift_num_1);
                break;
            case "10":
                ivNum.setVisibility(VISIBLE);
                ivNum.setImageResource(R.drawable.gift_num_2);
                break;
            case "66":
                ivNum.setVisibility(VISIBLE);
                ivNum.setImageResource(R.drawable.gift_num_66);
                break;
            case "99":
                ivNum.setVisibility(VISIBLE);
                ivNum.setImageResource(R.drawable.gift_num_99);
                break;
            case "188":
                ivNum.setVisibility(VISIBLE);
                ivNum.setImageResource(R.drawable.gift_num_188);
                break;
            case "520":
                ivNum.setVisibility(VISIBLE);
                ivNum.setImageResource(R.drawable.gift_num_520);
                break;
            case "1314":
                ivNum.setVisibility(VISIBLE);
                ivNum.setImageResource(R.drawable.gift_num_1314);
                break;
            case "3344":
                ivNum.setVisibility(VISIBLE);
                ivNum.setImageResource(R.mipmap.img_gift_3344);
                break;
            default:
                tvNumber.setVisibility(VISIBLE);
                tvNumber.setText("  Ｘ  " + itemRoomGiftBean.getNum());
                break;
        }
    }

    public void startAnimation() {
        state = GIFTITEM_SHOW;
        handler.sendEmptyMessageDelayed(0, SHOW_TIME);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getMyTag() {
        return tag;
    }

    public void setMyTag(String tag) {
        this.tag = tag;
    }

    public GiftAnimListener getAnimListener() {
        return animListener;
    }

    public void setAnimListener(GiftAnimListener animListener) {
        this.animListener = animListener;
    }
}
