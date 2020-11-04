package com.qpyy.room.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.room.R;
import com.qpyy.room.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.widget
 * 创建人 黄强
 * 创建时间 2020/8/7 18:13
 * 描述 describe
 */
public class NewsItemView extends ConstraintLayout {
    @BindView(R2.id.image)
    RoundedImageView image;
    @BindView(R2.id.tv_msg)
    TextView tvMsg;
    @BindView(R2.id.tv_num)
    TextView tvNum;
    @BindView(R2.id.tv_name)
    TextView tvName;
    @BindView(R2.id.tv_time)
    TextView tvTime;

    public NewsItemView(Context context) {
        super(context);
    }

    public NewsItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.room_rv_item_conversation, this);
        ButterKnife.bind(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NewsItemView);
        int icon = typedArray.getResourceId(R.styleable.NewsItemView_NewsItemView_Icon, R.mipmap.default_avatar);
        String name = typedArray.getString(R.styleable.NewsItemView_NewsItemView_Name);
        typedArray.recycle();
        image.setImageResource(icon);
        tvName.setText(name);
    }

    public NewsItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMsg(String text) {
        tvMsg.setText(text);
    }

    public void setCount(int count) {
        tvNum.setVisibility(count > 0 ? VISIBLE : GONE);
        tvNum.setText(String.valueOf(count));
    }
}
