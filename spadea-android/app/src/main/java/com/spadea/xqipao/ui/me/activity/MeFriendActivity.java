package com.spadea.xqipao.ui.me.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;
import com.spadea.xqipao.ui.me.fragment.FriendFragment;
import com.spadea.xqipao.ui.me.adapter.FriendTitleAdapter;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.BarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.ME_MEFRIEND, name = "我的好友")
public class MeFriendActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.viewpager)
    ViewPager viewpager;


    private FriendTitleAdapter friendTitleAdapter;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    public MeFriendActivity() {
        super(R.layout.activity_me_friend);
    }

    @Override
    protected void initData() {
        List<String> list = new ArrayList<>();
        list.add("好友");
        list.add("关注");
        list.add("粉丝");
        recyclerView.setAdapter(friendTitleAdapter = new FriendTitleAdapter(list));
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(FriendFragment.newInstance(0));
        fragmentList.add(FriendFragment.newInstance(1));
        fragmentList.add(FriendFragment.newInstance(2));
        viewpager.setAdapter(myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentList, getSupportFragmentManager()));
    }

    @Override
    protected void initView() {
        tvTitle.setText("我的好友");
        viewLine.setVisibility(View.GONE);
        BarUtils.setStatusBarAlpha(this, 0);
        tvTitle.setTextColor(Color.parseColor("#FFFFFF"));
        ivBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_ff));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

    }

    public void setListener() {
        friendTitleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                viewpager.setCurrentItem(position);
            }
        });
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                friendTitleAdapter.setIndewx(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }


}
