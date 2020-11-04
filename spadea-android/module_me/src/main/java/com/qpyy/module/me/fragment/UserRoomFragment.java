package com.qpyy.module.me.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.constant.ImgConstants;
import com.qpyy.libcommon.utils.ImageLoader;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.bean.UserRoomResp;
import com.qpyy.module.me.contacts.UserRoomContacts;
import com.qpyy.module.me.presenter.UserRoomPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 用户房间信息
 */
public class UserRoomFragment extends BaseMvpFragment<UserRoomPresenter> implements UserRoomContacts.View {


    @BindView(R2.id.riv_room)
    RoundedImageView rivRoom;
    @BindView(R2.id.iv_host)
    ImageView ivHost;
    @BindView(R2.id.tv_popularity)
    TextView tvPopularity;
    @BindView(R2.id.iv_room_type)
    ImageView ivRoomType;
    @BindView(R2.id.tv_room_name)
    TextView tvRoomName;
    @BindView(R2.id.tv_liveing)
    TextView tvLiveing;
    @BindView(R2.id.rl_room)
    RelativeLayout rlRoom;
    @BindView(R2.id.rl_room_null)
    RelativeLayout rlRoomNull;
    @BindView(R2.id.iv_play)
    ImageView ivPlay;

    private String userId;
    private UserRoomResp mUserRoomResp;

    public static UserRoomFragment newInstance(String userId) {
        UserRoomFragment fragment = new UserRoomFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected UserRoomPresenter bindPresenter() {
        return new UserRoomPresenter(this, getContext());
    }

    @Override
    protected void initData() {
        userId = getArguments().getString("userId");
        MvpPre.getUserRoom(userId);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.me_fragment_user_room;
    }

    @Override
    public void setUserRoom(UserRoomResp data) {
        mUserRoomResp = data;
        if (data == null || TextUtils.isEmpty(data.getRoom_id())) {
            rlRoomNull.setVisibility(View.VISIBLE);
            rlRoom.setVisibility(View.GONE);
        } else {
            rlRoomNull.setVisibility(View.GONE);
            rlRoom.setVisibility(View.VISIBLE);
            tvPopularity.setText(data.getPopularity());
            ImageLoader.loadImage(getContext(), rivRoom, data.getRoomPicture());
            ImageLoader.loadImage(getContext(), ivRoomType, data.getLabel_icon());
            tvRoomName.setText(data.getRoom_name());
            if ("0".equals(data.getOn_live())) {
                tvLiveing.setVisibility(View.GONE);
                ivPlay.setVisibility(View.GONE);
            } else {
                tvLiveing.setVisibility(View.VISIBLE);
                ivPlay.setVisibility(View.VISIBLE);
                ImageLoader.loadIcon(getActivity(), ivPlay, ImgConstants.PINK_IMG);
            }
        }
        ImageUtils.loadGift(ivHost, ImageUtils.ANIM);
    }


    @OnClick(R2.id.iv_in_room)
    public void onClick(View view) {
        if (mUserRoomResp != null && !TextUtils.isEmpty(mUserRoomResp.getRoom_id())) {
            ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", mUserRoomResp.getRoom_id()).navigation();
        }
    }

}
