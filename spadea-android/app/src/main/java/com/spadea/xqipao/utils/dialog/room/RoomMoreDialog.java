package com.spadea.xqipao.utils.dialog.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.spadea.xqipao.utils.dialog.BaseBottomSheetDialog;
import com.spadea.yuyin.R;

import butterknife.OnClick;

public class RoomMoreDialog extends BaseBottomSheetDialog {


    private RoomMoreListener mRoomMoreListener;

    public RoomMoreDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_room_more;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.ll_report, R.id.ll_exit, R.id.ll_setting, R.id.ll_invitation, R.id.ll_share})
    public void onClick(View view) {
        if (mRoomMoreListener != null) {
            switch (view.getId()) {
                case R.id.ll_report:
                    mRoomMoreListener.roomReport();
                    break;
                case R.id.ll_exit:
                    mRoomMoreListener.roomExit();
                    break;
                case R.id.ll_setting:
                    mRoomMoreListener.roomSetting();
                    break;
                case R.id.ll_invitation:
                    mRoomMoreListener.roomInvitation();
                    break;
                case R.id.ll_share:
                    mRoomMoreListener.roomShare();
                    break;

            }
        }
        this.dismiss();
    }


    public void setmRoomMoreListener(RoomMoreListener mRoomMoreListener) {
        this.mRoomMoreListener = mRoomMoreListener;
    }

    public interface RoomMoreListener {
        void roomReport();

        void roomExit();

        void roomSetting();

        void roomInvitation();

        void roomShare();
    }
}
