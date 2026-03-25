package com.sangptit.quanlysinhviensql;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnDanhMucLopHoc).setOnClickListener(this);
        findViewById(R.id.btnQuanLySinhVien).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnDanhMucLopHoc) {
            Intent intent = new Intent(MainActivity.this, DanhMucLopHocActivity.class);
            startActivity(intent);
        } else if (id == R.id.btnQuanLySinhVien) {
            Intent intent1 = new Intent(MainActivity.this, QuanLySinhVienActivity.class);
            startActivity(intent1);
        }
    }
}