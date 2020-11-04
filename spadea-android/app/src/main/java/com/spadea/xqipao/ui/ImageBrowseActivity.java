package com.spadea.xqipao.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.utils.view.ViewPagerFixed;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.utilcode.BarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ImageBrowseActivity extends BaseActivity {
    private static final String TAG = ImageBrowseActivity.class.getName();
    @BindView(R.id.viewPager)
    ViewPagerFixed viewPager;
    @BindView(R.id.hint)
    TextView hint;


    private int postion = 0;
    private List<String> list = new ArrayList<>();
    private ImgViewPagerAdapter imgViewPagerAdapter;


    public ImageBrowseActivity() {
        super(R.layout.activity_image_browse);
    }

    public static void start(Activity activity, int postion, List<String> list) {
        Intent intent = new Intent(activity, ImageBrowseActivity.class);
        intent.putExtra("postion", postion);
        intent.putStringArrayListExtra("img_data", (ArrayList<String>) list);
        activity.startActivity(intent);
    }

    @Override
    public void initData() {
        postion = getIntent().getIntExtra("postion", 0);
        list = getIntent().getStringArrayListExtra("img_data");
        viewPager.setAdapter(imgViewPagerAdapter = new ImgViewPagerAdapter(this, list));
        viewPager.setCurrentItem(postion);
        hint.setText(postion + 1 + "/" + list.size());
    }

    /**
     * init view
     */
    @Override
    public void initView() {
        BarUtils.setStatusBarAlpha(this, 0);
        BarUtils.setAndroidNativeLightStatusBar(this, false);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                hint.setText(i + 1 + "/" + list.size());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected IPresenter bindPresenter() {
        return null;
    }


    @Override
    public void showLoadings() {

    }

    @Override
    public void disLoadings() {

    }


    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        finish();
    }


}
