package com.qpyy.libcommon.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.R;
import com.qpyy.libcommon.constant.Constants;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class ShareUtil {

    private static final String TAG = "ShareUtil";


    /**
     * @param context       上下文
     * @param shareType     朋友圈/微信/QQ
     * @param shareTitle
     * @param shareText
     * @param shareImageUrl
     * @param shareUrl
     */
    public static void share(Context context, int shareType, String shareTitle, String shareText, String shareImageUrl, String shareUrl) {
        LogUtils.e(TAG, "share: " + shareTitle + "  shareDes  " + shareText + " shareImg  " + shareImageUrl + "   shareUrl " + shareUrl);
        MyShareListener listener = new MyShareListener();
        Platform.ShareParams sharePlatform = new Platform.ShareParams();
        Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.mipmap.common_ic_logo);
        Platform platform;
        if (shareType == Constants.Share.SHARE_WECHAT) {
            sharePlatform.setShareType(Platform.SHARE_WEBPAGE);
            setSharePlatform(sharePlatform, shareTitle, shareText, shareImageUrl, logo, shareUrl);
            if (shareUrl != null && !shareUrl.equalsIgnoreCase("")) {
                sharePlatform.setTitleUrl(shareUrl);
            }
            platform = ShareSDK.getPlatform(Wechat.NAME);
            if (!platform.isClientValid()) {
                ToastUtils.show("未安装微信");
                return;
            }
            platform.setPlatformActionListener(listener);
            platform.share(sharePlatform);
        } else if (shareType == Constants.Share.SHARE_WECHAT_CIRCLE) {
            platform = ShareSDK.getPlatform(WechatMoments.NAME);
            if (!platform.isClientValid()) {
                ToastUtils.show("未安装微信");
                return;
            }
            sharePlatform.setShareType(Platform.SHARE_WEBPAGE);
            setSharePlatform(sharePlatform, shareTitle, null, shareImageUrl, logo, shareUrl);
            if (shareUrl != null && !shareUrl.equalsIgnoreCase("")) {
                sharePlatform.setTitleUrl(shareUrl);
            }
            platform.setPlatformActionListener(listener);
            platform.share(sharePlatform);
        }
        /**
         * 如果是QQ分享
         */
        else if (shareType == Constants.Share.SHARE_QQ) {
            setSharePlatform(sharePlatform, shareTitle, shareText, shareImageUrl, logo, shareUrl);
            if (shareUrl != null && !shareUrl.equalsIgnoreCase("")) {
                sharePlatform.setTitleUrl(shareUrl);
            }
            sharePlatform.setShareType(Platform.SHARE_WEBPAGE);
            platform = ShareSDK.getPlatform(QQ.NAME);
            if (!platform.isClientValid()) {
                ToastUtils.show("未安装QQ");
                return;
            }
            platform.setPlatformActionListener(listener);
            platform.share(sharePlatform);
        } else if (shareType == Constants.Share.SHARE_QQ_ZONE) {
            setSharePlatform(sharePlatform, shareTitle, shareText, shareImageUrl, logo, shareUrl);
            if (shareUrl != null && !shareUrl.equalsIgnoreCase("")) {
                sharePlatform.setTitleUrl(shareUrl);
            }
            sharePlatform.setShareType(Platform.SHARE_WEBPAGE);
            platform = ShareSDK.getPlatform(QZone.NAME);
            if (!platform.isClientValid()) {
                ToastUtils.show("未安装QQ");
                return;
            }
            platform.setPlatformActionListener(listener);
            platform.share(sharePlatform);
        }
    }

    private static void setSharePlatform(Platform.ShareParams sharePlatform, String shareTitle, String shareText, String shareImageUrl, Bitmap logo, String url) {
        sharePlatform.setShareType(Platform.SHARE_WEBPAGE);
        sharePlatform.setTitle(shareTitle);

        if (shareText != null && !shareText.equalsIgnoreCase("")) {
            sharePlatform.setText(shareText);
        }
        if (shareImageUrl != null && !shareImageUrl.equalsIgnoreCase("")) {
            sharePlatform.setImageUrl(shareImageUrl);
        } else {
            sharePlatform.setImageData(logo);
        }
        sharePlatform.setUrl(url);
    }


    static class MyShareListener implements PlatformActionListener {

        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            Log.e(TAG, "onComplete: ");
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            Log.e(TAG, "onError: " + i + "   throwable   " + throwable.getMessage());
        }

        @Override
        public void onCancel(Platform platform, int i) {
            Log.e(TAG, "onCancel: ");
        }
    }

}
