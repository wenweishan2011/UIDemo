package com.code.art.framework.uidemo.animation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @author wenws
 * @date 2019/6/28.
 * description：
 */
class PointView : View{
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint = Paint()
    private val point = Point(100)

    init {
        paint.isAntiAlias = true
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        canvas!!.drawCircle(300F, 300F, point.radius.toFloat(), paint)
        super.onDraw(canvas)
    }

    fun setRadius(radius : Int){
        point.radius = radius
        invalidate()
    }
}