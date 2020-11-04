/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spadea.yuyin.ui.fragment1.trans;

import android.content.Context;
import android.widget.ListView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.adapter.EaseMessageAdapter;
import com.hyphenate.easeui.widget.presenter.EaseChatBigExpressionPresenter;
import com.hyphenate.easeui.widget.presenter.EaseChatFilePresenter;
import com.hyphenate.easeui.widget.presenter.EaseChatImagePresenter;
import com.hyphenate.easeui.widget.presenter.EaseChatLocationPresenter;
import com.hyphenate.easeui.widget.presenter.EaseChatRowPresenter;
import com.hyphenate.easeui.widget.presenter.EaseChatVideoPresenter;
import com.hyphenate.easeui.widget.presenter.EaseChatVoicePresenter;

public class TransEaseMessageAdapter extends EaseMessageAdapter {

    public TransEaseMessageAdapter(Context context, String username, int chatType, ListView listView) {
        super(context, username, chatType, listView);
    }

    @Override
    protected EaseChatRowPresenter createChatRowPresenter(EMMessage message, int position) {
        if (customRowProvider != null && customRowProvider.getCustomChatRow(message, position, this) != null) {
            return customRowProvider.getCustomChatRow(message, position, this);
        }

        EaseChatRowPresenter presenter = null;

        switch (message.getType()) {
            case TXT:
                if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_BIG_EXPRESSION, false)) {
                    presenter = new EaseChatBigExpressionPresenter();
                } else {
                    presenter = new TransEaseChatTextPresenter();
                }
                break;
            case LOCATION:
                presenter = new EaseChatLocationPresenter();
                break;
            case FILE:
                presenter = new EaseChatFilePresenter();
                break;
            case IMAGE:
                presenter = new EaseChatImagePresenter();
                break;
            case VOICE:
                presenter = new EaseChatVoicePresenter();
                break;
            case VIDEO:
                presenter = new EaseChatVideoPresenter();
                break;
            default:
                break;
        }

        return presenter;
    }
}
