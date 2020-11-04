package com.spadea.xqipao.utils.view.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.spadea.yuyin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContributionView extends FrameLayout {


    @BindView(R.id.tv_def_contribution_num)
    TextView tvDefContributionNum;
    private Context mContext;

    public ContributionView(@NonNull Context context) {
        this(context, null);
    }

    public ContributionView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContributionView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(getContext()).inflate(R.layout.view_room_contribution, this);
        ButterKnife.bind(this);
    }


    public void setData(String data) {
        tvDefContributionNum.setText(data);
    }
}
