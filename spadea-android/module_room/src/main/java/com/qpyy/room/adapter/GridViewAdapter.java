package com.qpyy.room.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qpyy.libcommon.bean.GiftModel;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.room.R;

import java.util.List;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.adapter
 * 创建人 黄强
 * 创建时间 2020/8/6 15:15
 * 描述 describe
 */
public class GridViewAdapter extends BaseAdapter {
    private List<GiftModel> mDatas;
    private LayoutInflater inflater;
    private Context mContext;
    private int type = 0;
    /**
     * 页数下标,从0开始(当前是第几页)
     */
    private int curIndex;
    /**
     * 每一页显示的个数
     */
    private int pageSize = 8;

    public GridViewAdapter(Context context, List<GiftModel> mDatas, int curIndex, int type) {
        inflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
        this.curIndex = curIndex;
        this.mContext = context;
        this.type = type;
    }

    /**
     * 先判断数据集的大小是否足够显示满本页？mDatas.size() > (curIndex+1)*pageSize,
     * 如果够，则直接返回每一页显示的最大条目个数pageSize,
     * 如果不够，则有几项返回几,(mDatas.size() - curIndex * pageSize);(也就是最后一页的时候就显示剩余item)
     */
    @Override
    public int getCount() {
        return mDatas.size() > (curIndex + 1) * pageSize ? pageSize : (mDatas.size() - curIndex * pageSize);
    }

    @Override
    public GiftModel getItem(int position) {
        return mDatas.get(position + curIndex * pageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + curIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.room_gv_gift_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_gift_name = (TextView) convertView.findViewById(R.id.tv_gift_name);
            viewHolder.tv_gift_price = (TextView) convertView.findViewById(R.id.tv_gift_price);
            viewHolder.iv_gift_pic = (ImageView) convertView.findViewById(R.id.iv_gift_pic);
            viewHolder.item_layout = (ConstraintLayout) convertView.findViewById(R.id.cl_gift);
            viewHolder.tv_gift_num = convertView.findViewById(R.id.tv_gift_num);
            viewHolder.tv_gift_change_love_values = convertView.findViewById(R.id.tv_gift_change_love_values);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /**
         * 在给View绑定显示的数据时，计算正确的position = position + curIndex * pageSize，
         */
        if (type == 1) {
            viewHolder.tv_gift_num.setVisibility(View.VISIBLE);
            viewHolder.tv_gift_change_love_values.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tv_gift_num.setVisibility(View.INVISIBLE);
            viewHolder.tv_gift_change_love_values.setVisibility(View.VISIBLE);
        }

            GiftModel giftModel = getItem(position);
        //设置礼物数量
        viewHolder.tv_gift_num.setText("x" + giftModel.getNumber());
        //设置礼物名字
        viewHolder.tv_gift_name.setText(giftModel.getName());
        //设置礼物价格
        String surplusTxt = giftModel.getPrice() + "金币";
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(surplusTxt);
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(mContext.getResources().getColor(R.color.color_FFB2B2B2));
        stringBuilder.setSpan(redSpan, surplusTxt.length() - 2, surplusTxt.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//修改最后两个字体的颜色
        viewHolder.tv_gift_price.setText(stringBuilder);

        //加载礼物图片
        ImageUtils.loadImageView(giftModel.getPicture(), viewHolder.iv_gift_pic);
        Glide.with(mContext).load(giftModel.getPicture());
        //设置选中后的样式
        if (giftModel.isChecked()) {//被选中
            viewHolder.item_layout.setBackgroundResource(R.drawable.room_gift_select_bg);
        } else {
            viewHolder.item_layout.setBackgroundResource(0);
        }
        //设置
        //设置礼物心动值
        if (giftModel.getCardiac() < 0) {
            viewHolder.tv_gift_change_love_values.setBackgroundResource(R.drawable.room_bg_gift_xd_reduce);
            viewHolder.tv_gift_change_love_values.setText(String.format("-%s", giftModel.getCardiac()));
        } else {
            viewHolder.tv_gift_change_love_values.setBackgroundResource(R.drawable.room_bg_gift_xd_add);
            viewHolder.tv_gift_change_love_values.setText(String.format("+%s", giftModel.getCardiac()));
        }

        if (giftModel.isManghe()){
            viewHolder.tv_gift_change_love_values.setVisibility(View.GONE);
        }
        return convertView;
    }


    class ViewHolder {
        public ConstraintLayout item_layout;
        public TextView tv_gift_name, tv_gift_price, tv_gift_num;
        public ImageView iv_gift_pic;
        public TextView tv_gift_change_love_values;
    }
}
