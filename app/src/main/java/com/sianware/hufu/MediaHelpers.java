package com.sianware.hufu;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

/**
 * Created by isaac on 8/15/17.
 */

public class MediaHelpers {

    public static void sendMediaKeyPress(Context context, int mediaKeyCode) {
        Intent i = new Intent(Intent.ACTION_MEDIA_BUTTON);
        i.putExtra(Intent.EXTRA_KEY_EVENT,new KeyEvent(KeyEvent.ACTION_DOWN, mediaKeyCode));
        context.sendOrderedBroadcast(i, null);

        i = new Intent(Intent.ACTION_MEDIA_BUTTON);
        i.putExtra(Intent.EXTRA_KEY_EVENT,new KeyEvent(KeyEvent.ACTION_UP, mediaKeyCode));
        context.sendOrderedBroadcast(i, null);
    }

    public static void sendMediaNext(Context context) {
        sendMediaKeyPress(context, KeyEvent.KEYCODE_MEDIA_NEXT);
    }

    public static void sendMediaPrevious(Context context) {
        sendMediaKeyPress(context, KeyEvent.KEYCODE_MEDIA_PREVIOUS);
    }

    public static void sendMediaPause(Context context) {
        sendMediaKeyPress(context, KeyEvent.KEYCODE_MEDIA_PAUSE);
    }

    public static void sendMediaPlay(Context context) {
        sendMediaKeyPress(context, KeyEvent.KEYCODE_MEDIA_PLAY);
    }

}
