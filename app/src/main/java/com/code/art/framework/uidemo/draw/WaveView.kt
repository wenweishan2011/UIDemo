package com.code.art.framework.uidemo.draw

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * @author wenws
 * @date 2019/6/30.
 * description：
 */
class WaveView : View{

    private var mItemWaveLength = 800F
    private var mPaint = Paint()
    private var mPath = Path()
    private var mDx = 0F
    private var mDy = 0F

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        mPaint.color = Color.GREEN
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.FILL_AND_STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        mPath.reset()
        //设置起点
        mPath.moveTo(mDx-mItemWaveLength, mDy)

        //绘制屏幕宽度的波
        var cx = -mItemWaveLength
        while (cx < width + mItemWaveLength){

            mPath.rQuadTo(mItemWaveLength/4, -70F, mItemWaveLength/2, 0F)
            mPath.rQuadTo(mItemWaveLength/4, 70F, mItemWaveLength/2, 0F)

            cx += mItemWaveLength
        }

        mPath.lineTo(width.toFloat(), height.toFloat())
        mPath.lineTo(0F, height.toFloat())
        mPath.close()
        canvas?.drawPath(mPath, mPaint)
        super.onDraw(canvas)
    }

    private fun startAnimation(){
        val valueAnimator = ValueAnimator.ofFloat(0F, mItemWaveLength)
        valueAnimator.addUpdateListener {
            mDx = it.animatedValue as Float
            invalidate()
        }
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = 1000
        valueAnimator.start()


        val heightAnimator = ValueAnimator.ofFloat(100F, 500F)
        heightAnimator.addUpdateListener {
            mDy= it.animatedValue as Float
        }
        heightAnimator.interpolator = LinearInterpolator()
        heightAnimator.duration = 5000
        heightAnimator.start()
    }

    override fun onAttachedToWindow() {
        startAnimation()
        super.onAttachedToWindow()
    }

}