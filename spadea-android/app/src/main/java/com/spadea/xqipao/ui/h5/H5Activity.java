package com.spadea.xqipao.ui.h5;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.BuildConfig;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.utils.Sha1Util;
import com.spadea.xqipao.utils.SystemUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.H5, name = "h5页面")
public class H5Activity extends BaseActivity<H5Presenter> implements H5Contacts.View {

    private static final String SCHEME_QQ_SERVICE = "qqyy://qq_service"; //客服

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.web_view)
    WebView webView;

    @Autowired
    public String title;
    @Autowired
    public String url;


    public H5Activity() {
        super(R.layout.activity_h5);
    }

    @Override
    protected void initData() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        //增加JSBridge
        webView.addJavascriptInterface(new WebViewBridgeConfig(), WebViewBridgeConfig.NAME);
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
        webView.loadUrl(url, headers);
        showLoadings();
    }

    @Override
    protected void initView() {
        tvTitle.setText(title);
        LogUtils.e("H5", "title: " + title + " url: " + url);
    }

    @Override
    protected H5Presenter bindPresenter() {
        return new H5Presenter(this, this);
    }


    @Override
    protected void setListener() {
        super.setListener();
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
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!TextUtils.isEmpty(view.getTitle())) {
                    tvTitle.setText(view.getTitle());
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (SCHEME_QQ_SERVICE.equals(url)) {
                    MvpPre.serviceUser();
                    return true;
                }
                if (url.startsWith(Constant.SCHEME)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            intent.setPackage(getPackageName());
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        onBackPressed();
    }


    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void serviceUserSuccess(String uin) {
        try {
            String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=" + uin + "&version=1";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl));
            startActivity(intent);
        } catch (Exception e) {
            ToastUtils.showShort("请先安装QQ");
        }
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }
}
