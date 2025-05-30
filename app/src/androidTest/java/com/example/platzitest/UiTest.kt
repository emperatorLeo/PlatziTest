package com.example.platzitest

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.platzitest.presentation.MainActivity
import com.example.platzitest.presentation.testing.FLOATING_BUTTON_TEST_TAG
import com.example.platzitest.presentation.testing.SEARCH_BAR_TEST_TAG
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun search_bar_is_displayed_test() {
       composeTestRule.onNodeWithTag(SEARCH_BAR_TEST_TAG).isDisplayed()
    }

    @Test
    fun floating_button_is_displayed_test() {
            composeTestRule.onNodeWithTag(FLOATING_BUTTON_TEST_TAG).isDisplayed()
    }
}
