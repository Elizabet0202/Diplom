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
import ru.iteco.fmhandroid.steps.AuthorizationSteps
import ru.iteco.fmhandroid.steps.NavigationSteps
import ru.iteco.fmhandroid.ui.AppActivity

@LargeTest
@Feature("Навигация")
@RunWith(AllureAndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AppActivity::class.java)

    private val authorizationSteps = AuthorizationSteps()
    private val navigationSteps = NavigationSteps()

    @Story("Боковое меню")
    @Description("Проверка открытия бокового меню и перехода в раздел 'Новости'")
    @Test
    fun sideMenuNavigation() {

        authorizationSteps.loginWithValidCredentials()
        authorizationSteps.checkSuccessfulAuthorization()

        navigationSteps.openSideMenu()
        navigationSteps.checkSideMenuItemsAreDisplayed()

        navigationSteps.openNewsFromSideMenu()
        navigationSteps.checkNewsScreenIsOpened()
    }
}