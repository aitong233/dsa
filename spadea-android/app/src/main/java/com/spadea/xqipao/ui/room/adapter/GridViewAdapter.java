package com.spadea.xqipao.ui.room.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spadea.xqipao.data.GiftModel;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

import java.util.List;


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
            convertView = inflater.inflate(R.layout.item_gridview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
            viewHolder.item_layout = (ConstraintLayout) convertView.findViewById(R.id.cl);
            viewHolder.tv_num = convertView.findViewById(R.id.tv_num);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /**
         * 在给View绑定显示的数据时，计算正确的position = position + curIndex * pageSize，
         */
        if (type == 1) {
            viewHolder.tv_num.setVisibility(View.VISIBLE);
        }

        GiftModel giftModel = getItem(position);
        viewHolder.tv_num.setText("x" + giftModel.getNumber());
        viewHolder.tv_name.setText(giftModel.getName());
        viewHolder.tv_price.setText(giftModel.getPrice() + "金币");

        ImageLoader.loadImage(mContext, viewHolder.iv, giftModel.getPicture());
        Glide.with(mContext).load(giftModel.getPicture());
        if (giftModel.isChecked()) {//被选中
            viewHolder.item_layout.setBackgroundResource(R.drawable.bg_r4_fff6fb_colormain);
        } else {
            viewHolder.item_layout.setBackgroundResource(0);
        }
        return convertView;
    }


    class ViewHolder {
        public ConstraintLayout item_layout;
        public TextView tv_name, tv_price, tv_num;
        public ImageView iv;
    }

}