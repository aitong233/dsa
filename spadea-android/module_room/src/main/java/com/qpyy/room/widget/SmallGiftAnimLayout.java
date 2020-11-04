package com.qpyy.room.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.qpyy.libcommon.bean.RoomGiveGiftModel;
import com.qpyy.room.R;
import com.qpyy.room.R2;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.widget
 * 创建人 王欧
 * 创建时间 2020/8/12 2:44 PM
 * 描述 describe
 */
public class SmallGiftAnimLayout extends LinearLayout {
    @BindView(R2.id.sgav1)
    SmallGiftAnimView mSgav1;
    @BindView(R2.id.sgav2)
    SmallGiftAnimView mSgav2;
    @BindView(R2.id.sgav3)
    SmallGiftAnimView mSgav3;

    public SmallGiftAnimLayout(Context context) {
        this(context, null);
    }

    public SmallGiftAnimLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.room_layout_small_gift_anim, this);
        ButterKnife.bind(this);
    }

    public void addGift(RoomGiveGiftModel.GiftListBean gift) {
        if (mSgav1.animEnded) {
            mSgav1.addAnim(gift);
        } else if (mSgav2.animEnded) {
            mSgav2.addAnim(gift);
        } else if (mSgav3.animEnded) {
            mSgav3.addAnim(gift);
        } else {
            int i = new Random().nextInt(3);
            switch (i) {
                case 0:
                    mSgav1.addAnim(gift);
                    break;
                case 1:
                    mSgav2.addAnim(gift);
                    break;
                case 2:
                    mSgav3.addAnim(gift);
                    break;
            }
        }

    }
}
