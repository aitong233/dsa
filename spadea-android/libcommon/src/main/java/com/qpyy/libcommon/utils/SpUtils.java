package com.qpyy.libcommon.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SPUtils;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.constant.SPConstants;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.libcommon.utils
 * 创建人 王欧
 * 创建时间 2020/6/28 9:38 AM
 * 描述 describe
 */
public class SpUtils {
    public static String getToken() {
        return SPUtils.getInstance(SPConstants.PREFERENCE_NAME).getString(SPConstants.TOKEN);
    }

    public static String getUserId() {
        return SPUtils.getInstance(SPConstants.PREFERENCE_NAME).getString(SPConstants.USER_ID);
    }

    public static void saveUserId(String userId) {
        SPUtils.getInstance(SPConstants.PREFERENCE_NAME).put(SPConstants.USER_ID, userId, true);
    }


    public static void saveSearchHistory(String searchHistory) {
        SPUtils.getInstance(SPConstants.PREFERENCE_SEARCH_HISTORY).put(SPConstants.SEARCH_HISTORY, searchHistory);
    }

    public static String getSearchHistory() {
        return SPUtils.getInstance(SPConstants.PREFERENCE_SEARCH_HISTORY).getString(SPConstants.SEARCH_HISTORY);
    }

    public static void putToken(String token) {
        SPUtils.getInstance(SPConstants.PREFERENCE_NAME).put(SPConstants.TOKEN, token, true);
    }

    public static void saveUserInfo(UserBean userBean) {
        String s = JSON.toJSONString(userBean);
        SPUtils.getInstance(SPConstants.PREFERENCE_NAME).put(SPConstants.USER_INFO, s, true);
    }

    public static UserBean getUserInfo() {
        String s = SPUtils.getInstance(SPConstants.PREFERENCE_NAME).getString(SPConstants.USER_INFO);
        if (TextUtils.isEmpty(s)) {
            return new UserBean();
        }
        UserBean userBean = JSON.parseObject(s, UserBean.class);
        if (userBean == null) {
            return new UserBean();
        }
        return userBean;
    }

    public static void saveRoomType(int ROOM_TYPE) {
        SPUtils.getInstance(SPConstants.PREFERENCE_NAME).put(SPConstants.ROOM_TYPE, ROOM_TYPE, true);
    }

    public static int getRoomType() {
        int s = SPUtils.getInstance(SPConstants.PREFERENCE_NAME).getInt(SPConstants.ROOM_TYPE, 0);
        return s;
    }

    public static void saveTransferStatus(boolean isTransferOpen) {
        SPUtils.getInstance(SPConstants.PREFERENCE_NAME).put(SPConstants.TRANSFER_STATUS, isTransferOpen, true);
    }

    public static boolean getTransferStatus() {
        boolean s = SPUtils.getInstance(SPConstants.PREFERENCE_NAME).getBoolean(SPConstants.TRANSFER_STATUS, false);
        return s;
    }

    public static void saveEmqttId(String clientId) {
        SPUtils.getInstance(SPConstants.PREFERENCE_NAME).put(SPConstants.EMQTT_CLIENT_ID, clientId);
    }

    public static String getEmqttId() {
        String s = SPUtils.getInstance(SPConstants.PREFERENCE_NAME).getString(SPConstants.EMQTT_CLIENT_ID);
        return s;
    }


    //获取SharedPreferences音乐轮播方式
    public static int getPlayPattern() {
        return SPUtils.getInstance(SPConstants.PREFERENCE_NAME).getInt(SPConstants.PLAY_MODE, 1);
    }

    //通过SharedPreferences设置音乐轮播方式
    public static void setPlayPattern(int i) {
        SPUtils.getInstance(SPConstants.PREFERENCE_NAME).put(SPConstants.PLAY_MODE, i);
    }

    //获取SharedPreferences音乐播放音量
    public static int getChannelVolume() {
        return SPUtils.getInstance(SPConstants.PREFERENCE_NAME).getInt(SPConstants.VOLUME, 20);
    }

    //设置SharedPreferences音乐播放音量
    public static void setChannelVolume(int i) {
        SPUtils.getInstance(SPConstants.PREFERENCE_NAME).put(SPConstants.VOLUME, i);
    }

    //获取播放的音乐
    public static int getPlayCurrentMusic() {
        return SPUtils.getInstance(SPConstants.PREFERENCE_NAME).getInt(SPConstants.CURRENT_MUSIC, 0);
    }

    //设置播放的音乐
    public static void setPlayCurrentMusic(int i) {
        SPUtils.getInstance(SPConstants.PREFERENCE_NAME).put(SPConstants.CURRENT_MUSIC, i);
    }

    //设置开启特效
    public static void setOpenEffect(int i) {
        SPUtils.getInstance(SPConstants.PREFERENCE_NAME).put(SPConstants.OPEN_EFFECT, i);
    }

    //获取开启特效
    public static int getOpenEffect() {
        return SPUtils.getInstance(SPConstants.PREFERENCE_NAME).getInt(SPConstants.OPEN_EFFECT, 1);
    }

    //设置耳返
    public static void setAuricularBack(int i) {
        SPUtils.getInstance(SPConstants.PREFERENCE_NAME).put(SPConstants.OPEN_AU_BACK, i);
    }

    //获取耳返
    public static int getAuricularBack() {
        return SPUtils.getInstance(SPConstants.PREFERENCE_NAME).getInt(SPConstants.OPEN_AU_BACK, 0);
    }

    //获取新的消息数量
    public static int getOrderNewCounts() {
        return SPUtils.getInstance(SPConstants.PREFERENCE_NAME).getInt(SPConstants.ORDER_NEWS_COUNT, 0);
    }

    //设置新的消息数量
    public static void setOrderNewCounts(int i) {
        SPUtils.getInstance(SPConstants.PREFERENCE_NAME).put(SPConstants.ORDER_NEWS_COUNT, i);
    }

    //获取装载的消息
    public static String getLastOrderMsg() {
        return SPUtils.getInstance(SPConstants.PREFERENCE_NAME).getString(SPConstants.ORDER_LAST_MSG, " ");
    }

    //设置装载的消息
    public static void setLastOrderMsg(String s) {
        SPUtils.getInstance(SPConstants.PREFERENCE_NAME).put(SPConstants.ORDER_LAST_MSG, s);
    }
}
