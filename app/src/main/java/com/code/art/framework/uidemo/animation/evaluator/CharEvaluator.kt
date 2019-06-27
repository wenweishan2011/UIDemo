package com.code.art.framework.uidemo.animation.evaluator

import android.animation.TypeEvaluator
import android.util.Log

/**
 * @author wenws
 * @date 2019/6/27.
 * description：
 */
class CharEvaluator : TypeEvaluator<Char>{
    override fun evaluate(fraction: Float, startValue: Char?, endValue: Char?): Char {
        var start = startValue!!.toInt()
        var end = endValue!!.toInt()

        var result = start + (end - start) * fraction
        Log.v("kotlin-log", result.toString())
        return result.toChar()
    }

}