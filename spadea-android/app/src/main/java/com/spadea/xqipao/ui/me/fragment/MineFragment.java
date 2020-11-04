package com.spadea.xqipao.ui.me.fragment;

import android.view.View;

import com.spadea.yuyin.R;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.me.contacter.MineContacts;
import com.spadea.xqipao.ui.me.presenter.MinePresenter;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.fragment
 * 创建人 王欧
 * 创建时间 2020/5/26 2:19 AM
 * 描述 describe
 */
public class MineFragment extends BaseFragment<MinePresenter> implements MineContacts.View {
    @Override
    protected MinePresenter bindPresenter() {
        return new MinePresenter(this, getActivity());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }
}
