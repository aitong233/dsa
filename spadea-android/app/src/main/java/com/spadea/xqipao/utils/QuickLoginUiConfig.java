package com.spadea.xqipao.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ActivityUtils;
import com.netease.nis.quicklogin.helper.UnifyUiConfig;
import com.netease.nis.quicklogin.utils.LoginUiHelper;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ConvertUtils;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.data.even.SplashFinishEvent;
import com.spadea.xqipao.ui.login.activity.CodeLoginActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by hzhuqi on 2019/12/31
 */
public class QuickLoginUiConfig {

    public static UnifyUiConfig getUiConfig(final Context context) {
//        TextView textView = new TextView(context);
//        textView.setTextColor(Color.WHITE);
//        textView.setTextSize(12);
//        textView.setText("本机号码登录");
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins(0, ConvertUtils.dp2px(182), 0, 0);
//        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        textView.setLayoutParams(layoutParams);

        LayoutInflater inflater = LayoutInflater.from(context);
        RelativeLayout otherLoginRel = (RelativeLayout) inflater.inflate(R.layout.custom_other_login, null);
        RelativeLayout.LayoutParams layoutParamsOther = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsOther.setMargins(0, 0, 0, ConvertUtils.dp2px(134));
        layoutParamsOther.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParamsOther.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        otherLoginRel.setLayoutParams(layoutParamsOther);
        int X_OFFSET = 0;
        int BOTTOM_OFFSET = 0;
        UnifyUiConfig uiConfig = new UnifyUiConfig.Builder()
                // 状态栏
                .setStatusBarColor(Color.BLACK)
                .setStatusBarDarkColor(false)
                // 设置导航栏
                .setHideNavigation(true)
                // 设置logo
                .setLogoIconName("login_ico")
                .setLogoWidth(217)
                .setLogoHeight(181)
                .setLogoXOffset(X_OFFSET)
                .setLogoTopYOffset(0)
                .setHideLogo(false)
                //手机掩码
                .setMaskNumberColor(Color.WHITE)
                .setMaskNumberSize(30)
                .setMaskNumberXOffset(X_OFFSET)
                .setMaskNumberTopYOffset(240)
                .setMaskNumberBottomYOffset(BOTTOM_OFFSET)
                // 认证品牌
                .setSloganSize(12)
                .setSloganColor(Color.WHITE)
                .setSloganXOffset(X_OFFSET)
                .setSloganTopYOffset(200)
                .setSloganBottomYOffset(BOTTOM_OFFSET)
                // 登录按钮
                .setLoginBtnText("一键安全登录")
                .setLoginBtnTextColor(context.getResources().getColor(R.color.color_6765FF))
                .setLoginBtnBackgroundRes("bg_r99_white")
                .setLoginBtnWidth(141)
                .setLoginBtnHeight(40)
                .setLoginBtnTextSize(14)
                .setLoginBtnXOffset(X_OFFSET)
                .setLoginBtnTopYOffset(325)
                .setLoginBtnBottomYOffset(BOTTOM_OFFSET)
                // 隐私栏
                .setPrivacyTextStart("登录即代表同意")
                .setProtocolText("《用户协议》")
                .setProtocolLink(Constant.URL.URL_USER_YHXY)
                .setProtocol2Text("《隐私协议》")
                .setProtocol2Link(Constant.URL.URL_USER_YSXY)
                .setPrivacyTextEnd("并授权黑桃A获得号码")
                .setPrivacyTextColor(Color.WHITE)
                .setPrivacyProtocolColor(Color.parseColor("#B6A2FF"))
//                .setHidePrivacyCheckBox(false)
                .setPrivacyXOffset(X_OFFSET)
                .setPrivacyState(true)
                .setPrivacySize(11)
//                .setPrivacyTopYOffset(510)
                .setPrivacyBottomYOffset(48)
                .setPrivacyTextGravityCenter(true)
                .setCheckedImageName("yd_checkbox_checked2")
                .setUnCheckedImageName("yd_checkbox_unchecked2")
                // 协议详情页导航栏
//                .setProtocolPageNavTitle("易盾一键登录SDK服务条款")
//                .setProtocolPageNavBackIcon("yd_checkbox_checked")
//                .setProtocolPageNavColor(Color.BLUE)

                .setBackgroundImage("bg_login")
//                .addCustomView(textView, "df_title", UnifyUiConfig.POSITION_IN_TITLE_BAR, null)
                // 自定义控件
                .addCustomView(otherLoginRel, "relative", UnifyUiConfig.POSITION_IN_BODY, new LoginUiHelper.CustomViewListener() {
                    @Override
                    public void onClick(Context context, View view) {
                        ActivityUtils.startActivity(CodeLoginActivity.class);
                        MyApplication.getInstance().quickLogin.quitActivity();
                        EventBus.getDefault().post(new SplashFinishEvent());
                    }
                })


                .build(context);
        return uiConfig;
    }

}
