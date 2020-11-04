package com.spadea.yuyin.util.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.data.WxPayModel;
import com.spadea.xqipao.utils.StatisticsUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.spadea.yuyin.util.Constants;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

/**
 * Author: ly-yjm
 * Date: 2018/9/30
 * Explain: 支付帮助类  包含  支付宝支付
 */
public class PaymentUtil {
    private static final int SDK_PAY_FLAG = 1;

    /**
     * 支付宝支付
     */
    public static void payAlipay(final Context mContext, String orderNo) {
        // 订单信息
        final String orderInfo = orderNo;
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) mContext);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    public static void payWxPayment(IWXAPI api, WxPayInfo apiPayInfoViewBean) {
        if (!api.isWXAppInstalled()) {
            ToastUtils.showShort("请安装微信");
            return;
        }
        PayReq req = new PayReq();
        req.appId = Constants.WX_APP_ID;
        req.partnerId = apiPayInfoViewBean.getPartnerid();
        req.prepayId = apiPayInfoViewBean.getPrepayid();
        req.nonceStr = apiPayInfoViewBean.getNoncestr();
        req.timeStamp = apiPayInfoViewBean.getTimestamp() + "";
        req.packageValue = apiPayInfoViewBean.getPackageX();
        req.sign = apiPayInfoViewBean.getSign();
        req.extData = "app data";
        api.sendReq(req);
    }

    public static void payWxPayment(IWXAPI api, WxPayModel apiPayInfoViewBean) {
        if (!api.isWXAppInstalled()) {
            ToastUtils.showShort("请安装微信");
            return;
        }
        PayReq req = new PayReq();
        req.appId = Constants.WX_APP_ID;
        req.partnerId = apiPayInfoViewBean.getPartnerid();
        req.prepayId = apiPayInfoViewBean.getPrepayid();
        req.nonceStr = apiPayInfoViewBean.getNoncestr();
        req.timeStamp = apiPayInfoViewBean.getTimestamp() + "";
        req.packageValue = apiPayInfoViewBean.getPackageX();
        req.sign = apiPayInfoViewBean.getSign();
        req.extData = "app data";
        api.sendReq(req);
    }

    @SuppressLint("HandlerLeak")
    public static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */

                    // 同步返回需要验证的信息
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付成功");
                        PayEvent messageEvent = new PayEvent(Constants.PAYSUCESS, "支付成功");
                        EventBus.getDefault().post(messageEvent);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付失败");
                        StatisticsUtils.addEvent(MyApplication.getInstance(), Constant.OpenInstall.ALIRECHARGEFAIL);
                        StatisticsUtils.addEvent(MyApplication.getInstance(), Constant.OpenInstall.RECHARGEFAIL);
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

}
