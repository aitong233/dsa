package com.qpyy.module.index.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CustomSlidingTabLayout;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.constant.URLConstants;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module.index.R;
import com.qpyy.module.index.R2;
import com.qpyy.module.index.bean.BannerModel;
import com.qpyy.module.index.bean.RoomTypeModel;
import com.qpyy.module.index.contacts.IndexCategoryContacts;
import com.qpyy.module.index.event.BannerRefreshEvent;
import com.qpyy.module.index.presenter.IndexCategoryPresenter;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class IndexCategoryFragment extends BaseMvpFragment<IndexCategoryPresenter> implements IndexCategoryContacts.View {


    @BindView(R2.id.banner)
    XBanner mBanner;
    @BindView(R2.id.sliding_tab_layout)
    CustomSlidingTabLayout mSlidingTabLayout;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;

    public static final String TYPE_ME = "-1";
    public static final String TYPE_RECOMMEND = "-2";
    public static final String TYPE_HOT = "-3";
    public static final String TYPE_ORDER = "-4";

    public static IndexCategoryFragment newInstance() {
        return new IndexCategoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void bannerRefresh(BannerRefreshEvent event) {
        MvpPre.getBanners();
    }

    @Override
    protected IndexCategoryPresenter bindPresenter() {
        return new IndexCategoryPresenter(this, getActivity());
    }

    @Override
    protected void initData() {
        MvpPre.getBanners();
        MvpPre.getCategories();
    }

    @Override
    protected void initView() {
        mBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                BannerModel bannerModel = (BannerModel) model;
                ImageUtils.loadCenterCrop(bannerModel.getContent(), (ImageView) view);
            }
        });
        mBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                /**
                 * 1房间2文章3链接 其他详情图
                 *
                 * type=1时，该值表示房间id；type=2时，表示文章id
                 */
                BannerModel bannerModel = (BannerModel) model;
                if ("6".equals(bannerModel.getType())) {
                    return;
                }
                if (bannerModel.getType().equals("1")) {
                    ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", bannerModel.getItem_id()).navigation();
                } else if (bannerModel.getType().equals("2")) {
                    ARouter.getInstance().build(ARouteConstants.H5).withString("url", URLConstants.ARTICLE + bannerModel.getItem_id()).withString("title", bannerModel.getTitle()).navigation();
                } else if (bannerModel.getType().equals("3")) {
                    ARouter.getInstance().build(ARouteConstants.H5).withString("url", bannerModel.getLink_url()).withString("title", bannerModel.getTitle()).navigation();
                } else {
                    ARouter.getInstance().build(ARouteConstants.H5).withString("url", URLConstants.HOME_BANNER + bannerModel.getAd_id()).withString("title", bannerModel.getTitle()).navigation();
                }
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.index_fragment_index_category;
    }


    @OnClick(R2.id.tv_search)
    public void onViewClicked() {
        ARouter.getInstance().build(ARouteConstants.INDEX_SEARCH).navigation();
    }

    @Override
    public void setCategories(List<RoomTypeModel> list) {
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), list));
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setCurrentTab(2);
    }

    @Override
    public void setBanners(List<BannerModel> list) {
        mBanner.setBannerData(R.layout.index_image_banner, list);
    }

    private static class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private List<RoomTypeModel> list;


        public MyFragmentPagerAdapter(FragmentManager fm, List<RoomTypeModel> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            RoomTypeModel model = list.get(position);
            if (TYPE_HOT.equals(model.getId())) {
                return HotListFragment.newInstance(model.getId());
            } else if (TYPE_RECOMMEND.equals(model.getId())) {
                return RecommendListFragment.newInstance(model.getId());
            } else if (TYPE_ME.equals(model.getId())) {
                return ChatRoomMeFragment.newInstance();
            }
            return RoomListFragment.newInstance(model.getId());
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            RoomTypeModel model = list.get(position);
            return model.getName();
        }
    }
}