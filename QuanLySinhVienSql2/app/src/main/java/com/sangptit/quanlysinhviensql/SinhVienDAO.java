package com.sangptit.quanlysinhviensql;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO {
    private SQLiteDatabase db;

    public SinhVienDAO(Context context) {
        Dbhelper dbhelper = new Dbhelper(context);
        this.db = dbhelper.getWritableDatabase();
    }

    public long insert(SinhVien sinhVien) {
        ContentValues values = new ContentValues();
        values.put("id", sinhVien.getId());
        values.put("hoten", sinhVien.getHoten());
        values.put("ngaysinh", DateTimeHelper.toString(sinhVien.getNgaysinh()));
        values.put("lophocid", sinhVien.getLophocid());
        return db.insert("sinhviens", null, values);
    }

    @SuppressLint("Range")
    public List<SinhVien> get(String sql, String... selectArgs) throws ParseException {
        List<SinhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()) {
            SinhVien sinhVien = new SinhVien();
            sinhVien.setId(cursor.getString(cursor.getColumnIndex("id")));
            sinhVien.setHoten(cursor.getString(cursor.getColumnIndex("hoten")));
            sinhVien.setNgaysinh(DateTimeHelper.toDate(cursor.getString(cursor.getColumnIndex("ngaysinh"))));
            sinhVien.setLophocid(cursor.getInt(cursor.getColumnIndex("lophocid")));
            list.add(sinhVien);
        }
        cursor.close();
        return list;
    }

    public List<SinhVien> getAll() throws ParseException {
        String sql = "SELECT * FROM sinhviens";
        return get(sql);
    }

    public List<SinhVien> getAllByLophoc(Integer lophocid) throws ParseException {
        String sql = "SELECT * FROM sinhviens where lophocid = ?";
        return get(sql, String.valueOf(lophocid));
    }
}
