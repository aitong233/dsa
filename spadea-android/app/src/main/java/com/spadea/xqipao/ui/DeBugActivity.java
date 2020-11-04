package com.spadea.xqipao.ui;

import android.view.View;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.ServiceUtils;
import com.spadea.xqipao.service.MyMqttService;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.utils.LogUtils;

import butterknife.OnClick;

public class DeBugActivity extends BaseActivity {


    public DeBugActivity() {
        super(R.layout.activity_debug);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {
    }

    @OnClick({R.id.bt_start, R.id.bt_start_sub})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_start:
                EMClient.getInstance().logout(true);
//                MyMqttService.startService(this);
                break;
            case R.id.bt_start_sub:
//                MyMqttService.stopService(this);
                testEmChat();
                break;

        }
        boolean serviceRunning = ServiceUtils.isServiceRunning(MyMqttService.class.getCanonicalName());
        LogUtils.e("服务状态", serviceRunning);
    }

    private void testEmChat() {
        EMClient.getInstance().login("B3F8CCCB75F30BA03574EF5BAD857409", "7wgYVWBY8C", new EMCallBack() {
            @Override
            public void onSuccess() {
                Logger.e("登录成功");
                joinChatRoom();
            }

            @Override
            public void onError(int code, String error) {
                Logger.e("登录错误", code + "   " + error);
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }

    private void joinChatRoom() {
        EMClient.getInstance().chatroomManager().joinChatRoom("107241208348673", new EMValueCallBack<EMChatRoom>() {
            @Override
            public void onSuccess(final EMChatRoom value) {
                Logger.e(value.toString());
            }

            @Override
            public void onError(final int error, String errorMsg) {
                Logger.e(errorMsg, error);
            }
        });
    }


}
