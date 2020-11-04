package com.qpyy.room.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.qpyy.libcommon.base.BaseMvpDialogFragment;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.utils.OnClickUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.bean.ClosePublicScreenEvent;
import com.qpyy.room.bean.EffectEvent;
import com.qpyy.room.bean.OpenPublicScreenEvent;
import com.qpyy.room.bean.RoomInfoResp;
import com.qpyy.room.bean.RoomVoiceState;
import com.qpyy.room.bean.UpdateRoom;
import com.qpyy.room.contacts.RoomToolContacts;
import com.qpyy.room.dialog.FansNotifyDialog;
import com.qpyy.libcommon.event.RoomOutEvent;
import com.qpyy.room.presenter.RoomToolPresenter;
import com.qpyy.rtc.RtcManager;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.dialog
 * 创建人 黄强
 * 创建时间 2020/8/3 09:37
 * 描述 describe
 */
public class RoomToolDialogFragment extends BaseMvpDialogFragment<RoomToolPresenter> implements RoomToolContacts.View {


    private static final String TAG = "RoomToolTableDialog";
    @BindView(R2.id.tv_tool_txt)
    TextView tvToolTxt;
    @BindView(R2.id.iv_room_cleancontent_tool)
    ImageView ivRoomCleancontentTool;
    @BindView(R2.id.iv_room_row_wheat_tool)
    ImageView ivRoomRowWheatTool;
    @BindView(R2.id.iv_room_music_tool)
    ImageView ivRoomMusicTool;
    @BindView(R2.id.iv_room_setpassword_tool)
    ImageView ivRoomSetpasswordTool;
    @BindView(R2.id.iv_room_heartvalue_tool)
    ImageView ivRoomHeartvalueTool;
    @BindView(R2.id.iv_room_clean_all_value_tool)
    ImageView ivRoomCleanAllValueTool;
    @BindView(R2.id.iv_room_notice_tool)
    ImageView ivRoomNoticeTool;
    @BindView(R2.id.iv_room_info_tool)
    ImageView ivRoomInfoTool;
    @BindView(R2.id.iv_room_effect_tool)
    ImageView ivRoomEffectTool;
    @BindView(R2.id.iv_room_sound_tool)
    ImageView ivRoomSoundTool;
    @BindView(R2.id.iv_room_image_tool)
    ImageView ivRoomImageTool;
    @BindView(R2.id.iv_room_sound_console_tool)
    ImageView ivRoomSoundConsoleTool;
    @BindView(R2.id.tv_tool_operation)
    TextView tvToolOperation;
    @BindView(R2.id.iv_room_feedback_tool)
    ImageView ivRoomFeedbackTool;
    @BindView(R2.id.iv_room_report_tool)
    ImageView ivRoomReportTool;
    @BindView(R2.id.iv_room_exit_tool)
    ImageView ivRoomExitTool;
    @BindView(R2.id.tv_room_cleancontent_tool)
    TextView tvRoomCleancontentTool;
    @BindView(R2.id.tv_room_row_wheat_tool)
    TextView tvRoomRowWheatTool;
    @BindView(R2.id.tv_room_music_tool)
    TextView tvRoomMusicTool;
    @BindView(R2.id.tv_room_setpassword_tool)
    TextView tvRoomSetpasswordTool;
    @BindView(R2.id.tv_room_heartvalue_tool)
    TextView tvRoomHeartvalueTool;
    @BindView(R2.id.tv_room_clean_all_value_tool)
    TextView tvRoomCleanAllValueTool;
    @BindView(R2.id.tv_room_notice_tool)
    TextView tvRoomNoticeTool;
    @BindView(R2.id.tv_room_info_tool)
    TextView tvRoomInfoTool;
    @BindView(R2.id.tv_room_effect_tool)
    TextView tvRoomEffectTool;
    @BindView(R2.id.tv_room_sound_tool)
    TextView tvRoomSoundTool;
    @BindView(R2.id.tv_room_image_tool)
    TextView tvRoomImageTool;
    @BindView(R2.id.tv_room_sound_console_tool)
    TextView tvRoomSoundConsoleTool;
    @BindView(R2.id.ll_screen)
    LinearLayout llScreen;
    @BindView(R2.id.ll_wheat)
    LinearLayout llWheat;
    @BindView(R2.id.ll_music)
    LinearLayout llMusic;
    @BindView(R2.id.ll_passwd)
    LinearLayout llPasswd;
    @BindView(R2.id.ll_heart)
    LinearLayout llHeart;
    @BindView(R2.id.ll_clean_all)
    LinearLayout llCleanAll;
    @BindView(R2.id.ll_notice)
    LinearLayout llNotice;
    @BindView(R2.id.ll_info)
    LinearLayout llInfo;
    @BindView(R2.id.ll_effect)
    LinearLayout llEffect;
    @BindView(R2.id.ll_sound)
    LinearLayout llSound;
    @BindView(R2.id.ll_image)
    LinearLayout llImage;
    @BindView(R2.id.ll_sound_console)
    LinearLayout llSoundConsole;
    @BindView(R2.id.ll_feedback_)
    LinearLayout llFeedback;
    @BindView(R2.id.ll_report)
    LinearLayout llReport;
    @BindView(R2.id.ll_exit)
    LinearLayout llExit;

