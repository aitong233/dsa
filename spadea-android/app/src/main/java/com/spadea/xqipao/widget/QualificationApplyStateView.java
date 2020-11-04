package com.spadea.xqipao.widget;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.spadea.yuyin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.widget
 * 创建人 王欧
 * 创建时间 2020/5/18 2:34 PM
 * 描述 describe
 */
public class QualificationApplyStateView extends ConstraintLayout {
    @BindView(R.id.iv_step1)
    ImageView mIvStep1;
    @BindView(R.id.iv_step2)
    ImageView mIvStep2;
    @BindView(R.id.iv_step3)
    ImageView mIvStep3;
    @BindView(R.id.iv_state)
    ImageView mIvState;
    @BindView(R.id.tv_step1)
    TextView mTvStep1;
    @BindView(R.id.tv_step2)
    TextView mTvStep2;
    @BindView(R.id.tv_step3)
    TextView mTvStep3;

    public QualificationApplyStateView(Context context) {
        this(context, null);
    }

    public QualificationApplyStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_qualification_apply_state, this);
        ButterKnife.bind(this);
    }

    public void setStep(int step) {
        switch (step) {
            case 1:
                mIvState.setImageResource(R.mipmap.image_apply_step_1);
                mIvStep1.setImageResource(R.mipmap.ic_apply_step_1_doing);
                mIvStep2.setImageResource(R.mipmap.ic_apply_step_2);
                mIvStep3.setImageResource(R.mipmap.ic_apply_step_3);
                mTvStep1.setTextColor(getResources().getColor(R.color.color_main));
                mTvStep2.setTextColor(getResources().getColor(R.color.color_9c9c9c));
                mTvStep3.setTextColor(getResources().getColor(R.color.color_9c9c9c));
                mTvStep1.getPaint().setFakeBoldText(true);
                mTvStep2.getPaint().setFakeBoldText(false);
                mTvStep3.getPaint().setFakeBoldText(false);
                break;
            case 2:
                mIvState.setImageResource(R.mipmap.image_apply_step_2);
                mIvStep1.setImageResource(R.mipmap.ic_apply_step_1_done);
                mIvStep2.setImageResource(R.mipmap.ic_apply_step_2_doing);
                mIvStep3.setImageResource(R.mipmap.ic_apply_step_3);
                mTvStep1.setTextColor(getResources().getColor(R.color.color_545454));
                mTvStep2.setTextColor(getResources().getColor(R.color.color_main));
                mTvStep3.setTextColor(getResources().getColor(R.color.color_9c9c9c));
                mTvStep1.getPaint().setFakeBoldText(false);
                mTvStep2.getPaint().setFakeBoldText(true);
                mTvStep3.getPaint().setFakeBoldText(false);
                break;
            case 3:
                mIvState.setImageResource(R.mipmap.image_apply_step_3);
                mIvStep1.setImageResource(R.mipmap.ic_apply_step_1_done);
                mIvStep2.setImageResource(R.mipmap.ic_apply_step_2_done);
                mIvStep3.setImageResource(R.mipmap.ic_apply_step_3_doing);
                mTvStep1.setTextColor(getResources().getColor(R.color.color_545454));
                mTvStep2.setTextColor(getResources().getColor(R.color.color_545454));
                mTvStep3.setTextColor(getResources().getColor(R.color.color_main));
                mTvStep1.getPaint().setFakeBoldText(false);
                mTvStep2.getPaint().setFakeBoldText(false);
                mTvStep3.getPaint().setFakeBoldText(true);
                break;
        }
    }
}
