package com.spadea.xqipao.ui.me.contacter;

import android.app.Activity;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.contacter
 * 创建人 王欧
 * 创建时间 2020/3/27 11:04 AM
 * 描述 describe
 */
public final class ChangePwdContacts {
    public interface View extends IView<Activity> {
        void success();
        void smsCodeSuccess();
    }

    public interface IChangePwdPre extends IPresenter {
        void resetPassword(String mobile, String pwd, String code);

        void smsCode(String mobile);
    }
}
