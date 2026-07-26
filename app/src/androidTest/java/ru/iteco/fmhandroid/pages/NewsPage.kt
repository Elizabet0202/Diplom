package ru.iteco.fmhandroid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf
import ru.iteco.fmhandroid.R
import ru.iteco.fmhandroid.utils.first
import ru.iteco.fmhandroid.utils.waitForView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup

class NewsPage {

    // Главный экран и меню.
    private val mainMenuButton = withId(R.id.main_menu_image_button)
    private val newsMenuItem = withText("News")

    // Экран списка новостей.
    private val newsScreen = withId(R.id.news_list_swipe_refresh)
    private val editNewsButton = withId(R.id.edit_news_material_button)

    // Панель управления новостями.
    private val newsControlPanel =
        withId(R.id.news_control_panel_swipe_to_refresh)

    private val addNewsButton = withId(R.id.add_news_image_view)

    // Экран создания новости.
    private val categoryField =
        withId(R.id.news_item_category_text_auto_complete_text_view)

    private val titleField =
        withId(R.id.news_item_title_text_input_edit_text)

    private val publicationDateField =
        withId(R.id.news_item_publish_date_text_input_edit_text)

    private val publicationTimeField =
        withId(R.id.news_item_publish_time_text_input_edit_text)

    private val descriptionField =
        withId(R.id.news_item_description_text_input_edit_text)

    private val saveButton = withId(R.id.save_button)

    // Элементы карточки новости.
    private val newsExpandButton =
        withId(R.id.view_news_item_image_view)

    private val newsDescription =
        withId(R.id.news_item_description_text_view)

    fun openMainMenu() {
        waitForView(mainMenuButton)

        onView(mainMenuButton)
            .check(matches(isDisplayed()))
            .perform(click())
    }

    fun clickNewsMenuItem() {
        waitForView(newsMenuItem)

        onView(newsMenuItem)
            .check(matches(isDisplayed()))
            .perform(click())
    }

    fun checkNewsScreen() {
        waitForView(newsScreen)

        onView(newsScreen)
            .check(matches(isDisplayed()))
    }

    fun expandFirstNews() {
        waitForView(first(newsExpandButton))

        onView(first(newsExpandButton))
            .perform(click())
    }

    fun checkFirstNewsDescriptionIsDisplayed() {
        val displayedDescription = allOf(
            newsDescription,
            isDisplayed()
        )

        waitForView(displayedDescription)

        onView(displayedDescription)
            .check(matches(isDisplayed()))
    }

    fun openNewsControlPanel() {
        waitForView(editNewsButton)

        onView(editNewsButton)
            .check(matches(isDisplayed()))
            .perform(click())

        waitForView(newsControlPanel)

        onView(newsControlPanel)
            .check(matches(isDisplayed()))
    }

    fun clickAddNewsButton() {
        waitForView(addNewsButton)

        onView(addNewsButton)
            .check(matches(isDisplayed()))
            .perform(click())

        waitForView(categoryField)

        onView(categoryField)
            .check(matches(isDisplayed()))
    }

    fun selectCategory(category: String) {
        onView(categoryField)
            .perform(click())

        onView(withText(category))
            .inRoot(isPlatformPopup())
            .check(matches(isDisplayed()))
            .perform(click())
    }

    fun enterTitle(title: String) {
        waitForView(titleField)

        onView(titleField)
            .perform(
                click(),
                replaceText(title),
                closeSoftKeyboard()
            )
    }

    fun selectCurrentPublicationDate() {
        onView(publicationDateField)
            .perform(click())

        waitForView(withId(android.R.id.button1))

        onView(withId(android.R.id.button1))
            .perform(click())
    }

    fun selectCurrentPublicationTime() {
        onView(publicationTimeField)
            .perform(click())

        waitForView(withId(android.R.id.button1))

        onView(withId(android.R.id.button1))
            .perform(click())
    }

    fun enterDescription(description: String) {
        onView(descriptionField)
            .perform(
                replaceText(description),
                closeSoftKeyboard()
            )
    }

    fun clickSaveButton() {
        waitForView(saveButton)

        onView(saveButton)
            .perform(click())
    }

    fun checkCreatedNewsIsDisplayed(title: String) {
        val createdNewsTitle = allOf(
            withId(R.id.news_item_title_text_view),
            withText(title)
        )

        onView(withId(R.id.news_list_recycler_view))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(createdNewsTitle)
                )
            )

        onView(createdNewsTitle)
            .check(matches(isDisplayed()))
    }
}