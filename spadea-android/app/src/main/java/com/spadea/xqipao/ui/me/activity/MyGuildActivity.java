package com.spadea.xqipao.ui.me.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module.me.bean.GuildResp;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.SpanUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.MyGuildContacts;
import com.spadea.xqipao.ui.me.presenter.MyGuildPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.activity
 * 创建人 王欧
 * 创建时间 2020/5/8 3:44 PM
 * 描述 describe
 */
@Route(path = ARouteConstants.MY_GUILD, name = "我的公会")
public class MyGuildActivity extends BaseActivity<MyGuildPresenter> implements MyGuildContacts.View {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_guild_name)
    TextView mTvGuildName;
    @BindView(R.id.tv_guild_id)
    TextView mTvGuildId;
    @BindView(R.id.tv_days)
    TextView mTvDays;
    private String mGuildId;

    public MyGuildActivity() {
        super(R.layout.activity_my_guild);
    }

    @Override
    protected void initData() {
        MvpPre.getGuildInfo();
    }

    @Override
    protected void initView() {
        mTvTitle.setText("我的公会");
    }

    @Override
    protected MyGuildPresenter bindPresenter() {
        return new MyGuildPresenter(this, this);
    }

    @Override
    public void guildInfo(GuildResp info) {
        mGuildId = String.valueOf(info.getId());
        mTvGuildName.setText(info.getName());
        mTvDays.setText(new SpanUtils().append(String.valueOf(info.getJoin_date())).append("天").setFontSize(14, true).setForegroundColor(getResources().getColor(R.color.color_9c9c9c)).create());
        mTvGuildId.setText(String.format("公会ID：%s", info.getGuild_no()));
    }

    @Override
    public void quitSuccess() {
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
                new AlertDialog.Builder(this).setMessage("是否退出此公会？").setNegativeButton("否", null).setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        MvpPre.quitGuild(mGuildId);
                    }
                }).create().show();
                break;
        }
    }
}
