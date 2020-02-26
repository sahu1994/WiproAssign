package com.test.wiproassignment.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.test.wiproassignment.R
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ListFragmentTest {

    @get:Rule
    var rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private lateinit var listFragment: ListFragment


    @Before
    fun setUp() {
        listFragment = ListFragment()
        rule.activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, listFragment)
            .commitAllowingStateLoss()
    }

    @Test
    fun shouldNotBeNull() {
        assertNotNull("ListFragment not null", listFragment)
    }

    @Test
    fun checkViewItems() {
        onView(withId(R.id.lvItems)).check(matches(isDisplayed()))
        onView(withId(R.id.pbList)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }



}