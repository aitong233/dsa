package com.spadea.xqipao.ui.live.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.spadea.yuyin.R;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 周星榜  1财富2魅力1
 */
public class WeekStarRankingFragment extends BaseFragment {


    @BindView(R.id.tv_charm_ranking)
    TextView tvCharmRanking;
    @BindView(R.id.tv_contribution)
    TextView tvContribution;
    @BindView(R.id.view_page)
    ViewPager viewPage;
    @BindView(R.id.tv_room)
    TextView tvRoom;


    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private List<Fragment> fragmentList;

    public static Fragment newInstance(String roomId, int type) {
        WeekStarRankingFragment weekStarRankingFragment = new WeekStarRankingFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        weekStarRankingFragment.setArguments(bundle);
        return weekStarRankingFragment;
    }


    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView) {
        fragmentList = new ArrayList<>();
        fragmentList.add(WeekStartFragment.newInstance(0));
        fragmentList.add(WeekStartFragment.newInstance(1));
        fragmentList.add(WeekStartFragment.newInstance(2));
        viewPage.setAdapter(myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentList, getChildFragmentManager()));
        viewPage.setCurrentItem(0);
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                reset();
                select(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_week_star_ranking;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @OnClick({R.id.tv_charm_ranking, R.id.tv_contribution,R.id.tv_room})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_charm_ranking:
                viewPage.setCurrentItem(0);
                break;
            case R.id.tv_contribution:
                viewPage.setCurrentItem(2);
                break;
            case R.id.tv_room:
                viewPage.setCurrentItem(1);
                break;
        }
    }

    private void reset() {
        tvCharmRanking.setBackgroundColor(Color.argb(0, 0, 0, 0));
        tvContribution.setBackgroundColor(Color.argb(0, 0, 0, 0));
        tvRoom.setBackgroundColor(Color.argb(0, 0, 0, 0));
    }

    private void select(int postion) {
        switch (postion) {
            case 0:
                tvCharmRanking.setBackgroundResource(R.drawable.bg_item_select_weekstar);
                break;
            case 2:
                tvContribution.setBackgroundResource(R.drawable.bg_item_select_weekstar);
                break;
            case 1:
                tvRoom.setBackgroundResource(R.drawable.bg_item_select_weekstar);
                break;
        }
    }


}
