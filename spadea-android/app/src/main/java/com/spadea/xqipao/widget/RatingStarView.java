package com.spadea.xqipao.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.yuyin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.widget
 * 创建人 王欧
 * 创建时间 2020/6/2 4:38 PM
 * 描述 describe
 */
public class RatingStarView extends RecyclerView {
    private int score;
    private boolean clickable;
    private BaseQuickAdapter<Integer, BaseViewHolder> mAdapter;

    public RatingStarView(@NonNull Context context) {
        this(context, null);
    }

    public RatingStarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingStarView);
        int starIcon = typedArray.getResourceId(R.styleable.RatingStarView_rsv_star_image, R.mipmap.ic_score_rating);
        int starUnselectIcon = typedArray.getResourceId(R.styleable.RatingStarView_rsv_star_unselect_image, R.mipmap.ic_score_rating_unselect);
        clickable = typedArray.getBoolean(R.styleable.RatingStarView_rsv_clickable, true);
        score = typedArray.getResourceId(R.styleable.RatingStarView_rsv_score, 5);
        typedArray.recycle();
        setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < score; i++) {
            items.add(i);
        }
        mAdapter = new BaseQuickAdapter<Integer, BaseViewHolder>(R.layout.rv_item_rating_star, items) {
            @Override
            protected void convert(BaseViewHolder helper, Integer item) {
                ImageView imageView = helper.getView(R.id.image);
                if (item < score) {
                    imageView.setImageResource(starIcon);
                } else {
                    imageView.setImageResource(starUnselectIcon);
                }
            }
        };
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!clickable) {
                    return;
                }
                score = position + 1;
                adapter.notifyDataSetChanged();
            }
        });
        setAdapter(mAdapter);
    }

    public void setScore(int score) {
        clickable = false;
        this.score = score;
        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < score; i++) {
            items.add(i);
        }
        mAdapter.setNewData(items);
    }

    public int getScore() {
        return score;
    }
}
