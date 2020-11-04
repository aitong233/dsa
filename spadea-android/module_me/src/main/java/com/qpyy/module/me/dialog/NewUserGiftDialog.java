package com.qpyy.module.me.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.qpyy.libcommon.base.BaseDialog;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.me.dialog
 * 创建人 王欧
 * 创建时间 2020/8/1 4:17 PM
 * 描述 describe
 */
public class NewUserGiftDialog extends BaseDialog {
    @BindView(R2.id.image)
    ImageView mImage;
    @BindView(R2.id.iv_close)
    ImageView mIvClose;


    public NewUserGiftDialog(@NonNull Context context, String imageUrl) {
        super(context);
        ImageUtils.loadImageView(imageUrl, mImage);
    }

    @Override
    public int getLayoutId() {
        return R.layout.me_dialog_new_user_gift;
    }

    @Override
    public void initView() {
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void initData() {

    }

    @OnClick(R2.id.iv_close)
    public void onViewClicked() {
        dismiss();
    }
}
