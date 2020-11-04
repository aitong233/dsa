package com.spadea.xqipao.ui.room.fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.xqipao.data.RowWheatModel;
import com.spadea.xqipao.ui.room.presenter.WheatPositionContactsPresenter;
import com.spadea.xqipao.ui.base.view.BaseDialogFragment;
import com.spadea.xqipao.ui.room.contacts.WheatPositionContacts;
import com.spadea.xqipao.ui.room.adapter.UpWheatAdapter;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ScreenUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/***
 * 麦序
 */
public class WheatPositionDialogFragment extends BaseDialogFragment<WheatPositionContactsPresenter> implements WheatPositionContacts.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_remove)
    TextView tvRemove;
    @BindView(R.id.tv_up_wheat)
    TextView tvUpWheat;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;


    private String mRoomId;
    private UpWheatAdapter upWheatAdapter;
    private RoomFragmentListener mRoomFragmentListener;

    public static WheatPositionDialogFragment newInstance(String roomId) {
        WheatPositionDialogFragment wheatPositionDialogFragment = new WheatPositionDialogFragment();
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

    }

    @Override
    protected void initView(View rootView) {
        mRoomId = getArguments().getString("roomId");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(upWheatAdapter = new UpWheatAdapter());
        upWheatAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RowWheatModel item = upWheatAdapter.getItem(position);
                switch (view.getId()) {
                    case R.id.tv_up_wheat:
                        MvpPre.agreeApply(item.getId(),mRoomId);
                        break;
                    case R.id.tv_remove:
                        MvpPre.deleteApply(item.getId(),mRoomId);
                        break;
                }
            }
        });
    }


    @OnClick({R.id.tv_remove, R.id.tv_up_wheat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_remove:
                String id = upWheatAdapter.idToString();
                if (TextUtils.isEmpty("暂无可操作数据")) {
                    return;
                }
                MvpPre.deleteApply(id,mRoomId);
                break;
            case R.id.tv_up_wheat:
                MvpPre.agreeApplyAll(mRoomId);
                break;
        }
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
        return R.layout.fragment_dialog_wheatposition;
    }

    @Override
    protected WheatPositionContactsPresenter bindPresenter() {
        return new WheatPositionContactsPresenter(this, mContext);
    }

    @Override
    public void onStart() {
        super.onStart();
        MvpPre.applyWheatList(mRoomId);
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ScreenUtils.getScreenWidth(), (int) (ScreenUtils.getScreenHeight() * 5.5 / 10));
        window.setWindowAnimations(R.style.ShowDialogBottom);
    }

    @Override
    public void setApplyWheatList(List<RowWheatModel> data) {
        tvCount.setText("等待连麦人数(" + data.size() + ")");
        upWheatAdapter.setNewData(data);
    }

    @Override
    public void agreeApplySuccess() {
        MvpPre.applyWheatList(mRoomId);
    }

    @Override
    public void deleteApplySuccess() {
        MvpPre.applyWheatList(mRoomId);
    }

    @Override
    public void agreeApplyAllSuccess() {
        MvpPre.applyWheatList(mRoomId);
    }

    @Override
    public void applyWheatListComplete() {
        smartRefreshLayout.finishRefresh();
    }
}
