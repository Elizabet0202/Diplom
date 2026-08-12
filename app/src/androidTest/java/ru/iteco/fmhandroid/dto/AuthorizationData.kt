package ru.iteco.fmhandroid.dto

data class AuthorizationCredentials(
    val login: String,
    val password: String
)

object AuthorizationData {

    val validUser = AuthorizationCredentials(
        login = "login2",
        password = "password2"
    )

    val invalidLoginUser = AuthorizationCredentials(
        login = "wrongLogin",
        password = "password2"
    )

    val invalidPasswordUser = AuthorizationCredentials(
        login = "login2",
        password = "wrongPassword"
    )

    val emptyCredentials = AuthorizationCredentials(
        login = "",
        password = ""
    )
}