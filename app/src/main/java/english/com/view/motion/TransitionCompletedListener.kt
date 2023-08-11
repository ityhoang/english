package english.com.view.motion

import androidx.constraintlayout.motion.widget.MotionLayout

/**
 * thresholdComplete range 0..1f
 */
abstract class TransitionCompletedListener(
    private val completeId: Int,
    private val thresholdComplete: Float = 1f
) :
    MotionLayout.TransitionListener {

    private var endId: Int? = null

    override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {

    }

    override fun onTransitionChange(
        motionLayout: MotionLayout?,
        startId: Int,
        endId: Int,
        progress: Float
    ) {
        this.endId = endId
        onComplete((motionLayout?.progress ?: 0f) >= thresholdComplete && endId == completeId)
    }

    override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
        onComplete((motionLayout?.progress ?: 0f) >= thresholdComplete && endId == completeId)
    }

    override fun onTransitionTrigger(
        motionLayout: MotionLayout?,
        triggerId: Int,
        positive: Boolean,
        progress: Float
    ) {
    }

    abstract fun onComplete(isEnd: Boolean)
}