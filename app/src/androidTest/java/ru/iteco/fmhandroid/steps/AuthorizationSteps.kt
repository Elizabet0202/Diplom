package ru.iteco.fmhandroid.steps

import io.qameta.allure.kotlin.Step
import ru.iteco.fmhandroid.dto.AuthorizationCredentials
import ru.iteco.fmhandroid.dto.AuthorizationData
import ru.iteco.fmhandroid.pages.AuthorizationPage

class AuthorizationSteps {

    private val authorizationPage = AuthorizationPage()

    @Step("Выполнить авторизацию")
    fun login(credentials: AuthorizationCredentials) {
        authorizationPage.checkAuthorizationScreen()
        authorizationPage.enterLogin(credentials.login)
        authorizationPage.enterPassword(credentials.password)
        authorizationPage.clickEnterButton()
    }

    @Step("Авторизоваться с корректными учетными данными")
    fun loginWithValidCredentials() {
        login(AuthorizationData.validUser)
    }

    @Step("Проверить успешную авторизацию")
    fun checkSuccessfulAuthorization() {
        authorizationPage.checkMainScreen()
    }

    @Step("Проверить отображение ошибки авторизации")
    fun checkFailedAuthorization() {
        authorizationPage.checkAuthorizationScreenAfterFailedLogin()
    }

    @Step("Выйти из аккаунта")
    fun logout() {
        authorizationPage.logout()
    }

    @Step("Проверить открытие экрана авторизации")
    fun checkLogoutSuccessful() {
        authorizationPage.checkAuthorizationScreenIsOpened()
    }
}