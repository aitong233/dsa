package com.spadea.xqipao.ui.login.activity;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.lihang.ShadowLayout;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.widget.ClearEditText;
import com.qpyy.module.me.bean.UserFillResp;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.home.activity.HomeActivity;
import com.spadea.xqipao.ui.login.contacter.ImproveInfoContacts;
import com.spadea.xqipao.ui.login.presenter.ImproveInfoPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.login.activity
 * 创建人 王欧
 * 创建时间 2020/7/2 5:46 PM
 * 描述 describe
 */
@Route(path = ARouters.IMPROVE_INFO)
public class ImproveInfoActivity extends BaseMvpActivity<ImproveInfoPresenter> implements ImproveInfoContacts.View {
    @BindView(R.id.ed_nick_name)
    ClearEditText mEdNickName;
    @BindView(R.id.et_invite_code)
    ClearEditText mEtInviteCode;
    @BindView(R.id.tv_commit)
    TextView mTvCommit;
    @BindView(R.id.rl_nan)
    RelativeLayout mRlNan;
    @BindView(R.id.rl_nv)
    RelativeLayout mRlNv;
    @BindView(R.id.sl_nan)
    ShadowLayout mSlNan;
    @BindView(R.id.sl_nv)
    ShadowLayout mSlNv;

    private String sex;

    @Autowired
    public String nickname;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        mEdNickName.setText(nickname);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_improve_info;
    }

    @Override
    protected ImproveInfoPresenter bindPresenter() {
        return new ImproveInfoPresenter(this, this);
    }


    @OnClick({R.id.tv_commit, R.id.rl_nv, R.id.rl_nan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                nickname = mEdNickName.getText().toString().trim();
                String inviteCode = mEtInviteCode.getText().toString().trim();
                if (TextUtils.isEmpty(nickname)) {
                    ToastUtils.showShort("请输入昵称");
                    return;
                }
                if (TextUtils.isEmpty(sex)) {
                    ToastUtils.showShort("请选择性别");
                    return;
                }
                MvpPre.fill(inviteCode, nickname, sex);
                break;
            case R.id.rl_nv:
                mTvCommit.setEnabled(true);
                mRlNan.setSelected(false);
                mRlNv.setSelected(true);
                mSlNv.setmShadowColor(Color.parseColor("#7A6765FF"));
                mSlNan.setmShadowColor(Color.parseColor("#1A000000"));
                sex = "2";
                break;
            case R.id.rl_nan:
                mTvCommit.setEnabled(true);
                mRlNan.setSelected(true);
                mRlNv.setSelected(false);
                mSlNan.setmShadowColor(Color.parseColor("#7A6765FF"));
                mSlNv.setmShadowColor(Color.parseColor("#1A000000"));
                sex = "1";
                break;

        }

    }

    @Override
    public void success(UserFillResp resp) {
        UserBean user = MyApplication.getInstance().getUser();
        user.setSex(Integer.parseInt(sex));
        user.setNickname(nickname);
        try {
            user.setSex(Integer.parseInt(sex));
        } catch (Exception e) {
            e.printStackTrace();
        }
        MyApplication.getInstance().setUser(user);
        ToastUtils.showShort("设置成功");
        Intent intent = new Intent(this, HomeActivity.class);
        if (resp != null && !"0".equals(resp.getGift_bag_id())) {
            intent.putExtra("giftBagUrl", resp.getGift_bag_url());
        }
        startActivity(intent);
        finish();
    }
}
