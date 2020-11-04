package com.spadea.xqipao.ui.login.contacter;

import android.app.Activity;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

public final class PerfectInformationContacts {


    public interface View extends IView<Activity> {
        void uploadImg(String url);

        void updateUserInfoSuccess();
    }

    public interface IPerfectInformationPre extends IPresenter {
        void uplodImg(List<LocalMedia> localMedia);

        void updateUserInfo(String signature, String birthday, String constellation, String profession,
                            String city_id, String user_photo, String sex, String head_picture,
                            String nickname, String province_id,String userNo);
    }

}
