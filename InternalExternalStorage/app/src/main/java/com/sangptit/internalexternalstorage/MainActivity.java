package com.sangptit.internalexternalstorage;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnwriteIS).setOnClickListener(this);
        findViewById(R.id.btnreadIS).setOnClickListener(this);
        findViewById(R.id.btnwriteFC).setOnClickListener(this);
        findViewById(R.id.btnreadFC).setOnClickListener(this);
        findViewById(R.id.btnwriteES).setOnClickListener(this);
        findViewById(R.id.btnreadES).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btnreadIS) {
            String readDataIS = readIS("myfile.txt");
            Toast.makeText(this, "Nôi dung file:" + readDataIS, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.btnwriteIS) {
            writeIS("myfile.txt","Xin chào các bạn!");
        } else if (id == R.id.btnreadFC) {
            String readDataFC = readFC("mycached.cache");
            Toast.makeText(this, "Data cache: " + readDataFC, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.btnwriteFC) {
            writeFC("mycached.cache","Dữ liệu lưu vào cahce");
        } else if (id == R.id.btnwriteES) {
            writeES("mysdcard.txt","Dữ liệu lưu vào thẻ nhớ ngoài");
        } else if (id == R.id.btnreadES) {
            String readDataEX = readES("mysdcard.txt");
            Toast.makeText(this, "Data sdcard: " + readDataEX, Toast.LENGTH_SHORT).show();
        }
    }

    private String readIS(String filename) {
        try (FileInputStream fin = openFileInput(filename)) {
            byte[] buffer = new byte[1024];
            int length = 0;
            length = fin.read(buffer);
            return new String(buffer,0,length);
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return null;
    }

    private void writeIS(String filename,String data) {
        try {
            FileOutputStream fout = openFileOutput(filename,MODE_PRIVATE);
            fout.write(data.getBytes(StandardCharsets.UTF_8));
            fout.close();
            Toast.makeText(this, "Dữ liệu đã được ghi", Toast.LENGTH_SHORT).show();
        } catch ( Exception ex ) {
            Toast.makeText(this, "Error:" + ex.getMessage(), Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }

    private void writeFC(String filename, String data) {
        File cacheDir = getCacheDir();
        try (FileOutputStream fout = new FileOutputStream(new File(cacheDir,filename))){
            fout.write(data.getBytes(StandardCharsets.UTF_8));
            Toast.makeText(this, "Dữ liệu lưu vào cache thành công!", Toast.LENGTH_SHORT).show();
        } catch ( Exception ex ) {
            ex.printStackTrace();
            Toast.makeText(this, "Error:" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private String readFC(String filename) {
        File cacheDir = getCacheDir();
        byte[] buffer = new byte[1024];
        int length = 0;
        try (FileInputStream fin= new FileInputStream(new File(cacheDir, filename))){
            length = fin.read(buffer);
            return new String(buffer,0,length);
        } catch ( Exception ex ) {
            ex.printStackTrace();
            Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    private void writeES(String filename, String data) {
        String sdcard = getExternalFilesDir("") + "/" + filename;
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(sdcard));
            writer.write(data);
            writer.close();
            Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show();
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }

    private String readES(String filename) {
        String sdcard = getExternalFilesDir("") + "/" + filename;
        try {
            Scanner scan = new Scanner(new File(sdcard));
            String data="";
            while (scan.hasNext()){
                data = data + scan.nextLine() + "\n";
            }
            scan.close();
            return data;
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return null;
    }
}