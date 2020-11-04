package com.qpyy.room.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;
import com.qpyy.room.bean.MusicResp;
import com.qpyy.room.bean.SearchUserModel;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.contacts
 * 创建人 黄强
 * 创建时间 2020/7/30 18:56
 * 描述 describe
 */
public class SearchAdminContacts {
    public interface View extends IView<Activity> {
        void addAdminSuccess(String userId);//添加管理员成功接口

        void addBlacklistSuccess(String userId);//添加黑名单成功接口

        void deleteAdminSuccess(String userId);//删除管理员成功接口

        void deleteBlacklistSuccess(String userId);//删除黑名单成功接口


        void setSearchUserData(List<SearchUserModel> data);//搜索的用户数据接口

        void searchUserComplete();//搜索用户数据成功接口

        void success(SearchUserModel data, int position);

        void kickOutSuccess(String userName);

    }

    public interface SearchAdminIpre extends IPresenter {
        void searchUser(String roomId, String keyword, int type);//搜索用户

        void addAdmin(String roomId, String userId, SearchUserModel searchUserModel, int position);//添加管理

        void deleteAdmin(String roomId, String userId, SearchUserModel searchUserModel, int position);//删除管理

        void addBlacklist(String roomId, String userId, SearchUserModel searchUserModel, int position);//添加黑名单

        void deleteBlacklist(String roomId, String userId, SearchUserModel searchUserModel, int position);//删除黑名单

        void getList(String roomId, int type);

        void kickOut(String userId, String roomId, String userName);
    }
}
