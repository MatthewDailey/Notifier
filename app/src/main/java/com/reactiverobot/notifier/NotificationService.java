package com.reactiverobot.notifier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.support.v4.content.LocalBroadcastManager;


public class NotificationService extends NotificationListenerService {

    private static final String LOG_TAG = "notification-service";
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String pack = sbn.getPackageName();
        Log.i(LOG_TAG, "Package " + pack);

        if (sbn.getNotification().tickerText != null) {
            String ticker = sbn.getNotification().tickerText.toString();
            Log.i(LOG_TAG, "Ticker " + ticker);
        } else {
            Log.i(LOG_TAG, "No ticker text.");
        }

        Bundle extras = sbn.getNotification().extras;
        if (extras != null) {
            String title = extras.getString("android.title");
            Log.i(LOG_TAG, "Title " + title);
            CharSequence text = extras.getCharSequence("android.text");
            Log.i(LOG_TAG, "Text " + text);
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("Msg","Notification Removed");
    }
}