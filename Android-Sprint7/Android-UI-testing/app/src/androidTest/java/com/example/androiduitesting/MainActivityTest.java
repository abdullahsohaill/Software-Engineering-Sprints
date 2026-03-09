package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddCity() {
        // Click on "ADD CITY" button
        onView(withId(R.id.button_add)).perform(click());

        // Type "Edmonton" in the edit text
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));

        // Click on "CONFIRM" button
        onView(withId(R.id.button_confirm)).perform(click());

        // Check if "Edmonton" is in the list
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).check(matches(withText("Edmonton")));
    }

    @Test
    public void testClearAll() {
        // Add a city first
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Clear all cities
        onView(withId(R.id.button_clear)).perform(click());

        // Check if the list is empty (this is a bit tricky with Espresso onData, 
        // usually we check if the view doesn't exist or has 0 children)
        onView(withText("Edmonton")).check(doesNotExist());
    }

    @Test
    public void testListViewClickSwitchesActivity() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city in the list
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Check if ShowActivity is displayed (assuming R.id.button_back is unique to ShowActivity)
        onView(withId(R.id.button_back)).check(matches(isDisplayed()));
    }
}
