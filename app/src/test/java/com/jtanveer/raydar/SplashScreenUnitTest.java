package com.jtanveer.raydar;

import android.content.Intent;

import com.jtanveer.raydar.ui.SplashActivity;
import com.jtanveer.raydar.ui.root.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowLooper;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class SplashScreenUnitTest {

    @Test
    public void testMainActivityStartedAfterDelay() throws InterruptedException {
        ActivityController<SplashActivity> controller = Robolectric.buildActivity(SplashActivity.class)
                .create().start().resume().visible();

        synchronized (this) {
            this.wait(5000);
        }
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks();

        SplashActivity splashActivity = controller.get();
        Intent expectedIntent = new Intent(splashActivity, MainActivity.class);

        assertEquals(expectedIntent.getComponent().getClassName(),
                shadowOf(splashActivity).getNextStartedActivity().getComponent().getClassName());
    }
}
