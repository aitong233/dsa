package com.spadea.xqipao.utils.dialog.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.utils.ShareUtil;
import com.spadea.xqipao.utils.dialog.BaseBottomSheetDialog;
import com.spadea.yuyin.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ShareRoomDialog extends BaseBottomSheetDialog {

    private Context mContext;

    @BindView(R.id.ll_qq)
    LinearLayout llQq;
    @BindView(R.id.ll_qq_kongjian)
    LinearLayout llQqKongjian;
    @BindView(R.id.ll_weixin)
    LinearLayout llWeixin;
    @BindView(R.id.ll_pengyouquan)
    LinearLayout llPengyouquan;
    @BindView(R.id.tv_close)
    TextView tvClose;

    public ShareRoomDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_share_room;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.ll_qq, R.id.ll_qq_kongjian, R.id.ll_weixin, R.id.ll_pengyouquan, R.id.tv_close})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_qq:
                ShareUtil.share(mContext, Constant.Share.SHARE_QQ, "我在黑桃A玩呢,大家都在玩,快来展示您的精彩", "黑桃A", "", Constant.URL.SHARE);
                break;
            case R.id.ll_qq_kongjian:
                ShareUtil.share(mContext, Constant.Share.SHARE_QQ_ZONE, "我在黑桃A玩呢,大家都在玩,快来展示您的精彩", "黑桃A", "", Constant.URL.SHARE);
                break;
            case R.id.ll_weixin:
                ShareUtil.share(mContext, Constant.Share.SHARE_WECHAT, "我在黑桃A玩呢,大家都在玩,快来展示您的精彩", "黑桃A", "", Constant.URL.SHARE);
                break;
            case R.id.ll_pengyouquan:
                ShareUtil.share(mContext, Constant.Share.SHARE_WECHAT_CIRCLE, "我在黑桃A玩呢,大家都在玩,快来展示您的精彩", "黑桃A", "", Constant.URL.SHARE);
                break;
            case R.id.tv_close:
                this.dismiss();
                break;
        }
        this.dismiss();
    }


}
