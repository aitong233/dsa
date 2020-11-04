package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.luck.picture.lib.entity.LocalMedia;
import com.spadea.xqipao.data.MyPhotoItem;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class MyPhotosContacts {


    public interface View extends IView<Activity> {
        void uploadImageSuccess(String imageUrl);

        void addUserPhotoSuccess();

        void deleteUserPhotoSuccess();

        void userPhotos(List<MyPhotoItem> photoItems);
    }

    public interface IMyPhotosPre extends IPresenter {
        void getUserPhotos();

        void deleteUserPhoto(String ids);

        void addUserPhoto(String userPhoto);

        void uploadImage(List<LocalMedia> mediaList);
    }
}