package com.qpyy.module.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.constant.Constants;
import com.qpyy.libcommon.utils.ImageLoader;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.libcommon.utils.MediaPlayerUtiles;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.libcommon.widget.dialog.CommonDialog;
import com.qpyy.libcommon.widget.voice.RecordVoiceButton;
import com.qpyy.libcommon.widget.voice.RecordVoiceView;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.bean.CityResp;
import com.qpyy.module.me.bean.ConstellationBean;
import com.qpyy.module.me.bean.ProfessionBean;
import com.qpyy.module.me.bean.RegionListResp;
import com.qpyy.module.me.bean.SexBean;
import com.qpyy.module.me.bean.UserHomeResp;
import com.qpyy.module.me.contacts.EditInformationContacts;
import com.qpyy.module.me.dialog.DateSelectDialog;
import com.qpyy.module.me.dialog.EditNickNameDialog;
import com.qpyy.module.me.dialog.SelectCityDialog;
import com.qpyy.module.me.dialog.SelectSexDialog;
import com.qpyy.module.me.event.UpdateInfoEvent;
import com.qpyy.module.me.presenter.EditInformationPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;


@Route(path = ARouteConstants.ME_MY_EDIT_INFO, name = "编辑个人资料")
public class EditInformationActivity extends BaseMvpActivity<EditInformationPresenter> implements EditNickNameDialog.OnEditNickName, EditInformationContacts.View, DateSelectDialog.OnSelectDate, SelectCityDialog.OnSelectCity {

    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.riv_user_head)
    RoundedImageView rivUserHead;
    @BindView(R2.id.tv_date)
    TextView tvDate;
    @BindView(R2.id.tv_sex)
    TextView tvSex;
    @BindView(R2.id.tv_constellation)
    TextView tvConstellation;
    @BindView(R2.id.tv_city)
    TextView tvCity;
    @BindView(R2.id.ll_voice)
    RecordVoiceButton mRecordVoiceButton;
    @BindView(R2.id.iv_video)
    RoundedImageView ivVideo;
    @BindView(R2.id.iv_close)
    ImageView ivClose;
    @BindView(R2.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R2.id.tv_profession)
    TextView tvProfession;
    @BindView(R2.id.ll_voice_view)
    RecordVoiceView recordVoiceView;

    private SelectSexDialog mSelectSexDialog;
    private SelectCityDialog mSelectCityDialog;
    private DateSelectDialog mDateSelectDialog;
    private EditNickNameDialog mEditNickNameDialog;


    private long mLength;
    private String mUserId;
    private SelectSexDialog mConstellationSelectDialog;

    @Override
    protected EditInformationPresenter bindPresenter() {
        return new EditInformationPresenter(this, this);
    }

    @Override
    protected void initData() {
        mUserId = SpUtils.getUserId();
        MvpPre.getUserHomePage(mUserId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateInfo(UpdateInfoEvent event) {
        MvpPre.getUserHomePage(mUserId);
    }

    @Override
    protected void initView() {
        super.initView();
        tvTitle.setText("编辑信息");
        mRecordVoiceButton.setEnrecordVoiceListener(new RecordVoiceButton.EnRecordVoiceListener() {
            @Override
            public void clearVoice() {
                Map<String, String> map = new HashMap<>();
                map.put("intro_voice", "delete");
                MvpPre.upDateUserInfo(map);
            }

            @Override
            public void onFinishRecord(long length, String strLength, String filePath) {
                if (!TextUtils.isEmpty(filePath)) {
                    mLength = length;
                    MvpPre.uploadFile(new File(filePath), 1);
                }
            }
        });
        recordVoiceView.addEnRecordVoiceListener(new RecordVoiceView.EnRecordVoiceListener() {
            @Override
            public void clearVoice() {
                handler.removeMessages(0);
                Map<String, String> map = new HashMap<>();
                map.put("intro_voice", "delete");
                MvpPre.upDateUserInfo(map);
            }

            @Override
            public void onStartPlay() {
                handler.removeMessages(0);
                if (mUserHomeResp != null) {
                    count = Integer.valueOf(mUserHomeResp.getIntro_voice_time());
                    handler.sendEmptyMessageDelayed(0, 1000);
                }
            }

            @Override
            public void onCompletion() {
                if (mUserHomeResp != null) {
                    recordVoiceView.setVoiceData(mUserHomeResp.getIntro_voice(), mUserHomeResp.getIntro_voice_time());
                }
                handler.removeMessages(0);
            }
        });
    }

    private int count = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            count--;
            recordVoiceView.setVoiceTime(String.valueOf(count));
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.me_activity_edit_information;
    }


    @OnLongClick(R2.id.iv_video)
    public boolean longClicks(View view) {
        int id = view.getId();
        if (id == R.id.iv_video) {
            ivClose.setVisibility(View.VISIBLE);
        }
        return true;
    }

    @OnClick({R2.id.rl_sex, R2.id.rl_constellation, R2.id.rl_date, R2.id.rl_nick_name, R2.id.rl_autograph, R2.id.iv_video,
            R2.id.riv_user_head, R2.id.iv_close, R2.id.rl_profession, R2.id.rl_city, R2.id.iv_back})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rl_sex) {
            MvpPre.verifyUserSex();
        } else if (id == R.id.rl_constellation) {
            if (mConstellationSelectDialog == null) {
                mConstellationSelectDialog = new SelectSexDialog(this);
                List<ConstellationBean> constellationBeanList = new ArrayList<>();
                constellationBeanList.add(new ConstellationBean("白羊座"));
                constellationBeanList.add(new ConstellationBean("金牛座"));
                constellationBeanList.add(new ConstellationBean("双子座"));
                constellationBeanList.add(new ConstellationBean("巨蟹座"));
                constellationBeanList.add(new ConstellationBean("狮子座"));
                constellationBeanList.add(new ConstellationBean("处女座"));
                constellationBeanList.add(new ConstellationBean("天秤座"));
                constellationBeanList.add(new ConstellationBean("天蝎座"));
                constellationBeanList.add(new ConstellationBean("射手座"));
                constellationBeanList.add(new ConstellationBean("魔羯座"));
                constellationBeanList.add(new ConstellationBean("水瓶座"));
                constellationBeanList.add(new ConstellationBean("双鱼座"));
                mConstellationSelectDialog.setData(constellationBeanList);
                mConstellationSelectDialog.addOnSelectSexClickListener(new SelectSexDialog.OnSelectSexClickListener() {
                    @Override
                    public void onConfirmClick(int postion) {
                        Map<String, String> map = new HashMap<>();
                        map.put("constellation", String.valueOf(constellationBeanList.get(postion).getText()));
                        MvpPre.upDateUserInfo(map);
                    }
                });
            }
            mConstellationSelectDialog.show();
        } else if (id == R.id.rl_date) {
            if (mDateSelectDialog == null) {
                mDateSelectDialog = new DateSelectDialog(this);
                mDateSelectDialog.setmOnSelectDate(this);
            }
            if (mUserHomeResp != null && !TextUtils.isEmpty(mUserHomeResp.getBirthday())) {
                String[] split = mUserHomeResp.getBirthday().split("-");
                mDateSelectDialog.setData(split[0], split[1], split[2]);
            }
            mDateSelectDialog.show();
        } else if (id == R.id.rl_nick_name) {
            if (mEditNickNameDialog == null) {
                mEditNickNameDialog = new EditNickNameDialog(this);
                mEditNickNameDialog.setmOnEditNickName(this);
            }
            mEditNickNameDialog.setName(mUserHomeResp.getNickname());
            mEditNickNameDialog.show();
        } else if (id == R.id.rl_autograph) {
            if (mUserHomeResp != null) {
                ARouter.getInstance().build(ARouteConstants.ME_PERSONAL_SIGNATURE).withString("text", mUserHomeResp.getSignature()).navigation();
            }
        } else if (id == R.id.riv_user_head) {
            startChoosePhoto(PictureMimeType.ofImage(), PictureConfig.CHOOSE_REQUEST);
        } else if (id == R.id.iv_close) {
            ivVideo.setImageResource(R.mipmap.me_img_video);
            ivClose.setVisibility(View.GONE);
            Map<String, String> map = new HashMap<>();
            map.put("vedio", "delete");
            MvpPre.upDateUserInfo(map);
        } else if (id == R.id.rl_profession) {
            MvpPre.getProfession();
        } else if (id == R.id.rl_city) {
            MvpPre.getRegionList();
        } else if (id == R.id.iv_video) {
            if (!TextUtils.isEmpty(mUserHomeResp.getUser_photo().getVedio())) {
                MNVedioBrowserActivity.startActivity(this, mUserHomeResp.getUser_photo().getVedio());
            } else {
                startChoosePhoto(PictureMimeType.ofVideo(), PictureConfig.REQUEST_CAMERA);
            }
        } else if (id == R.id.iv_back) {
            finish();
        }

    }


    private void startChoosePhoto(int mimeType, int requestCode) {
        PictureSelector.create(this)
                .openGallery(mimeType)
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .isCamera(true)
                .sizeMultiplier(0.5f)
                .setOutputCameraPath(Constants.FILE_PATH)
                .enableCrop(true)
                .compress(true)
                .videoMaxSecond(60)
                .recordVideoSecond(60)
                .withAspectRatio(1, 1)
                .cropCompressQuality(Constants.CROP_COMPRESS_SIZE)// 裁剪压缩质量 默认90 int
                .minimumCompressSize(Constants.COMPRESS_INGNORE)// 小于100kb的图片不压缩
                .compressSavePath(ImageUtils.getImagePath())
                .freeStyleCropEnabled(false)
                .circleDimmedLayer(false)
                .showCropFrame(true)
                .rotateEnabled(false) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .isDragFrame(false)
                .forResult(requestCode);//结果回调onActivityResult code
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                    if (localMedia != null && localMedia.size() != 0) {
                        LocalMedia imgMedia = localMedia.get(0);
                        String url;
                        if (imgMedia.isCompressed()) {
                            url = imgMedia.getCompressPath();
                        } else {
                            url = imgMedia.getPath();
                        }
                        MvpPre.uploadFile(new File(url), 0);
                    }
                    break;
                case PictureConfig.REQUEST_CAMERA:
                    List<LocalMedia> localMedia1 = PictureSelector.obtainMultipleResult(data);
                    if (localMedia1 != null && localMedia1.size() != 0) {
                        LocalMedia imgMedia = localMedia1.get(0);
                        MvpPre.uploadFile(new File(imgMedia.getPath()), 3);
                    }
                    break;
            }
        }
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (mRecordVoiceButton != null) {
            mRecordVoiceButton.releaseVoiceManager();
        }
        if (handler != null) {
            handler.removeMessages(0);
        }
        MediaPlayerUtiles.getInstance().stopAudio();
        super.onDestroy();
    }

    /**
     * 更新昵称
     *
     * @param nickName 昵称
     */
    @Override
    public void updateName(String nickName) {
        Map<String, String> map = new HashMap<>();
        map.put("nickname", nickName);
        MvpPre.upDateUserInfo(map);
    }

    /**
     * 更新成功
     */
    @Override
    public void updateSuccess() {
        MvpPre.getUserHomePage(mUserId);
    }

    @Override
    public void upLoadSuccess(String url, int type) {
        Map<String, String> map = new HashMap<>();
        if (type == 0) {
            MvpPre.updateAvatar(url);
        } else if (type == 1) {
            map.put("intro_voice", url);
            map.put("intro_voice_time", String.valueOf(mLength));
        } else {
            map.put("vedio", url);
        }
        MvpPre.upDateUserInfo(map);
    }

    @Override
    public void setProfession(List<ProfessionBean> data) {
        SelectSexDialog selectDialog = new SelectSexDialog(this);
        selectDialog.setData(data);
        selectDialog.addOnSelectSexClickListener(new SelectSexDialog.OnSelectSexClickListener() {
            @Override
            public void onConfirmClick(int postion) {
                Map<String, String> map = new HashMap<>();
                map.put("profession", data.get(postion).getText());
                MvpPre.upDateUserInfo(map);
            }
        });
        selectDialog.show();
    }

    @Override
    public void setRegionList(List<RegionListResp> data) {
        if (mSelectCityDialog == null) {
            mSelectCityDialog = new SelectCityDialog(this);
            mSelectCityDialog.setProvince(data);
            mSelectCityDialog.setOnSelectCity(this);
            mSelectCityDialog.show();
        }
        mSelectCityDialog.show();
    }


    private UserHomeResp mUserHomeResp;

    @Override
    public void setUserHomePage(UserHomeResp userHomePage) {
        mUserHomeResp = userHomePage;
        ImageLoader.loadHead(this, rivUserHead, userHomePage.getHead_picture());
        tvNickName.setText(userHomePage.getNickname());
        tvDate.setText(TextUtils.isEmpty(userHomePage.getBirthday()) ? "请选择日期" : userHomePage.getBirthday());
        tvSex.setText("0".equals(userHomePage.getSex()) ? "请选择性别" : "1".equals(userHomePage.getSex()) ? "男" : "女");
        tvConstellation.setText(TextUtils.isEmpty(userHomePage.getConstellation()) ? "请选择星座" : userHomePage.getConstellation());
        tvCity.setText(TextUtils.isEmpty(userHomePage.getCity()) ? "请选择城市" : userHomePage.getCity());
        tvProfession.setText(TextUtils.isEmpty(userHomePage.getProfession()) ? "请选择职业" : userHomePage.getProfession());
        if (TextUtils.isEmpty(userHomePage.getUser_photo().getVedio_cover())) {
            ivVideo.setImageResource(R.mipmap.me_img_video);
        } else {
            ImageLoader.loadImage(this, ivVideo, userHomePage.getUser_photo().getVedio_cover());
        }

        if (!TextUtils.isEmpty(userHomePage.getIntro_voice())) {
            recordVoiceView.setVisibility(View.VISIBLE);
            mRecordVoiceButton.setVisibility(View.GONE);
            recordVoiceView.setVoiceData(userHomePage.getIntro_voice(), userHomePage.getIntro_voice_time());
        } else {
            recordVoiceView.setVisibility(View.GONE);
            mRecordVoiceButton.clear();
            mRecordVoiceButton.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void updateAvatarSuccess(String headPicture) {
        ImageLoader.loadHead(this, rivUserHead, headPicture);
    }

    @Override
    public void showSelectSexDialog() {
        if (mSelectSexDialog == null) {
            List<SexBean> data = new ArrayList<>();
            data.add(new SexBean("男", 1));
            data.add(new SexBean("女", 2));
            mSelectSexDialog = new SelectSexDialog(this);
            mSelectSexDialog.setData(data);
            mSelectSexDialog.addOnSelectSexClickListener(new SelectSexDialog.OnSelectSexClickListener() {
                @Override
                public void onConfirmClick(int postion) {
                    CommonDialog commonDialog = new CommonDialog(getSelfActivity());
                    commonDialog.setContent("性别一经确认不可修改");
                    commonDialog.setmOnClickListener(new CommonDialog.OnClickListener() {
                        @Override
                        public void onLeftClick() {

                        }

                        @Override
                        public void onRightClick() {
                            Map<String, String> map = new HashMap<>();
                            map.put("sex", String.valueOf(data.get(postion).getType()));
                            MvpPre.upDateUserInfo(map);
                        }
                    });
                    commonDialog.show();

                }
            });
        }
        mSelectSexDialog.show();
    }

    /**
     * 更新出生年月日
     *
     * @param year
     * @param month
     * @param day
     */
    @Override
    public void selectDate(String year, String month, String day) {
        Map<String, String> map = new HashMap<>();
        map.put("birthday", year + "-" + month + "-" + day);
        MvpPre.upDateUserInfo(map);
    }


    @Override
    public void onSelectData(RegionListResp province, CityResp city) {
        Map<String, String> map = new HashMap<>();
        map.put("province_id", province.getId());
        map.put("city_id", city.getId());
        MvpPre.upDateUserInfo(map);
    }


}
