package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.spadea.yuyin.R;

import butterknife.BindView;
import butterknife.OnClick;

public class RoomDetailClickEmptyPitDialog extends BaseBottomSheetDialog {


    @BindView(R.id.tv_clear_xin_dong)
    TextView tvClearXinDong;
    @BindView(R.id.tv_jin_mai)
    TextView tvJinMai;
    @BindView(R.id.tv_prohibit)
    TextView tvProhibit;

    private int shutUp = 2; //0禁麦   1开麦
    private int roomState = 0;
    private RoomDetailClickEmptyPitOnClickListener mRoomDetailClickEmptyPitOnClickListener;

    public RoomDetailClickEmptyPitDialog(@NonNull Context context) {
        super(context);
    }


    @Override
    public int getLayout() {
        return R.layout.dialog_room_detail_click_empty_pit;
    }

    @Override
    public void initView() {

    }

    public void setData(int shutUp, int roomState) {
        this.shutUp = shutUp;
        this.roomState = roomState;
        initData();
    }


    @Override
    public  void initData() {
        if (shutUp == 2) {
            tvJinMai.setText("开启禁麦");
        } else {
            tvJinMai.setText("关闭禁麦");
        }
        if (roomState == 2) {
            tvProhibit.setText("封禁麦位");
        } else {
            tvProhibit.setText("开启麦位");
        }

    }

    @OnClick({R.id.tv_clear_xin_dong, R.id.tv_jin_mai, R.id.tv_prohibit, R.id.tv_cancel})
    public void onClick(View view) {
        dismiss();
        if (mRoomDetailClickEmptyPitOnClickListener != null) {
            switch (view.getId()) {
                case R.id.tv_clear_xin_dong:
                    mRoomDetailClickEmptyPitOnClickListener.clearHeart();
                    break;
                case R.id.tv_jin_mai:
                    if (shutUp == 1) {
                        shutUp = 2;
                    } else {
                        shutUp = 1;
                    }
                    initData();
                    mRoomDetailClickEmptyPitOnClickListener.wheatBan();
                    break;
                case R.id.tv_prohibit:
                    if (roomState == 0) {
                        roomState = 1;
                    } else {
                        roomState = 0;
                    }
                    initData();
                    mRoomDetailClickEmptyPitOnClickListener.sealingWheat();
                    break;
                case R.id.tv_cancel:
                    mRoomDetailClickEmptyPitOnClickListener.cancel();
                    break;
            }
        }
    }


    public void addRoomDetailClickEmptyPitOnClickListener(RoomDetailClickEmptyPitOnClickListener roomDetailClickEmptyPitOnClickListener) {
        this.mRoomDetailClickEmptyPitOnClickListener = roomDetailClickEmptyPitOnClickListener;
    }


    public interface RoomDetailClickEmptyPitOnClickListener {
        /**
         * 禁麦
         */
        void wheatBan();

        /**
         * 封麦
         */
        void sealingWheat();

        /**
         * 清空心动值
         */
        void clearHeart();

        /**
         * 取消
         */
        void cancel();
    }


}
