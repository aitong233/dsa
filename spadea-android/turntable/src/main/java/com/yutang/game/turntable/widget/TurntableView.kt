package com.yutang.game.turntable.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.yutang.game.turntable.R
import com.yutang.game.turntable.bean.GamePoolModel
import kotlinx.android.synthetic.main.turntable_layout_turntable.view.*

class TurntableView : ConstraintLayout {

    private val imageViews: MutableList<ImageView>

    constructor(context: Context) : super(context)

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.turntable_layout_turntable, this, true)
        imageViews = arrayListOf(iv_gift_1, iv_gift_2, iv_gift_3, iv_gift_4, iv_gift_5, iv_gift_6, iv_gift_7, iv_gift_8)
    }


    fun setGift(gifts: ArrayList<GamePoolModel>) {
        gifts.sortByDescending {//按降序排序
            it.price
        }
        gifts.forEachIndexed { index, gamePoolModel ->//设置价格最高的8个礼物到转盘上
            if (index >= 8) {
                return@forEachIndexed
            }
            Glide.with(context).load(gamePoolModel.picture).into(imageViews[index])
        }
    }

    fun startRotationAnim() {
        val animation = AnimationUtils.loadAnimation(context, R.anim.turntable_anim_rotate)
        animation.interpolator = LinearInterpolator() //防止停顿
        startAnimation(animation)
    }

    fun stopRotationAnim(){
        clearAnimation()
    }
}