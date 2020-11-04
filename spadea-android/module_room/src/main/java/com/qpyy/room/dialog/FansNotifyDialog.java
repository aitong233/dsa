package com.qpyy.room.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.FansNotifyInfo;
import com.qpyy.room.contacts.FansNotifyContacts;
import com.qpyy.room.presenter.FansNotifyPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/8/4 13:39
 * 描述 粉丝通知弹窗
 */
public class FansNotifyDialog extends BaseMvpDialogFragment<FansNotifyPresenter> implements FansNotifyContacts.View {

    private static final String TAG = "FansNotifyDialog";
    @BindView(R2.id.tx_title)
    TextView txTitle;
    @BindView(R2.id.use_hint_txt)
    TextView useHintTxt;
    @BindView(R2.id.bt_open)
    Button btOpen;
    @BindView(R2.id.tv_surplus)
    TextView tvSurplus;


    private String roomId;

    public static FansNotifyDialog newInstance(String roomId) {

        Bundle args = new Bundle();
        args.putString("roomId", roomId);
        FansNotifyDialog fragment = new FansNotifyDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected FansNotifyPresenter bindPresenter() {
        return new FansNotifyPresenter(this, getActivity());
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        this.roomId = arguments.getString("roomId");
    }


    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        int width = (int) (ScreenUtils.getScreenWidth() / 375.0 * 335);
        window.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0.4f;
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_dilaog_fans_notify;
    }


    @Override
    public void initData() {
        MvpPre.fansNotifyInfo();
    }

    @Override
    protected void initView() {

    }

    @OnClick(R2.id.bt_open)
    public void onViewClicked() {
        MvpPre.fansNotify(roomId);
    }

    @Override
    public void fansNotifyInfo(FansNotifyInfo info) {
        int notifyCounts = info.getLeft_count();//粉丝通知次数
        boolean isFreeOpen = "1".equals(info.getIs_free());//免费开启通知
        String surplusTxt = "今日剩余次数: " + notifyCounts + "次";//剩余次数提示
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(surplusTxt);
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(getContext().getResources().getColor(R.color.color_FF6765FF));
        if (notifyCounts == 0 && !isFreeOpen) {
            redSpan = new ForegroundColorSpan(getContext().getResources().getColor(R.color.color_FF6BA4));
            btOpen.setText("明日可用");
            btOpen.setBackgroundResource(R.drawable.room_notify_nofocus_bg);
            btOpen.setEnabled(false);
        }
        if (isFreeOpen) {
            btOpen.setText("开启通知免费");
        }
        if (notifyCounts > 0 && !isFreeOpen) {
            btOpen.setText(String.format("%s金币1次", info.getPrice()));
            if (notifyCounts >= 2) {
                tvSurplus.setText(String.format("每日最多发送%s次", info.getTimes_per_day()));
                return;
            }
        }
        stringBuilder.setSpan(redSpan, 8, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//修改第几个文字的颜色
        tvSurplus.setText(stringBuilder);
    }

    @Override
    public void success() {
        ToastUtils.show("通知成功");
        dismiss();
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, TAG);
    }
}
