package com.spadea.xqipao.ui.chart.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.widget.dialog.CommonDialog;
import com.qpyy.module_news.bean.EmChatUserInfo;
import com.spadea.yuyin.R;
import com.spadea.xqipao.ui.chart.contacts.ChatMoreContacts;
import com.spadea.xqipao.ui.chart.presenter.ChatMorePresenter;
import com.spadea.xqipao.utils.view.DecorationHeadView;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouteConstants.CHAT_MORE)
public class ChatMoreActivity extends BaseMvpActivity<ChatMorePresenter> implements ChatMoreContacts.View {


    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.dhv)
    DecorationHeadView mDhv;
    @BindView(R.id.tv_name)
    TextView mTvName;

    @Autowired
    public String emChatUserName;

    @Autowired
    public EmChatUserInfo userInfo;

    @Override
    protected void initData() {
        MvpPre.getEmChatUserInfo(emChatUserName);
    }

    @Override
    protected void initView() {
        super.initView();
        mTvTitle.setText("更多");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_more;
    }

    @Override
    protected ChatMorePresenter bindPresenter() {
        return new ChatMorePresenter(this, this);
    }


    @OnClick({R.id.iv_back, R.id.tv_remark, R.id.tv_jb, R.id.tv_add_black, R.id.sl_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_remark:
                ARouter.getInstance().build(ARouteConstants.REMARK_NAME).navigation();
                break;
            case R.id.tv_jb:
                ARouter.getInstance().build(ARouteConstants.CHAT_REPORT).withString("userId", userInfo.getUser_id()).navigation();
                break;
            case R.id.tv_add_black:
                showConfirmDialog();
                break;
            case R.id.sl_info:
                ARouter.getInstance().build(ARouteConstants.USER_ZONE).withString("userId", userInfo.getUser_id()).navigation();
                break;
        }
    }

    private void showConfirmDialog() {
        CommonDialog commonDialog = new CommonDialog(this);
        commonDialog.setContent("是否将TA加入黑名单");
        commonDialog.setmOnClickListener(new CommonDialog.OnClickListener() {
            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightClick() {
                MvpPre.addUser2BlackList(userInfo.getUser_id(), emChatUserName);
            }
        });
        commonDialog.show();
    }

    @Override
    public void userInfo(EmChatUserInfo userInfo) {
        this.userInfo = userInfo;
        mTvName.setText(userInfo.getNickname());
        mDhv.setData(userInfo.getHead_picture(), userInfo.getPicture());
    }
}
