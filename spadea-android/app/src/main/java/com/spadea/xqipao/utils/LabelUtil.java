package com.spadea.xqipao.utils;

import android.text.TextUtils;
import android.widget.TextView;

import com.spadea.yuyin.R;

public class LabelUtil {

    //相亲、电台、娱乐、交友、点唱、派单、官方,聊天，k歌
    public static void setLabel(String labelName, TextView textView) {
        if (TextUtils.isEmpty(labelName)){
           labelName="未知";
        }
        textView.setText(labelName);
        switch (labelName) {
            case "男神":
                textView.setBackgroundResource(R.drawable.bg_boy);
                break;
            case "女神":
                textView.setBackgroundResource(R.drawable.bg_girl);
                break;
            case "官方":
                textView.setBackgroundResource(R.drawable.bg_official);
                break;
            case "相亲":
                textView.setBackgroundResource(R.drawable.bg_blind_date);
                break;
            case "电台":
                textView.setBackgroundResource(R.drawable.bg_radio_station);
                break;
            case "娱乐":
                textView.setBackgroundResource(R.drawable.bg_entertainment);
                break;
            case "交友":
                textView.setBackgroundResource(R.drawable.bg_make_friends);
                break;
            case "点唱":
                textView.setBackgroundResource(R.drawable.bg_request_number);
                break;
            case "派单":
                textView.setBackgroundResource(R.drawable.bg_distribute_leaflets);
                break;
            case "聊天":
                textView.setBackgroundResource(R.drawable.bg_mm);
                break;
            case "K歌":
                textView.setBackgroundResource(R.drawable.bg_k);
                break;
            default:
                textView.setBackgroundResource(R.drawable.bg_default);
                break;
        }
    }


}
