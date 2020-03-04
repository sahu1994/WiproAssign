package com.wipro.wiproassignment

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.wipro.wiproassignment.utils.isNetworkAvailable
import com.wipro.wiproassignment.view.ListFragment
import com.wipro.wiproassignment.view.MainActivity
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
        Espresso.onView(ViewMatchers.withId(R.id.lvItems))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.pbList))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.toolbar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkNetworkAvailability() {
        assert(isNetworkAvailable(rule.activity))
    }

}