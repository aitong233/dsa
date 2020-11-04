package com.spadea.xqipao.ui.me.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.widget.voice.RecordVoiceButton;
import com.spadea.xqipao.data.ApplyImageItem;
import com.spadea.xqipao.data.SkillApplyModel;
import com.spadea.xqipao.data.SkillSection;
import com.spadea.xqipao.data.even.ApplyStepChangeEvent;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.me.contacter.QualificationContacts;
import com.spadea.xqipao.ui.me.presenter.QualificationPresenter;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.Constants;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.utilcode.ImageUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.ui.ImageBrowseActivity;
import com.spadea.xqipao.ui.me.activity.QualificationActivity;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.fragment
 * 创建人 王欧
 * 创建时间 2020/5/19 5:40 PM
 * 描述 describe
 */
public class ApplyStep1Fragment extends BaseFragment<QualificationPresenter> implements QualificationContacts.View {
    @BindView(R.id.tv_skill)
    EditText mTvSkill;
    @BindView(R.id.tv_title_voice)
    TextView mTvTitleVoice;
    @BindView(R.id.tv_tip_voice)
    TextView mTvTipVoice;
    @BindView(R.id.rl_voice)
    RelativeLayout mRlVoice;
    @BindView(R.id.tv_voice_sample)
    TextView mTvVoiceSample;
    @BindView(R.id.tv_sample_refresh)
    TextView mTvSampleRefresh;
    @BindView(R.id.tv_content_voice_sample)
    TextView mTvContentVoiceSample;
    @BindView(R.id.tv_exp_title)
    TextView mTvExpTitle;
    @BindView(R.id.tv_exp_tip)
    TextView mTvExpTip;
    @BindView(R.id.et_exp)
    EditText mEtExp;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.iv_sample_game)
    RoundedImageView mIvSampleGame;
    @BindView(R.id.iv_image_game)
    RoundedImageView mIvImageGame;
    @BindView(R.id.cl_image_game)
    ConstraintLayout mClImageGame;
    @BindView(R.id.iv_sample)
    RoundedImageView mIvSample;
    @BindView(R.id.iv_image)
    RoundedImageView mIvImage;
    @BindView(R.id.cl_image)
    ConstraintLayout mClImage;
    @BindView(R.id.ll_voice)
    RecordVoiceButton mRecordVoiceButton;
    @BindView(R.id.ll_id)
    LinearLayout mLLId;
    @BindView(R.id.tv_image_tip)
    TextView mTvImageTip;
    @BindView(R.id.recycler_rules)
    RecyclerView mRecyclerRules;

    private String imageUrl;
    private String voiceUrl;
    private SkillSection.Item skill;
    private int applyId;

    private static final int REQUEST_CODE_GAME = 224;
    private static final int REQUEST_CODE_ENTERMAINT = 225;
    private BaseQuickAdapter<String, BaseViewHolder> mRulesAdapter;

    @Override
    protected QualificationPresenter bindPresenter() {
        return new QualificationPresenter(this, getActivity());
    }

    @Override
    protected void initData() {
        if (getActivity() instanceof QualificationActivity) {
            skill = ((QualificationActivity) getActivity()).skill;
            if (skill.getIsMust() == 1) {
                mLLId.setVisibility(View.VISIBLE);
                mClImage.setVisibility(View.GONE);
                mClImageGame.setVisibility(View.VISIBLE);
                ImageLoader.loadImageCenterCrop(mContext, mIvSampleGame, skill.getExamPicture());
            } else {
                mLLId.setVisibility(View.GONE);
                mClImage.setVisibility(View.VISIBLE);
                mClImageGame.setVisibility(View.GONE);
                ImageLoader.loadImageCenterCrop(mContext, mIvSample, skill.getExamPicture());
            }

            mTvImageTip.setText(skill.getSkillRemark());
            mTvTipVoice.setText(skill.getVoiceRemark());
            MvpPre.getRules(skill.getId());
            applyInfo(((QualificationActivity) getActivity()).applyModel);
        }
        mRecordVoiceButton.setEnrecordVoiceListener(new RecordVoiceButton.EnRecordVoiceListener() {
            @Override
            public void clearVoice() {
                voiceUrl = null;
            }

            @Override
            public void onFinishRecord(long length, String strLength, String filePath) {
                if (!TextUtils.isEmpty(filePath)) {
                    MvpPre.uploadVoice(filePath);
                }
            }
        });
    }

    @Override
    protected void initView(View rootView) {
        mRecyclerRules.setLayoutManager(new LinearLayoutManager(mContext));
        mRulesAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.rv_item_skill_apply_rules) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.text, item);
            }
        };
        mRecyclerRules.setAdapter(mRulesAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragement_apply_step1;
    }

    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @OnClick({R.id.tv_voice_sample, R.id.tv_sample_refresh, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_voice_sample:
                if (mTvContentVoiceSample.getVisibility() == View.VISIBLE) {
                    mTvContentVoiceSample.setVisibility(View.GONE);
                    mTvSampleRefresh.setVisibility(View.GONE);
                } else {
                    mTvContentVoiceSample.setVisibility(View.VISIBLE);
                    mTvSampleRefresh.setVisibility(View.VISIBLE);
                    MvpPre.getRandomWords(skill.getId());
                }
                break;
            case R.id.tv_sample_refresh:
                MvpPre.getRandomWords(skill.getId());
                break;
            case R.id.tv_submit:
                String skillName = mTvSkill.getText().toString();
                if (skill.getIsMust() == 1 && TextUtils.isEmpty(skillName)) {
                    ToastUtils.showShort("请填写游戏ID");
                    return;
                }
                if (TextUtils.isEmpty(imageUrl)) {
                    ToastUtils.showShort("请上传技能图片");
                    return;
                }

                if (TextUtils.isEmpty(voiceUrl)) {
                    ToastUtils.showShort("请上传录音");
                    return;
                }

                String desc = mEtExp.getText().toString();
                if (TextUtils.isEmpty(desc)) {
                    ToastUtils.showShort("请填写接单说明");
                    return;
                }

                if (skill != null) {
                    SkillApplyModel model = new SkillApplyModel();
                    model.setGameNickname(skillName);
                    model.setRemark(desc);
                    model.setSkillId(skill.getId());
                    model.setSkillGroup(skill.getGroupId());
                    model.setVoiceExample(voiceUrl);
                    model.setApplyPicture(imageUrl);
                    if (skill.getStatus() == 0) {
                        model.setId(applyId);
                        MvpPre.updateApply(model);
                    } else {
                        MvpPre.addApply(model);
                    }
                }

                break;
        }
    }

    private void startChoosePhoto(int requestCode) {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofAll())
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .previewVideo(true)
                .isCamera(true)
                .sizeMultiplier(0.5f)
                .setOutputCameraPath(Constants.FILE_PATH)
                .enableCrop(false)
                .compress(false)
                .withAspectRatio(1, 1)
                .cropCompressQuality(90)// 裁剪压缩质量 默认90 int
                .minimumCompressSize(Constants.COMPRESS_INGNORE)// 小于100kb的图片不压缩
                .compressSavePath(ImageUtils.getImagePath())
                .freeStyleCropEnabled(false)
                .circleDimmedLayer(false)
                .showCropFrame(false)
                .rotateEnabled(false) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .isDragFrame(false)
                .forResult(requestCode);//结果回调onActivityResult code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
            if (localMedia != null && localMedia.size() != 0) {
                MvpPre.uploadImage(localMedia, requestCode);
            }
        }
    }

    @Override
    public void uploadImageSuccess(ApplyImageItem item, int position, int mimeType) {
        mIvImage.post(new Runnable() {
            @Override
            public void run() {
                imageUrl = item.url;
                if (position == REQUEST_CODE_GAME) {
                    ImageLoader.loadImageCenterCrop(mContext, mIvImageGame, item.url);
                } else if (position == REQUEST_CODE_ENTERMAINT) {
                    ImageLoader.loadImageCenterCrop(mContext, mIvImage, item.url);
                }
            }
        });

    }

    @Override
    public void uploadVoiceSuccess(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    @Override
    public void addApplySuccess(Boolean model) {
        if (mRecordVoiceButton != null) {
            mRecordVoiceButton.releaseVoiceManager();
        }
        EventBus.getDefault().post(new ApplyStepChangeEvent(2));
    }


    public void applyInfo(SkillApplyModel info) {
        if (info != null) {
            applyId = info.getId();
            mTvSkill.setText(info.getGameNickname());
            imageUrl = info.getApplyPicture();
            if (skill.getIsMust() == 1) {
                ImageLoader.loadImageCenterCrop(mContext, mIvImageGame, imageUrl);
            } else {
                ImageLoader.loadImageCenterCrop(mContext, mIvImage, imageUrl);
            }
//            voiceUrl = info.getVoiceExample();
            mEtExp.setText(info.getRemark());
//            if (!TextUtils.isEmpty(voiceUrl)) {
//                new DownloadUtil().downloadVoiceFile(voiceUrl, new DownloadListener() {
//                    @Override
//                    public void onStart() {
//
//                    }
//
//                    @Override
//                    public void onProgress(int currentLength) {
//
//                    }
//
//                    @Override
//                    public void onFinish(String localPath) {
//                        if (mRecordVoiceButton != null) {
//                            mRecordVoiceButton.setRecordFilePath(localPath);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure() {
//
//                    }
//                });
//            }
        }
    }

    @Override
    public void randomWords(String words) {
        mTvContentVoiceSample.setVisibility(View.VISIBLE);
        mTvSampleRefresh.setVisibility(View.VISIBLE);
        mTvContentVoiceSample.setText(words);
    }

    @Override
    public void rules(List<String> rules) {
        mRulesAdapter.setNewData(rules);
    }


    @OnClick({R.id.iv_image_game, R.id.iv_image, R.id.iv_sample_game, R.id.iv_sample})
    public void onImageViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_image_game:
                startChoosePhoto(REQUEST_CODE_GAME);
                break;
            case R.id.iv_image:
                startChoosePhoto(REQUEST_CODE_ENTERMAINT);
                break;
            case R.id.iv_sample:
            case R.id.iv_sample_game:
                if (!TextUtils.isEmpty(skill.getExamPicture())) {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(skill.getExamPicture());
                    ImageBrowseActivity.start(getActivity(), 0, list);
                }
                break;

        }
    }

    @Override
    public void onDestroyView() {
        if (mRecordVoiceButton != null) {
            mRecordVoiceButton.releaseVoiceManager();
        }
        super.onDestroyView();
    }
}
