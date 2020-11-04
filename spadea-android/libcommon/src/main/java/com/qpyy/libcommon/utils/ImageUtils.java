package com.qpyy.libcommon.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.widget.ImageView;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.qpyy.libcommon.BuildConfig;
import com.qpyy.libcommon.R;
import com.qpyy.libcommon.constant.Constants;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p> 图片加载工具类</p>
 *
 * @name ImageUtils
 */
public class ImageUtils {

    public static final int ANIM = -1;

    /**
     * 默认加载
     */
    public static void loadImageView(String path, ImageView mImageView) {
        Glide.with(mImageView.getContext()).load(path).into(mImageView);
    }

    /**
     * 默认加载
     */
    public static void loadDecorationAvatar(String path, SVGAImageView mImageView) {
        if (path.endsWith(".svga")) {
            try {
                SVGAParser.Companion.shareParser().decodeFromURL(new URL(path), new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                        if (mImageView != null) {
                            SVGADrawable svgaDrawable = new SVGADrawable(svgaVideoEntity);
                            mImageView.setImageDrawable(svgaDrawable);
                            mImageView.startAnimation();
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            if (mImageView.isAnimating()) {
                mImageView.stopAnimation();
            }
            Glide.with(mImageView.getContext()).load(path).into(mImageView);
        }
    }

    public static void loadHeadCC(String path, ImageView mImageView) {
        Glide.with(mImageView.getContext()).load(path).error(R.mipmap.default_avatar).placeholder(R.mipmap.default_avatar).centerCrop().into(mImageView);
    }

    public static void loadCenterCrop(String path, ImageView mImageView) {
        Glide.with(mImageView.getContext()).load(path).centerCrop().into(mImageView);
    }

    public static void loadRes(int path, ImageView mImageView) {
        Glide.with(mImageView.getContext()).load(path).into(mImageView);
    }

    public static void loadImageBlurBg(String url, ImageView imageView) {
        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.mipmap.room_bg).error(R.mipmap.room_bg).diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(imageView).asBitmap().apply(options).load(url).into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                try {
                    Drawable foregroundDrawable = FastBlurUtil.getForegroundDrawable(resource);
                    imageView.setImageDrawable(foregroundDrawable);
                } catch (Exception e) {
                    imageView.setImageResource(R.mipmap.room_bg);
                }
            }
        });
    }

    public static void loadRoomBg(String url, ImageView imageView) {
        RequestOptions options = new RequestOptions().centerCrop().placeholder(R.mipmap.room_bg).error(R.mipmap.room_bg).diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(imageView).load(url).apply(options).into(imageView);
    }


    /**
     * 设置加载中以及加载失败图片
     */
    public static void loadImageWithLoading(String path, ImageView mImageView, int lodingImage, int errorRes) {
        Glide.with(mImageView.getContext()).load(path).placeholder(lodingImage).
                error(errorRes).into(mImageView);
    }


    /**
     * 加载为bitmap
     *
     * @param path     图片地址
     * @param listener 回调
     */
    public static void loadBitmap(String path, final onLoadBitmap listener) {
        Glide.with(Utils.getApp()).asBitmap().load(path).into(new SimpleTarget<Bitmap>() {

            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                listener.onReady(resource);
            }

        });
    }

    public static void loadGift(ImageView view, int res) {
        if (res == ANIM) {
            try {
                AnimationDrawable background = (AnimationDrawable) view.getBackground();
                if (background != null) {
                    background.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Glide.with(view.getContext()).asGif().load(res).into(view);
        }
    }

    /**
     * 加载bitmap回调
     */
    public interface onLoadBitmap {
        void onReady(Bitmap resource);
    }

    public static void loadOneTimeGif(Context context, Object model, final ImageView imageView) {
        Glide.with(context).asGif().load(model).listener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                try {
                    Field gifStateField = GifDrawable.class.getDeclaredField("state");
                    gifStateField.setAccessible(true);
                    Class gifStateClass = Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable$GifState");
                    Field gifFrameLoaderField = gifStateClass.getDeclaredField("frameLoader");
                    gifFrameLoaderField.setAccessible(true);
                    Class gifFrameLoaderClass = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader");
                    Field gifDecoderField = gifFrameLoaderClass.getDeclaredField("gifDecoder");
                    gifDecoderField.setAccessible(true);
                    Class gifDecoderClass = Class.forName("com.bumptech.glide.gifdecoder.GifDecoder");
                    Object gifDecoder = gifDecoderField.get(gifFrameLoaderField.get(gifStateField.get(resource)));
                    Method getDelayMethod = gifDecoderClass.getDeclaredMethod("getDelay", int.class);
                    getDelayMethod.setAccessible(true);
                    //设置只播放一次
                    resource.setLoopCount(1);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }).into(imageView);
    }

    public static String getImagePath() {
        String path = Constants.IMAGE_PATH;
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        } else {
            return path;
        }
    }

    public static String getUrl(String url) {
        url = EncodeUtils.htmlDecode(url).toString();
        if (!TextUtils.isEmpty(url) && !url.contains("http"))
            url = BuildConfig.BASE_URL + url;
        return url;
    }
}