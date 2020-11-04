package com.spadea.xqipao.utils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spadea.xqipao.utils.view.itemdecoration.GridSpacingItemDecoration;
import com.spadea.yuyin.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.agora.rtc.Constants;

public class TunerSheetDialog extends BaseBottomSheetDialog {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.monitoring)
    Switch aSwitch;
    private Context mContext;


    private TunerAdapter tunerAdapter;
    private TunreOnIremClick mTunreOnIremClick;

    public TunerSheetDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_tuner;
    }

    @Override
    public void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        recyclerView.setAdapter(tunerAdapter = new TunerAdapter());
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(4, 30, true));
        tunerAdapter.setNewData(getData());
        tunerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Tuner item = tunerAdapter.getItem(position);
                tunerAdapter.setIndex(item.getType());
                if (mTunreOnIremClick != null) {
                    switch (item.getType()) {
                        case 0:
                            mTunreOnIremClick.setClodeLocalVoice();
                            break;
                        case 1:
//                            大叔
                            mTunreOnIremClick.setLocalVoiceChanger(Constants.VOICE_CHANGER_OLDMAN);
                            break;
                        case 2:
//小男孩 正太
                            mTunreOnIremClick.setLocalVoiceChanger(Constants.VOICE_CHANGER_BABYBOY);
                            break;
                        case 3:
//                            萝莉
                            mTunreOnIremClick.setLocalVoiceChanger(Constants.VOICE_CHANGER_BABYGIRL);
                            break;
                        case 4:
//                            空灵
                            mTunreOnIremClick.setLocalVoiceChanger(Constants.VOICE_CHANGER_ETHEREAL);
                            break;
                        case 5:
//演唱会
                            mTunreOnIremClick.setLocalVoiceReverbPreset(Constants.AUDIO_REVERB_VOCAL_CONCERT);
                            break;
                        case 6:
//ktv
                            mTunreOnIremClick.setLocalVoiceReverbPreset(Constants.AUDIO_REVERB_KTV);
                            break;
                        case 7:
//                            录音棚
                            mTunreOnIremClick.setLocalVoiceReverbPreset(Constants.AUDIO_REVERB_STUDIO);
                            break;
                    }
                }
            }
        });
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

    }


    public void addTunreOnIremClick(TunreOnIremClick tunreOnIremClick) {
        mTunreOnIremClick = tunreOnIremClick;
    }

    public class Tuner {

        private String name;
        private int type;

        public Tuner(String name, int type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }


    private List<Tuner> getData() {
        List<Tuner> tunerList = new ArrayList<>();
        Tuner tuner1 = new Tuner("原声", 0);
        Tuner tuner2 = new Tuner("大叔", 1);
        Tuner tuner3 = new Tuner("正太", 2);
        Tuner tuner4 = new Tuner("萝莉", 3);
        Tuner tuner5 = new Tuner("空灵", 4);
        Tuner tuner6 = new Tuner("演唱会", 5);
        Tuner tuner7 = new Tuner("KTV", 6);
        Tuner tuner8 = new Tuner("录音棚", 7);
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


    public interface TunreOnIremClick {
        void setLocalVoiceChanger(int type);

        void setLocalVoiceReverbPreset(int type);

        void setClodeLocalVoice();

        void enableInEarMonitoring(boolean b);
    }


}
