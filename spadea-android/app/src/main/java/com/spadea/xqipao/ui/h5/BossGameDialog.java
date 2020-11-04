package com.spadea.xqipao.ui.h5;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ScreenUtils;
import com.orhanobut.logger.Logger;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.event.BossMsgEvent;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.BuildConfig;
import com.spadea.yuyin.R;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.utils.Sha1Util;
import com.spadea.xqipao.utils.SystemUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.h5
 * 创建人 王欧
 * 创建时间 2020/7/31 5:31 PM
 * 描述 describe
 */
@Route(path = ARouteConstants.BOSS_GAME)
public class BossGameDialog extends BaseMvpDialogFragment {
    @BindView(R.id.web_view)
    WebView webView;

    @BindView(R.id.loading_view)
    LinearLayout loadingView;

    @Autowired
    public String url;

    private MyWebViewClient mWebViewClient;

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        if (webView != null) {
            webView.destroy();
            webView = null;
        }
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBossMsg(BossMsgEvent event) {
        callHandler(event.msg);
    }

    @Override
    protected void initData() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        //增加JSBridge
        WebViewBridgeConfig webViewBridgeConfig = new WebViewBridgeConfig();
        webView.addJavascriptInterface(webViewBridgeConfig, WebViewBridgeConfig.NAME);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setBlockNetworkImage(false);//解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setHorizontalScrollBarEnabled(false);//水平不显示
        webView.setVerticalScrollBarEnabled(false); //垂直不显示
        webView.requestFocus();
        Map<String, String> headers = new HashMap<>();
        headers.put("deviceId", SystemUtils.getShortClientID(MyApplication.getContext()));
        headers.put("appVersion", BuildConfig.VERSION_NAME + BuildConfig.VERSION_CODE);
        headers.put("versionName", BuildConfig.VERSION_NAME);
        headers.put("versionCode", String.valueOf(BuildConfig.VERSION_CODE));
        headers.put("clientType", "android");
        headers.put("deviceName", SystemUtils.getDeviceBrand() + SystemUtils.getSystemModel() + SystemUtils.getSystemVersion());
        headers.put("token", MyApplication.getInstance().getToken());
        headers.put("X-Token", MyApplication.getInstance().getToken());
        long timestamp = System.currentTimeMillis() / 1000;
        headers.put("timestamp", String.valueOf(timestamp));
        headers.put("sign", Sha1Util.shaEncode(timestamp));
        loadWebView(url, headers);
        showLoadings();
    }

    private void loadWebView(String url, Map<String, String> headers) {
        try {
            Uri uri = Uri.parse(url);
            Uri.Builder builder = uri.buildUpon();
            builder.appendQueryParameter("token", MyApplication.getInstance().getToken());
            String buildUrl = builder.build().toString();
            LogUtils.e("loadWebView", buildUrl);
            webView.loadUrl(buildUrl, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Activity getSelfActivity() {
        return super.getSelfActivity();
    }

    @Override
    protected void initView() {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //newProgress：当前进度，当进度等于一百时加载完成
                //根据需求做相应操作
                if (newProgress > 95) {
                    disLoadings();
                }
            }

        });
        mWebViewClient = new MyWebViewClient(webView);
        webView.setWebViewClient(mWebViewClient);
        if (BuildConfig.DEBUG) {
            mWebViewClient.enableLogging();
        }
    }


    public void callHandler(String msg) {
        try {
            mWebViewClient.callHandler("getLeftBloodAndroidoHandler", msg, new WVJBWebViewClient.WVJBResponseCallback() {

                @Override
                public void callback(Object data) {
                    Logger.d("getLeftBloodAndroidoHandler responded: " + data);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawableResource(R.drawable.bg_boss_game);
        window.setLayout(ScreenUtils.getScreenWidth(), (int) (ScreenUtils.getScreenWidth() / 108.0 * 160));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragement_dialog_boss_game;
    }

    class MyWebViewClient extends WVJBWebViewClient {
        public MyWebViewClient(WebView webView) {

            // support js send
            super(webView, new WVJBWebViewClient.WVJBHandler() {

                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Logger.d("ObjC Received message from JS:" + data);
                    callback.callback("Response for message from ObjC!");
                }
            });

			/*
			// not support js send
			super(webView);
			*/

            if (BuildConfig.DEBUG) {
                enableLogging();
            }

            registerHandler("quitGame", new WVJBHandler() {
                @Override
                public void request(Object data, WVJBResponseCallback callback) {
                    Logger.d("quitGame:" + data);
                    callback.callback("Response from testObjcCallback!");
                    dismiss();
                }
            });

        }

    }

    @Override
    public void showLoadings() {
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void disLoadings() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }

    public void show(FragmentManager manager) {
        show(manager, "BossGameDialog");
    }
}
