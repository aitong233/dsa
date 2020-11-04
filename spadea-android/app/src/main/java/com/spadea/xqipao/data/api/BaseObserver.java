package com.spadea.xqipao.data.api;


import com.google.gson.JsonSyntaxException;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.util.utilcode.LogUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import io.reactivex.Observer;

/**
 * Created by Administrator on 2017/11/22.
 */

public abstract class BaseObserver<T> implements Observer<T> {


    private ArrayList<Integer> resultCodes;

    public BaseObserver() {
    }

    public BaseObserver(int... a) {
        resultCodes = new ArrayList<>();
        for (int code : a) {
            resultCodes.add(code);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            ToastUtils.showShort("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectException) {
            ToastUtils.showShort("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectTimeoutException) {
            ToastUtils.showShort("网络中断，请检查您的网络状态");
        } else if (e instanceof UnknownHostException) {
            ToastUtils.showShort("网络中断，请检查您的网络状态");
        } else if (e instanceof IllegalStateException) {
            ToastUtils.showShort("解析失败");
        } else if (e instanceof APIException) {
            APIException apiException = (APIException) e;
            if (!apiException.isJava() && apiException.getCode() == -1) {
                MyApplication.getInstance().reLogin();
            }
            //JAVA code=403需要重新登录
            if (apiException.isJava() && apiException.getCode() == ResultCode.UNAUTHORIZED.getCode()) {
                MyApplication.getInstance().reLogin();
            }
            if (apiException.isJava() && resultCodes != null && resultCodes.contains(apiException.getCode())) {
                onErrorCode(apiException.getCode());
            } else {
                ToastUtils.showShort(apiException.getMessage());
            }
        } else if (e instanceof JsonSyntaxException) {
            ToastUtils.showShort("网络请求错误");
        } else {
            ToastUtils.showShort("服务器错误");
        }
        e.printStackTrace();
        onComplete();
    }

    public void onErrorCode(int code) {
        LogUtils.e(code, "errorCode");
    }
}
