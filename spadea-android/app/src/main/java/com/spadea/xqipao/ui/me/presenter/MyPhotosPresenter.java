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
import com.spadea.xqipao.data.MyPhotoItem;
import com.spadea.xqipao.data.api.BaseObserver;
import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.ui.me.contacter.MyPhotosContacts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class MyPhotosPresenter extends BasePresenter<MyPhotosContacts.View> implements MyPhotosContacts.IMyPhotosPre {
    public MyPhotosPresenter(MyPhotosContacts.View view, Context context) {
        super(view, context);
    }

    @Override
    public void getUserPhotos() {
        api.getUserPhotos(new BaseObserver<List<MyPhotoItem>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(List<MyPhotoItem> photoItems) {
                MvpRef.get().userPhotos(photoItems);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void deleteUserPhoto(String ids) {
        api.deleteUserPhotos(ids, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().deleteUserPhotoSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void addUserPhoto(String userPhoto) {
        api.addUserPhotos(userPhoto, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().addUserPhotoSuccess();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void uploadImage(List<LocalMedia> mediaList) {
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
                MvpRef.get().uploadImageSuccess(headPicUrl);
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