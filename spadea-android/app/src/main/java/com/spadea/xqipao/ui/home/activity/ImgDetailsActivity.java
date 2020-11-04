package com.spadea.xqipao.ui.home.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouters.HOME_IMGDETAILS, name = "图片详情")
public class ImgDetailsActivity extends BaseActivity {

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

    @BindView(R.id.img)
    ImageView img;




    @Autowired
    public String title;
    @Autowired
    public ArrayList<String> list;


    public ImgDetailsActivity() {
        super(R.layout.activity_details);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvTitle.setText(title);
        ImageLoader.loadImage(this, img, list.get(0));
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


    @OnClick(R.id.iv_back)
    public void onClick(View view) {
        finish();
    }


}
