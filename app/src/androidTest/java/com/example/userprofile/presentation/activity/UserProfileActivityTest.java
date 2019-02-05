package com.example.userprofile.presentation.activity;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.userprofile.R;
import com.example.userprofile.presentation.presenter.UserProfilePresenter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class UserProfileActivityTest {

    @Rule
    public ActivityTestRule<UserProfileActivity> activityTestRule = new ActivityTestRule(UserProfileActivity.class);

    @Test
    public void loadAndShowData() throws Exception {
        IdlingResource idlingResource = activityTestRule.getActivity().getIdlingResource();

        IdlingRegistry.getInstance().register(idlingResource);

        onView(withId(R.id.tvDisplayName)).check(matches(not(withText(""))));
        onView(withId(R.id.tvBirthday)).check(matches(not(withText(""))));
        onView(withId(R.id.tvGender)).check(matches(not(withText(""))));
        onView(withId(R.id.tvEthnicity)).check(matches(not(withText(""))));
        onView(withId(R.id.tvReligion)).check(matches(not(withText(""))));
        onView(withId(R.id.tvHeight)).check(matches(not(withText(""))));
        onView(withId(R.id.tvFigure)).check(matches(not(withText(""))));
        onView(withId(R.id.tvAboutMe)).check(matches(not(withText(""))));
        onView(withId(R.id.tvLocation)).check(matches(not(withText(""))));

        IdlingRegistry.getInstance().unregister(idlingResource);
    }
}