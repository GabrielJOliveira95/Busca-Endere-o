package com.example.cep_koin.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cep_koin.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun when_field_is_empty_should_display_an_error_message() {
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.cep_edit_text)).check(matches(hasErrorText("Digite o cep")))
    }

    @Test
    fun when_field_is_not_empty_should_disable_button_then_show_progress_bar() {
        onView(withId(R.id.cep_edit_text)).perform(typeText("06240000"))
        onView(withId(R.id.button)).perform(click())
    }
}