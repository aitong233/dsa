package com.spadea.xqipao.ui.me.presenter;

import android.content.Context;

import com.spadea.xqipao.ui.base.presenter.BasePresenter;
import com.spadea.xqipao.ui.me.contacter.MineContacts;

public class MinePresenter extends BasePresenter<MineContacts.View> implements MineContacts.IMinePre {
    public MinePresenter(MineContacts.View view, Context context) {
        super(view, context);
    }
}