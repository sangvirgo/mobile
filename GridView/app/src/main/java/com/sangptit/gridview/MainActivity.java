package com.sangptit.gridview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    GridView gvTen;
    String[] arrayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bước 1: Ánh xạ
        gvTen = findViewById(R.id.gvTen);

        // Bước 2: Tạo dữ liệu (mảng A-Z)
        arrayName = new String[]{
                "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X",
                "Y", "Z"
        };

        // Bước 3: Tạo Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,  // Layout có sẵn
                arrayName
        );

        // Bước 4: Gắn Adapter vào GridView
        gvTen.setAdapter(adapter);

        // Bước 5: Sự kiện click
        gvTen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        "Bạn chọn: " + arrayName[position],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
