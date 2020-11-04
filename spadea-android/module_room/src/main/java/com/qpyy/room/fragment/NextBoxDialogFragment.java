package com.qpyy.room.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.contacts.NextBoxContacts;
import com.qpyy.room.presenter.NextBoxPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/9/25 15:03
 * 描述 盲盒礼物说明
 */
public class NextBoxDialogFragment extends BaseMvpDialogFragment<NextBoxPresenter> implements NextBoxContacts.View {
    @BindView(R2.id.iv_close)
    ImageView ivClose;
    @BindView(R2.id.tv_content)
    TextView tvContent;


    public static NextBoxDialogFragment newInstance() {
        Bundle args = new Bundle();
        NextBoxDialogFragment fragment = new NextBoxDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.room_next_box_dialog;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        window.setGravity(Gravity.CENTER);
        window.setLayout((int) (ScreenUtils.getScreenWidth() / 375.0 * 303.0), (int) (ScreenUtils.getScreenHeight() / 667.0 * 300.0));
        window.setDimAmount(0.4f);
        setCancelable(false);
    }


    @Override
    protected NextBoxPresenter bindPresenter() {
        return new NextBoxPresenter(this, getContext());
    }

    @Override
    public void initData() {
        MvpPre.getContent();
    }

    /**
     * 关闭
     */
    @OnClick(R2.id.iv_close)
    public void onCloseDialog() {
        dismiss();
    }

    @Override
    public void setContent(String content) {
        tvContent.setText(Html.fromHtml(content));
    }
}
