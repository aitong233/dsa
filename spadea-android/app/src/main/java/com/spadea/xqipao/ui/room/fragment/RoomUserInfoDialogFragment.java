package com.spadea.xqipao.ui.room.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hyphenate.easeui.utils.view.GradeView;
import com.hyphenate.easeui.utils.view.JueView;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.ui.fragment0.roomdetail.reprot.ReportActivity;
import com.spadea.yuyin.util.utilcode.ConvertUtils;
import com.spadea.yuyin.util.utilcode.SpanUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.RoomUserInfo;
import com.spadea.xqipao.ui.room.presenter.RoomUserInfoPresenter;
import com.spadea.xqipao.utils.dialog.room.CountDownChooseDialog;
import com.spadea.xqipao.utils.view.DecorationHeadView;
import com.qpyy.libcommon.bean.RankInfo;
import com.spadea.xqipao.ui.base.view.BaseDialogFragment;
import com.spadea.xqipao.ui.room.contacts.RoomUserInfoContacts;

import butterknife.BindView;
import butterknife.OnClick;

public class RoomUserInfoDialogFragment extends BaseDialogFragment<RoomUserInfoPresenter> implements RoomUserInfoContacts.View {


    @BindView(R.id.tv_report)
    TextView tvReport;
    @BindView(R.id.dhv)
    DecorationHeadView dhv;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.ll_sex)
    LinearLayout llSex;
    @BindView(R.id.view_grade)
    GradeView viewGrade;
    @BindView(R.id.view_jue)
    JueView viewJue;
    @BindView(R.id.rl_data)
    RelativeLayout rlData;
    @BindView(R.id.iv_follow)
    ImageView ivFollow;
    @BindView(R.id.rl_rollow)
    RelativeLayout rlRollow;
    @BindView(R.id.rl_private)
    RelativeLayout rlPrivate;
    @BindView(R.id.rl_giving_gifts)
    RelativeLayout rlGivingGifts;
    @BindView(R.id.rl_down_wheat)
    RelativeLayout rlDownWheat;
    @BindView(R.id.iv_wheat_ban)
    ImageView ivWheatBan;
    @BindView(R.id.rl_wheat_bean)
    RelativeLayout rlWheatBean;
    @BindView(R.id.iv_banned)
    ImageView ivBanned;
    @BindView(R.id.rl_no_msg)
    RelativeLayout rlNoMsg;
    @BindView(R.id.rl_kick_out)
    RelativeLayout rlKickOut;
    @BindView(R.id.ll_admin)
    LinearLayout llAdmin;
    @BindView(R.id.iv_wheat)
    ImageView ivWheat;
    @BindView(R.id.ll)
    LinearLayout linearLayout;
    @BindView(R.id.iv_data)
    ImageView ivData;


    private String mRoomId;
    private String mUserId;
    private boolean mJurisdiction = false;
    private RoomUserInfo mRoomUserInfo;
    private RoomFragmentListener mRoomFragmentListener;

    public static RoomUserInfoDialogFragment newInstance(String roomId, String userId, boolean jurisdiction) {
        RoomUserInfoDialogFragment roomUserInfoDialogFragment = new RoomUserInfoDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomId", roomId);
        bundle.putString("userId", userId);
        bundle.putBoolean("jurisdiction", jurisdiction);
        roomUserInfoDialogFragment.setArguments(bundle);
        return roomUserInfoDialogFragment;
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
        MvpPre.getRoomUserInfo(mRoomId, mUserId);
    }

    @Override
    protected void initView(View rootView) {
        mRoomId = getArguments().getString("roomId");
        mUserId = getArguments().getString("userId");
        mJurisdiction = getArguments().getBoolean("jurisdiction", false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_roomuser_info;
    }

    @Override
    protected RoomUserInfoPresenter bindPresenter() {
        return new RoomUserInfoPresenter(this, mContext);
    }


    @OnClick({R.id.dhv, R.id.rl_data, R.id.tv_report, R.id.rl_rollow, R.id.rl_down_wheat, R.id.rl_wheat_bean, R.id.rl_no_msg, R.id.rl_giving_gifts, R.id.rl_kick_out, R.id.rl_private})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dhv:
                ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", mUserId).navigation(getActivity(), 0);
                break;
            case R.id.rl_data:
                if (mJurisdiction) {
                    new CountDownChooseDialog(getActivity(), mRoomUserInfo.getRoom_info().getPit_number()).show();
                } else {
                    ARouter.getInstance().build(ARouteConstants.USER_DETAILS).withString("userId", mUserId).navigation(getActivity(), 0);
                }
                this.dismiss();
                break;
            case R.id.tv_report:
                Intent intent = new Intent(getActivity(), ReportActivity.class);
                intent.putExtra("user_id", mUserId);
                startActivityForResult(intent, 0);
                break;
            case R.id.rl_rollow:
                if (mRoomUserInfo != null) {
                    if (mRoomUserInfo.getFollow() == 1) {
                        MvpPre.followUser(mUserId, 2);
                    } else {
                        MvpPre.followUser(mUserId, 1);
                    }
                }
                break;
            case R.id.rl_down_wheat:
                if (mRoomUserInfo != null) {
                    if (mRoomUserInfo.getRoom_info().getPit_number() == 0) {
                        mRoomFragmentListener.getOnWheat(mUserId);
                        this.dismiss();
                    } else {
                        MvpPre.downUserWheat(mUserId, mRoomId, mRoomUserInfo.getNickname(), String.valueOf(mRoomUserInfo.getRoom_info().getPit_number()));
                    }
                }
                break;
            case R.id.rl_wheat_bean:
                if (mRoomUserInfo != null) {
                    if (mRoomUserInfo.getRoom_info().getShutup() == 1) {
                        MvpPre.roomUserShutUp(mRoomId, mUserId, 2, mRoomUserInfo.getNickname());
                    } else {
                        MvpPre.roomUserShutUp(mRoomId, mUserId, 1, mRoomUserInfo.getNickname());
                    }
                }
                break;
            case R.id.rl_no_msg:
                if (mRoomUserInfo != null) {
                    if (mRoomUserInfo.getRoom_info().getBanned() == 1) {
                        MvpPre.setRoomBanned(mRoomId, mUserId, 2, mRoomUserInfo.getNickname());
                    } else {
                        MvpPre.setRoomBanned(mRoomId, mUserId, 1, mRoomUserInfo.getNickname());
                    }
                }
                break;
            case R.id.rl_giving_gifts:
                if (mRoomUserInfo != null) {
                    mRoomFragmentListener.givingGifts(mUserId);
                }
                this.dismiss();
                break;
            case R.id.rl_kick_out:
                if (mRoomUserInfo != null) {
                    MvpPre.kickOut(mUserId, mRoomId, mRoomUserInfo.getNickname());
                }
                break;
            case R.id.rl_private:
                if (mRoomUserInfo != null) {
                    if (mRoomUserInfo.getOnly_friend() == 1) {
                        ToastUtils.showShort("对方设置了好友才能发消息");
                        return;
                    }
                    if (MyApplication.getInstance().notSelf(mRoomUserInfo.getUser_id())) {
                        ARouter.getInstance().build(ARouteConstants.HOME_CHART)
                                .withString("userId", mRoomUserInfo.getEmchat_username())
                                .withString("nickname", mRoomUserInfo.getNickname())
                                .withString("avatar", mRoomUserInfo.getHead_picture())
                                .navigation();
                    } else {
                        ToastUtils.showShort("不能私信自己");
                    }
                }
                break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        window.setGravity(Gravity.CENTER);
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setCancelable(true);
    }

    @Override
    public void setRoomUserInfoData(RoomUserInfo data) {
        this.mRoomUserInfo = data;
        RankInfo rankInfo = data.getRank_info();
        if (data.getSex().equals("1")) {
            ivSex.setImageResource(R.drawable.bang_icon_man);
            llSex.getBackground().setLevel(1);
        } else {
            ivSex.setImageResource(R.drawable.bang_icon_women);
            llSex.getBackground().setLevel(2);
        }
        if (data.getFollow() == 0) {
            ivFollow.setImageLevel(0);
        } else {
            ivFollow.setImageLevel(1);
        }

        tvAge.setText(String.valueOf(data.getAge()));
        SpanUtils nameSpan = new SpanUtils();
        nameSpan.append(data.getNickname());
        if (data.getRole() == 5) {
            nameSpan.appendSpace(ConvertUtils.dp2px(12)).appendImage(R.mipmap.ic_official_user_info, SpanUtils.ALIGN_CENTER);
        }
        if (data.getUser_is_new() == 1) {
            nameSpan.appendSpace(ConvertUtils.dp2px(12)).appendImage(R.mipmap.ic_user_new, SpanUtils.ALIGN_CENTER);
        }
        tvNickname.setText(nameSpan.create());
        tvId.setText("ID: " + data.getUser_code());
        viewGrade.setGrade(rankInfo.getRank_id(), rankInfo.getRank_name());
        viewJue.setJue(rankInfo.getNobility_id(), rankInfo.getNobility_name());
        dhv.setData(data.getHead_picture(), rankInfo.getPicture());
        if (mJurisdiction) {
            RoomUserInfo.RoomInfoBean roomInfo = data.getRoom_info();
            ivData.setImageResource(R.mipmap.ic_room_user_info_count_down);
            llAdmin.setVisibility(View.VISIBLE);
            if (roomInfo.getPit_number() == 0) {
                ivWheat.setImageLevel(1);
            } else {
                ivWheat.setImageLevel(0);
            }
            if (roomInfo.getShutup() == 1) {
                ivWheatBan.setImageLevel(1);
            } else {
                ivWheatBan.setImageLevel(0);
            }
            if (roomInfo.getBanned() == 1) {
                ivBanned.setImageLevel(1);
            } else {
                ivBanned.setImageLevel(0);
            }
        } else {
            llAdmin.setVisibility(View.GONE);
            ivData.setImageResource(R.mipmap.icon_data);
        }
        linearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void roomUserInfoFail() {
        dismiss();
    }

    @Override
    public void followUserSuccess(int type) {
        if (type == 1) {
            ToastUtils.showShort("关注成功");
        } else {
            ToastUtils.showShort("取消关注成功");
        }
        this.dismiss();
    }

    @Override
    public void downUserWheatSuccess(String userName, String pitNumber) {
        ToastUtils.showShort("抱下麦成功");
//        mRoomFragmentListener.downUserWheat(userName, pitNumber);
        this.dismiss();
    }

    /**
     * 禁麦成功
     *
     * @param type
     * @param userName
     */
    @Override
    public void roomUserShutUp(int type, String userName) {
        mRoomFragmentListener.shutUp(String.valueOf(type), userName, mUserId);
        this.dismiss();
    }


    /**
     * 踢出房间
     *
     * @param userName
     */
    @Override
    public void kickOutSuccess(String userName) {
        this.dismiss();
    }

    /**
     * 禁言
     *
     * @param userName
     * @param type
     */
    @Override
    public void setRoomBannedSuccess(String userName, int type) {
        if (type == 1) {
            mRoomFragmentListener.addRoomBanned(mUserId, userName);
        } else {
            mRoomFragmentListener.cancelRoomBanned(mUserId, userName);
        }
        this.dismiss();
    }

}
