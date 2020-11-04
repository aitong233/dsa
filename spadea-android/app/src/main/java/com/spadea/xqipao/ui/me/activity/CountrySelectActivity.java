package com.spadea.xqipao.ui.me.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.SizeUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.CountryBean;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.utils.GetJsonDataUtil;
import com.spadea.xqipao.widget.FloatingItemDecoration;
import com.spadea.xqipao.widget.SlideBar;
import com.spadea.xqipao.ui.me.adapter.PickCityAdapter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.ME_COUNTRYSELECTACTIVITY, name = "国家选择")
public class CountrySelectActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.slidebar)
    SlideBar slidebar;
    private PickCityAdapter pickCityAdapter;
    private FloatingItemDecoration floatingItemDecoration;

    private HashMap<Integer, String> keys;
    private HashMap<String, Integer> letterIndexes = new HashMap<>();
    private LinearLayoutManager llm;

    public CountrySelectActivity() {
        super(R.layout.activity_country_select);
    }

    @Override
    protected void initData() {
        keys = new HashMap<>();
        String json = new GetJsonDataUtil().getJson(this, "country.json");
        List<CountryBean> list = JSON.parseArray(json, CountryBean.class);
        pickCityAdapter.setNewData(list);
        //添加了头部,所以keys要从1开始
        keys.put(0, "A");
        letterIndexes.put("#", 0);
        letterIndexes.put("A", 1);

        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                //首字母不同,设为ky
                String pre = list.get(i).getPinyin().substring(0, 1).toUpperCase();
                String next = list.get(i + 1).getPinyin().substring(0, 1).toUpperCase();
                if (!pre.equals(next)) {
                    keys.put(i + 1, next);
                    letterIndexes.put(next, i + 1);
                }
            }
        }
        floatingItemDecoration.setKeys(keys);


    }

    @Override
    protected void initView() {

        tvTitle.setText("国家/地区");
        pickCityAdapter = new PickCityAdapter();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(pickCityAdapter);

        floatingItemDecoration = new FloatingItemDecoration(this,
                this.getResources().getColor(R.color.divider_normal), 100, 0);
        floatingItemDecoration.setmTitleHeight(SizeUtils.dp2px( 27));
        floatingItemDecoration.setShowFloatingHeaderOnScrolling(true);//悬浮
        recyclerview.addItemDecoration(floatingItemDecoration);
        llm = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(llm);


        slidebar.setOnTouchingLetterChangedListener(new SlideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s, int offset) {
                int position = letterIndexes.get(s) == null ? -1 : letterIndexes.get(s);
                llm.scrollToPositionWithOffset(position, offset);
            }
        });

        pickCityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CountryBean item = (CountryBean) adapter.getData().get(position);
                Intent intent = getIntent();
                intent.putExtra("country", item.getName());
                intent.putExtra("code", item.getCode());
                setResult(200, intent);
                finish();
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
