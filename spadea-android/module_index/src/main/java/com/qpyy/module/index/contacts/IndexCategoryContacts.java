package com.qpyy.module.index.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.module.index.bean.BannerModel;
import com.qpyy.module.index.bean.RoomTypeModel;

import java.util.List;

public final class IndexCategoryContacts {


    public interface View extends IView<Activity> {
        void setCategories(List<RoomTypeModel> list);

        void setBanners(List<BannerModel> list);
    }

    public interface IIndexCategoryPre extends IPresenter {
        void getCategories();

        void getBanners();
    }
}