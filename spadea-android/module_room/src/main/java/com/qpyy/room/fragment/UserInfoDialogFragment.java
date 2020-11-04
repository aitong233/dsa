package com.qpyy.room.fragment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ConvertUtils;
import com.hjq.toast.ToastUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.bean.TransferUserModel;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.RoomInputEvent;
import com.qpyy.room.bean.RoomUserInfoResp;
import com.qpyy.room.contacts.RoomUserInfoContacts;
import com.qpyy.room.dialog.CountDownChooseDialog;
import com.qpyy.room.presenter.RoomUserInfoPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/8/4 17:50
 * 描述 实力用户
 */
public class UserInfoDialogFragment extends BaseMvpDialogFragment<RoomUserInfoPresenter> implements RoomUserInfoContacts.View {


    @BindView(R2.id.iv_dialog_user_war)
    ImageView ivDialogUserWar;
    @BindView(R2.id.tv_dialog_user_name)
    TextView tvDialogUserName;
    @BindView(R2.id.iv_dialog_user_label)
    ImageView ivDialogUserLabel;
    @BindView(R2.id.tv_dialog_user_id)
    TextView tvDialogUserId;
    @BindView(R2.id.cl_11)
    ConstraintLayout cl11;
    @BindView(R2.id.iv_dialog_user_grade_label)
    ImageView ivDialogUserGradeLabel;
    @BindView(R2.id.iv_user_alter_icon)
    ImageView ivUserAlterIcon;
    @BindView(R2.id.tv_dialog_user_alter)
    TextView tvDialogUserAlter;
    @BindView(R2.id.iv_user_alter_follow)
    ImageView ivUserAlterFollow;
    @BindView(R2.id.tv_dialog_user_follow)
    TextView tvDialogUserFollow;
    @BindView(R2.id.iv_user_alter_chat)
    ImageView ivUserAlterChat;
    @BindView(R2.id.tv_dialog_user_chat)
    TextView tvDialogUserChat;
    @BindView(R2.id.iv_user_alter_gifts)
    ImageView ivUserAlterGifts;
    @BindView(R2.id.tv_dialog_user_gifts)
    TextView tvDialogUserGifts;
    @BindView(R2.id.cl_dialog_user_bg)
    ConstraintLayout clDialogUserBg;
    @BindView(R2.id.riv_dialog_user_pic)
    RoundedImageView rivDialogUserPic;
    @BindView(R2.id.iv_user_ban_wheat_icon)
    ImageView ivUserBanWheatIcon;
    @BindView(R2.id.tv_dialog_user_ban_wheat)
    TextView tvDialogUserBanWheat;
    @BindView(R2.id.iv_user_ban_chat_icon)
    ImageView ivUserBanChatIcon;
    @BindView(R2.id.tv_dialog_user_ban_chat)
    TextView tvDialogUserBanChat;
    @BindView(R2.id.iv_user_kick_icon)
    ImageView ivUserKickIcon;
    @BindView(R2.id.tv_dialog_user_kick)
    TextView tvDialogUserKick;
    @BindView(R2.id.iv_user_time_icon)
    ImageView ivUserTimeIcon;
    @BindView(R2.id.tv_dialog_user_time)
    TextView tvDialogUserTime;
    @BindView(R2.id.ll_operation_user)
    LinearLayout llOperationUser;
    @BindView(R2.id.iv_dialog_clear_love_values_icon)
    ImageView ivDialogClearLoveValuesIcon;
    @BindView(R2.id.tv_dialog_clear_love_values)
    TextView tvDialogClearLoveValues;
    @BindView(R2.id.ll_dialog_clear_love_values)
    LinearLayout llDialogClearLoveValues;
    @BindView(R2.id.cl_parent)
    ConstraintLayout clParent;
    @BindView(R2.id.ll_alter)
    LinearLayout llAlter;
    @BindView(R2.id.ll_follow)
    LinearLayout llFollow;
    @BindView(R2.id.ll_chat)
    LinearLayout llChat;
    @BindView(R2.id.ll_gifts)
    LinearLayout llGifts;
    @BindView(R2.id.ll_ban_wheat)
    LinearLayout llBanWheat;
    @BindView(R2.id.ll_ban_chat)
    LinearLayout llBanChat;
    @BindView(R2.id.ll_kick)
    LinearLayout llKick;
    @BindView(R2.id.ll_time)
    LinearLayout llTime;
    @BindView(R2.id.tv_dialog_user_wheat_ap)
    TextView tvDialogUserWheatAp;
    @BindView(R2.id.iv_user_wheat_ap)
    ImageView ivUserWheatAp;
    @BindView(R2.id.iv_hongbao)
    ImageView ivHongbao;
    @BindView(R2.id.ll_wheat)
    LinearLayout llWheat;

