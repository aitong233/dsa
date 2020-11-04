package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.WaitForAdapter;
import com.qpyy.room.bean.ApplyWheatUsersResp;
import com.qpyy.room.contacts.WaitForContacts;
import com.qpyy.room.presenter.WaitForPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 排麦人数弹窗
 */
public class WaitForDialogFragment extends BaseMvpDialogFragment<WaitForPresenter> implements WaitForContacts.View {


    @BindView(R2.id.tv_count)
    TextView tvCount;
    @BindView(R2.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R2.id.tv_ordinary)
    TextView tvOrdinary;
    @BindView(R2.id.tv_up)
    TextView tvUp;
    @BindView(R2.id.ll_host)
    LinearLayout llHost;
    @BindView(R2.id.tv_cancel_wheat)
    TextView tvCancelWheat;
    private String roomId;
    private int role = 0;
    private int page = 1;
    private WaitForAdapter mWaitForAdapter;


    public static WaitForDialogFragment newInstance(String roomId, int role) {
        WaitForDialogFragment waitForDialogFragment = new WaitForDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomId", roomId);
        bundle.putInt("role", role);
        waitForDialogFragment.setArguments(bundle);
        return waitForDialogFragment;
    }


    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        roomId = getArguments().getString("roomId");
        role = getArguments().getInt("role");
    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0.4f;
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    protected WaitForPresenter bindPresenter() {
        return new WaitForPresenter(this, getActivity());
    }

    @Override
    protected void initData() {
        MvpPre.applyWheatUsers(roomId);
    }

    @Override
    protected void initView() {
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setAdapter(mWaitForAdapter = new WaitForAdapter(role));
        mWaitForAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ApplyWheatUsersResp.ListData item = mWaitForAdapter.getItem(position);
                int id = view.getId();
                if (id == R.id.tv_remove) {
                    MvpPre.deleteApply(item.getId(), roomId, position);
                } else if (id == R.id.tv_up) {
                    MvpPre.agreeApply(item.getId(), roomId, position);
                }
            }
        });
        //如果非管理
        if (role > 2) {
            llHost.setVisibility(View.GONE);
            tvCancelWheat.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_roome_dialog_fragment_wait_for;
    }


    @Override
    public void agreeApplySuccess(int postion) {
        MvpPre.applyWheatUsers(roomId);
    }

    @Override
    public void deleteApplySuccess(int postion) {
        MvpPre.applyWheatUsers(roomId);
    }

    @Override
    public void agreeApplyAllSuccess() {
        this.dismiss();
    }

    private String selfId;

    @Override
    public void setApplyWheatUsersData(List<ApplyWheatUsersResp.ListData> data) {
        mWaitForAdapter.setNewData(data);
        for (ApplyWheatUsersResp.ListData item : data) {
            if (item.getUser_id().equals(SpUtils.getUserId())) {
                selfId = item.getId();
            }
        }
    }

    @Override
    public void setUserCount(String count) {
        tvCount.setText(count);
    }

    public void show(FragmentManager childFragmentManager) {
        show(childFragmentManager, "WaitForDialogFragment");
    }


    @OnClick({R2.id.tv_ordinary, R2.id.tv_up, R2.id.tv_cancel_wheat})
    public void onViewClicked(View view) {
        int viewId = view.getId();
        if (viewId == R.id.tv_ordinary) {//一键移除
            String id = mWaitForAdapter.idToString();
            if (TextUtils.isEmpty(id)) {
                return;
            }
            MvpPre.deleteApply(id, roomId, 0);
        } else if (viewId == R.id.tv_up) {//一键上麦
            MvpPre.agreeApplyAll(roomId);
        } else if (viewId == R.id.tv_cancel_wheat) {//取消排麦
            MvpPre.deleteApply(selfId, roomId, 0);
        }
    }

}
