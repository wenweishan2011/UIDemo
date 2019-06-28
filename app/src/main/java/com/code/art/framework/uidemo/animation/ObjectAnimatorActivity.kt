package com.code.art.framework.uidemo.animation

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.Toast
import com.code.art.framework.uidemo.R
import kotlinx.android.synthetic.main.activity_object_animator.*
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author wenws
 * @date 2019/6/28.
 * description：
 */
class ObjectAnimatorActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val RADIUS = 600
        const val MENU_COUNT = 5
        const val TAG = "ObjectAnimatorActivity"
    }

    private var mViews: Array<ImageView> = emptyArray()

    private var mIsMenuOpened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_animator)

        mViews = arrayOf(menu1, menu2, menu3, menu4, menu5)

        menu.setOnClickListener(this)

        for (view in mViews) {
            view.setOnClickListener(this)
        }

        pointView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.pointView -> pointView()
            R.id.menu -> menuOperate(mIsMenuOpened)
            R.id.menu1 -> {
                Toast.makeText(this, "menu1 onclick", Toast.LENGTH_SHORT).show()
                menuOperate(false)
            }
        }
    }

    private fun getTranslationX(index: Int): Float {

        val degrees = Math.toRadians(90.0) / (MENU_COUNT - 1) * index

        return -(RADIUS * sin(degrees).toFloat())
    }

    private fun getTranslationY(index: Int): Float {

        val degrees = Math.toRadians(90.0) / (MENU_COUNT - 1) * index

        return -(RADIUS * cos(degrees).toFloat())
    }

    private fun getAnimatorList(): ArrayList<ObjectAnimator> {
        val animatorList = ArrayList<ObjectAnimator>()

        for (i in 0..4) {
            var startX = 0F
            var startY = 0F
            var endX = 0F
            var endY = 0F
            if (mIsMenuOpened) {
                startX = getTranslationX(i)
                startY = getTranslationY(i)


                val scaleXAnimator = ObjectAnimator.ofFloat(mViews[i], "scaleX", 1f, 0f)
                val scaleYAnimator = ObjectAnimator.ofFloat(mViews[i], "scaleY", 1f, 0f)

                animatorList.add(scaleXAnimator)
                animatorList.add(scaleYAnimator)

                val alphaAnimator = ObjectAnimator.ofFloat(mViews[i], "alpha", 1f, 0f)
                animatorList.add(alphaAnimator)
            } else {
                endX = getTranslationX(i)
                endY = getTranslationY(i)

                val scaleXAnimator = ObjectAnimator.ofFloat(mViews[i], "scaleX", 0f, 1f)
                val scaleYAnimator = ObjectAnimator.ofFloat(mViews[i], "scaleY", 0f, 1f)

                animatorList.add(scaleXAnimator)
                animatorList.add(scaleYAnimator)

                val alphaAnimator = ObjectAnimator.ofFloat(mViews[i], "alpha", 0f, 1f)
                animatorList.add(alphaAnimator)
            }
            Log.v(TAG, "startX:" + startX + "endX" + endX)
            Log.v(TAG, "startY:" + endY + "endY" + endY)
            val translationX = ObjectAnimator.ofFloat(mViews[i], "translationX", startX, endX)
            val translationY = ObjectAnimator.ofFloat(mViews[i], "translationY", startY, endY)

            var delay = i * 50
            translationX.startDelay = delay.toLong()
            translationY.startDelay = delay.toLong()

            animatorList.add(translationX)
            animatorList.add(translationY)
        }
        return animatorList
    }

    private fun menuOperate(open: Boolean) {
        Log.d(TAG, open.toString())
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(getAnimatorList() as Collection<Animator>?)
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
                for (view in mViews) {
                    view.visibility = View.VISIBLE
                }
            }

            override fun onAnimationEnd(animation: Animator?) {
                mIsMenuOpened = !mIsMenuOpened
                if (!mIsMenuOpened) {
                    for (view in mViews) {
                        view.visibility = View.GONE
                    }
                }
            }
        })
        animatorSet.interpolator = AccelerateInterpolator()
        animatorSet.setDuration(500).start()
    }

    private fun pointView() {
        val animator = ObjectAnimator.ofInt(pointView, "Radius", 0, 300, 100)
        animator.duration = 3000
        animator.start()
    }

}