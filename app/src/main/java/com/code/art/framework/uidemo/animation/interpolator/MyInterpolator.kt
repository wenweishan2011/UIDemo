package com.code.art.framework.uidemo.animation.interpolator

import android.animation.TimeInterpolator

/**
 * @author wenws
 * @date 2019/6/27.
 * description：
 */
class MyInterpolator : TimeInterpolator {
    override fun getInterpolation(input: Float): Float {
        return 1 - input
    }

}