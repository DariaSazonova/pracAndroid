package com.example.pr13

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.hasEntry
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsInstanceOf.instanceOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.util.*

fun test(animal:Animal): Matcher<View> {

    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("with animal ${animal.text}, ${animal.type} ")
        }

        override fun matchesSafely(item: RecyclerView?): Boolean {
            return (item?.adapter as Adapter).getList().any{it.text == animal.text && it.type == animal.type}

        }


    }
}

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.pr13", appContext.packageName)
    }
    @get: Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun test1(){
        onView(withId(R.id.buttonF)).perform(click())
    }
    @Test
    fun test2() {
        onView(withId(R.id.editTextTextPersonName)).perform(ViewActions.typeText("cat"))
        onView(withId(R.id.buttonF)).perform(ViewActions.click())
        val animal = Animal("cat", "Wikipedia has a recording of a cat meowing, because why not?")
        Thread.sleep(10000)
        onView(withId(R.id.recyclerView)).check(matches(test(animal)))
    }
    @Test
    @Throws(InterruptedException::class)
    fun testCaseForRecyclerScroll() {
        onView(withId(R.id.buttonF)).perform(ViewActions.click())
        Thread.sleep(1000)
        val recyclerView =
            activityRule.activity.findViewById<RecyclerView>(R.id.recyclerView)
        val itemCount =
            Objects.requireNonNull(recyclerView.adapter!!).itemCount
        onView(ViewMatchers.withId(R.id.recyclerView))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(
                        activityRule.activity.window.decorView
                    )
                )
            )
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))
    }



}