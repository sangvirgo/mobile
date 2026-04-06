package com.sangptit.broadcastdemo.activities;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sangptit.broadcastdemo.R;
import com.sangptit.broadcastdemo.receivers.AirplaneModeReceiver;
import com.sangptit.broadcastdemo.receivers.BatteryReceiver;
import com.sangptit.broadcastdemo.receivers.NetworkChangeReceiver;

public class MainActivity extends AppCompatActivity {

    private NetworkChangeReceiver networkReceiver;
    private BatteryReceiver batteryReceiver;
    private AirplaneModeReceiver airplaneModeReceiver;

    private TextView tvLog;
    private TextView tvNetworkStatus;
    private TextView tvBatteryStatus;
    private TextView tvAirplaneStatus;

    private StringBuilder logBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        networkReceiver = new NetworkChangeReceiver(new NetworkChangeReceiver.OnNetworkChangeListener() {
            @Override
            public void onNetworkChanged(boolean isConnected, String networkType) {
                String status = isConnected ? "Da ket noi: " + networkType : "Mat ket noi mang";
                tvNetworkStatus.setText(status);
                tvNetworkStatus.setTextColor(isConnected
                        ? getResources().getColor(R.color.green)
                        : getResources().getColor(R.color.red));
                appendLog("[NETWORK] " + status);
            }
        });

        batteryReceiver = new BatteryReceiver(new BatteryReceiver.OnBatteryChangeListener() {
            @Override
            public void onBatteryChanged(int level, boolean isCharging, String status) {
                String text = "Pin: " + level + "% | " + status;
                tvBatteryStatus.setText(text);
                tvBatteryStatus.setTextColor(level < 20
                        ? getResources().getColor(R.color.red)
                        : getResources().getColor(R.color.green));
                appendLog("[BATTERY] " + text);
            }
        });

        airplaneModeReceiver = new AirplaneModeReceiver(new AirplaneModeReceiver.OnAirplaneModeListener() {
            @Override
            public void onAirplaneModeChanged(boolean isAirplaneModeOn) {
                String status = isAirplaneModeOn ? "BAT" : "TAT";
                tvAirplaneStatus.setText("Che do may bay: " + status);
                tvAirplaneStatus.setTextColor(isAirplaneModeOn
                        ? getResources().getColor(R.color.red)
                        : getResources().getColor(R.color.green));
                appendLog("[AIRPLANE] Che do may bay: " + status);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter networkFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        IntentFilter batteryFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        IntentFilter airplaneFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(networkReceiver, networkFilter, Context.RECEIVER_NOT_EXPORTED);
            registerReceiver(batteryReceiver, batteryFilter, Context.RECEIVER_NOT_EXPORTED);
            registerReceiver(airplaneModeReceiver, airplaneFilter, Context.RECEIVER_NOT_EXPORTED);
        } else {
            registerReceiver(networkReceiver, networkFilter);
            registerReceiver(batteryReceiver, batteryFilter);
            registerReceiver(airplaneModeReceiver, airplaneFilter);
        }

        appendLog("=== All receivers REGISTERED ===");
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkReceiver);
        unregisterReceiver(batteryReceiver);
        unregisterReceiver(airplaneModeReceiver);
        appendLog("=== All receivers UNREGISTERED ===");
    }

    private void initViews() {
        tvNetworkStatus = findViewById(R.id.tvNetworkStatus);
        tvBatteryStatus = findViewById(R.id.tvBatteryStatus);
        tvAirplaneStatus = findViewById(R.id.tvAirplaneStatus);
        tvLog = findViewById(R.id.tvLog);
    }

    private void appendLog(String message) {
        String time = java.text.DateFormat.getTimeInstance().format(new java.util.Date());
        logBuilder.insert(0, time + " - " + message + "\n");
        tvLog.setText(logBuilder.toString());
    }
}