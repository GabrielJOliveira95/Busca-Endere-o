package com.example.cep_koin.ui

import android.view.View
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher


object CepRobot {

    fun setTextOnEditText(viewMatchers: Matcher<View>, text: String){
        onView(viewMatchers).perform(typeText(text))
    }

    fun clickOnView(viewMatchers: Matcher<View>){
        onView(viewMatchers).perform(click())
    }

    fun checkViewIsNotEnabled(viewMatchers: Matcher<View>){
        onView(viewMatchers).check(matches(isNotEnabled()))
    }

    fun checkHasErrorText(viewMatchers: Matcher<View>, text: String){
        onView(viewMatchers).check(matches(hasErrorText(text)))
    }

    fun checkViewIsVisible(viewMatchers: Matcher<View>){
        onView(viewMatchers).check(matches(isDisplayed()))
    }

    fun checkIfKeyboardIsHide(){
        closeSoftKeyboard()
    }
}