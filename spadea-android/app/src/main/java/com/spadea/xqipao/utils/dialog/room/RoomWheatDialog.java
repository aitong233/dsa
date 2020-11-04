package com.spadea.xqipao.utils.dialog.room;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.spadea.xqipao.data.HousePitBean;
import com.spadea.xqipao.utils.dialog.BaseDialog;
import com.spadea.yuyin.R;

import butterknife.BindView;
import butterknife.OnClick;

public class RoomWheatDialog extends BaseDialog {

    @BindView(R.id.tv_clean_xd)
    TextView tvCleanXd;
    @BindView(R.id.tv_forbidden)
    TextView tvForbidden;
    @BindView(R.id.tv_sealing_wheat)
    TextView tvSealingWheat;
    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.tv_show_xindong)
    TextView tvShowXinDong;

    private HousePitBean mHousePitBean;
    private int cardiac = 1;


    private RoomWheatClickListener mRoomWheatClickListener;

    public RoomWheatDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_room_wheat;
    }

    @Override
    public void initView() {
        ((View) dialogView.getParent()).setBackgroundColor(Color.parseColor("#00000000"));
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }

    @Override
    public void initData() {

    }

    public void setData(HousePitBean housePitBean, int cardiac) {
        this.mHousePitBean = housePitBean;
        this.cardiac = cardiac;
        if (housePitBean.getShutup().equals("1")) {
            tvForbidden.setText("解开禁麦");
        } else {
            tvForbidden.setText("禁麦");
        }

        if (housePitBean.getState().equals("1")) {
            tvSealingWheat.setText("解封麦位");
        } else {
            tvSealingWheat.setText("封麦");
        }
        //心动值开关 1.开 2.关
        if (cardiac == 1) {
            tvShowXinDong.setText("隐藏心动值");
        } else {
            tvShowXinDong.setText("显示心动值");
        }

    }

    @OnClick({R.id.tv_clean_xd, R.id.tv_forbidden, R.id.tv_sealing_wheat, R.id.tv_close, R.id.tv_clean_xd_all, R.id.tv_show_xindong})
    public void onClick(View view) {
        if (mHousePitBean != null && mRoomWheatClickListener != null) {
            switch (view.getId()) {
                case R.id.tv_clean_xd:
                    mRoomWheatClickListener.cleanXinDong(mHousePitBean.getPit_number());
                    break;
                case R.id.tv_forbidden:
                    if (mHousePitBean.getShutup().equals("1")) {
                        mRoomWheatClickListener.forbidden(mHousePitBean.getPit_number(), "2");
                    } else {
                        mRoomWheatClickListener.forbidden(mHousePitBean.getPit_number(), "1");
                    }
                    break;
                case R.id.tv_sealing_wheat:
                    if (mHousePitBean.getState().equals("1")) {
                        mRoomWheatClickListener.sealingWheat(mHousePitBean.getPit_number(), "2");
                    } else {
                        mRoomWheatClickListener.sealingWheat(mHousePitBean.getPit_number(), "1");
                    }
                    break;
                case R.id.tv_close:

                    break;
                case R.id.tv_clean_xd_all:
                    mRoomWheatClickListener.cleanAllXinDong();
                    break;
                case R.id.tv_show_xindong:
                    if (cardiac == 1) {
                        mRoomWheatClickListener.showXingDong(2);
                    } else {
                        mRoomWheatClickListener.showXingDong(1);
                    }
                    break;
            }
        }
        this.dismiss();
    }


    public void setmRoomWheatClickListener(RoomWheatClickListener mRoomWheatClickListener) {
        this.mRoomWheatClickListener = mRoomWheatClickListener;
    }

    public interface RoomWheatClickListener {

        void cleanAllXinDong();

        void cleanXinDong(String pitNumber);

        void forbidden(String pitNumber, String shutUp);

        void sealingWheat(String pitNumber, String state);

        void showXingDong(int cardiac);

    }

}
