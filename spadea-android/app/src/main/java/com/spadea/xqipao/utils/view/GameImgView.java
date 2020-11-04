package com.spadea.xqipao.utils.view;

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

import com.spadea.yuyin.R;


import butterknife.BindView;
import butterknife.ButterKnife;

public class GameImgView extends FrameLayout {

    private Context mContext;

    @BindView(R.id.iv_start)
    ImageView ivStart;
    @BindView(R.id.ll_result)
    LinearLayout llResult;
    @BindView(R.id.iv_qiu1)
    ImageView ivQiu1;
    @BindView(R.id.iv_qiu2)
    ImageView ivQiu2;
    @BindView(R.id.iv_qiu3)
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
