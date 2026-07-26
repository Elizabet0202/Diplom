package ru.iteco.fmhandroid.steps

import ru.iteco.fmhandroid.pages.NewsPage

class NewsSteps {

    private val newsPage = NewsPage()

    fun openNewsSection() {
        newsPage.openMainMenu()
        newsPage.clickNewsMenuItem()
    }

    fun checkNewsScreenIsOpened() {
        newsPage.checkNewsScreen()
    }

    fun expandFirstNews() {
        newsPage.expandFirstNews()
    }

    fun checkFirstNewsDescriptionIsDisplayed() {
        newsPage.checkFirstNewsDescriptionIsDisplayed()
    }

    fun openNewsControlPanel() {
        newsPage.openNewsControlPanel()
    }

    fun openCreateNewsScreen() {
        newsPage.clickAddNewsButton()
    }

    fun createNews(
        category: String,
        title: String,
        description: String
    ) {
        newsPage.selectCategory(category)
        newsPage.enterTitle(title)
        newsPage.selectCurrentPublicationDate()
        newsPage.selectCurrentPublicationTime()
        newsPage.enterDescription(description)
        newsPage.clickSaveButton()
    }

    fun checkNewsCreated(title: String) {
        newsPage.checkCreatedNewsIsDisplayed(title)
    }
}