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
import ru.iteco.fmhandroid.dto.AuthorizationData
import ru.iteco.fmhandroid.steps.AuthorizationSteps
import ru.iteco.fmhandroid.ui.AppActivity

@LargeTest
@Feature("Авторизация")
@RunWith(AllureAndroidJUnit4::class)
class AuthorizationTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AppActivity::class.java)

    private val authorizationSteps = AuthorizationSteps()

    @Story("Вход в приложение")
    @Description("Проверка успешной авторизации с корректными учетными данными")
    @Test
    fun successfulAuthorization() {
        authorizationSteps.login(AuthorizationData.validUser)

        authorizationSteps.checkSuccessfulAuthorization()
    }

    @Story("Вход в приложение")
    @Description("Проверка отображения ошибки при вводе неверного логина")
    @Test
    fun authorizationWithInvalidLogin() {
        authorizationSteps.login(AuthorizationData.invalidLoginUser)

        authorizationSteps.checkFailedAuthorization()
    }

    @Story("Вход в приложение")
    @Description("Проверка отображения ошибки при вводе неверного пароля")
    @Test
    fun authorizationWithInvalidPassword() {
        authorizationSteps.login(AuthorizationData.invalidPasswordUser)

        authorizationSteps.checkFailedAuthorization()
    }

    @Story("Вход в приложение")
    @Description("Проверка отображения ошибки при авторизации с пустыми полями")
    @Test
    fun authorizationWithEmptyFields() {
        authorizationSteps.login(AuthorizationData.emptyCredentials)

        authorizationSteps.checkFailedAuthorization()
    }

    @Story("Выход из аккаунта")
    @Description("Проверка успешного выхода пользователя из приложения")
    @Test
    fun logoutFromAccount() {
        authorizationSteps.login(AuthorizationData.validUser)

        authorizationSteps.checkSuccessfulAuthorization()

        authorizationSteps.logout()

        authorizationSteps.checkLogoutSuccessful()
    }
}