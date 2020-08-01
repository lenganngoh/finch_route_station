package gohleng.apps.finch_route_station.main.util

import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.cardview.widget.CardView

class ViewAnimator {

    val millisNormal = 200L

    fun animateViewTransY(view: View, startY: Float, y: Float, delay: Long, listener: AnimatorListenerAdapter?) {
        val valueAnimator = ValueAnimator.ofFloat(startY, -y)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            view.translationY = value
        }

        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
        valueAnimator.duration = millisNormal
        if (listener != null) valueAnimator.addListener(listener)
        valueAnimator.startDelay = delay
        valueAnimator.start()
    }

    fun animateAlpha(view: View, startAlpha: Float, targetAlpha: Float, delay: Long) {
        view.alpha = startAlpha
        view.visibility = View.VISIBLE
        val viewAnimator = view.animate().alpha(targetAlpha).setDuration(200)
        viewAnimator.startDelay = delay
        viewAnimator.start()
    }

    fun resizeOverlayView(view: View, newHeight: Int, delay: Long) {
        val viewParam = view.layoutParams
        val valueAnimator = ValueAnimator.ofInt(viewParam.height, newHeight)
        valueAnimator.duration = millisNormal
        valueAnimator.addUpdateListener {
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams = view.layoutParams
            layoutParams.height = animatedValue
            view.layoutParams = layoutParams
        }
        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
        valueAnimator.startDelay = delay
        valueAnimator.start()
    }

    fun resizeOverlayViewImage(image: View, newHeight: Int, delay: Long) {
        val imageParam = image.layoutParams
        val valueAnimator = ValueAnimator.ofInt(imageParam.height, newHeight)
        valueAnimator.duration = millisNormal
        valueAnimator.addUpdateListener {
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams = image.layoutParams
            layoutParams.height = animatedValue
            image.layoutParams = layoutParams
        }
        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
        valueAnimator.startDelay = delay
        valueAnimator.start()
    }

    fun resizeOverlayMargin(view: View, newHeight: Int, delay: Long) {
        val viewParam = view.layoutParams as ViewGroup.MarginLayoutParams
        val valueAnimator = ValueAnimator.ofInt(viewParam.topMargin, newHeight)
        valueAnimator.duration = millisNormal
        valueAnimator.addUpdateListener {
            val animatedValue = valueAnimator.animatedValue as Int
            val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.topMargin = animatedValue
            layoutParams.bottomMargin = animatedValue
            layoutParams.rightMargin = animatedValue
            layoutParams.leftMargin = animatedValue
            view.layoutParams = layoutParams
        }
        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
        valueAnimator.startDelay = delay
        valueAnimator.start()
    }

    fun resizeCardRadius(view: CardView, newRadius: Int, delay: Long) {
        val valueAnimator = ValueAnimator.ofInt(view.radius.toInt(), newRadius)
        valueAnimator.duration = millisNormal
        valueAnimator.addUpdateListener {
            val animatedValue = valueAnimator.animatedValue as Int
            view.radius = animatedValue.toFloat()
        }
        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
        valueAnimator.startDelay = delay
        valueAnimator.start()
    }
}