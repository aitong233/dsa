package com.spadea.xqipao.ui.me.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.Constants;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.utilcode.ConvertUtils;
import com.spadea.yuyin.util.utilcode.ImageUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.data.NameAuthModel;
import com.spadea.xqipao.ui.base.view.BaseFragment;
import com.spadea.xqipao.ui.me.contacter.NameIdentifyContacts;
import com.spadea.xqipao.ui.me.presenter.NameIdentifyPresenter;
import com.spadea.xqipao.widget.IdentifyCodeView;
import com.spadea.xqipao.widget.IdentifyEditView;
import com.spadea.xqipao.ui.me.dialog.IdentifyPopWindow;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.fragment
 * 创建人 王欧
 * 创建时间 2020/5/15 4:09 PM
 * 描述 describe
 */
public class ManualIdentifyFragment extends BaseFragment<NameIdentifyPresenter> implements NameIdentifyContacts.View, IdentifyCodeView.GetCodeClickCallBack {
    @BindView(R.id.iev_name)
    IdentifyEditView mIevName;
    @BindView(R.id.iev_no)
    IdentifyEditView mIevNo;
    @BindView(R.id.iev_phone)
    IdentifyEditView mIevPhone;
    @BindView(R.id.tv_tip_title)
    TextView mTvTipTitle;
    @BindView(R.id.id_1)
    ImageView mId1;
    @BindView(R.id.iv_1)
    ImageView mIv1;
    @BindView(R.id.iv_camera1)
    ImageView mIvCamera1;
    @BindView(R.id.id_2)
    ImageView mId2;
    @BindView(R.id.iv_2)
    ImageView mIv2;
    @BindView(R.id.iv_camera2)
    ImageView mIvCamera2;
    @BindView(R.id.id_3)
    ImageView mId3;
    @BindView(R.id.iv_camera3)
    ImageView mIvCamera3;
    @BindView(R.id.cl_idcard)
    ConstraintLayout mClIdcard;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.icv_code)
    IdentifyCodeView mIcvCode;

    @BindView(R.id.iv_shadow1)
    ImageView mIvShadow1;

    @BindView(R.id.iv_shadow2)
    ImageView mIvShadow2;

    @BindView(R.id.iv_shadow3)
    ImageView mIvShadow3;

    private IdentifyPopWindow mIdentifyPopWindow;

    private String frontImage;
    private String backImage;
    private String idCardImage;

    private static final int REQUEST_CODE_TYPE1 = 1001;
    private static final int REQUEST_CODE_TYPE2 = 1002;
    private static final int REQUEST_CODE_TYPE3 = 1003;

    @Override
    protected NameIdentifyPresenter bindPresenter() {
        return new NameIdentifyPresenter(this, getActivity());
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(View rootView) {
        mIcvCode.setCallBack(this);
        mIevNo.addTextWatcher(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String editable = mIevNo.getText().toString();
                String str = stringFilter(editable.toString());
                if (!editable.equals(str)) {
                    mIevNo.getEditText().setText(str);
                    //设置新的光标所在位置
                    mIevNo.getEditText().setSelection(str.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInfo();
            }
        });
        mIevName.addTextWatcher(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkInfo();
            }
        });
    }

    private void checkInfo() {
        if (TextUtils.isEmpty(frontImage) || TextUtils.isEmpty(backImage) || TextUtils.isEmpty(idCardImage) || TextUtils.isEmpty(mIevName.getText()) || mIevNo.getText().length() != 18) {
            mTvSubmit.setEnabled(false);
            mTvSubmit.setBackgroundResource(R.drawable.bg_r99_c9c9c9);
        } else {
            mTvSubmit.setEnabled(true);
            mTvSubmit.setBackgroundResource(R.drawable.bg_r99_gradient_main);
        }
    }

    private String stringFilter(String str) {
        // 只允许字母、数字和汉字
        String regEx = "[^a-zA-Z0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragement_manual_identify;
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }


    @OnClick({R.id.iv_camera1, R.id.iv_camera2, R.id.iv_camera3, R.id.iv_shadow1, R.id.iv_shadow2, R.id.iv_shadow3, R.id.tv_submit, R.id.tv_exam})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_camera1:
            case R.id.iv_shadow1:
                startChoosePhoto(REQUEST_CODE_TYPE1);
                break;
            case R.id.iv_camera2:
            case R.id.iv_shadow2:
                startChoosePhoto(REQUEST_CODE_TYPE2);
                break;
            case R.id.iv_camera3:
            case R.id.iv_shadow3:
                startChoosePhoto(REQUEST_CODE_TYPE3);
                break;
            case R.id.tv_submit:
                String name = mIevName.getText();
                String idNo = mIevNo.getText();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showShort("请输入姓名");
                    return;
                }
                if (TextUtils.isEmpty(idNo)) {
                    ToastUtils.showShort("请输入身份证号");
                    return;
                }
                NameAuthModel model = new NameAuthModel();
                model.setFullName(name);
                model.setIdNumber(idNo);
                model.setFront(frontImage);
                model.setBack(backImage);
                model.setIdCard(idCardImage);
                model.setUserId(MyApplication.getInstance().getUser().getUser_id());
                MvpPre.doAuth(model);
                break;
            case R.id.tv_exam:
                if (mIdentifyPopWindow == null) {
                    mIdentifyPopWindow = new IdentifyPopWindow(mContext);
                }
                if (mIdentifyPopWindow.isShowing()) {
                    mIdentifyPopWindow.dismiss();
                } else {
                    mIdentifyPopWindow.showAsDropDown(view, 0, ConvertUtils.dp2px(-10));
                }
                break;
        }
    }

    @Override
    public void sendCodeSuccess() {
        if (mIcvCode != null) {
            mIcvCode.startCountDown();
        }
    }

    @Override
    public void uploadImageSuccess(String imageUrl, int type) {
        if (type == REQUEST_CODE_TYPE1) {
            frontImage = imageUrl;
            mId1.post(new Runnable() {
                @Override
                public void run() {
                    mIv1.setVisibility(View.GONE);
                    mIvCamera1.setVisibility(View.GONE);
                    mIvShadow1.setVisibility(View.VISIBLE);
                    ImageLoader.loadImageCenterCrop(mContext, mId1, imageUrl);
                    checkInfo();
                }
            });
        } else if (type == REQUEST_CODE_TYPE2) {
            backImage = imageUrl;
            mId2.post(new Runnable() {
                @Override
                public void run() {
                    mIv2.setVisibility(View.GONE);
                    mIvCamera2.setVisibility(View.GONE);
                    mIvShadow2.setVisibility(View.VISIBLE);
                    ImageLoader.loadImageCenterCrop(mContext, mId2, imageUrl);
                    checkInfo();
                }
            });
        } else if (type == REQUEST_CODE_TYPE3) {
            idCardImage = imageUrl;
            mId3.post(new Runnable() {
                @Override
                public void run() {
                    mIvCamera3.setVisibility(View.GONE);
                    mIvShadow3.setVisibility(View.VISIBLE);
                    ImageLoader.loadImageCenterCrop(mContext, mId3, imageUrl);
                    checkInfo();
                }
            });
        }
    }

    @Override
    public void onGetCodeClick() {
        String phone = mIevPhone.getText();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort("请输入手机号");
            return;
        }
        MvpPre.sendCode(phone, 6);
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

    private void startChoosePhoto(int requestCode) {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .isCamera(true)
                .sizeMultiplier(0.5f)
                .setOutputCameraPath(Constants.FILE_PATH)
                .enableCrop(false)
                .compress(false)
                .withAspectRatio(162, 90)
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
}
