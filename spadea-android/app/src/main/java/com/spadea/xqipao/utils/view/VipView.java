package com.spadea.xqipao.utils.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.spadea.yuyin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VipView extends FrameLayout {

    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_right)
    TextView tvRight;
    private Context mContext;


    public VipView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public VipView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public VipView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(getContext()).inflate(R.layout.view_vip, this);
        ButterKnife.bind(this);
        seekbar.setEnabled(false);
    }

    public void setCurrentLevel(String current, int rankId) {
        tvLeft.setText(current);
        if (rankId <= 10) {
            tvLeft.setBackgroundResource(R.mipmap.icon_vip10);
        } else if (rankId > 10 && rankId <= 20) {
            tvLeft.setBackgroundResource(R.mipmap.icon_vip20);
        } else if (rankId > 20 && rankId <= 30) {
            tvLeft.setBackgroundResource(R.mipmap.icon_vip30);
        } else if (rankId > 30 && rankId <= 40) {
            tvLeft.setBackgroundResource(R.mipmap.icon_vip40);
        } else {
            tvLeft.setBackgroundResource(R.mipmap.icon_vip50);
        }
    }

    public void setNextLevel(String next, int rankId) {
        rankId++;
        tvRight.setText(next);
        if (rankId <= 10) {
            tvRight.setBackgroundResource(R.mipmap.icon_vip10);
        } else if (rankId > 10 && rankId <= 20) {
            tvRight.setBackgroundResource(R.mipmap.icon_vip20);
        } else if (rankId > 20 && rankId <= 30) {
            tvRight.setBackgroundResource(R.mipmap.icon_vip30);
        } else if (rankId > 30 && rankId <= 40) {
            tvRight.setBackgroundResource(R.mipmap.icon_vip40);
        } else {
            tvRight.setBackgroundResource(R.mipmap.icon_vip50);
        }
    }



    /**
     * diff/(next-current)
     */
    public void setSeekbar(int percent) {
        seekbar.setProgress(percent);
    }



}
