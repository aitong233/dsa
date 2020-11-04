package com.spadea.xqipao.utils.dialog.room;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.easeui.utils.view.GradeView;
import com.hyphenate.easeui.utils.view.JueView;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.HousePitBean;
import com.qpyy.libcommon.bean.RankInfo;
import com.spadea.xqipao.data.RoomUserInfoModel;
import com.spadea.xqipao.utils.dialog.BaseDialog;
import com.spadea.xqipao.utils.view.DecorationHeadView;

import butterknife.BindView;
import butterknife.OnClick;

public class RoomUserInfoDialog extends BaseDialog {


    @BindView(R.id.tv_report)
    TextView tvReport;

    @BindView(R.id.dhv)
    DecorationHeadView dhv;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.rl_data)
    RelativeLayout rlData;
    @BindView(R.id.rl_rollow)
    RelativeLayout rlRollow;
    @BindView(R.id.rl_private)
    RelativeLayout rlPrivate;
    @BindView(R.id.rl_giving_gifts)
    RelativeLayout rlGivingGifts;
    @BindView(R.id.rl_down_wheat)
    RelativeLayout rlDownWheat;
    @BindView(R.id.rl_wheat_bean)
    RelativeLayout rlWheatBean;
    @BindView(R.id.rl_no_msg)
    RelativeLayout rlNoMsg;
    @BindView(R.id.rl_kick_out)
    RelativeLayout rlKickOut;
    @BindView(R.id.ll_admin)
    LinearLayout llAdmin;
    @BindView(R.id.iv_follow)
    ImageView ivFollow;
    @BindView(R.id.iv_wheat_ban)
    ImageView ivWheatBan;
    @BindView(R.id.iv_banned)
    ImageView ivBanned;
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

    private RoomUserInfoModel mRoomUserInfoModel;
    private HousePitBean mHousePitBean;
    private RoomUserInfoClickListener mRoomUserInfoClickListener;
    private String pitNumber = "0";

    public RoomUserInfoDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_room_user_info;
    }

    @Override
    public void initView() {
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }

    @Override
    public void initData() {

    }

    public void setData(RoomUserInfoModel roomUserInfoModel, HousePitBean housePitBean, int jurisdiction, String pitNumber) {
        this.mRoomUserInfoModel = roomUserInfoModel;
        this.mHousePitBean = housePitBean;
        this.pitNumber = pitNumber;
        tvNickname.setText(roomUserInfoModel.getNickname());
        tvId.setText("ID: " + roomUserInfoModel.getUser_code());
        if (roomUserInfoModel.getSex().equals("1")) {
            ivSex.setImageResource(R.drawable.bang_icon_man);
            llSex.getBackground().setLevel(1);
        } else {
            ivSex.setImageResource(R.drawable.bang_icon_women);
            llSex.getBackground().setLevel(2);
        }
        tvAge.setText(roomUserInfoModel.getAge());
        if (roomUserInfoModel.getFollow().equals("0")) {
            ivFollow.setImageLevel(0);
        } else {
            ivFollow.setImageLevel(1);
        }
        RankInfo rank_info = roomUserInfoModel.getRank_info();
        viewGrade.setGrade(rank_info.getRank_id(), rank_info.getRank_name());
        viewJue.setJue(rank_info.getNobility_id(), rank_info.getNobility_name());
        dhv.setData(roomUserInfoModel.getHead_picture(), rank_info.getPicture());

        if (jurisdiction == 0) {
            llAdmin.setVisibility(View.GONE);
        } else {
            llAdmin.setVisibility(View.VISIBLE);
            if (housePitBean != null) {
                if (housePitBean.getShutup().equals("1")) {
                    ivWheatBan.setImageLevel(1);
                } else {
                    ivWheatBan.setImageLevel(0);
                }

                if (housePitBean.getBanned().equals("0")) {
                    ivBanned.setImageLevel(0);
                } else {
                    ivBanned.setImageLevel(1);
                }
            }
        }

    }

    @OnClick({R.id.tv_report, R.id.rl_data, R.id.rl_rollow, R.id.rl_private,
            R.id.rl_giving_gifts, R.id.rl_down_wheat, R.id.rl_wheat_bean, R.id.rl_no_msg, R.id.rl_kick_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_report:
                mRoomUserInfoClickListener.report(mRoomUserInfoModel.getUser_id());
                break;
            case R.id.rl_data:
                mRoomUserInfoClickListener.lookData(mRoomUserInfoModel.getUser_id());
                break;
            case R.id.rl_rollow:
                if (mRoomUserInfoModel.getFollow().equals("1")) {
                    mRoomUserInfoClickListener.followUser(mRoomUserInfoModel.getUser_id(), 2);
                } else {
                    mRoomUserInfoClickListener.followUser(mRoomUserInfoModel.getUser_id(), 1);
                }
                break;
            case R.id.rl_private:
                if (mRoomUserInfoModel.getUser_id().equals(MyApplication.getInstance().getUser().getUser_id())) {
                    ToastUtils.showShort("自己不能给自己送礼物哟");
                    return;
                }
                mRoomUserInfoClickListener.privateMsg(mRoomUserInfoModel.getEmchat_username(), mRoomUserInfoModel.getNickname(), mRoomUserInfoModel.getHead_picture(), mRoomUserInfoModel.getRank_info());
                break;
            case R.id.rl_giving_gifts:
                if (mRoomUserInfoModel.getUser_id().equals(MyApplication.getInstance().getUser().getUser_id())) {
                    ToastUtils.showShort("自己不能给自己送礼物哟");
                    return;
                }
                if (pitNumber.equals("0")) {
                    mRoomUserInfoClickListener.givingGiftsPersonal(mRoomUserInfoModel.getUser_id(), mRoomUserInfoModel.getNickname());
                } else {
                    mRoomUserInfoClickListener.givingGifts(mRoomUserInfoModel);
                }
                break;
            case R.id.rl_down_wheat:
                mRoomUserInfoClickListener.downWheats(mRoomUserInfoModel.getUser_id(), mHousePitBean.getPit_number(), mRoomUserInfoModel.getNickname());
                break;
            case R.id.rl_wheat_bean:
                if (mHousePitBean.getShutup().equals("1")) {
                    mRoomUserInfoClickListener.forbiddenWheat(mRoomUserInfoModel.getUser_id(), mHousePitBean.getPit_number(), "2", mRoomUserInfoModel.getNickname());
                } else {
                    mRoomUserInfoClickListener.forbiddenWheat(mRoomUserInfoModel.getUser_id(), mHousePitBean.getPit_number(), "1", mRoomUserInfoModel.getNickname());
                }
                break;
            case R.id.rl_no_msg:
                if (mHousePitBean.getBanned().equals("0")) {
                    mRoomUserInfoClickListener.forbiddenMsg(mRoomUserInfoModel.getUser_id(), 1, mRoomUserInfoModel.getNickname());
                } else {
                    mRoomUserInfoClickListener.forbiddenMsg(mRoomUserInfoModel.getUser_id(), 2, mRoomUserInfoModel.getNickname());
                }
                break;
            case R.id.rl_kick_out:
                mRoomUserInfoClickListener.userKickOut(mRoomUserInfoModel.getUser_id(), mHousePitBean.getPit_number(), mRoomUserInfoModel.getNickname());
                break;
        }
        dismiss();
    }


    public void setmRoomUserInfoClickListener(RoomUserInfoClickListener mRoomUserInfoClickListener) {
        this.mRoomUserInfoClickListener = mRoomUserInfoClickListener;
    }

    public interface RoomUserInfoClickListener {
        void lookData(String userId);

        void followUser(String userId, int type);

        void privateMsg(String userId, String nickname, String avatar, RankInfo rankInfo);

        void givingGifts(RoomUserInfoModel data);

        void givingGiftsPersonal(String userId, String nickName);

        void downWheats(String userId, String pitNumber, String userName);

        void forbiddenWheat(String userId, String pitNumber, String type, String userName);

        void forbiddenMsg(String userId, int type, String userName);

        void userKickOut(String userId, String pitNumber, String userName);

        void report(String userId);

    }
}
