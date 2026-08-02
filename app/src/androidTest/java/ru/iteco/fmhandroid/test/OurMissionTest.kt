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
import ru.iteco.fmhandroid.steps.OurMissionSteps
import ru.iteco.fmhandroid.ui.AppActivity

@LargeTest
@Feature("Наша миссия")
@RunWith(AllureAndroidJUnit4::class)
class OurMissionTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AppActivity::class.java)

    private val authorizationSteps = AuthorizationSteps()
    private val ourMissionSteps = OurMissionSteps()

    @Story("Тематические цитаты")
    @Description("Проверка открытия раздела 'Наша миссия' с тематическими цитатами")
    @Test
    fun openThematicQuotes() {

        authorizationSteps.loginWithValidCredentials()
        authorizationSteps.checkSuccessfulAuthorization()

        ourMissionSteps.openOurMissionSection()
        ourMissionSteps.checkOurMissionScreenIsOpened()
    }
}