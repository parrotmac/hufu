package com.sianware.hufu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ServiceManager extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        context.startService(new Intent(context,PersistentNotificationService.class));

        // Pulled straight from the documentation
        StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME) + "\n");
        String log = sb.toString();
        Log.d("Test BroadcastReceiver", log);

    }
}
