package ru.iteco.fmhandroid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import ru.iteco.fmhandroid.R
import ru.iteco.fmhandroid.utils.waitForView

class NavigationPage {

    private val mainMenuButton =
        withId(R.id.main_menu_image_button)

    private val mainMenuItem =
        withText("Main")

    private val newsMenuItem =
        withText("News")

    private val aboutMenuItem =
        withText("About")

    private val newsScreen =
        withId(R.id.news_list_swipe_refresh)

    fun openSideMenu() {
        waitForView(mainMenuButton)

        onView(mainMenuButton)
            .check(matches(isDisplayed()))
            .perform(click())
    }

    fun checkSideMenuItems() {
        waitForView(mainMenuItem)

        onView(mainMenuItem)
            .check(matches(isDisplayed()))

        onView(newsMenuItem)
            .check(matches(isDisplayed()))

        onView(aboutMenuItem)
            .check(matches(isDisplayed()))
    }

    fun clickNewsMenuItem() {
        onView(newsMenuItem)
            .check(matches(isDisplayed()))
            .perform(click())
    }

    fun checkNewsScreenIsOpened() {
        waitForView(newsScreen)

        onView(newsScreen)
            .check(matches(isDisplayed()))
    }
}