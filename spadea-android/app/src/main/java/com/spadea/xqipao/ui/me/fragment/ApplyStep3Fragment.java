package com.spadea.xqipao.ui.me.fragment;

import android.view.View;
import android.widget.TextView;

import com.spadea.yuyin.R;
import com.spadea.xqipao.data.even.BackHomeEvent;
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
 * 创建时间 2020/5/19 6:20 PM
 * 描述 describe
 */
public class ApplyStep3Fragment extends BaseFragment {
    @BindView(R.id.step_view)
    QualificationApplyStateView mStepView;
    @BindView(R.id.tv_tip_success)
    TextView mTvTipSuccess;
    @BindView(R.id.tv_finish)
    TextView mTvFinish;

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        mStepView.setStep(3);
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragement_apply_step3;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @OnClick(R.id.tv_finish)
    public void onViewClicked() {
        EventBus.getDefault().post(new BackHomeEvent());
        getActivity().finish();
    }
}
