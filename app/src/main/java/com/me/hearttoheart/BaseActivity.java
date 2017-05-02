package com.me.hearttoheart;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class BaseActivity extends Activity {

    public static final String EXITACTION = "action.exit";
    private ExitReceiver exitReceiver = new ExitReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter filter = new IntentFilter();
        filter.addAction(EXITACTION);
        registerReceiver(exitReceiver, filter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(exitReceiver);
    }

    public class ExitReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            BaseActivity.this.finish();
        }
    }
}
