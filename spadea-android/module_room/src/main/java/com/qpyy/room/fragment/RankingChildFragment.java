package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.RankingCharmListAdapter;
import com.qpyy.room.adapter.RankingWealthListAdapter;
import com.qpyy.room.bean.CharmRankingResp;
import com.qpyy.room.bean.WealthRankingResp;
import com.qpyy.room.contacts.DataListContacts;
import com.qpyy.room.event.RefreshRankListEvent;
import com.qpyy.room.presenter.DataListPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.fragment
 * 创建人 黄强
 * 创建时间 2020/8/13 19:58
 * 描述 describe
 */
public class RankingChildFragment extends BaseMvpFragment<DataListPresenter> implements DataListContacts.View {

    private static final String TAG = "RankingChildFragment";

    @BindView(R2.id.room_rank_top2_headIcon)
    RoundedImageView roomRankTop2HeadIcon;
    @BindView(R2.id.room_top2_name)
    TextView roomTop2Name;
    @BindView(R2.id.room_top2_label)
    ImageView roomTop2Label;
    @BindView(R2.id.room_top2_grade)
    ImageView roomTop2Grade;
    @BindView(R2.id.room_head_top2_label)
    TextView roomHeadTop2Label;
    @BindView(R2.id.room_rank_top1_headIcon)
    RoundedImageView roomRankTop1HeadIcon;
    @BindView(R2.id.room_top1_name)
    TextView roomTop1Name;
    @BindView(R2.id.room_top1_label)
    ImageView roomTop1Label;
    @BindView(R2.id.room_top1_grade)
    ImageView roomTop1Grade;
    @BindView(R2.id.room_head_top1_label)
    TextView roomHeadTop1Label;
    @BindView(R2.id.room_rank_top3_headIcon)
    RoundedImageView roomRankTop3HeadIcon;
    @BindView(R2.id.room_top3_name)
    TextView roomTop3Name;
    @BindView(R2.id.room_top3_label)
    ImageView roomTop3Label;
    @BindView(R2.id.room_top3_grade)
    ImageView roomTop3Grade;
    @BindView(R2.id.room_head_top3_label)
    TextView roomHeadTop3Label;
    @BindView(R2.id.rankRecycleView)
    RecyclerView rankRecycleView;

    private String roomId;
    private int dataType;
    private int rankType = 1;
    private RankingCharmListAdapter cAdapter;//魅力适配器
    private RankingWealthListAdapter wAdapter;//财富适配器

