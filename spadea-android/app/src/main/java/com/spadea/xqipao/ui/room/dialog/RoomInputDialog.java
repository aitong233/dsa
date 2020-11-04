package com.spadea.xqipao.ui.room.dialog;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.KeyboardUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.even.RoomInputEvent;
import com.spadea.xqipao.utils.dialog.BaseDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.room.dialog
 * 创建人 王欧
 * 创建时间 2020/6/22 10:51 AM
 * 描述 describe
 */
public class RoomInputDialog extends BaseDialog {
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.ll_comment)
    LinearLayout mLlComment;
    @BindView(R.id.tv_send)
    Button mTvSend;
    @BindView(R.id.ll_input)
    LinearLayout mLlInput;

    private String hint;

    private CountDownTimer mCountDownTimer;

    private boolean canSend = true;

    public RoomInputDialog(@NonNull Context context, String hint) {
        super(context);
        this.hint = hint;
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_room_input;
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

    @OnClick(R.id.tv_send)
    public void onViewClicked() {
        if (!canSend) {
            ToastUtils.showShort("消息发送较频繁~");
            return;
        }
        String text = mEtContent.getText().toString();
        if (TextUtils.isEmpty(text)) {
            ToastUtils.showShort("请输入评论内容");
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

    public void release() {
        releaseCountDownTimer();
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
