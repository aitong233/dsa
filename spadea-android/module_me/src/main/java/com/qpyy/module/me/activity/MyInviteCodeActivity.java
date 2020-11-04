package com.qpyy.module.me.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BaseAppCompatActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.me.activity
 * 创建人 王欧
 * 创建时间 2020/8/1 5:36 PM
 * 描述 describe
 */
@Route(path = ARouteConstants.MY_INVITE_CODE)
public class MyInviteCodeActivity extends BaseAppCompatActivity {
    @Autowired
    public String userCode;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.tv_user_code)
    TextView mTvUserCode;

    @Override
    protected void initData() {
        mTvTitle.setText("我的邀请码");
        mTvUserCode.setText(userCode);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.me_activity_my_invite_code;
    }

    @OnClick({R2.id.iv_back, R2.id.iv_copy})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.iv_copy) {
            if (!TextUtils.isEmpty(userCode)) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setPrimaryClip(ClipData.newPlainText("text", userCode));
                ToastUtils.show("已复制到粘贴板");
            }
        }
    }
}
