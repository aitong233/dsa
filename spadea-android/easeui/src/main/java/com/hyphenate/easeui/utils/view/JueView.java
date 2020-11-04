package com.hyphenate.easeui.utils.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hyphenate.easeui.R;

public class JueView extends FrameLayout {

    private ImageView ivJue;

    public JueView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public JueView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public JueView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_jue, this);
        ivJue = findViewById(R.id.iv_jue);
    }


    public void setJue(int nobilityId, String nobilityName) {
        setVisibility(VISIBLE);
        switch (nobilityId) {
            case 1:
                ivJue.setImageResource(R.drawable.img_viscount);
                break;
            case 2:
                ivJue.setImageResource(R.drawable.img_count);
                break;
            case 3:
                ivJue.setImageResource(R.drawable.img_marquis);
                break;
            case 4:
                ivJue.setImageResource(R.drawable.img_duke);
                break;
            case 5:
                ivJue.setImageResource(R.drawable.img_wang);
                break;
            case 6:
                ivJue.setImageResource(R.drawable.img_emperor);
                break;
            default:
                setVisibility(GONE);
                break;
        }
    }

}
