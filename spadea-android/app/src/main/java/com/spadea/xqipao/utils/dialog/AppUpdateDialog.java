package com.spadea.xqipao.utils.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;

import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.AppUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.utils.download.DownloadListener;
import com.spadea.xqipao.utils.download.DownloadUtil;
import com.spadea.xqipao.data.AppUpdateModel;
import com.spadea.xqipao.utils.LogUtils;

import butterknife.OnClick;

public class AppUpdateDialog extends BaseDialog implements DownloadListener {

    private AppUpdateModel appUpdateModel;
    private ProgressDialog mProgressDialog;

    public AppUpdateDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_app_update;
    }

    @Override
    public void initView() {
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.iv_app_update)
    public void onClick(View view) {
        if (appUpdateModel != null) {
            mProgressDialog = new android.app.ProgressDialog(mContext) {
                @Override
                public void onBackPressed() {
                }
            };
            mProgressDialog.setMax(100);//设置最大值
            mProgressDialog.setTitle("安装包下载");//设置标题
            mProgressDialog.setIcon(R.mipmap.ic_launcher);//设置标题小图标
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置样式为横向显示进度的样式
            mProgressDialog.incrementProgressBy(0);//设置初始值为0，其实可以不用设置，默认就是0
            mProgressDialog.setIndeterminate(false);//是否精确显示对话框，flase为是，反之为否
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setMessage("下载中请稍等！！！");
            mProgressDialog.show();
            DownloadUtil downloadUtil = new DownloadUtil();
            downloadUtil.downloadFile(appUpdateModel.getDownloadUrl(), this);
        }
    }

    public void setAppUpdateModel(AppUpdateModel appUpdateModel) {
        this.appUpdateModel = appUpdateModel;
    }


    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_SEARCH) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onProgress(int currentLength) {
        if (mProgressDialog != null) {
            mProgressDialog.setProgress(currentLength);
        }
    }

    @Override
    public void onFinish(String localPath) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        try {
            AppUtils.installApp(localPath, "com.spadea.yuyin.fileProvider");
        } catch (Exception e) {
            LogUtils.e("installAppError", e);
            onFailure();
        }

        dismiss();
    }

    @Override
    public void onFailure() {
        ToastUtils.showShort("下载失败前往浏览器手动更新");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri url = Uri.parse(appUpdateModel.getDownloadUrl());
        intent.setData(url);
        mContext.startActivity(intent);
    }
}
