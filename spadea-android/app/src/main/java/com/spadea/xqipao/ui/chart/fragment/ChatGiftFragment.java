package com.spadea.xqipao.ui.chart.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.bean.GiftModel;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module_news.bean.GiftNumBean;
import com.qpyy.module_news.widget.SelectGiftNumPopupWindow;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.chart.presenter.ChatGiftPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.chart.contacts.ChatGiftContacts;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class ChatGiftFragment extends BaseMvpDialogFragment<ChatGiftPresenter> implements ChatGiftContacts.View {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_count)
    TextView mTvCount;

    private String userId;


    private static GiftModel giftModel;

    private static final int pageSize = 8;
    private SelectGiftNumPopupWindow mSelectGiftNumPopupWindow;

    public static ChatGiftFragment newInstance(String userId) {

        Bundle args = new Bundle();
        args.putString("userId", userId);
        ChatGiftFragment fragment = new ChatGiftFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        userId = arguments.getString("userId");
    }

    @Override
    protected ChatGiftPresenter bindPresenter() {
        return new ChatGiftPresenter(this, getActivity());
    }

    @Override
    protected void initData() {
        MvpPre.giftWall();
        MvpPre.getBalance();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ScreenUtils.getScreenWidth(), ConvertUtils.dp2px(320));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.news_fragement_chat_gift;
    }

    @OnClick({R.id.ll_recharge, R.id.btn_pay, R.id.ll_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_recharge:
                ARouter.getInstance().build(ARouters.ME_BALANCE).navigation();
                break;
            case R.id.btn_pay:
                giveGift();
                break;
            case R.id.ll_num:
                if (giftModel == null) {
                    ToastUtils.showShort("请选择礼物");
                    return;
                }
                MvpPre.getGiftNumBeanData(giftModel);
                break;
        }
    }

    private void giveGift() {
        if (giftModel == null) {
            ToastUtils.showShort("请选择礼物");
            return;
        }
        String num = mTvCount.getText().toString();
        if (TextUtils.isEmpty(num)) {
            ToastUtils.showShort("请选择打赏礼物数量");
            return;
        }
        MvpPre.giveGift(giftModel, userId, num);
    }

    @Override
    public void setData(List<GiftModel> data) {
        if (data == null) {
            return;
        }
        mViewPager.setAdapter(new MyPageAdapter(data));
    }

    @Override
    public void setGiftNumBeanData(List<com.qpyy.module_news.bean.GiftNumBean> data) {
        if (mSelectGiftNumPopupWindow == null) {
            mSelectGiftNumPopupWindow = new SelectGiftNumPopupWindow(getActivity(), new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    mSelectGiftNumPopupWindow.dismiss();
                    GiftNumBean giftNumBean = (GiftNumBean) adapter.getItem(position);
                    mTvCount.setText(giftNumBean.getNumber());
                }
            });
        }
        mSelectGiftNumPopupWindow.setData(data);
        mSelectGiftNumPopupWindow.showAtLocation(mTvCount, Gravity.BOTTOM | Gravity.RIGHT, 50, 150);
    }

    @Override
    public void setBalanceMoney(String money) {
        mTvMoney.setText(money);
    }

    @Override
    public void pop() {
        dismiss();
    }

    private class MyPageAdapter extends PagerAdapter {

        private List<GiftModel> data;

        public MyPageAdapter(List<GiftModel> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return (int) Math.ceil(data.size() * 1.0 / pageSize);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(container.getContext()).inflate(R.layout.news_page_item_gift_list, container, false);
            recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(), 4));
            if (position * pageSize > data.size() - pageSize) {
                recyclerView.setAdapter(new MyGiftAdapter(data.subList(position * pageSize, data.size())));
            } else {
                recyclerView.setAdapter(new MyGiftAdapter(data.subList(position * pageSize, position * pageSize + pageSize)));
            }
            container.addView(recyclerView);
            return recyclerView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }
    }

    private class MyGiftAdapter extends BaseQuickAdapter<GiftModel, BaseViewHolder> {

        public MyGiftAdapter(@Nullable List<GiftModel> data) {
            super(R.layout.news_rv_item_gift, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, GiftModel item) {
            helper.setText(R.id.tv_name, item.getName());
            helper.setText(R.id.tv_price, new SpanUtils().append(String.valueOf(item.getPrice())).setForegroundColor(Utils.getApp().getResources().getColor(R.color.color_6765FF)).append("金币").create());
            RoundedImageView imageView = helper.getView(R.id.image);
            ImageUtils.loadCenterCrop(item.getPicture(), imageView);
            if (index == helper.getAdapterPosition()) {
                imageView.setBorderColor(Color.parseColor("#FFB6A2FF"));
            } else {
                imageView.setBorderColor(Color.TRANSPARENT);
            }
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setIndex(helper.getAdapterPosition());
                }
            });
        }

        private int index = -1;

        public void setIndex(int index) {
            ChatGiftFragment.giftModel = getItem(index);
            int type = ChatGiftFragment.giftModel.getType();
            if (type == 4 || type == 5 || type == 13) { //是盲盒
                int num = Integer.parseInt(mTvCount.getText().toString());
                if (num > 88) {
                    mTvCount.setText("88");
                }
            }
            this.index = index;
            notifyDataSetChanged();
        }

    }

    @Override
    public void showLoadings() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showLoading();
        }
    }

    @Override
    public void disLoadings() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).disLoading();
        }
    }

    @Override
    public void onDestroyView() {
        giftModel = null;
        super.onDestroyView();
    }
}