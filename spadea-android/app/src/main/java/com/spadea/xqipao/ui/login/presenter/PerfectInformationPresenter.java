package com.spadea.xqipao.ui.login.presenter;

import android.content.Context;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.login.contacter.PerfectInformationContacts;
import com.spadea.yuyin.util.oss.OSSOperUtils;
import com.spadea.yuyin.util.utilcode.FileUtils;
import com.spadea.yuyin.util.utilcode.TimeUtils;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class PerfectInformationPresenter extends BasePresenter<PerfectInformationContacts.View> implements PerfectInformationContacts.IPerfectInformationPre {

    public PerfectInformationPresenter(PerfectInformationContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void uplodImg(List<LocalMedia> localMedia) {
        LocalMedia localMedia1 = localMedia.get(0);
        String url = "";
        if (localMedia1.isCompressed()) {
            url = localMedia1.getCompressPath();
        } else {
            url = localMedia1.getPath();
        }
        File file = new File(url);
        url = "android_images/" + MyApplication.getInstance().getUser().getUser_id() + "/" + TimeUtils.getNowString(new SimpleDateFormat("yyyyMMddHHmmss")) + "_" + FileUtils.getFileName(file);
        String finalUrl = url;
        OSSOperUtils.newInstance().putObjectMethod(url, localMedia1.getCompressPath(), new OSSProgressCallback() {
            @Override
            public void onProgress(Object request, long currentSize, long totalSize) {

            }
        }, new OSSCompletedCallback() {
            @Override
            public void onSuccess(OSSRequest request, OSSResult result) {
                MvpRef.get().uploadImg(OSSOperUtils.AliYunOSSURLFile + finalUrl);
            }

            @Override
            public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {
                LogUtils.e("上传数据失败", request);
                clientException.printStackTrace();
                serviceException.printStackTrace();
            }
        });
    }

    @Override
    public void updateUserInfo(String signature, String birthday, String constellation, String profession, String city_id, String user_photo, String sex, String head_picture, String nickname, String province_id,String userNo) {
        MvpRef.get().showLoadings();
        api.updateUserInfo(signature, birthday, constellation, profession, city_id, user_photo, sex, head_picture, nickname, province_id,userNo, "",new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().updateUserInfoSuccess();
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }
}
