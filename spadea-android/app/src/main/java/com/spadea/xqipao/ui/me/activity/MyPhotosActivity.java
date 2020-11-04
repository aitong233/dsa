package com.spadea.xqipao.ui.me.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.spadea.yuyin.R;
import com.spadea.yuyin.base.CustomPicturePreviewActivity;
import com.spadea.yuyin.util.Constants;
import com.spadea.yuyin.util.ImageLoader;
import com.spadea.yuyin.util.utilcode.ImageUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.MyPhotoItem;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.MyPhotosContacts;
import com.spadea.xqipao.ui.me.presenter.MyPhotosPresenter;
import com.spadea.xqipao.ui.me.adapter.MyPhotoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.MY_PHOTOS)
public class MyPhotosActivity extends BaseActivity<MyPhotosPresenter> implements MyPhotosContacts.View, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.tv_left)
    TextView mTvLeft;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.topbar_line)
    View mTopbarLine;
    @BindView(R.id.ll_topbar)
    LinearLayout mLlTopbar;
    private MyPhotoAdapter mAdapter;

    public MyPhotosActivity() {
        super(R.layout.activity_my_photos);
    }

    @Override
    protected void initData() {
        MvpPre.getUserPhotos();
    }

    @Override
    protected void initView() {
        mTvTitle.setText("相册");
        mTvRight.setText("管理");
        mTvLeft.setText("取消");
        mTvRight.setVisibility(View.VISIBLE);
        mRv.setLayoutManager(new GridLayoutManager(this, 3));
        List<MyPhotoItem> items = new ArrayList<>();
        items.add(new MyPhotoItem(R.drawable.album_icon_sc));
        mAdapter = new MyPhotoAdapter(items);
        mRv.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRv);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    protected MyPhotosPresenter bindPresenter() {
        return new MyPhotosPresenter(this, this);
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }

    @Override
    public void onBackPressed() {
        String text = mTvRight.getText().toString();
        if ("删除".equals(text)) {
            mTvRight.setText("管理");
            mTvLeft.setVisibility(View.GONE);
            mAdapter.setInEdit(false);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick({R.id.iv_left, R.id.tv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
            case R.id.tv_left:
                onBackPressed();
                break;
            case R.id.tv_right:
                if (view instanceof TextView) {
                    switch (((TextView) view).getText().toString()) {
                        case "管理":
                            mTvLeft.setVisibility(View.VISIBLE);
                            mTvRight.setText("删除");
                            mAdapter.setInEdit(true);
                            break;
                        case "删除":
                            String ids = mAdapter.getCheckedIds();
                            if (TextUtils.isEmpty(ids)) {
                                ToastUtils.showShort("请选择要删除的图片");
                                return;
                            } else {
                                new AlertDialog.Builder(this)
                                        .setMessage("确定要删除吗？")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                MvpPre.deleteUserPhoto(ids);
                                            }
                                        })
                                        .setNegativeButton("取消", null).show();
                            }

                            break;
                    }
                }
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MyPhotoItem item = mAdapter.getItem(position);
        if (item != null) {
            if (item.getSrc() == 0) {
                ArrayList<LocalMedia> mutableList = new ArrayList<>();
                for (MyPhotoItem photoItem : mAdapter.getData()) {
                    if (photoItem.getSrc() == 0) {
                        LocalMedia localMedia = new LocalMedia();
                        localMedia.setPath(ImageLoader.getUrl(photoItem.getUrl()
                        ));
                        mutableList.add(localMedia);
                    }
                }
                Intent intent = new Intent(this, CustomPicturePreviewActivity.class);
                intent.putExtra("previewSelectList", mutableList);
                intent.putExtra("position", 0);
                startActivity(intent);
            } else {
                startChoosePhoto();
            }
        }
    }

    private void startChoosePhoto() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .isCamera(true)
                .isGif(true)
                .sizeMultiplier(0.5f)
                .setOutputCameraPath(Constants.FILE_PATH)
                .enableCrop(false)
                .compress(false)
                .minimumCompressSize(Constants.COMPRESS_INGNORE)// 小于100kb的图片不压缩
                .compressSavePath(ImageUtils.getImagePath())
                .freeStyleCropEnabled(false)
                .circleDimmedLayer(false)
                .showCropFrame(false)
                .rotateEnabled(false) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .isDragFrame(false)
                .forResult(300);//结果回调onActivityResult code
    }

    @Override
    public void uploadImageSuccess(String imageUrl) {
        MvpPre.addUserPhoto(imageUrl);
    }

    @Override
    public void addUserPhotoSuccess() {
        MvpPre.getUserPhotos();
    }

    @Override
    public void deleteUserPhotoSuccess() {
        MvpPre.getUserPhotos();
    }

    @Override
    public void userPhotos(List<MyPhotoItem> photoItems) {
        mTvRight.setText("管理");
        mTvLeft.setVisibility(View.GONE);
        mAdapter.setInEdit(false);
        photoItems.add(new MyPhotoItem(R.drawable.album_icon_sc));
        mAdapter.setNewData(photoItems);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
            if (localMedia != null && localMedia.size() != 0) {
                MvpPre.uploadImage(localMedia);
            }
        }
    }
}
