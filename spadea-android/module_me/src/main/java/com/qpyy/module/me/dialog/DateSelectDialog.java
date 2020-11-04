package com.qpyy.module.me.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.qpyy.libcommon.widget.dialog.BaseBottomSheetDialog;
import com.qpyy.libcommon.utils.TimeUtils;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.bean.DateBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.defaults.view.PickerView;

/**
 * 日期选择
 */
public class DateSelectDialog extends BaseBottomSheetDialog {

    @BindView(R2.id.pickerView_year)
    PickerView pickerViewYear;
    @BindView(R2.id.pickerView_month)
    PickerView pickerViewMonth;
    @BindView(R2.id.pickerView_day)
    PickerView pickerViewDay;


    private int year = 0;
    private int month = 0;
    private OnSelectDate mOnSelectDate;
    private List<DateBean> yearList;
    private List<DateBean> monthList;
    private List<DateBean> dayList;

    public DateSelectDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.me_dialog_date_select;
    }

    @Override
    public void initView() {
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
    }

    @Override
    public void initData() {
        setYear();
        pickerViewYear.setSelectedItemPosition(yearList.size());
        setMonth(TimeUtils.getYear());
        pickerViewMonth.setSelectedItemPosition(monthList.size());
        setDay(TimeUtils.getYear(), TimeUtils.getMonth());
        pickerViewDay.setSelectedItemPosition(dayList.size());
    }

    public void setData(String y, String m, String d) {
        int yIndex = 0;
        for (int i = 0; i < yearList.size(); i++) {
            DateBean dateBean = yearList.get(i);
            if (dateBean.getText().equals(y)) {
                yIndex = i;
            }
        }
        pickerViewYear.setSelectedItemPosition(yIndex);
        monthList = getMonth(Integer.parseInt(y));
        int mIndex = 0;
        for (int i = 0; i < monthList.size(); i++) {
            DateBean dateBean = monthList.get(i);
            if (dateBean.getText().equals(m)) {
                mIndex = i;
            }
        }
        pickerViewMonth.setSelectedItemPosition(mIndex);
        dayList = getDay(Integer.parseInt(y), Integer.parseInt(m));
        int dIndex = 0;
        for (int i = 0; i < dayList.size(); i++) {
            DateBean dateBean = dayList.get(i);
            if (dateBean.getText().equals(d)) {
                dIndex = i;
            }
        }
        pickerViewDay.setSelectedItemPosition(dIndex);
    }


    private void setDay(int year, int month) {
        dayList = getDay(year, month);
        pickerViewDay.setItems(dayList, new PickerView.OnItemSelectedListener<DateBean>() {
            @Override
            public void onItemSelected(DateBean item) {

            }
        });
        pickerViewDay.notifyDataSetChanged();
    }

    private void setMonth(int year) {
        monthList = getMonth(year);
        pickerViewMonth.setItems(monthList, new PickerView.OnItemSelectedListener<DateBean>() {
            @Override
            public void onItemSelected(DateBean item) {
                month = item.getDate();
                setDay(year, month);
            }
        });
        pickerViewMonth.notifyDataSetChanged();
    }

    private void setYear() {
        yearList = getYear();
        pickerViewYear.setItems(yearList, new PickerView.OnItemSelectedListener<DateBean>() {
            @Override
            public void onItemSelected(DateBean item) {
                year = item.getDate();
                setMonth(year);
            }
        });
        pickerViewYear.notifyDataSetChanged();
    }


    private List<DateBean> getDay(int year, int month) {
        int day = TimeUtils.getDaysByYearMonth(year, month);
        if (year == TimeUtils.getYear() && month == TimeUtils.getMonth()) {
            day = TimeUtils.getDay();
        }
        List<DateBean> dayList = new ArrayList<>();
        for (int i = 1; i <= day; i++) {
            if (i <= 9) {
                dayList.add(new DateBean("0" + i, i));
            } else {
                dayList.add(new DateBean(String.valueOf(i), i));
            }
        }
        return dayList;
    }

    private List<DateBean> getMonth(int year) {
        List<DateBean> mothList = new ArrayList<>();
        int month = 12;
        if (year == TimeUtils.getYear()) {
            month = TimeUtils.getMonth();
        }
        for (int i = 1; i <= month; i++) {
            if (i <= 9) {
                mothList.add(new DateBean("0" + i, i));
            } else {
                mothList.add(new DateBean(String.valueOf(i), i));
            }
        }
        return mothList;
    }

    private List<DateBean> getYear() {
        int year = TimeUtils.getYear();
        List<DateBean> yearList = new ArrayList<>();
        for (int i = 1900; i <= year; i++) {
            yearList.add(new DateBean(String.valueOf(i), i));
        }
        return yearList;
    }


    @OnClick({R2.id.tv_cancel, R2.id.tv_confirm})
    public void onClick(View view) {
        dismiss();
        if (view.getId() == R.id.tv_confirm) {
            if (mOnSelectDate != null) {
                DateBean year = pickerViewYear.getSelectedItem(DateBean.class);
                DateBean month = pickerViewMonth.getSelectedItem(DateBean.class);
                DateBean day = pickerViewDay.getSelectedItem(DateBean.class);
                mOnSelectDate.selectDate(year.getText(), month.getText(), day.getText());
            }
        }
    }

    public void setmOnSelectDate(OnSelectDate mOnSelectDate) {
        this.mOnSelectDate = mOnSelectDate;
    }

    public interface OnSelectDate {
        void selectDate(String year, String month, String day);
    }
}
