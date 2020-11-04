package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.text.Spannable;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.model.EaseDingMessageHelper;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.exceptions.HyphenateException;
import com.qpyy.libcommon.utils.ImageUtils;

import java.util.List;

public class EaseChatRowGift extends EaseChatRow {

    private TextView tvGiftName;
    private TextView tvCharm;
    private ImageView ivGift;

    public EaseChatRowGift(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ?
                R.layout.ease_row_received_gift : R.layout.ease_row_sent_gift, this);
    }

    @Override
    protected void onFindViewById() {
        tvGiftName = (TextView) findViewById(R.id.tv_gift_name);
        ivGift = findViewById(R.id.iv_gift);
        tvCharm = findViewById(R.id.tv_charm);
    }

    @Override
    public void onSetUpView() {
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();
        Spannable span = EaseSmileUtils.getSmiledText(context, txtBody.getMessage());
        // 设置内容
        tvGiftName.setText(span, BufferType.SPANNABLE);
        try {
            String price = message.getStringAttribute(EaseConstant.EXTRA_MSG_GIFT_PRICE);
            String num = message.getStringAttribute(EaseConstant.EXTRA_MSG_GIFT_NUM);
            String imageUrl = message.getStringAttribute(EaseConstant.EXTRA_MSG_GIFT_PIC);
            try {
                int giftNumber = Integer.parseInt(num);
                String giftName = message.getStringAttribute(EaseConstant.EXTRA_MSG_GIFT_NAME);
                if (giftNumber > 1) {
                    tvGiftName.setText(giftName + "x" + num);
                } else {
                    tvGiftName.setText(giftName);
                }
                tvCharm.setText(String.format("+%s", Integer.parseInt(price) * giftNumber));
            } catch (Exception e) {
                e.printStackTrace();
            }
            ImageUtils.loadCenterCrop(imageUrl, ivGift);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onViewUpdate(EMMessage msg) {
        switch (msg.status()) {
            case CREATE:
                onMessageCreate();
                break;
            case SUCCESS:
                onMessageSuccess();
                break;
            case FAIL:
                onMessageError();
                break;
            case INPROGRESS:
                onMessageInProgress();
                break;
        }
    }

    public void onAckUserUpdate(final int count) {
        if (ackedView != null) {
            ackedView.post(new Runnable() {
                @Override
                public void run() {
                    ackedView.setVisibility(VISIBLE);
                    ackedView.setText(String.format(getContext().getString(R.string.group_ack_read_count), count));
                }
            });
        }
    }

    private void onMessageCreate() {
        progressBar.setVisibility(View.VISIBLE);
        statusView.setVisibility(View.GONE);
    }

    private void onMessageSuccess() {
        progressBar.setVisibility(View.GONE);
        statusView.setVisibility(View.GONE);

        // Show "1 Read" if this msg is a ding-type msg.
        if (EaseDingMessageHelper.get().isDingMessage(message) && ackedView != null) {
            ackedView.setVisibility(VISIBLE);
            int count = message.groupAckCount();
            ackedView.setText(String.format(getContext().getString(R.string.group_ack_read_count), count));
        }

        // Set ack-user list change listener.
        EaseDingMessageHelper.get().setUserUpdateListener(message, userUpdateListener);
    }

    private void onMessageError() {
        progressBar.setVisibility(View.GONE);
        statusView.setVisibility(View.VISIBLE);
    }

    private void onMessageInProgress() {
        progressBar.setVisibility(View.VISIBLE);
        statusView.setVisibility(View.GONE);
    }

    private EaseDingMessageHelper.IAckUserUpdateListener userUpdateListener =
            new EaseDingMessageHelper.IAckUserUpdateListener() {
                @Override
                public void onUpdate(List<String> list) {
                    onAckUserUpdate(list.size());
                }
            };
}
