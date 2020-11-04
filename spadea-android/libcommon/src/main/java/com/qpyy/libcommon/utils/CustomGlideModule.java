package com.qpyy.libcommon.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.SDCardUtils;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

@GlideModule
public class CustomGlideModule extends AppGlideModule {


    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        int memoryCacheSizeBytes = 1024 * 1024 * 20; // 20mb
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
        if (SDCardUtils.isSDCardEnableByEnvironment()) {
            builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, "YUYANGImages", memoryCacheSizeBytes * 10));
        } else {
            builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "YUYANGImages", memoryCacheSizeBytes*10));
        }
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