    /**
     * newInstance 初始化fragment
     *
     * @return
     */
    public static RankingChildFragment newInstance(String roomId, int dataType, int rankType) {
        RankingChildFragment rankingChildFragment = new RankingChildFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomId", roomId);
        bundle.putInt("dataType", dataType);
        bundle.putInt("rankType", rankType);
        rankingChildFragment.setArguments(bundle);
        return rankingChildFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected DataListPresenter bindPresenter() {
        return new DataListPresenter(this, getActivity());
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        roomId = getArguments().getString("roomId");
        dataType = getArguments().getInt("dataType");
        rankType = getArguments().getInt("rankType");
    }

    @Override
    protected void initData() {
        if (rankType == 1) {
            MvpPre.getCharmListInfo(roomId, dataType);
        } else {
            MvpPre.getWealthListInfo(roomId, dataType);
        }
        if (rankType == 1) {
            cAdapter = new RankingCharmListAdapter();
            rankRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            rankRecycleView.setAdapter(cAdapter);
        } else {
            wAdapter = new RankingWealthListAdapter();
            rankRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            rankRecycleView.setAdapter(wAdapter);
        }
    }

    @Override
    protected void initView() {

    }

    /**
     * 初始化监听
     */
    @Override
    protected void initListener() {
        super.initListener();
        if (rankType == 1) {
            cAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    if (view.getId() == R.id.room_item_head) {
                        CharmRankingResp.ListsBean item = cAdapter.getItem(position);
                        ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", item.getUser_id()).navigation();
                    }
                }
            });
        } else {
            wAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    if (view.getId() == R.id.room_item_head) {
                        WealthRankingResp.ListsBean item = wAdapter.getItem(position);
                        ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", item.getUser_id()).navigation();
                    }
                }
            });
        }
    }


    @Override
    protected int getLayoutId() {
        Log.d(TAG, "(Start)启动了===========================RankingChildFragment");
        return R.layout.room_ranking_child;
    }

    @Override
    public void setNo1(CharmRankingResp.ListsBean listsBean) {//魅力排行榜一数据设值
        if (TextUtils.isEmpty(listsBean.getUser_id())) {
            ImageUtils.loadImageView(listsBean.getHead_picture(), roomRankTop1HeadIcon);
        } else {
            ImageUtils.loadHeadCC(listsBean.getHead_picture(), roomRankTop1HeadIcon);
        }
        setSexBg(roomHeadTop1Label, listsBean.getSex());
        roomTop1Name.setText(listsBean.getNickname());
        roomHeadTop1Label.setVisibility(TextUtils.isEmpty(listsBean.getNumber()) ? View.GONE : View.VISIBLE);
        roomHeadTop1Label.setText(listsBean.getNumber());
        roomTop1Label.setVisibility(TextUtils.isEmpty(listsBean.getLevel_icon()) ? View.GONE : View.VISIBLE);
        ImageUtils.loadImageView(listsBean.getLevel_icon(), roomTop1Label);
        roomTop1Grade.setVisibility(TextUtils.isEmpty(listsBean.getNobility_icon()) ? View.GONE : View.VISIBLE);
        ImageUtils.loadImageView(listsBean.getNobility_icon(), roomTop1Grade);
        roomRankTop1HeadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", listsBean.getUser_id()).navigation();
            }
        });
    }

    @Override
    public void setNo1(WealthRankingResp.ListsBean listsBean) {//财富排行榜一数据设值
        if (TextUtils.isEmpty(listsBean.getUser_id())) {
            ImageUtils.loadImageView(listsBean.getHead_picture(), roomRankTop1HeadIcon);
        } else {
            ImageUtils.loadHeadCC(listsBean.getHead_picture(), roomRankTop1HeadIcon);
        }
        roomTop1Name.setText(listsBean.getNickname());
        roomHeadTop1Label.setVisibility(TextUtils.isEmpty(listsBean.getNumber()) ? View.GONE : View.VISIBLE);
        roomHeadTop1Label.setText(listsBean.getNumber());
        roomTop1Label.setVisibility(TextUtils.isEmpty(listsBean.getLevel_icon()) ? View.GONE : View.VISIBLE);
        ImageUtils.loadImageView(listsBean.getLevel_icon(), roomTop1Label);
        roomTop1Grade.setVisibility(TextUtils.isEmpty(listsBean.getNobility_icon()) ? View.GONE : View.VISIBLE);
        ImageUtils.loadImageView(listsBean.getNobility_icon(), roomTop1Grade);
        roomRankTop1HeadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", listsBean.getUser_id()).navigation();
            }
        });
    }

    @Override
    public void setNo2(CharmRankingResp.ListsBean listsBean) {//魅力榜二
        if (TextUtils.isEmpty(listsBean.getUser_id())) {
            ImageUtils.loadImageView(listsBean.getHead_picture(), roomRankTop2HeadIcon);
        } else {
            ImageUtils.loadHeadCC(listsBean.getHead_picture(), roomRankTop2HeadIcon);
        }
        setSexBg(roomHeadTop2Label, listsBean.getSex());
        roomTop2Name.setText(listsBean.getNickname());
        roomHeadTop2Label.setVisibility(TextUtils.isEmpty(listsBean.getNumber()) ? View.GONE : View.VISIBLE);
        roomHeadTop2Label.setText(listsBean.getNumber());
        roomTop2Label.setVisibility(TextUtils.isEmpty(listsBean.getLevel_icon()) ? View.GONE : View.VISIBLE);
        ImageUtils.loadImageView(listsBean.getLevel_icon(), roomTop2Label);
        roomTop2Grade.setVisibility(TextUtils.isEmpty(listsBean.getNobility_icon()) ? View.GONE : View.VISIBLE);
        ImageUtils.loadImageView(listsBean.getNobility_icon(), roomTop2Grade);
        roomRankTop2HeadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", listsBean.getUser_id()).navigation();
            }
        });
    }

    @Override
    public void setNo2(WealthRankingResp.ListsBean listsBean) {//财富榜二
        if (TextUtils.isEmpty(listsBean.getUser_id())) {
            ImageUtils.loadImageView(listsBean.getHead_picture(), roomRankTop2HeadIcon);
        } else {
            ImageUtils.loadHeadCC(listsBean.getHead_picture(), roomRankTop2HeadIcon);
        }
        roomTop2Name.setText(listsBean.getNickname());
        roomHeadTop2Label.setVisibility(TextUtils.isEmpty(listsBean.getNumber()) ? View.GONE : View.VISIBLE);
        roomHeadTop2Label.setText(listsBean.getNumber());
        roomTop2Label.setVisibility(TextUtils.isEmpty(listsBean.getLevel_icon()) ? View.GONE : View.VISIBLE);
        ImageUtils.loadImageView(listsBean.getLevel_icon(), roomTop2Label);
        roomTop2Grade.setVisibility(TextUtils.isEmpty(listsBean.getNobility_icon()) ? View.GONE : View.VISIBLE);
        ImageUtils.loadImageView(listsBean.getNobility_icon(), roomTop2Grade);
        roomRankTop2HeadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", listsBean.getUser_id()).navigation();
            }
        });
    }

    @Override
    public void setNo3(CharmRankingResp.ListsBean listsBean) {//魅力榜三
        if (TextUtils.isEmpty(listsBean.getUser_id())) {
            ImageUtils.loadImageView(listsBean.getHead_picture(), roomRankTop3HeadIcon);
        } else {
            ImageUtils.loadHeadCC(listsBean.getHead_picture(), roomRankTop3HeadIcon);
        }
        setSexBg(roomHeadTop3Label, listsBean.getSex());
        roomTop3Name.setText(listsBean.getNickname());
        roomHeadTop3Label.setVisibility(TextUtils.isEmpty(listsBean.getNumber()) ? View.GONE : View.VISIBLE);
        roomHeadTop3Label.setText(listsBean.getNumber());
        roomTop3Label.setVisibility(TextUtils.isEmpty(listsBean.getLevel_icon()) ? View.GONE : View.VISIBLE);
        ImageUtils.loadImageView(listsBean.getLevel_icon(), roomTop3Label);
        roomTop3Grade.setVisibility(TextUtils.isEmpty(listsBean.getNobility_icon()) ? View.GONE : View.VISIBLE);
        ImageUtils.loadImageView(listsBean.getNobility_icon(), roomTop3Grade);
        roomRankTop3HeadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", listsBean.getUser_id()).navigation();
            }
        });
    }

    @Override
    public void setNo3(WealthRankingResp.ListsBean listsBean) {//财富榜三
        if (TextUtils.isEmpty(listsBean.getUser_id())) {
            ImageUtils.loadImageView(listsBean.getHead_picture(), roomRankTop3HeadIcon);
        } else {
            ImageUtils.loadHeadCC(listsBean.getHead_picture(), roomRankTop3HeadIcon);
        }
        roomTop3Name.setText(listsBean.getNickname());
        roomHeadTop3Label.setVisibility(TextUtils.isEmpty(listsBean.getNumber()) ? View.GONE : View.VISIBLE);
        roomHeadTop3Label.setText(listsBean.getNumber());
        roomTop3Label.setVisibility(TextUtils.isEmpty(listsBean.getLevel_icon()) ? View.GONE : View.VISIBLE);
        ImageUtils.loadImageView(listsBean.getLevel_icon(), roomTop3Label);
        roomTop3Grade.setVisibility(TextUtils.isEmpty(listsBean.getNobility_icon()) ? View.GONE : View.VISIBLE);
        ImageUtils.loadImageView(listsBean.getNobility_icon(), roomTop3Grade);
        roomRankTop3HeadIcon.setOnClickListener(v -> ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", listsBean.getUser_id()).navigation());
    }

    @Override
    public void setCharmView(List<CharmRankingResp.ListsBean> listsBeans) {
        cAdapter.setNewData(listsBeans);
    }

    @Override
    public void setWealthView(List<WealthRankingResp.ListsBean> listsBeans) {
        wAdapter.setNewData(listsBeans);
    }

    /**
     * 刷新
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RefreshRankListEvent refreshRankListEvent) {
        if (refreshRankListEvent.getRankType() == rankType && refreshRankListEvent.getType() == dataType) {
            if (rankType == 1) {
                MvpPre.getCharmListInfo(roomId, dataType);
            } else {
                MvpPre.getWealthListInfo(roomId, dataType);
            }
        }
    }

    /**
     * 设置头像男神女神热度背景
     */
    private void setSexBg(View view, String sex) {
        view.setPadding(ConvertUtils.dp2px(22), ConvertUtils.dp2px(1.5f), ConvertUtils.dp2px(6), 0);
        view.setBackgroundResource(UserBean.MALE.equals(sex) ? R.mipmap.room_index_bg_ranking_charm_gg : R.mipmap.room_index_bg_ranking_charm_mm);
    }

}
