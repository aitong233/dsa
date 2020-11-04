package com.qpyy.module.me.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.libcommon.widget.dialog.BaseBottomSheetDialog;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.bean.CityResp;
import com.qpyy.module.me.bean.RegionListResp;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.defaults.view.PickerView;

public class SelectCityDialog extends BaseBottomSheetDialog {


    @BindView(R2.id.pickerView_province)
    PickerView pickerViewProvince;
    @BindView(R2.id.pickerView_city)
    PickerView pickerViewCity;

    private OnSelectCity mOnSelectCity;

    public SelectCityDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.me_dialog_select_city;
    }

    @Override
    public void initView() {
        this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);
    }

    @Override
    public void initData() {
        pickerViewProvince.setAdapter(new PickerView.Adapter<PickerView.PickerItem>() {
            @Override
            public int getItemCount() {
                return 0;
            }

            @Override
            public PickerView.PickerItem getItem(int index) {
                return null;
            }
        });
        pickerViewCity.setAdapter(new PickerView.Adapter<PickerView.PickerItem>() {
            @Override
            public int getItemCount() {
                return 0;
            }

            @Override
            public PickerView.PickerItem getItem(int index) {
                return null;
            }
        });
    }

    public void setProvince(List<RegionListResp> data) {
        pickerViewProvince.setItems(data, new PickerView.OnItemSelectedListener<RegionListResp>() {
            @Override
            public void onItemSelected(RegionListResp item) {
                setCity(item.getChildren());
            }
        });
        setCity(data.get(0).getChildren());
    }

    public void setCity(List<CityResp> data) {
        pickerViewCity.setItems(data, new PickerView.OnItemSelectedListener<CityResp>() {
            @Override
            public void onItemSelected(CityResp item) {

            }
        });
        pickerViewCity.notifyDataSetChanged();
    }


    @OnClick({R2.id.tv_cancel, R2.id.tv_confirm})
    public void onClick(View view) {
        dismiss();
        if (view.getId() == R.id.tv_confirm) {
            if (mOnSelectCity != null) {
                RegionListResp provinceSelectedItem = pickerViewProvince.getSelectedItem(RegionListResp.class);
                CityResp citySelectedItem = pickerViewCity.getSelectedItem(CityResp.class);
                mOnSelectCity.onSelectData(provinceSelectedItem, citySelectedItem);
            }
        }
    }

    public void setOnSelectCity(OnSelectCity onSelectCity) {
        this.mOnSelectCity = onSelectCity;
    }

    public interface OnSelectCity {
        void onSelectData(RegionListResp province, CityResp city);
    }
}
