package com.qpyy.room.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.qpyy.libcommon.constant.Constants;
import com.qpyy.libcommon.constant.URLConstants;
import com.qpyy.libcommon.utils.ShareUtil;
import com.qpyy.libcommon.widget.dialog.BaseBottomSheetDialog;
import com.qpyy.room.R;
import com.qpyy.room.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 房间分享弹窗
 */
public class ShareDialog extends BaseBottomSheetDialog {


    private static final String TAG = "ShareDialog";
    @BindView(R2.id.tv_qq)
    TextView tvQq;
    @BindView(R2.id.tv_weixin)
    TextView tvWeixin;
    @BindView(R2.id.tv_clean)
    TextView tvClean;

    public ShareDialog(@NonNull Context context) {
        super(context);
        Log.i(TAG, "(Start)启动了===========================ShareDialog");
    }

    @Override
    public int getLayout() {
        return R.layout.room_dialog_share;
    }

    @Override
    public void initView() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0.4f;
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setGravity(Gravity.BOTTOM);
    }

    @Override
    public void initData() {

    }

    @OnClick({R2.id.tv_qq, R2.id.tv_weixin, R2.id.tv_clean})
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.tv_qq == id) {
            ShareUtil.share(getContext(), Constants.Share.SHARE_QQ, "我在黑桃A玩呢,大家都在玩,快来展示您的精彩", "黑桃A", "", URLConstants.SHARE);
        } else if (R.id.tv_weixin == id) {
            ShareUtil.share(getContext(), Constants.Share.SHARE_WECHAT, "我在黑桃A玩呢,大家都在玩,快来展示您的精彩", "黑桃A", "", URLConstants.SHARE);
        }
        dismiss();
    }

}
