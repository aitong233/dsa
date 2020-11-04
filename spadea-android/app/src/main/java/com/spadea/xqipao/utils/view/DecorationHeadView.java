package com.spadea.xqipao.utils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.xqipao.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.utils.view
 * 创建人 王欧
 * 创建时间 2020/4/9 12:46 PM
 * 描述 describe
 */
public class DecorationHeadView extends ConstraintLayout {
    @BindView(R.id.riv)
    RoundedImageView mRiv;
    @BindView(R.id.iv_frame)
    ImageView mIvFrame;

    public DecorationHeadView(Context context) {
        this(context, null, 0);
    }

    public DecorationHeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DecorationHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.view_decoration_head, this, true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DecorationHeadView);
        typedArray.recycle();
        ButterKnife.bind(this);
    }

    public void setData(String headPicture, String framePicture) {
        LogUtils.e(headPicture,framePicture);
        if (!TextUtils.isEmpty(headPicture)) {
            ImageLoader.loadHead(getContext(), mRiv, headPicture);
        }
        ImageLoader.loadHeadWithoutPlaceholder(getContext(), mIvFrame, framePicture);
    }
}
