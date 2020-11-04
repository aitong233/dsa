package com.qpyy.libcommon.utils;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ActivityUtils;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.widget.dialog.CommonDialog;

public class DialogUtils {
    public static void showNoBalance() {
        Activity activity = ActivityUtils.getTopActivity();
        if (!(activity instanceof FragmentActivity)) {
            return;
        }
        CommonDialog commonDialog = new CommonDialog(activity);
        commonDialog.setContent("您的余额已不足，请及时充值");
        commonDialog.setLeftText("再想想");
        commonDialog.setRightText("去充值");
        commonDialog.setmOnClickListener(new CommonDialog.OnClickListener() {
            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightClick() {
                DialogFragment navigation = (DialogFragment) ARouter.getInstance().build(ARouteConstants.RECHARGE_DIALOG).navigation();
                navigation.show(((FragmentActivity) activity).getSupportFragmentManager(), "RechargeDialogFragment");
            }
        });
        commonDialog.show();
    }
}
