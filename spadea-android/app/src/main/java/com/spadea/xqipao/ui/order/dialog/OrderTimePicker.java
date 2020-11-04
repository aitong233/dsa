package com.spadea.xqipao.ui.order.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.TimeUtils;
import com.spadea.xqipao.ui.order.adapter.ArrayWheelAdapter;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.utils.dialog.BaseDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.order.dialog
 * 创建人 王欧
 * 创建时间 2020/6/6 1:05 PM
 * 描述 describe
 */
public class OrderTimePicker extends BaseDialog {
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;
    @BindView(R.id.wv_day)
    WheelView mWvDay;
    @BindView(R.id.wv_hour)
    WheelView mWvHour;
    @BindView(R.id.wv_minute)
    WheelView mWvMinute;
    private List<String> mDayItems;
    private List<String> mFirstMinuteItems;
    private List<String> mMinuteItems;
    private List<String> mHourItems;
    private List<String> mFirstHourItems;

    private OnSelectedListener mOnSelectedListener;

    private static final String TODAY = "今天";
    private static final String TOMORROW = "明天";
    private static final String AFTER_TOMORROW = "后天";


    public OrderTimePicker(@NonNull Context context, OnSelectedListener selectedListener) {
        super(context);
        mOnSelectedListener = selectedListener;
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_order_time_picker;
    }

    @Override
    public void initView() {
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setWindowAnimations(R.style.ShowDialogBottom);
    }

    @Override
    public void initData() {
        initDateDatas();
        mWvDay.setCyclic(false);
        mWvHour.setCyclic(false);
        mWvMinute.setCyclic(false);
        initDays();
    }

    private void initDateDatas() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        mDayItems = new ArrayList<>();
        if (hour == 23 && minute > 45) {
            hour = 0;
            minute = 0;
            LogUtils.e("initDateDatas", "当天无可选时间");
        } else {
            mDayItems.add(TODAY);
        }
        mDayItems.add(TOMORROW);
        mDayItems.add(AFTER_TOMORROW);
        mFirstHourItems = new ArrayList<>();
        for (int i = hour; i < 24; i++) {
            if (i == hour && minute > 45) {
                continue;
            }
            if (i < 10) {
                mFirstHourItems.add(0 + String.valueOf(i));
            } else {
                mFirstHourItems.add(String.valueOf(i));
            }
        }
        mHourItems = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                mHourItems.add(0 + String.valueOf(i));
            } else {
                mHourItems.add(String.valueOf(i));
            }
        }
        if (minute > 45) {
            minute = 0;
        }
        mFirstMinuteItems = new ArrayList<>();
        for (int i = minute; i < 60; i++) {
            if (i % 15 == 0) {
                if (i == 0) {
                    mFirstMinuteItems.add("00");
                } else {
                    mFirstMinuteItems.add(String.valueOf(i));
                }
            }
        }
        mMinuteItems = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if (i % 15 == 0) {
                if (i == 0) {
                    mMinuteItems.add("00");
                } else {
                    mMinuteItems.add(String.valueOf(i));
                }
            }
        }
    }

    private void initDays() {
        mWvDay.setAdapter(new ArrayWheelAdapter(mDayItems));
        mWvHour.setAdapter(new ArrayWheelAdapter(mFirstHourItems));
        mWvMinute.setAdapter(new ArrayWheelAdapter(mFirstMinuteItems));
        mWvDay.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (index == 0) {
                    mWvHour.setAdapter(new ArrayWheelAdapter(mFirstHourItems));
                } else {
                    mWvHour.setAdapter(new ArrayWheelAdapter(mHourItems));
                }
            }
        });
        mWvHour.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (index == 0 && mWvDay.getCurrentItem() == 0) {
                    mWvMinute.setAdapter(new ArrayWheelAdapter(mFirstMinuteItems));
                } else {
                    mWvMinute.setAdapter(new ArrayWheelAdapter(mMinuteItems));
                }
            }
        });
        mWvMinute.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {

            }
        });
    }

    @OnClick({R.id.tv_cancel, R.id.tv_confirm})
    public void onViewClicked(View view) {
        dismiss();
        switch (view.getId()) {
            case R.id.tv_cancel:
                break;
            case R.id.tv_confirm:
                Calendar calendar = Calendar.getInstance();
                String dayStr = mWvDay.getSelectedItemStr();
                int day = 0;
                if (TOMORROW.equals(dayStr)) {
                    day = 1;
                } else if (AFTER_TOMORROW.equals(dayStr)) {
                    day = 2;
                }
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                String hourStr = mWvHour.getSelectedItemStr();
                String minuteStr = mWvMinute.getSelectedItemStr();
                if (!TextUtils.isEmpty(hourStr)) {
                    hour = Integer.parseInt(hourStr);
                }
                int minute = calendar.get(Calendar.MINUTE);
                if (!TextUtils.isEmpty(minuteStr)) {
                    minute = Integer.parseInt(minuteStr);
                }
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                if (mOnSelectedListener != null) {
                    long timeMillis = calendar.getTimeInMillis() + day * 24 * 60 * 60 * 1000;
                    LogUtils.e("选中时间", TimeUtils.millis2String(timeMillis));
                    String minuteStar = minute > 9 ? String.valueOf(minute) : String.format("0%s", minute);
                    String hourStar = hour > 9 ? String.valueOf(hour) : String.format("0%s", hour);
                    mOnSelectedListener.onSelected(timeMillis, String.format("%s %s:%s", mWvDay.getSelectedItemStr(), hourStar, minuteStar));
                }
                break;
        }
    }

    public interface OnSelectedListener {
        void onSelected(long time, String timeStr);
    }
}
