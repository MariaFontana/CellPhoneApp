package com.example.acer.mynewponeapp.Activity;


import androidx.test.rule.ActivityTestRule;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)

public class ActivityHomeTest extends TestCase {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    public void testOnCreate() {

    }
    @Test
    public void testOnBackPressed() {
    }
    @Test
    public void testOnNavigationItemSelected() {
    }
    @Test
    public void testOnPointerCaptureChanged() {
    }
    @Test
    public void testSetFragment() {
    }
}