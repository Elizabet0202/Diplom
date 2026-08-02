package ru.iteco.fmhandroid.steps

import io.qameta.allure.kotlin.Step
import ru.iteco.fmhandroid.pages.OurMissionPage

class OurMissionSteps {

    private val ourMissionPage = OurMissionPage()

    @Step("Открыть раздел 'Наша миссия'")
    fun openOurMissionSection() {
        ourMissionPage.openOurMission()
    }

    @Step("Проверить открытие экрана 'Наша миссия'")
    fun checkOurMissionScreenIsOpened() {
        ourMissionPage.checkOurMissionScreen()
    }
}