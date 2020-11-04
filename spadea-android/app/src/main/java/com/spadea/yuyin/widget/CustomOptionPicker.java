package com.spadea.yuyin.widget;

import android.app.Activity;

import com.spadea.yuyin.R;

import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Copyright (c) 1
 *
 * @Description
 * @Author 1
 * @Copyright Copyright (c) 1
 * @Date $date$ $time$
 */

public class CustomOptionPicker extends OptionPicker {
    public CustomOptionPicker(Activity activity, String[] items) {
        super(activity, items);
        setSubmitTextColor(activity.getResources().getColor(R.color.color_main));
        setCancelTextColor(activity.getResources().getColor(R.color.color_main));
        setPressedTextColor(activity.getResources().getColor(R.color.color_main));
        setDividerColor(activity.getResources().getColor(R.color.color_c9));
        setTopLineColor(activity.getResources().getColor(R.color.color_c9));
        setTextColor(activity.getResources().getColor(R.color.color_text));
    }

    public CustomOptionPicker(Activity activity, List<String> items) {
        super(activity, items);
        setSubmitTextColor(activity.getResources().getColor(R.color.color_main));
        setCancelTextColor(activity.getResources().getColor(R.color.color_main));
        setPressedTextColor(activity.getResources().getColor(R.color.color_main));
        setDividerColor(activity.getResources().getColor(R.color.color_c9));
        setTopLineColor(activity.getResources().getColor(R.color.color_c9));
        setTextColor(activity.getResources().getColor(R.color.color_text));
    }
}
