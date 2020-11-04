package com.spadea.xqipao.ui.me.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.JueInfo;
import com.spadea.xqipao.data.NobilityInfo;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.me.contacter.JueContacts;
import com.spadea.xqipao.ui.me.presenter.JuePresenter;
import com.spadea.xqipao.utils.view.NoScrollViewPager;
import com.spadea.xqipao.ui.me.adapter.ImgAdapter;
import com.spadea.yuyin.R;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class JueFragment extends BaseFragment<JuePresenter> implements JueContacts.View {


    @BindView(R.id.xbanner)
    XBanner xbanner;
    @BindView(R.id.no_scroll_viewpager)
    NoScrollViewPager noScrollViewPager;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private ImgAdapter imgAdapter;

    private List<String> equity = new ArrayList<>();
    private String[] title = new String[]{"子爵权益", "伯爵权益", "侯爵权益", "公爵权益", "王爵权益", "帝王权益"};

    public static Fragment newInstance() {
        JueFragment jueFragment = new JueFragment();
        return jueFragment;
    }

    /**
     * img_zijue
     *
     * @return
     */

    @Override
    protected JuePresenter bindPresenter() {
        return new JuePresenter(this, mContext);
    }

    @Override
    protected void initData() {
        equity.add("https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/images/nobility/01.png");
        equity.add("https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/images/nobility/02.png");
        equity.add("https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/images/nobility/03.png");
        equity.add("https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/images/nobility/04.png");
        equity.add("https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/images/nobility/05.png");
        equity.add("https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/images/nobility/06.png");
        imgAdapter = new ImgAdapter(mContext, equity);
        noScrollViewPager.setAdapter(imgAdapter);
        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                JueInfo jueInfo = (JueInfo) model;
                view.setBackground(getResources().getDrawable(jueInfo.getRes()));
                ImageView imageView = view.findViewById(R.id.iv_type);
                if (jueInfo.getType() == 0) {
                    imageView.setVisibility(View.GONE);
                } else if (jueInfo.getType() == 1) {
                    imageView.setImageDrawable(getResources().getDrawable(R.mipmap.icon_upgrade));
                } else {
                    imageView.setImageDrawable(getResources().getDrawable(R.mipmap.img_renew));
                }
            }
        });
        xbanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                tvTitle.setText(title[i]);
                noScrollViewPager.setCurrentItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        MvpPre.userNobilityInfo();
    }

    @Override
    protected void initListener() {
        super.initListener();
        xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                JueInfo jueInfo = (JueInfo) model;
                switch (jueInfo.getType()) {
                    case 0:
                        break;
                    case 1:
                        ARouter.getInstance().build(ARouters.ME_JUEUPGRADE) .navigation();
                        break;
                    case 2:
                        ARouter.getInstance().build(ARouters.ME_JUERENEW) .navigation();
                        break;
                }
            }
        });
    }

    @Override
    protected void initView(View rootView) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jue;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @Override
    public void userNobilityInfoSuccess(NobilityInfo data) {
        List<JueInfo> list = new ArrayList<>();
        list.add(new JueInfo(R.mipmap.img_zijue_equity, 1));
        list.add(new JueInfo(R.mipmap.img_bojue_equity, 1));
        list.add(new JueInfo(R.mipmap.img_houjue_equity, 1));
        list.add(new JueInfo(R.mipmap.img_gongjue_equity, 1));
        list.add(new JueInfo(R.mipmap.img_wangjue_equity, 1));
        list.add(new JueInfo(R.mipmap.img_diwang_equity, 1));
        for (int i = 0; i < list.size(); i++) {
            JueInfo jueInfo = list.get(i);
            if (data.getInfo().getNobility_id() == 0) {
                jueInfo.setType(1);
            } else {
                if (i > data.getInfo().getNobility_id()-1) {
                    jueInfo.setType(1);
                } else if (data.getInfo().getNobility_id()-1 == i) {
                    jueInfo.setType(2);
                } else {
                    jueInfo.setType(0);
                }
            }
        }
        xbanner.setBannerData(R.layout.view_juewei, list);
    }
}
