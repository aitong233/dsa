package com.spadea.xqipao.ui.me.contacter;

import android.support.v4.app.FragmentActivity;

import com.luck.picture.lib.entity.LocalMedia;
import com.spadea.xqipao.data.NameAuthModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class NameIdentifyContacts {


    public interface View extends IView<FragmentActivity> {
        void sendCodeSuccess();

        void uploadImageSuccess(String imageUrl, int type);

    }

    public interface INameIdentifyPre extends IPresenter {
        void sendCode(String phoneNumber, int type);

        void doAuth(NameAuthModel model);

        void uploadImage(List<LocalMedia> mediaList, int type);
    }
}