    private String mRoomId;
    private String mUserId;
    private String shutup;
    //爵位等级
    private int GRADE1 = 1;//子爵
    private int GRADE2 = 2;//伯爵
    private int GRADE3 = 3;//侯爵
    private int GRADE4 = 4;//公爵
    private int GRADE5 = 5;//王爵
    private int GRADE6 = 6;//皇帝
    private boolean isControlPower = false;//是否拥有控制权
    private RoomUserInfoResp mRoomUserInfo;


    public static UserInfoDialogFragment newInstance(String roomId, String userId, boolean isControlPower, String shutup) {//isControlPower是否显示下面两个布局
        Bundle args = new Bundle();
        args.putString("roomId", roomId);
        args.putString("userId", userId);
        args.putString("shutup", shutup);
        args.putBoolean("isControlPower", isControlPower);
        UserInfoDialogFragment fragment = new UserInfoDialogFragment();
        fragment.setArguments(args);
        Log.d("StrengthUserDialog", "(Start)启动了===========================UserInfoDialogFragment");
        return fragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        mRoomId = arguments.getString("roomId");
        mUserId = arguments.getString("userId");
        shutup = arguments.getString("shutup");
        isControlPower = arguments.getBoolean("isControlPower");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void initView() {
//        initLayout();
        boolean transferStatus = SpUtils.getTransferStatus();
        if (!transferStatus) {
            ivHongbao.setVisibility(View.GONE);
        }

    }

    @Override
    public void initData() {
        MvpPre.getRoomUserInfo(mRoomId, mUserId);
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
    protected int getLayoutId() {
        return R.layout.room_dialog_user_info;
    }

    @Override
    protected RoomUserInfoPresenter bindPresenter() {
        return new RoomUserInfoPresenter(this, getContext());
    }


    @OnClick({R2.id.iv_dialog_user_war, R2.id.ll_alter, R2.id.ll_follow, R2.id.ll_chat, R2.id.ll_gifts,
            R2.id.ll_ban_wheat, R2.id.ll_ban_chat, R2.id.ll_kick, R2.id.ll_time,
            R2.id.riv_dialog_user_pic, R2.id.ll_wheat, R2.id.ll_love_clear, R2.id.iv_hongbao})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_dialog_user_war) {//警告
            ARouter.getInstance().build(ARouteConstants.CHAT_REPORT).withString("userId", mUserId).navigation();
            dismiss();
        } else if (id == R.id.riv_dialog_user_pic) {//头像
            ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", mUserId).navigation();
            dismiss();
        } else if (id == R.id.ll_alter) {//@
            String text = String.format("@%s", mRoomUserInfo.getNickname());
            EventBus.getDefault().post(new RoomInputEvent(text));
//            MvpPre.sendTxtMessage(mUserId, "1", text, mRoomId);
        } else if (id == R.id.ll_follow) {//关注
            if (mRoomUserInfo != null) {
                if (mRoomUserInfo.getFollow() == 1) {
                    MvpPre.followUser(mUserId, 2);
                } else {
                    MvpPre.followUser(mUserId, 1);
                }
            }
        } else if (id == R.id.ll_chat) {//私信
            DialogFragment dialogFragment = (DialogFragment) ARouter.getInstance().build(ARouteConstants.ROOM_CHART).withString("userId", mRoomUserInfo.getEmchat_username()).withString("nickname", mRoomUserInfo.getNickname()).withString("avatar", mRoomUserInfo.getHead_picture()).navigation();
            dialogFragment.show(getFragmentManager(), "RoomChatFragment");
            dismiss();
        } else if (id == R.id.ll_gifts) {//礼物
            if (mRoomUserInfo != null) {
                RoomGiftDialogFragment.newInstance(mRoomUserInfo.getUser_id(), mRoomId).show(getFragmentManager());
            }
            this.dismiss();
        } else if (id == R.id.ll_ban_wheat) {//禁麦
            if (mRoomUserInfo != null) {
                LogUtils.e("shutup", mRoomUserInfo.getShutup());
                if (mRoomUserInfo.getShutup() == 1) {
                    MvpPre.roomUserShutUp(mRoomId, mRoomUserInfo.getPit_number(), 2, mRoomUserInfo.getNickname());
                } else {
                    MvpPre.roomUserShutUp(mRoomId, mRoomUserInfo.getPit_number(), 1, mRoomUserInfo.getNickname());
                }
            }
        } else if (id == R.id.ll_ban_chat) {//禁言
            if (mRoomUserInfo != null) {
                if (mRoomUserInfo.getBanned() == 1) {
                    MvpPre.setRoomBanned(mRoomId, mUserId, 2, mRoomUserInfo.getNickname());
                } else {
                    MvpPre.setRoomBanned(mRoomId, mUserId, 1, mRoomUserInfo.getNickname());
                }
            }
        } else if (id == R.id.ll_kick) {//踢出
            if (mRoomUserInfo != null) {
                MvpPre.kickOut(mUserId, mRoomId, mRoomUserInfo.getNickname());
            }
        } else if (id == R.id.ll_time) {//计时
            if (mRoomUserInfo != null) {
                CountDownChooseDialog.newInstance(mRoomId, mRoomUserInfo.getPit_number()).show(getFragmentManager());
//                    new CountDownChooseDialog(getSelfActivity(), mRoomUserInfo.getRoom_info().getPit_number()).show();
            } else {
                ToastUtils.show("网络状况不佳");
            }
            dismiss();
        } else if (id == R.id.ll_wheat) {//抱麦/下麦
            if ("0".equals(mRoomUserInfo.getPit_number())) {//抱麦
                MvpPre.putOnWheat(mRoomId, mUserId);
            } else {
                MvpPre.downUserWheat(mUserId, mRoomId, mRoomUserInfo.getNickname(), mRoomUserInfo.getPit_number());
            }

        } else if (id == R.id.ll_love_clear) {
            MvpPre.clearCardiac(mRoomId, mRoomUserInfo.getPit_number());
        } else if (id == R.id.iv_hongbao) {
            TransferUserModel transferUserModel = new TransferUserModel();
            transferUserModel.setHead_picture(mRoomUserInfo.getHead_picture());
            transferUserModel.setNickname(mRoomUserInfo.getNickname());
            transferUserModel.setUser_code(mRoomUserInfo.getUser_code());
            transferUserModel.setUser_id(mRoomUserInfo.getUser_id());
            ARouter.getInstance().build(ARouteConstants.ME_TRANSFER).withSerializable("transferUser", transferUserModel).navigation();
        }
    }

