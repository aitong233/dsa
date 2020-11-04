package com.qpyy.module_news.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.module_news.R;
import com.qpyy.module_news.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module_news.widget
 * 创建人 王欧
 * 创建时间 2020/7/8 3:18 PM
 * 描述 describe
 */
public class NewsItemView extends ConstraintLayout {
    @BindView(R2.id.image)
    RoundedImageView mImage;
    @BindView(R2.id.tv_msg)
    TextView mTvMsg;
    @BindView(R2.id.tv_num)
    TextView mTvNum;
    @BindView(R2.id.tv_name)
    TextView mTvName;
    @BindView(R2.id.tv_time)
    TextView mTvTime;

    public NewsItemView(Context context) {
        this(context, null);
    }

    public NewsItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.news_rv_item_conversation, this);
        ButterKnife.bind(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NewsItemView);
        int icon = typedArray.getResourceId(R.styleable.NewsItemView_NewsItemView_Icon, R.mipmap.default_avatar);
        String name = typedArray.getString(R.styleable.NewsItemView_NewsItemView_Name);
        typedArray.recycle();
        mImage.setImageResource(icon);
        mTvName.setText(name);
    }

    public void setMsg(String text) {
        mTvMsg.setText(text);
    }

    public void setCount(int count) {
        mTvNum.setVisibility(count > 0 ? VISIBLE : GONE);
        mTvNum.setText(String.valueOf(count));
    }
}
