package com.spadea.xqipao.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.widget
 * 创建人 王欧
 * 创建时间 2020/5/14 2:21 PM
 * 描述 describe
 */
public class IdentifyEditView extends LinearLayout implements View.OnFocusChangeListener {
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.edit_text)
    EditText mEditText;
    @BindView(R.id.ll)
    LinearLayout mLL;

    public IdentifyEditView(Context context) {
        this(context, null);
    }

    public IdentifyEditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_identify_edit, this);
        ButterKnife.bind(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IdentifyEditView);
        String title = typedArray.getString(R.styleable.IdentifyEditView_id_title);
        String hint = typedArray.getString(R.styleable.IdentifyEditView_id_hint);
        int icon = typedArray.getResourceId(R.styleable.IdentifyEditView_id_icon, R.mipmap.ic_name_identify);
        int inputType = typedArray.getInteger(R.styleable.IdentifyEditView_id_input_type, 0);
        typedArray.recycle();
        mTvName.setText(title);
        mIvIcon.setImageResource(icon);
        mEditText.setHint(hint);
        if (inputType == 0) {
            mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        } else if (inputType == 1) {
            mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (inputType == 2) {
            mEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(18)});
        }
        mEditText.setOnFocusChangeListener(this);
        mIvIcon.setColorFilter(MyApplication.getInstance().getResources().getColor(R.color.color_9c9c9c));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            mIvIcon.setColorFilter(MyApplication.getInstance().getResources().getColor(R.color.color_main));
            mLL.setBackgroundResource(R.drawable.bg_identify_edit_focus);
        } else {
            mIvIcon.setColorFilter(MyApplication.getInstance().getResources().getColor(R.color.color_9c9c9c));
            mLL.setBackgroundResource(R.drawable.bg_r5_white);
        }
    }

    public String getText() {
        return mEditText.getText().toString();
    }

    public void addTextWatcher(TextWatcher textChange) {
        mEditText.addTextChangedListener(textChange);
    }

    public EditText getEditText() {
        return mEditText;
    }
}
