package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
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
public class ShowActivityTest {

    // tells espresso to start main activity before each test
    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new ActivityScenarioRule<>(MainActivity.class);

    // helper function to quickly add a city for us to click on
    private void addCityForTesting() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Lahore"));
        onView(withId(R.id.button_confirm)).perform(click());
    }

    // test 1: check whether the activity correctly switched
    @Test
    public void testActivitySwitched() {
        addCityForTesting();

        // click the very first item in our list
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // if the back button is visible, it means we successfully switched to show activity
        onView(withId(R.id.button_back)).check(matches(isDisplayed()));
    }

    // test 2: test whether the city name is consistent
    @Test
    public void testCityNameIsConsistent() {
        addCityForTesting(); // adds "Lahore"

        // click the first item
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // check if the big textview actually says "Lahore"
        onView(withId(R.id.show_city_name)).check(matches(withText("Lahore")));
    }

    // test 3: test the "back" button
    @Test
    public void testBackButton() {
        addCityForTesting();

        // go to show activity
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // click the back button
        onView(withId(R.id.button_back)).perform(click());

        // if the add city button is visible again, it means we went back to main activity successfully
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
    }
}