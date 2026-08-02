package ru.iteco.fmhandroid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import ru.iteco.fmhandroid.R
import ru.iteco.fmhandroid.utils.waitForView

class OurMissionPage {

    private val ourMissionButton =
        withId(R.id.our_mission_image_button)

    private val ourMissionTitle =
        withId(R.id.our_mission_title_text_view)

    private val quotesList =
        withId(R.id.our_mission_item_list_recycler_view)

    fun openOurMission() {
        waitForView(ourMissionButton)

        onView(ourMissionButton)
            .check(matches(isDisplayed()))
            .perform(click())
    }

    fun checkOurMissionScreen() {
        waitForView(ourMissionTitle)

        onView(ourMissionTitle)
            .check(matches(isDisplayed()))

        onView(quotesList)
            .check(matches(isDisplayed()))
    }
}