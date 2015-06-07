package com.reactiverobot.notifier;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceActivity;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowNotificationManager;

import static org.junit.Assert.assertEquals;

/**
 * Created by Matthew on 6/7/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = 21)
public class MainActivityTest {

    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;
    }

    private final ShadowNotificationManager nm = Shadows.shadowOf(
            (NotificationManager) RuntimeEnvironment.application.getSystemService(Context.NOTIFICATION_SERVICE));

    @Test
    public void clickNotificationButton() {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

        View notificationButton = mainActivity.findViewById(R.id.button);

        notificationButton.performClick();

        assertEquals(1, nm.getAllNotifications().size());
    }

    @Test
    public void enableSettingButton() {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

        View notificationButton = mainActivity.findViewById(R.id.settings_button);

        notificationButton.performClick();

        Intent expectedIntent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        Intent intent = Shadows.shadowOf(mainActivity).getNextStartedActivity();
        assertEquals(expectedIntent, intent);
    }

}
