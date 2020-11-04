package com.spadea.xqipao.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

public class ImgViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<String> list = new ArrayList<String>();


    public ImgViewPagerAdapter(Context context, List<String> list) {
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
        PhotoView photoView = new PhotoView(context);
        photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Glide.with(context).load(list.get(position)).into(photoView);
        //把图片添加到container中
        container.addView(photoView);
        //把图片返回给框架，用来缓存
        return photoView;
    }

    //销毁条目
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //object:刚才创建的对象，即要销毁的对象
        container.removeView((View) object);
    }

}
