package com.spadea.xqipao.ui.room.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.spadea.yuyin.R;
import com.spadea.xqipao.data.RoomPitInfo;
import com.spadea.xqipao.ui.room.presenter.WheatToolPreswenter;
import com.spadea.xqipao.ui.base.view.BaseDialogFragment;
import com.spadea.xqipao.ui.room.contacts.WheatToolContacts;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 没人麦位操作
 */
public class WheatToolDialogFragment extends BaseDialogFragment<WheatToolPreswenter> implements WheatToolContacts.View {


    @BindView(R.id.tv_show_xindong)
    TextView tvShowXindong;
    @BindView(R.id.tv_clean_xd_all)
    TextView tvCleanXdAll;
    @BindView(R.id.tv_clean_xd)
    TextView tvCleanXd;
    @BindView(R.id.tv_sealing_wheat)
    TextView tvSealingWheat;
    @BindView(R.id.tv_close)
    TextView tvClose;

    private RoomPitInfo mRoomPitInfo;
    private String mRoomId;
    private String mPitNum;

    public static WheatToolDialogFragment newInstance(String roomId, String pitNum) {
        WheatToolDialogFragment wheatToolDialogFragment = new WheatToolDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomId", roomId);
        bundle.putString("pitNum", pitNum);
        wheatToolDialogFragment.setArguments(bundle);
        return wheatToolDialogFragment;
    }

    @Override
    protected void initData() {
        MvpPre.roomPitInfo(mRoomId, mPitNum);
    }

    @Override
    protected void initView(View rootView) {
        mRoomId = getArguments().getString("roomId");
        mPitNum = getArguments().getString("pitNum");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dialog_wheatoperation;
    }

    @Override
    protected WheatToolPreswenter bindPresenter() {
        return new WheatToolPreswenter(this, mContext);
    }

    @OnClick({R.id.tv_show_xindong, R.id.tv_clean_xd_all, R.id.tv_clean_xd, R.id.tv_sealing_wheat, R.id.tv_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_show_xindong:
                if (mRoomPitInfo != null) {
                    if (mRoomPitInfo.getCardiac_state().equals("1")) {
                        MvpPre.setRoomCardiac(mRoomId, 2);
                    } else {
                        MvpPre.setRoomCardiac(mRoomId, 1);
                    }
                }
                break;
            case R.id.tv_clean_xd_all:
                MvpPre.clearRoomCardiac(mRoomId);
                break;
            case R.id.tv_clean_xd:
                MvpPre.clearCardiac(mRoomId, mPitNum);
                break;
            case R.id.tv_sealing_wheat:
                if (mRoomPitInfo != null) {
                    if (mRoomPitInfo.getState().equals("1")) {
                        MvpPre.closePit("2", mPitNum, mRoomId);
                    } else {
                        MvpPre.closePit("1", mPitNum, mRoomId);
                    }
                }
                break;
            case R.id.tv_close:
                this.dismiss();
                break;
        }
    }

    @Override
    public void setRoomPitInfo(RoomPitInfo data) {
        this.mRoomPitInfo = data;
        //1开启2关闭
        if (data.getCardiac_state().equals("1")) {
            tvShowXindong.setText("隐藏心动值");
        } else {
            tvShowXindong.setText("显示心动值");
        }
//        1封麦2不封麦
        if (data.getState().equals("1")) {
            tvSealingWheat.setText("解麦");
        } else {
            tvSealingWheat.setText("封麦");
        }
    }

    /**
     * 显示隐藏心动值
     */
    @Override
    public void setRoomCardiacSuccess() {
        this.dismiss();
    }

    @Override
    public void clearRoomCardiacSuccess() {
        this.dismiss();
    }

    @Override
    public void clearCardiacSuccess() {
        this.dismiss();
    }

    @Override
    public void closePitSuccess() {
        this.dismiss();
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
}
