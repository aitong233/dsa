package com.spadea.xqipao.ui.me.activity;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.spadea.yuyin.MyApplication;
import com.spadea.yuyin.R;
import com.spadea.yuyin.util.Constants;
import com.spadea.yuyin.util.PreferencesUtils;
import com.spadea.xqipao.common.aroute.ARouters;
import com.spadea.xqipao.ui.base.view.BaseActivity;
import com.spadea.xqipao.ui.me.contacter.MessageSettingContact;
import com.spadea.xqipao.ui.me.presenter.MessageSettingPresenter;
import com.qpyy.libcommon.bean.UserBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.spirit.xqipao.ui.me.activity
 * 创建人 王欧
 * 创建时间 2020/4/1 10:10 AM
 * 描述 describe
 */
@Route(path = ARouters.MESSAGE_SETTING, name = "推送通知设置")
public class MessageSettingActivity extends BaseActivity<MessageSettingPresenter> implements MessageSettingContact.View, CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.sw0)
    Switch mSwBroadcast;
    @BindView(R.id.sw_voice)
    Switch mSwVoice;
    @BindView(R.id.sw_vibrate)
    Switch mSwVibrate;
    @BindView(R.id.sw1)
    Switch mSwFans;
    @BindView(R.id.sw_only_friend)
    Switch mSwOnlyFriend;

    public MessageSettingActivity() {
        super(R.layout.activity_message_setting);
    }

    @Override
    protected void initData() {
        mTvTitle.setText("推送通知设置");
    }

    @Override
    protected void initView() {
        UserBean user = MyApplication.getInstance().getUser();
        mSwBroadcast.setChecked(user.getBroadcast() == 1);
        mSwFans.setChecked(user.getFans() == 1);
        mSwVibrate.setChecked(user.getNews_vibrate() == 1);
        mSwVoice.setChecked(user.getNews_voice() == 1);
        mSwOnlyFriend.setChecked(user.getOnly_friend() == 1);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mSwBroadcast.setOnCheckedChangeListener(this);
        mSwFans.setOnCheckedChangeListener(this);
        mSwVibrate.setOnCheckedChangeListener(this);
        mSwVoice.setOnCheckedChangeListener(this);
        mSwOnlyFriend.setOnCheckedChangeListener(this);
    }

    @Override
    protected MessageSettingPresenter bindPresenter() {
        return new MessageSettingPresenter(this, this);
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
    public void setSuccess(int broadcast, int fans, int news_voice, int news_vibrate, int only_friend) {
        UserBean user = MyApplication.getInstance().getUser();
        if (user != null) {
            user.setBroadcast(broadcast);
            user.setFans(fans);
            user.setNews_voice(news_voice);
            user.setNews_vibrate(news_vibrate);
            user.setOnly_friend(only_friend);
            MyApplication.getInstance().setUser(user);
        }
    }

    @OnClick({R.id.iv_left, R.id.tv_left})
    public void onViewClicked(View view) {
        onBackPressed();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        PreferencesUtils.putInt(MyApplication.getInstance(), Constants.NEWS_VOICE, mSwVoice.isChecked() ? 1 : 2);
        PreferencesUtils.putInt(MyApplication.getInstance(), Constants.NEWS_VIBRATE, mSwVibrate.isChecked() ? 1 : 2);
        MvpPre.setting(mSwBroadcast.isChecked() ? 1 : 2, mSwFans.isChecked() ? 1 : 2, mSwVoice.isChecked() ? 1 : 2, mSwVibrate.isChecked() ? 1 : 2, mSwOnlyFriend.isChecked() ? 1 : 2);
    }
}
