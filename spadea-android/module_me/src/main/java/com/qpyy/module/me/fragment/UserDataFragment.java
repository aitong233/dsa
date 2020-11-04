package com.qpyy.module.me.fragment;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.bean.UserHomeResp;
import com.qpyy.module.me.contacts.UserDetailsConacts;
import com.qpyy.module.me.presenter.UserDetailsPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人资料
 */
public class UserDataFragment extends BaseMvpFragment<UserDetailsPresenter> implements UserDetailsConacts.View {


    @BindView(R2.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R2.id.tv_id)
    TextView tvId;
    @BindView(R2.id.tv_liang)
    ImageView tvLiang;
    @BindView(R2.id.tv_copy)
    TextView tvCopy;
    @BindView(R2.id.tv_age)
    TextView tvAge;
    @BindView(R2.id.tv_occupation)
    TextView tvOccupation;
    @BindView(R2.id.tv_city)
    TextView tvCity;

    private UserHomeResp mUserHomeResp;

    private String userId;


    public static UserDataFragment newInstance(String userId) {
        UserDataFragment fragment = new UserDataFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        userId = arguments.getString("userId");
    }

    @Override
    protected UserDetailsPresenter bindPresenter() {
        return new UserDetailsPresenter(this, getActivity());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onResume() {
        super.onResume();
        MvpPre.getUserDetails(userId,null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.me_fagment_user_data;
    }


    @OnClick(R2.id.tv_copy)
    public void onClick(View view) {
        if (mUserHomeResp != null) {
            ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setPrimaryClip(ClipData.newPlainText("text", mUserHomeResp.getUser_code()));
            ToastUtils.show("已复制到粘贴板");
        }
    }

    @Override
    public void setUserDetails(UserHomeResp data) {
        mUserHomeResp = data;
        if (mUserHomeResp != null) {
            tvNickName.setText(mUserHomeResp.getNickname());
            tvId.setText(mUserHomeResp.getUser_code());
            if ("1".equals(mUserHomeResp.getGood_number())) {
                tvLiang.setVisibility(View.VISIBLE);
            } else {
                tvLiang.setVisibility(View.GONE);
            }
            tvAge.setText(mUserHomeResp.getAge());
            tvOccupation.setText(mUserHomeResp.getProfession());
            tvCity.setText(mUserHomeResp.getCity());
        }
    }

    @Override
    public void onFail() {

    }

    @Override
    public void followSuccess(String type) {

    }

    @Override
    public void addBlackUserSuccess() {

    }
}
