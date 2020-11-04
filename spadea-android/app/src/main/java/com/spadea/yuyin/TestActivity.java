package com.spadea.yuyin;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.base.IPresenter;
import com.spadea.yuyin.util.utilcode.SpanUtils;
import com.spadea.yuyin.R;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;

public class TestActivity extends BaseMvpActivity {
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

//
        new Thread(new Runnable() {

            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            public void run() {
                try {
                    SpanUtils spanUtils = new SpanUtils();
                    Bitmap bitmap1 = Glide.with(TestActivity.this).asBitmap().load("http://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/admin_images/5efd8ac7b9463.png").diskCacheStrategy(DiskCacheStrategy.ALL).into(50, 50).get();
                    Bitmap bitmap2 = Glide.with(TestActivity.this).asBitmap().load("http://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/admin_images/5efd8970ced99.png").diskCacheStrategy(DiskCacheStrategy.ALL).into(50, 50).get();
                    spanUtils.append(Html.fromHtml("<font size=\"50\" color=\"red\">设置了字号和颜色</font>"));
                    spanUtils.appendImage(bitmap1, SpanUtils.ALIGN_CENTER);
                    spanUtils.appendImage(bitmap2, SpanUtils.ALIGN_CENTER);
                    spanUtils.appendImage(getResources().getDrawable(R.mipmap.a2), SpanUtils.ALIGN_CENTER);
                    spanUtils.appendImage(getResources().getDrawable(R.mipmap.ic_user_new), SpanUtils.ALIGN_CENTER);
                    Bitmap bitmap = Glide.with(TestActivity.this).asBitmap().load(R.mipmap.ic_user_new).into(50, 50).get();
                    spanUtils.appendImage(bitmap, SpanUtils.ALIGN_CENTER);
                    Bitmap bitmap3 = Glide.with(TestActivity.this).asBitmap().load("https://yutangyuyin.oss-cn-hangzhou.aliyuncs.com/user-dir/EmSWct5BMD.png").diskCacheStrategy(DiskCacheStrategy.ALL).into(50, 50).get();
                    spanUtils.appendImage(bitmap3, SpanUtils.ALIGN_CENTER);
                    spanUtils.append(Html.fromHtml("<font size=\"50\" color=\"red\">设置了字号和颜色</font>"));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvText.setText(spanUtils.create());
                        }
                    });
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }


}
