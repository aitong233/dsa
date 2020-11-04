package com.spadea.yuyin.base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.spadea.yuyin.util.Constants;
import com.spadea.yuyin.util.utilcode.FileUtils;
import com.spadea.yuyin.util.utilcode.ToastUtils;
import com.spadea.yuyin.R;
import com.luck.picture.lib.PictureBaseActivity;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.dialog.CustomDialog;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.photoview.OnViewTapListener;
import com.luck.picture.lib.photoview.PhotoView;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.luck.picture.lib.tools.ScreenUtils;
import com.luck.picture.lib.tools.ToastManage;
import com.luck.picture.lib.widget.PreviewViewPager;
import com.luck.picture.lib.widget.longimage.ImageSource;
import com.luck.picture.lib.widget.longimage.ImageViewState;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CustomPicturePreviewActivity extends PictureBaseActivity implements View.OnClickListener {
    private ImageButton left_back;
    private TextView tv_title;
    private TextView tv_right;
    private PreviewViewPager viewPager;
    private List<LocalMedia> images = new ArrayList();
    private int position = 0;
    private String directory_path;
    private CustomPicturePreviewActivity.SimpleFragmentAdapter adapter;
    private LayoutInflater inflater;
    private RxPermissions rxPermissions;
    private CustomPicturePreviewActivity.loadDataThread loadDataThread;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    String path = (String) msg.obj;
                    ToastManage.s(CustomPicturePreviewActivity.this.mContext, CustomPicturePreviewActivity.this.getString(R.string.picture_save_success) + "\n" + path);
                    CustomPicturePreviewActivity.this.dismissDialog();
                default:
            }
        }
    };

    public CustomPicturePreviewActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_picture_preview);
        this.inflater = LayoutInflater.from(this);
        this.tv_title = (TextView) this.findViewById(R.id.picture_title);
        this.left_back = (ImageButton) this.findViewById(R.id.left_back);
        this.tv_right = findViewById(R.id.tv_right);
        this.viewPager = (PreviewViewPager) this.findViewById(R.id.preview_pager);
        this.position = this.getIntent().getIntExtra("position", 0);
        this.directory_path = this.getIntent().getStringExtra("directory_path");
        this.images = (List) this.getIntent().getSerializableExtra("previewSelectList");
        this.left_back.setOnClickListener(this);
        this.tv_right.setOnClickListener(this);
        this.initViewPageAdapterData();
    }

    private void initViewPageAdapterData() {
        this.tv_title.setText(this.position + 1 + "/" + this.images.size());
        this.adapter = new CustomPicturePreviewActivity.SimpleFragmentAdapter();
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.setCurrentItem(this.position);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                CustomPicturePreviewActivity.this.tv_title.setText(position + 1 + "/" + CustomPicturePreviewActivity.this.images.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_back:
                this.finish();
                this.overridePendingTransition(0, R.anim.a3);
                break;
            case R.id.tv_right:
                String it = images.get(position).getPath();
                String filePath = Constants.IMAGE_PATH + FileUtils.getFileName(it);
                if (FileUtils.createOrExistsDir(Constants.IMAGE_PATH)) {
                    OkGo.<File>get(it).execute(new FileCallback(Constants.IMAGE_PATH, FileUtils.getFileName(it)) {
                        @Override
                        public void onSuccess(Response<File> response) {
                            ToastUtils.showShort("图片下载成功");
                            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + filePath)));
                        }

                        @Override
                        public void onError(Response<File> response) {
                            super.onError(response);
                            ToastUtils.showShort("图片下载失败");
                        }
                    });
                }
                break;
            default:
                break;
        }

    }

    private void displayLongPic(Bitmap bmp, SubsamplingScaleImageView longImg) {
        longImg.setQuickScaleEnabled(true);
        longImg.setZoomEnabled(true);
        longImg.setPanEnabled(true);
        longImg.setDoubleTapZoomDuration(100);
        longImg.setMinimumScaleType(2);
        longImg.setDoubleTapZoomDpi(2);
        longImg.setImage(ImageSource.cachedBitmap(bmp), new ImageViewState(0.0F, new PointF(0.0F, 0.0F), 0));
    }

    private void showDownLoadDialog(final String path) {
        final CustomDialog dialog = new CustomDialog(this, ScreenUtils.getScreenWidth(this) * 3 / 4, ScreenUtils.getScreenHeight(this) / 4, R.layout.picture_wind_base_dialog_xml, R.style.Theme_dialog);
        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btn_commit = (Button) dialog.findViewById(R.id.btn_commit);
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tv_content = (TextView) dialog.findViewById(R.id.tv_content);
        tv_title.setText(this.getString(R.string.picture_prompt));
        tv_content.setText(this.getString(R.string.picture_prompt_content));
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomPicturePreviewActivity.this.showPleaseDialog();
                boolean isHttp = PictureMimeType.isHttp(path);
                if (isHttp) {
                    CustomPicturePreviewActivity.this.loadDataThread = CustomPicturePreviewActivity.this.new loadDataThread(path);
                    CustomPicturePreviewActivity.this.loadDataThread.start();
                } else {
                    try {
                        String dirPath = PictureFileUtils.createDir(CustomPicturePreviewActivity.this, System.currentTimeMillis() + ".png", CustomPicturePreviewActivity.this.directory_path);
                        PictureFileUtils.copyFile(path, dirPath);
                        ToastManage.s(CustomPicturePreviewActivity.this.mContext, CustomPicturePreviewActivity.this.getString(R.string.picture_save_success) + "\n" + dirPath);
                        CustomPicturePreviewActivity.this.dismissDialog();
                    } catch (IOException var4) {
                        ToastManage.s(CustomPicturePreviewActivity.this.mContext, CustomPicturePreviewActivity.this.getString(R.string.picture_save_error) + "\n" + var4.getMessage());
                        CustomPicturePreviewActivity.this.dismissDialog();
                        var4.printStackTrace();
                    }
                }

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showLoadingImage(String urlPath) {
        try {
            URL u = new URL(urlPath);
            String path = PictureFileUtils.createDir(this, System.currentTimeMillis() + ".png", this.directory_path);
            byte[] buffer = new byte[8192];
            int ava = 0;
            long start = System.currentTimeMillis();
            BufferedInputStream bin = new BufferedInputStream(u.openStream());

            int read;
            BufferedOutputStream bout;
            long var10000;
            for (bout = new BufferedOutputStream(new FileOutputStream(path)); (read = bin.read(buffer)) > -1; var10000 = (long) ava / (System.currentTimeMillis() - start)) {
                bout.write(buffer, 0, read);
                ava += read;
            }

            bout.flush();
            bout.close();
            Message message = this.handler.obtainMessage();
            message.what = 200;
            message.obj = path;
            this.handler.sendMessage(message);
        } catch (IOException var13) {
            ToastManage.s(this.mContext, this.getString(R.string.picture_save_error) + "\n" + var13.getMessage());
            var13.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        this.overridePendingTransition(0, R.anim.a3);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.loadDataThread != null) {
            this.handler.removeCallbacks(this.loadDataThread);
            this.loadDataThread = null;
        }

    }

    public class loadDataThread extends Thread {
        private String path;

        public loadDataThread(String path) {
            this.path = path;
        }

        @Override
        public void run() {
            try {
                CustomPicturePreviewActivity.this.showLoadingImage(this.path);
            } catch (Exception var2) {
                var2.printStackTrace();
            }

        }
    }

    public class SimpleFragmentAdapter extends PagerAdapter {
        public SimpleFragmentAdapter() {
        }

        @Override
        public int getCount() {
            return CustomPicturePreviewActivity.this.images.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View contentView = CustomPicturePreviewActivity.this.inflater.inflate(R.layout.picture_image_preview, container, false);
            final PhotoView imageView = (PhotoView) contentView.findViewById(R.id.preview_image);
            final SubsamplingScaleImageView longImg = (SubsamplingScaleImageView) contentView.findViewById(R.id.longImg);
            LocalMedia media = (LocalMedia) CustomPicturePreviewActivity.this.images.get(position);
            if (media != null) {
                String pictureType = media.getPictureType();
                final String path;
                if (media.isCut() && !media.isCompressed()) {
                    path = media.getCutPath();
                } else if (!media.isCompressed() && (!media.isCut() || !media.isCompressed())) {
                    path = media.getPath();
                } else {
                    path = media.getCompressPath();
                }

                boolean isHttp = PictureMimeType.isHttp(path);
                if (isHttp) {
                    CustomPicturePreviewActivity.this.showPleaseDialog();
                }

                boolean isGif = PictureMimeType.isGif(pictureType);
                final boolean eqLongImg = PictureMimeType.isLongImg(media);
                imageView.setVisibility(eqLongImg && !isGif ? View.GONE : View.VISIBLE);
                longImg.setVisibility(eqLongImg && !isGif ? View.VISIBLE : View.GONE);
                RequestOptions options;
                if (isGif && !media.isCompressed()) {
                    options = (new RequestOptions()).override(480, 800).priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.NONE);
                    Glide.with(CustomPicturePreviewActivity.this).asGif().apply(options).load(path).listener(new RequestListener<GifDrawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                            CustomPicturePreviewActivity.this.dismissDialog();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                            CustomPicturePreviewActivity.this.dismissDialog();
                            return false;
                        }
                    }).into(imageView);
                } else {
                    options = (new RequestOptions()).diskCacheStrategy(DiskCacheStrategy.ALL);
                    Glide.with(CustomPicturePreviewActivity.this).asBitmap().load(path).apply(options).into(new SimpleTarget<Bitmap>(480, 800) {
                        @Override
                        public void onLoadFailed(@Nullable Drawable errorDrawable) {
                            super.onLoadFailed(errorDrawable);
                            CustomPicturePreviewActivity.this.dismissDialog();
                        }

                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            CustomPicturePreviewActivity.this.dismissDialog();
                            if (eqLongImg) {
                                CustomPicturePreviewActivity.this.displayLongPic(resource, longImg);
                            } else {
                                imageView.setImageBitmap(resource);
                            }

                        }
                    });
                }
                imageView.setOnViewTapListener(new OnViewTapListener() {
                    @Override
                    public void onViewTap(View view, float x, float y) {
                        CustomPicturePreviewActivity.this.finish();
                        CustomPicturePreviewActivity.this.overridePendingTransition(0, R.anim.a3);
                    }
                });
                longImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CustomPicturePreviewActivity.this.finish();
                        CustomPicturePreviewActivity.this.overridePendingTransition(0, R.anim.a3);
                    }
                });
//                imageView.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//                        if (CustomPicturePreviewActivity.this.rxPermissions == null) {
//                            CustomPicturePreviewActivity.this.rxPermissions = new RxPermissions(CustomPicturePreviewActivity.this);
//                        }
//
//                        CustomPicturePreviewActivity.this.rxPermissions.request(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}).subscribe(new Observer<Boolean>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//                            }
//
//                            @Override
//                            public void onNext(Boolean aBoolean) {
//                                if (aBoolean.booleanValue()) {
//                                    CustomPicturePreviewActivity.this.showDownLoadDialog(path);
//                                } else {
//                                    ToastManage.s(CustomPicturePreviewActivity.this.mContext, CustomPicturePreviewActivity.this.getString(R.string.picture_jurisdiction));
//                                }
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                            }
//
//                            @Override
//                            public void onComplete() {
//                            }
//                        });
//                        return true;
//                    }
//                });
            }

            container.addView(contentView, 0);
            return contentView;
        }
    }
}
