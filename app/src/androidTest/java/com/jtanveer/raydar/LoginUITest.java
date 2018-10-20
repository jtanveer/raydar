package com.jtanveer.raydar;

import android.support.test.espresso.intent.Intents;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jtanveer.raydar.ui.home.HomeActivity;
import com.jtanveer.raydar.ui.root.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {
        Intents.init();
    }

    @Test
    public void loginSuccessTest() {
        // user needs to be signed up beforehand
        onView(withId(R.id.et_login_email)).check(matches((isDisplayed())));
        onView(withId(R.id.et_login_email)).perform(click(), replaceText("nayon@colortalking.com"));
        onView(withId(R.id.et_login_password)).check(matches((isDisplayed())));
        onView(withId(R.id.et_login_password)).perform(click(), replaceText("qweasd@#"), closeSoftKeyboard());
        onView(withId(R.id.bt_login)).perform(click());

        intended(hasComponent(HomeActivity.class.getName()));
    }

    @After
    public void release() {
        Intents.release();
    }

    @Test
    public void loginFailedTest() {
        onView(withId(R.id.et_login_email)).check(matches((isDisplayed())));
        onView(withId(R.id.et_login_email)).perform(click(), replaceText("nayon@colortalking.com"));
        onView(withId(R.id.et_login_password)).check(matches((isDisplayed())));
        onView(withId(R.id.et_login_password)).perform(click(), replaceText("123asd#@"), closeSoftKeyboard());
        onView(withId(R.id.bt_login)).perform(click());

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("Wrong email or password")))
                .check(matches(isDisplayed()));
    }
}
