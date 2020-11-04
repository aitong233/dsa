package com.qpyy.room.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.toast.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qpyy.libcommon.base.BaseMvpActivity;
import com.qpyy.libcommon.bean.RoomKickOutModel;
import com.qpyy.libcommon.constant.ARouteConstants;
import com.qpyy.libcommon.constant.Constants;
import com.qpyy.libcommon.utils.ImageUtils;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.libcommon.utils.SpUtils;
import com.qpyy.room.R;
import com.qpyy.room.R2;
import com.qpyy.room.adapter.RoomAdminOrBlacklistAdapter;
import com.qpyy.room.adapter.SoundEffectAdapter;
import com.qpyy.room.bean.RoomExtraModel;
import com.qpyy.room.bean.RoomSceneItem;
import com.qpyy.room.bean.UpdateRoomName;
import com.qpyy.room.contacts.RoomInfoContacts;
import com.qpyy.room.event.RoomInfoUpdateEvent;
import com.qpyy.room.presenter.RoomInfoPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称 qipao-android
 * 包名：com.qpyy.room.activity
 * 创建人 黄强
 * 创建时间 2020/7/30 19:36
 * 描述 describe
 */
@Route(path = ARouteConstants.ROOM_INFO)
public class RoomInfoActivity extends BaseMvpActivity<RoomInfoPresenter> implements RoomInfoContacts.View {


