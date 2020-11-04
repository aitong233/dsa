package com.spadea.xqipao.ui.chart.presenter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.easeui.widget.presenter.EaseChatRowPresenter;
import com.spadea.xqipao.ui.chart.view.CustomOrderChatRow;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.chart.presenter
 * 创建人 王欧
 * 创建时间 2020/6/5 9:32 AM
 * 描述 describe
 */
public class OrderChatRowPresenter extends EaseChatRowPresenter {
    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new CustomOrderChatRow(cxt,message,position,adapter);
    }
}
