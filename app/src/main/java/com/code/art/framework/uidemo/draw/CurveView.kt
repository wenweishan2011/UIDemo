package com.code.art.framework.uidemo.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * @author wenws
 * @date 2019/6/30.
 * description：
 */
class CurveView : View {

    private var mPaint  = Paint()
    private var mPath = Path()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        mPaint.color = Color.RED
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = 2F
        mPaint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        mPath.moveTo(100F, 300F)
        mPath.quadTo(150F, 250F, 200F, 300F)
        mPath.quadTo(250F, 350F, 300F, 300F)
        canvas?.drawPath(mPath, mPaint)
        super.onDraw(canvas)
    }

}