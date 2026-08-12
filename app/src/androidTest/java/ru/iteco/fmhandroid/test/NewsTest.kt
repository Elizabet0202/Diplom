package ru.iteco.fmhandroid.test

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.Description
import io.qameta.allure.kotlin.Feature
import io.qameta.allure.kotlin.Story
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.dto.NewsData
import ru.iteco.fmhandroid.steps.AuthorizationSteps
import ru.iteco.fmhandroid.steps.NewsSteps
import ru.iteco.fmhandroid.ui.AppActivity

@RunWith(AllureAndroidJUnit4::class)
@LargeTest
@Feature("Новости")
class NewsTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AppActivity::class.java)

    private val authorizationSteps = AuthorizationSteps()
    private val newsSteps = NewsSteps()

    @Story("Просмотр новостей")
    @Description("Проверка открытия списка новостей")
    @Test
    fun openNewsList() {
        authorizationSteps.loginWithValidCredentials()
        authorizationSteps.checkSuccessfulAuthorization()

        newsSteps.openNewsSection()
        newsSteps.checkNewsScreenIsOpened()
    }

    @Story("Просмотр новостей")
    @Description("Проверка раскрытия первой новости")
    @Test
    fun expandFirstNews() {
        authorizationSteps.loginWithValidCredentials()
        authorizationSteps.checkSuccessfulAuthorization()

        newsSteps.openNewsSection()
        newsSteps.checkNewsScreenIsOpened()

        newsSteps.expandFirstNews()
        newsSteps.checkFirstNewsDescriptionIsDisplayed()
    }

    @Story("Управление новостями")
    @Description("Проверка создания новой новости")
    @Test
    fun createNews() {
        val newsData = NewsData.createNews
        val uniqueNewsTitle = NewsData.uniqueCreateTitle()

        authorizationSteps.loginWithValidCredentials()
        authorizationSteps.checkSuccessfulAuthorization()

        newsSteps.openNewsSection()
        newsSteps.checkNewsScreenIsOpened()

        newsSteps.openNewsControlPanel()
        newsSteps.openCreateNewsScreen()

        newsSteps.createNews(
            category = newsData.category,
            title = uniqueNewsTitle,
            description = newsData.description
        )

        newsSteps.checkNewsCreated(uniqueNewsTitle)
    }

    @Story("Управление новостями")
    @Description("Проверка удаления созданной новости")
    @Test
    fun deleteNews() {
        val newsData = NewsData.deleteNews
        val uniqueNewsTitle = NewsData.uniqueDeleteTitle()

        authorizationSteps.loginWithValidCredentials()
        authorizationSteps.checkSuccessfulAuthorization()

        newsSteps.openNewsSection()
        newsSteps.checkNewsScreenIsOpened()

        newsSteps.openNewsControlPanel()
        newsSteps.openCreateNewsScreen()

        newsSteps.createNews(
            category = newsData.category,
            title = uniqueNewsTitle,
            description = newsData.description
        )

        newsSteps.checkNewsCreated(uniqueNewsTitle)

        newsSteps.deleteNews(uniqueNewsTitle)
        newsSteps.checkNewsDeleted(uniqueNewsTitle)
    }
}
