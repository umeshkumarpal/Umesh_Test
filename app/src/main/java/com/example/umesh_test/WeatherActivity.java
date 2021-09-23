package com.example.umesh_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umesh_test.WeatherModel.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {
    EditText city_Name;
    TextView temp_centigrete, temp_farhnheit, latitute, longtitute;
    Button show_Result_Button;
    String Name, name;

    Float Tem_c;
    Float Tem_f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        city_Name = findViewById(R.id.cityName);
        show_Result_Button = findViewById(R.id.showResult);
        temp_centigrete = findViewById(R.id.centigrate);
        temp_farhnheit = findViewById(R.id.fahrenheit);
        latitute = findViewById(R.id.latitute);
        longtitute = findViewById(R.id.longtude);

        show_Result_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (city_Name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "fill", Toast.LENGTH_SHORT);
                } else {
                    LoadWeatherApi();
                }
            }
        });

    }


    private void LoadWeatherApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<Weather> call = api.getWeatherData("35c9f92ac5bf4df0811144140212307", city_Name.getText().toString().trim());

        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                Weather list = response.body();
                Float lat = list.getLocation().getLat();
                Float lon = list.getLocation().getLon();
                Float temc = list.getCurrent().getTemp_c();
                Float temf = list.getCurrent().getTemp_f();

                temp_centigrete.setText(Float.toString(temc));
                temp_farhnheit.setText(Float.toString(temf));
                latitute.setText(Float.toString(lat));
                longtitute.setText(Float.toString(lon));


            }


            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}