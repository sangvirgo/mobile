package com.sangptit.quanlysinhviensql;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DanhMucLopHocActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtTenLopHoc;
    ListView lvDanhSachLopHoc;
    List<LopHoc> lopHocList;
    LopHocAdapter lopHocAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc_lop_hoc);

        findViewById(R.id.btnLuuLopHoc).setOnClickListener(this);
        findViewById(R.id.btnThoatLopHoc).setOnClickListener(this);

        edtTenLopHoc = findViewById(R.id.edtTenLopHoc);
        lvDanhSachLopHoc = findViewById(R.id.lvdanhsachlophoc);

        fillLopHocListView();

        lvDanhSachLopHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                LopHocDAO lopHocDAO = new LopHocDAO(DanhMucLopHocActivity.this);
                LopHoc lopHoc = lopHocList.get(i);
                lopHocDAO.delete(lopHoc.getId());
                fillLopHocListView();
                Toast.makeText(DanhMucLopHocActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void fillLopHocListView() {
        LopHocDAO lopHocDAO = new LopHocDAO(this);
        lopHocList = lopHocDAO.getAll();
        lopHocAdapter = new LopHocAdapter(this, lopHocList);
        lvDanhSachLopHoc.setAdapter(lopHocAdapter);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnLuuLopHoc) {
            LopHoc lopHoc = new LopHoc();
            lopHoc.setTenlophoc(edtTenLopHoc.getText().toString());
            LopHocDAO lopHocDAO = new LopHocDAO(this);
            lopHocDAO.insert(lopHoc);
            fillLopHocListView();
            Toast.makeText(this, "Đã lưu lớp học", Toast.LENGTH_LONG).show();
        } else if (id == R.id.btnThoatLopHoc) {
            finish();
        }
    }
}
