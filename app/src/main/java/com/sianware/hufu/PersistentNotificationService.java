package com.sianware.hufu;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.KeyEvent;

public class PersistentNotificationService extends Service {

    public PersistentNotificationService() {
    }


    public static boolean taskIsRunning = false;

    private static final int NOTIFICATION = 1;
    public static final String CLOSE_ACTION = "close";
    @Nullable
    private NotificationManager mNotificationManager = null;
    private final NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(this);

    /*
    Blatantly copied notification code from https://stackoverflow.com/a/29569884/1120802
     */
    private void setupNotifications() { //called in onCreate()
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP),
                0);
        PendingIntent pendingCloseIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        .setAction(CLOSE_ACTION),
                0);
        mNotificationBuilder
                .setSmallIcon(R.drawable.hufu_text)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setContentTitle(getText(R.string.app_name))
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.ic_menu_close_clear_cancel,
                        "Shutdown", pendingCloseIntent)
                .setOngoing(true);
    }

    private void showNotification() {
        mNotificationBuilder
                .setTicker("Service Connected [Ticker]")
                .setContentText("Headunit Feature Upgrader Running");
        if (mNotificationManager != null) {
            mNotificationManager.notify(NOTIFICATION, mNotificationBuilder.build());
        }
        Notification statusNotification = mNotificationBuilder.build();
        startForeground(NOTIFICATION, statusNotification);
    }

    @Override
    public void onCreate() {
        setupNotifications();
        showNotification();
        if(!taskIsRunning) {
            taskIsRunning = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int logCount = 0;
                    long lastRunAt = 0;
                    while(true) {
                        long now = System.currentTimeMillis()/1000;
                        if (now > lastRunAt + 1) {
                            Log.d("IDK", String.format("Current instance has been running for around %d", logCount++));
                            lastRunAt = now;

                            // Simple test
//                            if(logCount == 10) {
//                                MediaHelpers.sendMediaNext(getApplicationContext());
//                            }
                        }
                    }
                }
            }).start();
        }
    }

    @Override
    public void onDestroy() {
        taskIsRunning = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }
}
