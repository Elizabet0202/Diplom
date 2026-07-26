package ru.iteco.fmhandroid.test

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.steps.AuthorizationSteps
import ru.iteco.fmhandroid.steps.NewsSteps
import ru.iteco.fmhandroid.ui.AppActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class NewsTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AppActivity::class.java)

    private val authorizationSteps = AuthorizationSteps()
    private val newsSteps = NewsSteps()

    @Test
    fun openNewsList() {
        authorizationSteps.loginWithValidCredentials()
        authorizationSteps.checkSuccessfulAuthorization()

        newsSteps.openNewsSection()
        newsSteps.checkNewsScreenIsOpened()
    }

    @Test
    fun expandFirstNews() {
        authorizationSteps.loginWithValidCredentials()
        authorizationSteps.checkSuccessfulAuthorization()

        newsSteps.openNewsSection()
        newsSteps.checkNewsScreenIsOpened()

        newsSteps.expandFirstNews()
        newsSteps.checkFirstNewsDescriptionIsDisplayed()
    }

    @Test
    fun createNews() {
        val uniqueNewsTitle =
            "Auto test news ${System.currentTimeMillis()}"

        authorizationSteps.loginWithValidCredentials()
        authorizationSteps.checkSuccessfulAuthorization()

        newsSteps.openNewsSection()
        newsSteps.checkNewsScreenIsOpened()

        newsSteps.openNewsControlPanel()
        newsSteps.openCreateNewsScreen()

        newsSteps.createNews(
            category = "Объявление",
            title = uniqueNewsTitle,
            description = "News created by automated UI test"
        )

        newsSteps.checkNewsCreated(uniqueNewsTitle)
    }
}