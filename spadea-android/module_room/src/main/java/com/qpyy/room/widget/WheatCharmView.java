package com.qpyy.room.widget;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qpyy.libcommon.bean.UserBean;
import com.qpyy.room.R;
import com.qpyy.room.R2;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WheatCharmView extends ConstraintLayout {
    @BindView(R2.id.tv_value)
    TextView tvValue;
    @BindView(R2.id.tv_time)
    TextView tvTime;

    @BindView(R2.id.bg)
    RelativeLayout bg;


    public WheatCharmView(Context context) {
        this(context, null);
    }

    public WheatCharmView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.room_view_wheat_charm, this);
        ButterKnife.bind(this);
    }

    public void setSex(String sex, String userId, String value) {
        if (UserBean.FEMALE.equals(sex)) {
            bg.setBackgroundResource(R.mipmap.room_bg_wheat_charm_mm);
//            tvTime.setBackgroundResource(R.mipmap.room_bg_wheat_time_value_mm);
        } else {
            bg.setBackgroundResource(R.mipmap.room_bg_wheat_charm_gg);
//            tvTime.setBackgroundResource(R.mipmap.room_bg_wheat_time_value_gg);
        }
        if (TextUtils.isEmpty(userId) || "0".equals(userId)) {
            bg.setBackgroundResource(R.mipmap.room_bg_wheat_charm_default);
//            tvTime.setBackgroundResource(R.mipmap.room_bg_wheat_time_value_common);
        }
        tvValue.setText(value);
    }

    public void setTime(int time) {
        if (time == 0) {
            tvTime.setText("");
        } else {
            tvTime.setText(String.format("%s'%s", time / 60, time % 60));
        }
    }

//    public boolean isOpen(){
//        return (TextUtils.isEmpty(userId) || "0".equals(userId));
//    }
}
