package com.spadea.xqipao.utils.popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.spadea.yuyin.R;


public class RoomMorePopupWindow extends PopupWindow implements View.OnClickListener {


    private View rootView;
    private final LinearLayout llMixer;
    private final LinearLayout llMusic;
    private final LinearLayout llRoomInfo;
    private final LinearLayout llPassword;
    private final LinearLayout llPublic;


    private RoomMorePopupClickListener mRoomMorePopupClickListener;


    public RoomMorePopupWindow(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        rootView = inflater.inflate(R.layout.popup_room_more, null);
        llMixer = rootView.findViewById(R.id.ll_mixer);
        llMusic = rootView.findViewById(R.id.ll_music);
        llRoomInfo = rootView.findViewById(R.id.ll_room_info);
        llPassword = rootView.findViewById(R.id.ll_password);
        llPublic = rootView.findViewById(R.id.ll_public);
        setContentView(rootView);

        llMixer.setOnClickListener(this);
        llMusic.setOnClickListener(this);
        llRoomInfo.setOnClickListener(this);
        llPassword.setOnClickListener(this);
        llPublic.setOnClickListener(this);
        this.setBackgroundDrawable(new BitmapDrawable());
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setTouchable(true);
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_mixer:
                mRoomMorePopupClickListener.mixer();
                break;
            case R.id.ll_music:
                mRoomMorePopupClickListener.music();
                break;
            case R.id.ll_room_info:
                mRoomMorePopupClickListener.roomInfo();
                break;
            case R.id.ll_password:
                mRoomMorePopupClickListener.roomPassword();
                break;
            case R.id.ll_public:
                mRoomMorePopupClickListener.clearPublic();
                break;
        }
        this.dismiss();
    }

    public void setmRoomMorePopupClickListener(RoomMorePopupClickListener mRoomMorePopupClickListener) {
        this.mRoomMorePopupClickListener = mRoomMorePopupClickListener;
    }

    public void setData(int type, int state) {
//        0不在麦位  1在麦位
        if (state == 0) {
            llMixer.setVisibility(View.GONE);
            llMusic.setVisibility(View.GONE);
        } else {
            llMixer.setVisibility(View.VISIBLE);
            llMusic.setVisibility(View.VISIBLE);
        }

        //0不是管理员或房主    1是管理员或房主
        if (type == 0) {
            llRoomInfo.setVisibility(View.GONE);
            llPassword.setVisibility(View.GONE);
            llPublic.setVisibility(View.GONE);
        } else {
            llRoomInfo.setVisibility(View.VISIBLE);
            llPassword.setVisibility(View.VISIBLE);
            llPublic.setVisibility(View.VISIBLE);
        }
    }

    public interface RoomMorePopupClickListener {
        void mixer();

        void music();

        void roomInfo();

        void roomPassword();

        void clearPublic();

        void roomBackgroud();
    }

}
