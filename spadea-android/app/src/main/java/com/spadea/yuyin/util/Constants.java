package com.spadea.yuyin.util;


import android.os.Environment;

/**
 * Copyright (c) 1
 *
 * @Description
 * @Author 1
 * @Copyright Copyright (c) 1
 * @Date 2017/12/11 0011 14:19
 */

public class Constants {
    public static final String WX_APP_ID = "wx621581e64de29ec4";
    public static final String QQ_APP_ID = "101623114";
    public static final long CODE_TIME = 60000;
    public static final String FILE_PATH = "/YuTang";
    public static final String IMAGE_PATH = Environment.getExternalStorageDirectory().toString() + "/YuTang/images/";

    public static final int CROP_COMPRESS_SIZE = 50;
    public static final int COMPRESS_INGNORE = 60;
    public static final String USERINFO = "userInfo";
    public static final int PAYSUCESS = 0x0000;
    public static final String NEWS_VOICE = "news_voice";
    public static final String NEWS_VIBRATE = "news_vibrate";
    public static final String ORDER_NEWS_COUNT = "orderNewsCount";
    public static final String ORDER_LAST_MSG = "lastOrderMsg";
}
