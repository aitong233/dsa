package com.spadea.xqipao.ui.home.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.BannerModel;
import com.spadea.xqipao.data.RoomTypeModel;
import com.spadea.xqipao.data.TopTwoModel;
import com.spadea.xqipao.ui.home.presenter.LivePresenter;
import com.spadea.xqipao.ui.live.activity.RankingListActivity;
import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;
import com.spadea.xqipao.ui.room.activity.LiveRoomActivity;
import com.spadea.xqipao.utils.view.ImgPager;
import com.stx.xhb.xbanner.XBanner;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.PreferencesUtils;
import com.spadea.yuyin.util.utilcode.TimeUtils;
import com.spadea.yuyin.widget.CoustomSlidingTabLayout;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.home.contacts.LiveContacts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LiveFragment extends BaseFragment<LivePresenter> implements LiveContacts.View {


    @BindView(R.id.tv_online)
    TextView tvOnline;
    @BindView(R.id.banner)
    XBanner banner;
    @BindView(R.id.coustom_sliding_tab_layout)
    CoustomSlidingTabLayout coustomSlidingTabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.imp_ranking)
    ImgPager impRanking;
    @BindView(R.id.imp_leaflets)
    ImgPager impLeaflets;
    @BindView(R.id.iv_sign)
    ImageView mIvSign;


    private MyFragmentPagerAdapter myFragmentPagerAdapter;


    public static LiveFragment newInstance(String title) {
        LiveFragment liveFragment = new LiveFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        liveFragment.setArguments(bundle);
        return liveFragment;
    }


    @Override
    protected LivePresenter bindPresenter() {
        return new LivePresenter(this, getContext());
    }

    @Override
    protected void initData() {
        banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                BannerModel bannerModel = (BannerModel) model;
                //设置图片圆角角度
                RoundedCorners roundedCorners = new RoundedCorners(10);
                //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
                RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
                Glide.with(mContext)
                        .load(bannerModel.getContent())
                        .apply(options)
                        .into((ImageView) view);
            }
        });


        MvpPre.getTopTwo();
        MvpPre.getBanners();
        MvpPre.getRoomType();
        MvpPre.signSwitch();
    }

    @Override
    protected void initView(View rootView) {
        List<String> list = new ArrayList<>();
        list.add("https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/custom/img.png");
        list.add("https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/custom/img.png");
        list.add("https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/custom/img.png");
        impLeaflets.loadData(list);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragement_live;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }

    @Override
    protected void initListener() {
        super.initListener();
        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
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
                    LiveRoomActivity.start(getActivity(), bannerModel.getItem_id());
                } else if (bannerModel.getType().equals("2")) {
                    ARouter.getInstance().build(ARouters.H5).withString("url", Constant.URL.ARTICLE + bannerModel.getItem_id()).withString("title", bannerModel.getTitle()).navigation();
                } else if (bannerModel.getType().equals("3")) {
                    ARouter.getInstance().build(ARouters.H5).withString("url", bannerModel.getLink_url()).withString("title", bannerModel.getTitle()).navigation();
                } else {
                    ARouter.getInstance().build(ARouters.H5).withString("url", Constant.URL.HOME_BANNER + bannerModel.getAd_id()).withString("title", bannerModel.getTitle()).navigation();
                }
            }
        });
    }

    /**
     * 获取在线人数成功
     *
     * @param number
     */
    @Override
    public void onlineSuccess(String number) {
        tvOnline.setText(number + "人在线");
    }

    @Override
    public void bannersSuccess(List<BannerModel> data) {
        banner.setBannerData(data);
    }

    @Override
    public void roomTypeSuccess(List<RoomTypeModel> data) {
        String[] title = new String[data.size() + 2];
        List<Fragment> fragmentList = new ArrayList<>();
        title[0] = "全部";
        title[1] = "热门";
        fragmentList.add(AllLiveFragment.newInstance("全部"));
        fragmentList.add(HostLiveFragment.newInstance("热门"));
        for (int i = 0; i < data.size(); i++) {
            RoomTypeModel roomTypeModel = data.get(i);
            title[i + 2] = roomTypeModel.getName();
            fragmentList.add(DefaultLiveFragment.newInstance(roomTypeModel.getName(), roomTypeModel.getId()));
        }
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentList, getChildFragmentManager());
        viewPager.setAdapter(myFragmentPagerAdapter);
        coustomSlidingTabLayout.setViewPager(viewPager, title);
        coustomSlidingTabLayout.setCurrentTab(1);
    }

    @Override
    public void setTopTwo(TopTwoModel data) {
        List<String> list = new ArrayList<>();
        list.add(data.getTop1_picture());
        list.add(data.getTop2_picture());
        impRanking.loadData(list);
    }

    @Override
    public void signSwitch(boolean open) {
        mIvSign.setVisibility(open ? View.VISIBLE : View.GONE);
        if (open && !PreferencesUtils.getBoolean(MyApplication.getInstance(), TimeUtils.getNowString(new SimpleDateFormat("yyyyMMdd")), false)) {
            ARouter.getInstance().build(ARouters.SIGN).navigation();
        }
    }


    @OnClick({R.id.rl_list, R.id.rl_hall, R.id.iv_room, R.id.iv_search, R.id.iv_sign})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_list:
                RankingListActivity.startActivity(getActivity(), "");
                break;
            case R.id.rl_hall:
                ARouter.getInstance().build(ARouteConstants.INDEX_SEARCH).navigation();
                break;
            case R.id.iv_room:
                ARouter.getInstance().build(ARouters.ME_ROOM).navigation();
                break;
            case R.id.iv_search:
                ARouter.getInstance().build(ARouters.LIVE_SEARCH).navigation();
                break;
            case R.id.iv_sign:
                ARouter.getInstance().build(ARouters.SIGN).navigation();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        MvpPre.getOnline();
    }

    @Override
    public void onDestroy() {
        impLeaflets.onDestroy();
        impRanking.onDestroy();
        super.onDestroy();
    }
}
