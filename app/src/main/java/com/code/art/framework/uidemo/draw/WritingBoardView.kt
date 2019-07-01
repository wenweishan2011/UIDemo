package com.code.art.framework.uidemo.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @author wenws
 * @date 2019/6/30.
 * description：
 */
class WritingBoardView : View {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var mPaint = Paint()
    private var mPath = Path()
    private var preX = 0F
    private var preY = 0F

    init {
        mPaint.color = Color.WHITE
        mPaint.isAntiAlias = true
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 3F
    }

    override fun onDraw(canvas: Canvas?) {
        mPaint.color = Color.BLACK
        mPaint.textSize = 25F

        canvas?.drawText("手写板", 0F,  -mPaint.fontMetrics.ascent, mPaint)
        mPaint.color = Color.WHITE
        canvas?.drawPath(mPath, mPaint)
        super.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                mPath.moveTo(event.x,event.y)
                preX = event.x
                preY = event.y
                return true
            }
            MotionEvent.ACTION_MOVE ->{
                val endX = (event.x + preX)/2
                val endY = (event.y + preY)/2
                mPath.quadTo(preX, preY, endX, endY)
                preX = event.x
                preY = event.y
                invalidate()
            }
            MotionEvent.ACTION_UP ->{

            }
        }
        return super.onTouchEvent(event)
    }

}
