package com.spadea.xqipao.ui.me.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.base.view.BaseAppCompatActivity;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.ui.fragment2.setting.feedback.FeedBackActivity;
import com.spadea.yuyin.util.utilcode.FileUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.activity
 * 创建人 王欧
 * 创建时间 2020/3/26 2:40 PM
 * 描述 describe
 */
@Route(path = ARouteConstants.ME_SETTING, name = "帮助中心")
public class SettingActivity extends BaseAppCompatActivity {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_cache)
    TextView mTvCache;

    public SettingActivity() {
        super(R.layout.activity_setting2);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("设置");
        mTvCache.setHint(FileUtils.getDirSize(getCacheDir()));
    }

    @OnClick({R.id.iv_left, R.id.tv_left, R.id.ll_account, R.id.ll_notification, R.id.ll_privacy, R.id.ll_clear_cache, R.id.ll_feedback, R.id.ll_about_us, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
            case R.id.tv_left:
                onBackPressed();
                break;
            case R.id.ll_account:
                startActivity(new Intent(this, AccountSecurityActivity.class));
                break;
            case R.id.ll_notification:
                ARouter.getInstance().build(ARouters.MESSAGE_SETTING).navigation();
                break;
            case R.id.ll_privacy:
                ARouter.getInstance().build(ARouters.BLACK_LIST).navigation();
                break;
            case R.id.ll_clear_cache:
                new AlertDialog.Builder(this).setMessage("确认清理缓存？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtils.showShort("开始清理...");
                        FileUtils.deleteAllInDir(getCacheDir());
                        FileUtils.deleteAllInDir(getExternalCacheDir());
                        ToastUtils.showShort("缓存清理成功!");
                        mTvCache.setHint(FileUtils.getDirSize(getCacheDir()));
                    }
                }).setNegativeButton("取消", null).create().show();
                break;
            case R.id.ll_feedback:
                startActivity(new Intent(this, FeedBackActivity.class));
                break;
            case R.id.ll_about_us:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.btn_logout:
                new AlertDialog.Builder(this).setMessage("确定要退出登录吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MyApplication.getInstance().reLogin();
                            }
                        })
                        .setNegativeButton("取消", null).create().show();
                break;
        }
    }
}
