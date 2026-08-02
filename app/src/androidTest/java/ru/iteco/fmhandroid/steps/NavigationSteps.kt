package ru.iteco.fmhandroid.steps

import io.qameta.allure.kotlin.Step
import ru.iteco.fmhandroid.pages.NavigationPage

class NavigationSteps {

    private val navigationPage = NavigationPage()

    @Step("Открыть боковое меню")
    fun openSideMenu() {
        navigationPage.openSideMenu()
    }

    @Step("Проверить отображение пунктов бокового меню")
    fun checkSideMenuItemsAreDisplayed() {
        navigationPage.checkSideMenuItems()
    }

    @Step("Открыть раздел 'Новости' из бокового меню")
    fun openNewsFromSideMenu() {
        navigationPage.clickNewsMenuItem()
    }

    @Step("Проверить открытие экрана новостей")
    fun checkNewsScreenIsOpened() {
        navigationPage.checkNewsScreenIsOpened()
    }
}