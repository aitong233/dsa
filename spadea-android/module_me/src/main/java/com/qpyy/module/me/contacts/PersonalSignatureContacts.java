package com.qpyy.module.me.contacts;

import android.app.Activity;

import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.base.IView;

public final class PersonalSignatureContacts {

    public interface View extends IView<Activity> {
        void updataSuccess();
    }

    public interface IPersonalSignaturePre extends IPresenter {
        void updataPersonalSignature(String text);
    }
}
