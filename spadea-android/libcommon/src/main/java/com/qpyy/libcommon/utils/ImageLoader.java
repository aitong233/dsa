package com.qpyy.libcommon.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.qpyy.libcommon.R;


/**
 * Copyright (c) 1
 *
 * @Description
 * @Author 1
 * @Copyright Copyright (c) 1
 * @Date 2017/12/11 0011 14:18
 */

public class ImageLoader {


    public static void loadHead(Context context, ImageView view, String url) {
        RequestOptions options = RequestOptions.circleCropTransform();
        GlideApp.with(context).load(url).apply(options).error(R.mipmap.default_avatar).placeholder(R.mipmap.default_avatar).diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
    }

    public static void loadImage(Context context, ImageView view, String url) {
        GlideApp.with(context).load(url).error(R.mipmap.default_image).placeholder(R.mipmap.default_image).diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
    }

    public static void loadIcon(Context context, ImageView view, String url) {
        if (TextUtils.isEmpty(url)) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
        GlideApp.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
    }

}
