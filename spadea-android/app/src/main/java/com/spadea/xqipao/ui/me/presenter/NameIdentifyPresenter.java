package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.luck.picture.lib.entity.LocalMedia;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.util.oss.OSSOperUtils;
import com.spadea.yuyin.util.utilcode.FileUtils;
import com.spadea.yuyin.util.utilcode.TimeUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.NameAuthModel;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.data.api.JavaBaseObserver;
import com.spadea.xqipao.data.even.NameIdentifySuccessEvent;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.ui.me.contacter.NameIdentifyContacts;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class NameIdentifyPresenter extends BasePresenter<NameIdentifyContacts.View> implements NameIdentifyContacts.INameIdentifyPre {
    public NameIdentifyPresenter(NameIdentifyContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void sendCode(String phoneNumber, int type) {
        MvpRef.get().showLoadings();
        api.sendCode(MyApplication.getInstance().getToken(), phoneNumber, type, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                ToastUtils.showShort("短信验证码发送成功请注意查收");
                MvpRef.get().sendCodeSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void doAuth(NameAuthModel model) {
        MvpRef.get().showLoadings();
        api.nameAuth(String.valueOf(model.getUserId()), model.getFullName(), model.getIdNumber(), model.getIdCard(), model.getFront(), model.getBack(), new JavaBaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                EventBus.getDefault().post(new NameIdentifySuccessEvent());
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });

    }

    @Override
    public void uploadImage(List<LocalMedia> mediaList, int type) {
        LocalMedia localMedia1 = mediaList.get(0);
        String filePath = "";
        if (localMedia1.isCompressed()) {
            filePath = localMedia1.getCompressPath();
        } else {
            filePath = localMedia1.getPath();
        }
        File file = new File(filePath);
        String url = "android_images/" + MyApplication.getInstance().getUser().getUser_id() + "/" + TimeUtils.getNowString(new SimpleDateFormat("yyyyMMddHHmmss")) + "_" + FileUtils.getFileName(file);
        MvpRef.get().showLoadings();
        OSSOperUtils.newInstance().putObjectMethod(url, filePath, new OSSProgressCallback() {
            @Override
            public void onProgress(Object request, long currentSize, long totalSize) {

            }
        }, new OSSCompletedCallback() {
            @Override
            public void onSuccess(OSSRequest request, OSSResult result) {
                String headPicUrl = OSSOperUtils.AliYunOSSURLFile + url;
                MvpRef.get().disLoadings();
                MvpRef.get().uploadImageSuccess(headPicUrl, type);
            }

            @Override
            public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {
                LogUtils.e("上传数据失败", request);
                clientException.printStackTrace();
                serviceException.printStackTrace();
                MvpRef.get().disLoadings();
            }
        });
    }
}