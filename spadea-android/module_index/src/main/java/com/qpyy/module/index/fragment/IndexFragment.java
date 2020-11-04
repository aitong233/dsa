package com.qpyy.module.index.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CustomSlidingTabLayout;

import com.qpyy.libcommon.adapter.MyFragmentPagerAdapter;
import com.qpyy.libcommon.base.BaseFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module.index.R;
import com.qpyy.module.index.R2;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.fragment
 * 创建人 王欧
 * 创建时间 2020/6/30 11:02 AM
 * 描述 describe
 */
public class IndexFragment extends BaseFragment {

    @BindView(R2.id.sliding_tab_layout)
    CustomSlidingTabLayout mSlidingTabLayout;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;

    public static IndexFragment newInstance() {
        return new IndexFragment();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(IndexCategoryFragment.newInstance());
        mViewPager.setAdapter(new MyFragmentPagerAdapter(fragments, getChildFragmentManager()));
        mSlidingTabLayout.setViewPager(mViewPager, new String[]{"聊天室"});
        mSlidingTabLayout.setCurrentTab(0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.index_fragment_index;
    }

    @OnClick({R2.id.iv_ranking_list})
    public void rankingClicked(View view) {


        ARouter.getInstance().build(ARouteConstants.INDEX_RANKING_LIST).navigation();
    }
}
