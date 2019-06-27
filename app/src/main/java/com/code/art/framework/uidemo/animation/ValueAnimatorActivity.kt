package com.code.art.framework.uidemo.animation

import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import com.code.art.framework.uidemo.R
import com.code.art.framework.uidemo.animation.evaluator.CharEvaluator
import com.code.art.framework.uidemo.animation.evaluator.MyEvaluator
import com.code.art.framework.uidemo.animation.evaluator.ViewEvaluator
import com.code.art.framework.uidemo.animation.interpolator.MyInterpolator
import kotlinx.android.synthetic.main.activity_value_animator.*

/**
 * @author wenws
 * @date 2019/6/27.
 * description：
 */
class ValueAnimatorActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = "ValueAnimatorActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_animator)
        reverse.setOnClickListener(this)
        charEval.setOnClickListener(this)
        viewEval.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.reverse -> widgetMove()
            R.id.charEval -> charEval()
            R.id.viewEval -> viewEval()
            else -> {

            }
        }
    }

    /**
     * 对象变化
     */
    private fun viewEval(){
        val animator = ValueAnimator.ofObject(ViewEvaluator(), Point(10), Point(20))
        animator.duration = 3000
        animator.addUpdateListener {
            val animatedValue = it.animatedValue as Point
            viewEval.text = animatedValue.radius.toString()
        }
        animator.interpolator = BounceInterpolator()
        animator.start()

    }

    /**
     * 控件显示，从“A”-“Z”变化
     */
    private fun charEval() {
        val animator = ValueAnimator.ofObject(CharEvaluator(), 'A', 'Z')
        animator.interpolator = AccelerateInterpolator()
        animator.duration = 3000

        animator.addUpdateListener {
            val value = it.animatedValue as Char
            charEval.text = value.toString()
        }

        animator.start()

    }

    /**
     * 移动控件
     */
    private fun widgetMove() {
        //设置值范围
        var valueAnimator = ValueAnimator.ofInt(0, 600)
        valueAnimator.interpolator = MyInterpolator()
        valueAnimator.addUpdateListener {
            //获取变化的值
            var value: Int = it.animatedValue as Int
            Log.v("kotlin-log", value.toString())
            reverse.layout(reverse.left, value, reverse.right, reverse.height + value)
        }

        valueAnimator.setEvaluator(MyEvaluator())
        valueAnimator.duration = 1000
        valueAnimator.start()

    }
}