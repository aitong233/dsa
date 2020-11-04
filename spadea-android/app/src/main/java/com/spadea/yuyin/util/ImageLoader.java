package com.spadea.yuyin.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.util.utilcode.ConvertUtils;
import com.spadea.yuyin.util.utilcode.EncodeUtils;
import com.spadea.yuyin.R;
import com.spadea.yuyin.net.UrlUtils;
import com.spadea.xqipao.utils.FastBlurUtil;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Copyright (c) 1
 *
 * @Description
 * @Author 1
 * @Copyright Copyright (c) 1
 * @Date 2017/12/11 0011 14:18
 */

public class ImageLoader {


    public static void loadHead(final Context context, ImageView view, String url) {
        RequestOptions options = RequestOptions.circleCropTransform();

        Glide.with(context).load(url).apply(options).transition(DrawableTransitionOptions.withCrossFade(600)).into(view);
    }

    public static void loadHeadWithoutPlaceholder(final Context context, ImageView view, String url) {
        Glide.with(context).load(url).into(view);
    }

    public static void loadImage(Context context, ImageView view, String url) {
        Glide.with(context).load(url).error(R.drawable.shape_deficon).transition(DrawableTransitionOptions.withCrossFade(600)).into(view);
    }


    public static void loadGif(Context context, ImageView view, String url) {
        Glide.with(context).load(url).transition(DrawableTransitionOptions.withCrossFade(600)).into(view);
    }
    public static void loadImageCenterCrop(Context context, ImageView view, String url) {
        Glide.with(context).load(url).centerCrop().error(R.drawable.default_image).placeholder(R.drawable.default_image).into(view);
    }
    public static void loadImage( ImageView view, String url) {
        Glide.with(view.getContext()).load(url).error(R.drawable.shape_deficon).transition(DrawableTransitionOptions.withCrossFade(600)).into(view);
    }

    public static void loadImageNC( ImageView view, String url) {
        Glide.with(view.getContext()).load(url).error(R.drawable.shape_deficon).into(view);
    }

    public static void loadImage(ImageView view, String url, @DrawableRes int placeholder) {
        Glide.with(MyApplication.getInstance()).load(url).error(placeholder).placeholder(placeholder).into(view);
    }

    public static String getUrl(String url) {
        url = EncodeUtils.htmlDecode(url).toString();
        if (!TextUtils.isEmpty(url) && !url.contains("http"))
            url = new UrlUtils().getAPIHTTP() + url;
        return url;
    }


    public static void loadImageBlurBg(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.room_icon_bg3).error(R.drawable.room_icon_bg3).diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).asBitmap().apply(options).load(url).into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                try {
                    Drawable foregroundDrawable = FastBlurUtil.getForegroundDrawable(context, resource);
                    imageView.setImageDrawable(foregroundDrawable);
                } catch (Exception e) {
                    imageView.setImageResource(R.drawable.room_icon_bg3);
                }
            }
        });
    }

    public static void loadRoomBg(Context context, ImageView imageView, String url) {
        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.room_icon_bg3).error(R.drawable.room_icon_bg3).diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadRoomBgWithWH(Context context, ImageView imageView, String url, int sampleWith, int sampleHeight) {
        RequestOptions options = new RequestOptions().centerCrop().override(sampleWith, sampleHeight).placeholder(R.drawable.room_icon_bg3).error(R.drawable.room_icon_bg3).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new RoundedCornersTransformation(ConvertUtils.dp2px(3), 1));
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void loadBgWithCorner(Context context, ImageView imageView, String url, int corner) {
        RequestOptions options = new RequestOptions().placeholder(R.drawable.bg_rv_item_all_live).error(R.drawable.bg_rv_item_all_live).diskCacheStrategy(DiskCacheStrategy.ALL).transform(new CenterCrop(), new RoundedCorners(ConvertUtils.dp2px(corner))
        );
        Glide.with(context).load(url).apply(options).into(imageView);
    }
}
