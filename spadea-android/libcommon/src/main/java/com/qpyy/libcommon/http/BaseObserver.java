package com.qpyy.libcommon.http;


import com.blankj.utilcode.util.ThreadUtils;
import com.google.gson.JsonSyntaxException;
import com.hjq.toast.ToastUtils;
import com.orhanobut.logger.Logger;
import com.qpyy.libcommon.event.LogOutEvent;
import com.qpyy.libcommon.utils.DialogUtils;

import org.apache.http.conn.ConnectTimeoutException;
import org.greenrobot.eventbus.EventBus;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;

/**
 * Created by Administrator on 2017/11/22.
 */

public abstract class BaseObserver<T> implements Observer<T> {

    private boolean showErrMsg;

    public BaseObserver() {
        this(true);
    }

    public BaseObserver(boolean showErrMsg) {
        this.showErrMsg = showErrMsg;
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            ToastUtils.show("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectException) {
            ToastUtils.show("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectTimeoutException) {
            ToastUtils.show("网络中断，请检查您的网络状态");
        } else if (e instanceof UnknownHostException) {
            ToastUtils.show("网络中断，请检查您的网络状态");
        } else if (e instanceof IllegalStateException) {
            ToastUtils.show("解析失败");
        } else if (e instanceof APIException) {
            APIException apiException = (APIException) e;
            if (apiException.getCode() == -1) {
                EventBus.getDefault().post(new LogOutEvent());
            } else {
                onErrorCode(apiException.getCode());
            }
            if (showErrMsg) {
                ToastUtils.show(apiException.getMessage());
            }
        } else if (e instanceof JsonSyntaxException) {
            ToastUtils.show("网络请求错误");
        } else {
            ToastUtils.show("服务器错误");
        }
        e.printStackTrace();
        onComplete();
    }

    public void onErrorCode(int code) {
        Logger.e("onErrorCode", code);
        if (code == 2) {
            ThreadUtils.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DialogUtils.showNoBalance();
                }
            });
        }
    }
}
