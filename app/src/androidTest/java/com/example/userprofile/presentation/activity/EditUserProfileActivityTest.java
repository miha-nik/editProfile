package com.example.userprofile.presentation.activity;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import com.example.userprofile.R;
import com.example.userprofile.presentation.presenter.UserProfilePresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import butterknife.BindView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class EditUserProfileActivityTest {


    @Rule
    public ActivityTestRule<UserProfileActivity> activityUserTestRule = new ActivityTestRule(UserProfileActivity.class);
    @Rule
    public ActivityTestRule<EditUserProfileActivity> activityEditUserTestRule = new ActivityTestRule(EditUserProfileActivity.class);


    @Test
    public void loadAndShowData() throws Exception {
//        IdlingResource idlingUserResource = activityUserTestRule.getActivity().getIdlingResource();
//        IdlingRegistry.getInstance().register(idlingUserResource);
//        onView(withId(R.id.btnEdit)).perform(click());
//        IdlingRegistry.getInstance().unregister(idlingUserResource);
//
//        IdlingResource idlingEditUserResource = activityEditUserTestRule.getActivity().getIdlingResource();
//        IdlingRegistry.getInstance().register(idlingEditUserResource);
//
//        onView(withId(R.id.sGender)).check(matches(not(withSpinnerText(("")))));
//
//        IdlingRegistry.getInstance().unregister(idlingEditUserResource);
    }

}