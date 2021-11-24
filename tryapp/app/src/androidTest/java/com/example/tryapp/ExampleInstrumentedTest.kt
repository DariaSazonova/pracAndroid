package com.example.tryapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.tryapp", appContext.packageName)
    }
    @get:Rule
    var rule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun mul0(){
        onView(withId(R.id.editDenomirator1)).perform((typeText("0")))
        onView(withId(R.id.editNumerator1)).perform((typeText("0")))
        onView(withId(R.id.editWholeFraction1)).perform((typeText("0")))
        onView(withId(R.id.editDenomirator2)).perform((typeText("0")))
        onView(withId(R.id.editNumerator2)).perform((typeText("0")))
        onView(withId(R.id.editWholeFraction2)).perform((typeText("0")))

        onView(withId(R.id.buttonMul)).perform(click())

        onView(withId(R.id.editDenomiratorRes)).check(matches(withText("")))
        onView(withId(R.id.editNumeratorRes)).check(matches(withText("")))
        onView(withId(R.id.editWholeFractionRes)).check(matches(withText("")))
    }
    @Test
    fun mul1(){
        onView(withId(R.id.editDenomirator1)).perform((typeText("0")))
        onView(withId(R.id.editNumerator1)).perform((typeText("12")))
        onView(withId(R.id.editWholeFraction1)).perform((typeText("1")))
        onView(withId(R.id.editDenomirator2)).perform((typeText("12")))
        onView(withId(R.id.editNumerator2)).perform((typeText("1")))
        onView(withId(R.id.editWholeFraction2)).perform((typeText("1")))

        onView(withId(R.id.buttonMul)).perform(click())

        onView(withId(R.id.editDenomiratorRes)).check(matches(withText("")))
        onView(withId(R.id.editNumeratorRes)).check(matches(withText("")))
        onView(withId(R.id.editWholeFractionRes)).check(matches(withText("")))
    }
    @Test
    fun mul2(){
        onView(withId(R.id.editDenomirator1)).perform((typeText("13")))
        onView(withId(R.id.editNumerator1)).perform((typeText("1")))
        onView(withId(R.id.editWholeFraction1)).perform((typeText("0")))
        onView(withId(R.id.editDenomirator2)).perform((typeText("13")))
        onView(withId(R.id.editNumerator2)).perform((typeText("13")))
        onView(withId(R.id.editWholeFraction2)).perform((typeText("0")))

        onView(withId(R.id.buttonMul)).perform(click())

        onView(withId(R.id.editDenomiratorRes)).check(matches(withText("13")))
        onView(withId(R.id.editNumeratorRes)).check(matches(withText("1")))
        onView(withId(R.id.editWholeFractionRes)).check(matches(withText("0")))
    }
    @Test
    fun div0(){
        onView(withId(R.id.editDenomirator1)).perform((typeText("1")))
        onView(withId(R.id.editNumerator1)).perform((typeText("1")))
        onView(withId(R.id.editWholeFraction1)).perform((typeText("1")))
        onView(withId(R.id.editDenomirator2)).perform((typeText("2")))
        onView(withId(R.id.editNumerator2)).perform((typeText("2")))
        onView(withId(R.id.editWholeFraction2)).perform((typeText("2")))

        onView(withId(R.id.buttonDil)).perform(click())

        onView(withId(R.id.editDenomiratorRes)).check(matches(withText("3")))
        onView(withId(R.id.editNumeratorRes)).check(matches(withText("2")))
        onView(withId(R.id.editWholeFractionRes)).check(matches(withText("0")))
    }
    @Test
    fun div1(){
        onView(withId(R.id.editDenomirator1)).perform((typeText("0")))
        onView(withId(R.id.editNumerator1)).perform((typeText("1")))
        onView(withId(R.id.editWholeFraction1)).perform((typeText("1")))
        onView(withId(R.id.editDenomirator2)).perform((typeText("2")))
        onView(withId(R.id.editNumerator2)).perform((typeText("2")))
        onView(withId(R.id.editWholeFraction2)).perform((typeText("2")))

        onView(withId(R.id.buttonDil)).perform(click())

        onView(withId(R.id.editDenomiratorRes)).check(matches(withText("")))
        onView(withId(R.id.editNumeratorRes)).check(matches(withText("")))
        onView(withId(R.id.editWholeFractionRes)).check(matches(withText("")))
    }
    @Test
    fun div2(){
        onView(withId(R.id.editDenomirator1)).perform((typeText("0")))
        onView(withId(R.id.editNumerator1)).perform((typeText("0")))
        onView(withId(R.id.editWholeFraction1)).perform((typeText("0")))
        onView(withId(R.id.editDenomirator2)).perform((typeText("0")))
        onView(withId(R.id.editNumerator2)).perform((typeText("0")))
        onView(withId(R.id.editWholeFraction2)).perform((typeText("0")))

        onView(withId(R.id.buttonDil)).perform(click())

        onView(withId(R.id.editDenomiratorRes)).check(matches(withText("")))
        onView(withId(R.id.editNumeratorRes)).check(matches(withText("")))
        onView(withId(R.id.editWholeFractionRes)).check(matches(withText("")))
    }
    @Test
    fun div3(){
        onView(withId(R.id.editDenomirator1)).perform((typeText("1")))
        onView(withId(R.id.editNumerator1)).perform((typeText("0")))
        onView(withId(R.id.editWholeFraction1)).perform((typeText("0")))
        onView(withId(R.id.editDenomirator2)).perform((typeText("1")))
        onView(withId(R.id.editNumerator2)).perform((typeText("0")))
        onView(withId(R.id.editWholeFraction2)).perform((typeText("0")))

        onView(withId(R.id.buttonDil)).perform(click())

        onView(withId(R.id.editDenomiratorRes)).check(matches(withText("")))
        onView(withId(R.id.editNumeratorRes)).check(matches(withText("")))
        onView(withId(R.id.editWholeFractionRes)).check(matches(withText("")))
    }
}