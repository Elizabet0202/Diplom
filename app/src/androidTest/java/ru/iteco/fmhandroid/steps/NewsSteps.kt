package ru.iteco.fmhandroid.steps

import io.qameta.allure.kotlin.Step
import ru.iteco.fmhandroid.pages.NewsPage

class NewsSteps {

    private val newsPage = NewsPage()

    @Step("Открыть раздел 'Новости'")
    fun openNewsSection() {
        newsPage.openMainMenu()
        newsPage.clickNewsMenuItem()
    }

    @Step("Проверить открытие экрана новостей")
    fun checkNewsScreenIsOpened() {
        newsPage.checkNewsScreen()
    }

    @Step("Раскрыть первую новость")
    fun expandFirstNews() {
        newsPage.expandFirstNews()
    }

    @Step("Проверить отображение описания первой новости")
    fun checkFirstNewsDescriptionIsDisplayed() {
        newsPage.checkFirstNewsDescriptionIsDisplayed()
    }

    @Step("Открыть панель управления новостями")
    fun openNewsControlPanel() {
        newsPage.openNewsControlPanel()
    }

    @Step("Открыть экран создания новости")
    fun openCreateNewsScreen() {
        newsPage.clickAddNewsButton()
    }

    @Step("Создать новость с заголовком: {title}")
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

    @Step("Проверить, что новость '{title}' создана")
    fun checkNewsCreated(title: String) {
        newsPage.checkCreatedNewsIsDisplayed(title)
    }

    @Step("Удалить новость '{title}'")
    fun deleteNews(title: String) {
        newsPage.deleteNewsByTitle(title)
    }

    @Step("Проверить, что новость '{title}' удалена")
    fun checkNewsDeleted(title: String) {
        newsPage.checkNewsDeleted(title)
    }
}