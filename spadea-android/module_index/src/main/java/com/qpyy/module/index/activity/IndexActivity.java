package com.qpyy.module.index.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.FragmentUtils;
import com.qpyy.libcommon.base.BaseAppCompatActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.module.index.R;
import com.qpyy.module.index.fragment.IndexFragment;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.module.index.activity
 * 创建人 王欧
 * 创建时间 2020/6/28 10:56 AM
 * 描述 describe
 */
@Route(path = ARouteConstants.INDEX_INDEX)
public class IndexActivity extends BaseAppCompatActivity {
    @Override
    protected void initData() {
//        SpUtils.putToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiI2NDI2ODIiLCJpYXQiOjE1OTI3OTA4NjgsImV4cCI6MTU5NTM4Mjg2OH0.UMjIV6gaTNaYNLgJFakBFU6DiF9Er8z2AqVsz9RtA4Y");
        FragmentUtils.add(getSupportFragmentManager(), IndexFragment.newInstance(), R.id.fl_container);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.index_activity_index;
    }

//    @OnClick({R2.id.btn_rank_list})
//    public void onViewClicked(View view) {
//        ARouter.getInstance().build(ARouteConstants.INDEX_RANKING_LIST).navigation();
//    }
}
