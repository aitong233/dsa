package com.spadea.xqipao.ui.home.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.home.fragment.SearchRoomFragment;
import com.spadea.xqipao.ui.home.fragment.SearchUserFragment;
import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.yuyin.R;
import com.spadea.yuyin.widget.CoustomSlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


@Route(path = ARouters.LIVE_SEARCH, name = "搜索页面")
public class SearchActivity extends BaseActivity {


    @BindView(R.id.ed_search)
    EditText edSeatch;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.coustom_sliding_tab_layout)
    CoustomSlidingTabLayout coustomSlidingTabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.rl_record)
    RelativeLayout rlRecord;
    @BindView(R.id.ll_data)
    LinearLayout lldata;

    private SearchUserFragment searchUserFragment;
    private SearchRoomFragment searchRoomFragment;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private String[] title = new String[]{"    用户   ", "    房间   "};


    public SearchActivity() {
        super(R.layout.activity_searchx);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(searchUserFragment = new SearchUserFragment());
        fragmentList.add(searchRoomFragment = new SearchRoomFragment());
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentList, getSupportFragmentManager());
        viewpager.setAdapter(myFragmentPagerAdapter);
        viewpager.setOffscreenPageLimit(2);
        coustomSlidingTabLayout.setViewPager(viewpager, title);
        coustomSlidingTabLayout.setCurrentTab(0);
    }

    @Override
    protected void setListener() {
        super.setListener();
        edSeatch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == 6 || actionId == 5||actionId==0) {
                String keyword = edSeatch.getText().toString().trim();
                LogUtils.e("搜索",keyword);
                if (!TextUtils.isEmpty(keyword)) {
                    rlRecord.setVisibility(View.GONE);
                    lldata.setVisibility(View.VISIBLE);
                    if (searchRoomFragment != null && searchUserFragment != null) {
                        searchUserFragment.searchKeyWord(keyword);
                        searchRoomFragment.searchKeyWord(keyword);
                    }
                } else {
                    rlRecord.setVisibility(View.VISIBLE);
                    lldata.setVisibility(View.GONE);
                }
            }
            return false;
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

    @OnClick({R.id.tv_clean})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_clean:
                finish();
                break;
        }
    }

}