    private boolean effectOn = false;//开启/关闭特效
    private boolean heartvalueOn = false;//开启/关闭心动
    private boolean cleancontentOn = false;//开启/关闭公屏
    private boolean rowWheatOn = false;//排麦模式
    private boolean voiceState = true;//声音状态
    private RoomInfoResp mRoomInfoResp;

    private String password;
    private String mRoomId;
    private boolean isPlay = false;//音乐是否正在播放
    private boolean isStation = false;//是否是电台房


    public static RoomToolDialogFragment newInstance(RoomInfoResp roomInfoResp, String password, boolean isPlay, boolean isStation, boolean voiceState) {
        Bundle args = new Bundle();
        args.putSerializable("roomInfoResp", roomInfoResp);
        args.putString("password", password);
        args.putBoolean("isPlay", isPlay);
        args.putBoolean("isStation", isStation);
        args.putBoolean("voiceState", voiceState);
        RoomToolDialogFragment fragment = new RoomToolDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        this.mRoomInfoResp = (RoomInfoResp) arguments.getSerializable("roomInfoResp");
        mRoomId = mRoomInfoResp.getRoom_info().getRoom_id();
        this.password = arguments.getString("password");
        this.isPlay = arguments.getBoolean("isPlay");
        this.isStation = arguments.getBoolean("isStation");
        this.voiceState = arguments.getBoolean("voiceState");
    }

