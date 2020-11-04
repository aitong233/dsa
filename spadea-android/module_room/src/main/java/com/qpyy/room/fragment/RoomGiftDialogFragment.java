package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.hjq.toast.ToastUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.adapter.MyFragmentPagerAdapter;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.bean.GiftModel;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.event.BalanceEvent;
import com.qpyy.libcommon.widget.MarqueeTextView;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.GiftUserAdapter;
import com.qpyy.room.bean.GiftBackResp;
import com.qpyy.room.bean.GiftNumBean;
import com.qpyy.room.bean.RoomPitUserModel;
import com.qpyy.room.contacts.RoomGiftContacts;
import com.qpyy.room.event.GiftUserRefreshEvent;
import com.qpyy.room.presenter.RoomGiftPresenter;
import com.qpyy.room.widget.KeyboardPopupWindow;
import com.qpyy.room.widget.SelectGiftNumPopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/8/6 13:25
 * 描述 describe
 */
public class RoomGiftDialogFragment extends BaseMvpDialogFragment<RoomGiftPresenter> implements RoomGiftContacts.View {


    private static final String TAG = "RoomGiftDialogFragment";
    @BindView(R2.id.sliding_tab_layout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R2.id.tv_one_key_all_give)
    TextView tvOneKeyAllGive;
    @BindView(R2.id.tv_all_wheat)
    TextView tvAllWheat;
    @BindView(R2.id.riv_all_open_wheat_label)
    RoundedImageView rtvAllWheat;
    @BindView(R2.id.rl_pit_one)
    RelativeLayout rlPitOne;
    @BindView(R2.id.rv_gift_user)
    RecyclerView rvGiftUser;
    @BindView(R2.id.svp_gift_list)
    ViewPager svpGiftList;
    @BindView(R2.id.tv_have_coin)
    TextView tvHaveCoin;
    @BindView(R2.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R2.id.tv_give_coin_num)
    MarqueeTextView tvGiveCoinNum;
    @BindView(R2.id.tv_give)
    TextView tvGive;
    @BindView(R2.id.tv_next_box)
    TextView tvNextBox;
    private boolean isOpenNextBox;//是否开放盲盒


    private String roomId;
    private String userId;
    private List<Fragment> fragmentList = new ArrayList<>();
    private SelectGiftNumPopupWindow mSelectGiftNumPopupWindow;
    private KeyboardPopupWindow mKeyboardPopupWindow;
    private GiftUserAdapter giftUserAdapter;
    private boolean all = false;


