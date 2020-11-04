package com.qpyy.module.me.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FollowView extends FrameLayout {

    @BindView(R2.id.rl_follow)
    RelativeLayout rlFollow;
    @BindView(R2.id.rl_concerned)
    RelativeLayout rlConcerned;

    private Context mContext;
    private OnFollowClickListener mOnFollowClickListener;

    public FollowView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public FollowView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FollowView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(getContext()).inflate(R.layout.me_view_follow, this);
        ButterKnife.bind(this);
    }


    @OnClick({R2.id.rl_follow, R2.id.rl_concerned})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rl_follow) {
            if (mOnFollowClickListener != null) {
                mOnFollowClickListener.addFollow();
            }
        } else if (id == R.id.rl_concerned) {
            if (mOnFollowClickListener != null) {
                mOnFollowClickListener.cancelFollow();
            }
        }
    }


    public void setFollow(String follow) {
        if ("1".equals(follow)) {
            rlFollow.setVisibility(GONE);
            rlConcerned.setVisibility(VISIBLE);
        } else {
            rlFollow.setVisibility(VISIBLE);
            rlConcerned.setVisibility(GONE);
        }
    }

    public void setOnFollowClickListener(OnFollowClickListener onFollowClickListener) {
        this.mOnFollowClickListener = onFollowClickListener;
    }


    public interface OnFollowClickListener {
        void addFollow();

        void cancelFollow();
    }

}
