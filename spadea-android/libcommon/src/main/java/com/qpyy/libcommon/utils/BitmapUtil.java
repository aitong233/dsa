package com.qpyy.libcommon.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.LruCache;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BitmapUtil {


    private static Application application;

    private static LruCache<String, Bitmap> lruCache;

    public BitmapUtil() {
        int memorySize = (int) Runtime.getRuntime().maxMemory() / 1024;
        int cacheSize = memorySize / 8;
        lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //计算每一个缓存Bitmap的所占内存的大小
                return value.getByteCount() / 1024;
            }
        };
    }


    public static boolean saveBitmapToSdCard(Context context, Bitmap mybitmap, String name) {
        boolean result = false;
        //创建位图保存目录
        String path = Environment.getExternalStorageDirectory() + "/YuTang/";
        File sd = new File(path);
        if (!sd.exists()) {
            sd.mkdir();
        }
        File file = new File(path + name + ".jpg");
        FileOutputStream fileOutputStream = null;
        if (!file.exists()) {
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    fileOutputStream = new FileOutputStream(file);
                    mybitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    //update gallery
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri uri = Uri.fromFile(file);
                    intent.setData(uri);
                    context.sendBroadcast(intent);
                    result = true;
                } else {
                    Toast.makeText(context, "不能读取到SD卡", Toast.LENGTH_SHORT).show();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static Map<String, Bitmap> bitmapMap = new HashMap<>();

    public static Bitmap getImag(String path) {
        try {
            if (TextUtils.isEmpty(path)) {
                return null;
            }
            if (bitmapMap.get(path) != null) {
                return bitmapMap.get(path);
            }
            URL url = new URL(path);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
            bitmapMap.put(path, bitmap);
            return bitmap;
        } catch (IOException e) {
            return null;
        }
    }


    public static Drawable getDrawable(Context context, int id) {
        return context.getResources().getDrawable(id);
    }
}
