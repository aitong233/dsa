package com.spadea.xqipao.ui.room.fragment;

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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.utilcode.ScreenUtils;
import com.spadea.xqipao.data.RoomBgBean;
import com.spadea.xqipao.ui.room.presenter.RoomBackgroundPresenter;
import com.spadea.xqipao.ui.base.view.BaseDialogFragment;
import com.spadea.xqipao.ui.room.contacts.RoomBackgroudContacts;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.fragment
 * 创建人 王欧
 * 创建时间 2020/4/14 4:26 PM
 * 描述 describe
 */
public class RoomBackgroudDialogFragment extends BaseDialogFragment<RoomBackgroundPresenter> implements RoomBackgroudContacts.View {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    private String roomId;
    private String picture;
    private boolean pictureChanged;

    public static RoomBackgroudDialogFragment newInstance(String roomId, String picture) {
        RoomBackgroudDialogFragment fragment = new RoomBackgroudDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomId", roomId);
        bundle.putString("picture", picture);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new MyAdapter();
        mAdapter.setCheckedPicture(picture);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initView(View rootView) {
        if (getArguments() != null) {
            roomId = getArguments().getString("roomId");
            picture = getArguments().getString("picture");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_r5_80000000));
        window.setGravity(Gravity.CENTER);
        window.setLayout((int) (ScreenUtils.getScreenWidth() / 375.0 * 342.0), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setWindowAnimations(R.style.ShowDialogBottom);
        setCancelable(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragement_dialog_room_background;
    }

    @Override
    protected RoomBackgroundPresenter bindPresenter() {
        return new RoomBackgroundPresenter(this, mContext);
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
    public void onResume() {
        super.onResume();
        MvpPre.getBackgroundList();
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

    @OnClick(R.id.tv_action)
    public void onViewClicked() {
        MvpPre.setBg(roomId, mAdapter.getCheckedPicture());
    }

    private static class MyAdapter extends BaseQuickAdapter<RoomBgBean, BaseViewHolder> {

        MyAdapter() {
            super(R.layout.rv_item_room_bg);
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
                ImageLoader.loadImageBlurBg(MyApplication.getInstance(), MyApplication.getInstance().getUser().getHead_picture(), helper.getView(R.id.iv_bg));
            } else {
                ImageLoader.loadRoomBg(MyApplication.getInstance(), helper.getView(R.id.iv_bg), item.getPicture());
            }
            if (TextUtils.isEmpty(item.getName())) {
                helper.setVisible(R.id.tv_name, false);
            } else {
                helper.setVisible(R.id.tv_name, true);
                helper.setText(R.id.tv_name, item.getName());
            }
            if ((TextUtils.isEmpty(checkedPicture) && helper.getAdapterPosition() == 0) || (!(TextUtils.isEmpty(checkedPicture)) && checkedPicture.equals(item.getPicture()))) {
                helper.setBackgroundRes(R.id.cl, R.drawable.bg_room_bg_item_selected);
            } else {
                helper.setBackgroundRes(R.id.cl, R.drawable.bg_room_bg_item_normal);
            }
            helper.getView(R.id.cl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkedPicture = item.getPicture();
                    EventBus.getDefault().post(item);
                    notifyDataSetChanged();
                }
            });

        }
    }
}
