package com.hyphenate.easeui.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.easeui.R;

public class LevelUtils {


    public static void setlevel(String rank_id, LinearLayout ll_level, ImageView iv_level, TextView tv_level) {
        switch (rank_id) {
            case "0":
                ll_level.setVisibility(View.GONE);
                break;
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "10":
                ll_level.setVisibility(View.VISIBLE);
                ll_level.setBackgroundResource(R.drawable.room_icon_vip_gray);
                iv_level.setImageResource(R.drawable.room_icon_v);
                iv_level.setVisibility(View.VISIBLE);
                tv_level.setText(rank_id);
                break;
            case "11":
            case "12":
            case "13":
            case "14":
            case "15":
            case "16":
            case "17":
            case "18":
            case "19":
            case "20":
                ll_level.setVisibility(View.VISIBLE);
                ll_level.setBackgroundResource(R.drawable.room_icon_vip_green);
                iv_level.setImageResource(R.drawable.room_icon_v);
                iv_level.setVisibility(View.VISIBLE);
                tv_level.setText(rank_id);
                break;
            case "21":
            case "22":
            case "23":
            case "24":
            case "25":
            case "26":
            case "27":
            case "28":
            case "29":
            case "30":
                ll_level.setVisibility(View.VISIBLE);
                ll_level.setBackgroundResource(R.drawable.room_icon_vip_blue);
                iv_level.setImageResource(R.drawable.room_icon_v);
                iv_level.setVisibility(View.VISIBLE);
                tv_level.setText(rank_id);
                break;
            case "31":
            case "32":
            case "33":
            case "34":
            case "35":
            case "36":
            case "37":
            case "38":
            case "39":
            case "40":
                ll_level.setVisibility(View.VISIBLE);
                ll_level.setBackgroundResource(R.drawable.room_icon_vip_orange);
                iv_level.setImageResource(R.drawable.room_icon_v);
                iv_level.setVisibility(View.VISIBLE);
                tv_level.setText(rank_id);
                break;
            case "41":
            case "42":
            case "43":
            case "44":
            case "45":
            case "46":
            case "47":
            case "48":
            case "49":
            case "50":
                ll_level.setVisibility(View.VISIBLE);
                ll_level.setBackgroundResource(R.drawable.room_icon_vip_violet);
                iv_level.setImageResource(R.drawable.room_icon_v);
                iv_level.setVisibility(View.VISIBLE);
                tv_level.setText(rank_id);
                break;
            case "皇冠1":
                ll_level.setVisibility(View.VISIBLE);
                ll_level.setBackgroundResource(R.drawable.room_icon_vip_hung1);
                iv_level.setImageResource(R.drawable.room_icon_v);
                iv_level.setVisibility(View.GONE);
                tv_level.setText(rank_id);
                break;
            case "皇冠2":
                ll_level.setVisibility(View.VISIBLE);
                ll_level.setBackgroundResource(R.drawable.room_icon_vip_hung2);
                iv_level.setImageResource(R.drawable.room_icon_v);
                iv_level.setVisibility(View.GONE);
                tv_level.setText(rank_id);
                break;
            case "皇冠3":
                ll_level.setVisibility(View.VISIBLE);
                ll_level.setBackgroundResource(R.drawable.room_icon_vip_hung3);
                iv_level.setImageResource(R.drawable.room_icon_v);
                iv_level.setVisibility(View.GONE);
                tv_level.setText(rank_id);
                break;
            case "王冠":
                ll_level.setVisibility(View.VISIBLE);
                ll_level.setBackgroundResource(R.drawable.room_icon_vip_wang);
                iv_level.setImageResource(R.drawable.room_icon_v);
                iv_level.setVisibility(View.GONE);
                tv_level.setText(rank_id);
                break;
        }
    }


}
