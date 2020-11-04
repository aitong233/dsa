package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.bean.GiftModel;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.GridViewAdapter;
import com.qpyy.room.adapter.ViewPagerAdapter;
import com.qpyy.room.bean.GiftEvent;
import com.qpyy.room.contacts.GiftContacts;
import com.qpyy.room.event.GiftUserRefreshEvent;
import com.qpyy.room.presenter.GiftPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GiftFragment extends BaseMvpFragment<GiftPresenter> implements GiftContacts.View {


    @BindView(R2.id.vp_gift)
    ViewPager vpGift;
    @BindView(R2.id.ll_gift_dot)
    LinearLayout llGiftDot;

    private int curIndex = 0;//当前页
    private LayoutInflater mInflater;
    private int type = 0;// 0礼物  1背包
    private int pageSize = 8;//一页显示的礼物个数
    private int pageCount;//页数
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Override
    protected GiftPresenter bindPresenter() {
        return new GiftPresenter(this, getActivity());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        type = getArguments().getInt("type", 0);
        mInflater = LayoutInflater.from(getActivity());
        if (type == 0) {
            MvpPre.giftWall();
        } else {
            MvpPre.userBackPack();
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.room_fragment_gifts;
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
            final GridView gridview = (GridView) mInflater.inflate(R.layout.room_vp_gv_gift, vpGift, false);
            final GridViewAdapter gridAdapter = new GridViewAdapter(getActivity(), data, j, type);
            gridview.setAdapter(gridAdapter);
            gridViewAdapterList.add(gridAdapter);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    for (int i = 0; i < data.size(); i++) {
                        GiftModel giftModel = data.get(i);
                        if (i == id) {
                            if (giftModel.isChecked()) {
                                //不选中
                                EventBus.getDefault().post(new GiftUserRefreshEvent(false, type));
                                giftModel.setChecked(false);
                            } else {
                                //选中就判断是否盲盒
                                EventBus.getDefault().post(new GiftUserRefreshEvent(giftModel.isManghe(), type));
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
        vpGift.setAdapter(new ViewPagerAdapter(mPagerList, getActivity()));
        setOvalLayout();
    }

    public void setOvalLayout() {
        llGiftDot.removeAllViews();
        for (int i = 0; i < pageCount; i++) {
            llGiftDot.addView(mInflater.inflate(R.layout.room_dot, null));
        }
        if (pageCount != 0) {
            llGiftDot.getChildAt(0).findViewById(R.id.v_dot)
                    .setBackgroundResource(R.mipmap.room_gift_indicatior_select);
        }
        vpGift.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                for (GridViewAdapter adapter : gridViewAdapterList) {
                    adapter.notifyDataSetChanged();
                }
                // 取消圆点选中
                llGiftDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.mipmap.room_gift_indicatior_normal);
                // 圆点选中
                llGiftDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.mipmap.room_gift_indicatior_select);
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

}
