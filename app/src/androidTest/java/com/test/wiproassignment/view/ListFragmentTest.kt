package com.test.wiproassignment.view

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.test.wiproassignment.R
import org.hamcrest.EasyMock2Matchers.equalTo
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ListFragmentTest {

    private lateinit var listFragment: ListFragment

    @Before
    fun setUp() {
        listFragment = ListFragment()

    }

    @Test
    fun shouldNotBeNull() {
        assertNotNull("ListFragment not null", listFragment)
    }


    @After
    fun tearDown() {
    }

}