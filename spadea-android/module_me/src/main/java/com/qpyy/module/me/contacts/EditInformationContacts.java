package com.qpyy.module.me.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.me.bean.ProfessionBean;
import com.qpyy.module.me.bean.RegionListResp;
import com.qpyy.module.me.bean.UserHomeResp;

import java.io.File;
import java.util.List;
import java.util.Map;

public final class EditInformationContacts {


    public interface View extends IView<Activity> {
        void updateSuccess();

        void upLoadSuccess(String url, int type);

        void setProfession(List<ProfessionBean> data);

        void setRegionList(List<RegionListResp> data);

        void setUserHomePage(UserHomeResp userHomePage);



        void updateAvatarSuccess(String url);

        void showSelectSexDialog();
    }

    public interface IEditInformationPre extends IPresenter {
        void upDateUserInfo(Map<String, String> map);

        void uploadFile(File file, int type);

        void getProfession();

        void getRegionList();

        void getUserHomePage(String userId);



        void updateAvatar(String headPicture);

        void verifyUserSex();

    }

}