    public static RoomGiftDialogFragment newInstance(String userId, String roomId) {
        RoomGiftDialogFragment roomGiftFragment = new RoomGiftDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);
        bundle.putString("roomId", roomId);
        roomGiftFragment.setArguments(bundle);
        return roomGiftFragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        userId = arguments.getString("userId");
        roomId = arguments.getString("roomId");
    }

    @Override
    protected void initData() {
        MvpPre.getBalance();
        MvpPre.getRoomPitUser(roomId, userId, false);
        MvpPre.getBoxInfo();
    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0.4f;
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvGiftUser.setLayoutManager(linearLayoutManager);
        rvGiftUser.setAdapter(giftUserAdapter = new GiftUserAdapter());
        String[] title = new String[]{"礼物", "背包"};
        fragmentList.add(GiftFragment.newInstance(0));
        fragmentList.add(GiftFragment.newInstance(1));
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentList, getChildFragmentManager());
        svpGiftList.setAdapter(myFragmentPagerAdapter);
        slidingTabLayout.setViewPager(svpGiftList, title);
        initListener();
    }

    /**
     * 监听
     */
    private void initListener() {
        giftUserAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RoomPitUserModel item = giftUserAdapter.getItem(position);
                if (item.isSelect()) {
                    item.setSelect(false);
                } else {
                    item.setSelect(true);
                }
                if (svpGiftList.getCurrentItem() == 1) {
                    tvOneKeyAllGive.setVisibility(giftUserAdapter.getSelectCount() > 1 ? View.INVISIBLE : View.VISIBLE);//选中了两个以上麦位，一键送礼隐藏
                }
                all = giftUserAdapter.isAll();
                if (all) {
                    rtvAllWheat.setVisibility(View.VISIBLE);
                } else {
                    rtvAllWheat.setVisibility(View.GONE);
                }
                giftUserAdapter.notifyItemChanged(position, item);
            }
        });

        svpGiftList.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                all = false;
                if (i == 1) {
                    tvOneKeyAllGive.setVisibility(View.VISIBLE);
                    MvpPre.getRoomPitUser(roomId, userId, true);
                    MvpPre.userBackPack();
                    tvNextBox.setVisibility(View.GONE);
                } else {
                    tvOneKeyAllGive.setVisibility(View.INVISIBLE);
                    MvpPre.getRoomPitUser(roomId, userId, false);
                    MvpPre.getBalance();
                    if (isOpenNextBox) {
                        tvNextBox.setVisibility(View.VISIBLE);
                    } else {
                        tvNextBox.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                giftUserAdapter.clearAllSelected();
                rtvAllWheat.setVisibility(View.GONE);
            }
        });

    }


    @Override
    public void setBalanceMoney(String money) {
        if (svpGiftList.getCurrentItem() == 0) {
            tvHaveCoin.setText(new SpanUtils().append(money).setBold().create());
            tvRecharge.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setGiftNumBeanData(List<GiftNumBean> data) {
        if (mSelectGiftNumPopupWindow == null) {
            mSelectGiftNumPopupWindow = new SelectGiftNumPopupWindow(getActivity(), new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    GiftNumBean giftNumBean = (GiftNumBean) adapter.getItem(position);
                    if ("0".equals(giftNumBean.getNumber())) {//自定义
                        mKeyboardPopupWindow = new KeyboardPopupWindow(getContext(), getView());
                        mKeyboardPopupWindow.refreshKeyboardOutSideTouchable(false);//false开启键盘 ，true关闭键盘
                        mKeyboardPopupWindow.addRoomPasswordListener(new KeyboardPopupWindow.KeyboardCompleteListener() {//监听自定键盘的完成
                            @Override
                            public void inputComplete(String inputContent) {
                                tvGiveCoinNum.setText(inputContent);
                                mKeyboardPopupWindow.releaseResources();
                            }
                        });
                    } else {
                        tvGiveCoinNum.setText(giftNumBean.getNumber());
                    }
                    mSelectGiftNumPopupWindow.dismiss();
                }
            });
        }
        mSelectGiftNumPopupWindow.setData(data);
        mSelectGiftNumPopupWindow.showAtLocation(tvGiveCoinNum, Gravity.BOTTOM | Gravity.RIGHT, 50, 150);
    }

    @Override
    public void setRoomPitUser(List<RoomPitUserModel> data) {
        for (int i = 0; i < giftUserAdapter.getData().size(); i++) {
            RoomPitUserModel model = giftUserAdapter.getItem(i);
            if (data.size() > i && data.get(i).getUser_id().equals(model.getUser_id())) {
                data.get(i).setSelect(model.isSelect());
            }
        }
        giftUserAdapter.setNewData(data);
        if (data.size() == 1 && !TextUtils.isEmpty(userId)) {
            giftUserAdapter.allElection(true);
            rtvAllWheat.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setNextBoxState(boolean isOpen) {
        this.isOpenNextBox = isOpen;
        if (isOpen) {
            tvNextBox.setVisibility(View.VISIBLE);
        } else {
            tvNextBox.setVisibility(View.GONE);
        }
    }


    @Override
    protected int getLayoutId() {
        Log.d(TAG, "(Start)启动了===========================RoomGiftDialogFragment");
        return R.layout.room_fragment_dialog_room_gift;
    }

    @Override
    protected RoomGiftPresenter bindPresenter() {
        return new RoomGiftPresenter(this, getContext());
    }

    @Override
    public void showLoadings(String content) {

    }


    @OnClick({R2.id.tv_one_key_all_give, R2.id.tv_recharge, R2.id.tv_give_coin_num, R2.id.tv_give, R2.id.tv_all_wheat})
    public void onViewClicked(View view) {
        int currentItem = svpGiftList.getCurrentItem();
        GiftFragment fragment = (GiftFragment) fragmentList.get(currentItem);
        GiftModel giftModel = fragment.getmData();
        int id = view.getId();//一键赠送全麦
        if (id == R.id.tv_one_key_all_give) {
            int count = giftUserAdapter.getSelectCount();
            if (count <= 0) {
                ToastUtils.show("请选择打赏对象");
                return;
            }
            if (count > 1) {
                ToastUtils.show("只能选择单个用户");
                return;
            }
            String userId = giftUserAdapter.getUserIdToString();
            String pit = giftUserAdapter.getUserPitToString();
            MvpPre.giveBackGiftAll(userId, roomId, pit);
        } else if (id == R.id.tv_recharge) {//充值
            DialogFragment navigation = (DialogFragment) ARouter.getInstance().build(ARouteConstants.RECHARGE_DIALOG).navigation();
            navigation.show(getChildFragmentManager(), "RechargeDialogFragment");
        } else if (id == R.id.tv_give_coin_num) {//选择数量
            if (giftModel == null) {
                ToastUtils.show("请选择礼物");
                return;
            }
            MvpPre.getGiftNumBeanData(giftModel);
        } else if (id == R.id.tv_give) {//赠送
            if (giftModel == null) {
                ToastUtils.show("请选择礼物");
                return;
            }
            int count = giftUserAdapter.getSelectCount();
            if (count <= 0) {
                ToastUtils.show("请选择打赏对象");
                return;
            }
            String userId = giftUserAdapter.getUserIdToString();
            String pit = giftUserAdapter.getUserPitToString();
            String num = tvGiveCoinNum.getText().toString();
            if (TextUtils.isEmpty(num)) {
                ToastUtils.show("请选择打赏礼物数量");
                return;
            }
            if (Integer.valueOf(num) <= 0) {
                ToastUtils.show("请选择打赏礼物数量");
                return;
            }
            if (currentItem == 0) {
                //礼物打赏
                MvpPre.giveGift(userId, giftModel.getId(), roomId, pit, num, giftModel, 0);
            } else {
                //背包礼物打赏
                MvpPre.giveBackGift(userId, giftModel.getGift_id(), roomId, pit, num, giftModel, 1);
            }
        } else if (id == R.id.tv_all_wheat) {//全麦
            if (all) {
                giftUserAdapter.allElection(false);
                rtvAllWheat.setVisibility(View.GONE);
            } else {
                giftUserAdapter.allElection(true);
                rtvAllWheat.setVisibility(View.VISIBLE);

            }
            all = !all;
        }
    }

    /**
     * 盲盒说明
     */
    @OnClick(R2.id.tv_next_box)
    public void onNextBox() {
        NextBoxDialogFragment.newInstance().show(getChildFragmentManager(), "NextBoxDialogFragment");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGiftBack(GiftBackResp resp) {
        if (svpGiftList.getCurrentItem() == 1) {
            tvHaveCoin.setText(new SpanUtils().append("总价：").setFontSize(12, true).append(resp.getTotal_price()).setBold().create());
            tvRecharge.setVisibility(View.GONE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void userRefresh(GiftUserRefreshEvent event) {
        if (event.type == 0 && BaseApplication.getIns().showSelf) {
            MvpPre.getRoomPitUser(roomId, userId, event.addSelf);
        }
        if (event.addSelf) {  //是盲盒
            String tvNum = tvGiveCoinNum.getText().toString();
            int num = Integer.parseInt(tvNum);
            if (num > 88) {
                tvGiveCoinNum.setText("88");
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void balanceEvent(BalanceEvent event) {
        MvpPre.getBalance();
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

    public void show(FragmentManager childFragmentManager) {
        show(childFragmentManager, TAG);
    }

}
