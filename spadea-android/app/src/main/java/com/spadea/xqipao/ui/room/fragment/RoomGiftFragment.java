package com.spadea.xqipao.ui.room.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.xqipao.data.GiftModel;
import com.spadea.xqipao.data.GiftNumBean;
import com.spadea.xqipao.data.RoomPitUserModel;
import com.spadea.xqipao.data.even.GiftEvent;
import com.spadea.xqipao.ui.room.presenter.RoomGiftPresenter;
import com.spadea.xqipao.utils.popupwindow.SelectGiftNumPopupWindow;
import com.spadea.xqipao.ui.base.view.BaseDialogFragment;
import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;
import com.spadea.xqipao.ui.me.activity.BalanceActivity;
import com.spadea.xqipao.ui.room.adapter.GiftUserAdapter;
import com.spadea.xqipao.ui.room.contacts.RoomGiftContacts;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ScreenUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.yuyin.widget.ScrollViewPager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RoomGiftFragment extends BaseDialogFragment<RoomGiftPresenter> implements RoomGiftContacts.View {


    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.svp)
    ScrollViewPager svp;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.ll_num)
    LinearLayout llNum;
    @BindView(R.id.recyclerView_user)
    RecyclerView recyclerView;
    @BindView(R.id.tv_gift)
    TextView tvGift;
    @BindView(R.id.tv_knapsack)
    TextView tvKnapsack;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.rl_all)
    RelativeLayout rlAll;


    private String roomId;
    private String userId;
    private RoomFragmentListener mRoomFragmentListener;
    private List<Fragment> fragmentList = new ArrayList<>();
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private SelectGiftNumPopupWindow mSelectGiftNumPopupWindow;
    private GiftUserAdapter giftUserAdapter;
    private boolean all = false;
    private int type = 0;


    public static RoomGiftFragment newInstance(String userId, String roomId) {
        RoomGiftFragment roomGiftFragment = new RoomGiftFragment();
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        bundle.putString("RoomId", roomId);
        roomGiftFragment.setArguments(bundle);
        return roomGiftFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RoomFragmentListener) {
            mRoomFragmentListener = (RoomFragmentListener) context;
        }
    }


    @Override
    protected void initData() {
        userId = getArguments().getString("UserId");
        roomId = getArguments().getString("RoomId");
        MvpPre.getBalance();
        MvpPre.getRoomPitUser(roomId, userId, true);
    }

    @Override
    protected void initView(View rootView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(giftUserAdapter = new GiftUserAdapter());
        fragmentList.add(GiftFragment.newInstance(0));
        fragmentList.add(GiftFragment.newInstance(1));
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentList, getChildFragmentManager());
        svp.setAdapter(myFragmentPagerAdapter);
        svp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                type = i;
                all = false;
                if (type == 0) {
                    tvGift.setTextColor(Color.parseColor("#FFFF8890"));
                    tvKnapsack.setTextColor(Color.parseColor("#FFFFFF"));
                    MvpPre.getRoomPitUser(roomId, userId, true);
                } else {
                    tvKnapsack.setTextColor(Color.parseColor("#FFFF8890"));
                    tvGift.setTextColor(Color.parseColor("#FFFFFF"));
                    MvpPre.getRoomPitUser(roomId, userId, false);
                }
                tvAll.setTextColor(Color.parseColor("#333333"));
                tvAll.setBackgroundResource(R.drawable.bg_gift_user_unselect);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        giftUserAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RoomPitUserModel item = giftUserAdapter.getItem(position);
                if (item.isSelect()) {
                    item.setSelect(false);
                } else {
                    item.setSelect(true);
                }
                giftUserAdapter.notifyItemChanged(position, item);
                all = giftUserAdapter.isAll();
                if (all) {
                    tvAll.setTextColor(Color.parseColor("#FFFFFF"));
                    tvAll.setBackgroundResource(R.drawable.bg_gift_user_select);
                } else {
                    tvAll.setTextColor(Color.parseColor("#333333"));
                    tvAll.setBackgroundResource(R.drawable.bg_gift_user_unselect);
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_room_gift;
    }

    @Override
    protected RoomGiftPresenter bindPresenter() {
        return new RoomGiftPresenter(this, mContext);
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ScreenUtils.getScreenWidth(), (int) (ScreenUtils.getScreenHeight() * 5.5 / 10));
        window.setWindowAnimations(R.style.ShowDialogBottom);
    }


    @Override
    public void setBalanceMoney(String money) {
        tvMoney.setText(money);
    }

    @OnClick({R.id.tv_gift, R.id.tv_knapsack})
    public void onSelect(View view) {
        switch (view.getId()) {
            case R.id.tv_gift:
                svp.setCurrentItem(0);
                break;
            case R.id.tv_knapsack:
                svp.setCurrentItem(1);
                break;
        }
    }


    @OnClick({R.id.tv_count, R.id.btn_pay, R.id.rl_all, R.id.ll_recharge})
    public void onClick(View view) {
        int currentItem = svp.getCurrentItem();
        GiftFragment fragment = (GiftFragment) fragmentList.get(currentItem);
        GiftModel giftModel = fragment.getmData();
        switch (view.getId()) {
            case R.id.tv_count:
                if (currentItem == 1 && giftModel == null) {
                    ToastUtils.showShort("请选择礼物");
                    return;
                }
                if (currentItem == 1) {
                    MvpPre.getGiftNumBeanData(giftModel);
                } else {
                    MvpPre.getGiftNumBeanData(null);
                }
                break;
            case R.id.btn_pay:
                if (giftModel == null) {
                    ToastUtils.showShort("请选择礼物");
                    return;
                }
                int count = giftUserAdapter.getSelectCount();
                if (count <= 0) {
                    ToastUtils.showShort("请选择打赏对象");
                    return;
                }
                String userId = giftUserAdapter.getUserIdToString();
                String pit = giftUserAdapter.getUserPitToString();
                String num = tvCount.getText().toString();
                if (TextUtils.isEmpty(num)) {
                    ToastUtils.showShort("请选择打赏礼物数量");
                    return;
                }
                if (Integer.valueOf(num) <= 0) {
                    ToastUtils.showShort("请选择打赏礼物数量");
                    return;
                }
                if (currentItem == 0) {
                    //礼物打赏
                    MvpPre.giveGift(userId, giftModel.getId(), roomId, pit, num, giftModel, 0);
                } else {
                    //背包礼物打赏
                    MvpPre.giveBackGift(userId, giftModel.getGift_id(), roomId, pit, num, giftModel, 1);
                }
                break;
            case R.id.rl_all:
                if (all) {
                    giftUserAdapter.allElection(false);
                    tvAll.setTextColor(Color.parseColor("#333333"));
                    tvAll.setBackgroundResource(R.drawable.bg_gift_user_unselect);
                } else {
                    giftUserAdapter.allElection(true);
                    tvAll.setTextColor(Color.parseColor("#FFFFFF"));
                    tvAll.setBackgroundResource(R.drawable.bg_gift_user_select);
                }
                all = !all;
                break;
            case R.id.ll_recharge:
                startActivity(new Intent(mContext, BalanceActivity.class));
                break;
        }
    }


    @Override
    public void giveGiftSuccess(GiftModel giftModel, String num, int type) {
        MvpPre.getBalance();
        EventBus.getDefault().post(new GiftEvent());
//        String giftId;
//        if (type == 0) {
//            giftId = giftModel.getId();
//        } else {
//            giftId = giftModel.getGift_id();
//        }
//        List<RoomPitUserModel> giftUser = giftUserAdapter.getGiftUser();
//        for (RoomPitUserModel item : giftUser) {
//            String pitNum = "";
//            if (!item.getPit_number().equals("其他")) {
//                pitNum = item.getPit_number();
//            }
//            mRoomFragmentListener.sendGiftMessage(item.getNickname(), giftId, giftModel.getPicture(), giftModel.getName(), giftModel.getPrice(), giftModel.getSpecial(), num, pitNum);
//            if (!TextUtils.isEmpty(pitNum)) {
//                GiftBean giftBean = new GiftBean();
//                giftBean.setGift_id(giftModel.getGift_id());
//                giftBean.setId(giftId);
//                giftBean.setPicture(giftModel.getPicture());
//                giftBean.setName(giftModel.getName());
//                giftBean.setPrice(giftModel.getPrice());
//                giftBean.setSpecial(giftModel.getSpecial());
//                giftBean.setPits(pitNum);
//                EventBus.getDefault().post(giftBean);
//            }
//        }
    }

    @Override
    public void setGiftNumBeanData(List<GiftNumBean> data) {
        if (mSelectGiftNumPopupWindow == null) {
            mSelectGiftNumPopupWindow = new SelectGiftNumPopupWindow(mContext, new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    mSelectGiftNumPopupWindow.dismiss();
                    GiftNumBean giftNumBean = (GiftNumBean) adapter.getItem(position);
                    tvCount.setText(giftNumBean.getNum());
                }
            });
        }
        mSelectGiftNumPopupWindow.setData(data);
        mSelectGiftNumPopupWindow.showAtLocation(tvCount, Gravity.BOTTOM | Gravity.RIGHT, 50, 150);
    }


    @Override
    public void setRoomPitUser(List<RoomPitUserModel> data) {
        giftUserAdapter.setNewData(data);
        if (data.size() == 1 && !TextUtils.isEmpty(userId)) {
            giftUserAdapter.allElection(true);
            tvAll.setTextColor(Color.parseColor("#FFFFFF"));
            tvAll.setBackgroundResource(R.drawable.bg_gift_user_select);
        }
    }


}
