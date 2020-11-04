package com.qpyy.module.me.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.libcommon.bean.CheckImageResp;
import com.qpyy.libcommon.http.BaseObserver;
import com.qpyy.libcommon.utils.oss.OSSOperUtils;
import com.qpyy.module.me.api.ApiClient;
import com.qpyy.module.me.bean.PhotoWallResp;
import com.qpyy.module.me.contacts.UserPhotoWallContacts;

import java.io.File;

import io.reactivex.disposables.Disposable;

public class UserPhotoWallPresenter extends BasePresenter<UserPhotoWallContacts.View> implements UserPhotoWallContacts.IUserPhotoWallPre {


    public UserPhotoWallPresenter(UserPhotoWallContacts.View view, Context context) {
        super(view, context);
    }


    @Override
    public void getPhotoWall(String userId, int p) {
        ApiClient.getInstance().photoWall(userId, p, new BaseObserver<PhotoWallResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(PhotoWallResp photoWallResp) {
                MvpRef.get().setPhotoWall(photoWallResp);
            }

            @Override
            public void onComplete() {
                MvpRef.get().finishRefresh();
            }
        });
    }

    @Override
    public void deletePhoto(String id, int index) {
        MvpRef.get().showLoadings();
        ApiClient.getInstance().deletePhoto(id, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().deletePhotoSuccess(index);
            }

            @Override
            public void onComplete() {
                MvpRef.get().disLoadings();
            }
        });
    }

    @Override
    public void uploadFile(File file, int type) {
        MvpRef.get().showLoadings("上传中...");
        String url = OSSOperUtils.getPath(file, type);
        OSSOperUtils.newInstance().putObjectMethod(url, file.getPath(), new OSSOperUtils.OssCallback() {
            @Override
            public void onSuccess() {
                MvpRef.get().upLoadSuccess(OSSOperUtils.AliYunOSSURLFile + url, type);
                MvpRef.get().disLoadings();
                MvpRef.get().uploadFileComplete();
            }

            @Override
            public void onFail() {
                MvpRef.get().disLoadings();
                MvpRef.get().uploadFileComplete();
            }
        });
    }

    @Override
    public void checkImage(String image) {
        api.checkImage(image,null, new BaseObserver<CheckImageResp>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(CheckImageResp checkImageResp) {
                MvpRef.get().checkImageSuccess(checkImageResp.getAction(), image);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void addPhoto(String image) {
        ApiClient.getInstance().addPhoto(image, new BaseObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                MvpRef.get().addPhotoSuccess();
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
