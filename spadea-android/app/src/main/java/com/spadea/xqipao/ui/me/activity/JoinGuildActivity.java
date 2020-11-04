package com.spadea.xqipao.ui.me.activity;

import android.support.constraint.Group;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module.me.bean.GuildResp;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.LogUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.JoinGuildContacts;
import com.spadea.xqipao.ui.me.presenter.JoinGuildPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.activity
 * 创建人 王欧
 * 创建时间 2020/5/8 2:30 PM
 * 描述 describe
 */
@Route(path = ARouteConstants.JOIN_GUILD, name = "加入公会")
public class JoinGuildActivity extends BaseActivity<JoinGuildPresenter> implements JoinGuildContacts.View {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_id)
    EditText mEtId;
    @BindView(R.id.tv_guild)
    TextView mTvGuild;
    @BindView(R.id.group_info)
    Group mGroupInfo;
    @BindView(R.id.tv_action)
    TextView mTvAction;
    @BindView(R.id.iv_shadow)
    ImageView mIvShadow;

    private GuildResp mGuildResp;


    public JoinGuildActivity() {
        super(R.layout.activity_join_guild);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("申请加入公会");
        mEtId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvAction.setEnabled(false);
                mGroupInfo.setVisibility(View.GONE);
                mTvAction.setBackgroundResource(R.drawable.bg_r99_b4b4b4);
                if (s.length() > 5) {
                    MvpPre.guildInfo(s.toString());
                }
                if (s.length() > 0) {
                    mIvShadow.setVisibility(View.VISIBLE);
                } else {
                    mIvShadow.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected JoinGuildPresenter bindPresenter() {
        return new JoinGuildPresenter(this, this);
    }

    @Override
    public void guildInfo(GuildResp guildResp) {
        mGuildResp = guildResp;
        mGroupInfo.setVisibility(guildResp == null ? View.GONE : View.VISIBLE);
        if (guildResp != null) {
            mTvGuild.setText(guildResp.getName());
            mTvAction.setBackgroundResource(R.drawable.bg_gradient_action_guild);
            mTvAction.setEnabled(true);
        }
    }

    @Override
    public void joinSuccess() {
        ToastUtils.showShort("申请成功，请等待审核");
        finish();
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }

    @OnClick({R.id.iv_back, R.id.tv_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_action:
                if (mGuildResp != null) {
                    MvpPre.joinGuild(String.valueOf(mGuildResp.getId()));
                } else {
                    LogUtils.e("sfdf");
                }
                break;
        }
    }
}
