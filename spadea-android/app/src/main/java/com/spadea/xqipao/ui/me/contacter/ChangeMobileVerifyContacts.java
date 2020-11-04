package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.contacter
 * 创建人 王欧
 * 创建时间 2020/3/26 5:06 PM
 * 描述 describe
 */
public final class ChangeMobileVerifyContacts {
    public interface View extends IView<Activity> {
        void bindSuccess();
    }

    public interface IChangeMobileVerifyPre extends IPresenter {

        void bindingMobile(String mobile, String code);
    }
}
