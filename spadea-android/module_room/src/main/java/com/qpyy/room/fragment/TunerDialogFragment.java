package com.qpyy.room.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.base.IPresenter;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.libcommon.widget.dialog.BaseBottomSheetDialog;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.TunerListAdapter;
import com.qpyy.room.bean.MixerResp;
import com.qpyy.room.bean.TunerBean;
import com.qpyy.room.contacts.TunerContacts;
import com.qpyy.room.presenter.TunerPresenter;
import com.qpyy.rtc.RtcManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.agora.rtc.Constants;


/**
 * 调音台弹窗
 */
public class TunerDialogFragment extends BaseMvpDialogFragment<TunerPresenter> implements TunerContacts.View {

    private static final String TAG = "TunerSheetDialog";
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.sw_monitoring)
    Switch swMonitoring;
    @BindView(R2.id.rv_effect_style_list)
    RecyclerView rvEffectStyleList;

    private Context mContext;


    private String roomId;
    private int tunerType;
    private TunerListAdapter tunerAdapter;

    public static TunerDialogFragment instantiate(String roomId, int tunerType) {
        TunerDialogFragment tunerDialogFragment = new TunerDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomId", roomId);
        bundle.putInt("tunerType", tunerType);
        tunerDialogFragment.setArguments(bundle);
        return tunerDialogFragment;
    }


    @Override
    public void initView() {
        rvEffectStyleList.setLayoutManager(new GridLayoutManager(mContext, 4));
        rvEffectStyleList.setAdapter(tunerAdapter = new TunerListAdapter());
        rvEffectStyleList.addItemDecoration(new GridSpacingItemDecoration(4, 30, true));
        tunerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MixerResp item = tunerAdapter.getItem(position);
                tunerAdapter.setIndex(position);
                MvpPre.setUserMixer(roomId, item.getId());
                RtcManager.getInstance().setTone(item.getId());
            }
        });
        swMonitoring.setChecked(SpUtils.getAuricularBack() == 1 ? true : false);//设置耳返开启还是关闭
        swMonitoring.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                RtcManager.getInstance().enableHeadphoneMonitor(b);
                //耳返设置完保存本地
                SpUtils.setAuricularBack(b ? 1 : 0);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_dialog_tuner;
    }

    @Override
    protected TunerPresenter bindPresenter() {
        return new TunerPresenter(this, getActivity());
    }

    @Override
    public void initData() {
        roomId = getArguments().getString("roomId");
        tunerType = getArguments().getInt("tunerType");
        MvpPre.mixer();
    }


    @Override
    protected void initDialogStyle(Window window) {
        super.initDialogStyle(window);
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0.4f;
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


    @Override
    public void setMixerData(List<MixerResp> mixerResps) {
        tunerAdapter.setNewData(mixerResps);
        tunerAdapter.setIndex(tunerType);
    }

}