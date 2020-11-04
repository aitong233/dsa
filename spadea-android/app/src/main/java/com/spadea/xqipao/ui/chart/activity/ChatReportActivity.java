package com.spadea.xqipao.ui.chart.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjq.toast.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.constant.Constants;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.module_news.bean.ReportType;
import com.spadea.yuyin.R;
import com.spadea.xqipao.ui.chart.contacts.ChatReportContacts;
import com.spadea.xqipao.ui.chart.presenter.ChatReportPresenter;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.CHAT_REPORT)
public class ChatReportActivity extends BaseMvpActivity<ChatReportPresenter> implements ChatReportContacts.View {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    @BindView(R.id.iv_pic)
    ImageView mIvPic;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.tv_num)
    TextView mTvNum;
    private ReportTypeAdapter mAdapter;

    private String imageUrl;

    @Autowired
    public String userId;

    @Override
    protected void initData() {
        MvpPre.getReportType();
    }

    @Override
    protected void initView() {
        super.initView();
        mTvTitle.setText("举报");
        mEtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvNum.setText(String.valueOf(s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new ReportTypeAdapter();
        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_report;
    }

    @Override
    protected ChatReportPresenter bindPresenter() {
        return new ChatReportPresenter(this, this);
    }

    @OnClick({R.id.iv_back, R.id.iv_pic, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_pic:
                startChoosePhoto();
                break;
            case R.id.tv_save:
                String reportType = mAdapter.getSelectedType();
                if (TextUtils.isEmpty(reportType)) {
                    ToastUtils.show("请完善举报信息");
                    return;
                }
                String content = mEtContent.getText().toString();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.show("请完善举报信息");
                    return;
                }
                if (TextUtils.isEmpty(imageUrl)) {
                    ToastUtils.show("请完善举报信息");
                    return;
                }
                MvpPre.reportUser(imageUrl, userId, content, reportType);
                break;
        }
    }

    @Override
    public void reportType(List<ReportType> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void reportSuccess() {
        ToastUtils.show("举报成功");
        finish();
    }

    @Override
    public void upLoadSuccess(String url, int type) {
        ImageUtils.loadCenterCrop(url, mIvPic);
        imageUrl = url;
    }

    private static class ReportTypeAdapter extends BaseQuickAdapter<ReportType, BaseViewHolder> {
        private int index = -1;

        public ReportTypeAdapter() {
            super(R.layout.news_rv_item_report_type, null);
        }

        @Override
        protected void convert(BaseViewHolder helper, ReportType item) {
            if (helper.getAdapterPosition() == index) {
                helper.setTextColor(R.id.text, Utils.getApp().getResources().getColor(R.color.color_FF6765FF));
                helper.setBackgroundRes(R.id.text, R.drawable.common_bg_r99_dbd2ff);
            } else {
                helper.setTextColor(R.id.text, Utils.getApp().getResources().getColor(R.color.color_FF9C9C9C));
                helper.setBackgroundRes(R.id.text, R.drawable.common_bg_r99_f2f2f2);
            }
            helper.setText(R.id.text, item.getName());
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setIndex(helper.getAdapterPosition());
                }
            });
            if ((helper.getAdapterPosition() + 1) % 3 == 0) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) helper.itemView.getLayoutParams();
                layoutParams.rightMargin = 0;
                helper.itemView.setLayoutParams(layoutParams);
            }
        }

        public void setIndex(int index) {
            this.index = index;
            notifyDataSetChanged();
        }

        public String getSelectedType() {
            if (index != -1) {
                return getItem(index).getName();
            }
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE) {
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
            }
        }
    }

    private static final int REQUEST_CODE = 2000;

    private void startChoosePhoto() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .isCamera(true)
                .sizeMultiplier(0.5f)
                .setOutputCameraPath(Constants.FILE_PATH)
                .enableCrop(false)
                .compress(false)
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
                .forResult(REQUEST_CODE);//结果回调onActivityResult code
    }
}
