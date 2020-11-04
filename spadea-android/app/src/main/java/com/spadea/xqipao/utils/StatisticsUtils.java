package com.spadea.xqipao.utils;

import android.content.Context;

import com.fm.openinstall.OpenInstall;
import com.spadea.yuyin.MyApplication;
import com.tendcloud.appcpa.TalkingDataAppCpa;
import com.tendcloud.tenddata.TCAgent;
import com.tendcloud.tenddata.TDAccount;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.utils
 * 创建人 王欧
 * 创建时间 2020/5/11 4:52 PM
 * 描述 describe
 */
public class StatisticsUtils {


    public static void onRegister(String userId, TDAccount.AccountType accountType, String nickName) {
        TCAgent.onRegister(userId, accountType, nickName);
        TalkingDataAppCpa.onRegister(userId);
        //友盟广告
        Map<String,String> regMap = new HashMap<>();
        regMap.put("userid", userId);
        MobclickAgent.onEvent(MyApplication.getInstance(), "__register", regMap);
    }

    public static void onLogin(String userId, TDAccount.AccountType accountType, String nickName) {
        MobclickAgent.onProfileSignIn(userId);
        TCAgent.onLogin(userId, accountType, nickName);
        TalkingDataAppCpa.onLogin(userId);
        //友盟广告
        Map<String,String> regMap = new HashMap<>();
        regMap.put("userid", userId);
        MobclickAgent.onEvent(MyApplication.getInstance(), "__login", regMap);
    }

    public static void addEvent(Context context, String eventName) {
        OpenInstall.reportEffectPoint(eventName, 1);
        TCAgent.onEvent(context, eventName);
        MobclickAgent.onEvent(context, eventName, eventName);
    }

    public static void addEvent(Context context, String eventName, String labelName, Map<String, Object> params) {
        TCAgent.onEvent(context, eventName, labelName, params);
        MobclickAgent.onEventObject(context, eventName, params);
    }

    public static void addPaySuccessEvent(Context context, String eventName, String money) {
        Map<String, Object> map = new HashMap<>();
        map.put("支付总额", money);
        addEvent(context, eventName, "支付成功", map);
        try {
            TalkingDataAppCpa.onPay(MyApplication.getInstance().getUser().getUser_id(), String.valueOf(System.currentTimeMillis()), Integer.parseInt(money), "CNY", eventName);
            OpenInstall.reportEffectPoint(eventName, Long.parseLong(money));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //付费成功  请将示例中的userid-safei替换成用户的用户id；请将100023467替换成流水号，如订单号或时间戳;请将”测试物品”替换成用户准备购买的装备名称或购买的实际内容；请将100替换成购买的金额。
        Map<String,String> successPayMap = new HashMap<>();
        successPayMap.put("userid", MyApplication.getInstance().getUser().getUser_id());
        successPayMap.put("orderid", String.valueOf(System.currentTimeMillis()));
        successPayMap.put("item","充值");
        successPayMap.put("amount", money);
        MobclickAgent.onEvent(MyApplication.getInstance(),"__finish_payment", successPayMap);

    }
}
