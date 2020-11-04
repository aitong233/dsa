package com.qpyy.libcommon.constant;

import android.os.Environment;

public class Constants {
    public static final String FILE_PATH = "/YuTang";
    public static final int CROP_COMPRESS_SIZE = 50;
    public static final int COMPRESS_INGNORE = 60;
    public static final String IMAGE_PATH = Environment.getExternalStorageDirectory().toString() + "/YuTang/images/";
    public static final String MUSIC_PATH = Environment.getExternalStorageDirectory().toString() + "/YuTang/music/";
    public static final String ORDER_NEWS_COUNT = "orderNewsCount";
    public static final String ORDER_LAST_MSG = "lastOrderMsg";


    public final class Share {
        public static final int SHARE_WECHAT = 1;
        public static final int SHARE_WECHAT_CIRCLE = 2;
        public static final int SHARE_QQ = 3;
        public static final int SHARE_QQ_ZONE = 4;
    }
}
