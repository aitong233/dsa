package com.qpyy.module.index.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lihang.ShadowLayout;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module.index.R;
import com.qpyy.module.index.R2;
import com.qpyy.module.index.bean.CharmRankingResp;
import com.qpyy.module.index.contacts.RankingListContacts;
import com.qpyy.module.index.presenter.RankingListPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.fragment
 * 创建人 王欧
 * 创建时间 2020/6/28 2:24 PM
 * 描述 describe
 */
public class RankingListFragment extends BaseMvpFragment<RankingListPresenter> implements RankingListContacts.View {
    @BindView(R2.id.view_top2)
    View mViewTop2;
    @BindView(R2.id.view_top1)
    View mViewTop1;
    @BindView(R2.id.view_top3)
    View mViewTop3;
    @BindView(R2.id.riv_2)
    RoundedImageView mRiv2;
    @BindView(R2.id.riv_1)
    RoundedImageView mRiv1;
    @BindView(R2.id.riv_3)
    RoundedImageView mRiv3;
    @BindView(R2.id.tv_name2)
    TextView mTvName2;
    @BindView(R2.id.tv_name1)
    TextView mTvName1;
    @BindView(R2.id.tv_name3)
    TextView mTvName3;
    @BindView(R2.id.tv_charm2)
    TextView mTvCharm2;
    @BindView(R2.id.tv_charm1)
    TextView mTvCharm1;
    @BindView(R2.id.tv_charm3)
    TextView mTvCharm3;
    @BindView(R2.id.iv_vip2)
    ImageView mIvVip2;
    @BindView(R2.id.iv_level2)
    ImageView mIvLevel2;
    @BindView(R2.id.ll_vip2)
    LinearLayout mLlVip2;
    @BindView(R2.id.iv_vip1)
    ImageView mIvVip1;
    @BindView(R2.id.iv_level1)
    ImageView mIvLevel1;
    @BindView(R2.id.ll_vip1)
    LinearLayout mLlVip1;
    @BindView(R2.id.iv_vip3)
    ImageView mIvVip3;
    @BindView(R2.id.iv_level3)
    ImageView mIvLevel3;
    @BindView(R2.id.ll_vip3)
    LinearLayout mLlVip3;
    @BindView(R2.id.recycle_view)
    RecyclerView mRecycleView;
    @BindView(R2.id.riv)
    RoundedImageView mRiv;
    @BindView(R2.id.tv_name)
    TextView mTvName;
    @BindView(R2.id.tv_rank_state)
    TextView mTvRankState;
    @BindView(R2.id.tv_rank_no)
    TextView mTvRankNo;
    @BindView(R2.id.tv_charm)
    TextView mTvCharm;
    @BindView(R2.id.sl_my)
    ShadowLayout mSlMy;
    @BindView(R2.id.iv_icon_charm)
    ImageView mIvIconCharm;
    private String roomId;
    private int type;
    private int dateType;

    public static final int TYPE_DAY = 1;//日榜
    public static final int TYPE_WEEK = 2;//周榜
    public static final int TYPE_MONTH = 3;//月榜
    public static final int TYPE_LAST_WEEK = 4;//上周榜
    public static final int TYPE_TOTAL = 5;//总榜
    private BaseQuickAdapter<CharmRankingResp.ListsBean, BaseViewHolder> mAdapter;

