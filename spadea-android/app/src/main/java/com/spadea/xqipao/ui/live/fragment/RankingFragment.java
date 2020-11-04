package com.spadea.xqipao.ui.live.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.xqipao.data.CharmModel;
import com.spadea.xqipao.ui.live.presenter.RankingPresenter;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.live.adapter.CharmAdapter;
import com.spadea.xqipao.ui.live.contacts.RankingContacts;
import com.spadea.yuyin.R;

import com.spadea.yuyin.util.ImageLoader;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * 日榜  周榜  总榜
 */
public class RankingFragment extends BaseFragment<RankingPresenter> implements RankingContacts.View {


    @BindView(R.id.riv_no2)
    RoundedImageView rivNo2;
    @BindView(R.id.riv_no1)
    RoundedImageView rivNo1;
    @BindView(R.id.riv_no3)
    RoundedImageView rivNo3;
    @BindView(R.id.tv_no2_nickname)
    TextView tvNo2Nickname;
    @BindView(R.id.tv_no2_chram_num)
    TextView tvNo2ChramNum;
    @BindView(R.id.tv_no1_nickname)
    TextView tvNo1Nickname;
    @BindView(R.id.tv_no1_charm_num)
    TextView tvNo1CharmNum;
    @BindView(R.id.tv_no3_nickname)
    TextView tvNo3Nickname;
    @BindView(R.id.tv_no3_charm_num)
    TextView tvNo3CharmNum;
    @BindView(R.id.rl_ranking)
    RelativeLayout rlRanking;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rl_ranking_list)
    RelativeLayout rlRankingList;
    @BindView(R.id.roundedimageview)
    RoundedImageView roundedimageview;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_no)
    TextView tvNo;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.rl_buttom)
    RelativeLayout rlButtom;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;


    private int type;
    private int id;
    private String roomId;
    private CharmAdapter charmAdapter;
    private CommonEmptyView commonEmptyView;

    public static Fragment newInstance(String roomId, int type, int id) {
        RankingFragment rankingFragment = new RankingFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putInt("id", id);
        bundle.putString("roomId", roomId);
        rankingFragment.setArguments(bundle);
        return rankingFragment;
    }

    @Override
    protected RankingPresenter bindPresenter() {
        return new RankingPresenter(this, getContext());
    }

    @Override
    protected void initData() {
        if (type == 0) {
            MvpPre.getCharmList(roomId, id);
        } else {
            MvpPre.getWealthList(roomId, id);
        }
    }

    @Override
    protected void initView(View rootView) {
        type = getArguments().getInt("type");
        id = getArguments().getInt("id");
        roomId = getArguments().getString("roomId");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(charmAdapter = new CharmAdapter(type));
        commonEmptyView = new CommonEmptyView(getContext());
        charmAdapter.setEmptyView(commonEmptyView);
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
            }
        });
        charmAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CharmModel.ListsBean item = charmAdapter.getItem(position);
                ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", item.getUser_id()).navigation();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ranking;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @Override
    public void setNo1(CharmModel.ListsBean data) {
        ImageLoader.loadHead(getContext(), rivNo1, data.getHead_picture());
        tvNo1Nickname.setText(data.getNickname());
        if (type == 0) {
            tvNo1CharmNum.setText("魅力值 " + data.getNumber());
        } else {
            tvNo1CharmNum.setText("财富值 " + data.getNumber());
        }
        rivNo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", data.getUser_id()).navigation();
            }
        });
    }

    @Override
    public void setNo2(CharmModel.ListsBean data) {
        ImageLoader.loadHead(getContext(), rivNo2, data.getHead_picture());
        tvNo2Nickname.setText(data.getNickname());
        if (type == 0) {
            tvNo2ChramNum.setText("魅力值 " + data.getNumber());
        } else {
            tvNo2ChramNum.setText("财富值 " + data.getNumber());
        }
        rivNo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", data.getUser_id()).navigation();
            }
        });
    }

    @Override
    public void setNo3(CharmModel.ListsBean data) {
        ImageLoader.loadHead(getContext(), rivNo3, data.getHead_picture());
        tvNo3Nickname.setText(data.getNickname());
        if (type == 0) {
            tvNo3CharmNum.setText("魅力值 " + data.getNumber());
        } else {
            tvNo3CharmNum.setText("财富值 " + data.getNumber());
        }
        rivNo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", data.getUser_id()).navigation();
            }
        });
    }

    @Override
    public void setListData(List<CharmModel.ListsBean> list) {
        if (charmAdapter != null) {
            charmAdapter.setNewData(list);
        }
    }

    @Override
    public void setUserData(CharmModel.MyBean myBean) {
        if (myBean == null) {
            return;
        }
        ImageLoader.loadHead(getContext(), roundedimageview, myBean.getHead_picture());
        tvUserName.setText(myBean.getNickname());
        tvNo.setText("当前排名" + myBean.getRank());
        if (myBean.getRank() == -1) {
            tvNo.setText("暂时未上榜");
            tvDistance.setText("距离上榜还差" + myBean.getDiff());
        } else {
            if (myBean.getRank() == 1) {
                tvDistance.setText("当前位于榜首");
            } else {
                if (TextUtils.isEmpty(myBean.getDiff())) {
                    tvDistance.setVisibility(View.GONE);
                } else {
                    tvDistance.setText("距离上一名还差" + myBean.getDiff());
                }
            }
            tvNo.setText("目前排名" + myBean.getRank() + "名");
        }

    }

    @Override
    public void networkCompletion() {
        srl.finishRefresh();
    }

}
