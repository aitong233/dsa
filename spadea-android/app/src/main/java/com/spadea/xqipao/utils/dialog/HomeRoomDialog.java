package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.spadea.yuyin.R;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeRoomDialog extends BaseBottomSheetDialog {


    @BindView(R.id.tv_collection)
    TextView tvCollection;
    @BindView(R.id.tv_report)
    TextView tvReport;
    @BindView(R.id.tv_clean)
    TextView tvClean;

    private OnClickListener mOnClickListener;
    private String roomId;
    private String state = "0";


    public HomeRoomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_home_room;
    }

    public void setData(String roomId, String state) {
        this.roomId = roomId;
        this.state = state;
        if (state .equals("0")) {
            tvCollection.setText("收藏房间");
        } else {
            tvCollection.setText("取消收藏");
        }
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.tv_collection, R.id.tv_report, R.id.tv_clean})
    public void onClick(View view) {
        if (mOnClickListener == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_collection:
                mOnClickListener.collection(roomId, state);
                break;
            case R.id.tv_report:
                mOnClickListener.report(roomId);
                break;
            case R.id.tv_clean:

                break;
        }
        this.dismiss();
    }

    public void setmOnClickListener(OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    public interface OnClickListener {
        void collection(String toomId, String state);

        void report(String roomId);
    }
}
