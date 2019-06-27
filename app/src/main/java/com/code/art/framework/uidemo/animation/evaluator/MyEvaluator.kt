package com.code.art.framework.uidemo.animation.evaluator

import android.animation.TypeEvaluator

/**
 * @author wenws
 * @date 2019/6/27.
 * description：
 */
class MyEvaluator : TypeEvaluator<Int> {

    override fun evaluate(fraction: Float, startValue: Int?, endValue: Int?): Int {
        //返回 计算后的当前值
        return (startValue!! + (endValue!! - startValue) * fraction).toInt()
    }
}