package com.reactiverobot.notifier.handler;

import android.app.Notification;

/**
 * Created by Matthew on 6/7/2015.
 */
public interface NotificationTextHandler {

    public static class NotificationText {
        public final String ticker;
        public final String title;
        public final String text;
        public final String packageName;

        public NotificationText(String ticker, String title, String text, String packageName) {
            this.ticker = ticker;
            this.title = title;
            this.text = text;
            this.packageName = packageName;
        }
    }

    boolean shouldAccept(NotificationText notificationText);
    void handleNotification(NotificationText notificationText);
}