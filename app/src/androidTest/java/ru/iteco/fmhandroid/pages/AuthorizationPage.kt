package ru.iteco.fmhandroid.pages

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers.allOf
import ru.iteco.fmhandroid.R
import ru.iteco.fmhandroid.utils.waitForView

class AuthorizationPage {

    private val loginField = allOf(
        isAssignableFrom(EditText::class.java),
        isDescendantOfA(withId(R.id.login_text_input_layout))
    )

    private val passwordField = allOf(
        isAssignableFrom(EditText::class.java),
        isDescendantOfA(withId(R.id.password_text_input_layout))
    )

    private val enterButton = withId(R.id.enter_button)

    private val mainScreen = withId(R.id.main_swipe_refresh)

    fun checkAuthorizationScreen() {
        waitForView(enterButton)

        onView(enterButton)
            .check(matches(isDisplayed()))
    }

    fun enterLogin(login: String) {
        onView(loginField)
            .perform(
                replaceText(login),
                closeSoftKeyboard()
            )
    }

    fun enterPassword(password: String) {
        onView(passwordField)
            .perform(
                replaceText(password),
                closeSoftKeyboard()
            )
    }

    fun clickEnterButton() {
        onView(enterButton)
            .perform(click())
    }

    fun checkMainScreen() {
        waitForView(mainScreen)

        onView(mainScreen)
            .check(matches(isDisplayed()))
    }

    fun checkAuthorizationScreenAfterFailedLogin() {
        waitForView(enterButton)

        onView(enterButton)
            .check(matches(isDisplayed()))

        onView(loginField)
            .check(matches(isDisplayed()))

        onView(passwordField)
            .check(matches(isDisplayed()))
    }
}