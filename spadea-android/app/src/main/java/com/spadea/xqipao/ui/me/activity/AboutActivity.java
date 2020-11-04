package com.spadea.xqipao.ui.me.activity;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qpyy.libcommon.constant.URLConstants;
import com.spadea.yuyin.BuildConfig;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.AppUpdateModel;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.AboutContacts;
import com.spadea.xqipao.ui.me.presenter.AboutPresenter;
import com.spadea.xqipao.utils.dialog.AppUpdateDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity<AboutPresenter> implements AboutContacts.View {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_version_name)
    TextView tvVersionName;
    @BindView(R.id.ll0)
    LinearLayout ll0;
    @BindView(R.id.ll_version_check)
    LinearLayout llVersionCheck;

    public AboutActivity() {
        super(R.layout.activity_about_us);
    }

    @Override
    protected void initData() {
        tvTitle.setText("关于我们");
        tvVersionName.setText(BuildConfig.VERSION_NAME + "." + BuildConfig.VERSION_CODE);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected AboutPresenter bindPresenter() {
        return new AboutPresenter(this, this);
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @OnClick({R.id.iv_back, R.id.ll0, R.id.ll_version_check, R.id.ll1, R.id.ll2})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll0:
                ARouter.getInstance().build(ARouters.H5).withString("url", Constant.URL.PROTOCOL).withString("title", "关于我们").navigation();
                break;
            case R.id.ll1:
                ARouter.getInstance().build(ARouters.H5).withString("url", URLConstants.QPYY_YSZC).withString("title", "黑桃A隐私政策").navigation();
                break;
            case R.id.ll2:
                ARouter.getInstance().build(ARouters.H5).withString("url", URLConstants.CHILDREN_PROTOCOL).withString("title", "黑桃A儿童隐私保护声明").navigation();
                break;
            case R.id.ll_version_check:
                MvpPre.appUpdate();
                break;
        }

    }

    @Override
    public void appUpdate(AppUpdateModel appUpdateModel) {
        if (appUpdateModel.getVersionCode() <= BuildConfig.VERSION_CODE) {
            ToastUtils.showShort("已经是最新版本");
        } else {
            AppUpdateDialog appUpdateDialog = new AppUpdateDialog(this);
            appUpdateDialog.setAppUpdateModel(appUpdateModel);
            appUpdateDialog.show();
        }

    }
}
