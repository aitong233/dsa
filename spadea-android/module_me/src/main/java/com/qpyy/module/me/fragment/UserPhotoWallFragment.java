package com.qpyy.module.me.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ThreadUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qpyy.libcommon.base.BaseMvpFragment;
import com.qpyy.libcommon.constant.Constants;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.module.me.R;
import com.qpyy.module.me.R2;
import com.qpyy.module.me.activity.MNImageBrowserActivity;
import com.qpyy.module.me.adapter.UserPhotoWallAdapter;
import com.qpyy.module.me.bean.PhotoWallResp;
import com.qpyy.module.me.bean.XBannerData;
import com.qpyy.module.me.contacts.UserPhotoWallContacts;
import com.qpyy.module.me.presenter.UserPhotoWallPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import butterknife.BindView;

/**
 * 用户照片墙
 */
public class UserPhotoWallFragment extends BaseMvpFragment<UserPhotoWallPresenter> implements UserPhotoWallContacts.View {


    @BindView(R2.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    private UserPhotoWallAdapter mUserPhotoWallAdapter;
    private static Queue<File> queue = new LinkedList<File>();
    private String userId;
    private int p = 1;


    public static UserPhotoWallFragment newInstance(String userId) {
        UserPhotoWallFragment fragment = new UserPhotoWallFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected UserPhotoWallPresenter bindPresenter() {
        return new UserPhotoWallPresenter(this, getContext());
    }

    @Override
    protected void initData() {
        userId = getArguments().getString("userId");
        MvpPre.getPhotoWall(userId, p);
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(mUserPhotoWallAdapter = new UserPhotoWallAdapter());
        mUserPhotoWallAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                String id = SpUtils.getUserId();
                if (userId.equals(id)) {
                    mUserPhotoWallAdapter.setDelete(true);
                }
                return false;
            }
        });
        mUserPhotoWallAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                PhotoWallResp.GiftResp item = mUserPhotoWallAdapter.getItem(position);
                MvpPre.deletePhoto(item.getId(), position);
            }
        });
        mUserPhotoWallAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PhotoWallResp.GiftResp item = mUserPhotoWallAdapter.getItem(position);
                if ("0".equals(item.getId())) {
                    startChoosePhoto(PictureMimeType.ofImage(), PictureConfig.CHOOSE_REQUEST);
                } else {
                    List<XBannerData> list = new ArrayList<>();
                    for (PhotoWallResp.GiftResp giftResp : giftRespList) {
                        list.add(new XBannerData(0, giftResp.getUrl(), ""));
                    }
                    MNImageBrowserActivity.startActivity(getContext(), list, position);
                }
            }
        });
        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                p++;
                MvpPre.getPhotoWall(userId, p);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                p = 1;
                MvpPre.getPhotoWall(userId, p);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.me_fragment_user_photo_wall;
    }

    private List<PhotoWallResp.GiftResp> giftRespList = new ArrayList<>();

    @Override
    public void setPhotoWall(PhotoWallResp data) {
        List<PhotoWallResp.GiftResp> list = data.getList();
        if (p == 1) {
            giftRespList.clear();
            giftRespList.add(new PhotoWallResp.GiftResp("-1", data.getAvatar()));
            giftRespList.addAll(list);
        } else {
            giftRespList.addAll(list);
        }
        ArrayList<PhotoWallResp.GiftResp> giftResps = new ArrayList<>(giftRespList);
        if (userId.equals(SpUtils.getUserId())) {
            giftResps.add(new PhotoWallResp.GiftResp("0", ""));
        }
        mUserPhotoWallAdapter.setNewData(giftResps);
        if (list == null || list.size() == 0) {
            mSmartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
        mUserPhotoWallAdapter.setDelete(false);
    }

    @Override
    public void deletePhotoSuccess(int index) {
        mUserPhotoWallAdapter.remove(index);
    }

    @Override
    public void finishRefresh() {
        mSmartRefreshLayout.finishRefresh();
        mSmartRefreshLayout.finishLoadMore();
    }

    @Override
    public void upLoadSuccess(String url, int type) {
        ThreadUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MvpPre.addPhoto(url);
            }
        });
    }

    @Override
    public void checkImageSuccess(String type, String image) {

    }

    @Override
    public void addPhotoSuccess() {
        mSmartRefreshLayout.autoRefresh();
    }

    @Override
    public void uploadFileComplete() {
        if (queue != null && queue.size() != 0) {
            MvpPre.uploadFile(queue.poll(), 0);
        }
    }


    private void startChoosePhoto(int mimeType, int requestCode) {
        PictureSelector.create(this)
                .openGallery(mimeType)
                .selectionMode(PictureConfig.MULTIPLE)
                .maxSelectNum(9)
                .minSelectNum(1)
                .previewImage(false)
                .isCamera(true)
                .sizeMultiplier(0.5f)
                .setOutputCameraPath(Constants.FILE_PATH)
                .compress(true)
                .withAspectRatio(1, 1)
                .cropCompressQuality(Constants.CROP_COMPRESS_SIZE)// 裁剪压缩质量 默认90 int
                .minimumCompressSize(Constants.COMPRESS_INGNORE)// 小于100kb的图片不压缩
                .compressSavePath(ImageUtils.getImagePath())
                .isDragFrame(false)
                .forResult(requestCode);//结果回调onActivityResult code
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && data != null) {
            if (queue != null) {
                queue.clear();
            }
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                    if (localMedia != null && localMedia.size() != 0) {
                        for (LocalMedia imgMedia : localMedia) {
                            String url;
                            if (imgMedia.isCompressed()) {
                                url = imgMedia.getCompressPath();
                            } else {
                                url = imgMedia.getPath();
                            }
                            queue.add(new File(url));
                        }
                        MvpPre.uploadFile(queue.poll(), 0);
                    }
                    break;
            }
        }
    }


}
