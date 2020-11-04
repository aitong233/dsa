package com.spadea.xqipao.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.SizeUtils;

import java.util.ArrayList;
import java.util.List;

public class MainTabView extends LinearLayout {
    public MainTabView(Context context) {
        super(context);
        init();
    }

    public MainTabView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MainTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    void init() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
    }



    int unselc = Color.parseColor("#666666");
    int selc = Color.parseColor("#333333");


    List<View> list = new ArrayList<>();
    int pos=0;

    public void addTab(String... str) {
        for (int i = 0; i < str.length; i++) {
            TextView tv = new TextView(getContext());
            //tv.setPadding(SizeUtils.dp2px(10),SizeUtils.dp2px(2),SizeUtils.dp2px(10),SizeUtils.dp2px(2));
            tv.setTextColor(unselc);
            tv.setBackgroundResource(R.drawable.main_bg_selector);
            tv.setTextSize(14);
            tv.setGravity(Gravity.CENTER);
            tv.getPaint().setFakeBoldText(true);
            addView(tv, SizeUtils.dp2px(80), SizeUtils.dp2px(28));
            tv.setText(str[i]);
            MarginLayoutParams lp = (MarginLayoutParams) tv.getLayoutParams();
            lp.leftMargin = SizeUtils.dp2px(8);
            tv.setTag(i);
            list.add(tv);

            if(i==0){
                tv.setTextColor(selc);
                tv.setBackgroundResource(R.drawable.shape_mainr8);
            }
        }

        OnClickListener click = new OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                TextView tv = ((TextView) view);
                tv.setTextColor(selc);
                tv.setBackgroundResource(R.drawable.shape_mainr8);
//                if(pos!=(int)tv.getTag()){
//                    unSel(list.get(pos));
//                    Sel(tv);
//                    pos = (int) tv.getTag();
//                }
                if(viewPager!=null)viewPager.setCurrentItem((Integer) tv.getTag());
                if(change!=null)change.onChange((Integer) tv.getTag());

            }
        };

        for (View view:list)view.setOnClickListener(click);

    }


    public void selPos(int pos){
        reset();
        TextView tv = ((TextView) list.get(pos));
        tv.setTextColor(selc);
        tv.setBackgroundResource(R.drawable.shape_mainr8);
    }


    void unSel(View v){
        ViewCompat.animate(v).scaleX(1).scaleY(1).setDuration(366).start();
    }

    void Sel(View v){
        ViewCompat.animate(v).scaleX(1.2f).scaleY(1.2f).setDuration(366).start();
    }


    void reset(){
        for (View view:list){
            TextView tv = ((TextView) view);
            tv.setTextColor(unselc);
            tv.setBackgroundResource(R.drawable.shape_gr30);
        }
    }


    Change change;

    public void setChange(Change change) {
        this.change = change;
    }

    public interface Change{
        void onChange(int pos);
    }


    ViewPager viewPager;
    public void bindVP(ViewPager viewpager){
        this.viewPager = viewpager;
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //mSegmentTabLayout.setCurrentTab(i);
                selPos(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