    private final static String TAG = "RoomInfoActivity";
    @BindView(R2.id.iv_room_info_head_bg)
    ImageView ivRoomInfoHeadBg;
    @BindView(R2.id.iv_bg)
    ImageView ivBg;
    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.riv_room_info_head_pic)
    RoundedImageView rivRoomInfoHeadPic;
    @BindView(R2.id.tv_room_info_name)
    TextView tvRoomInfoName;
    @BindView(R2.id.tv_room_info_id)
    TextView tvRoomInfoId;
    @BindView(R2.id.tv_room_info_pop)
    TextView tvRoomInfoPop;
    @BindView(R2.id.tv_room_info_admin_txt)
    TextView tvRoomInfoAdminTxt;
    @BindView(R2.id.iv_room_info_update_admin)
    ImageView ivRoomInfoUpdateAdmin;
    @BindView(R2.id.rv_admin)
    RecyclerView rvAdmin;
    @BindView(R2.id.tv_room_info_blacklist_txt)
    TextView tvRoomInfoBlacklistTxt;
    @BindView(R2.id.iv_room_info_update_blacklist)
    ImageView ivRoomInfoUpdateBlacklist;
    @BindView(R2.id.rv_blacklist)
    RecyclerView rvBlacklist;
    @BindView(R2.id.tv_room_info_cover_txt)
    TextView tvRoomInfoCoverTxt;
    @BindView(R2.id.iv_room_info_cover_head_pic)
    RoundedImageView ivRoomInfoCoverHeadPic;
    @BindView(R2.id.tv_room_info_update_cover)
    TextView tvRoomInfoUpdateCover;
    @BindView(R2.id.tv_room_info_setting_txt)
    TextView tvRoomInfoSettingTxt;
    @BindView(R2.id.rb_room_info_privacy_setting_rb1)
    RadioButton rbRoomInfoPrivacySettingRb1;
    @BindView(R2.id.rb_room_info_privacy_setting_rb2)
    RadioButton rbRoomInfoPrivacySettingRb2;
    @BindView(R2.id.rg_room_info_privacy_setting_rg)
    RadioGroup rgRoomInfoPrivacySettingRg;
    @BindView(R2.id.tv_room_info_password_txt)
    TextView tvRoomInfoPasswordTxt;
    @BindView(R2.id.et_room_info_set_pw)
    EditText etRoomInfoSetPw;
    @BindView(R2.id.tv_room_info_sound_txt)
    TextView tvRoomInfoSoundTxt;
    @BindView(R2.id.room_play_void_txt)
    TextView roomPlayVoidTxt;
    @BindView(R2.id.et_room_info_ed_play_void_con)
    EditText etRoomInfoEdPlayVoidCon;
    @BindView(R2.id.tv_room_play_font_num)
    TextView tvRoomPlayFontNum;
    @BindView(R2.id.tv_room_info_play_void_ed_max)
    TextView tvRoomInfoPlayVoidEdMax;
    @BindView(R2.id.tv_room_info_welcome_txt)
    TextView tvRoomInfoWelcomeTxt;
    @BindView(R2.id.et_room_info_ed_welcome_con)
    EditText etRoomInfoEdWelcomeCon;
    @BindView(R2.id.tv_room_welcome_font_num)
    TextView tvRoomWelcomeFontNum;
    @BindView(R2.id.tv_room_info_play_welcome_ed_max)
    TextView tvRoomInfoPlayWelcomeEdMax;
    @BindView(R2.id.bt_room_info_set_submit)
    Button btRoomInfoSetSubmit;
    @BindView(R2.id.rv_sound_effect)
    RecyclerView mRvSoundEffect;

    @Autowired
    public int sceneType;//房间场景
    @Autowired
    public String roomId;//房间ID
    @Autowired
    public String roomPassword;//房间密码
    @Autowired
    public boolean isOwner;//是房主
    private String mRoomName;//房间名称
    private RoomAdminOrBlacklistAdapter adminRoomInfoAdapter;//管理员适配器
    private RoomAdminOrBlacklistAdapter blackRoomInfoAdapter;//黑名单适配器
    private String coverImageUrl = "";//房间背景地址
    private SoundEffectAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(RoomInfoUpdateEvent event) {
        MvpPre.getRoomExtra(roomId, roomPassword);//通过房间ID和密码获取房间信息
    }

    @Override
    protected RoomInfoPresenter bindPresenter() {
        return new RoomInfoPresenter(this, this);
    }

    @Override
    protected void initData() {
        MvpPre.getRoomExtra(roomId, roomPassword);//通过房间ID和密码获取房间信息
        MvpPre.soundEffectInfo();//音效配置
    }

    @Override
    protected void initView() {
        super.initView();
        tvTitle.setText("房间信息");
        tvTitle.setTextColor(Color.parseColor("#FFFFFF"));
        ivBack.setImageDrawable(getResources().getDrawable(R.mipmap.icon_back_ff));
        ivBg.setVisibility(View.GONE);//隐藏title背景 显示房间背景
        if (roomPassword != null && roomPassword.length() == 4) {
            tvRoomInfoPasswordTxt.setText("修改房间密码");
        }
        rvAdmin.setLayoutManager(new GridLayoutManager(this, 5));
        rvBlacklist.setLayoutManager(new GridLayoutManager(this, 5));
        rvAdmin.setAdapter(adminRoomInfoAdapter = new RoomAdminOrBlacklistAdapter());
        rvBlacklist.setAdapter(blackRoomInfoAdapter = new RoomAdminOrBlacklistAdapter());
        mRvSoundEffect.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new SoundEffectAdapter(sceneType);
        mRvSoundEffect.setAdapter(mAdapter);
        initListener();//监听
    }

    private RoomSceneItem roomSceneItem;

    /**
     * 控件监听
     */
    private void initListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RoomSceneItem item = mAdapter.getItem(position);
                mAdapter.setSceneType(item.getId());
                roomSceneItem = item;
            }
        });
        //单选按钮
        rgRoomInfoPrivacySettingRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rbRoomInfoPrivacySettingRb1.getId()) {
                    //公开
                    rbRoomInfoPrivacySettingRb1.setTextColor(getResources().getColor(R.color.color_FF6765FF));
                    rbRoomInfoPrivacySettingRb2.setTextColor(getResources().getColor(R.color.color_FF9C9C9C));
                    etRoomInfoSetPw.setVisibility(View.GONE);
                } else {
                    //保密
                    rbRoomInfoPrivacySettingRb1.setTextColor(getResources().getColor(R.color.color_FF9C9C9C));
                    rbRoomInfoPrivacySettingRb2.setTextColor(getResources().getColor(R.color.color_FF6765FF));
                    etRoomInfoSetPw.setVisibility(View.VISIBLE);
                }
            }
        });
        /**
         * 欢迎语内容监听
         */
        etRoomInfoEdWelcomeCon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etRoomInfoEdWelcomeCon.getText().toString().length() > 200) { //判断EditText中输入的字符数是不是已经大于6
                    etRoomInfoEdWelcomeCon.setText(s.toString().substring(0, 200)); //设置EditText只显示前面6位字符
                    etRoomInfoEdWelcomeCon.setSelection(200);//让光标移至末端
                }
                tvRoomWelcomeFontNum.setText(String.valueOf(etRoomInfoEdWelcomeCon.getText().toString().length()));//更新字数
            }
        });

        /**
         * 欢迎语点击监听
         */
        etRoomInfoEdWelcomeCon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.et_room_info_ed_welcome_con) {
                    // 解决scrollView中嵌套EditText导致不能上下滑动的问题
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });

        /**
         * 玩法内容监听
         */
        etRoomInfoEdPlayVoidCon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etRoomInfoEdPlayVoidCon.getText().toString().length() > 200) { //判断EditText中输入的字符数是不是已经大于6
                    etRoomInfoEdPlayVoidCon.setText(s.toString().substring(0, 200)); //设置EditText只显示前面6位字符
                    etRoomInfoEdPlayVoidCon.setSelection(200);//让光标移至末端
                }
                tvRoomPlayFontNum.setText(String.valueOf(etRoomInfoEdPlayVoidCon.getText().toString().length()));//更新字数
            }
        });

        /**
         * 玩法点击监听
         */
        etRoomInfoEdPlayVoidCon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.et_room_info_ed_play_void_con) {
                    // 解决scrollView中嵌套EditText导致不能上下滑动的问题
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });

        /**
         * 黑名单删除点击
         */
        blackRoomInfoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RoomExtraModel.ManagerListBean item = blackRoomInfoAdapter.getItem(position);
                MvpPre.deleteBlacklist(roomId, item.getUser_id(), position);

            }
        });

        /**
         * 管理员删除点击
         */
        adminRoomInfoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (isOwner) {
                    RoomExtraModel.ManagerListBean item = adminRoomInfoAdapter.getItem(position);
                    MvpPre.deleteAdmin(roomId, item.getUser_id(), position);
                } else {
                    ToastUtils.show("只有房主可以修改管理员");
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.room_activity_room_setting;
    }


    @OnClick({R2.id.iv_back, R2.id.iv_room_info_update_admin, R2.id.iv_room_info_update_blacklist,
            R2.id.tv_room_info_update_cover, R2.id.bt_room_info_set_submit, R2.id.tv_room_info_name})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {//退出
            finish();
        } else if (id == R.id.iv_room_info_update_admin) {//添加管理员
            ARouter.getInstance().build(ARouteConstants.ROOM_MANAGE).withInt("addType", 0).withString("roomId", roomId).navigation();
        } else if (id == R.id.iv_room_info_update_blacklist) {//添加黑名单
            ARouter.getInstance().build(ARouteConstants.ROOM_MANAGE).withInt("addType", 1).withString("roomId", roomId).navigation();
        } else if (id == R.id.tv_room_info_update_cover) {//更换房间背景
            startChoosePhoto(PictureMimeType.ofImage(), PictureConfig.CHOOSE_REQUEST);
        } else if (id == R.id.bt_room_info_set_submit) {//提交
            String roomName = mRoomName;//房间名称
            String welcomeCon = etRoomInfoEdWelcomeCon.getText().toString();
            String playCon = etRoomInfoEdPlayVoidCon.getText().toString();
//                if (TextUtils.isEmpty(roomName)) {
//                    ToastUtils.showShort("请输入房间名称");
//                    return;
//                }
            String isPassword = "0";
            if (rbRoomInfoPrivacySettingRb1.isChecked()) {
                //公开
                isPassword = "0";
            } else {
                //保密
                isPassword = "1";
            }
            String password = etRoomInfoSetPw.getText().toString();
            if ("1".equals(isPassword)) {
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.show("请输入房间密码");
                    return;
                }
                if (password.length() > 0 && password.length() < 4) {
                    ToastUtils.show("请输入完整的密码");
                    return;
                }
            }
            LogUtils.e(TAG, "我的房间名称：" + tvRoomInfoName.getText().toString()
                    + "\n我的房间ID:" + roomId
                    + "\n管理员数量:" + adminRoomInfoAdapter.getItemCount()
                    + "\n黑名单数量:" + blackRoomInfoAdapter.getItemCount()
                    + "\n我的房间封面本地地址:" + coverImageUrl
//                        + "\n房间密码:" + (roomPassword.length() > 0 ? roomPassword : "没有密码")
                    + "\n房间玩法:" + etRoomInfoEdPlayVoidCon.getText().toString()
                    + "\n房间欢迎语:" + etRoomInfoEdWelcomeCon.getText().toString());
            if (roomSceneItem != null) {
                MvpPre.updateSoundEffect(roomId, roomSceneItem, coverImageUrl, password, playCon, roomName, welcomeCon, isPassword);
            } else {
                MvpPre.editRoom(coverImageUrl, "", password,
                        playCon, roomId, roomName, "", "", welcomeCon, "", isPassword);//提交修改
            }
        } else if (view.getId() == R.id.tv_room_info_name) {//点击了房间名称
            LogUtils.d(TAG, "房间名称：" + mRoomName + "进入修改");
            ARouter.getInstance().build(ARouteConstants.EDIT_ROOM_NAME).withString("roomName", mRoomName).navigation();
        }
    }

    /**
     * 选择图片
     *
     * @param mimeType
     * @param requestCode
     */
    private void startChoosePhoto(int mimeType, int requestCode) {
        PictureSelector.create(this)
                .openGallery(mimeType)
                .selectionMode(PictureConfig.SINGLE)
                .maxSelectNum(1)//最大选择数量
                .minSelectNum(1)//最小选择数量
                .previewImage(false)//是否预览图片
                .isCamera(true)//是否允许使用相机
                .sizeMultiplier(0.5f)//大小倍数
                .setOutputCameraPath(Constants.FILE_PATH)//设置拍照照片存储路径
                .compress(false)//是否开启压缩
                .withAspectRatio(1, 1)
                .cropCompressQuality(Constants.CROP_COMPRESS_SIZE)// 裁剪压缩质量 默认90 int
                .minimumCompressSize(Constants.COMPRESS_INGNORE)// 小于100kb的图片不压缩
                .compressSavePath(ImageUtils.getImagePath())//压缩图保存路径
                .isDragFrame(true)//是否允许拖动窗体
                .forResult(requestCode);//结果回调onActivityResult code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                if (localMedia != null && localMedia.size() != 0) {
                    LocalMedia imgMedia = localMedia.get(0);
                    String url;
                    if (imgMedia.isCompressed()) {
                        url = imgMedia.getCompressPath();
                    } else {
                        url = imgMedia.getPath();
                    }
                    MvpPre.uploadImage(new File(url), 0);
                }
                break;
        }
    }

    /**
     * 获取数据初始化房间信息
     *
     * @param roomExtraModel
     */
    @Override
    public void setRoomExtraSuccess(RoomExtraModel roomExtraModel) {
        //信息
        mRoomName = roomExtraModel.getRoom_name();
        tvRoomInfoName.setText(mRoomName);//房间名称
        tvRoomInfoId.setText(roomExtraModel.getRoom_code());//房间ID
        tvRoomInfoPop.setText(roomExtraModel.getPopularity());//房间热度
        coverImageUrl = roomExtraModel.getCover_picture();
        ImageUtils.loadHeadCC(roomExtraModel.getHead_picture(), rivRoomInfoHeadPic);//头像
        ImageUtils.loadHeadCC(coverImageUrl, ivRoomInfoCoverHeadPic);//封面背景（小）
        ImageUtils.loadHeadCC(coverImageUrl, ivRoomInfoHeadBg);//封面背景
        adminRoomInfoAdapter.setNewData(roomExtraModel.getManager_list());//管理员
        blackRoomInfoAdapter.setNewData(roomExtraModel.getBlack_list());//黑名单
        etRoomInfoEdWelcomeCon.setText(roomExtraModel.getGreeting());//初始化欢迎语
        etRoomInfoEdPlayVoidCon.setText(roomExtraModel.getPlaying());//初始化玩法介绍
        rgRoomInfoPrivacySettingRg.check(roomExtraModel.getIs_password() == 1 ?
                R.id.rb_room_info_privacy_setting_rb2 : R.id.rb_room_info_privacy_setting_rb1);//公开私密
        etRoomInfoSetPw.setVisibility(roomExtraModel.getIs_password() == 1 ? View.VISIBLE : View.GONE);
        //房间对外类型
        //房间音效
    }

    /**
     * 删除
     *
     * @param type
     * @param position
     */
    @Override
    public void delete(int type, int position) {
        if (type == 0) {
            adminRoomInfoAdapter.remove(position);
        } else {
            blackRoomInfoAdapter.remove(position);
        }
    }

    /**
     * 踢出房间
     *
     * @param roomKickOutModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeMessages(RoomKickOutModel roomKickOutModel) {
        LogUtils.e("userId",SpUtils.getUserId());
        if (roomKickOutModel.getUser_id().equals(SpUtils.getUserId())) {
            finish();
        }
    }

    /**
     * 删除管理员成功
     *
     * @param userId
     */
    @Override
    public void deleteAdminSuccess(String userId) {
        ToastUtils.show("管理员删除成功");
    }

    /**
     * 删除没名单成功
     *
     * @param userId
     */
    @Override
    public void deleteBlacklistSuccess(String userId) {
        ToastUtils.show("黑名单删除成功");
    }

    /**
     * 提交成功
     */
    @Override
    public void editRoomSuccess() {
        ToastUtils.show("房间信息修改完成");
    }

    @Override
    public void uploadSuccess(String url) {
        ImageUtils.loadCenterCrop(url, ivRoomInfoHeadBg);
        ImageUtils.loadCenterCrop(url, ivRoomInfoCoverHeadPic);
        coverImageUrl = url;
    }

    @Override
    public void soundEffectInfo(List<RoomSceneItem> items) {
        mAdapter.setNewData(items);
    }

    @Override
    public void updateSoundEffectSuccess(RoomSceneItem item) {

    }

    /**
     * 设置房间秘密
     *
     * @param password
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void roomSettingPassword(String password) {
        roomPassword = password;
    }

    /**
     * 修改完房间后刷新
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyRoomName(UpdateRoomName updateRoomName) {
        mRoomName = updateRoomName.getRoomName();
        tvRoomInfoName.setText(mRoomName);//更新房间名
        HashMap<String, String> map = new HashMap<>();
        map.put("room_name", mRoomName);
        MvpPre.roomUpdate(map);//提交修改
    }
}
