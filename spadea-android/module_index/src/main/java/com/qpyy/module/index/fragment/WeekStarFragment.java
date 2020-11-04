package com.qpyy.module.index.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lihang.ShadowLayout;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module.index.R;
import com.qpyy.module.index.R2;
import com.qpyy.module.index.bean.LastWeekStarResp;
import com.qpyy.module.index.contacts.WeekStarContacts;
import com.qpyy.module.index.event.RankingTabSwitchEvent;
import com.qpyy.module.index.presenter.WeekStarPresenter;
import com.qpyy.module.index.widget.LastStar3View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.fragment
 * 创建人 王欧
 * 创建时间 2020/6/28 4:15 PM
 * 描述 describe
 */
public class WeekStarFragment extends BaseMvpFragment<WeekStarPresenter> implements WeekStarContacts.View {
    @BindView(R2.id.sl_day)
    ShadowLayout mSlDay;
    @BindView(R2.id.sl_week)
    ShadowLayout mSlWeek;
    @BindView(R2.id.sl_month)
    ShadowLayout mSlMonth;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;
    @BindView(R2.id.group_week_star)
    Group mGroupWeekStar;
    @BindView(R2.id.group_last_week_star)
    Group mGroupLastWeekStar;
    @BindView(R2.id.riv_top1)
    RoundedImageView mRivTop1;
    @BindView(R2.id.tv_name_top1)
    TextView mTvNameTop1;
    @BindView(R2.id.iv_vip1)
    ImageView mIvVip1;
    @BindView(R2.id.iv_level1)
    ImageView mIvLevel1;
    @BindView(R2.id.tv_charm1)
    TextView mTvCharm1;
    @BindView(R2.id.lsv1)
    LastStar3View mLsv1;
    @BindView(R2.id.lsv2)
    LastStar3View mLsv2;
    @BindView(R2.id.lsv3)
    LastStar3View mLsv3;
    private String roomId;

    public static WeekStarFragment newInstance(String roomId) {

        Bundle args = new Bundle();
        args.putString("roomId", roomId);
        WeekStarFragment fragment = new WeekStarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSwitchTab(RankingTabSwitchEvent event) {
        if (event.type != RankingFragment.TYPE_WEEK_STAR) {
            mViewPager.setCurrentItem(2);
        }
    }

    @Override
    protected WeekStarPresenter bindPresenter() {
        return new WeekStarPresenter(this, getActivity());
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        roomId = arguments.getString("roomId");
    }

    @Override
    protected void initData() {
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), roomId));
    }

    @Override
    protected void initView() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                resetTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.index_fragment_week_star;
    }

    @OnClick({R2.id.tv_day, R2.id.tv_week, R2.id.tv_month, R2.id.iv_last_week_star, R2.id.iv_week_star})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_day) {
            resetTab(0);
            mViewPager.setCurrentItem(0);
        } else if (id == R.id.tv_week) {
            resetTab(1);
            mViewPager.setCurrentItem(1);
        } else if (id == R.id.tv_month) {
            resetTab(2);
            mViewPager.setCurrentItem(2);
        } else if (id == R.id.iv_week_star) {
            mGroupLastWeekStar.setVisibility(View.VISIBLE);
            mGroupWeekStar.setVisibility(View.GONE);
            MvpPre.getLastList(roomId, 0);
        } else if (id == R.id.iv_last_week_star) {
            mGroupLastWeekStar.setVisibility(View.GONE);
            mGroupWeekStar.setVisibility(View.VISIBLE);
        }
    }

    private void resetTab(int index) {
        mSlDay.setVisibility(View.GONE);
        mSlWeek.setVisibility(View.GONE);
        mSlMonth.setVisibility(View.GONE);
        if (index == 0) {
            mSlDay.setVisibility(View.VISIBLE);
        } else if (index == 1) {
            mSlWeek.setVisibility(View.VISIBLE);
        } else if (index == 2) {
            mSlMonth.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setLastWeekStar(LastWeekStarResp resp) {
        if (resp != null) {
            if (resp.getLast_star() != null) {
                mRivTop1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", resp.getLast_star().getUser_id()).navigation();
                    }
                });
                mIvVip1.setVisibility(TextUtils.isEmpty(resp.getLast_star().getNobility_icon()) ? View.GONE : View.VISIBLE);
                ImageUtils.loadHeadCC(resp.getLast_star().getHead_picture(), mRivTop1);
                ImageUtils.loadImageView(resp.getLast_star().getLevel_icon(), mIvLevel1);
                ImageUtils.loadImageView(resp.getLast_star().getNobility_icon(), mIvVip1);
                mTvCharm1.setText(resp.getLast_star().getTotal_price());
                mTvNameTop1.setText(resp.getLast_star().getNickname());
                mTvCharm1.setBackgroundResource(UserBean.MALE.equals(resp.getLast_star().getSex()) ? R.drawable.index_bg_ranking_charm_gg : R.drawable.index_bg_ranking_charm_mm);
            }
            List<LastWeekStarResp.TopRichThreeBean> top_rich_three = resp.getTop_rich_three();
            if (top_rich_three != null) {
                if (top_rich_three.size() > 0) {
                    mLsv1.setData(top_rich_three.get(0));
                }
                if (top_rich_three.size() > 1) {
                    mLsv2.setData(top_rich_three.get(1));
                }
                if (top_rich_three.size() > 2) {
                    mLsv3.setData(top_rich_three.get(2));
                }
            }
        }
    }

    @Override
    public void refreshFollow() {
        MvpPre.getLastList(roomId, 0);
    }

    private static class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {


        private String roomId;


        public MyFragmentPagerAdapter(FragmentManager fm, String roomId) {
            super(fm);
            this.roomId = roomId;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return WeekStarListFragment.newInstance(roomId, WeekStarListFragment.TYPE_CHARM);
            } else if (position == 1) {
                return WeekStarListFragment.newInstance(roomId, WeekStarListFragment.TYPE_ROOM);
            }
            return WeekStarListFragment.newInstance(roomId, WeekStarListFragment.TYPE_CONTRIBUTE);
        }

        @Override
        public int getCount() {
            return 3;
        }

    }
}
