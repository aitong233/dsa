package com.qpyy.room.widget;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.bean.RoomGiveGiftModel;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;

import java.util.LinkedList;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.widget
 * 创建人 王欧
 * 创建时间 2020/8/12 10:01 AM
 * 描述 小猫钓鱼飘窗
 */
public class SmallGiftAnimView extends ConstraintLayout implements Animation.AnimationListener {

    @BindView(R2.id.riv_avatar)
    RoundedImageView mRivAvatar;
    @BindView(R2.id.tv_number)
    TextView mTvNumber;
    @BindView(R2.id.iv_gift)
    ImageView mIvGift;
    @BindView(R2.id.tv_name)
    TextView mTvName;
    @BindView(R2.id.tv_content)
    TextView mTvContent;
    private Animation mAnimation;
    public boolean animEnded = true;

    public SmallGiftAnimView(Context context) {
        this(context, null);
    }

    public SmallGiftAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.room_view_small_gift_anim, this);
        ButterKnife.bind(this);
        setVisibility(GONE);
        mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.room_anim_set_big_gift);
        mAnimation.setFillAfter(true);
        mAnimation.setAnimationListener(this);
    }

    public void showAnim() {
        if (SpUtils.getOpenEffect() == 0) {
            return;
        }
        if (queue == null || queue.size() == 0) {
            return;
        }
        if (animEnded && isAttachedToWindow()) {
            RoomGiveGiftModel.GiftListBean animBean = queue.poll();
            if (animBean != null) {
                ImageUtils.loadImageView(animBean.getPicture(), mIvGift);
                ImageUtils.loadImageView(animBean.getHead_picture(), mRivAvatar);
                mTvName.setText(Html.fromHtml(animBean.getNickname_from()));
                mTvContent.setText(String.format("送给%s", animBean.getNickname_to()));
                mTvNumber.setText(animBean.getNumber());
            }
            setVisibility(VISIBLE);
            animEnded = false;
            this.startAnimation(mAnimation);
        }
    }

    public void addAnim(RoomGiveGiftModel.GiftListBean bean) {
        if (SpUtils.getOpenEffect() == 0) {
            return;
        }
        if (queue.size() == 0) {
            queue.add(bean);
            showAnim();
        } else {
            queue.add(bean);
        }
    }

    private Queue<RoomGiveGiftModel.GiftListBean> queue = new LinkedList<>();

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        animEnded = true;
        postDelayed(new Runnable() {
            @Override
            public void run() {
                showAnim();
            }
        }, 200);

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    /**
     * 关闭特效
     */
    public void closeEffect(){
        //清空队列
        queue.clear();
        //关闭动画
        if(mAnimation !=null && !animEnded){
            mAnimation.cancel();
            mAnimation = null;
        }
    }
}