    /**
     * 初始化布局和界面等级样式
     */
    private void initLayout() {
        //是否拥有控制权（禁麦/禁言/提出/计时） RE: initView()方法优先执行
        if (isControlPower && mRoomUserInfo.getAllow_manager()) {
            llDialogClearLoveValues.setVisibility(View.VISIBLE);
            llOperationUser.setVisibility(View.VISIBLE);
        } else {
            llDialogClearLoveValues.setVisibility(View.GONE);
            llOperationUser.setVisibility(View.GONE);
        }
    }

    /**
     * 修改drawable 的颜色
     */
    private Drawable wrappedDrawable(Drawable drawable, ColorStateList colors) {
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    /**
     * 初始化数据
     *
     * @param data
     */
    @Override
    public void setRoomUserInfoData(RoomUserInfoResp data) {
        mRoomUserInfo = data;
        mRoomUserInfo.setShutup(Integer.parseInt(shutup));
        tvDialogUserName.setText(data.getNickname());//name
        tvDialogUserId.setText("ID: " + data.getUser_code());//id
        if (!"1".equals(data.getGood_number())) {
            tvDialogUserId.setCompoundDrawables(null, null, null, null);
        }
        if (mRoomUserInfo != null) {
            if (mRoomUserInfo.getFollow() == 1) {
                tvDialogUserFollow.setText("已关注");
                ivUserAlterFollow.setVisibility(View.GONE);
            } else {
                tvDialogUserFollow.setText("关注");
                ivUserAlterFollow.setVisibility(View.VISIBLE);
            }
        }
        if (TextUtils.isEmpty(data.getRank_icon())) {
            ivDialogUserLabel.setVisibility(View.GONE);
        } else {
            ImageUtils.loadImageView(data.getRank_icon(), ivDialogUserLabel);
        }
        ImageUtils.loadHeadCC(data.getHead_picture(), rivDialogUserPic);//头像
        if (!TextUtils.isEmpty(data.getNobility_picture())) {
            ivDialogUserGradeLabel.setVisibility(View.VISIBLE);
            ImageUtils.loadImageView(data.getNobility_picture(), ivDialogUserGradeLabel);
        }
        clParent.setVisibility(View.VISIBLE);
        RoomUserInfoResp.NobilitySetBean setBean = data.getNobility_set();
        int textColor = Color.parseColor(setBean.getTxt_color());
        if (setBean != null && !TextUtils.isEmpty(data.getNobility_id()) && !"0".equals(data.getNobility_id())) {
            GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Color.parseColor(setBean.getColor_from()), Color.parseColor(setBean.getColor_to())});
            drawable.setCornerRadii(new float[]{ConvertUtils.dp2px(20), ConvertUtils.dp2px(20), ConvertUtils.dp2px(20), ConvertUtils.dp2px(20), 0, 0, 0, 0});
            clDialogUserBg.setBackground(drawable);
            //清空心动值
            GradientDrawable charmDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor(setBean.getColor_from()), Color.parseColor(setBean.getColor_to())});
            llDialogClearLoveValues.setBackground(charmDrawable);

            tvDialogUserName.setTextColor(textColor);
            ivDialogUserWar.setColorFilter(textColor);
            tvDialogUserAlter.setTextColor(textColor);
            ivUserAlterIcon.setColorFilter(textColor);
            tvDialogUserFollow.setTextColor(textColor);
            ivUserAlterFollow.setColorFilter(textColor);
            tvDialogUserChat.setTextColor(textColor);
            ivUserAlterChat.setColorFilter(textColor);
            tvDialogUserGifts.setTextColor(textColor);
            ivUserAlterGifts.setColorFilter(textColor);
            tvDialogUserBanWheat.setTextColor(textColor);
            ivUserBanWheatIcon.setColorFilter(textColor);
            tvDialogUserBanChat.setTextColor(textColor);
            ivUserBanChatIcon.setColorFilter(textColor);
            tvDialogUserKick.setTextColor(textColor);
            ivUserKickIcon.setColorFilter(textColor);
            tvDialogUserTime.setTextColor(textColor);
            ivUserTimeIcon.setColorFilter(textColor);
            ivDialogClearLoveValuesIcon.setColorFilter(textColor);
            tvDialogClearLoveValues.setTextColor(textColor);
            tvDialogUserWheatAp.setTextColor(textColor);
            ivUserWheatAp.setColorFilter(textColor);

            GradientDrawable idDrawable = new GradientDrawable();
            idDrawable.setColor(textColor);
            idDrawable.setAlpha(26);
            idDrawable.setCornerRadius(ConvertUtils.dp2px(3));
            tvDialogUserId.setBackground(idDrawable);
            rivDialogUserPic.setBorderColor(Color.parseColor(setBean.getHead_color()));
            llOperationUser.setBackgroundResource(R.color.white);//有爵位统一设置成白色
        } else {//没有爵位

        }
        //在麦位且是管理才显示清空心动值和计时
        if (isControlPower && !"0".equals(data.getPit_number())) {
            llDialogClearLoveValues.setVisibility(View.VISIBLE);
        } else {
            llDialogClearLoveValues.setVisibility(View.GONE);
        }
        //抱麦 还是上麦
        //在麦位
        if ("0".equals(data.getPit_number())) {//抱麦
            tvDialogUserWheatAp.setText("抱麦");
            ivUserWheatAp.setImageResource(R.mipmap.room_dialog_user_wheat_ap);
        } else {
            tvDialogUserWheatAp.setText("下麦");
            ivUserWheatAp.setImageResource(R.mipmap.room_dialog_user_wheat_ap2);
        }
        //是否拥有控制权（禁麦/禁言/提出/计时） RE: initView()方法优先执行
        if (isControlPower && mRoomUserInfo.getAllow_manager()) {
            llDialogClearLoveValues.setVisibility(View.VISIBLE);
            llOperationUser.setVisibility(View.VISIBLE);
        } else {
            llDialogClearLoveValues.setVisibility(View.GONE);
            llOperationUser.setVisibility(View.GONE);
        }
    }

    @Override
    public void roomUserInfoFail() {
        ToastUtils.show("用户信息获取失败");
    }

    @Override
    public void followUserSuccess(int type) {
        if (type == 1) {
            ToastUtils.show("关注成功");
        } else {
            ToastUtils.show("取消关注成功");
        }
        this.dismiss();
    }

    /**
     * 抱麦成功
     *
     * @param userName
     * @param pitNumber
     */
    @Override
    public void downUserWheatSuccess(String userName, String pitNumber) {
        ToastUtils.show("抱下麦成功");
        this.dismiss();
    }

    /**
     * 禁麦
     *
     * @param type
     * @param userName
     */
    @Override
    public void roomUserShutUp(int type, String userName) {
        this.dismiss();
    }

    /**
     * 踢出成功
     *
     * @param userName
     */
    @Override
    public void kickOutSuccess(String userName) {
        this.dismiss();
    }

    /**
     * 禁言成功
     *
     * @param userName
     * @param type
     */
    @Override
    public void setRoomBannedSuccess(String userName, int type) {
        this.dismiss();
    }

    @Override
    public void dismissDialog() {
        dismiss();
    }

    public void show(FragmentManager childFragmentManager) {
        show(childFragmentManager, "UserInfoDialogFragment");
    }

}
