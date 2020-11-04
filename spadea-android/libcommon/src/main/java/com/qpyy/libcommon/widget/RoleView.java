package com.qpyy.libcommon.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.qpyy.libcommon.R;

@SuppressLint("AppCompatCustomView")
public class RoleView extends ImageView {

    private Context mContext;

    public RoleView(Context context) {
        super(context);
        initView(context);
    }


    public RoleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RoleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        this.mContext = context;
//        setVisibility(GONE);
//        setImageResource(R.mipmap.img_host);
    }


    public void setRole(int role) {
        if (role == 1) {
            setImageResource(R.mipmap.img_host);
        } else if (role == 2) {
            setImageResource(R.mipmap.img_admin);
        } else if (role == 5) {
            setImageResource(R.mipmap.img_official);
        } else {
            this.setVisibility(GONE);
        }
    }

}
