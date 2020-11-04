package com.qpyy.module_main.presenter;

import android.content.Context;

import com.qpyy.libcommon.base.BasePresenter;
import com.qpyy.module_main.contacts.ImproveInfoContacts;

public class ImproveInfoPresenter extends BasePresenter<ImproveInfoContacts.View> implements ImproveInfoContacts.IImproveInfoPre {
    public ImproveInfoPresenter(ImproveInfoContacts.View view, Context context) {
        super(view, context);
    }
}