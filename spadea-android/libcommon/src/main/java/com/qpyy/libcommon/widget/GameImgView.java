package com.qpyy.libcommon.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qpyy.libcommon.R;
import com.qpyy.libcommon.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameImgView extends FrameLayout {

    private Context mContext;

    @BindView(R2.id.iv_start)
    ImageView ivStart;
    @BindView(R2.id.ll_result)
    LinearLayout llResult;
    @BindView(R2.id.iv_qiu1)
    ImageView ivQiu1;
    @BindView(R2.id.iv_qiu2)
    ImageView ivQiu2;
    @BindView(R2.id.iv_qiu3)
    ImageView ivQiu3;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            overGame();
        }
    };


    public GameImgView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public GameImgView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GameImgView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(getContext()).inflate(R.layout.view_gameimgview, this);
        ButterKnife.bind(this);
    }


    public void startGame() {
        llResult.setVisibility(GONE);
        ivStart.setVisibility(VISIBLE);
        ivStart.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ivStart != null) {
                    ivStart.setVisibility(GONE);
                }
            }
        }, 2000);
    }

    public void setGameResult(String qiu1, String qiu2, String qiu3) {
        ivStart.setVisibility(GONE);
        llResult.setVisibility(VISIBLE);
        ivQiu1.setImageResource(getResources().getIdentifier(qiu1, "mipmap", mContext.getPackageName()));
        ivQiu2.setImageResource(getResources().getIdentifier(qiu2, "mipmap", mContext.getPackageName()));
        ivQiu3.setImageResource(getResources().getIdentifier(qiu3, "mipmap", mContext.getPackageName()));
        Message message = handler.obtainMessage(0, 0, 0);
        handler.sendMessageDelayed(message, 5000);
    }

    public void overGame() {
        llResult.setVisibility(GONE);
        ivStart.setVisibility(GONE);
    }


}
