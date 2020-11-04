package com.spadea.xqipao.ui.room.fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ScreenUtils;
import com.spadea.xqipao.data.RowWheatModel;
import com.spadea.xqipao.ui.room.presenter.WheatPositionContactsPresenter;
import com.spadea.xqipao.ui.base.view.BaseDialogFragment;
import com.spadea.xqipao.ui.room.adapter.WheatAdapter;
import com.spadea.xqipao.ui.room.contacts.WheatPositionContacts;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WheatDialogFragment extends BaseDialogFragment<WheatPositionContactsPresenter> implements WheatPositionContacts.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.tv_clean_wheat)
    TextView tvCleanWheat;


    private String mRoomId;
    private WheatAdapter wheatAdapter;
    private RowWheatModel mRowWheatModel;
    private RoomFragmentListener mRoomFragmentListener;


    public static WheatDialogFragment newInstance(String roomId) {
        WheatDialogFragment wheatPositionDialogFragment = new WheatDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomId", roomId);
        wheatPositionDialogFragment.setArguments(bundle);
        return wheatPositionDialogFragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RoomFragmentListener) {
            mRoomFragmentListener = (RoomFragmentListener) context;
        }
    }


    @Override
    protected void initData() {
        MvpPre.applyWheatList(mRoomId);
    }


    @Override
    protected void initView(View rootView) {
        mRoomId = getArguments().getString("roomId");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(wheatAdapter = new WheatAdapter());
    }

    @Override
    protected void initListener() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                MvpPre.applyWheatList(mRoomId);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dialog_wheat;
    }

    @Override
    protected WheatPositionContactsPresenter bindPresenter() {
        return new WheatPositionContactsPresenter(this, mContext);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ScreenUtils.getScreenWidth(), (int) (ScreenUtils.getScreenHeight() * 5.5 / 10));
        window.setWindowAnimations(R.style.ShowDialogBottom);
    }

    @Override
    public void setApplyWheatList(List<RowWheatModel> data) {
        mRowWheatModel = null;
        wheatAdapter.setNewData(data);
        tvCount.setText("等待连麦人数(" + data.size() + ")");
        for (RowWheatModel item : data) {
            if (item.getUser_id().equals(MyApplication.getInstance().getUser().getUser_id())) {
                mRowWheatModel = item;
            }
        }
        if (mRowWheatModel != null) {
            tvCleanWheat.setVisibility(View.VISIBLE);
        } else {
            tvCleanWheat.setVisibility(View.GONE);
        }

    }


    @OnClick(R.id.tv_clean_wheat)
    public void onClick(View view) {
        if (mRowWheatModel != null) {
            MvpPre.deleteApply(mRowWheatModel.getId(),mRoomId);
        }
    }

    @Override
    public void agreeApplySuccess() {

    }

    @Override
    public void deleteApplySuccess() {
        MvpPre.applyWheatList(mRoomId);
    }

    @Override
    public void agreeApplyAllSuccess() {

    }

    @Override
    public void applyWheatListComplete() {
        smartRefreshLayout.finishRefresh();
    }


}
