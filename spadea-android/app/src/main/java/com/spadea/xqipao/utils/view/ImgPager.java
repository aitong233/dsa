package com.spadea.xqipao.utils.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.spadea.xqipao.utils.AnimationUtil;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImgPager extends FrameLayout {

    private final int SHOW_TIME = 3000;
    private int index = 0;

    @BindView(R.id.riv1)
    RoundedImageView riv1;
    @BindView(R.id.riv2)
    RoundedImageView riv2;
    @BindView(R.id.ll_img)
    LinearLayout llImg;
    private Context mContext;
    private OnImgPagerClickListener mOnImgPagerClickListener;
    private List<String> dataList = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (index >= dataList.size() - 1) {
                index = 0;
            } else {
                index++;
            }
            setImg(getData(index), riv1);
            setImg(getData(index + 1), riv2);
            handler.sendEmptyMessageDelayed(0, SHOW_TIME);
        }
    };


    public ImgPager(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public ImgPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ImgPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(getContext()).inflate(R.layout.view_imgpager, this);
        ButterKnife.bind(this);
    }


    public void loadData(List<String> data) {
        this.dataList = data;
        if (this.dataList.size() == 0) {
            llImg.setVisibility(GONE);
        } else if (dataList.size() == 1) {
            riv1.setVisibility(VISIBLE);
            setImg(data.get(0), riv1);
        } else {
            riv1.setVisibility(VISIBLE);
            setImg(data.get(index), riv1);
            riv2.setVisibility(VISIBLE);
            setImg(data.get(index + 1), riv2);
            handler.sendEmptyMessageDelayed(0, SHOW_TIME);
        }
    }


    private void setImg(String url, RoundedImageView roundedImageView) {
        ImageLoader.loadHead(mContext, roundedImageView, url);
        Animation zoomInNearAnim = AnimationUtil.createZoomInNearAnim();
        roundedImageView.startAnimation(zoomInNearAnim);
    }


    private String getData(int postion) {
        if (postion >= dataList.size()) {
            return dataList.get(0);
        } else {
            return dataList.get(postion);
        }
    }


    @OnClick({R.id.riv1, R.id.riv2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.riv1:
                if (mOnImgPagerClickListener != null) {
                    mOnImgPagerClickListener.onItemClick(riv1, index, getData(index));
                }
                break;
            case R.id.riv2:
                if (mOnImgPagerClickListener != null) {
                    if (index >= dataList.size() - 1) {
                        mOnImgPagerClickListener.onItemClick(riv2, 0, getData(0));
                    } else {
                        mOnImgPagerClickListener.onItemClick(riv2, index + 1, getData(index + 1));
                    }
                }
                break;
        }
    }

    public void addOnImgPagerClickListener(OnImgPagerClickListener onImgPagerClickListener) {
        this.mOnImgPagerClickListener = onImgPagerClickListener;
    }

    public interface OnImgPagerClickListener {
        void onItemClick(View view, int postion, String data);
    }


    public void onDestroy(){
        if (handler!=null){
            handler.removeMessages(0);
        }
    }

}
