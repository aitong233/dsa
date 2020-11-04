package com.spadea.xqipao.ui.me.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.spadea.yuyin.R;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.data.SkillApplyModel;
import com.spadea.xqipao.data.SkillSection;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.NameIdentifyFailContacts;
import com.spadea.xqipao.ui.me.presenter.NameIdentifyFailPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.activity
 * 创建人 王欧
 * 创建时间 2020/5/21 3:45 PM
 * 描述 describe
 */
@Route(name = "审核失败", path = ARouters.APPLY_FAIL)
public class NameIdentifyFailActivity extends BaseActivity<NameIdentifyFailPresenter> implements NameIdentifyFailContacts.View {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;
    @BindView(R.id.tv_action)
    TextView mTvAction;

    @Autowired
    public String content;

    @Autowired
    public SkillSection.Item skill;

    @Autowired
    public int type;

    private SkillApplyModel mSkillApplyModel;

    public NameIdentifyFailActivity() {
        super(R.layout.activity_name_identify_fail);
    }

    @Override
    protected void initData() {
        mTvDesc.setText(content);
        if (type == 1) {
            mTvTitle.setText("实名认证");
        } else if (type == 2) {
            mTvTitle.setText("资质申请");
            MvpPre.getApply(skill.getId());
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected NameIdentifyFailPresenter bindPresenter() {
        return new NameIdentifyFailPresenter(this, this);
    }

    @OnClick({R.id.iv_back, R.id.tv_action})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_action:
                if (type == 1) {
                    ARouter.getInstance().build(ARouters.NAME_IDENTIFY).navigation();
                } else if (type == 2) {
                    ARouter.getInstance().build(ARouters.ME_QUALIFICATION_APPLY).withObject("skill", skill).withObject("applyModel", mSkillApplyModel).navigation();
                }
                finish();
                break;
        }
    }

    @Override
    public void applyInfo(SkillApplyModel info) {
        mSkillApplyModel = info;
        if (info != null) {
            mTvDesc.setText(info.getReason());
        }
    }

    @Override
    public void showLoadings() {
        showLoading();
    }

    @Override
    public void disLoadings() {
        disLoading();
    }
}
