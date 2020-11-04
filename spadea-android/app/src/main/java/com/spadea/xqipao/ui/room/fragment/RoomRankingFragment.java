package com.spadea.xqipao.ui.room.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hyphenate.easeui.utils.view.GradeView;
import com.hyphenate.easeui.utils.view.JueView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.data.CharmModel;
import com.spadea.xqipao.ui.room.presenter.RoomRankingPresenter;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.room.adapter.RoomRankingAdapter;
import com.spadea.xqipao.ui.room.contacts.RoomRankingContacts;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;

public class RoomRankingFragment extends BaseFragment<RoomRankingPresenter> implements RoomRankingContacts.View {


    @BindView(R.id.tv_charm)
    TextView tvCharm;
    @BindView(R.id.view_charm)
    View viewCharm;
    @BindView(R.id.rl_charm)
    RelativeLayout rlCharm;
    @BindView(R.id.tv_wealth)
    TextView tvWealth;
    @BindView(R.id.view_wealth)
    View viewWealth;
    @BindView(R.id.rl_wealth)
    RelativeLayout rlWealth;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.view_day)
    View viewDay;
    @BindView(R.id.rl_day)
    RelativeLayout rlDay;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.view_week)
    View viewWeek;
    @BindView(R.id.rl_week)
    RelativeLayout rlWeek;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.view_total)
    View viewTotal;
    @BindView(R.id.rl_total)
    RelativeLayout rlTotal;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.riv)
    RoundedImageView riv;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.rl_iv)
    RelativeLayout rlIv;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.grade_view)
    GradeView gradeView;
    @BindView(R.id.jue_view)
    JueView jueView;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindColor(R.color.color_select)
    int select;
    @BindColor(R.color.color_un_select)
    int unSelect;
    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;

    private int type = 1;
    private int item = 1;
    private String roomId;


    private RoomRankingAdapter mRoomRankingAdapter;

    public static RoomRankingFragment newInstance(String roomId) {
        RoomRankingFragment roomRankingFragment = new RoomRankingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("RoomId", roomId);
        roomRankingFragment.setArguments(bundle);
        return roomRankingFragment;
    }

    @Override
    protected RoomRankingPresenter bindPresenter() {
        return new RoomRankingPresenter(this, mContext);
    }

    @Override
    protected void initData() {
        roomId = getArguments().getString("RoomId");
        if (type == 1) {
            MvpPre.getCharmList(roomId, item);
        } else {
            MvpPre.getWealthList(roomId, item);
        }
    }

    @Override
    protected void initView(View rootView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mRoomRankingAdapter = new RoomRankingAdapter());
    }


    @Override
    protected void initListener() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
            }
        });
        mRoomRankingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CharmModel.ListsBean item = mRoomRankingAdapter.getItem(position);
                ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", item.getUser_id()).navigation();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_roomranking;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @OnClick({R.id.rl_charm, R.id.rl_wealth, R.id.rl_day, R.id.rl_week, R.id.rl_total})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_charm:
                tvCharm.setTextColor(select);
                viewCharm.setBackgroundColor(select);
                tvWealth.setTextColor(unSelect);
                viewWealth.setBackgroundColor(unSelect);
                type = 1;
                break;
            case R.id.rl_wealth:
                tvCharm.setTextColor(unSelect);
                viewCharm.setBackgroundColor(unSelect);
                tvWealth.setTextColor(select);
                viewWealth.setBackgroundColor(select);
                type = 2;
                break;
            case R.id.rl_day:
                reset();
                tvDay.setTextColor(select);
                viewDay.setBackgroundColor(select);
                item = 1;
                break;
            case R.id.rl_week:
                reset();
                tvWeek.setTextColor(select);
                viewWeek.setBackgroundColor(select);
                item = 2;
                break;
            case R.id.rl_total:
                reset();
                tvTotal.setTextColor(select);
                viewTotal.setBackgroundColor(select);
                item = 3;
                break;
        }
        mRoomRankingAdapter.setType(type);
        initData();
    }


    private void reset() {
        tvDay.setTextColor(unSelect);
        viewDay.setBackgroundColor(unSelect);
        tvWeek.setTextColor(unSelect);
        viewWeek.setBackgroundColor(unSelect);
        tvTotal.setTextColor(unSelect);
        viewTotal.setBackgroundColor(unSelect);
    }


    @Override
    public void setData(List<CharmModel.ListsBean> data) {
        mRoomRankingAdapter.setNewData(data);
    }

    @Override
    public void setUserData(CharmModel.MyBean data) {
        tvUserName.setText(data.getNickname());
        ImageLoader.loadHead(mContext, riv, data.getHead_picture());
        tvNum.setText(type == 1 ? "魅力" + data.getNumber() : "财富" + data.getNumber());
        if (data.getRank() > 0) {
            tvDistance.setVisibility(View.INVISIBLE);
        } else {
            if ("0".equals(data.getDiff())) {
                tvDistance.setVisibility(View.INVISIBLE);
            } else {
                tvDistance.setVisibility(View.VISIBLE);
                tvDistance.setText("距离上榜还差" + data.getDiff());
            }
        }
        gradeView.setVisibility(View.VISIBLE);
        gradeView.setGrade(data.getRank_info().rank_id, data.getRank_info().rank_name);
        jueView.setVisibility(View.VISIBLE);
        jueView.setJue(data.getRank_info().getNobility_id(), data.getRank_info().getNobility_name());
    }

    @Override
    public void onComplete() {
        smartRefreshLayout.finishRefresh();
    }
}
