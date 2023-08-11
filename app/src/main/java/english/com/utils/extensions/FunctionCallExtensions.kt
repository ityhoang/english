package english.com.utils.extensions

import kotlinx.coroutines.delay

internal const val DEFAULT_MINIMUM_TIME_TO_CALL_API = 300L

/**
 * Calls a specified function [block] and return its result. And, make sure function call time is
 * greater than [minimumTimeInMillis]. A delay period will be added if function call time is less
 * than [minimumTimeInMillis].
 *
 * This function should be used to avoid screen flickering when loading time is too short
 * (due to no network connection).
 */
internal suspend fun <R> runWithMinimumTime(minimumTimeInMillis: Long, block: suspend () -> R): R {
    val startedAt = System.currentTimeMillis()
    val result = block()
    val runningBlockTime = System.currentTimeMillis() - startedAt

    if (runningBlockTime < minimumTimeInMillis) {
        delay(minimumTimeInMillis - runningBlockTime)
    }

    return result
}
