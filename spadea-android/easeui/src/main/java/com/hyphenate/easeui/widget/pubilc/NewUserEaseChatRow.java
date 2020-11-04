package com.hyphenate.easeui.widget.pubilc;

import android.content.Context;
import android.text.Html;
import android.text.Spannable;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.qpyy.libcommon.widget.NewView;

public class NewUserEaseChatRow extends EaseChatRow {


    private TextView textViewContent;
    private NewView newView;


    public NewUserEaseChatRow(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(R.layout.ease_row_received_message_new_user, this);
    }

    @Override
    protected void onFindViewById() {
        textViewContent = findViewById(R.id.tv_content);
        newView = findViewById(R.id.new_view);


    }

    @Override
    protected void onViewUpdate(EMMessage msg) {

    }

    @Override
    protected void onSetUpView() {
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
        Spannable span = EaseSmileUtils.getSmiledText(context, Html.fromHtml(txtBody.getMessage()));
        newView.setNew(1);
        textViewContent.setText(span);
    }
}
