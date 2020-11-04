package com.spadea.xqipao.ui.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ImgAdapter extends PagerAdapter {
    private Context context;
    private List<String> list = new ArrayList<>();


    public ImgAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //返回要显示的条目内容
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //container  容器  相当于用来存放imageView
        View view = LayoutInflater.from(context).inflate(R.layout.view_vip_info, container, false);
        ImageView imageview = view.findViewById(R.id.image);
        imageview.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoader.loadImage(MyApplication.getInstance(), imageview, list.get(position));
        //把图片添加到container中
        container.addView(view);
        //把图片返回给框架，用来缓存
        return view;
    }

    //销毁条目
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //object:刚才创建的对象，即要销毁的对象
        container.removeView((View) object);
    }
}