    public static RankingListFragment newInstance(String roomId, int type, int dateType) {

        Bundle args = new Bundle();
        args.putString("roomId", roomId);
        args.putInt("type", type);
        args.putInt("dateType", dateType);
        RankingListFragment fragment = new RankingListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RankingListPresenter bindPresenter() {
        return new RankingListPresenter(this, getActivity());
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        roomId = arguments.getString("roomId");
        type = arguments.getInt("type", RankingFragment.TYPE_CHARM);
        dateType = arguments.getInt("dateType", TYPE_DAY);
    }

    @Override
    protected void initData() {
        if (type == RankingFragment.TYPE_CHARM) {
            MvpPre.getCharmList(roomId, dateType);
        } else if (type == RankingFragment.TYPE_WEALTH) {
            MvpPre.getWealthList(roomId, dateType);
        }
    }

    @Override
    protected void initView() {
        if (type == RankingFragment.TYPE_CHARM) {
            mSlMy.setmShadowColor(Color.parseColor("#618F6FFF"));
        } else if (type == RankingFragment.TYPE_WEALTH) {
            mSlMy.setmShadowColor(Color.parseColor("#70F7B500"));
        } else if (type == RankingFragment.TYPE_WEEK_STAR) {
            mSlMy.setmShadowColor(Color.parseColor("#7AFE969D"));
        }
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new BaseQuickAdapter<CharmRankingResp.ListsBean, BaseViewHolder>(R.layout.index_rv_item_ranking_list) {

            @Override
            protected void convert(BaseViewHolder helper, CharmRankingResp.ListsBean item) {
                helper.setText(R.id.tv_charm, item.getNumber());
                helper.setText(R.id.tv_name, item.getNickname());
                helper.setText(R.id.tv_no, String.valueOf(item.getRank()));
                helper.setGone(R.id.iv_level, !TextUtils.isEmpty(item.getLevel_icon()));
                helper.setGone(R.id.iv_vip, !TextUtils.isEmpty(item.getNobility_icon()));
                ImageUtils.loadImageView(item.getLevel_icon(), helper.getView(R.id.iv_level));
                ImageUtils.loadImageView(item.getNobility_icon(), helper.getView(R.id.iv_vip));
                ImageUtils.loadHeadCC(item.getHead_picture(), helper.getView(R.id.riv_avatar));
                setCharmBg(helper.getView(R.id.tv_charm), item.getSex());
                setHeadBorder(helper.getView(R.id.riv_avatar), item.getSex());
                if (item.getRank() % 2 == 0) {
                    helper.itemView.setBackgroundResource(R.mipmap.index_bg_ranking_list_item);
                } else {
                    helper.itemView.setBackgroundColor(Color.WHITE);
                }
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (type == RankingFragment.TYPE_WEALTH) {
                            return;
                        }
                        ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", item.getUser_id()).navigation();
                    }
                });
            }
        };
        mRecycleView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecycleView);
        mAdapter.setEmptyView(R.layout.index_view_empty_ranking_list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.index_fragment_ranking_list;
    }

    @Override
    public void setMyInfo(CharmRankingResp.MyBean myInfo) {
        if (myInfo != null) {
            String color = "#FFFF6BA4";
            if (type == RankingFragment.TYPE_WEALTH) {
                color = "#FFF9B721";
            } else if (type == RankingFragment.TYPE_CHARM) {
                color = "#FF6765FF";
            }
            if (type == RankingFragment.TYPE_CHARM) {
                mIvIconCharm.setBackgroundResource(UserBean.MALE.equals(myInfo.getSex()) ? R.mipmap.index_ic_heart_gg : R.mipmap.index_ic_heart_mm);
            } else {
                mIvIconCharm.setBackgroundResource(R.mipmap.index_ic_heart_wealth);
            }
            ImageUtils.loadHeadCC(myInfo.getHead_picture(), mRiv);
            mTvName.setText(myInfo.getNickname());
            mTvCharm.setText(myInfo.getNumber());
            if (myInfo.getRank() == -1) {
                mTvRankState.setText("暂未上榜");
                mTvRankNo.setText(new SpanUtils().append("距上榜差 ").append(myInfo.getDiff()+"").setBold().setForegroundColor(Color.parseColor(color)).setFontSize(12, true).create());
            } else {
                if (myInfo.getRank() == 1) {
                    mTvRankState.setText("第1名");
                    mTvRankNo.setText("继续加油哦~");
                } else {
                    mTvRankState.setText("已上榜");
                    mTvRankNo.setText(new SpanUtils().append("距上一名差 ").append(myInfo.getDiff()+"").setBold().setForegroundColor(Color.parseColor(color)).setFontSize(12, true).create());
                }
            }
            mRiv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", myInfo.getUser_id()).navigation();
                }
            });
        }
    }

    @Override
    public void setNo1(CharmRankingResp.ListsBean listsBean) {
        setCharmBg(mTvCharm1, listsBean.getSex());
        mTvName1.setText(listsBean.getNickname());
        mTvCharm1.setText(listsBean.getNumber());
        mIvVip1.setVisibility(TextUtils.isEmpty(listsBean.getNobility_icon()) ? View.GONE : View.VISIBLE);
        ImageUtils.loadHeadCC(listsBean.getHead_picture(), mRiv1);
        ImageUtils.loadImageView(listsBean.getNobility_icon(), mIvVip1);
        ImageUtils.loadImageView(listsBean.getLevel_icon(), mIvLevel1);
        mRiv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == RankingFragment.TYPE_WEALTH) {
                    return;
                }
                ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", listsBean.getUser_id()).navigation();
            }
        });
    }

    private void setCharmBg(View view, String sex) {
        if (type != RankingFragment.TYPE_CHARM) {
            return;
        }
        view.setPadding(ConvertUtils.dp2px(22), ConvertUtils.dp2px(1.5f), ConvertUtils.dp2px(6), 0);
        view.setBackgroundResource(UserBean.MALE.equals(sex) ? R.drawable.index_bg_ranking_charm_gg : R.drawable.index_bg_ranking_charm_mm);
    }

    private void setHeadBorder(RoundedImageView imageView, String sex) {
        imageView.setBorderColor(UserBean.MALE.equals(sex) ? Color.parseColor("#FF6765FF") : Color.parseColor("#FFFF8890"));
    }

    @Override
    public void setNo2(CharmRankingResp.ListsBean listsBean) {
        setCharmBg(mTvCharm2, listsBean.getSex());
        mTvName2.setText(listsBean.getNickname());
        mTvCharm2.setText(listsBean.getNumber());
        mIvVip2.setVisibility(TextUtils.isEmpty(listsBean.getNobility_icon()) ? View.GONE : View.VISIBLE);
        ImageUtils.loadHeadCC(listsBean.getHead_picture(), mRiv2);
        ImageUtils.loadImageView(listsBean.getNobility_icon(), mIvVip2);
        ImageUtils.loadImageView(listsBean.getLevel_icon(), mIvLevel2);
        mRiv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == RankingFragment.TYPE_WEALTH) {
                    return;
                }
                ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", listsBean.getUser_id()).navigation();
            }
        });
    }

    @Override
    public void setNo3(CharmRankingResp.ListsBean listsBean) {
        setCharmBg(mTvCharm3, listsBean.getSex());
        mTvName3.setText(listsBean.getNickname());
        mTvCharm3.setText(listsBean.getNumber());
        mIvVip3.setVisibility(TextUtils.isEmpty(listsBean.getNobility_icon()) ? View.GONE : View.VISIBLE);
        ImageUtils.loadHeadCC(listsBean.getHead_picture(), mRiv3);
        ImageUtils.loadImageView(listsBean.getNobility_icon(), mIvVip3);
        ImageUtils.loadImageView(listsBean.getLevel_icon(), mIvLevel3);
        mRiv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == RankingFragment.TYPE_WEALTH) {
                    return;
                }
                ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", listsBean.getUser_id()).navigation();
            }
        });
    }

    @Override
    public void setList(List<CharmRankingResp.ListsBean> list) {
        mAdapter.setNewData(list);
    }
}
