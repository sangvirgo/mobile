package com.sangptit.broadcastdemo.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CustomReceiver extends BroadcastReceiver {

    private static final String TAG = "CustomReceiver";
    public static final String CUSTOM_ACTION = "com.sangptit.broadcastdemo.CUSTOM_ACTION";
    public static final String EXTRA_MESSAGE = "message";

    public interface OnCustomBroadcastListener {
        void onCustomBroadcastReceived(String message);
    }

    private final OnCustomBroadcastListener listener;

    public CustomReceiver(OnCustomBroadcastListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (CUSTOM_ACTION.equals(intent.getAction())) {
            String message = intent.getStringExtra(EXTRA_MESSAGE);
            if (message == null) {
                message = "";
            }
            Log.d(TAG, "Nhan custom broadcast: " + message);
            if (listener != null) {
                listener.onCustomBroadcastReceived(message);
            }
        }
    }
}
