package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.KeyboardUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ChatDialog extends BaseDialog {


    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_send)
    Button tvSend;

    private ChatInputListener chatInputListener;

    public ChatDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_comment;
    }

    @Override
    public void initView() {
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        Display d = window.getWindowManager().getDefaultDisplay();
        //获取屏幕宽
        wlp.width = (int) (d.getWidth());
        //宽度按屏幕大小的百分比设置，这里我设置的是全屏显示
        wlp.gravity = Gravity.BOTTOM;
        if (wlp.gravity == Gravity.BOTTOM)
            wlp.y = 0;
        //如果是底部显示，则距离底部的距离是0
        window.setAttributes(wlp);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void initData() {
        etContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == 6 || actionId == 5) {//EditorInfo.IME_ACTION_SEARCH、EditorInfo.IME_ACTION_SEND等分别对应EditText的imeOptions属性
                    String text = etContent.getText().toString();
                    if (TextUtils.isEmpty(text)) {

                    } else {
                        if (chatInputListener != null) {
                            chatInputListener.onInput(text);
                            etContent.setText("");
                            KeyboardUtils.hideSoftInput(etContent);
                            dismiss();
                        }
                    }
                }
                return false;
            }
        });
    }

    @OnClick(R.id.tv_send)
    public void onClick() {
        String text = etContent.getText().toString();
        if (TextUtils.isEmpty(text)) {
            ToastUtils.showShort("发送的消息不能位空");
            return;
        }
        if (chatInputListener != null) {
            chatInputListener.onInput(text);
            etContent.setText("");
            KeyboardUtils.hideSoftInput(etContent);
            dismiss();
        }
    }


    public void setChatInputListener(ChatInputListener chatInputListener) {
        this.chatInputListener = chatInputListener;
    }

    public interface ChatInputListener {
        void onInput(String text);
    }
}
