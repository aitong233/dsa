package com.qpyy.libcommon.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.qpyy.libcommon.R;
import com.qpyy.libcommon.utils.ImageLoader;

public class NewView extends android.support.v7.widget.AppCompatImageView {

    private Context mContext;


    public NewView(Context context) {
        super(context);
        initView(context);
    }


    public NewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public NewView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
    }

    public void setNew(int isNew) {
        if (isNew == 0) {
            this.setVisibility(GONE);
        } else if (isNew == 1) {
            setImageResource(R.mipmap.ic_user_new);
        }
    }



}
