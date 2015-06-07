package com.reactiverobot.notifier;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.support.v4.content.LocalBroadcastManager;

import com.google.gson.Gson;
import com.reactiverobot.notifier.handler.NotificationTextHandler;
import com.reactiverobot.notifier.handler.NotificationTextHandler.NotificationText;


public class NotificationService extends NotificationListenerService {

    public static final String LOG_TAG = "notification-service";
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

        String ticker = null;
        if (sbn.getNotification().tickerText != null) {
             ticker = sbn.getNotification().tickerText.toString();
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

            NotificationText notificationText = new NotificationText(ticker, title, text.toString(), pack);
            Gson gson = new Gson();

            Intent broadcastIntent = new Intent("notification-text-msg");
            broadcastIntent.putExtra("ntext", gson.toJson(notificationText));
            LocalBroadcastManager.getInstance(context).sendBroadcast(broadcastIntent);
        }

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (sbn.isClearable()) {
            Log.i(LOG_TAG, "Clearing notification id=" + sbn.getId());
            notificationManager.cancel(sbn.getId());
        } else {
            Log.i(LOG_TAG, "Unable to clear notification id=" + sbn.getId());
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("Msg","Notification Removed");
    }
}