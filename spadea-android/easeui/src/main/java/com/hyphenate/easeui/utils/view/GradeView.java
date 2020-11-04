package com.hyphenate.easeui.utils.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.easeui.R;

public class GradeView extends FrameLayout {


    private TextView tvGrade;
    private ImageView ivGrade;


    public GradeView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public GradeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GradeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_grade, this);
        tvGrade = findViewById(R.id.tv_grade);
        ivGrade = findViewById(R.id.iv_grade);
    }

    public void setGrade(int rankId, String rankName) {
        setVisibility(VISIBLE);
        switch (rankId) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                tvGrade.setVisibility(VISIBLE);
                ivGrade.setVisibility(GONE);
                tvGrade.setText(rankName);
                tvGrade.setBackgroundResource(R.drawable.bg_grade10);
                break;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                tvGrade.setVisibility(VISIBLE);
                ivGrade.setVisibility(GONE);
                tvGrade.setText(rankName);
                tvGrade.setBackgroundResource(R.drawable.bg_grade20);
                break;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
                tvGrade.setVisibility(VISIBLE);
                ivGrade.setVisibility(GONE);
                tvGrade.setText(rankName);
                tvGrade.setBackgroundResource(R.drawable.bg_grade30);
                break;
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
                tvGrade.setVisibility(VISIBLE);
                ivGrade.setVisibility(GONE);
                tvGrade.setText(rankName);
                tvGrade.setBackgroundResource(R.drawable.bg_grade40);
                break;
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
                tvGrade.setVisibility(VISIBLE);
                ivGrade.setVisibility(GONE);
                tvGrade.setText(rankName);
                tvGrade.setBackgroundResource(R.drawable.bg_grade50);
                break;
            case 51:
                tvGrade.setVisibility(GONE);
                ivGrade.setVisibility(VISIBLE);
                ivGrade.setImageResource(R.drawable.img_an_crown1);
                break;
            case 52:
                tvGrade.setVisibility(GONE);
                ivGrade.setVisibility(VISIBLE);
                ivGrade.setImageResource(R.drawable.img_an_crown2);
                break;
            case 53:
                tvGrade.setVisibility(GONE);
                ivGrade.setVisibility(VISIBLE);
                ivGrade.setImageResource(R.drawable.img_an_crown3);
                break;
            case 54:
                tvGrade.setVisibility(GONE);
                ivGrade.setVisibility(VISIBLE);
                ivGrade.setImageResource(R.drawable.img_an_crown);
                break;
            case 55:
                tvGrade.setVisibility(GONE);
                ivGrade.setVisibility(VISIBLE);
                ivGrade.setImageResource(R.drawable.img_an_crown);
                break;
            default:
                tvGrade.setVisibility(GONE);
                ivGrade.setVisibility(GONE);
                setVisibility(GONE);
                break;
        }
    }


}
