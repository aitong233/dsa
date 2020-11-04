package com.spadea.xqipao.utils.view.room.approach;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.utilcode.SpanUtils;
import com.spadea.xqipao.data.ApproachBean;
import com.spadea.xqipao.data.RoomInAnimModel;
import com.spadea.xqipao.data.socket.WeekStarInModel;
import com.spadea.xqipao.utils.view.gift.GiftAnimListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemApproachView extends FrameLayout {

    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.riv)
    RoundedImageView riv;
    @BindView(R.id.iv_approach)
    ImageView ivApproach;
    @BindView(R.id.tv_content)
    TextView tvContent;


    public static final int SHOW_TIME = 3000;
    public static final int GIFTITEM_DEFAULT = 0;
    public static final int GIFTITEM_SHOW = 1;

    public int state = GIFTITEM_DEFAULT;
    public int index;
    @BindView(R.id.group_jue)
    Group mGroupJue;
    @BindView(R.id.iv_bg_week_star)
    ImageView mIvBgWeekStar;
    @BindView(R.id.tv_welcome)
    TextView mTvWelcome;
    @BindView(R.id.iv_week_star)
    ImageView mIvWeekStar;
    @BindView(R.id.group_week_star)
    Group mGroupWeekStar;

    private GiftAnimListener animListener;
    private Context mContext;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    handler.removeCallbacksAndMessages(null);
                    state = GIFTITEM_DEFAULT;
                    if (animListener != null)
                        animListener.giftAnimEnd(index);
                    break;
            }
        }
    };


    public ItemApproachView(@NonNull Context context) {
        super(context);
        initView(context, null);
    }

    public ItemApproachView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, null);
    }

    public ItemApproachView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_item_approach_new, this, true);
        ButterKnife.bind(this);
        this.mContext = context;
    }


    public void setData(RoomInAnimModel roomInAnimModel) {
        if (roomInAnimModel.type == 1 && roomInAnimModel.data instanceof ApproachBean) {
            mGroupJue.setVisibility(VISIBLE);
            mGroupWeekStar.setVisibility(GONE);
            ApproachBean approachBean = (ApproachBean) roomInAnimModel.data;
            ImageLoader.loadHead(mContext, riv, approachBean.getHeadPicture());
            tvContent.setText(approachBean.getUserName() + "进入房间");
            switch (approachBean.getNobilityId()) {
                case 1:
                    ivApproach.setImageResource(R.mipmap.img_frame1);
                    ivBg.setImageResource(R.mipmap.bg_zijue_approach);
                    break;
                case 2:
                    ivApproach.setImageResource(R.mipmap.img_frame2);
                    ivBg.setImageResource(R.mipmap.bg_bojue_approach);
                    break;
                case 3:
                    ivApproach.setImageResource(R.mipmap.img_frame3);
                    ivBg.setImageResource(R.mipmap.bg_houjue_approach);
                    break;
                case 4:
                    ivApproach.setImageResource(R.mipmap.img_frame4);
                    ivBg.setImageResource(R.mipmap.bg_gongjue_approach);
                    break;
                case 5:
                    ivApproach.setImageResource(R.mipmap.img_frame5);
                    ivBg.setImageResource(R.mipmap.bg_wangjue_approach);
                    break;
                case 6:
                    ivApproach.setImageResource(R.mipmap.img_frame6);
                    ivBg.setImageResource(R.mipmap.bg_diwang_approach);
                    break;
            }
        } else if (roomInAnimModel.type == 2 && roomInAnimModel.data instanceof WeekStarInModel) {
            mGroupJue.setVisibility(GONE);
            mGroupWeekStar.setVisibility(VISIBLE);
            WeekStarInModel weekStarInModel = (WeekStarInModel) roomInAnimModel.data;
            String colorHex="#FFE360";
            if (weekStarInModel.getData().getType() == 1) {
                if (weekStarInModel.getData().getLevel() == 1) {
                    colorHex="#EAD730";
                    mIvBgWeekStar.setImageResource(R.drawable.ic_week_star_1);
                } else if (weekStarInModel.getData().getLevel() == 2) {
                    colorHex="#ACB7D6";
                    mIvBgWeekStar.setImageResource(R.drawable.ic_week_star_2);
                }else if (weekStarInModel.getData().getLevel() == 3) {
                    colorHex="#E1E4A3";
                    mIvBgWeekStar.setImageResource(R.drawable.ic_week_star_3);
                }
            } else {
                mIvBgWeekStar.setImageResource(R.drawable.ic_week_star_caifu);
            }
            mTvWelcome.setText(new SpanUtils().append("欢迎 ").append(weekStarInModel.getData().getNickname()).setForegroundColor(Color.parseColor(colorHex)).append(" 进入房间").create());
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


    public GiftAnimListener getAnimListener() {
        return animListener;
    }

    public void setAnimListener(GiftAnimListener animListener) {
        this.animListener = animListener;
    }

}
