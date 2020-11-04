package com.spadea.xqipao.ui.h5;

import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.ui.fragment2.setting.feedback.FeedBackActivity;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.data.api.RemoteDataSource;

import org.json.JSONObject;

import io.reactivex.disposables.Disposable;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.h5
 * 创建人 王欧
 * 创建时间 2020/6/16 1:26 PM
 * 描述 describe
 */
public class WebViewBridgeConfig {
    public static final String NAME = "bridge";
    public static final String TYPE_QQ_SERVICE = "qqService";
    public static final String TYPE_FEEDBACK = "feedback";
    public static final String TYPE_USER_ZONE = "userZone";
    public static final String TYPE_RECHARGE = "recharge";
    public static final String TYPE_BACK = "onBackPressed";

    @JavascriptInterface
    public void postMessage(String json) {
        LogUtils.e(json);
        try {

            JSONObject jsonObject = new JSONObject(json);
            String type = jsonObject.getString("type");
            JSONObject object = jsonObject.getJSONObject("data");
            switch (type) {
                case TYPE_QQ_SERVICE:
                    serviceUser();
                    break;
                case TYPE_FEEDBACK:
                    com.blankj.utilcode.util.ActivityUtils.startActivity(FeedBackActivity.class);
                    break;
                case TYPE_USER_ZONE:
                    ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", object.getString("userId")).navigation();
                    break;
                case TYPE_RECHARGE:
                    ARouter.getInstance().build(ARouters.ME_BALANCE).navigation();
                    break;
                case TYPE_BACK:
                    try {
                        ActivityUtils.getTopActivity().onBackPressed();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //跳转QQ客服
    private void serviceUser() {
        RemoteDataSource.getInstance().serviceUser(new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String uin) {
                try {
                    String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=" + uin + "&version=1";
                    com.blankj.utilcode.util.ActivityUtils.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
                } catch (Exception e) {
                    ToastUtils.showShort("请先安装QQ");
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
