package com.sangptit.quanlysinhviensql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    private static final String DB_Name = "QLSinhvien";
    private static final int DB_Version = 1;

    public Dbhelper(@Nullable Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String lophocSQL = "CREATE TABLE lophocs (ID INTEGER PRIMARY KEY AUTOINCREMENT, tenlop text not null)";
        String sinhvienSQL = "CREATE TABLE sinhviens(id text primary key, hoten text not null, ngaysinh text, lophocid INTEGER, FOREIGN KEY (lophocid) REFERENCES lophocs(ID))";
        sqLiteDatabase.execSQL(lophocSQL);
        sqLiteDatabase.execSQL(sinhvienSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String lophocSQL = "DROP TABLE IF EXISTS lophocs";
        String sinhvienSQL = "DROP TABLE IF EXISTS sinhviens";
        sqLiteDatabase.execSQL(sinhvienSQL);
        sqLiteDatabase.execSQL(lophocSQL);
        onCreate(sqLiteDatabase);
    }
}
