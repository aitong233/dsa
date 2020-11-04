package com.qpyy.libcommon.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.qpyy.libcommon.utils.ImageLoader;

public class NobilityView extends android.support.v7.widget.AppCompatImageView {

    private Context mContext;


    public NobilityView(Context context) {
        super(context);
        initView(context);
    }

    public NobilityView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public NobilityView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
//        ImageLoader.loadIcon(mContext,this,"http://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/admin_images/5efd8ac7b9463.png");
    }

    public void setNobility(String url){
        setVisibility(TextUtils.isEmpty(url)? GONE:VISIBLE);
        ImageLoader.loadIcon(mContext,this,url);
    }
}
