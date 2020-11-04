package com.spadea.xqipao.utils.view.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spadea.yuyin.R;
import com.spadea.xqipao.data.WheatModel;
import com.spadea.xqipao.utils.view.MarqueeTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomHeadView extends FrameLayout {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    MarqueeTextView tvTitle;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_popularity)
    TextView tvPopularity;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.iv_collection)
    ImageView ivCollection;
    @BindView(R.id.img_lock)
    ImageView imgLock;
    @BindView(R.id.iv_more)
    ImageView ivMore;

    private WheatModel wheatModel;
    private RoomHeadViewClickListener mRoomHeadViewClickListener;

    private int favorite = 0;

    public RoomHeadView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public RoomHeadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initTypeValue(context, attrs);
        initView(context);
    }

    public RoomHeadView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeValue(context, attrs);
        initView(context);
    }

    private void initTypeValue(Context context, AttributeSet attrs) {

    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_room_head, this, true);
        ButterKnife.bind(this);
    }

    public void setRoomName(String roomName) {
        if (!TextUtils.isEmpty(roomName)) {
            tvTitle.setText(roomName);
        } else {
            tvTitle.setText("暂无");
        }
    }

    public void setRoomLock(int isPassword) {
        if (isPassword==0) {
            imgLock.setVisibility(GONE);
        } else {
            imgLock.setVisibility(VISIBLE);
        }
    }

    public void setRoomeType(String type) {
        if (!TextUtils.isEmpty(type)) {
            tvType.setText(type);
        } else {
            tvType.setText("未知");
        }
    }


    public void setRoomePopularity(String popularity) {
        if (!TextUtils.isEmpty(popularity)) {
            tvPopularity.setText(popularity);
        } else {
            tvPopularity.setText("0");
        }
    }

    public void setRoomeId(String roomeId) {
        if (!TextUtils.isEmpty(roomeId)) {
            tvId.setText("ID: " + roomeId);
        } else {
            tvId.setText("暂无");
        }
    }


    @OnClick({R.id.iv_more, R.id.iv_collection, R.id.iv_back})
    public void onClick(View view) {
        if (mRoomHeadViewClickListener != null) {
            switch (view.getId()) {
                case R.id.iv_more:
                    mRoomHeadViewClickListener.onMore();
                    break;
                case R.id.iv_collection:
                    if (favorite == 0) {
                        mRoomHeadViewClickListener.onCollection(1);
                    } else {
                        mRoomHeadViewClickListener.onCollection(0);
                    }
                    break;
                case R.id.iv_back:
                    mRoomHeadViewClickListener.back();
                    break;
            }
        }
    }


    public void setmRoomHeadViewClickListener(RoomHeadViewClickListener mRoomHeadViewClickListener) {
        this.mRoomHeadViewClickListener = mRoomHeadViewClickListener;
    }

    public void setRoomFavorite(int favorite) {
        if (favorite == 1) {
            ivCollection.setImageLevel(1);
        } else {
            ivCollection.setImageLevel(0);
        }
        this.favorite = favorite;
    }


    public interface RoomHeadViewClickListener {
        void onCollection(int favorite);

        void onMore();

        void back();
    }

}
