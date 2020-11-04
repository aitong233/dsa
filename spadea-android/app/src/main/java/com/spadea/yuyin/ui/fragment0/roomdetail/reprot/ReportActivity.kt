package com.spadea.yuyin.ui.fragment0.roomdetail.reprot

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.spadea.yuyin.R
import com.spadea.yuyin.base.BaseActivity3
import com.spadea.yuyin.util.Constants
import com.spadea.yuyin.util.ImageLoader
import kotlinx.android.synthetic.main.activity_report.*
import kotlinx.android.synthetic.main.layout_topbar.*
import java.io.File

class ReportActivity : BaseActivity3<ReportContract.Present>(), ReportContract.View {

    override val mPresenter: ReportContract.Present
        get() = ReportPresent().also { it.attachView(this) }
    override val layoutRes: Int
        get() = R.layout.activity_report
    var user_id = ""
    var room_id = ""

    override fun initAll() {
        user_id = intent.getStringExtra("user_id") ?: ""
        room_id = intent.getStringExtra("id") ?: ""
        if (user_id.isNullOrEmpty()) {
            tv_title.text = "举报房间"
        } else {
            tv_title.text = "举报用户"
        }
    }

    override fun setListener() {
        iv_left.setOnClickListener {
            onBackPressed()
        }
        iv_upload.setOnClickListener {
            startChoose()
        }
        btn_submit.setOnClickListener {
            if (user_id.isNullOrEmpty()) {
                mPresenter?.tipOffRoom(room_id, et.text.toString(), image)
            } else {
                mPresenter?.reportUser(user_id, et.text.toString(), image)
            }
        }
    }

    override fun processLogic() {

    }

    override fun getContext(): Context {
        return mContext
    }

    override fun onEmpty() {

    }

    override fun onError() {

    }

    override fun reportUser() {
        finish()
    }

    override fun tipOffRoom() {
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PictureConfig.CHOOSE_REQUEST -> {
                    selectList = PictureSelector.obtainMultipleResult(data)
                    image = selectList?.get(0)?.compressPath ?: ""
                    ImageLoader.loadImage(mContext, iv_upload, image)
                }
            }
        }
    }

    private fun startChoose() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)
                .previewImage(true)
                .isCamera(true)
                .sizeMultiplier(0.5f)
                .setOutputCameraPath(Constants.FILE_PATH)
                .enableCrop(false)
                .compress(true)
                .withAspectRatio(1, 1)
                .cropCompressQuality(Constants.CROP_COMPRESS_SIZE)// 裁剪压缩质量 默认90 int
                .minimumCompressSize(Constants.COMPRESS_INGNORE)// 小于100kb的图片不压缩
                .compressSavePath(getPath())
                .freeStyleCropEnabled(false)
                .circleDimmedLayer(false)
                .showCropFrame(true)
                .rotateEnabled(false) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .isDragFrame(false)
                .forResult(PictureConfig.CHOOSE_REQUEST)//结果回调onActivityResult code
    }

    var selectList: List<LocalMedia>? = null
    var image = ""

    private fun getPath(): String {
        val path = Constants.IMAGE_PATH
        val file = File(path)
        return if (file.mkdirs()) {
            path
        } else path
    }
}
