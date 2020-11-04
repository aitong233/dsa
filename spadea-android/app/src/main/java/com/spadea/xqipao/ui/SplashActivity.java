package com.spadea.xqipao.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fm.openinstall.OpenInstall;
import com.fm.openinstall.listener.AppInstallAdapter;
import com.fm.openinstall.model.AppData;
import com.netease.nis.quicklogin.listener.QuickLoginPreMobileListener;
import com.netease.nis.quicklogin.listener.QuickLoginTokenListener;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.data.even.SplashFinishEvent;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.home.activity.HomeActivity;
import com.spadea.xqipao.ui.login.activity.CodeLoginActivity;
import com.spadea.xqipao.ui.login.activity.ImproveInfoActivity;
import com.spadea.xqipao.ui.login.contacter.LoginContacter;
import com.spadea.xqipao.ui.login.presenter.LoginPresenter;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.utils.SPUtil;
import com.spadea.xqipao.utils.dialog.PolicyDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.BarUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.qpyy.libcommon.bean.UserBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SplashActivity extends BaseActivity<LoginPresenter> implements LoginContacter.View, PolicyDialog.PolicyClickListener {


    @BindView(R.id.tv_fs_contnet)
    TextView tv_fs_contnet;
    @BindView(R.id.svg_splash)
    SVGAImageView svgSplash;
    private boolean canOnePass;

    public SplashActivity() {
        super(R.layout.activity_splash);
    }


    @Override
    protected void initData() {
        OpenInstall.getInstall(new AppInstallAdapter() {
            @Override
            public void onInstall(AppData appData) {
                try {
                    String channelCode = appData.getChannel();
                    SPUtil.saveString(Constant.Channel.CHANNELCODE, channelCode);
                    String bindData = appData.getData();
                    JSONObject jsonObject = JSON.parseObject(bindData);
                    String invitationCode = jsonObject.getString("invitationCode");
                    SPUtil.saveString(Constant.Channel.USERON, invitationCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
//        playSvga();
        boolean isFirsts = SPUtil.getBooleanDefultFalse(Constant.ISFIRSTS);
        if (!isFirsts) {
            PolicyDialog policyDialog = new PolicyDialog(SplashActivity.this);
            policyDialog.setmPolicyClickListener(SplashActivity.this);
            policyDialog.show();
        } else {
            checkOnePass();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
//        if (svgSplash != null) {
//            svgSplash.stopAnimation(true);
//        }
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void finishEvent(SplashFinishEvent event) {
        finish();
    }

    private void checkOnePass() {
        if (!TextUtils.isEmpty(MyApplication.getInstance().getToken())) {
            isRoot();
            return;
        }
        showLoadings();
        MyApplication.getInstance().quickLogin.prefetchMobileNumber(new QuickLoginPreMobileListener() {
            @Override
            public void onGetMobileNumberSuccess(String YDToken, final String mobileNumber) {
                // 注:2.0.0及以后版本，直接在该回调中调用取号接口onePass即可
                LogUtils.e("oauth", "YDToken:" + YDToken + "    \nmobileNumber:" + mobileNumber);
                canOnePass = true;
                isRoot();
            }

            @Override
            public void onGetMobileNumberError(String YDToken, final String msg) {
                LogUtils.e("oauth", "错误信息" + msg);
                isRoot();
            }
        });
    }

    @Override
    protected LoginPresenter bindPresenter() {
        return new LoginPresenter(this, this);
    }

    @Override
    protected void initView() {
        BarUtils.setStatusBarAlpha(this, 0);
    }


    @OnClick({R.id.tv_fs_contnet})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_fs_contnet:
                boolean isFirsts = SPUtil.getBooleanDefultFalse(Constant.ISFIRSTS);
                if (!isFirsts) {
                    PolicyDialog policyDialog = new PolicyDialog(SplashActivity.this);
                    policyDialog.setmPolicyClickListener(SplashActivity.this);
                    policyDialog.show();
                } else {
                    isRoot();
                }
                break;
            default:
                break;
        }
    }

    private void isRoot() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                disLoadings();
                if (!SplashActivity.this.isTaskRoot()) {
                    Intent intent = getIntent();
                    if (intent != null) {
                        String action = intent.getAction();
                        if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                            finish();
                        } else {
                            goNextActivity();
                        }
                    } else {
                        goNextActivity();
                    }
                } else {
                    goNextActivity();
                }
            }
        });
    }


    private void goNextActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (isFinishing() || isDestroyed()) {
                return;
            }
        }
        new RxPermissions(SplashActivity.this)
                .request(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.RECORD_AUDIO)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            if (TextUtils.isEmpty(MyApplication.getInstance().getToken())) {
                                LogUtils.e("SplashEnd", "ARouters.CODE_LOGIN");
                                if (canOnePass) {
                                    doOnePass();
                                } else {
                                    go2Login();
                                }
                            } else {
                                LogUtils.e("SplashEnd", "ARouters.MAIN");
                                UserBean userBean = MyApplication.getInstance().getUser();
                                if (userBean.getSex() == 0) {
                                    Intent intent = new Intent(SplashActivity.this, ImproveInfoActivity.class);
                                    intent.putExtra("nickname", String.format("用户%s", userBean.getUser_code()));
                                    startActivity(intent);
                                } else {
                                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                                }
                                finish();
                            }

                        } else {
                            ToastUtils.showShort("请开启相应权限");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void policyAgree() {
        SPUtil.saveboolean(Constant.ISFIRSTS, true);
        checkOnePass();
    }

    @Override
    public void policyExit() {
        this.finish();
    }


    public void playSvga() {
        SVGAParser svgaParser = SVGAParser.Companion.shareParser();
        svgaParser.decodeFromAssets("splash_gd.svga", new SVGAParser.ParseCompletion() {
            @Override
            public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                if (svgSplash == null) {
                    return;
                }
                SVGADrawable svgaDrawable = new SVGADrawable(svgaVideoEntity);
                svgSplash.setImageDrawable(svgaDrawable);
                svgSplash.startAnimation();
                svgSplash.setCallback(new SVGACallback() {
                    @Override
                    public void onPause() {
                        LogUtils.e("动画", "播放暂停");
                    }

                    @Override
                    public void onFinished() {
                        tv_fs_contnet.setText("跳过(0)");
                        boolean isFirsts = SPUtil.getBooleanDefultFalse(Constant.ISFIRSTS);
                        if (!isFirsts) {
                            PolicyDialog policyDialog = new PolicyDialog(SplashActivity.this);
                            policyDialog.setmPolicyClickListener(SplashActivity.this);
                            policyDialog.show();
                        } else {
                            isRoot();
                        }
                    }

                    @Override
                    public void onRepeat() {

                    }

                    @Override
                    public void onStep(int i, double v) {
                        if (i == 90 && svgSplash != null && svgSplash.isAnimating()) {
                            svgSplash.stopAnimation();
                        }
                        if (tv_fs_contnet != null) {
                            tv_fs_contnet.setText("跳过(" + (int) ((1 - v) * 3) + ")");
                        }
                    }
                });
            }

            @Override
            public void onError() {
                LogUtils.e("动画", "播放错误");
            }
        });
    }

    @Override
    public void sendCodeSuccess(String phoneNumber) {

    }

    @Override
    public void finish() {
        super.finish();
    }

    private void doOnePass() {
        MyApplication.getInstance().quickLogin.onePass(new QuickLoginTokenListener() {
            @Override
            public void onGetTokenSuccess(final String YDToken, final String accessCode) {
                LogUtils.e("oauth", "YDToken:" + YDToken + "  \n  accessCode:" + accessCode);
                MvpPre.oauthLogin(YDToken, accessCode, 3);
                MyApplication.getInstance().quickLogin.quitActivity();
            }

            @Override
            public void onGetTokenError(String YDToken, String msg) {
                LogUtils.e("oauth", "YDToken" + YDToken + "    msg" + msg);
                ToastUtils.showShort("登录失败,请重试或者更换登录方式");
                MyApplication.getInstance().quickLogin.quitActivity();
                go2Login();
            }

            @Override
            public void onCancelGetToken() {
                super.onCancelGetToken();
                finish();
            }
        });
    }

    private void go2Login() {
        startActivity(new Intent(SplashActivity.this, CodeLoginActivity.class));
        finish();
    }

    @Override
    public void showLoadings() {
        showCommonLoading();
    }

    @Override
    public void disLoadings() {
        disCommonLoading();
    }
}
