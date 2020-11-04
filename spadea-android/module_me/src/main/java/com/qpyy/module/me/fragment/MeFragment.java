package com.qpyy.module.me.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.utils.ImageLoader;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.libcommon.widget.AgeView;
import com.qpyy.libcommon.widget.DecorationHeadView;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.bean.MyInfoResp;
import com.qpyy.module.me.contacts.MeConacts;
import com.qpyy.module.me.presenter.MePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MeFragment extends BaseMvpFragment<MePresenter> implements MeConacts.View {


    @BindView(R2.id.riv_user_head)
    DecorationHeadView rivUserHead;
    @BindView(R2.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R2.id.iv_nobility)
    ImageView ivNobility;
    @BindView(R2.id.tv_user_id)
    TextView tvUserId;
    @BindView(R2.id.iv_liang)
    ImageView ivLiang;
    @BindView(R2.id.tv_follow)
    TextView tvFollow;
    @BindView(R2.id.tv_fans)
    TextView tvFans;
    @BindView(R2.id.tv_friends)
    TextView tvFriends;
    @BindView(R2.id.tv_visit)
    TextView tvVisit;
    @BindView(R2.id.rl_my_wallet)
    RelativeLayout llWallet;
    @BindView(R2.id.tv_my_room)
    TextView llRoom;
    @BindView(R2.id.tv_my_lens)
    TextView llLens;
    @BindView(R2.id.iv_level)
    ImageView ivLevel;
    @BindView(R2.id.age_view)
    AgeView ageView;

    private MyInfoResp mMyInfoResp;

    public static MeFragment newInstance(String title) {
        MeFragment fragment = new MeFragment();
        return fragment;
    }


    @Override
    protected MePresenter bindPresenter() {
        return new MePresenter(this, getActivity());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            MvpPre.getMyInfo();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }


    @Override
    public void onResume() {
        super.onResume();
        MvpPre.getMyInfo();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.me_fagment_me;
    }

    @OnClick({R2.id.rl_decoration, R2.id.rl_invite_code, R2.id.rl_nobility, R2.id.rl_auth, R2.id.rl_vip, R2.id.rl_union, R2.id.rl_service, R2.id.rl_help, R2.id.rl_setting,
            R2.id.ll_visit, R2.id.ll_follow, R2.id.ll_fans, R2.id.ll_friends, R2.id.tv_nick_name, R2.id.rl_my_wallet, R2.id.tv_my_room, R2.id.tv_my_lens, R2.id.riv_user_head
    })
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rl_decoration) {
            ARouter.getInstance().build(ARouteConstants.ME_SHOP).navigation();
        } else if (id == R.id.rl_nobility) {
            //我的爵位
            ARouter.getInstance().build(ARouteConstants.ME_GRADEACTIVITY).withInt("type", 1).navigation();
        } else if (id == R.id.rl_vip) {
            //会员中心
            ARouter.getInstance().build(ARouteConstants.ME_GRADEACTIVITY).navigation();
        } else if (id == R.id.rl_auth) {
            //实名认证
            MvpPre.getNameAuthResult(0);
        } else if (id == R.id.rl_union) {
            MvpPre.getGuildInfo();
        } else if (id == R.id.rl_invite_code) {
            if (mMyInfoResp != null) {
                ARouter.getInstance().build(ARouteConstants.MY_INVITE_CODE).withString("userCode", mMyInfoResp.getUser_code()).navigation();
            }
        } else if (id == R.id.rl_service) {
            MvpPre.serviceUser();
        } else if (id == R.id.rl_help) {
            //帮助中心
            ARouter.getInstance().build(ARouteConstants.ME_HELP).navigation();
        } else if (id == R.id.rl_setting) {
            ARouter.getInstance().build(ARouteConstants.ME_SETTING).navigation();
        } else if (id == R.id.ll_visit) {
            ARouter.getInstance().build(ARouteConstants.ME_VISIT).navigation();
        } else if (id == R.id.ll_follow) {
            ARouter.getInstance().build(ARouteConstants.ME_MY_FRIENDS).withInt("type", 1).navigation();
        } else if (id == R.id.ll_fans) {
            ARouter.getInstance().build(ARouteConstants.ME_MY_FRIENDS).withInt("type", 2).navigation();
        } else if (id == R.id.ll_friends) {
            ARouter.getInstance().build(ARouteConstants.ME_MY_FRIENDS).withInt("type", 0).navigation();
        } else if (id == R.id.tv_nick_name || id == R.id.riv_user_head) {
            //我的空间
            if (mMyInfoResp != null) {
                ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", mMyInfoResp.getUser_id()).navigation();
            }
        } else if (id == R.id.tv_my_lens) {
            if (mMyInfoResp != null) {
                ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", mMyInfoResp.getUser_id()).navigation();
            }
        } else if (id == R.id.rl_my_wallet) {
            //我的钱包
            ARouter.getInstance().build(ARouteConstants.ME_WALLETS).navigation();
        } else if (id == R.id.tv_my_room) {
            //我的房间
            if (mMyInfoResp != null) {
                if ("0".equals(mMyInfoResp.getRoom_id())) {
                    ARouter.getInstance().build(ARouteConstants.CREATED_ROOM).navigation();
                } else {
                    ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", mMyInfoResp.getRoom_id()).navigation();
                }
            }
        }
    }

    @Override
    public void myInfoSuccess(MyInfoResp data) {
        this.mMyInfoResp = data;
        SpUtils.saveUserId(data.getUser_id());
        SpUtils.saveTransferStatus(data.getTransfer_state() == 1);
        SpUtils.saveRoomType(data.getSys_type_id());
        tvNickName.setText(data.getNickname());
        tvFollow.setText(data.getFollow_count());
        tvFans.setText(data.getFans_count());
        tvFriends.setText(data.getFriend_count());
        tvVisit.setText(data.getVisit_count());
        tvUserId.setText("ID:" + data.getUser_code());
        ageView.setData(data.getSex(), data.getAge());
        rivUserHead.setData(data.getHead_picture(), data.getRank_info().getPicture(), data.getSex());
        ImageLoader.loadIcon(getActivity(), ivLevel, data.getLevel_icon());
        ImageLoader.loadIcon(getActivity(), ivNobility, data.getNobility_icon());
        ivLiang.setVisibility("1".equals(data.getGood_number()) ? View.VISIBLE : View.GONE);
    }

    @Override
    public void serviceSuccess(String data) {
        try {
            String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=" + data + "&version=1";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
        } catch (Exception e) {
            ToastUtils.show("请先安装QQ");
        }
    }


}
