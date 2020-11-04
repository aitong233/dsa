package com.spadea.xqipao.utils.dialog.room;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spadea.xqipao.utils.popupwindow.RoomMorePopupWindow;
import com.spadea.yuyin.R;
import com.spadea.xqipao.utils.dialog.BaseBottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.utils.dialog.room
 * 创建人 王欧
 * 创建时间 2020/4/14 3:18 PM
 * 描述 describe
 */
public class RoomOpMoreDialog extends BaseBottomSheetDialog {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private boolean isHost;
    private boolean onPit;

    private RoomMorePopupWindow.RoomMorePopupClickListener mRoomMorePopupClickListener;
    private BaseQuickAdapter<ItemBean, BaseViewHolder> mAdapter;

    public void setRoomMorePopupClickListener(RoomMorePopupWindow.RoomMorePopupClickListener roomMorePopupClickListener) {
        mRoomMorePopupClickListener = roomMorePopupClickListener;
    }

    public RoomOpMoreDialog(@NonNull Context context) {
        super(context);
    }

    public void setData(boolean isHost, boolean onPit) {
        this.onPit = onPit;
        this.isHost = isHost;
        mAdapter.setNewData(generateData());
    }

    @Override
    public int getLayout() {
        return R.layout.doalog_room_op_more;
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 5));
        mAdapter = new BaseQuickAdapter<ItemBean, BaseViewHolder>(R.layout.rv_item_room_op_more) {
            @Override
            protected void convert(BaseViewHolder helper, ItemBean item) {
                helper.setImageResource(R.id.iv_icon, item.icon);
                helper.setText(R.id.tv_title, item.title);
            }
        };
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ItemBean item = (ItemBean) adapter.getItem(position);
                if (item == null || mRoomMorePopupClickListener == null) {
                    return;
                }
                switch (item.position) {
                    case 0:
                        mRoomMorePopupClickListener.roomInfo();
                        break;
                    case 1:
                        mRoomMorePopupClickListener.roomPassword();
                        break;
                    case 2:
                        mRoomMorePopupClickListener.clearPublic();
                        break;
                    case 3:
                        mRoomMorePopupClickListener.music();
                        break;
                    case 4:
                        mRoomMorePopupClickListener.mixer();
                        break;
                    case 5:
                        mRoomMorePopupClickListener.roomBackgroud();
                        break;
                }
                dismiss();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    private List<ItemBean> generateData() {
        List<ItemBean> itemBeans = new ArrayList<>();
        if (isHost) {
            itemBeans.add(new ItemBean(0, "房间信息", R.mipmap.ic_room_info));
            itemBeans.add(new ItemBean(1, "设置密码", R.mipmap.ic_set_room_pwd));
            itemBeans.add(new ItemBean(2, "清理公屏", R.mipmap.ic_clear_public_screen));
        }
        if (onPit) {
            itemBeans.add(new ItemBean(3, "播放器", R.mipmap.ic_room_music_play));
            itemBeans.add(new ItemBean(4, "调音台", R.mipmap.ic_room_tyt));
        }
        if (isHost) {
            itemBeans.add(new ItemBean(5, "切换背景", R.mipmap.ic_switch_room_bg));
        }

        return itemBeans;

    }

    private static class ItemBean {
        int position;
        String title;
        @DrawableRes
        int icon;

        ItemBean(int position, String title, int icon) {
            this.position = position;
            this.title = title;
            this.icon = icon;
        }
    }
}
