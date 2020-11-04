package com.qpyy.room.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ConvertUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.DownWheatEvent;
import com.qpyy.room.bean.RoomUserInfoResp;
import com.qpyy.room.contacts.RoomUserInfoContacts;
import com.qpyy.room.presenter.RoomUserInfoPresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/8/17 09:27
 * 描述 describe
 */
public class MyInfoDialogFragment extends BaseMvpDialogFragment<RoomUserInfoPresenter> implements RoomUserInfoContacts.View {

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
    @BindView(R2.id.tv_dialog_down_wheat)
    TextView tvDialogDownWheat;
    @BindView(R2.id.tv_dialog_user_gifts)
    TextView tvDialogUserGifts;
    @BindView(R2.id.cl_dialog_user_bg)
    ConstraintLayout clDialogUserBg;
    @BindView(R2.id.riv_dialog_user_pic)
    RoundedImageView rivDialogUserPic;
    @BindView(R2.id.cl_parent)
    ConstraintLayout clParent;
    @BindView(R2.id.iv_dialog_down_wheat)
    ImageView ivDialogDownWheat;
    @BindView(R2.id.iv_dialog_user_gifts)
    ImageView ivDialogUserGifts;

    private String mRoomId;


    public static MyInfoDialogFragment newInstance(String mRoomId) {
        Bundle args = new Bundle();
        args.putString("mRoomId", mRoomId);
        MyInfoDialogFragment fragment = new MyInfoDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RoomUserInfoPresenter bindPresenter() {
        return new RoomUserInfoPresenter(this, getContext());
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        mRoomId = getArguments().getString("mRoomId", "");
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
    protected void initData() {
        MvpPre.getRoomUserInfo(mRoomId, SpUtils.getUserId());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_dialog_me_info;
    }


    @OnClick({R2.id.tv_dialog_down_wheat, R2.id.tv_dialog_user_gifts, R2.id.riv_dialog_user_pic})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_dialog_down_wheat) {
            //下麦
            EventBus.getDefault().post(new DownWheatEvent());
            this.dismiss();
        } else if (id == R.id.tv_dialog_user_gifts) {
            //送礼
            RoomGiftDialogFragment.newInstance(SpUtils.getUserId(), mRoomId).show(getFragmentManager());
            this.dismiss();
        } else if (id == R.id.riv_dialog_user_pic) {
            ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", SpUtils.getUserId()).navigation();
        }
    }

    /**
     * 获取数据
     *
     * @param data
     */
    @Override
    public void setRoomUserInfoData(RoomUserInfoResp data) {
        if ("0".equals(data.getPit_number())) {
            tvDialogDownWheat.setText("上麦");
        } else {
            tvDialogDownWheat.setText("下麦");
        }
        tvDialogUserName.setText(data.getNickname());//name
        tvDialogUserId.setText("ID: " + data.getUser_code());//id
        if (!"1".equals(data.getGood_number())) {
            tvDialogUserId.setCompoundDrawables(null, null, null, null);
        }
        if (TextUtils.isEmpty(data.getRank_icon())) {
            ivDialogUserLabel.setVisibility(View.GONE);
        } else {
            ImageUtils.loadImageView(data.getRank_icon(), ivDialogUserLabel);
        }
        ImageUtils.loadHeadCC(data.getHead_picture(), rivDialogUserPic);//头像
        clParent.setVisibility(View.VISIBLE);
        ImageUtils.loadImageView(data.getRank_icon(), ivDialogUserLabel);

        //爵位颜色
        RoomUserInfoResp.NobilitySetBean setBean = data.getNobility_set();
        int textColor = Color.parseColor(setBean.getTxt_color());
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Color.parseColor(setBean.getColor_from()), Color.parseColor(setBean.getColor_to())});
        drawable.setCornerRadii(new float[]{ConvertUtils.dp2px(20), ConvertUtils.dp2px(20), ConvertUtils.dp2px(20), ConvertUtils.dp2px(20), 0, 0, 0, 0});
        clDialogUserBg.setBackground(drawable);//背景
        rivDialogUserPic.setBorderColor(Color.parseColor(setBean.getHead_color()));//头像外圈
        tvDialogDownWheat.setTextColor(textColor);//下麦
        ivDialogDownWheat.setColorFilter(textColor);
        tvDialogUserGifts.setTextColor(textColor);//送礼
        ivDialogUserGifts.setColorFilter(textColor);

        GradientDrawable idDrawable = new GradientDrawable();
        idDrawable.setColor(textColor);
        idDrawable.setAlpha(26);
        idDrawable.setCornerRadius(ConvertUtils.dp2px(3));
        tvDialogUserId.setBackground(idDrawable);
        if (!TextUtils.isEmpty(data.getNobility_picture())) {
            ivDialogUserGradeLabel.setVisibility(View.VISIBLE);
            ImageUtils.loadImageView(data.getNobility_picture(), ivDialogUserGradeLabel);//爵位图片
        }

    }

    @Override
    public void roomUserInfoFail() {

    }

    @Override
    public void followUserSuccess(int type) {

    }

    @Override
    public void downUserWheatSuccess(String userName, String pitNumber) {

    }

    @Override
    public void roomUserShutUp(int type, String userName) {

    }

    @Override
    public void kickOutSuccess(String userName) {

    }

    @Override
    public void setRoomBannedSuccess(String userName, int type) {

    }

    @Override
    public void dismissDialog() {
        dismiss();
    }

}
