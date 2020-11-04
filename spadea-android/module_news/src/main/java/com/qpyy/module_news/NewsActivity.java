package com.qpyy.module_news;

import com.blankj.utilcode.util.FragmentUtils;
import com.qpyy.libcommon.base.BaseAppCompatActivity;
import com.qpyy.module_news.fragment.NewsFragment;

public class NewsActivity extends BaseAppCompatActivity {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        FragmentUtils.add(getSupportFragmentManager(), NewsFragment.newInstance(), R.id.container);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.news_activity_news;
    }
}
