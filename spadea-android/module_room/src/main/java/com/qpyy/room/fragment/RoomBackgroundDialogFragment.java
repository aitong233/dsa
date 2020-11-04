package com.qpyy.room.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.RoomBgBean;
import com.qpyy.room.contacts.RoomBackgroundContacts;
import com.qpyy.room.presenter.RoomBackgroundPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.fragment
 * 创建人 黄强
 * 创建时间 2020/8/3 14:17
 * 描述 describe
 */
public class RoomBackgroundDialogFragment extends BaseMvpDialogFragment<RoomBackgroundPresenter> implements RoomBackgroundContacts.View {

    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    private String roomId;
    private String picture;
    private boolean pictureChanged;

    public static RoomBackgroundDialogFragment newInstance(String roomId, String picture) {
        RoomBackgroundDialogFragment fragment = new RoomBackgroundDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomId", roomId);
        bundle.putString("picture", picture);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new MyAdapter();
        mAdapter.setCheckedPicture(picture);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        roomId = arguments.getString("roomId");
        picture = arguments.getString("picture");
    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        window.setGravity(Gravity.CENTER);
        window.setLayout((int) (ScreenUtils.getScreenWidth() / 375.0 * 304.0), WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0.4f;
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        setCancelable(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_fragement_dialog_room_background;
    }

    @Override
    protected RoomBackgroundPresenter bindPresenter() {
        return new RoomBackgroundPresenter(this, getActivity());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        pictureChanged = false;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (!pictureChanged) {
            EventBus.getDefault().post(new RoomBgBean());
        }
        super.onDismiss(dialog);
    }

    @Override
    public void setSuccess(String bgPicture) {
        pictureChanged = true;
        dismiss();
    }

    @Override
    public void backgroundList(List<RoomBgBean> list) {
        List<RoomBgBean> datas = new ArrayList<>();
        RoomBgBean defaultBg = new RoomBgBean();
        defaultBg.setId("default");
        defaultBg.setPicture("");
        defaultBg.setName("默认");
        datas.add(defaultBg);
        datas.addAll(list);
        mAdapter.setNewData(datas);
    }

    @Override
    public void onResume() {
        super.onResume();
        MvpPre.getBackgroundList();
    }


    @OnClick(R2.id.tv_action)
    public void onViewClicked() {
        String checkedPicture = mAdapter.getCheckedPicture();
        MvpPre.setBg(roomId, checkedPicture);
//        EventBus.getDefault().post(item);
    }

    @Override
    public void showLoadings(String content) {

    }

    private static class MyAdapter extends BaseQuickAdapter<RoomBgBean, BaseViewHolder> {

        MyAdapter() {
            super(R.layout.room_rv_item_room_bg);
        }

        private String checkedPicture;

        public void setCheckedPicture(String checkedPicture) {
            this.checkedPicture = checkedPicture;
        }

        String getCheckedPicture() {
            return checkedPicture;
        }

        @Override
        protected void convert(BaseViewHolder helper, RoomBgBean item) {
            if (TextUtils.isEmpty(item.getPicture())) {
                ImageUtils.loadImageBlurBg(BaseApplication.getIns().getUser().getHead_picture(), helper.getView(R.id.iv_bg));
            } else {
                ImageUtils.loadRoomBg(item.getPicture(), helper.getView(R.id.iv_bg));
            }
            if (TextUtils.isEmpty(item.getName())) {
                helper.setVisible(R.id.tv_name, false);
            } else {
                helper.setVisible(R.id.tv_name, true);
                helper.setText(R.id.tv_name, item.getName());
            }
            if ((TextUtils.isEmpty(checkedPicture) && helper.getAdapterPosition() == 0) || (!(TextUtils.isEmpty(checkedPicture)) && checkedPicture.equals(item.getPicture()))) {
                helper.setBackgroundRes(R.id.cl, R.drawable.room_bg_room_bg_item_selected);
            } else {
                helper.setBackgroundRes(R.id.cl, R.drawable.room_bg_room_bg_item_normal);
            }
            helper.getView(R.id.cl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkedPicture = item.getPicture();
                    notifyDataSetChanged();
                }
            });

        }
    }
}
