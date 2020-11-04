package com.qpyy.module.me.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.me.bean.PhotoWallResp;

import java.io.File;

public final class UserPhotoWallContacts {

    public interface View extends IView<Activity> {
        void setPhotoWall(PhotoWallResp data);

        void deletePhotoSuccess(int index);

        void finishRefresh();

        void upLoadSuccess(String url, int type);

        void checkImageSuccess(String type, String image);

        void addPhotoSuccess();

        void uploadFileComplete();

    }


    public interface IUserPhotoWallPre extends IPresenter {
        void getPhotoWall(String userId, int p);

        void deletePhoto(String id, int index);

        void uploadFile(File file, int type);

        void checkImage(String image);

        void addPhoto(String image);
    }
}