    @Override
    public void initView() {
        //显示心动
        heartvalueOn = mRoomInfoResp.getRoom_info().getCardiac() == 1 ? true : false;
        ivRoomHeartvalueTool.setImageResource(heartvalueOn ? R.mipmap.room_heartvalue_off_tool : R.mipmap.room_heartvalue_on_tool);
        tvRoomHeartvalueTool.setText(heartvalueOn ? "关闭心动值" : "显示心动值");
        //排麦模式
        rowWheatOn = "1".equals(mRoomInfoResp.getRoom_info().getWheat()) ? true : false;
        ivRoomRowWheatTool.setImageResource(rowWheatOn ? R.mipmap.room_row_wheat_tool : R.mipmap.room_free_tool);
        //开启特效(本地)
        effectOn = (SpUtils.getOpenEffect() == 1) ? true : false;
        ivRoomEffectTool.setImageResource(effectOn ? R.mipmap.room_effect_off_tool : R.mipmap.room_effect_on_tool);
        tvRoomEffectTool.setText(effectOn ? "关闭特效" : "打开特效");
        //打开公屏
        cleancontentOn = mRoomInfoResp.getRoom_info().getChat_status() == 1 ? true : false;
        ivRoomCleancontentTool.setImageResource(cleancontentOn ? R.mipmap.room_cleancontent_tool : R.mipmap.room_opencontent_tool);
        tvRoomCleancontentTool.setText(cleancontentOn ? "关闭公屏" : "打开公屏");
        //声音状态
        ivRoomSoundTool.setImageResource(voiceState ? R.mipmap.room_sound_off_tool : R.mipmap.room_sound_on_tool);
        tvRoomSoundTool.setText(voiceState ? "关闭声音" : "打开声音");
        //权限
        //非管理员
        if (!mRoomInfoResp.isManager()) {
            //麦上
            if (mRoomInfoResp.getUser_info().getPit() > 0) {
                llScreen.setVisibility(View.GONE);
                llWheat.setVisibility(View.GONE);
                llPasswd.setVisibility(View.GONE);
                llHeart.setVisibility(View.GONE);
                llInfo.setVisibility(View.GONE);
                llCleanAll.setVisibility(View.GONE);
                llImage.setVisibility(View.GONE);
            } else {
                //麦下
                llScreen.setVisibility(View.GONE);
                llWheat.setVisibility(View.GONE);
                llPasswd.setVisibility(View.GONE);
                llHeart.setVisibility(View.GONE);
                llInfo.setVisibility(View.GONE);
                llCleanAll.setVisibility(View.GONE);
                llImage.setVisibility(View.GONE);
                llMusic.setVisibility(View.GONE);
                llSoundConsole.setVisibility(View.GONE);
                llNotice.setVisibility(View.GONE);
            }
        }

        //电台
        if (isStation) {
            llWheat.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_dialog_more_tools;
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
    protected RoomToolPresenter bindPresenter() {
        return new RoomToolPresenter(this, getActivity());
    }

    @Override
    public void initData() {

    }


    /**
     * 上传数据
     */
    private void updateData(HashMap<String, String> setMap) {
        UpdateRoom updateRoom = new UpdateRoom();
        updateRoom.setMap(setMap);
        updateRoom.setRoomId(mRoomId);
        EventBus.getDefault().post(updateRoom);//通知RoomFragment更新
    }

    @Override
    public void setRoomCardiacSuccess() {
        //心动显示/关闭设置成功

    }

    @OnClick({R2.id.ll_screen, R2.id.ll_wheat, R2.id.ll_music,
            R2.id.ll_passwd, R2.id.ll_heart, R2.id.ll_clean_all,
            R2.id.ll_notice, R2.id.ll_info, R2.id.ll_effect, R2.id.ll_sound,
            R2.id.ll_image, R2.id.ll_sound_console, R2.id.ll_feedback_,
            R2.id.ll_report, R2.id.ll_exit})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.ll_screen) {//开关公屏
            if (cleancontentOn) {
                //关闭
                ivRoomCleancontentTool.setImageResource(R.mipmap.room_opencontent_tool);
                tvRoomCleancontentTool.setText("打开公屏");
                cleancontentOn = false;
                EventBus.getDefault().post(new ClosePublicScreenEvent());
            } else {
                //开启
                ivRoomCleancontentTool.setImageResource(R.mipmap.room_cleancontent_tool);
                tvRoomCleancontentTool.setText("关闭公屏 ");
                cleancontentOn = true;
                EventBus.getDefault().post(new OpenPublicScreenEvent());
            }
        } else if (id == R.id.ll_wheat) {//麦序模式
            HashMap<String, String> rowWheatOnMap = new HashMap<>();//更新值
            rowWheatOnMap.put("wheat", rowWheatOn ? "2" : "1");//上传更新排麦模式
            updateData(rowWheatOnMap);
            if (rowWheatOn) {
                ivRoomRowWheatTool.setImageResource(R.mipmap.room_free_tool);
                rowWheatOn = false;
            } else {
                ivRoomRowWheatTool.setImageResource(R.mipmap.room_row_wheat_tool);
                rowWheatOn = true;
            }
        } else if (id == R.id.ll_music) {//背景音乐
            MusicListDialogFragment.newInstance(SpUtils.getPlayCurrentMusic(), isPlay)
                    .show(getFragmentManager(), "MusicListDialogFragment");
            dismiss();
        } else if (id == R.id.ll_passwd) {//设置秘密啊
            if (!OnClickUtils.isFastDoubleClick()) {
                RoomPasswordSetDialogFragment.newInstance(false, mRoomId).show(getFragmentManager());
            }
            dismiss();
        } else if (id == R.id.ll_heart) {//显示心动值
            HashMap<String, String> heartvalueOnMap = new HashMap<>();//更新值
            MvpPre.setRoomCardiac(mRoomId, heartvalueOn ? 2 : 1);//上传开关心动
            updateData(heartvalueOnMap);
            //显示/不显示心动
            if (heartvalueOn) {
                ivRoomHeartvalueTool.setImageResource(R.mipmap.room_heartvalue_on_tool);
                tvRoomHeartvalueTool.setText("显示心动值");
                heartvalueOn = false;
                MvpPre.setRoomCardiac(mRoomId, 0);
            } else {
                ivRoomHeartvalueTool.setImageResource(R.mipmap.room_heartvalue_off_tool);
                tvRoomHeartvalueTool.setText("关闭心动值");
                heartvalueOn = true;
                MvpPre.setRoomCardiac(mRoomId, 1);
            }
        } else if (id == R.id.ll_clean_all) {//清空心动值
            MvpPre.clearRoomCardiac(mRoomId);
            dismiss();
        } else if (id == R.id.ll_notice) {//粉丝通知
            FansNotifyDialog.newInstance(mRoomId).show(getFragmentManager());
            dismiss();
        } else if (id == R.id.ll_info) {//房间信息
            boolean isOwner = false;
            if (SpUtils.getUserId().equals(mRoomInfoResp.getOwner_info().getUser_id()))
                isOwner = true;
            ARouter.getInstance().build(ARouteConstants.ROOM_INFO).withString("roomId", mRoomInfoResp.getRoom_info().getRoom_id()).withString("roomPassword", password).withInt("sceneType", mRoomInfoResp.getRoom_info().getSceneId()).withBoolean("isOwner",isOwner).navigation();
            dismiss();
        } else if (id == R.id.ll_effect) {//关闭特效
            //开关特效
            if (effectOn) {
                //关闭
                ivRoomEffectTool.setImageResource(R.mipmap.room_effect_on_tool);
                tvRoomEffectTool.setText("打开特效");
                effectOn = false;
                //保存到本地
                SpUtils.setOpenEffect(0);
                EventBus.getDefault().post(new EffectEvent(false));
            } else {
                //打开
                ivRoomEffectTool.setImageResource(R.mipmap.room_effect_off_tool);
                tvRoomEffectTool.setText("关闭特效");
                effectOn = true;
                SpUtils.setOpenEffect(1);
                EventBus.getDefault().post(new EffectEvent(true));
            }
        } else if (id == R.id.ll_sound) {
            if (voiceState) {
                //关闭声音
                ivRoomSoundTool.setImageResource(R.mipmap.room_sound_on_tool);
                tvRoomSoundTool.setText("打开声音");
                RtcManager.getInstance().muteSpeaker(true);
                voiceState = false;
            } else {
                ivRoomSoundTool.setImageResource(R.mipmap.room_sound_off_tool);
                tvRoomSoundTool.setText("关闭声音");
                RtcManager.getInstance().muteSpeaker(false);
                voiceState = true;
            }
            EventBus.getDefault().post(new RoomVoiceState(voiceState));//通知
        } else if (id == R.id.ll_image) {//切换背景
            if (!OnClickUtils.isFastDoubleClick()) {
                RoomBackgroundDialogFragment.newInstance(mRoomId, mRoomInfoResp.getRoom_info().getBg_picture()).show(getFragmentManager(), "RoomBackgroundDialogFragment");
            }
            dismiss();
        } else if (id == R.id.ll_sound_console) {//调音台
            int mixer = mRoomInfoResp.getUser_info().getMixer();
            TunerDialogFragment.instantiate(mRoomId, mixer).show(getFragmentManager(), "TunerDialogFragment");
            dismiss();
        } else if (id == R.id.ll_feedback_) {//意见反馈
            Intent intent = new Intent();
            intent.setClassName("com.spadea.yuyin", "com.spadea.yuyin.ui.fragment2.setting.feedback.FeedBackActivity");
            startActivity(intent);
        } else if (id == R.id.ll_report) {//举报
            Intent report = new Intent();
            report.putExtra("id", mRoomId);
            report.setClassName("com.spadea.yuyin", "com.spadea.yuyin.ui.fragment0.roomdetail.reprot.ReportActivity");
            startActivity(report);
        } else if (id == R.id.ll_exit) {//退出房间
            EventBus.getDefault().post(new RoomOutEvent());
        }
    }

}
