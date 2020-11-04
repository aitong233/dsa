package com.spadea.xqipao.utils.view.room;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.opensource.svgaplayer.SVGAImageView;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.utilcode.ConvertUtils;
import com.spadea.xqipao.data.FaceBean;
import com.spadea.xqipao.data.RoomPitBean;
import com.qpyy.libcommon.utils.GiftAnimatorUtil;
import com.spadea.xqipao.utils.LogUtils;
import com.qpyy.libcommon.widget.animator.ExplosionField;
import com.spadea.xqipao.utils.view.GameImgView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DefaultWheatView extends BaseWheatView {


    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.iv_ripple)
    SVGAImageView ivRipple;
    @BindView(R.id.roundedimage_view)
    RoundedImageView roundedImageView;
    @BindView(R.id.iv_expression)
    ExpressionImgView expressionImgView;
    @BindView(R.id.tv_cover1)
    TextView tvCover1;
    @BindView(R.id.iv_mic1)
    ImageView ivMic1;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.game_imgview)
    GameImgView gameImgView;
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;
    @BindView(R.id.iv_head_bg)
    ImageView ivHeadBg;
    CountDownTimer mCountDownTimer;
    @BindView(R.id.iv_frame)
    ImageView ivFrame;

    private int defaultPic = R.mipmap.icon_def_up;
    private boolean showHostTag;
    private boolean mShowDecoration;
    private boolean showUserName;
    private boolean hideXd;


    public DefaultWheatView(@NonNull Context context) {
        super(context);
        initView(context, null);
    }


    public DefaultWheatView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public DefaultWheatView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @Override
    public void showVolumeTips(boolean b) {
        if (b) {
            if (!ivRipple.isAnimating()) {
                ivRipple.startAnimation();
            }
        } else {
            ivRipple.stopAnimation();
        }
    }

    private void initView(Context context, AttributeSet attrs) {
        this.mContext = context;
        this.isHostWheat = false;
        LayoutInflater.from(context).inflate(R.layout.view_default_wheat, this, true);
        ButterKnife.bind(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DefaultWheatView);
        defaultPic = typedArray.getResourceId(R.styleable.DefaultWheatView_default_pic, R.mipmap.icon_def_up);
        mShowDecoration = typedArray.getBoolean(R.styleable.DefaultWheatView_show_decoration, true);
        showHostTag = typedArray.getBoolean(R.styleable.DefaultWheatView_show_host_tag, false);
        showUserName = typedArray.getBoolean(R.styleable.DefaultWheatView_showUserName, true);
        hideXd =typedArray.getBoolean(R.styleable.DefaultWheatView_hide_xd,false);
        float rippleScale = typedArray.getFloat(R.styleable.DefaultWheatView_rippleScale, 1);
        int headBg = typedArray.getResourceId(R.styleable.DefaultWheatView_head_bg, -1);
        float nameTextSize = typedArray.getDimensionPixelSize(R.styleable.DefaultWheatView_name_text_size, ConvertUtils.sp2px(12));
        typedArray.recycle();
        ivRipple.setScaleX(rippleScale);
        ivRipple.setScaleY(rippleScale);
        roundedImageView.setImageResource(defaultPic);
        if (headBg == -1) {
            ivHeadBg.setVisibility(GONE);
        } else {
            ivHeadBg.setVisibility(VISIBLE);
            ivHeadBg.setBackgroundResource(headBg);
        }
        tvUserName.setTextSize(TypedValue.COMPLEX_UNIT_PX, nameTextSize);
    }

    @Override
    public void setData(RoomPitBean item) {
        this.mRoomPitBean = item;
        mPitNumber = item.getPit_number();
        ivFrame.setVisibility(GONE);
        countDownTime(String.valueOf(item.getCount_down()));
        if (TextUtils.isEmpty(item.getUser_id())) {
            gameImgView.setVisibility(GONE);
            gameImgView.overGame();
            if (item.getState().equals("1")) {
                roundedImageView.setImageResource(R.mipmap.icon_lock);
            } else {
                roundedImageView.setImageResource(defaultPic);
            }
            ivMic1.setVisibility(GONE);
            tvUserName.setVisibility(INVISIBLE);
            ivRipple.stopAnimation();
            expressionImgView.remove();
        } else {
            gameImgView.setVisibility(VISIBLE);
            if (item.getShutup().equals("1")) {
                ivMic1.setVisibility(VISIBLE);
                ivRipple.stopAnimation();
            } else {
                ivMic1.setVisibility(GONE);
                if (item.getVoice().equals("0")) {
                    ivRipple.stopAnimation();
                } else {
                    ivRipple.startAnimation();
                }
            }
            if (mShowDecoration && item.getRank_info() != null && !TextUtils.isEmpty(item.getRank_info().getPicture())) {
                ivFrame.setVisibility(VISIBLE);
                ivRipple.setScaleX(1.05f);
                ivRipple.setScaleY(1.05f);
                ImageLoader.loadHeadWithoutPlaceholder(MyApplication.getInstance(), ivFrame, item.getRank_info().getPicture());
            } else {
                ivRipple.setScaleX(1f);
                ivRipple.setScaleY(1f);
                ivFrame.setVisibility(GONE);
            }
            ImageLoader.loadHead(MyApplication.getInstance(), roundedImageView, item.getHead_picture());
            tvUserName.setVisibility(VISIBLE);
            tvUserName.setText(item.getNickname());
        }

        tvCover1.setText(item.getXin_dong());
        if (!showUserName){
            tvUserName.setVisibility(GONE);
        }
    }

    @Override
    public void showXd(int cardiac) {
        if (hideXd){
            tvCover1.setVisibility(GONE);
            return;
        }
        if (cardiac == 1) {
            tvCover1.setVisibility(VISIBLE);
        } else {
            tvCover1.setVisibility(GONE);
        }
    }

    @Override
    public void startGame() {
        gameImgView.startGame();
    }

    @Override
    public void openGame(String qiu1, String qiu2, String qiu3) {
        gameImgView.setGameResult(qiu1, qiu2, qiu3);
    }

    @Override
    public void overGame() {
        gameImgView.overGame();
    }


    @OnClick(R.id.roundedimage_view)
    public void onClick(View view) {
        if (mRoomPitBean != null && mBaseWheatOnClickListener != null) {
            if (!TextUtils.isEmpty(mRoomPitBean.getUser_id())) {
                mBaseWheatOnClickListener.wheatHeadPicture(isHostWheat, mRoomPitBean.getUser_id());
            } else if (mRoomPitBean.getState().equals("1")) {
                mBaseWheatOnClickListener.wheatLock(isHostWheat, mPitNumber);
            } else {
                mBaseWheatOnClickListener.wheatAdd(isHostWheat, mPitNumber);
            }
        }
    }


    @Override
    public void setExpression(FaceBean faceBean) {
        expressionImgView.addData(faceBean);
    }

    @Override
    public void showGift(String url) {
        ivPic.setVisibility(VISIBLE);
        Glide.with(mContext).load(url).into(ivPic);
        ObjectAnimator tada = GiftAnimatorUtil.tada(ivPic);
        tada.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                new ExplosionField(mContext).explode(ivPic, new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ivPic.setVisibility(GONE);
                    }
                });
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        tada.start();
    }

    @Override
    public void deleteCardiac() {
        tvCover1.setText("0");
    }

    @Override
    public void closePit(String action) {
        if (action.equals("1")) {
            roundedImageView.setImageResource(R.mipmap.icon_lock);
        } else {
            roundedImageView.setImageResource(defaultPic);
        }
        this.mRoomPitBean.setState(action);
    }

    @Override
    public void setShutup(int shutup) {
        if (shutup == 1) {
            ivMic1.setVisibility(VISIBLE);
        } else {
            ivMic1.setVisibility(GONE);
        }
    }

    @Override
    public void setWheatCardiac(String cardiac) {
        tvCover1.setText(cardiac);
    }

    @Override
    public void countDownTime(String time) {
        try {
            int second = Integer.parseInt(time);
            if (second <= 0) {
                tvCountDown.setVisibility(GONE);
                releaseCountDownTimer();
                return;
            }
            releaseCountDownTimer();
            mCountDownTimer = new CountDownTimer(second * 1000L, 1000L) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (tvCountDown != null) {
                        tvCountDown.setVisibility(VISIBLE);
                        tvCountDown.setText(String.valueOf(millisUntilFinished / 1000));
                    }
                }

                @Override
                public void onFinish() {
                    tvCountDown.setVisibility(GONE);
                }
            };
            mCountDownTimer.start();
        } catch (Exception e) {
            LogUtils.e("countDownTime", e);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        releaseCountDownTimer();
        super.onDetachedFromWindow();
    }

    private void releaseCountDownTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }
}
