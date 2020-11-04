package com.spadea.xqipao.utils.dialog.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.spadea.xqipao.utils.dialog.BaseBottomSheetDialog;
import com.spadea.yuyin.R;

import butterknife.OnClick;

public class RoomExitDialog extends BaseBottomSheetDialog {


    private RoomExitListener mRoomExitListener;

    public RoomExitDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_room_exit;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.rl_minimize, R.id.rl_exit, R.id.rl_close})
    public void onClcik(View view) {
        if (mRoomExitListener != null) {
            switch (view.getId()) {
                case R.id.rl_minimize:
                    mRoomExitListener.roomMin();
                    break;
                case R.id.rl_exit:
                    mRoomExitListener.roomExit();
                    break;
            }
        }
        this.dismiss();
    }


    public void setmRoomExitListener(RoomExitListener mRoomExitListener) {
        this.mRoomExitListener = mRoomExitListener;
    }

    public interface RoomExitListener {
        void roomExit();

        void roomMin();


    }
}
