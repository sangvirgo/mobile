package com.sangptit.demoweatherapi.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sangptit.demoweatherapi.R;
import com.sangptit.demoweatherapi.adapter.WeatherAdapter;
import com.sangptit.demoweatherapi.model.WeatherForecastModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txtCityName;
    private TextView txtTemperature;
    private TextView txtCityTemperature;
    private EditText editTextTextPersonName;
    private ImageView imgSearch;
    private ImageView imgSeasonForecast;
    private ListView lvWeatherForecast;

    private List<WeatherForecastModel> weatherForecastModelList;
    private WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCityName = findViewById(R.id.txtCityName);
        txtTemperature = findViewById(R.id.txtTemperature);
        txtCityTemperature = findViewById(R.id.txtCityTemperature);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        imgSearch = findViewById(R.id.imgSearch);
        imgSeasonForecast = findViewById(R.id.imgSeasonForecast);
        lvWeatherForecast = findViewById(R.id.lvWeatherForecast);

        weatherForecastModelList = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(this, weatherForecastModelList);
        lvWeatherForecast.setAdapter(weatherAdapter);

        GetData("Hanoi");

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = editTextTextPersonName.getText().toString().trim();
                if (city.isEmpty()) {
                    return;
                }
                GetData(city);
            }
        });
    }

    private void GetData(String cityName) {
        String url = "https://api.weatherapi.com/v1/forecast.json?key=fc8f4188ad3f48d8a10132707221212&q="
                + cityName + "&days=1&aqi=no&alerts=no";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        Log.d("BBB", url.toString());
        txtCityName.setText(cityName);
        weatherForecastModelList.clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //String temperature = response.getJSONObject("current").getString("temp_c");
                        try {
                            String temperature = response.getJSONObject("current").getString("temp_c");
                            txtTemperature.setText(temperature + "°C");

                            String condition = response.getJSONObject("current")
                                    .getJSONObject("condition").getString("text");
                            String conditionicon = response.getJSONObject("current")
                                    .getJSONObject("condition").getString("icon");
                            txtCityTemperature.setText(condition);
                            Picasso.get().load("https:" + conditionicon).into(imgSeasonForecast);
                            //Log.d("AAA",""+conditionicon);

                            JSONObject forecastObj = response.getJSONObject("forecast");
                            JSONObject forecast0 = forecastObj.getJSONArray("forecastday").getJSONObject(0);
                            JSONArray hourArray = forecast0.getJSONArray("hour");

                            for (int i = 0; i < hourArray.length(); i++) {
                                JSONObject hourObj = hourArray.getJSONObject(i);
                                String time = hourObj.getString("time");
                                String temper = hourObj.getString("temp_c");
                                String img = hourObj.getJSONObject("condition").getString("icon");
                                String wind = hourObj.getString("wind_kph");
                                Log.d("AAA", " " + time + " " + temper + " " + img + " " + wind);
                                weatherForecastModelList.add(new WeatherForecastModel(time, temper, img, wind));
                            }
                            weatherAdapter.notifyDataSetChanged();
                            //Toast.makeText(MainActivity.this, "" + temperature , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("AAA", "Có lổi xảy ra" + url.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
