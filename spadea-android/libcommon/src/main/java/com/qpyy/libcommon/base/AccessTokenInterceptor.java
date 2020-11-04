package com.qpyy.libcommon.base;

import com.qpyy.libcommon.utils.Sha1Util;
import com.qpyy.libcommon.utils.SpUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class AccessTokenInterceptor implements Interceptor {

    private final static String TAG = AccessTokenInterceptor.class.getCanonicalName();

    private static final Map<String, String> mHeaderMap = new ConcurrentHashMap<>();

    public static String token = "";

    public AccessTokenInterceptor(Map<String, String> headers) {
        mHeaderMap.putAll(headers);
    }


    public Map<String, String> getHeaders() {
        return mHeaderMap;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        mHeaderMap.put("X-Token", SpUtils.getToken());
        mHeaderMap.put("token", SpUtils.getToken());
        long timestamp = System.currentTimeMillis() / 1000;
        mHeaderMap.put("timestamp", String.valueOf(timestamp));
        mHeaderMap.put("sign", Sha1Util.shaEncode(timestamp));
        Request newRequest = request.newBuilder().headers(Headers.of(mHeaderMap)).build();
        Response response = chain.proceed(newRequest);
        return response;
    }
}
