package com.spadea.xqipao.ui.room.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.spadea.yuyin.MyApplication;
import com.spadea.xqipao.data.GiftModel;
import com.spadea.xqipao.data.GiftNumBean;
import com.spadea.xqipao.ui.room.presenter.RoomUserGiftPresenter;
import com.spadea.xqipao.utils.popupwindow.SelectGiftNumPopupWindow;
import com.spadea.xqipao.ui.base.view.BaseDialogFragment;
import com.spadea.xqipao.ui.live.adapter.MyFragmentPagerAdapter;
import com.spadea.xqipao.ui.me.activity.BalanceActivity;
import com.spadea.xqipao.ui.room.contacts.RoomUserGiftContacts;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ScreenUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.yuyin.widget.ScrollViewPager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RoomUserGiftFragment extends BaseDialogFragment<RoomUserGiftPresenter> implements RoomUserGiftContacts.View {


    @BindView(R.id.ctl)
    SlidingTabLayout ctl;
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


    private String[] title = new String[]{"礼物", "背包"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private MyFragmentPagerAdapter myFragmentPagerAdapter;


    private SelectGiftNumPopupWindow mSelectGiftNumPopupWindow;
    private List<GiftNumBean> giftNumBeanList = new ArrayList<>();
    private boolean b = false;
    private String roomId;
    private String chatrooms;
    private String userId;
    private String nickName;


    public static RoomUserGiftFragment newInstance(String roomId, String chatrooms, String userId, String nickName) {
        RoomUserGiftFragment roomUserGiftFragment = new RoomUserGiftFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ROOMID", roomId);
        bundle.putString("CHATROOMS", chatrooms);
        bundle.putString("USERID", userId);
        bundle.putString("NICKNAME", nickName);
        roomUserGiftFragment.setArguments(bundle);
        return roomUserGiftFragment;
    }


    @Override
    protected void initData() {
        roomId = getArguments().getString("ROOMID");
        chatrooms = getArguments().getString("CHATROOMS");
        userId = getArguments().getString("USERID");
        nickName = getArguments().getString("NICKNAME");
        giftNumBeanList.clear();
        giftNumBeanList.add(new GiftNumBean("10", "十全十美"));
        giftNumBeanList.add(new GiftNumBean("66", "一切顺利"));
        giftNumBeanList.add(new GiftNumBean("99", "天长地久"));
        giftNumBeanList.add(new GiftNumBean("188", "要抱抱"));
        giftNumBeanList.add(new GiftNumBean("520", "我爱你"));
        giftNumBeanList.add(new GiftNumBean("1314", "一生一世"));
        giftNumBeanList.add(new GiftNumBean("3344", "三生三世"));
        MvpPre.getBalance();

    }

    @Override
    protected void initView(View rootView) {
        fragmentList.clear();
        fragmentList.add(GiftFragment.newInstance(0));
        fragmentList.add(GiftFragment.newInstance(1));
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentList, getChildFragmentManager());
        svp.setAdapter(myFragmentPagerAdapter);
        ctl.setViewPager(svp, title);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_room_user_gift;
    }

    @Override
    protected RoomUserGiftPresenter bindPresenter() {
        return new RoomUserGiftPresenter(this, mContext);
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


    @OnClick({R.id.tv_count, R.id.btn_pay, R.id.tv_money})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_count:
                if (mSelectGiftNumPopupWindow == null) {
                    mSelectGiftNumPopupWindow = new SelectGiftNumPopupWindow(mContext,    new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            GiftNumBean giftNumBean = (GiftNumBean) adapter.getItem(position);
                            tvCount.setText(giftNumBean.getNum());
                        }
                    });
                }
                mSelectGiftNumPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.RIGHT, 50, 150);
                break;
            case R.id.btn_pay:
                int currentItem = svp.getCurrentItem();
                GiftFragment fragment = (GiftFragment) fragmentList.get(currentItem);
                GiftModel giftModel = fragment.getmData();
                if (giftModel == null) {
                    ToastUtils.showShort("请选择礼物");
                    return;
                }
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
                    MvpPre.giveGift(userId, giftModel.getId(), roomId, "", num, giftModel, 0);
                } else {
                    //背包礼物打赏
                    MvpPre.giveBackGift(userId, giftModel.getGift_id(), roomId, "", num, giftModel, 1);
                }
                break;
            case R.id.tv_money:
                startActivity(new Intent(mContext, BalanceActivity.class));
                break;
        }
    }


    @Override
    public void giveGiftSuccess(GiftModel giftModel, String userId, String num, int type) {
        EMMessage txtSendMessage = EMMessage.createTxtSendMessage("送给" + nickName, chatrooms);
        txtSendMessage.setFrom(MyApplication.getInstance().getUser().getEmchat_username());
        txtSendMessage.setChatType(EMMessage.ChatType.ChatRoom);
        txtSendMessage.setAttribute("action", 2);
        if (type == 0) {
            txtSendMessage.setAttribute("gift_id", giftModel.getId());
        } else {
            txtSendMessage.setAttribute("gift_id", giftModel.getGift_id());
        }
        txtSendMessage.setAttribute("gift_pic", giftModel.getPicture());
        txtSendMessage.setAttribute("gift_name", giftModel.getName());
        txtSendMessage.setAttribute("gift_price", giftModel.getPrice());
        txtSendMessage.setAttribute("gift_spectial", giftModel.getSpecial());
        txtSendMessage.setAttribute("gift_num", num);
        txtSendMessage.setAttribute("pits", "");
        txtSendMessage.setAttribute("user_id", MyApplication.getInstance().getUser().getUser_id());
        txtSendMessage.setAttribute("nickname", MyApplication.getInstance().getUser().getNickname());
        txtSendMessage.setAttribute("avatar", MyApplication.getInstance().getUser().getHead_picture());
        txtSendMessage.setAttribute("rank_id", MyApplication.getInstance().getUser().getRank_id());
        txtSendMessage.setAttribute("nobility_name", MyApplication.getInstance().getUser().getNobility_name());
        txtSendMessage.setAttribute("role", MyApplication.getInstance().getUser().getRole());
        EMClient.getInstance().chatManager().sendMessage(txtSendMessage);
        EventBus.getDefault().post(giftModel);
//        if (type == 0) {
//            WebSocketUtils.getInstance().giveGift(giftModel.getId(), userId, num, roomId);
//            WebSocketUtils.getInstance().refresh2(giftModel.getId(), num, userId, roomId);
//        } else {
//            WebSocketUtils.getInstance().giveGift(giftModel.getGift_id(), userId, num, roomId);
//            WebSocketUtils.getInstance().refresh2(giftModel.getGift_id(), num, userId, roomId);
//        }
        this.dismiss();
    }

}
