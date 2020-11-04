package com.spadea.xqipao.ui.live.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.data.WeekStarBean;
import com.spadea.xqipao.data.WeekStarModel;
import com.spadea.xqipao.ui.live.presenter.WeekStartPresenter;
import com.spadea.xqipao.utils.view.CommonEmptyView;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.live.adapter.WeekStarAdapter;
import com.spadea.xqipao.ui.live.contacts.WeekStartContacts;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 周星榜详情  1财富2魅力
 */
public class WeekStartFragment extends BaseFragment<WeekStartPresenter> implements WeekStartContacts.View {

    @BindView(R.id.roundedimageview)
    RoundedImageView roundedImageView;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_ranking_num)
    TextView tvRankingNum;
    @BindView(R.id.tv_disparity)
    TextView tvDisparity;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @BindView(R.id.riv_no1_head_no1)
    RoundedImageView rivHeadNo1;
    @BindView(R.id.tv_nickname_no1)
    TextView tvNickNameNo1;
    @BindView(R.id.tv_num_no1)
    TextView tvNumNo1;

    @BindView(R.id.riv_no1_head_no2)
    RoundedImageView rivHeadNo2;
    @BindView(R.id.tv_nickname_no2)
    TextView tvNickNameNo2;
    @BindView(R.id.tv_num_no2)
    TextView tvNumNo2;


    @BindView(R.id.riv_no1_head_no3)
    RoundedImageView rivHeadNo3;
    @BindView(R.id.tv_nickname_no3)
    TextView tvNickNameNo3;
    @BindView(R.id.tv_num_no3)
    TextView tvNumNo3;


    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.rl_buttom)
    RelativeLayout rlButtom;

    @BindView(R.id.iv_gift1)
    RoundedImageView ivGift1;
    @BindView(R.id.tv_gift_name1)
    TextView tvGiftName1;
    @BindView(R.id.iv_gift2)
    RoundedImageView ivGift2;
    @BindView(R.id.tv_gift_name2)
    TextView tvGiftName2;
    @BindView(R.id.rl_img_no1)
    RelativeLayout rlImgNo1;
    @BindView(R.id.rl_img_no2)
    RelativeLayout rlImgNo2;
    @BindView(R.id.rl_img_no3)
    RelativeLayout rlImgNo3;

    private WeekStarModel mWeekStarModel;
    private int type = 0;
    private WeekStarAdapter weekStarAdapter;

    public static Fragment newInstance(int type) {
        WeekStartFragment weekStartFragment = new WeekStartFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        weekStartFragment.setArguments(bundle);
        return weekStartFragment;
    }

    @Override
    protected WeekStartPresenter bindPresenter() {
        return new WeekStartPresenter(this, getContext());
    }

    @Override
    protected void initData() {
        MvpPre.getWeekStarList();
    }

    @Override
    protected void initView(View rootView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayout.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        type = getArguments().getInt("type", 0);
        recyclerView.setAdapter(weekStarAdapter = new WeekStarAdapter(type));
        CommonEmptyView commonEmptyView = new CommonEmptyView(getContext());
        weekStarAdapter.setEmptyView(commonEmptyView);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
            }
        });

        weekStarAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WeekStarBean item = weekStarAdapter.getItem(position);
                if (item.getType() != 1) {
                    ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", item.getUser_id()).navigation();
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_week_start;
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }


    @Override
    public void getWeekStarListSuccess(WeekStarModel weekStarModel) {
        if (weekStarModel != null) {
            mWeekStarModel = weekStarModel;
            WeekStarModel.GiftInfoBean gift_info = weekStarModel.getGift_info();
            if (gift_info != null) {
                tvGiftName1.setText("礼物名：" + gift_info.getGift_name_1());
                tvGiftName2.setText("礼物名：" + gift_info.getGift_name_2());
                ImageLoader.loadImage(mContext, ivGift1, gift_info.getGift_picture_1());
                ImageLoader.loadImage(mContext, ivGift2, gift_info.getGift_picture_2());
            }

            switch (type) {
                case 0:
                    List<WeekStarBean> charmList = new ArrayList<>();
                    List<WeekStarModel.GiftCharmBean.ListsBeanX> lists = weekStarModel.getGift_charm().getLists();
                    for (WeekStarModel.GiftCharmBean.ListsBeanX item : lists) {
                        WeekStarBean weekStarBean = new WeekStarBean();
                        weekStarBean.setType(0);
                        weekStarBean.setHead_picture(item.getHead_picture());
                        weekStarBean.setLevel(item.getLevel());
                        weekStarBean.setNickname(item.getNickname());
                        weekStarBean.setRank(item.getRank());
                        weekStarBean.setTotal_price(item.getTotal_price());
                        weekStarBean.setUser_id(item.getUser_id());
                        charmList.add(weekStarBean);
                    }
                    setUserData(weekStarModel.getGift_charm().getMy());
                    setListData(charmList);
                    break;
                case 1:
                    List<WeekStarBean> roomList = new ArrayList<>();
                    List<WeekStarModel.GiftRoomBean.ListsBeanXX> lists1 = weekStarModel.getGift_room().getLists();
                    for (WeekStarModel.GiftRoomBean.ListsBeanXX item : lists1) {
                        WeekStarBean weekStarBean = new WeekStarBean();
                        weekStarBean.setType(1);
                        weekStarBean.setHead_picture(item.getCover_picture());
                        weekStarBean.setNickname(item.getRoom_name());
                        weekStarBean.setRank(item.getRank());
                        weekStarBean.setTotal_price(item.getTotal_price());
                        roomList.add(weekStarBean);
                    }
                    setUserData(null);
                    setListData(roomList);
                    break;
                case 2:
                    List<WeekStarBean> richList = new ArrayList<>();
                    List<WeekStarModel.GiftRichBean.ListsBean> lists2 = weekStarModel.getGift_rich().getLists();
                    for (WeekStarModel.GiftRichBean.ListsBean item : lists2) {
                        WeekStarBean weekStarBean = new WeekStarBean();
                        weekStarBean.setType(0);
                        weekStarBean.setHead_picture(item.getHead_picture());
                        weekStarBean.setLevel(item.getLevel());
                        weekStarBean.setNickname(item.getNickname());
                        weekStarBean.setRank(item.getRank());
                        weekStarBean.setTotal_price(item.getTotal_price());
                        weekStarBean.setUser_id(item.getUser_id());
                        richList.add(weekStarBean);
                    }
                    setUserData(weekStarModel.getGift_rich().getMy());
                    setListData(richList);
                    break;
            }

        }
    }

    @Override
    public void networkCompletion() {
        smartRefreshLayout.finishRefresh();
    }

    private void setUserData(WeekStarModel.GiftRichBean.MyBean myBean) {
        if (myBean != null) {
            rlButtom.setVisibility(View.VISIBLE);
            ImageLoader.loadHead(getContext(), roundedImageView, myBean.getHead_picture());
            tvUserName.setText(myBean.getNickname());
            tvRankingNum.setText("当前排名" + myBean.getRank());
            if (myBean.getRank() == -1) {
                tvRankingNum.setText("暂时未上榜");
                tvDisparity.setText("距离上榜还差" + myBean.getDiff());
            } else {
                if (myBean.getRank() == 1) {
                    tvDisparity.setText("当前位于榜首");
                } else {
                    if (TextUtils.isEmpty(myBean.getDiff())) {
                        tvDisparity.setVisibility(View.GONE);
                    } else {
                        tvDisparity.setText("距离上一名还差" + myBean.getDiff());
                    }
                }
                tvRankingNum.setText("目前排名" + myBean.getRank() + "名");
            }
        } else {
            rlButtom.setVisibility(View.GONE);
        }
    }


    private void setListData(List<WeekStarBean> listData) {
        if (listData != null && listData.size() != 0) {
            if (listData.size() == 1) {
                setNo1Data(listData.get(0));
                setNo2Data(null);
                setNo3Data(null);
                weekStarAdapter.setNewData(new ArrayList<>());
            } else if (listData.size() == 2) {
                setNo1Data(listData.get(0));
                setNo2Data(listData.get(1));
                setNo3Data(null);
                weekStarAdapter.setNewData(new ArrayList<>());
            } else {
                setNo1Data(listData.get(0));
                setNo2Data(listData.get(1));
                setNo3Data(listData.get(2));
                List<WeekStarBean> listsBeans = listData;
                weekStarAdapter.setNewData(listsBeans.subList(3, listData.size()));
            }
        } else {
            setNo2Data(null);
            setNo3Data(null);
            setNo3Data(null);
            weekStarAdapter.setNewData(new ArrayList<>());
        }
    }

    private void setNo1Data(WeekStarBean weekStarBean) {
        if (weekStarBean != null) {
            ImageLoader.loadImage(mContext, rivHeadNo1, weekStarBean.getHead_picture());
            tvNickNameNo1.setText(weekStarBean.getNickname());
            tvNumNo1.setText(weekStarBean.getTotal_price());
        } else {
            rivHeadNo1.setImageResource(R.drawable.default_image);
            tvNickNameNo1.setText("虚位以待");
            tvNumNo1.setText("");
        }
        rlImgNo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weekStarBean != null) {
                    if (weekStarBean.getType() != 1) {
                        ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", weekStarBean.getUser_id()).navigation();
                    }
                }
            }
        });
    }


    private void setNo2Data(WeekStarBean weekStarBean) {
        if (weekStarBean != null) {
            ImageLoader.loadImage(mContext, rivHeadNo2, weekStarBean.getHead_picture());
            tvNickNameNo2.setText(weekStarBean.getNickname());
            tvNumNo2.setText(weekStarBean.getTotal_price());
        } else {
            rivHeadNo1.setImageResource(R.drawable.default_image);
            tvNickNameNo2.setText("虚位以待");
            tvNumNo1.setText("");
        }
        rlImgNo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weekStarBean != null) {
                    if (weekStarBean.getType() != 1) {
                        ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", weekStarBean.getUser_id()).navigation();
                    }
                }
            }
        });
    }


    private void setNo3Data(WeekStarBean weekStarBean) {
        if (weekStarBean != null) {
            ImageLoader.loadImage(mContext, rivHeadNo3, weekStarBean.getHead_picture());
            tvNickNameNo3.setText(weekStarBean.getNickname());
            tvNumNo3.setText(weekStarBean.getTotal_price());
        } else {
            rivHeadNo3.setImageResource(R.drawable.default_image);
            tvNickNameNo3.setText("虚位以待");
            tvNumNo3.setText("");
        }
        rlImgNo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weekStarBean != null) {
                    if (weekStarBean.getType() != 1) {
                        ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", weekStarBean.getUser_id()).navigation();
                    }
                }
            }
        });
    }


}
