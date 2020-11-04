package com.spadea.xqipao.ui.chart.view;

import android.content.Context;
import android.text.Spannable;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.spadea.yuyin.R;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.chart.view
 * 创建人 王欧
 * 创建时间 2020/6/5 9:38 AM
 * 描述 describe
 */
public class CustomOrderChatRow extends EaseChatRow {
    private TextView contentView;

    public CustomOrderChatRow(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ?
                R.layout.ease_row_received_order_message : R.layout.ease_row_received_order_message, this);
    }

    @Override
    protected void onFindViewById() {
        contentView = findViewById(R.id.text);
    }

    @Override
    protected void onViewUpdate(EMMessage msg) {

    }

    @Override
    protected void onSetUpView() {
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
        Spannable span = EaseSmileUtils.getSmiledText(context, txtBody.getMessage());
        // 设置内容
        contentView.setText(span, TextView.BufferType.SPANNABLE);
    }
}
