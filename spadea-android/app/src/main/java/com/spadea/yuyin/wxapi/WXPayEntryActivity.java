package com.spadea.yuyin.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.spadea.yuyin.util.Constants;
import com.spadea.yuyin.util.pay.PayEvent;
import com.spadea.yuyin.util.utilcode.LogUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.spadea.yuyin.R;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.utils.StatisticsUtils;

import org.greenrobot.eventbus.EventBus;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, Constants.WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }


    @Override
    public void onResp(BaseResp baseResp) {
        LogUtils.e("微信支付", baseResp.errCode);
        LogUtils.e("微信支付", baseResp.errStr);
        PayEvent messageEvent;
        switch (baseResp.errCode) {
            case -2:
                ToastUtils.showShort("支付取消");
                messageEvent = new PayEvent(-2, "支付取消");
                EventBus.getDefault().post(messageEvent);
                StatisticsUtils.addEvent(this, Constant.OpenInstall.WXRECHARGEFAIL);
                StatisticsUtils.addEvent(this, Constant.OpenInstall.RECHARGEFAIL);
                finish();
                break;
            case 0:
                ToastUtils.showShort("支付成功");
                messageEvent = new PayEvent(Constants.PAYSUCESS, "支付成功");
                EventBus.getDefault().post(messageEvent);
                this.finish();
                break;
            case 1:
                ToastUtils.showShort("支付失败");
                messageEvent = new PayEvent(1, "支付失败");
                EventBus.getDefault().post(messageEvent);
                StatisticsUtils.addEvent(this, Constant.OpenInstall.WXRECHARGEFAIL);
                StatisticsUtils.addEvent(this, Constant.OpenInstall.RECHARGEFAIL);
                finish();
                break;
            default:
                finish();
                break;
        }
    }


    @Override
    public void onReq(BaseReq baseReq) {
        LogUtils.e("微信支付1", baseReq);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}