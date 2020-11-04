package com.spadea.xqipao.utils.view.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.spadea.yuyin.R;

public class BlindRoomHostView extends FrameLayout {
    public BlindRoomHostView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public BlindRoomHostView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initTypeValue(context,attrs);
        initView(context);
    }

    public BlindRoomHostView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeValue(context,attrs);
        initView(context);
    }

    public void  initTypeValue(Context context ,AttributeSet attrs){

    }

    public void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.layout_room_blind_host,this,true);

    }

}
