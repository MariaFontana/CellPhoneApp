package com.example.acer.mynewponeapp;

import android.content.Intent;
import android.widget.Button;

import com.example.acer.mynewponeapp.Activity.ActivityHome;
import com.example.acer.mynewponeapp.Activity.MainActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;



import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)

public class MainActivityTest {


    private MainActivity activity;
    private Button buttonRegistre;



    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
        buttonRegistre = (Button) activity.findViewById(R.id.buttonLogin);
    }
    @Test
    public void PressButtonGoActivityHome() throws Exception {
        buttonRegistre.performClick();

            ShadowActivity shadowActivity = shadowOf(activity);
            Intent startedIntent = shadowActivity.getNextStartedActivity();
            ShadowIntent shadowIntent = shadowOf(startedIntent);
            assertThat(shadowIntent.getIntentClass(), equalTo(ActivityHome.class.getName()));
        }
    }


