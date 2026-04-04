package com.sangptit.demogridviewnangcao;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gvHinhAnh;
    ArrayList<HinhAnh> arrayHinhAnh;
    HinhAnhAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bước 1: Ánh xạ view
        AnhXa();

        // Bước 2: Khởi tạo dữ liệu
        KhoiTaoDuLieu();

        // Bước 3: Tạo adapter và gán cho GridView
        adapter = new HinhAnhAdapter(this, R.layout.dong_hinh_anh, arrayHinhAnh);
        gvHinhAnh.setAdapter(adapter);

        // Bước 4: Xử lý sự kiện click
        gvHinhAnh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Hiển thị thông báo khi click vào item
                Toast.makeText(MainActivity.this,
                        arrayHinhAnh.get(position).getTen(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý sự kiện long click
        gvHinhAnh.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        "Bạn đã long click: " + arrayHinhAnh.get(position).getTen(),
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void AnhXa() {
        gvHinhAnh = findViewById(R.id.gvHinhAnh);
        arrayHinhAnh = new ArrayList<>();
    }

    private void KhoiTaoDuLieu() {
        // Thêm dữ liệu vào ArrayList
        arrayHinhAnh.add(new HinhAnh(R.drawable.android1, "Hình số 1"));
        arrayHinhAnh.add(new HinhAnh(R.drawable.android2, "Hình số 2"));
        arrayHinhAnh.add(new HinhAnh(R.drawable.android3, "Hình số 3"));
        arrayHinhAnh.add(new HinhAnh(R.drawable.android4, "Hình số 4"));
        arrayHinhAnh.add(new HinhAnh(R.drawable.android5, "Hình số 5"));
        arrayHinhAnh.add(new HinhAnh(R.drawable.android6, "Hình số 6"));
        arrayHinhAnh.add(new HinhAnh(R.drawable.android7, "Hình số 7"));
        arrayHinhAnh.add(new HinhAnh(R.drawable.android8, "Hình số 8"));
        arrayHinhAnh.add(new HinhAnh(R.drawable.android9, "Hình số 9"));
    }
}