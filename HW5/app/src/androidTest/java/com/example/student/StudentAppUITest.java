package com.example.student;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


    @RunWith(AndroidJUnit4.class)
    @LargeTest
    public class StudentAppUITest {

        @Rule
        public ActivityTestRule<MainActivity> activityRule =
                new ActivityTestRule<>(MainActivity.class);


        @Test
        public void testStudentSchoolGradeStatus() {

            onView(withId(R.id.School))
                    .perform(typeText("NSC"));
            onView(withId(R.id.SID))
                    .perform(typeText("12345" ));
            onView(withId(R.id.grade))
                    .perform(typeText("4.0" ));



            onView(withId(R.id.button)).perform(click());
            onView(withId(R.id.textViewResult)).check(matches(withText("NSC ,For SID: 12345 ,Passed AD340 with good grade!")));
        }
    }


