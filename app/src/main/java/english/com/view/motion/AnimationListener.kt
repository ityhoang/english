package english.com.view.motion

import android.animation.Animator

abstract class AnimationListener : Animator.AnimatorListener {

    override fun onAnimationStart(animation: Animator, isReverse: Boolean) {
    }

    override fun onAnimationStart(p0: Animator) {
    }

    override fun onAnimationEnd(animation: Animator, isReverse: Boolean) {
        super.onAnimationEnd(animation, isReverse)
    }

    override fun onAnimationEnd(p0: Animator) {
        onComplete()
    }

    override fun onAnimationCancel(p0: Animator) {
    }

    override fun onAnimationRepeat(p0: Animator) {
    }

    abstract fun onComplete()
}