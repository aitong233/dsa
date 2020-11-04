package com.spadea.xqipao.ui.room.contacts;

import android.app.Activity;

import com.spadea.xqipao.data.SearchUserModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class AddContacts {

    public interface View extends IView<Activity> {
        void addAdminSuccess(String userId);

        void addRorbidSuccess(String userId);

        void deleteManagerSuccess(String userId);

        void deleteForbidSuccess(String userId);


        void setSearChUserData(List<SearchUserModel> data);

        void searchUserComplete();

        void success(SearchUserModel data, int posiont);
    }

    public interface IAddPre extends IPresenter {


        void searChUser(String roomId, String keyword, int type);

        void addManager(String roomId, String userId, SearchUserModel searchUserModel, int postion);

        void deleteManager(String roomId, String userId, SearchUserModel searchUserModel, int postion);

        void addRorbid(String roomId, String userId, SearchUserModel searchUserModel, int postion);

        void deleteForbid(String roomId, String userId, SearchUserModel searchUserModel, int postion);

        void getList(String roomId, int type);

    }


}
