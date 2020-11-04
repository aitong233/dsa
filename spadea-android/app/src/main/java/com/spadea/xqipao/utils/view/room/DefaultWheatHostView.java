package com.spadea.xqipao.utils.view.room;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.opensource.svgaplayer.SVGAImageView;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.data.FaceBean;
import com.spadea.xqipao.data.RoomPitBean;
import com.qpyy.libcommon.utils.GiftAnimatorUtil;
import com.qpyy.libcommon.widget.animator.ExplosionField;
import com.spadea.xqipao.utils.view.GameImgView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * icon_lock   锁坑       icon_def_up  上麦
 */
public class DefaultWheatHostView extends BaseWheatView {


    private Context mContext;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.iv_ripple1)
    SVGAImageView ivRipple1;
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
    @BindView(R.id.rl_user)
    RelativeLayout rlUser;
    @BindView(R.id.game_imgview)
    GameImgView gameImgView;


    public DefaultWheatHostView(@NonNull Context context) {
        super(context);
        initView(context);
    }


    public DefaultWheatHostView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DefaultWheatHostView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    public void showVolumeTips(boolean b) {
        if (b) {
            if (!ivRipple1.isAnimating()) {
                ivRipple1.startAnimation();
            }
        } else {
            ivRipple1.stopAnimation();
        }
    }

    private void initView(Context context) {
        this.mContext = context;
        this.isHostWheat = true;
        LayoutInflater.from(context).inflate(R.layout.view_default_wheat_host, this, true);
        ButterKnife.bind(this);
    }

    @Override
    public void setData(RoomPitBean item) {
        this.mRoomPitBean = item;
        this.mPitNumber = item.getPit_number();
        if (TextUtils.isEmpty(item.getUser_id())) {
            gameImgView.setVisibility(GONE);
            gameImgView.overGame();
            if (item.getState().equals("1")) {
                roundedImageView.setImageResource(R.mipmap.icon_lock);
            } else {
                roundedImageView.setImageResource(R.mipmap.icon_def_up);
            }
            ivMic1.setVisibility(GONE);
            ivRipple1.stopAnimation();
            tvUserName.setVisibility(INVISIBLE);
            expressionImgView.remove();
        } else {
            gameImgView.setVisibility(VISIBLE);
            if (item.getShutup().equals("1")) {
                ivMic1.setVisibility(VISIBLE);
                ivRipple1.stopAnimation();
            } else {
                ivMic1.setVisibility(GONE);
                if (item.getVoice().equals("0")) {
                    ivRipple1.stopAnimation();
                } else {
                    ivRipple1.startAnimation();
                }
            }
            ImageLoader.loadHead(MyApplication.getInstance(), roundedImageView, item.getHead_picture());
            tvUserName.setVisibility(VISIBLE);
            tvUserName.setText(item.getNickname());
        }
        tvCover1.setText(item.getXin_dong());
    }

    @Override
    public void showXd(int cardiac) {
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
            roundedImageView.setImageResource(R.mipmap.icon_def_up);
        }
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

    }
}

