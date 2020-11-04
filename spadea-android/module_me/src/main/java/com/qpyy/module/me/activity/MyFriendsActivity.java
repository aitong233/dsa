package com.qpyy.module.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.SlidingTabLayout;
import com.qpyy.libcommon.adapter.MyFragmentPagerAdapter;
import com.qpyy.libcommon.base.BaseAppCompatActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.fragment.MyFriendsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.ME_MY_FRIENDS, name = "我的好友")
public class MyFriendsActivity extends BaseAppCompatActivity {


    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.sliding_tab_layout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R2.id.view_pager)
    ViewPager viewPager;

    @Autowired
    public int type = 0;


    @Override
    protected void initView() {
        tvTitle.setText("我的好友");
    }

    @Override
    protected void initData() {
        List<Fragment> fragmentList = new ArrayList<>();
        String[] title = new String[]{"好友", "关注", "粉丝"};
        fragmentList.add(MyFriendsFragment.newInstance(0));
        fragmentList.add(MyFriendsFragment.newInstance(1));
        fragmentList.add(MyFriendsFragment.newInstance(2));
        viewPager.setAdapter(new MyFragmentPagerAdapter(fragmentList, getSupportFragmentManager()));
        slidingTabLayout.setViewPager(viewPager, title);
        slidingTabLayout.setCurrentTab(type);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.me_activity_my_friends;
    }


    @OnClick({R2.id.iv_back})
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back) {
            finish();
        }
    }
}
