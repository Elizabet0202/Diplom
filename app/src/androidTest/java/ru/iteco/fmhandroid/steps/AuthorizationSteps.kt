package ru.iteco.fmhandroid.steps

import ru.iteco.fmhandroid.pages.AuthorizationPage

class AuthorizationSteps {

    private val authorizationPage = AuthorizationPage()

    fun login(login: String, password: String) {
        authorizationPage.checkAuthorizationScreen()
        authorizationPage.enterLogin(login)
        authorizationPage.enterPassword(password)
        authorizationPage.clickEnterButton()
    }

    fun loginWithValidCredentials() {
        login(
            login = VALID_LOGIN,
            password = VALID_PASSWORD
        )
    }

    fun checkSuccessfulAuthorization() {
        authorizationPage.checkMainScreen()
    }

    fun checkFailedAuthorization() {
        authorizationPage.checkAuthorizationScreenAfterFailedLogin()
    }

    companion object {
        private const val VALID_LOGIN = "login2"
        private const val VALID_PASSWORD = "password2"
    }
}