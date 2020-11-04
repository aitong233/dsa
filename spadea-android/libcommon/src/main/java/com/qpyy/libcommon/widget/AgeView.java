package com.qpyy.libcommon.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qpyy.libcommon.R;
import com.qpyy.libcommon.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgeView extends FrameLayout {

    @BindView(R2.id.tv_age)
    TextView tvAge;
    @BindView(R2.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R2.id.iv_sex)
    ImageView ivSex;

    private Context mContext;


    public AgeView(@NonNull Context context) {
        super(context);
        initView(context);
    }


    public AgeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AgeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(getContext()).inflate(R.layout.view_age, this);
        ButterKnife.bind(this);
    }


    public void setData(String sex, String age) {
        tvAge.setText(age);
        if ("1".equals(sex)) {
            ivSex.setImageLevel(1);
            rlRoot.setBackgroundResource(R.drawable.bg_sex_male);
        } else {
            ivSex.setImageLevel(2);
            rlRoot.setBackgroundResource(R.drawable.bg_sex_girl);
        }
    }

}
