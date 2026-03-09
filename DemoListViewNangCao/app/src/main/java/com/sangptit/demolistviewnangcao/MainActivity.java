package com.sangptit.demolistviewnangcao;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvCity;
    ArrayList<City> cityArrayList;
    CityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ
        lvCity = findViewById(R.id.lvCity);

        // Tạo dữ liệu
        cityArrayList = new ArrayList<>();
        cityArrayList.add(new City("New York", R.drawable.newyork, "https://en.wikipedia.org/wiki/New_York_City"));
        cityArrayList.add(new City("Paris", R.drawable.paris, "https://en.wikipedia.org/wiki/Paris"));
        cityArrayList.add(new City("Rome", R.drawable.rome, "https://en.wikipedia.org/wiki/Rome"));
//        cityArrayList.add(new City("Tokyo", R.drawable.newyork, "https://en.wikipedia.org/wiki/Tokyo"));
//        cityArrayList.add(new City("London", R.drawable.paris, "https://en.wikipedia.org/wiki/London"));

        // Gắn Adapter
        adapter = new CityAdapter(this, R.layout.dong_thanh_pho, cityArrayList);
        lvCity.setAdapter(adapter);

        // Sự kiện click item
        lvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                City selectedCity = cityArrayList.get(position);
                Toast.makeText(MainActivity.this,
                        "Bạn chọn: " + selectedCity.getNameCity() + "\n" + selectedCity.getLinkWiki(),
                        Toast.LENGTH_LONG).show();
            }
        });

        // Sự kiện long click (xóa item)
        lvCity.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                City cityToRemove = cityArrayList.get(position);

                Toast.makeText(MainActivity.this,
                        "Đã xóa: " + cityToRemove.getNameCity(),
                        Toast.LENGTH_SHORT).show();

                cityArrayList.remove(position);
                adapter.notifyDataSetChanged();

                return true; // true = đã xử lý sự kiện
            }
        });
    }
}