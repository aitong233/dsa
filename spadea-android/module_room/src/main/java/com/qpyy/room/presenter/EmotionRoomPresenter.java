package com.qpyy.room.presenter;

import android.content.Context;

import com.qpyy.room.contacts.EmotionRoomContacts;

public class EmotionRoomPresenter extends BaseRoomPresenter<EmotionRoomContacts.View> implements EmotionRoomContacts.IEmotionRoomPre {
    public EmotionRoomPresenter(EmotionRoomContacts.View view, Context context) {
        super(view, context);
    }
}