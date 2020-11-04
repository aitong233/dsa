package com.qpyy.module_main.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module_main.R;
import com.qpyy.module_main.contacts.ImproveInfoContacts;
import com.qpyy.module_main.presenter.ImproveInfoPresenter;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module_main.activity
 * 创建人 王欧
 * 创建时间 2020/7/2 4:01 PM
 * 描述 describe
 */
@Route(path = ARouteConstants.IMPROVE_INFO)
public class ImproveInfoActivity extends BaseMvpActivity<ImproveInfoPresenter> implements ImproveInfoContacts.View {
    @Override
    protected ImproveInfoPresenter bindPresenter() {
        return new ImproveInfoPresenter(this, this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_improve_info;
    }
}
