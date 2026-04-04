package com.sangptit.broadcastdemo;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sangptit.broadcastdemo.receivers.AirplaneModeReceiver;
import com.sangptit.broadcastdemo.receivers.BatteryReceiver;
import com.sangptit.broadcastdemo.receivers.CustomReceiver;
import com.sangptit.broadcastdemo.receivers.NetworkChangeReceiver;

public class MainActivity extends AppCompatActivity {

    private NetworkChangeReceiver networkReceiver;
    private BatteryReceiver batteryReceiver;
    private AirplaneModeReceiver airplaneModeReceiver;
    private CustomReceiver customReceiver;

    private TextView tvLog;
    private TextView tvNetworkStatus;
    private TextView tvBatteryStatus;
    private TextView tvAirplaneStatus;
    private TextView tvCustomMessage;

    private EditText edtMessage;
    private Button btnSendBroadcast;
    private Button btnOpenSecondActivity;
    private Button btnSendGlobalBroadcast;

    private final StringBuilder logBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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

        customReceiver = new CustomReceiver(new CustomReceiver.OnCustomBroadcastListener() {
            @Override
            public void onCustomBroadcastReceived(String message) {
                tvCustomMessage.setText("Nhan duoc: " + message);
                tvCustomMessage.setTextColor(getResources().getColor(R.color.blue));
                appendLog("[CUSTOM] Nhan: " + message);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter networkFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        IntentFilter batteryFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        IntentFilter airplaneFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        IntentFilter customFilter = new IntentFilter(CustomReceiver.CUSTOM_ACTION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(networkReceiver, networkFilter, Context.RECEIVER_NOT_EXPORTED);
            registerReceiver(batteryReceiver, batteryFilter, Context.RECEIVER_NOT_EXPORTED);
            registerReceiver(airplaneModeReceiver, airplaneFilter, Context.RECEIVER_NOT_EXPORTED);
            registerReceiver(customReceiver, customFilter, Context.RECEIVER_EXPORTED);
        } else {
            registerReceiver(networkReceiver, networkFilter);
            registerReceiver(batteryReceiver, batteryFilter);
            registerReceiver(airplaneModeReceiver, airplaneFilter);
            registerReceiver(customReceiver, customFilter);
        }

        appendLog("=== All receivers REGISTERED ===");
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkReceiver);
        unregisterReceiver(batteryReceiver);
        unregisterReceiver(airplaneModeReceiver);
        unregisterReceiver(customReceiver);
        appendLog("=== All receivers UNREGISTERED ===");
    }

    private void sendCustomBroadcast(String message) {
        Intent intent = new Intent(CustomReceiver.CUSTOM_ACTION);
        intent.putExtra(CustomReceiver.EXTRA_MESSAGE, message);
        intent.setPackage(getPackageName());
        sendBroadcast(intent);
    }

    private void initViews() {
        tvNetworkStatus = findViewById(R.id.tvNetworkStatus);
        tvBatteryStatus = findViewById(R.id.tvBatteryStatus);
        tvAirplaneStatus = findViewById(R.id.tvAirplaneStatus);
        tvCustomMessage = findViewById(R.id.tvCustomMessage);
        tvLog = findViewById(R.id.tvLog);
        edtMessage = findViewById(R.id.edtMessage);
        btnSendBroadcast = findViewById(R.id.btnSendBroadcast);
        btnOpenSecondActivity = findViewById(R.id.btnOpenSecondActivity);
        btnSendGlobalBroadcast = findViewById(R.id.btnSendGlobalBroadcast);

        btnSendBroadcast.setOnClickListener(v -> {
            String message = edtMessage.getText().toString().trim();
            if (message.isEmpty()) {
                message = "Xin chao Broadcast";
            }
            sendCustomBroadcast(message);
            appendLog("[ACTION] Gui custom broadcast: " + message);
        });

        btnOpenSecondActivity.setOnClickListener(v -> appendLog("[ACTION] Nut Open Second Activity (chua gan man hinh phu)"));
        btnSendGlobalBroadcast.setOnClickListener(v -> appendLog("[ACTION] Nut Send Global Broadcast (demo log)"));
    }

    private void appendLog(String message) {
        String time = java.text.DateFormat.getTimeInstance().format(new java.util.Date());
        logBuilder.insert(0, time + " - " + message + "\n");
        tvLog.setText(logBuilder.toString());
    }
}