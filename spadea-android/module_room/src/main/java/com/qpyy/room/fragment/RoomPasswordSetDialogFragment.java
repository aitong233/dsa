package com.qpyy.room.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.hjq.toast.ToastUtils;
import com.jungly.gridpasswordview.GridPasswordView;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.contacts.RoomPasswordSetContacts;
import com.qpyy.room.contacts.RoomPasswordSetPresenter;
import com.qpyy.room.event.PasswordInputEvent;
import com.qpyy.libcommon.event.RoomOutEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/7/30 15:35
 * 描述 输入密码弹窗
 */
public class RoomPasswordSetDialogFragment extends BaseMvpDialogFragment<RoomPasswordSetPresenter> implements RoomPasswordSetContacts.View {

    private static final String TAG = "RoomPasswordSetDialogFragment";
    @BindView(R2.id.tx_title)
    TextView txTitle;
    @BindView(R2.id.use_hint_txt)
    TextView useHintTxt;
    @BindView(R2.id.gpv_pswView)
    GridPasswordView gpvPswView;
    @BindView(R2.id.ed_pw_sub_icon_layout)
    LinearLayout edPwSubIconLayout;
    @BindView(R2.id.bt_pw)
    Button btPw;

    private boolean hidePwd;
    private String roomId;

    public static RoomPasswordSetDialogFragment newInstance(boolean hidePwd, String roomId) {

        Bundle args = new Bundle();
        args.putBoolean("hidePwd", hidePwd);
        args.putString("roomId", roomId);
        RoomPasswordSetDialogFragment fragment = new RoomPasswordSetDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        this.hidePwd = arguments.getBoolean("hidePwd");
        this.roomId = arguments.getString("roomId");
    }

    @Override
    public int getLayoutId() {
        return R.layout.room_dialog_room_pw_setting;
    }

    @Override
    public void initView() {
        //是否隐藏密码
        if (hidePwd) {
            txTitle.setText("请输入房间密码");
            useHintTxt.setVisibility(View.INVISIBLE);
            gpvPswView.setPasswordVisibility(false);
        } else {
            txTitle.setText("请设置房间密码");
            useHintTxt.setVisibility(View.VISIBLE);
            gpvPswView.setPasswordVisibility(true);
        }
    }

    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        int width = (int) (ScreenUtils.getScreenWidth() * 335 / 375.0);
        window.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected RoomPasswordSetPresenter bindPresenter() {
        return new RoomPasswordSetPresenter(this, getActivity());
    }

    @Override
    public void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (hidePwd) {
            EventBus.getDefault().post(new RoomOutEvent());
        }
    }

    @OnClick({R2.id.bt_pw})
    public void onViewClicked(View view) {
        String passWord = gpvPswView.getPassWord();
        if (passWord.length() != 4 && passWord.length() > 0) {
            ToastUtils.show("请输入完整的密码");
            return;
        }
        if (hidePwd) {
            EventBus.getDefault().post(new PasswordInputEvent(passWord));
            hidePwd=false;
            dismiss();
        } else {
            MvpPre.setRoomPassword(roomId, passWord);
        }
    }

    @Override
    public void roomPasswordSettingSuccess() {
        ToastUtils.show("密码设置成功");
        dismiss();
    }

    public void show(FragmentManager manager) {
        show(manager, TAG);
    }
}
