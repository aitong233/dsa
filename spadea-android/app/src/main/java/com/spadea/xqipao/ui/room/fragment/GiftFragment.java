package com.spadea.xqipao.ui.room.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.spadea.yuyin.R;
import com.spadea.xqipao.data.GiftModel;
import com.spadea.xqipao.data.even.GiftEvent;
import com.spadea.xqipao.ui.room.presenter.GiftPresenter;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.room.adapter.GridViewAdapter;
import com.spadea.xqipao.ui.room.adapter.ViewPagerAdapter;
import com.spadea.xqipao.ui.room.contacts.GiftContacts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GiftFragment extends BaseFragment<GiftPresenter> implements GiftContacts.View {


    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.ll_dot)
    LinearLayout llDot;
    private int curIndex = 0;
    private LayoutInflater mInflater;
    private int type = 0;
    private int pageSize = 8;
    private int pageCount;
    private List<View> mPagerList;//页面集合
    private List<GridViewAdapter> gridViewAdapterList = new ArrayList<>();

    private List<GiftModel> mData;

    public static GiftFragment newInstance(int type) {
        GiftFragment giftFragment = new GiftFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        giftFragment.setArguments(bundle);
        return giftFragment;
    }


    @Override
    protected GiftPresenter bindPresenter() {
        return new GiftPresenter(this, mContext);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View rootView) {
        EventBus.getDefault().register(this);
        type = getArguments().getInt("type", 0);
        mInflater = LayoutInflater.from(mContext);
        if (type == 0) {
            MvpPre.giftWall();
        } else {
            MvpPre.userBackPack();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gifts;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    public GiftModel getmData() {
        if (mData != null) {
            for (GiftModel item : mData) {
                if (item.isChecked()) {
                    return item;
                }
            }
        }
        return null;
    }


    @Override
    public void setData(List<GiftModel> data) {
        if (data == null) {
            return;
        }
        this.mData = data;
        gridViewAdapterList.clear();
        pageCount = (int) Math.ceil(data.size() * 1.0 / pageSize);
        mPagerList = new ArrayList<View>();
        for (int j = 0; j < pageCount; j++) {
            final GridView gridview = (GridView) mInflater.inflate(R.layout.bottom_vp_gridview, viewpager, false);
            final GridViewAdapter gridAdapter = new GridViewAdapter(mContext, data, j, type);
            gridview.setAdapter(gridAdapter);
            gridViewAdapterList.add(gridAdapter);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    for (int i = 0; i < data.size(); i++) {
                        GiftModel giftModel = data.get(i);
                        if (i == id) {
                            if (giftModel.isChecked()) {
                                giftModel.setChecked(false);
                            } else {
                                giftModel.setChecked(true);
                            }
                        } else {
                            giftModel.setChecked(false);
                        }
                    }
                    gridAdapter.notifyDataSetChanged();
                }
            });
            mPagerList.add(gridview);
        }
        viewpager.setAdapter(new ViewPagerAdapter(mPagerList, mContext));
        setOvalLayout();
    }

    public void setOvalLayout() {
        llDot.removeAllViews();
        for (int i = 0; i < pageCount; i++) {
            llDot.addView(mInflater.inflate(R.layout.dot, null));
        }
        if (pageCount != 0) {
            llDot.getChildAt(0).findViewById(R.id.v_dot)
                    .setBackgroundResource(R.drawable.dot_selected);
        }
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                for (GridViewAdapter adapter : gridViewAdapterList) {
                    adapter.notifyDataSetChanged();
                }
                // 取消圆点选中
                llDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                llDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void giftEvent(GiftEvent giftEvent) {
        if (type == 1) {
            MvpPre.userBackPack();
        }
    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
