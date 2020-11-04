package com.qpyy.room.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.contacts.PitCountDownContacts;
import com.qpyy.room.presenter.PitCountDownPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.utils.dialog.room
 * 创建人 王欧
 * 创建时间 2020/3/30 3:08 PM
 * 描述 describe
 */
public class CountDownChooseDialog extends BaseMvpDialogFragment<PitCountDownPresenter> implements PitCountDownContacts.View {
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;

    private String pitNumber;

    private String roomId;

    public static CountDownChooseDialog newInstance(String roomId, String pitNumber) {

        Bundle args = new Bundle();
        args.putString("roomId", roomId);
        args.putString("pitNumber", pitNumber);
        CountDownChooseDialog fragment = new CountDownChooseDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        roomId = arguments.getString("roomId");
        pitNumber = arguments.getString("pitNumber");
    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        window.setGravity(Gravity.BOTTOM);
    }

    @Override
    public void initView() {
        List<ItemBean> items = new ArrayList<>();
        items.add(new ItemBean(60, "一分钟"));
        items.add(new ItemBean(3 * 60, "三分钟"));
        items.add(new ItemBean(5 * 60, "五分钟"));
        items.add(new ItemBean(10 * 60, "十分钟"));
        items.add(new ItemBean(15 * 60, "十五分钟"));
        items.add(new ItemBean(0, "关闭"));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new BaseQuickAdapter<ItemBean, BaseViewHolder>(R.layout.room_rv_item_dialog_count_down, items) {
            @Override
            protected void convert(BaseViewHolder helper, ItemBean item) {
                helper.setText(R.id.text, item.title);
                helper.getView(R.id.text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtils.e("countDownItem", item.title);
                        if (item.second != 0) {
                            MvpPre.pitCountDown(roomId, pitNumber, String.valueOf(item.second));
                        } else {
                            dismiss();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_dialog_count_down_choose;
    }

    @Override
    protected PitCountDownPresenter bindPresenter() {
        return new PitCountDownPresenter(this, getActivity());
    }

    @Override
    public void initData() {

    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, "CountDownChooseDialog");
    }

    @Override
    public void pitCountDown(String roomId, String pitNumber, String time) {
        dismiss();
    }

    private static class ItemBean {
        int second;
        String title;

        ItemBean(int second, String title) {
            this.second = second;
            this.title = title;
        }
    }
}
