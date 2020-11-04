package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jungly.gridpasswordview.GridPasswordView;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RoomSettingPasswordDialog extends BaseDialog {


    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.pswView)
    GridPasswordView pswView;
    @BindView(R.id.btn_submit)
    Button btnSubmit;


    private RoomPasswordListener mRoomPasswordListener;

    public RoomSettingPasswordDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_room_setting_pwd;
    }

    @Override
    public void initView() {
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        pswView.clearPassword();
    }

    @OnClick({R.id.iv_close, R.id.btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:

                break;
            case R.id.btn_submit:
                if (mRoomPasswordListener != null) {
                    String password = pswView.getPassWord();
                    if (TextUtils.isEmpty(password) || password.length() == 4) {
                        mRoomPasswordListener.roomSettingPassword(password);
                    } else {
                        ToastUtils.showShort("请输入完整的密码");
                    }
                }
                break;
        }
        this.dismiss();
    }


    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_SEARCH) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void addRoomPasswordListener(RoomPasswordListener roomPasswordListener) {
        mRoomPasswordListener = roomPasswordListener;
    }

    public interface RoomPasswordListener {
        void roomSettingPassword(String password);
    }

}
