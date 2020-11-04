package com.qpyy.room.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.KeyboardUtils;
import com.hjq.toast.ToastUtils;
import com.qpyy.room.R;
import com.qpyy.room.bean.RoomInputEvent;
import com.qpyy.room.event.RoomInputHideEvent;

import org.greenrobot.eventbus.EventBus;

public class RoomMessageInputMenu extends ConstraintLayout {
    EditText etContent;
    Button tvSend;

    public RoomMessageInputMenu(Context context) {
        this(context, null);
    }

    public RoomMessageInputMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.room_message_input_menu, this);
        etContent = findViewById(R.id.et_content);
        tvSend = findViewById(R.id.tv_send);
        tvSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewClicked(v);
            }
        });
        setVisibility(GONE);
    }

    public void onViewClicked(View view) {
        if (!canSend) {
            ToastUtils.show("消息发送较频繁~");
            return;
        }
        String text = etContent.getText().toString();
        if (TextUtils.isEmpty(text)) {
            ToastUtils.show("请输入评论内容");
            return;
        }
        EventBus.getDefault().post(new RoomInputEvent(text));
        etContent.setText("");
        countDownTimer();
        dismiss();
    }

    /**
     * 下面的内容为发送消息逻辑
     */
    private CountDownTimer mCountDownTimer;
    private boolean canSend = true;

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
    protected void onDetachedFromWindow() {
        releaseCountDownTimer();
        super.onDetachedFromWindow();
    }

    private void releaseCountDownTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    public void show() {
        setVisibility(VISIBLE);
        etContent.requestFocus();
        KeyboardUtils.showSoftInput(etContent);
        EventBus.getDefault().post(new RoomInputHideEvent(false));
    }

    public void dismiss() {
        setVisibility(GONE);
        KeyboardUtils.hideSoftInput(etContent);
        EventBus.getDefault().post(new RoomInputHideEvent(true));
    }
}
