package com.qpyy.room.dialog;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.KeyboardUtils;
import com.hjq.toast.ToastUtils;
import com.qpyy.libcommon.widget.dialog.BaseDialog;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.RoomInputEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.dialog
 * 创建人 王欧
 * 创建时间 2020/6/22 10:51 AM
 * 描述 信息输入
 */
public class RoomInputDialog extends BaseDialog {

    private static final String TAG = "RoomInputDialog";
    @BindView(R2.id.et_content)
    EditText mEtContent;
    @BindView(R2.id.ll_comment)
    LinearLayout mLlComment;
    @BindView(R2.id.tv_send)
    Button mTvSend;
    @BindView(R2.id.ll_input)
    LinearLayout mLlInput;

    private String hint;

    private CountDownTimer mCountDownTimer;

    private boolean canSend = true;

    public RoomInputDialog(@NonNull Context context, String hint) {
        super(context);
        this.hint = hint;
        Log.d(TAG, "(Start)启动了===========================RoomInputDialog");
    }

    @Override
    public int getLayout() {
        return R.layout.room_dialog_room_input;
    }

    @Override
    public void initView() {
        Window window = getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
    }

    @Override
    public void initData() {
        if (!TextUtils.isEmpty(hint)) {
            mEtContent.setHint(hint);
        }
    }

    @OnClick(R2.id.tv_send)
    public void onViewClicked() {
        if (!canSend) {
            ToastUtils.show("消息发送较频繁~");
            return;
        }
        String text = mEtContent.getText().toString();
        if (TextUtils.isEmpty(text)) {
            ToastUtils.show("请输入评论内容");
            return;
        }
        EventBus.getDefault().post(new RoomInputEvent(text));
        mEtContent.setText("");
        countDownTimer();
        dismiss();
    }

    private void countDownTimer() {
        releaseCountDownTimer();
        mCountDownTimer = new CountDownTimer(3 * 1000L, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                canSend = false;
            }

            @Override
            public void onFinish() {
                canSend = true;
            }
        };
        mCountDownTimer.start();
    }

    @Override
    protected void onStop() {
        releaseCountDownTimer();
        super.onStop();
    }

    private void releaseCountDownTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    @Override
    public void dismiss() {
        KeyboardUtils.hideSoftInput(mEtContent);
        super.dismiss();
    }

    @Override
    public void show() {
        super.show();
        if (mEtContent != null) {
            mEtContent.postDelayed(new Runnable() {
                @Override
                public void run() {
                    KeyboardUtils.showSoftInput(mEtContent);
                }
            }, 200);
        }
    }
}
