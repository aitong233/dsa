package com.qpyy.room;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.FragmentUtils;
import com.hjq.toast.ToastUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.qpyy.libcommon.base.BaseAppCompatActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.http.RetrofitClient;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.room.bean.RoomInputEvent;
import com.qpyy.room.fragment.EmojDialogFragment;
import com.qpyy.room.fragment.PublicScreenEaseChatFragment;
import com.qpyy.room.widget.KeyboardPopupWindow;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseAppCompatActivity {

    @BindView(R2.id.btn_login)
    Button btnLogin;
    @BindView(R2.id.btn_room)
    Button btnRoom;
    @BindView(R2.id.frame)
    FrameLayout frame;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_activity_main;
    }

    @OnClick({R2.id.btn_login, R2.id.btn_room, R2.id.btn_chat, R2.id.btn_send})
    public void onViewClick(View view) {
        if (view.getId() == R.id.btn_login) {
            RetrofitClient.getInstance().login();
        } else if (view.getId() == R.id.btn_room) {
            ARouter.getInstance().build(ARouteConstants.LIVE_ROOM).withString("roomId", "3008").navigation();
        } else if (view.getId() == R.id.btn_chat) {

        } else if (view.getId() == R.id.btn_send) {
            EventBus.getDefault().post(new RoomInputEvent("你好哈擦撒开了参赛了"));
        }
    }

}
