package com.spadea.xqipao.ui.login.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.makeramen.roundedimageview.RoundedImageView;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.Constants;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.utilcode.ImageUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.Constant;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.login.contacter.PerfectInformationContacts;
import com.spadea.xqipao.ui.login.presenter.PerfectInformationPresenter;
import com.spadea.xqipao.utils.LogUtils;
import com.spadea.xqipao.utils.SPUtil;
import com.qpyy.libcommon.bean.UserBean;
import com.spadea.xqipao.ui.base.view.BaseActivity;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;

@Route(path = ARouters.ME_PERFECTINFORMATION, name = "完善资料")
public class PerfectInformationActivity extends BaseActivity<PerfectInformationPresenter> implements PerfectInformationContacts.View {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.riv)
    RoundedImageView riv;

    @BindView(R.id.ed_nick_name)
    EditText edNickName;
    @BindView(R.id.rb_nan)
    RadioButton rbNan;
    @BindView(R.id.rb_nv)
    RadioButton rbNv;
    @BindView(R.id.rg_sex)
    RadioGroup rgSex;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.ed_invitation_code)
    EditText edInvitationCode;

    private DatePicker datePicker;

    private int startYear = 1900;
    private int startMonth = 1;
    private int startDay = 1;

    private int birthYear = Calendar.getInstance().get(Calendar.YEAR) - 1;
    private int birthMonth = 1;
    private int birthDay = 1;

    private String headPicture;
    private String nickname;
    private String sex = "1";
    private String invitationCode;
    private String birthday;

    @Autowired
    public int labelCount = 0;

    public PerfectInformationActivity() {
        super(R.layout.activity_perfect_information);
    }

    @Override
    protected void initData() {
        LogUtils.e("断点", "代码执行了");
        String userNo = SPUtil.getString(Constant.Channel.USERON);
        if (!TextUtils.isEmpty(userNo)) {
            edInvitationCode.setText(userNo);
        }
        UserBean user = MyApplication.getInstance().getUser();
        if (user != null && !TextUtils.isEmpty(user.getHead_picture())) {
            this.headPicture = user.getHead_picture();
            ImageLoader.loadHead(MyApplication.getContext(), riv, user.getHead_picture());
        }
        if (user != null && !TextUtils.isEmpty(user.getNickname())) {
            edNickName.setText(user.getNickname());
        }
    }

    @Override
    protected void initView() {
        tvTitle.setText("完善资料");
        viewLine.setVisibility(View.GONE);
    }


    @Override
    protected void setListener() {
        super.setListener();
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_nan:
                        sex = "1";
                        break;
                    case R.id.rb_nv:
                        sex = "2";
                        break;
                }
            }
        });
    }

    @Override
    protected PerfectInformationPresenter bindPresenter() {
        return new PerfectInformationPresenter(this, this);
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }

    @OnClick({R.id.iv_back, R.id.iv_add_img, R.id.tv_commit, R.id.tv_birthday})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_commit:
                nickname = edNickName.getText().toString().trim();
                String userNo = edInvitationCode.getText().toString();
                if (TextUtils.isEmpty(nickname)) {
                    ToastUtils.showShort("请输入昵称");
                    return;
                }
                if (TextUtils.isEmpty(headPicture)) {
                    ToastUtils.showShort("请设置头像");
                    return;
                }
                if (TextUtils.isEmpty(sex)) {
                    ToastUtils.showShort("请选择性别");
                    return;
                }
                if (TextUtils.isEmpty(birthday)) {
                    ToastUtils.showShort("请选择出生日期");
                    return;
                }
                MvpPre.updateUserInfo("", birthday, "", "", "", "", sex, headPicture, nickname, "", userNo);
                break;
            case R.id.tv_birthday:
                if (datePicker == null) {
                    datePicker = new DatePicker(this);
                    datePicker.setTitleText("");
                    datePicker.setSubmitTextColor(getResources().getColor(R.color.color_main));
                    datePicker.setCancelTextColor(getResources().getColor(R.color.color_main));
                    datePicker.setPressedTextColor(getResources().getColor(R.color.color_main));
                    datePicker.setDividerColor(getResources().getColor(R.color.color_c9));
                    datePicker.setTopLineColor(getResources().getColor(R.color.color_c9));
                    datePicker.setTextColor(getResources().getColor(R.color.color_text));
                    datePicker.setLabelTextColor(getResources().getColor(R.color.color_text));
                    datePicker.setCanceledOnTouchOutside(true);
                    datePicker.setUseWeight(true);
                    datePicker.setCancelTextColor(getResources().getColor(R.color.color_main));
                    datePicker.setSubmitTextColor(getResources().getColor(R.color.color_main));
                    datePicker.setTopPadding(ConvertUtils.toPx(this, 10f));
                    datePicker.setRangeStart(startYear, startMonth, startDay);//最开始的时间
                    datePicker.setRangeEnd(Calendar.getInstance().get(Calendar.YEAR) - 1, Calendar.getInstance().get(Calendar.MONTH) + 1, Calendar.getInstance().get(Calendar.DATE));
                    datePicker.setSelectedItem(birthYear, birthMonth, birthDay);
                    datePicker.setResetWhileWheel(false);
                    datePicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {

                        @Override
                        public void onDatePicked(String year, String month, String day) {
                            tvBirthday.setText(year + "-" + month + "-" + day);
                            birthday = year + "-" + month + "-" + day;
                            tvBirthday.setTextColor(Color.parseColor("#333333"));
                        }
                    });
                }
                datePicker.show();
                break;
            case R.id.iv_add_img:
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())
                        .selectionMode(PictureConfig.SINGLE)
                        .previewImage(true)
                        .isCamera(true)
                        .sizeMultiplier(0.5f)
                        .setOutputCameraPath(Constants.FILE_PATH)
                        .enableCrop(true)
                        .compress(true)
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
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                if (localMedia != null && localMedia.size() != 0) {
                    MvpPre.uplodImg(localMedia);
                }
                break;
        }
    }

    @Override
    public void uploadImg(String url) {
        this.headPicture = url;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageLoader.loadHead(PerfectInformationActivity.this, riv, url);
            }
        });
    }

    @Override
    public void updateUserInfoSuccess() {
        UserBean user = MyApplication.getInstance().getUser();
        user.setSex(Integer.parseInt(sex));
        user.setNickname(nickname);
        user.setBirthday(birthday);
        MyApplication.getInstance().setUser(user);
        ToastUtils.showShort("设置成功");
        if (labelCount == 0) {
            ARouter.getInstance().build(ARouters.ME_LABEL).navigation();
            finish();
        } else {
            finish();
        }
    }
}
