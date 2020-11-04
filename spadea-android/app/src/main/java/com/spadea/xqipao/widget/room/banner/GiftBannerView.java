package com.spadea.xqipao.widget.room.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Layout;
import android.text.Spanned;
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
import com.spadea.xqipao.data.socket.RoomGiftModel;

import java.util.LinkedList;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiftBannerView extends FrameLayout implements Animation.AnimationListener, View.OnClickListener {

    @BindView(R.id.tv_content)
    TextView tvContent;

    private static Queue<RoomGiftModel> queue = new LinkedList<RoomGiftModel>();
    private boolean b = false;
    private Context mContext;
    private TranslateAnimation translateAnimation;
    private RoomGiftModel mRoomGiftModel;
    private GiftBannerViewOnClickListener mGiftBannerViewOnClickListener;


    public GiftBannerView(@NonNull Context context) {
        this(context, null);
    }

    public GiftBannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GiftBannerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_gift_banner, this, true);
        ButterKnife.bind(this);
        setOnClickListener(this);
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


    public void load(RoomGiftModel data) {
        if (queue == null) {
            return;
        }
        if (queue.size() == 0 && !b) {
            showGift(data);
        } else {
            queue.add(data);
        }
    }

    private void showGift(RoomGiftModel data) {
        mRoomGiftModel = data;
        b = true;
        setVisibility(VISIBLE);
        Spanned spanned = Html.fromHtml(data.getText());
        tvContent.setText(spanned);
        int length = spanned.length();
        float xx = Layout.getDesiredWidth(spanned, tvContent.getPaint());
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
        RoomGiftModel gift = getGift();
        if (gift != null) {
            showGift(gift);
        } else {
            setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    private RoomGiftModel getGift() {
        RoomGiftModel data = null;
        if (!isEmpty()) {
            // 获取队列首个礼物实体
            data = queue.poll();
            // 移除队列首个礼物实体
        }
        return data;
    }

    @Override
    public void onClick(View v) {
        if (mGiftBannerViewOnClickListener != null && mRoomGiftModel != null) {
            mGiftBannerViewOnClickListener.onGiftBannerClick(mRoomGiftModel.getRoom_id());
        }
    }

    public interface GiftBannerViewOnClickListener {
        void onGiftBannerClick(String roomId);
    }

    public void setmGiftBannerViewOnClickListener(GiftBannerViewOnClickListener mGiftBannerViewOnClickListener) {
        this.mGiftBannerViewOnClickListener = mGiftBannerViewOnClickListener;
    }

    /**
     * 礼物是否为空
     */
    public boolean isEmpty() {
        return queue == null || queue.size() == 0;
    }
}
