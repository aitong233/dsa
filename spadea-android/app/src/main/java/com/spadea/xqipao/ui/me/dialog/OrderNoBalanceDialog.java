package com.spadea.xqipao.ui.me.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.spadea.yuyin.R;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.utils.dialog.BaseBottomSheetDialog;
import com.spadea.xqipao.widget.dialog.CustomAlertDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.dialog
 * 创建人 王欧
 * 创建时间 2020/6/3 9:14 AM
 * 描述 describe
 */
public class OrderNoBalanceDialog extends BaseBottomSheetDialog {
    @BindView(R.id.tv_balance)
    TextView mTvBalance;

    public OrderNoBalanceDialog(@NonNull Context context, String balance) {
        super(context);
        mTvBalance.setText(String.format("账户余额(%s金币)", balance));
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_order_no_balance;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.iv_close, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                new CustomAlertDialog(mContext, "离完成就差一步，真的要退出吗？", "再想想", "确认", new CustomAlertDialog.CustomDialogListener() {
                    @Override
                    public void onConfirm() {
                        OrderNoBalanceDialog.this.dismiss();
                    }

                    @Override
                    public void onCancel() {

                    }
                }).show();
                break;
            case R.id.tv_submit:
                ARouter.getInstance().build(ARouters.ME_BALANCE).withBoolean("withFinish", true).navigation();
                dismiss();
                break;
        }
    }
}
