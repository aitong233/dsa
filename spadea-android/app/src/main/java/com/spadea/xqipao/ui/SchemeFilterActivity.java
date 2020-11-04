package com.spadea.xqipao.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui
 * 创建人 王欧
 * 创建时间 2020/7/6 10:24 AM
 * 描述 describe
 */
public class SchemeFilterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();
        if (uri != null) {
            ARouter.getInstance().build(uri).navigation();
        }
        finish();
    }
}
