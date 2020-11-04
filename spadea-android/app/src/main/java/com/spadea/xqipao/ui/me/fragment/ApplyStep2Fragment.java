package com.spadea.xqipao.ui.me.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.spadea.yuyin.R;
import com.spadea.xqipao.data.even.ApplyStepChangeEvent;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.widget.QualificationApplyStateView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.fragment
 * 创建人 王欧
 * 创建时间 2020/5/19 6:19 PM
 * 描述 describe
 */
public class ApplyStep2Fragment extends BaseFragment {
    @BindView(R.id.step_view)
    QualificationApplyStateView mStepView;
    @BindView(R.id.iv_sample)
    RoundedImageView mIvSample;
    @BindView(R.id.iv_avatar)
    RoundedImageView mIvAvatar;
    @BindView(R.id.iv_set_avatar)
    ImageView mIvSetAvatar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        mStepView.setStep(2);
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragement_apply_step2;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @OnClick({R.id.iv_avatar, R.id.iv_set_avatar, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:
                break;
            case R.id.iv_set_avatar:
                break;
            case R.id.tv_submit:
                EventBus.getDefault().post(new ApplyStepChangeEvent(3));
                break;
        }
    }
}
