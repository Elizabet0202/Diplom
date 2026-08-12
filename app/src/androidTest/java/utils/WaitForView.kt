package ru.iteco.fmhandroid.utils

import android.os.SystemClock
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.Matcher

fun waitForView(
    viewMatcher: Matcher<View>,
    timeout: Long = 30_000L
) {
    onView(isRoot()).perform(waitForMatcher(viewMatcher, timeout))
}
fun waitForViewToDisappear(
    viewMatcher: Matcher<View>,
    timeout: Long = 30_000L
) {
    onView(isRoot()).perform(
        waitForMatcherToDisappear(viewMatcher, timeout)
    )
}

private fun waitForMatcherToDisappear(
    viewMatcher: Matcher<View>,
    timeout: Long
): ViewAction {
    return object : ViewAction {

        override fun getConstraints(): Matcher<View> = isRoot()

        override fun getDescription(): String =
            "Ожидание исчезновения элемента в течение $timeout мс"

        override fun perform(
            uiController: UiController,
            view: View
        ) {
            val endTime = SystemClock.uptimeMillis() + timeout

            do {
                val exists = view
                    .walkThroughViews()
                    .any { viewMatcher.matches(it) }

                if (!exists) {
                    return
                }

                uiController.loopMainThreadForAtLeast(100)

            } while (SystemClock.uptimeMillis() < endTime)

            throw AssertionError(
                "Элемент не исчез за $timeout мс: $viewMatcher"
            )
        }
    }
}
private fun waitForMatcher(
    viewMatcher: Matcher<View>,
    timeout: Long
): ViewAction {
    return object : ViewAction {

        override fun getConstraints(): Matcher<View> = isRoot()

        override fun getDescription(): String =
            "Ожидание появления элемента в течение $timeout мс"

        override fun perform(uiController: UiController, view: View) {
            val endTime = SystemClock.uptimeMillis() + timeout

            do {
                for (child in view.walkThroughViews()) {
                    if (viewMatcher.matches(child)) {
                        return
                    }
                }

                uiController.loopMainThreadForAtLeast(100)
            } while (SystemClock.uptimeMillis() < endTime)

            throw AssertionError(
                "Элемент не появился за $timeout мс: $viewMatcher"
            )
        }
    }
}

private fun View.walkThroughViews(): Sequence<View> = sequence {
    yield(this@walkThroughViews)

    if (this@walkThroughViews is android.view.ViewGroup) {
        for (index in 0 until childCount) {
            yieldAll(getChildAt(index).walkThroughViews())
        }
    }
}