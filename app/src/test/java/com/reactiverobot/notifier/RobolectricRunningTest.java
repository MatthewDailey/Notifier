package com.reactiverobot.notifier;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Matthew on 6/7/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = 21)
public class RobolectricRunningTest {

    @Test
    public void testTrue() {
        Assert.assertTrue(true);
    }
}
