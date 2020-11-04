package com.spadea.xqipao.utils.view.room;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.spadea.yuyin.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.opensource.svgaplayer.SVGAImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WheatView extends FrameLayout {

    @BindView(R.id.iv_ripple1)
    SVGAImageView svgaImageView;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.iv_crown)
    ImageView ivCrown;
    @BindView(R.id.rl_user)
    RelativeLayout rlUser;
    @BindView(R.id.riv)
    RoundedImageView rivHeadPortrat;

    private Drawable ivDefaultImg;
    private boolean b = true;

    public WheatView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public WheatView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initTypeValue(context, attrs);
        initView(context);
    }

    public WheatView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeValue(context, attrs);
        initView(context);
    }

    public void initTypeValue(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WheatView);
        ivDefaultImg = a.getDrawable(R.styleable.WheatView_IV_DefaultImg);
        b = a.getBoolean(R.styleable.WheatView_User_Visibility, true);
        a.recycle();
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_wheat, this, true);
        ButterKnife.bind(this);

        ivAdd.setImageDrawable(ivDefaultImg);

        if (b) {
            rlUser.setVisibility(VISIBLE);
        } else {
            rlUser.setVisibility(GONE);
        }

    }


}
