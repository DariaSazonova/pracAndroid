package com.example.pr7v17

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
//    @get:Rule
//    var activity = ActivityScenarioRule(MainActivity::class.java)
    @Test
fun useAppContext() {
    // Context of the app under test.
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    assertEquals("com.example.myapplication", appContext.packageName)
}
    @get:Rule
    var rule:ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun mul(){
        onView(withId(R.id.editDenomirator1)).perform((typeText("0")))
        onView(withId(R.id.editNumerator1)).perform((typeText("0")))
        onView(withId(R.id.editWholeFraction1)).perform((typeText("0")))
        onView(withId(R.id.editDenomirator2)).perform((typeText("0")))
        onView(withId(R.id.editNumerator2)).perform((typeText("0")))
        onView(withId(R.id.editWholeFraction2)).perform((typeText("0")))

        onView(withId(R.id.buttonMul)).perform(click())

        onView(withId(R.id.editDenomiratorRes)).check(matches(withText("7")))
        onView(withId(R.id.editNumeratorRes)).check(matches(withText("")))
        onView(withId(R.id.editWholeFractionRes)).check(matches(withText("")))
    }
}