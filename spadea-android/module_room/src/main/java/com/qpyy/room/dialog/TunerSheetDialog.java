package com.qpyy.room.dialog;

import android.content.Context;
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
import com.qpyy.libcommon.widget.dialog.BaseBottomSheetDialog;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.TunerListAdapter;
import com.qpyy.room.bean.TunerBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.agora.rtc.Constants;


/**
 * 调音台弹窗
 */
public class TunerSheetDialog extends BaseBottomSheetDialog {

    private static final String TAG = "TunerSheetDialog";
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.sw_monitoring)
    Switch swMonitoring;
    @BindView(R2.id.rv_effect_style_list)
    RecyclerView rvEffectStyleList;

    private Context mContext;


    private TunerListAdapter tunerAdapter;
    private TunreOnIremClick mTunreOnIremClick;
    private List<TunerBean> tunerList;

    public TunerSheetDialog(@NonNull Context context) {
        super(context);
        Log.d(TAG, "(Start)启动了===========================TunerSheetDialog");
    }

    @Override
    public int getLayout() {
        return R.layout.room_dialog_tuner;
    }

    @Override
    public void initView() {
        rvEffectStyleList.setLayoutManager(new GridLayoutManager(mContext, 4));
        rvEffectStyleList.setAdapter(tunerAdapter = new TunerListAdapter());
        rvEffectStyleList.addItemDecoration(new GridSpacingItemDecoration(4, 30, true));
//        tunerAdapter.setNewData(getData());
        tunerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                TunerBean item = tunerAdapter.getItem(position);
//                tunerAdapter.setIndex(item.getType());
//                for (TunerBean tuner : tunerList){
//                    tuner.setSelect(false);
//                }
//                item.setSelect(true);//选中
//                if (mTunreOnIremClick != null) {
//                    switch (item.getType()) {
//                        case 0:
//                            mTunreOnIremClick.setClodeLocalVoice();
//                            break;
//                        case 1:
//                            //大叔
//                            mTunreOnIremClick.setLocalVoiceChanger(Constants.VOICE_CHANGER_OLDMAN);
//                            break;
//                        case 2:
//                            //小男孩 正太
//                            mTunreOnIremClick.setLocalVoiceChanger(Constants.VOICE_CHANGER_BABYBOY);
//                            break;
//                        case 3:
//                            //萝莉
//                            mTunreOnIremClick.setLocalVoiceChanger(Constants.VOICE_CHANGER_BABYGIRL);
//                            break;
//                        case 4:
//                            //空灵
//                            mTunreOnIremClick.setLocalVoiceChanger(Constants.VOICE_CHANGER_ETHEREAL);
//                            break;
//                        case 5:
//                            //演唱会
//                            mTunreOnIremClick.setLocalVoiceReverbPreset(Constants.AUDIO_REVERB_VOCAL_CONCERT);
//                            break;
//                        case 6:
//                            //ktv
//                            mTunreOnIremClick.setLocalVoiceReverbPreset(Constants.AUDIO_REVERB_KTV);
//                            break;
//                        case 7:
//                            //录音棚
//                            mTunreOnIremClick.setLocalVoiceReverbPreset(Constants.AUDIO_REVERB_STUDIO);
//                            break;
//                    }
//                }
            }
        });
        swMonitoring.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mTunreOnIremClick != null) {
                    mTunreOnIremClick.enableInEarMonitoring(b);
                }
            }
        });
    }

    @Override
    public void initData() {
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        window.setWindowAnimations(0);
        layoutParams.dimAmount = 0.4f;
        window.setGravity(Gravity.BOTTOM);
    }


    private List<TunerBean> getData() {
        tunerList = new ArrayList<>();
        TunerBean tuner1 = new TunerBean("原声", 0,R.mipmap.room_tuner_origin,true);
        TunerBean tuner2 = new TunerBean("大叔", 1,R.mipmap.room_tuner_uncle,false);
        TunerBean tuner3 = new TunerBean("正太", 2,R.mipmap.room_tuner_boy,false);
        TunerBean tuner4 = new TunerBean("萝莉", 3,R.mipmap.room_tuner_loli,false);
        TunerBean tuner5 = new TunerBean("空灵", 4,R.mipmap.room_tuner_intangible,false);
        TunerBean tuner6 = new TunerBean("演唱会", 5,R.mipmap.room_tuner_concert,false);
        TunerBean tuner7 = new TunerBean("KTV", 6,R.mipmap.room_tuner_ktv,false);
        TunerBean tuner8 = new TunerBean("录音棚", 7,R.mipmap.room_tuner_studio,false);
        tunerList.add(tuner1);
        tunerList.add(tuner2);
        tunerList.add(tuner3);
        tunerList.add(tuner4);
        tunerList.add(tuner5);
        tunerList.add(tuner6);
        tunerList.add(tuner7);
        tunerList.add(tuner8);
        return tunerList;
    }

    public void addTunreOnIremClick(TunreOnIremClick tunreOnIremClick) {
        mTunreOnIremClick = tunreOnIremClick;
    }


    public interface TunreOnIremClick {
        void setLocalVoiceChanger(int type);

        void setLocalVoiceReverbPreset(int type);

        void setClodeLocalVoice();

        void enableInEarMonitoring(boolean b);
    }


}