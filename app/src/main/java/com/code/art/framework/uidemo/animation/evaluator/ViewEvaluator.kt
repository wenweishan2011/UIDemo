package com.code.art.framework.uidemo.animation.evaluator

import android.animation.TypeEvaluator
import com.code.art.framework.uidemo.animation.Point

/**
 * @author wenws
 * @date 2019/6/28.
 * description：
 */
class ViewEvaluator : TypeEvaluator<Point> {
    override fun evaluate(fraction: Float, startValue: Point?, endValue: Point?): Point {
        var start = startValue!!.radius
        var end = endValue!!.radius
        return Point((start + (end - start) * fraction).toInt())
    }
}