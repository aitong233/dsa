package com.qpyy.module.index.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
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
import com.qpyy.module.index.bean.WeekStarResp;
import com.qpyy.module.index.contacts.WeekStarListContacts;
import com.qpyy.module.index.presenter.WeekStarListPresenter;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.fragment
 * 创建人 王欧
 * 创建时间 2020/6/28 2:24 PM
 * 描述 describe
 */
public class WeekStarListFragment extends BaseMvpFragment<WeekStarListPresenter> implements WeekStarListContacts.View {
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
    @BindView(R2.id.iv_top)
    ImageView mIvTop;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.iv_gift1)
    ImageView mIvGift1;
    @BindView(R2.id.iv_gift2)
    ImageView mIvGift2;

    private String roomId;
    private int type;

    public static final int TYPE_CHARM = 1;//魅力
    public static final int TYPE_ROOM = 2;//房间
    public static final int TYPE_CONTRIBUTE = 3;//贡献

    public static WeekStarListFragment newInstance(String roomId, int type) {

        Bundle args = new Bundle();
        args.putString("roomId", roomId);
        args.putInt("type", type);
        WeekStarListFragment fragment = new WeekStarListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected WeekStarListPresenter bindPresenter() {
        return new WeekStarListPresenter(this, getActivity());
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        roomId = arguments.getString("roomId");
        type = arguments.getInt("type", TYPE_CHARM);
    }

    @Override
    protected void initData() {
        MvpPre.getList(roomId, type);
    }

    @Override
    protected void initView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void setHeadBorder(RoundedImageView imageView, String sex) {
        imageView.setBorderColor(UserBean.MALE.equals(sex) ? Color.parseColor("#FF6765FF") : Color.parseColor("#FFFF8890"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.index_fragment_week_star_list;
    }

    @Override
    public void setGifts(WeekStarResp.GiftInfoBean giftInfo) {
        ImageUtils.loadImageView(giftInfo.getGift_picture_1(), mIvGift1);
        ImageUtils.loadImageView(giftInfo.getGift_picture_2(), mIvGift2);
    }

    @Override
    public void setMyInfo(WeekStarResp.GiftRichBean.MyBean myInfo) {
        if (myInfo == null) {
            mSlMy.setVisibility(View.GONE);
        } else {
            mSlMy.setVisibility(View.VISIBLE);
            ImageUtils.loadHeadCC(myInfo.getHead_picture(), mRiv);
            mTvName.setText(myInfo.getNickname());
            mTvCharm.setText(myInfo.getTotal_price());
            if (myInfo.getRank() == -1) {
                mTvRankState.setText("暂未上榜");
                mTvRankNo.setText(new SpanUtils().append("距上榜差 ").append(myInfo.getDiff()).setBold().setForegroundColor(Color.parseColor("#FFFF6BA4")).setFontSize(12, true).create());
            } else {
                if (myInfo.getRank() == 1) {
                    mTvRankState.setText("第1名");
                    mTvRankNo.setText("继续加油哦~");
                } else {
                    mTvRankState.setText("已上榜");
                    mTvRankNo.setText(new SpanUtils().append("距上一名差 ").append(myInfo.getDiff()).setBold().setForegroundColor(Color.parseColor("#FFFF6BA4")).setFontSize(12, true).create());
                }
            }
            mRiv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", myInfo.getUser_id()).navigation();
                }
            });
            if (type == TYPE_CHARM) {
                mIvIconCharm.setBackgroundResource(UserBean.MALE.equals(myInfo.getSex()) ? R.mipmap.index_ic_heart_gg : R.mipmap.index_ic_heart_mm);
            } else {
                mIvIconCharm.setBackgroundResource(R.mipmap.index_ic_heart_wealth);
            }
        }
    }

    @Override
    public void setCharmList(WeekStarResp.GiftCharmBean bean) {
        BaseQuickAdapter<WeekStarResp.GiftRichBean.ListsBean, BaseViewHolder> adapter = new BaseQuickAdapter<WeekStarResp.GiftRichBean.ListsBean, BaseViewHolder>(R.layout.index_rv_item_ranking_list, bean.getList()) {

            @Override
            protected void convert(BaseViewHolder helper, WeekStarResp.GiftRichBean.ListsBean item) {
                setCharmBg(helper.getView(R.id.tv_charm), item.getSex());
                helper.setText(R.id.tv_charm, item.getTotal_price());
                helper.setText(R.id.tv_name, item.getNickname());
                helper.setText(R.id.tv_no, String.valueOf(item.getRank()));
                helper.setGone(R.id.iv_level, !TextUtils.isEmpty(item.getLevel_icon()));
                helper.setGone(R.id.iv_vip, !TextUtils.isEmpty(item.getNobility_icon()));
                ImageUtils.loadImageView(item.getLevel_icon(), helper.getView(R.id.iv_level));
                ImageUtils.loadImageView(item.getNobility_icon(), helper.getView(R.id.iv_vip));
                ImageUtils.loadHeadCC(item.getHead_picture(), helper.getView(R.id.riv_avatar));
                setHeadBorder(helper.getView(R.id.riv_avatar), item.getSex());
                if (item.getRank() % 2 == 0) {
                    helper.itemView.setBackgroundResource(R.mipmap.index_bg_ranking_list_item);
                } else {
                    helper.itemView.setBackgroundColor(Color.WHITE);
                }
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", item.getUser_id()).navigation();
                    }
                });
            }
        };
        mRecycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(mRecycleView);
        adapter.setEmptyView(R.layout.index_view_empty_ranking_list);
    }

    @Override
    public void setRoomList(WeekStarResp.GiftRoomBean bean) {
        mRecycleView.setPadding(0, ConvertUtils.dp2px(20), 0, 0);
        BaseQuickAdapter<WeekStarResp.GiftRoomBean.ListsBeanXX, BaseViewHolder> adapter = new BaseQuickAdapter<WeekStarResp.GiftRoomBean.ListsBeanXX, BaseViewHolder>(R.layout.index_rv_item_ranking_list, bean.getList()) {

            @Override
            protected void convert(BaseViewHolder helper, WeekStarResp.GiftRoomBean.ListsBeanXX item) {
                helper.setText(R.id.tv_charm, item.getTotal_price());
                helper.setText(R.id.tv_name, item.getRoom_name());
                helper.setText(R.id.tv_no, String.valueOf(item.getRank()));
                helper.setGone(R.id.iv_level, !TextUtils.isEmpty(item.getLevel_icon()));
                helper.setGone(R.id.iv_vip, !TextUtils.isEmpty(item.getNobility_icon()));
                ImageUtils.loadImageView(item.getLevel_icon(), helper.getView(R.id.iv_level));
                ImageUtils.loadImageView(item.getNobility_icon(), helper.getView(R.id.iv_vip));
                ImageUtils.loadHeadCC(item.getCover_picture(), helper.getView(R.id.riv_avatar));
                setHeadBorder(helper.getView(R.id.riv_avatar), item.getSex());
                if (item.getRank() % 2 == 0) {
                    helper.itemView.setBackgroundResource(R.mipmap.index_bg_ranking_list_item);
                } else {
                    helper.itemView.setBackgroundColor(Color.WHITE);
                }
//                helper.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", item.getRoomId()).navigation();
//                    }
//                });
            }
        };
        mRecycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(mRecycleView);
        adapter.setEmptyView(R.layout.index_view_empty_ranking_list);
    }

    private void setCharmBg(View view, String sex) {
        if (type != TYPE_CHARM) {
            return;
        }
        view.setPadding(ConvertUtils.dp2px(22), ConvertUtils.dp2px(1.5f), ConvertUtils.dp2px(6), 0);
        view.setBackgroundResource(UserBean.MALE.equals(sex) ? R.drawable.index_bg_ranking_charm_gg : R.drawable.index_bg_ranking_charm_mm);
    }

    @Override
    public void setWealthList(WeekStarResp.GiftRichBean bean) {
        BaseQuickAdapter<WeekStarResp.GiftRichBean.ListsBean, BaseViewHolder> adapter = new BaseQuickAdapter<WeekStarResp.GiftRichBean.ListsBean, BaseViewHolder>(R.layout.index_rv_item_ranking_list, bean.getList()) {

            @Override
            protected void convert(BaseViewHolder helper, WeekStarResp.GiftRichBean.ListsBean item) {
                helper.setText(R.id.tv_charm, item.getTotal_price());
                helper.setText(R.id.tv_name, item.getNickname());
                helper.setText(R.id.tv_no, String.valueOf(item.getRank()));
                helper.setGone(R.id.iv_level, !TextUtils.isEmpty(item.getLevel_icon()));
                helper.setGone(R.id.iv_vip, !TextUtils.isEmpty(item.getNobility_icon()));
                ImageUtils.loadImageView(item.getLevel_icon(), helper.getView(R.id.iv_level));
                ImageUtils.loadImageView(item.getNobility_icon(), helper.getView(R.id.iv_vip));
                ImageUtils.loadHeadCC(item.getHead_picture(), helper.getView(R.id.riv_avatar));
                setHeadBorder(helper.getView(R.id.riv_avatar), item.getSex());
                if (item.getRank() % 2 == 0) {
                    helper.itemView.setBackgroundResource(R.mipmap.index_bg_ranking_list_item);
                } else {
                    helper.itemView.setBackgroundColor(Color.WHITE);
                }
            }
        };
        mRecycleView.setAdapter(adapter);
        adapter.bindToRecyclerView(mRecycleView);
        adapter.setEmptyView(R.layout.index_view_empty_ranking_list);
    }

}
