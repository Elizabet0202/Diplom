package ru.iteco.fmhandroid

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.ui.AppActivity

@RunWith(AndroidJUnit4::class)
class AppLaunchTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AppActivity::class.java)

    @Test
    fun validLoginOpensMainScreen() {

        // Ждём, пока исчезнет стартовый экран
        Thread.sleep(3000)

        // Вводим логин
        onView(
            allOf(
                isAssignableFrom(EditText::class.java),
                isDescendantOfA(withId(R.id.login_text_input_layout))
            )
        ).perform(
            replaceText("login2"),
            closeSoftKeyboard()
        )

        // Вводим пароль
        onView(
            allOf(
                isAssignableFrom(EditText::class.java),
                isDescendantOfA(withId(R.id.password_text_input_layout))
            )
        ).perform(
            replaceText("password2"),
            closeSoftKeyboard()
        )

        // Нажимаем кнопку входа
        onView(withId(R.id.enter_button))
            .perform(click())

        // Ждём открытия главного экрана
        Thread.sleep(3000)

        // Проверяем, что экран авторизации исчез
        onView(withId(R.id.enter_button))
            .check(doesNotExist())
    }
}