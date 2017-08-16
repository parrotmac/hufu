package com.sianware.hufu;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        switch (action) {
            case PersistentNotificationService.CLOSE_ACTION:
                exit();
                break;
        }
    }

    private void exit() {
        stopService(new Intent(this, PersistentNotificationService.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent("com.sianware.START_BACKGROUND_SERVICE");
        intent.setClass(this, ServiceManager.class);
        sendBroadcast(intent);

        Intent huAppIntent = new Intent(Intent.ACTION_MAIN);
        huAppIntent.setComponent(new ComponentName("gb.xxy.hr","gb.xxy.hr.hu_act"));
        try {
            startActivity(huAppIntent);
        } catch (ActivityNotFoundException e) {
            Log.i("Headunit Not Found", "Headunit Reloaded application (gb.xxy.hr) not found, or hu_act activity no longer exists.");
        }
    }

    public void testBtnClicked(View v)
    {
        Intent intent = new Intent("com.sianware.TEST_BUTTON_CLICKED");
        intent.putExtra("value","test123");
        intent.setClass(this, ServiceManager.class);
        sendBroadcast(intent);

        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_SHORT).show();
    }

}
