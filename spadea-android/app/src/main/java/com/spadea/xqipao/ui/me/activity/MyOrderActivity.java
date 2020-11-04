package com.spadea.xqipao.ui.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.SlidingTabLayout;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.base.view.BaseAppCompatActivity;
import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;
import com.spadea.xqipao.ui.me.fragment.MyOrderFragment;
import com.spadea.yuyin.R;
import com.spadea.xqipao.ui.me.adapter.MyOrderAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.MY_ORDER, name = "我的订单")
public class MyOrderActivity extends BaseAppCompatActivity {


    @BindView(R.id.tv_title)
    TextView mTvTitle;

    public MyOrderActivity() {
        super(R.layout.activity_my_order);
    }


    @Override
    protected void initData() {

    }

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    SlidingTabLayout tab;

    @Override
    protected void initView() {
        mTvTitle.setText("我的订单");
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(MyOrderFragment.newInstance(MyOrderAdapter.TYPE_RECV));
        fragmentList.add(MyOrderFragment.newInstance(MyOrderAdapter.TYPE_SEND));
        MyFragmentPagerAdapter fm = new MyFragmentPagerAdapter(fragmentList, getSupportFragmentManager());
        vp.setAdapter(fm);
        tab.setViewPager(vp, new String[]{"我接的单", "我下的单"});
        tab.setCurrentTab(0);
    }

    @OnClick(R.id.tv_title)
    public void onViewClicked() {
        onBackPressed();
    }
}
