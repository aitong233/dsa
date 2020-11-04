package com.spadea.xqipao.ui.chart.contacts;

import com.hyphenate.chat.EMMessage;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EaseChatContacts {

    public interface View {
        void sendTextMessage(String content);

        void sendCheckedImageMesage(String imagePath);

        void sendMessage(@Nullable EMMessage message);
    }

    public interface Presenter {
    }
}
