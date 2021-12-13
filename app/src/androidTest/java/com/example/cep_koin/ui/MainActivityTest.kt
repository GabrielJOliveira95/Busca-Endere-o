package com.example.cep_koin.ui

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cep_koin.R
import com.example.cep_koin.ui.CepRobot.checkHasErrorText
import com.example.cep_koin.ui.CepRobot.checkIfKeyboardIsHide
import com.example.cep_koin.ui.CepRobot.checkViewIsNotEnabled
import com.example.cep_koin.ui.CepRobot.checkViewIsVisible
import com.example.cep_koin.ui.CepRobot.clickOnView
import com.example.cep_koin.ui.CepRobot.setTextOnEditText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun when_field_is_empty_should_display_an_error_message() {
        clickOnView(withId(R.id.button))
        setTextOnEditText(withId(R.id.cep_edit_text), "")
        checkHasErrorText(withId(R.id.cep_edit_text),"Digite o cep")
    }

    @Test
    fun `when_field_is_not_empty_should_disable_button_then_hide_keyboard_and_show_progress_bar`() {
        setTextOnEditText(withId(R.id.cep_edit_text),"06240000")
        clickOnView(withId(R.id.button))
        checkIfKeyboardIsHide()
        checkViewIsNotEnabled(withId(R.id.button))
        checkViewIsVisible(withId(R.id.progress_cep_loading))
    }
}