package ru.iteco.fmhandroid.utils

import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun first(matcher: Matcher<View>): Matcher<View> {
    return object : TypeSafeMatcher<View>() {

        private var isFirstView = true

        override fun describeTo(description: Description) {
            description.appendText("первый элемент, соответствующий: ")
            matcher.describeTo(description)
        }

        override fun matchesSafely(view: View): Boolean {
            if (matcher.matches(view) && isFirstView) {
                isFirstView = false
                return true
            }

            return false
        }
    }
}