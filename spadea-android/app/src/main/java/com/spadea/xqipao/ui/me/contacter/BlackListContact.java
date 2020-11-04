package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.data.BlacListSectionBean;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.contacter
 * 创建人 王欧
 * 创建时间 2020/4/1 1:56 PM
 * 描述 describe
 */
public class BlackListContact {
    public interface View extends IView<Activity> {
        void blackList(List<BlacListSectionBean> listBeans);

        void userRemoved(int position);
    }

    public interface BlackListPre extends IPresenter {
        void getBlackList(int page,String keyword);

        void removeUser(String blackId, int position);
    }

}
