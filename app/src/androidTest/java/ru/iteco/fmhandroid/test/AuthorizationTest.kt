package ru.iteco.fmhandroid.test

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.steps.AuthorizationSteps
import ru.iteco.fmhandroid.ui.AppActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class AuthorizationTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AppActivity::class.java)

    private val authorizationSteps = AuthorizationSteps()

    @Test
    fun successfulAuthorization() {
        authorizationSteps.loginWithValidCredentials()

        authorizationSteps.checkSuccessfulAuthorization()
    }

    @Test
    fun authorizationWithInvalidLogin() {
        authorizationSteps.login(
            login = "wrongLogin",
            password = "password2"
        )

        authorizationSteps.checkFailedAuthorization()
    }

    @Test
    fun authorizationWithInvalidPassword() {
        authorizationSteps.login(
            login = "login2",
            password = "wrongPassword"
        )

        authorizationSteps.checkFailedAuthorization()
    }

    @Test
    fun authorizationWithEmptyFields() {
        authorizationSteps.login(
            login = "",
            password = ""
        )

        authorizationSteps.checkFailedAuthorization()
    }
}