package com.spadea.xqipao.ui.me.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spadea.yuyin.util.utilcode.ClipboardUtils;
import com.spadea.xqipao.data.UserInfoDataModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class UserDataFragment extends BaseFragment {


    @BindView(R.id.ll_auth)
    LinearLayout llAuth;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_copy)
    TextView tvCopy;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_occupation)
    TextView tvOccupation;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_user_number)
    TextView tvUserNumber;

    private UserInfoDataModel userInfoDataModel;

    public static Fragment newInstance(UserInfoDataModel data) {
        UserDataFragment userDataFragment = new UserDataFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        userDataFragment.setArguments(bundle);
        return userDataFragment;
    }

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        userInfoDataModel = (UserInfoDataModel) getArguments().getSerializable("data");
        if (userInfoDataModel.getAuth().equals("0")) {
            llAuth.setVisibility(View.GONE);
            tvUserNumber.setVisibility(View.GONE);
        } else {
            llAuth.setVisibility(View.VISIBLE);
            tvUserNumber.setVisibility(View.VISIBLE);
        }
        tvNickName.setText("昵称：" + userInfoDataModel.getNickname());
        tvId.setText("ID：" + userInfoDataModel.getUser_code());
        tvAge.setText("年龄：" + userInfoDataModel.getAge());
        tvOccupation.setText("职业：" + userInfoDataModel.getProfession());
        tvCity.setText("城市：" + userInfoDataModel.getCity());
    }

    @Override
    protected void initView(View rootView) {

    }

    @OnClick(R.id.tv_copy)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_copy:
                if (userInfoDataModel != null) {
                    ClipboardUtils.copyText(userInfoDataModel.getUser_code());
                    ToastUtils.showShort("复制成功");
                }
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_data;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


}
