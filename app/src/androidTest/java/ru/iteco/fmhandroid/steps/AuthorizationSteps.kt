package ru.iteco.fmhandroid.steps

import io.qameta.allure.kotlin.Step
import ru.iteco.fmhandroid.pages.AuthorizationPage

class AuthorizationSteps {

    private val authorizationPage = AuthorizationPage()

    @Step("Авторизоваться с логином: {login}")
    fun login(login: String, password: String) {
        authorizationPage.checkAuthorizationScreen()
        authorizationPage.enterLogin(login)
        authorizationPage.enterPassword(password)
        authorizationPage.clickEnterButton()
    }

    @Step("Авторизоваться с корректными учетными данными")
    fun loginWithValidCredentials() {
        login(
            login = VALID_LOGIN,
            password = VALID_PASSWORD
        )
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

    companion object {
        private const val VALID_LOGIN = "login2"
        private const val VALID_PASSWORD = "password2"
    }
}